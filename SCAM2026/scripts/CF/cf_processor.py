from .cf_classifier import CFConflictClassifier
from .cf_models import CFNodeSnapshot, CFConflictSnapshot
from .cf_reporter import CFConflictReporter


class CFConflictProcessor:
    def __init__(self, output_dir=".", debug=False):
        self.output_dir = output_dir
        self.debug = debug
        self.classifier = CFConflictClassifier()
        self.conflicts = []
        self.report_path = None
        self.source_name = None
        self.metrics = []
        self.conflict_idx = 0

    def _calculate_node_metrics(self, node):
        """Extract metrics from a single node."""
        if node is None:
            return {
                "stack_length": 0,
                "class": None,
                "method": None,
                "start_class": None,
                "start_method": None,
            }

        stack_trace = node.get("stackTrace", [])
        stack_length = len(stack_trace)
        location = node.get("location", {})
        node_class = location.get("class")
        node_method = location.get("method")
        start_class = stack_trace[0].get("class") if stack_trace else None
        start_method = stack_trace[0].get("method") if stack_trace else None

        return {
            "stack_length": stack_length,
            "class": node_class,
            "method": node_method,
            "start_class": start_class,
            "start_method": start_method,
        }

    def _store_cf_metrics(
        self,
        source1_metrics,
        source2_metrics,
        confluence_metrics,
        scenario_jar,
        start_idx,
    ):
        """Store computed metrics for a CF conflict."""
        s1_len = source1_metrics["stack_length"]
        s2_len = source2_metrics["stack_length"]
        c_len = confluence_metrics["stack_length"]
        diff_s1_s2 = abs(s1_len - s2_len)
        max_len_s1_s2 = max(s1_len, s2_len)

        same_class = (
            source1_metrics["class"] == source2_metrics["class"]
            and source1_metrics["class"] is not None
        )
        same_method = (
            same_class
            and source1_metrics["method"] == source2_metrics["method"]
            and source1_metrics["method"] is not None
        )

        same_start_class = (
            source1_metrics["start_class"] == source2_metrics["start_class"]
            and source1_metrics["start_class"] is not None
        )
        same_start_method = (
            source1_metrics["start_method"] == source2_metrics["start_method"]
            and source1_metrics["start_method"] is not None
        )

        self.metrics.append(
            {
                "conflict_idx": self.conflict_idx,
                "source1_len": s1_len,
                "source2_len": s2_len,
                "confluence_len": c_len,
                "source1_vs_source2_diff": diff_s1_s2,
                "source1_vs_source2_max_len": max_len_s1_s2,
                "same_class": same_class,
                "same_method": same_method,
                "same_start_class": same_start_class,
                "same_start_method": same_start_method,
                "source1_class": source1_metrics["class"],
                "source1_method": source1_metrics["method"],
                "source2_class": source2_metrics["class"],
                "source2_method": source2_metrics["method"],
                "confluence_class": confluence_metrics["class"],
                "confluence_method": confluence_metrics["method"],
                "scenario_jar": scenario_jar,
                "scenario_index": start_idx,
            }
        )
        self.conflict_idx += 1

    @staticmethod
    def _node_role_map(interference):
        role_map = {"source1": None, "source2": None, "confluence": None}
        fallback_sources = []

        for node in interference:
            node_type = str(node.get("type", "")).lower()
            if "source1" in node_type:
                role_map["source1"] = node
            elif "source2" in node_type:
                role_map["source2"] = node
            elif "confluence" in node_type:
                role_map["confluence"] = node
            else:
                fallback_sources.append(node)

        if role_map["source1"] is None and fallback_sources:
            role_map["source1"] = fallback_sources.pop(0)
        if role_map["source2"] is None and fallback_sources:
            role_map["source2"] = fallback_sources.pop(0)
        if role_map["confluence"] is None and fallback_sources:
            role_map["confluence"] = fallback_sources.pop(0)

        return role_map

    def _build_node_snapshot(self, role, node):
        if node is None:
            return CFNodeSnapshot(
                role=role,
                node_type="",
                branch=None,
                text=None,
                location={},
                stack_files=[],
                stack_trace=[],
            )

        files = self.classifier._extract_files(node) if role != "confluence" else []
        if role == "confluence" and not files:
            confluence_file = self.classifier._extract_confluence_file(node)
            if confluence_file:
                files = [confluence_file]

        return CFNodeSnapshot(
            role=role,
            node_type=str(node.get("type", "")),
            branch=node.get("branch"),
            text=node.get("text"),
            location=node.get("location", {}) or {},
            stack_files=files,
            stack_trace=node.get("stackTrace", []) or [],
        )

    def process_conflicts(self, conflicts, start_idx=0, source_name=None, scenario_idx=None):
        if source_name is not None:
            self.source_name = source_name

        # If scenario_idx not explicitly provided, try to get it from conflict or use default
        if scenario_idx is None:
            scenario_idx = start_idx

        for conflict in conflicts:
            if not isinstance(conflict, dict):
                continue

            if str(conflict.get("label", "")).strip().lower() != "cf conflict":
                continue

            scenario_jar = conflict.get("ScenarioJAR", "Unknown")
            print(f"Processing CF: scenario {scenario_idx} ({scenario_jar}), conflict {self.conflict_idx}")

            body = conflict.get("body", {}) or {}
            interference = body.get("interference", []) or []
            if len(interference) < 3:
                continue

            role_map = self._node_role_map(interference)
            source1 = self._build_node_snapshot("source1", role_map["source1"])
            source2 = self._build_node_snapshot("source2", role_map["source2"])
            confluence = self._build_node_snapshot("confluence", role_map["confluence"])

            # Check for symmetric modified lines (source1 and source2 have identical stacks)
            source1_stack = role_map["source1"].get("stackTrace", []) if role_map["source1"] else []
            source2_stack = role_map["source2"].get("stackTrace", []) if role_map["source2"] else []

            # Extract file-line tuples for comparison
            source1_tuples = [(f.get("file", ""), f.get("line")) for f in source1_stack if f.get("file") or f.get("line")]
            source2_tuples = [(f.get("file", ""), f.get("line")) for f in source2_stack if f.get("file") or f.get("line")]

            # Check if either source has any modified frames
            from utils.conflict_processor import ConflictProcessor
            processor = ConflictProcessor()
            modified_lines = conflict.get("__scenario_modified_lines") or conflict.get("__modifiedLines") or {}
            source1_has_modified = False
            source2_has_modified = False

            if modified_lines:
                # Helper to get file key from frame (checks file, location.file, and class)
                def get_frame_file_key(frame):
                    file_val = frame.get("file")
                    if not file_val:  # Handles None, empty string, and missing keys
                        file_val = frame.get("location", {}).get("file")
                    if not file_val:
                        file_val = frame.get("class")
                    return processor._normalize_file_key(file_val) if file_val else None

                # Check source1 stack
                for frame in source1_stack:
                    file_key = get_frame_file_key(frame)
                    line = frame.get("line")
                    if file_key and line is not None:
                        entry = processor._get_modified_lines_entry(file_key, modified_lines)
                        if entry and (line in entry.get('leftAdded', set()) or line in entry.get('leftRemoved', set()) or
                                     line in entry.get('rightAdded', set()) or line in entry.get('rightRemoved', set())):
                            source1_has_modified = True
                            break

                # Check source2 stack
                for frame in source2_stack:
                    file_key = get_frame_file_key(frame)
                    line = frame.get("line")
                    if file_key and line is not None:
                        entry = processor._get_modified_lines_entry(file_key, modified_lines)
                        if entry and (line in entry.get('leftAdded', set()) or line in entry.get('leftRemoved', set()) or
                                     line in entry.get('rightAdded', set()) or line in entry.get('rightRemoved', set())):
                            source2_has_modified = True
                            break

            overlapping_lines = {}
            if source1_tuples and source2_tuples and source1_tuples == source2_tuples:
                # Extract modified lines if available
                modified_lines = conflict.get("__scenario_modified_lines") or conflict.get("__modifiedLines") or {}
                if modified_lines:
                    for fkey, entry in modified_lines.items():
                        left_mods = entry.get('leftAdded', set()) | entry.get('leftRemoved', set())
                        right_mods = entry.get('rightAdded', set()) | entry.get('rightRemoved', set())
                        overlap = left_mods & right_mods
                        if overlap:
                            overlapping_lines[fkey] = sorted(list(overlap))

                if self.debug:
                    print("\n" + "="*80)
                    print("ERROR (CF): Symmetric modified lines detected")
                    print(f"Source1 and Source2 stacks are identical: {source1_tuples}")
                    if overlapping_lines:
                        print("Overlapping modified lines (modified by both branches):")
                        for fkey, lines in overlapping_lines.items():
                            print(f"  {fkey}: {lines}")
                    else:
                        print("No overlapping modifications found")
                    print("="*80 + "\n")

            classification_data = self.classifier.classify_conflict(
                role_map["source1"] or {},
                role_map["source2"] or {},
                role_map["confluence"] or {},
                modified_lines=modified_lines,
            )

            # Override classification if errors are detected
            classification = classification_data["classification"]

            # Check for unmodified stack trace (if any source has no modified frames)
            if modified_lines and (not source1_has_modified or not source2_has_modified):
                classification = "Error: unmodified_stack_trace"
            # Check for symmetric error
            elif source1_tuples and source2_tuples and source1_tuples == source2_tuples:
                classification = "Error: symmetric_modified_lines"

            # Calculate metrics for this CF conflict
            from utils.conflict_processor import ConflictProcessor
            metrics = ConflictProcessor.calculate_conflict_metrics(
                role_map["source1"],
                role_map["source2"]
            )

            snapshot = CFConflictSnapshot(
                index=len(self.conflicts),
                scenario_index=scenario_idx,
                scenario_jar=conflict.get("ScenarioJAR"),
                label=conflict.get("label", "CF conflict"),
                event_type=conflict.get("type", ""),
                classification=classification,
                reason="source order is ignored; classification uses ordered file sequences for each source and the confluence file",
                source1=source1,
                source2=source2,
                confluence=confluence,
                canonical_signature=classification_data["canonical_signature"],
            )
            self.conflicts.append(snapshot)
            # Update classification_data to reflect any overrides made for errors
            classification_data["classification"] = classification
            snapshot._classification_data = classification_data  # type: ignore[attr-defined]
            snapshot._metrics = metrics  # type: ignore[attr-defined]

            source1_metrics = self._calculate_node_metrics(role_map["source1"])
            source2_metrics = self._calculate_node_metrics(role_map["source2"])
            confluence_metrics = self._calculate_node_metrics(role_map["confluence"])
            self._store_cf_metrics(
                source1_metrics,
                source2_metrics,
                confluence_metrics,
                conflict.get("ScenarioJAR"),
                start_idx,
            )

        return self.conflicts

    def build_report(self):
        distribution = {}
        for snapshot in self.conflicts:
            distribution[snapshot.classification] = distribution.get(snapshot.classification, 0) + 1

        # Calculate per-scenario statistics
        scenarios_stats = {}
        for snapshot in self.conflicts:
            scenario_idx = snapshot.scenario_index
            if scenario_idx not in scenarios_stats:
                scenarios_stats[scenario_idx] = {
                    "scenario_index": scenario_idx,
                    "scenario_jar": snapshot.scenario_jar,
                    "conflict_count": 0,
                    "type_distribution": {}
                }
            scenarios_stats[scenario_idx]["conflict_count"] += 1
            classification = snapshot.classification
            scenarios_stats[scenario_idx]["type_distribution"][classification] = \
                scenarios_stats[scenario_idx]["type_distribution"].get(classification, 0) + 1

        # Sort scenarios by index for consistent ordering
        scenarios = sorted(scenarios_stats.values(), key=lambda s: s["scenario_index"])

        rule_signatures = {rule.label: rule.signature for rule in self.classifier.rules}

        return {
            "source": self.source_name,
            "summary": {
                "total_conflicts": len(self.conflicts),
                "type_distribution": distribution,
                "analog_verification": self.classifier.build_analog_verification(rule_signatures),
            },
            "scenarios": scenarios,
            "conflicts": [self._snapshot_to_report(snapshot) for snapshot in self.conflicts],
        }

    @staticmethod
    def _snapshot_to_report(snapshot):
        data = snapshot.to_dict()
        classification_data = getattr(snapshot, "_classification_data", {}) or {}
        metrics = getattr(snapshot, "_metrics", {}) or {}
        data["file_sequences"] = {
            "source1_full": classification_data.get("source1_files_full", data["source1"]["stack_files"]),
            "source2_full": classification_data.get("source2_files_full", data["source2"]["stack_files"]),
            "source1_classification": classification_data.get("source1_files", data["source1"]["stack_files"]),
            "source2_classification": classification_data.get("source2_files", data["source2"]["stack_files"]),
            "confluence_classification": [classification_data.get("confluence_file")] if classification_data.get("confluence_file") else data["confluence"]["stack_files"],
        }
        data["classification_details"] = classification_data
        data["metrics"] = metrics
        return data

    def export_metrics_to_json(self):
        """Export CF metrics to a JSON file.

        Returns:
            str: Path to the exported JSON file.
        """
        import os
        import json

        output_path = os.path.join(self.output_dir, "cf_metrics.json")

        export_data = {
            "summary": {
                "total_conflicts": len(self.metrics),
            },
            "metrics": self.metrics,
        }

        with open(output_path, "w", encoding="utf-8") as f:
            json.dump(export_data, f, indent=2)

        return output_path

    def save_results(self, plot=True):
        report = self.build_report()
        reporter = CFConflictReporter(self.output_dir)
        self.report_path = reporter.write(report)
        if plot:
            reporter.plot(report)
        return self.report_path
