import os
from collections import Counter
import matplotlib.pyplot as plt
from matplotlib.backends.backend_pdf import PdfPages
from CF.cf_processor import CFConflictProcessor


def generate_report_cf(cf_processor: CFConflictProcessor, modified_lines, output_dir: str|None=None):
    if output_dir is None or not os.path.exists(output_dir):
        cf_dir = os.path.join(".", "CF")
        output_pdf = "conflict_types_report.pdf"
    else:
        # Create CF subfolder inside the actual output directory.
        cf_dir = os.path.join(output_dir, "CF")
        os.makedirs(cf_dir, exist_ok=True)
        output_pdf = os.path.join(cf_dir, "conflict_types_report.pdf")
    
    # Save CF results to the output directory before generating the PDF report
    cf_processor.output_dir = cf_dir
    cf_processor.save_results()

    #TODO: Implement PDF for CF
    return
                
    # Plotting and PDF generation
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
            ax.axis('equal')  # Equal aspect ratio ensures that pie is drawn as a circle.
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
                # truncate if too long
                if len(text) > 115:
                    text = text[:112] + "..."
                ax.text(0.05, y_pos, text, fontsize=9, family='monospace')
                y_pos -= 0.03
                
            pdf.savefig(fig)
            plt.close(fig)
            
    print(f"Report successfully generated at: {output_pdf}")
    print("Counts:")
    for k, v in types_counter.most_common():
        print(f"  {k}: {v}")
