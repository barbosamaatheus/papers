import sys
import os
from utils.parser import _process_json

# Allow direct execution of this script from scripts/OA/ while importing local modules.
sys.path.insert(0, os.path.dirname(__file__))
from utils.conflict_analysis_methods import ConflictAnalyzer

def main():
    input_jsons = []

    for arg in sys.argv[1:]:
        if arg.lower().startswith("out.json="):
            value = arg.split("=", 1)[1].strip()
            if value:
                input_jsons.append(value)
        elif not arg.startswith("-"):
            # Positional argument (file path)
            input_jsons.append(arg)

    # Validate that at least one input is provided
    if not input_jsons:
        print(
            "Error: out.json must be provided"
        )
        sys.exit(1)

    processors = []
    output_dirs = []
    payloads = []

    # Process all JSON files
    for json_path in input_jsons:
        processor, output_dir, payload = _process_json(json_path)
        processors.append(processor)
        output_dirs.append(output_dir)
        payloads.append(payload)

    # Run analyzers if data is available
    if processors:
        conflict_analyzer = ConflictAnalyzer()

        for i, processor in enumerate(processors):
            conflict_analyzer.analyze(processor, output_dirs[i], payloads[i])

if __name__ == "__main__":
    main()