import os
from dataclasses import dataclass


@dataclass(frozen=True)
class CFRule:
    label: str
    signature: str

    def matches(self, source_files, source_prime_files, confluence_files):
        s = list(source_files)
        sp = list(source_prime_files)
        cf = list(confluence_files)

        if self.label == "A1":
            return len(s) == len(sp) == len(cf) == 1 and s[0] == sp[0] == cf[0]
        if self.label == "A2":
            return len(s) == len(sp) == len(cf) == 1 and s[0] != sp[0] and sp[0] == cf[0]
        if self.label == "B2":
            return len(s) == len(sp) == len(cf) == 1 and s[0] == sp[0] and cf[0] != s[0]
        if self.label == "C2":
            return len(s) == 2 and len(sp) == len(cf) == 1 and s[1] == sp[0] == cf[0] and s[0] != s[1]
        if self.label == "D2":
            return len(s) == 2 and len(sp) == len(cf) == 1 and s[0] == sp[0] == cf[0] and s[1] != s[0]
        if self.label == "E2":
            return len(s) == 2 and len(sp) == len(cf) == 1 and s[0] == sp[0] and s[1] == cf[0] and s[0] != s[1]
        if self.label == "F2":
            return len(s) == 2 and len(sp) == len(cf) == 1 and s[1] == sp[0] and s[0] == cf[0] and s[0] != s[1]
        if self.label == "G2":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s == sp and s[1] == cf[0] and s[0] != s[1]
        if self.label == "H2":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s == sp and s[0] == cf[0] and s[0] != s[1]
        if self.label == "A3":
            return len(s) == len(sp) == len(cf) == 1 and len({s[0], sp[0], cf[0]}) == 3
        if self.label == "B3":
            return len(s) == 2 and len(sp) == len(cf) == 1 and len({s[0], s[1], sp[0]}) == 3 and sp[0] == cf[0]
        if self.label == "C3":
            return len(s) == 2 and len(sp) == len(cf) == 1 and len({s[0], s[1], sp[0]}) == 3 and s[1] == cf[0]
        if self.label == "D3":
            return len(s) == 2 and len(sp) == len(cf) == 1 and s[0] == sp[0] and s[1] != s[0] and cf[0] not in (s[0], s[1])
        if self.label == "E3":
            return len(s) == 2 and len(sp) == len(cf) == 1 and s[0] == cf[0] and len({s[0], s[1], sp[0]}) == 3
        if self.label == "F3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[1] == sp[1] == cf[0] and s[0] != s[1] and sp[0] != sp[1] and s[0] != sp[0]
        if self.label == "G3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s == sp and cf[0] not in s
        if self.label == "H3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[0] == sp[0] and s[1] == cf[0] and len({s[0], s[1], sp[1]}) == 3
        if self.label == "I3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[0] == sp[0] == cf[0] and len({s[0], s[1], sp[1]}) == 3
        if self.label == "A4":
            return len(s) == 2 and len(sp) == len(cf) == 1 and len({s[0], s[1], sp[0], cf[0]}) == 4
        if self.label == "B4":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[1] == cf[0] and len({s[0], s[1], sp[0], sp[1]}) == 4
        if self.label == "C4":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[0] == cf[0] and len({s[0], s[1], sp[0], sp[1]}) == 4
        if self.label == "D4":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[1] == sp[1] and s[0] != sp[0] and cf[0] not in (s[0], s[1], sp[0], sp[1])
        if self.label == "E4":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[0] == sp[0] and s[1] != sp[1] and cf[0] not in (s[0], s[1], sp[1])
        if self.label == "A5":
            return len(s) == len(sp) == 2 and len(cf) == 1 and len({s[0], s[1], sp[0], sp[1], cf[0]}) == 5
        if self.label == "F4":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[1] == sp[0] and len({s[0], s[1], sp[1], cf[0]}) == 4
        if self.label == "I2":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[0] == sp[1] and s[1] == sp[0] and cf[0] in (s[0], s[1])
        if self.label == "K3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[0] == sp[1] and s[1] == sp[0] and cf[0] not in (s[0], s[1])
        if self.label == "J3":
            return len(s) == 2 and len(sp) == len(cf) == 1 and s[1] == sp[0] and len({s[0], s[1], cf[0]}) == 3
        if self.label == "L3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[1] == sp[0] and cf[0] == s[0] and len({s[0], s[1], sp[1]}) == 3
        if self.label == "M3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[1] == sp[0] == cf[0] and len({s[0], s[1], sp[1]}) == 3
        if self.label == "N3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[1] == sp[0] and cf[0] == sp[1] and len({s[0], s[1], sp[1]}) == 3
        if self.label == "O3":
            return len(s) == len(sp) == 2 and len(cf) == 1 and s[1] == sp[1] and s[0] != sp[0] and cf[0] == s[0] and len({s[0], s[1], sp[0]}) == 3
        return False


