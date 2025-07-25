#include "SymbolTable.cpp"
#include <sstream>
using namespace std;

int tokenize(const string &line, string* &tokens) {
    int count = 0;
    int capacity = 10;
    tokens = new string[capacity];

    istringstream iss(line);
    string tok;
    while (iss >> tok) {
        if (count >= capacity) {
            capacity *= 2;
            string* temp = new string[capacity];
            for (int i = 0; i < count; ++i) {
                temp[i] = tokens[i];
            }
            delete[] tokens;
            tokens = temp;
        }
        tokens[count++] = tok;
    }
    return count;
}

int main(int argc, char* argv[]) {
    if (argc != 4) {
        cerr << "Usage: " << argv[0] << " <input_file> <output_file> <hash_function>" << endl;
        return 1;
    }

    freopen(argv[1], "r", stdin);   // input file
    freopen(argv[2], "w", stdout);  // output file

    string line;
    int cmd_count = 1;

    getline(cin, line);
    int scopeTableSize = stoi(line);
    SymbolTable symbolTable(scopeTableSize,1, argv[3]);

    while (getline(cin, line)) {
        if (line.empty()) continue;

        string* tokens;
        int tokenCount = tokenize(line, tokens);
        if (tokenCount == 0) {
            delete[] tokens;
            continue;
        }

        char cmd = tokens[0][0];
        cout << "Cmd " << cmd_count++ << ": " << line << endl;

        if (cmd == 'I') {
            if (tokenCount < 3) {
                cout << "\tNumber of parameters mismatch for the command I" << endl;
                delete[] tokens;
                continue;
            }

            string symbolName = tokens[1];
            string type = tokens[2];
            for (int i = 3; i < tokenCount; i++) {
                type += " " + tokens[i];
            }

            SymbolInfo symbol(symbolName, type);
            symbolTable.Insert(symbol);
        }

        else if (cmd == 'L') {
            if (tokenCount != 2) {
                cout << "\tNumber of parameters mismatch for the command L" << endl;
                delete[] tokens;
                continue;
            }
            symbolTable.LookUp(tokens[1]);
        }

        else if (cmd == 'D') {
            if (tokenCount != 2) {
                cout << "\tNumber of parameters mismatch for the command D" << endl;
                delete[] tokens;
                continue;
            }
            symbolTable.Remove(tokens[1]);
        }

        else if (cmd == 'P') {
            if (tokenCount != 2) {
                cout << "\tNumber of parameters mismatch for the command P" << endl;
                delete[] tokens;
                continue;
            }
            if (tokens[1] == "A")
                symbolTable.PrintAllScopeTable();
            else if (tokens[1] == "C")
                symbolTable.PrintCurrentScopeTable();
            else
                cout << "\tInvalid command" << endl;
        }

        else if (cmd == 'S') {
            if (tokenCount != 1) {
                cout << "\tNumber of parameters mismatch for the command S" << endl;
                delete[] tokens;
                continue;
            }
            symbolTable.EnterScope();
        }

        else if (cmd == 'E') {
            if (tokenCount != 1) {
                cout << "\tNumber of parameters mismatch for the command E" << endl;
                delete[] tokens;
                continue;
            }
            symbolTable.ExitScope();
        }

        else if (cmd == 'Q') {
            if (tokenCount != 1) {
                cout << "\tNumber of parameters mismatch for the command Q" << endl;
                delete[] tokens;
                continue;
            }
            delete[] tokens;
            break;
        }

        else {
            cout << "\tInvalid command" << endl;
        }

        delete[] tokens;
    }

    return 0;
}
