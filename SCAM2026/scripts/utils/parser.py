import json
import os
from collections import defaultdict
from utils.conflict_processor import ConflictProcessor
from CF.cf_processor import CFConflictProcessor

class ConflictParser:
    def __init__(self, output_dir="."):
        """Class to process conflict entries and accumulate metrics for analysis and reporting.

        Args:
            output_dir (str): Path used as base for output files. Defaults to current dir.
        """
        self.output_dir = output_dir
        self.conflict_data = {
            "l_lengths": defaultdict(list),
            "r_lengths": defaultdict(list),
            "same_class": defaultdict(list),
            "same_method": defaultdict(list),
            "start_same_class": defaultdict(list),
            "start_same_method": defaultdict(list),
            "diffs": defaultdict(list),
            "depth": defaultdict(list),
            "jar_map": defaultdict(set),
            "rows": [],
            "conflict_idx": 0,
            "same_depth_count": 0,
        }
        self.current_conflict_type = None

    def process_conflicts(self, conflicts, start_idx=0):
        """Process a list of conflict entries and accumulate metrics.

        Args:
            conflicts (list): List of conflict entries (dicts) as parsed from JSON.
            start_idx (int): Index used to partition results per input file/group.
        """
        for entry in conflicts:
            if (
                not isinstance(entry, dict)
                or "body" not in entry
                or "interference" not in entry["body"]
            ):
                raise Exception("Invalid entry format", entry)

            scenario_jar = entry.get("ScenarioJAR")
            self.conflict_data["jar_map"][start_idx].add(scenario_jar)

            interference = entry["body"]["interference"]
            if not isinstance(interference, list) or len(interference) < 2:
                raise Exception("Invalid interference format", entry)

            event_type = (entry.get("type") or "")
            if "OA" in event_type:
                self._process_interference(interference, scenario_jar, start_idx)
            elif "CONFLICT" in event_type:
                self._process_df_conflict(interference, scenario_jar, start_idx)
    
    def _process_df_conflict(self, interference, scenario_jar, start_idx):
        """Process DF/CONFLICT events that may contain more than two nodes.

        Heuristic:
        - Choose the first node whose 'type' contains 'Source' as the left node.
        - Choose the last node whose 'type' contains 'Sink' as the right node.
        - Fallback to first and last nodes when Source/Sink not found.

        The resulting left/right stacks are passed to the same metrics collector
        used by OA conflicts so the CSV output format stays identical.
        """
        self.current_conflict_type = "DF"
        # Find left (source) node
        left_node = None
        right_node = None
        for node in interference:
            ntype = (node.get("type") or "")
            if "Source" in ntype and left_node is None:
                left_node = node

        # Find right (sink) node by scanning reversed
        for node in reversed(interference):
            ntype = (node.get("type") or "")
            if "Sink" in ntype and right_node is None:
                right_node = node

        # Fallbacks
        if left_node is None:
            left_node = interference[0]
        if right_node is None:
            right_node = interference[-1]

        self._process_node_pair(left_node, right_node, scenario_jar, start_idx)
    
    def _process_node_pair(self, left_node, right_node, scenario_jar, start_idx):
        """Extract node pair information without storing metrics.

        Metrics will be calculated later by conflict processors after filtering.
        This method is kept for compatibility but doesn't store metrics anymore.
        """
        # Node information processing moved to individual conflict processors
        pass

    def _process_interference(self, interference, scenario_jar, start_idx):
        """Process OA interference events, which should contain exactly two nodes."""
        if len(interference) != 2:
            raise Exception("OA interference should have exactly 2 nodes", interference)

        self.current_conflict_type = "OA"
        left_node = interference[0]
        right_node = interference[1]

        self._process_node_pair(left_node, right_node, scenario_jar, start_idx)
        
    def _store_metrics(
        self,
        l_len,
        r_len,
        diff,
        max_len,
        same_class,
        same_method,
        same_start_class,
        same_start_method,
        scenario_jar,
        start_idx,
    ):
        """Store computed metrics for one processed conflict pair.

        This appends per-index metrics into `self.conflict_data` and increments
        the global conflict counter used for CSV/row generation.
        """
        data = self.conflict_data
        data["l_lengths"][start_idx].append(l_len)
        data["r_lengths"][start_idx].append(r_len)
        data["diffs"][start_idx].append(diff)
        data["depth"][start_idx].append(max_len)
        data["same_class"][start_idx].append(same_class)
        data["same_method"][start_idx].append(same_method)
        data["start_same_class"][start_idx].append(same_start_class)
        data["start_same_method"][start_idx].append(same_start_method)

        data["rows"].append(
            [
                data["conflict_idx"],
                l_len,
                r_len,
                max_len,
                diff,
                same_class,
                same_method,
                same_start_class,
                same_start_method,
                scenario_jar,
                start_idx,
                self.current_conflict_type,
            ]
        )
        data["conflict_idx"] += 1

    def export_metrics_to_json(self, conflict_type, output_dir:str|None=None):
        """Export metrics for a specific conflict type to JSON.

        Args:
            conflict_type (str): Type of conflict to export ("OA" or "DF").

        Returns:
            str: Path to the exported JSON file.
        """
        filtered_rows = [row for row in self.conflict_data["rows"] if row[11] == conflict_type]

        if not filtered_rows:
            return None

        export_data = {
            "summary": {
                "total_conflicts": len(filtered_rows),
                "same_depth_count": sum(1 for row in filtered_rows if row[1] == row[2]),
            },
            "rows": [
                {
                    "conflict_idx": row[0],
                    "l_len": row[1],
                    "r_len": row[2],
                    "max_len": row[3],
                    "diff": row[4],
                    "same_class": row[5],
                    "same_method": row[6],
                    "same_start_class": row[7],
                    "same_start_method": row[8],
                    "scenario_jar": row[9],
                    "scenario_index": row[10],
                }
                for row in filtered_rows
            ]
        }

        filename = f"{conflict_type.lower()}_metrics.json"
        directory = f"{conflict_type.upper()}"
        if not output_dir:
            output_dir = self.output_dir
        
        output_path = os.path.join(output_dir, directory, filename)

        with open(output_path, "w", encoding="utf-8") as f:
            json.dump(export_data, f, indent=2)

        return output_path


