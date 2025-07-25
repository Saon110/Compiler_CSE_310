#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>
#include <sstream>
#include <cctype>
using namespace std;

string trim(const string &s) {
    size_t start = s.find_first_not_of(" \t\r\n");
    size_t end = s.find_last_not_of(" \t\r\n");
    return (start == string::npos) ? "" : s.substr(start, end - start + 1);
}

bool startsWith(const string &s, const string &prefix) {
    return s.substr(0, prefix.size()) == prefix;
}

vector<string> splitByComma(const string &s) {
    vector<string> parts;
    size_t comma = s.find(',');
    if (comma != string::npos) {
        parts.push_back(trim(s.substr(0, comma)));
        parts.push_back(trim(s.substr(comma + 1)));
    }
    return parts;
}

void optimizeRedundantMov(vector<string> &lines) {
    for (size_t i = 0; i + 1 < lines.size(); ++i) {
        string line1 = trim(lines[i]);
        string line2 = trim(lines[i + 1]);

        if (startsWith(line1, "MOV") && startsWith(line2, "MOV")) {
            auto args1 = splitByComma(line1.substr(3));
            auto args2 = splitByComma(line2.substr(3));
            if (args1.size() == 2 && args2.size() == 2 &&
                args1[0] == args2[1] && args1[1] == args2[0]) {
                lines[i + 1] = ";; Removed redundant MOV: " + lines[i + 1];
            }
        }
    }
}

void optimizeRedundantPushPop(vector<string> &lines) {
    for (size_t i = 0; i + 1 < lines.size(); ++i) {
        string line1 = trim(lines[i]);
        string line2 = trim(lines[i + 1]);

        if (startsWith(line1, "PUSH") && startsWith(line2, "POP")) {
            string reg1 = trim(line1.substr(4));
            string reg2 = trim(line2.substr(3));
            if (reg1 == reg2) {
                lines[i] = ";; Removed redundant PUSH: " + lines[i];
                lines[i + 1] = ";; Removed redundant POP: " + lines[i + 1];
            }
        }
    }
}

void optimizeRedundantArithmetic(vector<string> &lines) {
    for (size_t i = 0; i < lines.size(); ++i) {
        string line = trim(lines[i]);
        if (startsWith(line, "ADD")) {
            auto args = splitByComma(line.substr(3));
            if (args.size() == 2 && args[1] == "0") {
                lines[i] = ";; Removed redundant ADD 0: " + lines[i];
            }
        } else if (startsWith(line, "MUL")) {
            auto args = splitByComma(line.substr(3));
            if (args.size() == 2 && args[1] == "1") {
                lines[i] = ";; Removed redundant MUL 1: " + lines[i];
            }
        }
    }
}

void optimizeRedundantLabels(vector<string> &lines) {
    map<string, string> label_remap;
    vector<bool> to_remove(lines.size(), false);
    string last_label = "";
    for (size_t i = 0; i < lines.size(); ++i) {
        string line = trim(lines[i]);
        if (!line.empty() && line.back() == ':') {
            string label = line.substr(0, line.size() - 1);
            if (last_label.empty()) {
                last_label = label;
            } else {
                to_remove[i] = true;
                label_remap[label] = last_label;
            }
        } else {
            last_label = "";
        }
    }

    vector<string> new_lines;
    for (size_t i = 0; i < lines.size(); ++i) {
        if (to_remove[i]) {
            new_lines.push_back(";; Removed redundant label: " + lines[i]);
        } else {
            string line = lines[i];
            for (auto &[from, to] : label_remap) {
                size_t pos;
                while ((pos = line.find(from)) != string::npos) {
                    if ((pos == 0 || !isalnum(line[pos - 1])) &&
                        (pos + from.size() == line.size() || !isalnum(line[pos + from.size()]))) {
                        line.replace(pos, from.size(), to);
                    } else {
                        break;
                    }
                }
            }
            new_lines.push_back(line);
        }
    }

    lines = new_lines;
}

int main() {
    string inputFileName = "./output/output.asm";
    string outputFileName = "./output/optimized_output.asm";

    ifstream inputFile(inputFileName);
    if (!inputFile) {
        cerr << "Error: Cannot open input file.\n";
        return 1;
    }

    vector<string> lines;
    string line;
    while (getline(inputFile, line)) {
        lines.push_back(line);
    }
    inputFile.close();

    optimizeRedundantMov(lines);
    optimizeRedundantPushPop(lines);
    optimizeRedundantArithmetic(lines);
    optimizeRedundantLabels(lines);

    ofstream outputFile(outputFileName);
    if (!outputFile) {
        cerr << "Error: Cannot open output file.\n";
        return 1;
    }

    for (const string &l : lines)
        outputFile << l << endl;

    cout << "Optimization complete. Output written to " << outputFileName << endl;
    return 0;
}
