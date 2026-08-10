import os


class ConflictProcessor:
    """Handles processing and normalization of conflict data without report generation."""
    
    def __init__(self):
        """Initialize internal state for tracking modified lines mapping."""
        self.modified_lines_map = {}

    def _normalize_file_key(self, value):
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

    def _frame_line_valid(self, frame):
        """Return True if frame has a non-negative numeric line."""
        if frame is None:
            return False
        line = frame.get('line')
        if line is None:
            return False
        try:
            return int(line) >= 0
        except Exception:
            return False

    def _frame_file_key(self, frame):
        """Extract normalized file key from a frame dict."""
        file_val = frame.get("file") or frame.get("location", {}).get("file") or \
            frame.get("class") or frame.get("location", {}).get("class")
        return self._normalize_file_key(file_val)

    def _get_frames_with_lines(self, interference):
        """Return ordered list of (file_key, line, original_frame) tuples for an interference."""
        stack_trace = interference.get("stackTrace", [])
        frames = []
        previous = None
        for frame in stack_trace:
            # skip frames with invalid or negative line numbers
            if not self._frame_line_valid(frame):
                continue
            line = frame.get('line')

            file_key = self._frame_file_key(frame)
            if not file_key:
                continue

            # avoid consecutive duplicates of same file
            if file_key == previous:
                # still append as separate frame with line if different line
                if frames and frames[-1][0] == file_key and frames[-1][1] == line:
                    continue
            frames.append((file_key, line, frame))
            previous = file_key

        # include location file at the end if present
        loc = interference.get("location", {}) or {}
        loc_file = loc.get("file")
        if loc_file:
            loc_line = loc.get('line')
            try:
                if loc_line is not None and int(loc_line) >= 0:
                    lk = self._normalize_file_key(loc_file)
                    if not frames or frames[-1][0] != lk or frames[-1][1] != loc_line:
                        frames.append((lk, loc_line, {'location': loc}))
            except Exception:
                pass

        return frames

    def _frames_to_files(self, frames):
        """Convert a list of (file_key, line, frame) tuples into ordered file keys.

        Consecutive duplicate file keys are collapsed so the returned list contains
        only transitions between distinct files.
        """
        files = []
        prev = None
        for fkey, ln, fr in frames:
            if fkey is None:
                continue
            if fkey == prev:
                continue
            files.append(fkey)
            prev = fkey
        return files

    def _files_for_classification(self, files_full):
        """Return files to use for classification.

        If more than two files are present, pick only the first and last to
        represent the pair for classification heuristics.
        If first and last files are the same, deduplicate to avoid [A, A] cases.
        """
        if len(files_full) > 2:
            first_file = files_full[0]
            last_file = files_full[-1]
            if first_file == last_file:
                return [first_file]
            return [first_file, last_file]
        return files_full

    def _remove_tuple_from_frames(self, frames, fkey, line):
        """Return frames with the specific (fkey, line) tuple removed.

        This is used to drop matched (file,line) entries from one side when
        ownership is attributed to the other side of a pair.
        """
        return [frm for frm in frames if not (frm[0] == fkey and frm[1] == line)]

    def _get_modified_lines_entry(self, fkey, modified_lines):
        """Get entry from modified_lines, matching by normalized file key.

        Handles cases where modified_lines keys may be full file paths or normalized keys.
        """
        if not modified_lines:
            return None

        entry = modified_lines.get(fkey)
        if entry:
            return entry

        for full_path_key, entry_data in modified_lines.items():
            if self._normalize_file_key(full_path_key) == fkey:
                return entry_data

        return None

    def _determine_owner(self, fkey, line, left_tuples, right_tuples, modified_lines):
        """Determine ownership ('L'/'R'/None) for a (file,line) tuple."""
        entry = None
        if modified_lines:
            entry = modified_lines.get(fkey)
        else:
            entry = self.modified_lines_map.get(fkey)
        if entry:
            left_mod = line in entry.get('leftAdded', set())
            right_mod = line in entry.get('rightAdded', set())
            if left_mod and not right_mod:
                return 'L'
            if right_mod and not left_mod:
                return 'R'

        # fallback to closer in stack
        try:
            lpos = left_tuples.index((fkey, line))
            rpos = right_tuples.index((fkey, line))
            return 'L' if lpos < rpos else 'R'
        except ValueError:
            return None

    def _filter_duplicate_last_frames(self, left_frames: list, right_frames: list, left_owner, right_owner, left_tuples, right_tuples, modified_lines):
        """Filter duplicate last frames when they are the same between left and right.

        If the last frames (file, line) are identical:
        1. Determine ownership of that frame
        2. If owned by one branch, remove from the other
        3. If not clearly owned, keep in list with closest modified frame
        4. If distances are equal, remove from bigger list (or raise error if same size)

        Returns filtered (left_frames, right_frames) or raises error if ambiguous.
        """
        if not left_frames or not right_frames:
            return left_frames, right_frames

        left_last = (left_frames[-1][0], left_frames[-1][1])
        right_last = (right_frames[-1][0], right_frames[-1][1])

        if left_last != right_last:
            return left_frames, right_frames

        fkey, line = left_last

        # Determine ownership of the duplicate last frame
        owner = self._determine_owner(fkey, line, left_tuples, right_tuples, modified_lines)

        if owner == left_owner:
            right_frames.pop()
        elif owner == right_owner:
            left_frames.pop()
        else:
            # Frame is not clearly owned, check if any frames (file + line) are in modified_lines
            frames_modified = False
            for f, ln, _ in left_frames + right_frames:
                entry = self._get_modified_lines_entry(f, modified_lines)
                if entry and (ln in entry.get('leftAdded', set()) or ln in entry.get('leftRemoved', set()) or
                             ln in entry.get('rightAdded', set()) or ln in entry.get('rightRemoved', set())):
                    frames_modified = True
                    break

            if not frames_modified:
                # No frames with modifications found in modified_lines, cannot determine ownership
                return left_frames, right_frames, 'unmodified_stack_trace'

            # Frame is not clearly owned, use closest modified frame heuristic
            left_closest_dist = self._closest_modified_distance(left_frames, fkey, modified_lines, 'L')
            right_closest_dist = self._closest_modified_distance(right_frames, fkey, modified_lines, 'R')

            if left_closest_dist < right_closest_dist:
                right_frames.pop()
            elif right_closest_dist < left_closest_dist:
                left_frames.pop()
            else:
                # Same distance, remove from bigger list
                if len(left_frames) > len(right_frames):
                    left_frames.pop()
                elif len(right_frames) > len(left_frames):
                    right_frames.pop()
                else:
                    raise ValueError(f"Ambiguous frame ownership at {fkey}:{line} with equal list sizes and distances")

        return left_frames, right_frames

    def _closest_modified_distance(self, frames, target_file, modified_lines, branch):
        """Find distance from target file to the closest modified frame in this branch.

        Returns the distance to the closest modified frame, or infinity if none found.
        """
        mod_key = 'leftAdded' if branch == 'L' else 'rightAdded'
        closest = float('inf')

        for i, (fkey, line, fr) in enumerate(frames):
            if fkey != target_file:
                continue
            entry = self._get_modified_lines_entry(fkey, modified_lines)
            if not entry or line not in entry.get(mod_key, set()):
                continue
            closest = min(closest, abs(len(frames) - 1 - i))

        return closest

    def _find_start_index(self, frames, this_interf, other_interf, modified_lines):
        """Rule 1: start from first modified line by the branch that owns this frame list.

        Determines branch ownership by checking:
        1. If locations differ, check which branch modified the current interference's location
        2. If locations are the same or location check doesn't decide, use stacktrace to decide
        3. Find the first line in this frame list modified by the owning branch

        Returns a tuple (start_index, branch_owner) where branch_owner is 'L' or 'R'.
        """
        this_loc = this_interf.get("location", {}) or {}
        other_loc = other_interf.get("location", {}) or {}
        this_loc_key = (this_loc.get("file") or this_loc.get("class"), this_loc.get("line"))
        other_loc_key = (other_loc.get("file") or this_loc.get("class"), other_loc.get("line"))
        locations_same = this_loc_key == other_loc_key and this_loc_key != (None, None)

        branch_owner = None

        # Step 1: If locations are different, try location-based ownership determination
        if not locations_same:
            location = this_interf.get("location", {})
            loc_file = location.get("file") or location.get("class")
            loc_line = location.get("line")

            if loc_file and loc_line is not None:
                try:
                    loc_line = int(loc_line)
                    loc_key = self._normalize_file_key(loc_file)
                    entry = self._get_modified_lines_entry(loc_key, modified_lines)
                    if entry:
                        left_mod = loc_line in entry.get('leftAdded', set()) or loc_line in entry.get('leftRemoved', set())
                        right_mod = loc_line in entry.get('rightAdded', set()) or loc_line in entry.get('rightRemoved', set())

                        if left_mod and not right_mod:
                            branch_owner = 'L'
                        elif right_mod and not left_mod:
                            branch_owner = 'R'
                except (ValueError, TypeError):
                    pass

        # Step 2: If locations are the same or location didn't decide, use stacktrace
        if branch_owner is None:
            for fkey, line, fr in frames:
                if line is None or line == -1:
                    continue
                entry = self._get_modified_lines_entry(fkey, modified_lines)
                if not entry:
                    continue
                left_mod = line in entry.get('leftAdded', set()) or line in entry.get('leftRemoved', set())
                right_mod = line in entry.get('rightAdded', set()) or line in entry.get('rightRemoved', set())

                if left_mod and not right_mod:
                    branch_owner = 'L'
                    break
                elif right_mod and not left_mod:
                    branch_owner = 'R'
                    break

        # Default to left if still not determined
        if branch_owner is None:
            branch_owner = 'L'

        modification_keys = {'leftAdded', 'leftRemoved'} if branch_owner == 'L' else {'rightAdded', 'rightRemoved'}

        # Step 3: Find starting index for the owning branch
        for i, (fkey, line, fr) in enumerate(frames):
            if line is None or line == -1:
                continue
            entry = self._get_modified_lines_entry(fkey, modified_lines)
            if not entry:
                continue
            for key in modification_keys:
                if line in entry.get(key, set()):
                    return i, branch_owner

        return 0, branch_owner

    def _process_interference_pair(self, left_interf, right_interf, modified_lines, debug=False):
        """Apply modified-lines and duplicate-removal rules to a pair of interferences.

        Returns a dict with full lists and classification lists.

        Args:
            debug: Set to True to enable intermediate console output and error messages
        """
        if debug:
            print("===========================================================================")
            print("Processing:")
            print(f"Modified Lines: {modified_lines}")

        left_frames = self._get_frames_with_lines(left_interf)
        right_frames = self._get_frames_with_lines(right_interf)

        if debug:
            print(f"Left Frames: {[(f,l) for (f,l,_) in left_frames]}\nRight Frames: {[(f,l) for (f,l,_) in right_frames]}")
            print("===========================================================================")

        # Check for unmodified stack traces (frames with no modifications)
        def has_modified_frames(frames_list):
            for fkey, line, _ in frames_list:
                entry = self._get_modified_lines_entry(fkey, modified_lines)
                line = int(line)
                if entry and (line in entry.get('leftAdded', set()) or line in entry.get('leftRemoved', set()) or
                             line in entry.get('rightAdded', set()) or line in entry.get('rightRemoved', set())):
                    return True
            return False

        left_has_modified = has_modified_frames(left_frames)
        right_has_modified = has_modified_frames(right_frames)

        if modified_lines and (not left_has_modified or not right_has_modified):
            return {
                'error': 'unmodified_stack_trace',
                'left_frames': left_frames,
                'right_frames': right_frames,
                'left_files_full': [],
                'right_files_full': [],
                'left_files_for_class': [],
                'right_files_for_class': []
            }

        # Check if frames are completely identical (symmetric case - error condition)
        left_tuples_check = [(f, line) for (f, line, _) in left_frames]
        right_tuples_check = [(f, line) for (f, line, _) in right_frames]
        if left_tuples_check == right_tuples_check:
            # Find overlapping modified lines (both left and right modified same line in same file)
            overlapping_lines = {}
            if modified_lines:
                for fkey, entry in modified_lines.items():
                    left_mods = entry.get('leftAdded', set()) | entry.get('leftRemoved', set())
                    right_mods = entry.get('rightAdded', set()) | entry.get('rightRemoved', set())
                    overlap = left_mods & right_mods
                    if overlap:
                        overlapping_lines[fkey] = sorted(list(overlap))

            if debug:
                print("ERROR: Symmetric modified lines detected")
                print(f"Left frames and right frames are identical: {left_tuples_check}")
                if overlapping_lines:
                    print("Overlapping modified lines (modified by both branches):")
                    for fkey, lines in overlapping_lines.items():
                        print(f"  {fkey}: {lines}")
                else:
                    print("No overlapping modifications found")
                print("="*80 + "\n")

            return {
                'error': 'symmetric_modified_lines',
                'left_frames': left_frames,
                'right_frames': right_frames,
                'left_files_full': [],
                'right_files_full': [],
                'left_files_for_class': [],
                'right_files_for_class': []
            }

        # Rule 1: start from first modified line by that branch's dev
        lstart, left_owner = self._find_start_index(left_frames, left_interf, right_interf, modified_lines)
        rstart, right_owner = self._find_start_index(right_frames, right_interf, left_interf, modified_lines)

        left_frames = left_frames[lstart:]
        right_frames = right_frames[rstart:]

        if debug:
            print(f"______Filtered Frames:\nLeft Frames: {[(f,l) for (f,l,_) in left_frames]}\nRight Frames: {[(f,l) for (f,l,_) in right_frames]}")

        # Rule 2: Filter duplicate last frames if they are the same
        left_tuples = [(f, ln) for (f, ln, _) in left_frames]
        right_tuples = [(f, ln) for (f, ln, _) in right_frames]
        filter_result = self._filter_duplicate_last_frames(left_frames, right_frames, left_owner, right_owner, left_tuples, right_tuples, modified_lines)

        # Check if an error was returned
        if len(filter_result) == 3:
            left_frames, right_frames, error = filter_result
            return {
                'error': error,
                'left_frames': left_frames,
                'right_frames': right_frames,
                'left_files_full': [],
                'right_files_full': [],
                'left_files_for_class': [],
                'right_files_for_class': []
            }

        left_frames, right_frames = filter_result

        # Build file lists (full) preserving order and dedup consecutive same files
        left_files_full = self._frames_to_files(left_frames)
        right_files_full = self._frames_to_files(right_frames)

        return {
            'left_frames': left_frames,
            'right_frames': right_frames,
            'left_files_full': left_files_full,
            'right_files_full': right_files_full,
            'left_files_for_class': self._files_for_classification(left_files_full),
            'right_files_for_class': self._files_for_classification(right_files_full)
        }

    def _process_interference_df(self, interference, modified_lines):
        """Process DF/CONFLICT events that may contain more than two nodes.

        Heuristic:
        - Choose the first node whose 'type' contains 'Source' as the left node.
        - Choose the last node whose 'type' contains 'Sink' as the right node.
        - Fallback to first and last nodes when Source/Sink not found.

        The resulting left/right stacks are passed to the same metrics collector
        used by OA conflicts so the CSV output format stays identical.
        """
        # Find left (source) node
        source_node = None
        sink_node = None
        for node in interference:
            ntype = (node.get("type") or "")
            if "Source" in ntype and source_node is None:
                source_node = node

        # Find right (sink) node by scanning reversed
        for node in reversed(interference):
            ntype = (node.get("type") or "")
            if "Sink" in ntype and sink_node is None:
                sink_node = node

        # Fallbacks
        if source_node is None:
            source_node = interference[0]
        if sink_node is None:
            sink_node = interference[-1]

        return self._process_interference_pair(source_node, sink_node, modified_lines)

    def _build_modified_lines_map(self, payload):
        """Parse payload.modifiedLines into self.modified_lines_map."""
        modified = payload.get("modifiedLines", []) if isinstance(payload, dict) else []
        self.modified_lines_map = {}
        for entry in modified:
            fname = entry.get("file")
            if not fname:
                continue
            key = self._normalize_file_key(fname)
            self.modified_lines_map[key] = {
                'leftAdded': set(entry.get('leftAdded', []) or []),
                'leftRemoved': set(entry.get('leftRemoved', []) or []),
                'rightAdded': set(entry.get('rightAdded', []) or []),
                'rightRemoved': set(entry.get('rightRemoved', []) or []),
            }

    @staticmethod
    def calculate_conflict_metrics(left_node, right_node):
        """Calculate metrics for a pair of conflict nodes.

        Returns dict with stack and location metrics.
        """
        l_stack = left_node.get("stackTrace", []) if left_node else []
        r_stack = right_node.get("stackTrace", []) if right_node else []
        l_len, r_len = len(l_stack), len(r_stack)
        diff = abs(l_len - r_len)
        max_len = max(l_len, r_len)

        l_class = left_node.get("location", {}).get("class") if left_node else None
        r_class = right_node.get("location", {}).get("class") if right_node else None
        l_method = left_node.get("location", {}).get("method") if left_node else None
        r_method = right_node.get("location", {}).get("method") if right_node else None

        same_class = l_class == r_class and l_class is not None
        same_method = same_class and l_method == r_method and l_method is not None

        l_start_class = l_stack[0].get("class") if l_stack else None
        r_start_class = r_stack[0].get("class") if r_stack else None
        same_start_class = l_start_class == r_start_class and l_start_class is not None

        l_start_method = l_stack[0].get("method") if l_stack else None
        r_start_method = r_stack[0].get("method") if r_stack else None
        same_start_method = l_start_method == r_start_method and l_start_method is not None

        return {
            "l_len": l_len,
            "r_len": r_len,
            "max_len": max_len,
            "diff": diff,
            "same_class": same_class,
            "same_method": same_method,
            "same_start_class": same_start_class,
            "same_start_method": same_start_method,
        }
