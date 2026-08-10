import sys
import os
from collections import Counter
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages
import traceback

# Allow direct execution of this script from scripts/OA/ while importing local modules.
sys.path.insert(0, os.path.dirname(__file__))
from utils.conflict_processor import ConflictProcessor
from oa_reporter import OAConflictReporter

@staticmethod
def _classify_1_1(left_files, right_files):
    return "OA_A1: left A, right A, conflict inside A" if left_files[0] == right_files[0] else "OA_A2: left A, right B, conflict between A and B"

@staticmethod
def _classify_1_2(left_files, right_files):
    if left_files[0] == right_files[0] and left_files[0] != right_files[-1]:
        return "OA_B2: one side A, other A-B, conflict between A and B"
    if left_files[0] == right_files[-1]:
        return "OA_C2: one side B, other A-B, conflict inside B"
    if left_files[0] != right_files[0] and left_files[0] != right_files[-1]:
        return "OA_A3: one side A, other B-C, conflict between A and C"
    return "Other cases"

@staticmethod
def _classify_2_1(left_files, right_files):
    if right_files[0] == left_files[0] and left_files[-1] != right_files[0]:
        return "OA_B2: one side A, other A-B, conflict between A and B"
    if right_files[0] == left_files[-1]:
        return "OA_C2: one side B, other A-B, conflict inside B"
    if right_files[0] != left_files[0] and left_files[-1] != right_files[0]:
        return "OA_A3: one side A, other B-C, conflict between A and C"
    return "Other cases"

@staticmethod
def _classify_2_2(left_files, right_files):
    left0, left1 = left_files[0], left_files[1]
    right0, right1 = right_files[0], right_files[1]

    # exact same last file
    if left1 == right1:
        if left0 == right0:
            return "OA_D2: left A-B, right A-B, conflict inside B"
        return "OA_B3: left A-C, right B-C, conflict inside C"

    # shared first file
    if left0 == right0:
        return "OA_C3: left A-B, right A-C, conflict between B and C"

    # reversed sources: left A-B, right B-A (must precede chain check)
    if left0 == right1 and left1 == right0:
        return "OA_E2: left A-B, right B-A, conflict between A and B"

    # chained flows: one side's last file equals the other side's first
    if left1 == right0 or left0 == right1:
        return "OA_D3: left A-B, right B-C, conflict between B and C"

    return "OA_A4: left A-B, right C-D, conflict between B and D"

def classify_conflict(left_files, right_files):
    left_count = len(left_files)
    right_count = len(right_files)

    if left_count == 1 and right_count == 1:
        return _classify_1_1(left_files, right_files)
    elif left_count == 1 and right_count == 2:
        return _classify_1_2(left_files, right_files)
    elif left_count == 2 and right_count == 1:
        return _classify_2_1(left_files, right_files)
    elif left_count == 2 and right_count == 2:
        return _classify_2_2(left_files, right_files)
    else:
        raise ValueError(f"Unexpected number of files: left {left_count}, right {right_count}")

def generate_scenario_report_oa(oa_conflicts, modified_lines, scenario_idx=0):
    """Process a single scenario's OA conflicts and return aggregated results.

    Returns a tuple of (types_counter, details) without writing files.

    Args:
        scenario_idx: Index of the scenario being processed
    """
    types_counter = Counter()
    details = []
    conflict_idx = 0

    conflict_analyzer = ConflictProcessor()
    conflict_analyzer.modified_lines_map = modified_lines if isinstance(modified_lines, dict) else {}

    def _per_conflict_modified_lines(conflict):
        per_conflict = conflict.get("__scenario_modified_lines") or conflict.get("__modifiedLines")
        if isinstance(per_conflict, dict):
            return per_conflict
        return conflict_analyzer.modified_lines_map

    # iterate directly over collected conflicts
    for conflict in oa_conflicts:
        try:
            print(f"Processing OA: scenario {scenario_idx}, conflict {conflict_idx}")
            interference = conflict['body']['interference']
            if len(interference) < 2:
                continue

            effective_modified_lines = _per_conflict_modified_lines(conflict)
            conflict_analyzer.modified_lines_map = effective_modified_lines if isinstance(effective_modified_lines, dict) else {}
            processed = conflict_analyzer._process_interference_pair(interference[0], interference[-1], effective_modified_lines)

            # Check for error conditions
            if processed.get('error'):
                error_type = f"Error: {processed['error'].replace('_', ' ').title()}"
                types_counter[error_type] += 1
                details.append({
                    'type': error_type,
                    'scenario': conflict.get('ScenarioJAR', 'Unknown'),
                    'description': conflict['body'].get('description', ''),
                    'scenario_index': scenario_idx,
                    'conflict_index': conflict_idx
                })
                conflict_idx += 1
                continue

            left_files = processed.get('left_files_for_class', [])
            right_files = processed.get('right_files_for_class', [])

            c_type = classify_conflict(left_files, right_files)
            types_counter[c_type] += 1

            # Calculate metrics for this conflict
            interference = conflict['body']['interference']
            metrics = conflict_analyzer.calculate_conflict_metrics(
                interference[0] if len(interference) > 0 else None,
                interference[1] if len(interference) > 1 else None
            )

            details.append({
                'type': c_type,
                'scenario': conflict.get('ScenarioJAR', 'Unknown'),
                'description': conflict['body'].get('description', ''),
                'scenario_index': scenario_idx,
                'conflict_index': conflict_idx,
                'metrics': metrics
            })
            conflict_idx += 1
        except Exception as e:
            types_counter["Error"] += 1
            print("Error processing conflict:", getattr(conflict, '_id', conflict.get('_id') if isinstance(conflict, dict) else None))
            traceback.print_exc()

    return types_counter, details


