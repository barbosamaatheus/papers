import os
import json
from collections import Counter
from utils.conflict_processor import ConflictProcessor
from OA.conflict_types_distribution import generate_report_oa, generate_scenario_report_oa
from DF.conflict_types_distribution import generate_report_df, generate_scenario_report_df
from CF.conflict_types_distribution import generate_report_cf
from CF.cf_processor import CFConflictProcessor

class ConflictAnalyzer(ConflictProcessor):
    """High-level analyzer that builds on `ConflictProcessor` to generate reports.

    This class locates and loads conflict payloads from `out.json` files and
    delegates report generation to specific report modules per conflict type.
    """
    def __init__(self):
        """Initialize analyzer and default output directory."""
        super().__init__()
        self.output_dir = '.'        
        
    def _load_conflicts(self, json_path):
        """Load the `out.json` file from `json_path` and return its conflicts list.

        Returns an empty list when the file is missing or malformed.
        """
        if not os.path.exists(json_path):
            return []

        with open(json_path, "r", encoding="utf-8") as json_file:
            payload = json.load(json_file)

        if isinstance(payload, dict):
            conflicts = payload.get("conflicts", [])
            return conflicts if isinstance(conflicts, list) else []

        if isinstance(payload, list):
            conflicts = []
            for entry in payload:
                if not isinstance(entry, dict):
                    continue
                if isinstance(entry.get("conflicts"), list):
                    conflicts.extend(entry["conflicts"])
                elif "body" in entry and "interference" in entry["body"]:
                    conflicts.append(entry)
            return conflicts

        return []

    def _collect_conflicts(self, payload):
        """Flatten conflict entries from dict or list payloads."""
        if isinstance(payload, dict):
            conflicts = payload.get("conflicts", [])
            return conflicts if isinstance(conflicts, list) else []

        if isinstance(payload, list):
            conflicts = []
            for entry in payload:
                if not isinstance(entry, dict):
                    continue
                if isinstance(entry.get("conflicts"), list):
                    conflicts.extend(entry["conflicts"])
                elif "body" in entry and "interference" in entry["body"]:
                    conflicts.append(entry)
            return conflicts

        return []

    def _filter_conflicts_same_stack(self, conflicts):
        def _normalize_file_key(value):
            """Normalize various file/location representations into a canonical key.

            Examples handled: full paths, filenames with extensions, or simple identifiers.
            Returns `None` when `value` is falsy.
            """
            if not value:
                return None
            lowers = value.lower()
            known_exts = ('.java', '.py', '.kt', '.scala', '.js', '.ts', '.c', '.cpp', '.h', '.cs')
            if any(lowers.endswith(ext) for ext in known_exts) or '/' in value or '\\' in value:
                return os.path.splitext(os.path.basename(value))[0]
            if "." in value and "/" not in value and "\\" not in value:
                return value.rsplit(".", 1)[-1]
            return os.path.splitext(os.path.basename(value))[0]

        def _get_interf_details(conflict):
            interf = conflict.get("body", {}).get("interference", [])
            loc_0 = interf[0].get("location", {})
            loc_last = interf[-1].get("location", {})
            file_0 = _normalize_file_key(loc_0.get("file") or loc_0.get("class"))
            file_last = _normalize_file_key(loc_last.get("file") or loc_last.get("class"))
            line_0 = int(loc_0.get("line"))
            line_last = int(loc_last.get("line"))
            return file_0, file_last, line_0, line_last

        unique_conflicts = []
        for c in conflicts:
            if not any(_get_interf_details(c) == _get_interf_details(uc) for uc in unique_conflicts):
                unique_conflicts.append(c)

        return unique_conflicts
        
    def analyze(self, processor, output_dir, payload):
        """Analyze conflicts for `output_dir` and produce reports.

        Supports both single-scenario and multi-scenario out.json files.

        Single scenario: {"modifiedLines": [...], "conflicts": [...]}
        Multi-scenario: [{"modifiedLines": [...], "conflicts": [...]}, ...]

        Args:
            processor (ConflictProcessor): The conflict processor to use.
            output_dir (str): Directory containing the `out.json` payload.
            payload (str | dict | list): Loaded JSON payload or a path to it.
        """
        self.output_dir = output_dir
        if isinstance(processor, CFConflictProcessor):
            generate_report_cf(processor, self.modified_lines_map, output_dir)
            if hasattr(processor, 'export_metrics_to_json'):
                processor.export_metrics_to_json()
            return

        if isinstance(payload, str):
            with open(payload, "r", encoding="utf-8") as f:
                payload_data = json.load(f)
        else:
            payload_data = payload

        # Handle both single and multi-scenario formats
        scenarios = []
        if isinstance(payload_data, dict):
            scenarios = [payload_data]
        elif isinstance(payload_data, list):
            scenarios = [s for s in payload_data if isinstance(s, dict)]

        # Process each scenario independently and accumulate results
        all_oa_types_counter = Counter()
        all_oa_details = []
        all_df_types_counter = Counter()
        all_df_details = []

        for scenario_idx, scenario_data in enumerate(scenarios):
            # Build modified_lines for THIS scenario only
            self._build_modified_lines_map(scenario_data)
            scenario_modified_lines = self.modified_lines_map.copy()

            # Extract conflicts from this scenario
            if isinstance(scenario_data.get("conflicts"), list):
                conflicts = scenario_data["conflicts"]
            else:
                conflicts = []

            oa_conflicts = [c for c in conflicts if 'OA' in c.get('type', '')]
            df_conflicts = [c for c in conflicts if 'CONFLICT' in c.get('type', '')]

            oa_conflicts = self._filter_conflicts_same_stack(oa_conflicts)
            df_conflicts = self._filter_conflicts_same_stack(df_conflicts)

            types_counter_oa, details_oa = generate_scenario_report_oa(oa_conflicts, scenario_modified_lines, scenario_idx=scenario_idx)
            types_counter_df, details_df = generate_scenario_report_df(df_conflicts, scenario_modified_lines, scenario_idx=scenario_idx)

            all_oa_types_counter.update(types_counter_oa)
            all_df_types_counter.update(types_counter_df)
            all_oa_details.extend(details_oa)
            all_df_details.extend(details_df)

        # Generate combined reports with all accumulated conflicts
        # Each conflict carries its scenario's modified_lines for independent processing
        if len(all_oa_types_counter) and len(all_oa_details):
            generate_report_oa(all_oa_types_counter, all_oa_details, output_dir)
        if len(all_df_types_counter) and len(all_df_details):
            generate_report_df(all_df_types_counter, all_df_details, output_dir)

        if processor and hasattr(processor, 'export_metrics_to_json'):
            processor.export_metrics_to_json("OA", output_dir)
            processor.export_metrics_to_json("DF", output_dir)