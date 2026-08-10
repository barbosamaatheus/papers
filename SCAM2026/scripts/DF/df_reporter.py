import json
import os

from utils.visualization import Visualizer


class DFConflictReporter:
    PLOT_FILENAME = "df_conflict_types_pie.png"

    def __init__(self, output_dir):
        self.output_dir = output_dir
        self.visualizer = Visualizer()

    def write(self, report):
        os.makedirs(self.output_dir, exist_ok=True)
        output_path = os.path.join(self.output_dir, "df_conflict_report.json")
        with open(output_path, "w", encoding="utf-8") as f:
            json.dump(report, f, indent=2, ensure_ascii=False)
        return output_path

    def plot(self, report):
        distribution = report.get("summary", {}).get("type_distribution", {})
        if not distribution:
            return

        total = sum(distribution.values())
        sorted_items = sorted(distribution.items(), key=lambda kv: kv[1], reverse=True)
        labels = [k for k, _ in sorted_items]
        values = [v for _, v in sorted_items]
        percentages = [(v / total * 100) if total > 0 else 0 for v in values]

        filename = os.path.join(self.output_dir, self.PLOT_FILENAME)
        self.visualizer.plot_pie_chart(
            {"labels": labels, "values": values, "percentages": percentages},
            "Distribution of DF Conflict Types",
            filename,
        )
