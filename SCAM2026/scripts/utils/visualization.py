import matplotlib.pyplot as plt
import seaborn as sns


class Visualizer:
    def __init__(self):
        self.setup_plot_style()

    @staticmethod
    def setup_plot_style():
        sns.set_palette("tab10")

    @staticmethod
    def _save_plot(filename):
        """Save and close figure"""
        plt.tight_layout()
        plt.savefig(filename)
        plt.close()

    def plot_pie_chart(self, data, title, filename):
        plt.figure(figsize=(12, 6))
        colors = sns.color_palette("tab10", len(data["labels"]))
        plt.pie(
            data["values"],
            labels=[
                f"{label}\n({int(count)} - {pct:.1f}%)"
                for label, count, pct in zip(
                    data["labels"], data["values"], data["percentages"]
                )
            ],
            autopct="",
            startangle=140,
            colors=colors,
        )
        plt.axis("equal")
        plt.title(title, pad=20)
        plt.subplots_adjust(top=0.85)
        self._save_plot(filename)