def _process_json(json_path):
    """Load and process a single out.json file, returning (processor, output_dir)."""
    output_dir = os.path.dirname(os.path.abspath(json_path)) or "."
    processor = ConflictParser(output_dir)
    cf_processor = CFConflictProcessor()
    modified_lines_helper = ConflictProcessor()

    def _default_scenario_jar():
        """Return a default scenario jar name derived from the json filename."""
        return os.path.splitext(os.path.basename(json_path))[0]

    def _normalize_conflict_entry(entry, scenario_jar):
        """Ensure an entry has `ScenarioJAR` and return a shallow copy.

        Args:
            entry (dict): Original conflict entry.
            scenario_jar (str): Scenario jar name to set when missing.

        Returns:
            dict: Shallow copy of entry with `ScenarioJAR` ensured.
        """
        normalized_entry = dict(entry)
        normalized_entry.setdefault("ScenarioJAR", scenario_jar)
        normalized_entry["__modifiedLines"] = normalized_entry.get("__modifiedLines", {})
        return normalized_entry

    def _build_modified_lines_map(modified_lines):
        payload = {
            "modifiedLines": modified_lines if isinstance(modified_lines, list) else []
        }
        modified_lines_helper._build_modified_lines_map(payload)
        return modified_lines_helper.modified_lines_map.copy()

    def _is_cf_entry(entry):
        label = str(entry.get("label") or "").lower()
        if "cf conflict" in label:
            return True

        interference = entry.get("body", {}).get("interference", [])
        if not isinstance(interference, list):
            return False

        node_types = {
            str(node.get("type") or "").lower()
            for node in interference
            if isinstance(node, dict)
        }
        return {"source1", "source2", "confluence"}.issubset(node_types)

    def _dispatch_conflicts(conflicts, scenario_jar, scenario_modified_lines, start_idx):
        regular_conflicts = []
        cf_conflicts = []

        for conflict in conflicts:
            if not isinstance(conflict, dict):
                continue

            normalized_conflict = _normalize_conflict_entry(conflict, scenario_jar)
            normalized_conflict["__scenarioIndex"] = start_idx
            normalized_conflict["__modifiedLines"] = scenario_modified_lines
            if _is_cf_entry(normalized_conflict):
                cf_conflicts.append(normalized_conflict)
            else:
                regular_conflicts.append(normalized_conflict)

        if regular_conflicts:
            processor.process_conflicts(regular_conflicts, start_idx=start_idx)

        if cf_conflicts:
            cf_processor.process_conflicts(cf_conflicts, source_name=os.path.basename(json_path), scenario_idx=start_idx)

    with open(json_path, "r", encoding="utf-8-sig") as f:
        data = json.load(f)

    if isinstance(data, dict):
        scenario_jar = _default_scenario_jar()
        scenario_modified_lines = _build_modified_lines_map(data.get("modifiedLines", []))
        if isinstance(data.get("conflicts"), list):
            _dispatch_conflicts(data["conflicts"], scenario_jar, scenario_modified_lines, start_idx=0)
        else:
            raise Exception("Invalid conflict data format")
    elif isinstance(data, list):
        for idx, entry in enumerate(data):
            if not isinstance(entry, dict):
                raise Exception("Invalid conflict data format")

            scenario_jar = entry.get("ScenarioJAR") or _default_scenario_jar()
            scenario_modified_lines = _build_modified_lines_map(entry.get("modifiedLines", []))

            if isinstance(entry.get("conflicts"), list):
                _dispatch_conflicts(
                    entry["conflicts"],
                    scenario_jar,
                    scenario_modified_lines,
                    start_idx=idx,
                )
            elif "body" in entry and "interference" in entry["body"]:
                _dispatch_conflicts([entry], scenario_jar, scenario_modified_lines, start_idx=idx)
            else:
                raise Exception("Invalid conflict data format")

    for idx in processor.conflict_data["jar_map"]:
        if len(processor.conflict_data["jar_map"][idx]) > 1:
            raise Exception(
                "Multiple scenario jars found",
                processor.conflict_data["jar_map"][idx],
                idx,
            )  

    return (
        processor if processor.conflict_data["rows"] else cf_processor if cf_processor.conflicts else None,
        output_dir,
        data,
    )