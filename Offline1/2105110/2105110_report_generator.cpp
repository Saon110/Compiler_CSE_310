#include "2105110_SymbolTable.hpp"

using namespace std;

int tokenize(const string &line, string *&tokens) {
    int capacity = 10;
    int count = 0;
    tokens = new string[capacity];

    int n = line.size();
    int i = 0;
    while (i < n) {
        while (i < n && isspace((unsigned char)line[i])) {
            ++i;
        }
        if (i >= n) break;

        int start = i;
        while (i < n && !isspace((unsigned char)line[i])) {
            ++i;
        }
        int len = i - start;

        if (count >= capacity) {
            int newCap = capacity * 2;
            string *tmp = new string[newCap];
            for (int k = 0; k < count; ++k) {
                tmp[k] = tokens[k];
            }
            delete[] tokens;
            tokens = tmp;
            capacity = newCap;
        }
        tokens[count++] = line.substr(start, len);
    }

    return count;
}

int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        cerr << "Usage: " << argv[0] << " <input_file> <output_file>" << endl;
        return 1;
    }

    freopen(argv[1], "r", stdin);  // input file

    string line;
    int cmd_count = 1;

    getline(cin, line);
    int scopeTableSize = stoi(line);
    SymbolTable symbolTable1(scopeTableSize,1, "SDBMHash");
    SymbolTable symbolTable2(scopeTableSize,1, "DJBHash");
    SymbolTable symbolTable3(scopeTableSize,1, "BKDRHash");
    SymbolTable symbolTable4(scopeTableSize,1, "DEKHash");

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
            symbolTable1.Insert(symbol);
            symbolTable2.Insert(symbol);
            symbolTable3.Insert(symbol);
            symbolTable4.Insert(symbol);
        }

        else if (cmd == 'L') {
            if (tokenCount != 2) {
                cout << "\tNumber of parameters mismatch for the command L" << endl;
                delete[] tokens;
                continue;
            }
            symbolTable1.LookUp(tokens[1]);
            symbolTable2.LookUp(tokens[1]);
            symbolTable3.LookUp(tokens[1]);
            symbolTable4.LookUp(tokens[1]);
        }

        else if (cmd == 'D') {
            if (tokenCount != 2) {
                cout << "\tNumber of parameters mismatch for the command D" << endl;
                delete[] tokens;
                continue;
            }
            symbolTable1.Remove(tokens[1]);
            symbolTable2.Remove(tokens[1]);
            symbolTable3.Remove(tokens[1]);
            symbolTable4.Remove(tokens[1]);
        }

        else if (cmd == 'P') {
            if (tokenCount != 2) {
                cout << "\tNumber of parameters mismatch for the command P" << endl;
                delete[] tokens;
                continue;
            }
            if (tokens[1] == "A"){
                symbolTable1.PrintAllScopeTable();
                symbolTable2.PrintAllScopeTable();
                symbolTable3.PrintAllScopeTable();
                symbolTable4.PrintAllScopeTable();}
            else if (tokens[1] == "C"){
                symbolTable1.PrintCurrentScopeTable();
                symbolTable2.PrintCurrentScopeTable();
                symbolTable3.PrintCurrentScopeTable();
                symbolTable4.PrintCurrentScopeTable();}
            else
                cout << "\tInvalid command" << endl;
        }

        else if (cmd == 'S') {
            if (tokenCount != 1) {
                cout << "\tNumber of parameters mismatch for the command S" << endl;
                delete[] tokens;
                continue;
            }
            symbolTable1.EnterScope();
            symbolTable2.EnterScope();
            symbolTable3.EnterScope();
            symbolTable4.EnterScope();
        }

        else if (cmd == 'E') {
            if (tokenCount != 1) {
                cout << "\tNumber of parameters mismatch for the command E" << endl;
                delete[] tokens;
                continue;
            }
            symbolTable1.ExitScope();
            symbolTable2.ExitScope();
            symbolTable3.ExitScope();
            symbolTable4.ExitScope();
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

    

    freopen(argv[2], "w", stdout);  // output file
    string source = "https://www.partow.net/programming/";

    cout<<"SDMBHash: "<<"[Source=> "<<source<<"]"<<endl;
    cout << "Total Collisions: " << symbolTable1.getTotalCollisions() << endl;
    cout << "Total Scope Tables: " << symbolTable1.getNumScopes() << endl;
    cout << "Ratio: " << (float)symbolTable1.getTotalCollisions() / symbolTable1.getNumScopes() << endl<<endl;

    cout<<"DJBHash: "<<"[Source=> "<<source<<"]"<<endl;
    cout << "Total Collisions: " << symbolTable2.getTotalCollisions() << endl;
    cout << "Total Scope Tables: " << symbolTable2.getNumScopes() << endl;
    cout << "Ratio: " << (float)symbolTable2.getTotalCollisions() / symbolTable2.getNumScopes() << endl<<endl;

    cout<<"BKDRHash: "<<"[Source=> "<<source<<"]"<<endl;
    cout << "Total Collisions: " << symbolTable3.getTotalCollisions() << endl;
    cout << "Total Scope Tables: " << symbolTable3.getNumScopes() << endl;
    cout << "Ratio: " << (float)symbolTable3.getTotalCollisions() / symbolTable3.getNumScopes() << endl<<endl;

    cout<<"DEKHash: "<<"[Source=> "<<source<<"]"<<endl;
    cout << "Total Collisions: " << symbolTable4.getTotalCollisions() << endl;
    cout << "Total Scope Tables: " << symbolTable4.getNumScopes() << endl;
    cout << "Ratio: " << (float)symbolTable4.getTotalCollisions() / symbolTable4.getNumScopes() << endl<<endl;

    fclose(stdin);
    fclose(stdout);

    return 0;
}