class CFConflictClassifier:
    """Classify CF conflicts based on the file sequences of source1, source2 and confluence."""

    _KNOWN_EXTS = (".java", ".py", ".kt", ".scala", ".js", ".ts", ".c", ".cpp", ".h", ".cs")

    def __init__(self):
        self.rules = [
            # 5 distinct files
            CFRule("A5", "S:AB|S':CD|CF:E"),
            # 4 distinct files
            CFRule("E4", "S:AB|S':AC|CF:D"),
            CFRule("F4", "S:AB|S':BC|CF:D"),
            CFRule("D4", "S:AB|S':CB|CF:D"),
            CFRule("C4", "S:BA|S':CD|CF:B"),
            CFRule("B4", "S:AB|S':CD|CF:B"),
            CFRule("A4", "S:AB|S':C|CF:D"),
            # 3 distinct files — {2,2}
            CFRule("I3", "S:BA|S':BC|CF:B"),
            CFRule("H3", "S:AB|S':AC|CF:B"),
            CFRule("K3", "S:AB|S':BA|CF:C"),
            CFRule("G3", "S:AB|S':AB|CF:C"),
            CFRule("O3", "S:AB|S':CB|CF:A"),
            CFRule("F3", "S:AB|S':CB|CF:B"),
            CFRule("M3", "S:AB|S':BC|CF:B"),
            CFRule("N3", "S:AB|S':BC|CF:C"),
            CFRule("L3", "S:AB|S':BC|CF:A"),
            # 3 distinct files — {2,1}
            CFRule("E3", "S:BA|S':C|CF:B"),
            CFRule("J3", "S:AB|S':B|CF:C"),
            CFRule("D3", "S:AB|S':A|CF:C"),
            CFRule("C3", "S:AB|S':C|CF:B"),
            CFRule("B3", "S:AB|S':C|CF:C"),
            CFRule("A3", "S:A|S':B|CF:C"),
            # 2 distinct files
            CFRule("I2", "S:AB|S':BA|CF:A"),
            CFRule("H2", "S:AB|S':AB|CF:A"),
            CFRule("G2", "S:AB|S':AB|CF:B"),
            CFRule("F2", "S:BA|S':A|CF:B"),
            CFRule("E2", "S:AB|S':A|CF:B"),
            CFRule("D2", "S:AB|S':A|CF:A"),
            CFRule("C2", "S:AB|S':B|CF:B"),
            CFRule("B2", "S:A|S':A|CF:B"),
            CFRule("A2", "S:A|S':B|CF:B"),
            # 1 distinct file
            CFRule("A1", "S:A|S':A|CF:A"),
        ]

    def _normalize_file_key(self, value):
        if not value:
            return None
        text = str(value).strip()
        if not text:
            return None

        lowered = text.lower()
        if any(lowered.endswith(ext) for ext in self._KNOWN_EXTS) or "/" in text or "\\" in text:
            return os.path.splitext(os.path.basename(text))[0]

        if "." in text:
            return text.rsplit(".", 1)[-1]

        return os.path.splitext(os.path.basename(text))[0] or text

    @staticmethod
    def _valid_line(frame):
        line = frame.get("line")
        if line is None:
            return False
        try:
            return int(line) >= 0
        except Exception:
            return False

    def _extract_files(self, node):
        stack_trace = node.get("stackTrace", []) or []
        files = []
        previous_file = None

        for frame in stack_trace:
            if not isinstance(frame, dict) or not self._valid_line(frame):
                continue

            file_value = frame.get("file") or frame.get("location", {}).get("file") or frame.get("class")
            file_key = self._normalize_file_key(file_value)
            if not file_key:
                continue

            if file_key != previous_file:
                files.append(file_key)
                previous_file = file_key

        if not files:
            location = node.get("location", {}) or {}
            file_value = location.get("file") or location.get("class")
            file_key = self._normalize_file_key(file_value)
            if file_key:
                files.append(file_key)

        return files

    def _extract_confluence_file(self, node):
        location = node.get("location", {}) or {}
        file_value = location.get("file") or location.get("class")
        return self._normalize_file_key(file_value)

    @staticmethod
    def _reduce_sequence(files):
        if len(files) <= 2:
            return list(files)
        first = files[0]
        last = files[-1]
        if first == last:
            return [first]
        return [first, last]

    @staticmethod
    def _try_int(value):
        try:
            return int(value)
        except (TypeError, ValueError):
            return None

    @staticmethod
    def _confluence_location(confluence_node):
        loc = (confluence_node or {}).get("location") or {}
        return loc.get("class"), loc.get("method"), CFConflictClassifier._try_int(loc.get("line"))

    @staticmethod
    def _filter_confluence_frames(node, conf_class, conf_method, conf_line):
        """Return a copy of *node* with every stack frame that matches the
        confluence location exactly (same class, method, AND line) removed.

        Matching all three fields prevents removing frames that merely share a
        file or method with the confluence but are at a different code location.
        """
        if not conf_class or not conf_method or conf_line is None:
            return node, False
        original = (node or {}).get("stackTrace") or []
        filtered = [
            f for f in original
            if not (
                f.get("class") == conf_class
                and f.get("method") == conf_method
                and CFConflictClassifier._try_int(f.get("line")) == conf_line
            )
        ]
        if len(filtered) == len(original):
            return node, False
        result = dict(node or {})
        result["stackTrace"] = filtered
        return result, True

    def classify(self, source1_files, source2_files, confluence_file):
        cf = [confluence_file] if confluence_file is not None else []
        for left, right in ((source1_files, source2_files), (source2_files, source1_files)):
            for rule in self.rules:
                if rule.matches(left, right, cf):
                    return rule.label
        return "Other cases"

    def build_canonical_signature(self, source1_files, source2_files, confluence_file):
        ordered_sources = sorted([tuple(source1_files), tuple(source2_files)])
        return {
            "sources": ordered_sources,
            "confluence": confluence_file,
        }

    def classify_conflict(self, source1_node, source2_node, confluence_node, modified_lines=None):
        conf_class, conf_method, conf_line = self._confluence_location(confluence_node)

        s1_clean, s1_removed = self._filter_confluence_frames(source1_node, conf_class, conf_method, conf_line)
        s2_clean, s2_removed = self._filter_confluence_frames(source2_node, conf_class, conf_method, conf_line)

        # Filter to start from first modified line (same as OA/DF)
        if modified_lines:
            from utils.conflict_processor import ConflictProcessor
            processor = ConflictProcessor()
            processor.modified_lines_map = modified_lines if isinstance(modified_lines, dict) else {}

            # Create frame lists for each source to find start indices
            def frames_from_node(node):
                if not node:
                    return []
                frames = []
                for frame in node.get("stackTrace", []) or []:
                    if not isinstance(frame, dict):
                        continue
                    file_val = frame.get("file") or frame.get("location", {}).get("file") or frame.get("class")
                    file_key = processor._normalize_file_key(file_val) if file_val else None
                    if file_key:
                        line = frame.get("line")
                        if line is not None:
                            frames.append((file_key, line, None))
                return frames

            s1_frames = frames_from_node(s1_clean)
            s2_frames = frames_from_node(s2_clean)

            # Find starting indices
            def find_start_idx(frames):
                for i, (fkey, line, fr) in enumerate(frames):
                    entry = processor._get_modified_lines_entry(fkey, modified_lines)
                    if entry and (line in entry.get('leftAdded', set()) or line in entry.get('leftRemoved', set()) or
                                 line in entry.get('rightAdded', set()) or line in entry.get('rightRemoved', set())):
                        return i
                return 0

            s1_start = find_start_idx(s1_frames)
            s2_start = find_start_idx(s2_frames)

            # Trim stack traces to start from modified lines
            if s1_start > 0:
                s1_stack = s1_clean.get("stackTrace", []) if s1_clean else []
                s1_clean = dict(s1_clean or {})
                s1_clean["stackTrace"] = s1_stack[s1_start:] if len(s1_stack) > s1_start else []

            if s2_start > 0:
                s2_stack = s2_clean.get("stackTrace", []) if s2_clean else []
                s2_clean = dict(s2_clean or {})
                s2_clean["stackTrace"] = s2_stack[s2_start:] if len(s2_stack) > s2_start else []

        source1_files_full = self._extract_files(s1_clean)
        source2_files_full = self._extract_files(s2_clean)
        confluence_file = self._extract_confluence_file(confluence_node)

        source1_files = self._reduce_sequence(source1_files_full)
        source2_files = self._reduce_sequence(source2_files_full)

        classification = self.classify(source1_files, source2_files, confluence_file)
        canonical_signature = self.build_canonical_signature(source1_files, source2_files, confluence_file)

        return {
            "classification": classification,
            "canonical_signature": canonical_signature,
            "source1_files_full": source1_files_full,
            "source2_files_full": source2_files_full,
            "source1_files": source1_files,
            "source2_files": source2_files,
            "confluence_file": confluence_file,
            "confluence_frames_removed": s1_removed or s2_removed,
        }

    def build_analog_verification(self, signatures_by_label):
        grouped = {}
        for label, signature in signatures_by_label.items():
            if isinstance(signature, dict):
                key = (tuple(signature.get("sources", [])), signature.get("confluence"))
            else:
                key = signature
            grouped.setdefault(key, []).append(label)

        analog_groups = [sorted(labels) for labels in grouped.values() if len(labels) > 1]
        return {
            "analog_groups": analog_groups,
            "result": "No exact analog classifications found under source-node swapping." if not analog_groups else "Analog classifications detected."
        }