def generate_report_oa(types_counter, details, output_dir=None):
    """Process all OA conflicts from all scenarios and generate combined report."""

    # Determine output path
    if output_dir is None or not os.path.exists(output_dir):
        oa_dir = "."
        output_pdf = "conflict_types_report.pdf"
    else:
        oa_dir = os.path.join(output_dir, "OA")
        os.makedirs(oa_dir, exist_ok=True)
        output_pdf = os.path.join(oa_dir, "conflict_types_report.pdf")

    # Calculate per-scenario statistics
    scenarios_stats = {}
    for detail in details:
        scenario_idx = detail.get("scenario_index", 0)
        if scenario_idx not in scenarios_stats:
            scenarios_stats[scenario_idx] = {
                "scenario_index": scenario_idx,
                "scenario_jar": detail.get("scenario", "Unknown"),
                "conflict_count": 0,
                "type_distribution": {}
            }
        scenarios_stats[scenario_idx]["conflict_count"] += 1
        conflict_type = detail.get("type", "Unknown")
        scenarios_stats[scenario_idx]["type_distribution"][conflict_type] = \
            scenarios_stats[scenario_idx]["type_distribution"].get(conflict_type, 0) + 1

    # Sort scenarios by index for consistent ordering
    scenarios = sorted(scenarios_stats.values(), key=lambda s: s["scenario_index"])

    # Create report structure
    report = {
        "summary": {
            "total_conflicts": sum(types_counter.values()),
            "type_distribution": dict(types_counter),
        },
        "scenarios": scenarios,
        "details": details
    }

    # Write JSON report using reporter
    reporter = OAConflictReporter(oa_dir)
    json_path = reporter.write(report)
    reporter.plot(report)

    # Write combined PDF report
    with PdfPages(output_pdf) as pdf:
        # Pie chart page
        fig, ax = plt.subplots(figsize=(10, 8))
        labels = []
        for k, v in types_counter.items():
            short_label = k.split(':', 1)[0].strip()
            labels.append(f"{short_label} ({v})")
        sizes = list(types_counter.values())

        if not sizes:
            ax.text(0.5, 0.5, 'No conflicts found', horizontalalignment='center', verticalalignment='center', fontsize=14)
            ax.axis('off')
        else:
            ax.pie(sizes, labels=labels, autopct='%1.1f%%', startangle=140)
            ax.axis('equal')
            ax.set_title('Distribution of Semantic Conflict Types', fontsize=16)

        pdf.savefig(fig)
        plt.close(fig)

        # Details page(s)
        items_per_page = 30
        for i in range(0, len(details), items_per_page):
            fig, ax = plt.subplots(figsize=(10, 11))
            ax.axis('off')

            y_pos = 0.95
            if i == 0:
                ax.text(0.05, y_pos, "Detailed Conflict Types List", fontsize=14, fontweight='bold')
                y_pos -= 0.05

            page_details = details[i:i+items_per_page]
            for d in page_details:
                text = f"[{d['type']}] {d['description']} (JAR: {os.path.basename(d['scenario'])})"
                if len(text) > 115:
                    text = text[:112] + "..."
                ax.text(0.05, y_pos, text, fontsize=9, family='monospace')
                y_pos -= 0.03

            pdf.savefig(fig)
            plt.close(fig)

    print(("="*80 + "\n")*2 + "\n")
    print(f"JSON report successfully generated at: {json_path}")
    print(f"PDF report successfully generated at: {output_pdf}")
    print("Counts:")
    for k, v in types_counter.most_common():
        print(f"  {k}: {v}")