#include <iostream>
#include <sstream>
#include <vector>
using namespace std;

class SymbolInfo
{
private:
    string name;
    string type;

public:
    SymbolInfo *next;

    SymbolInfo(string name, string type, SymbolInfo *next = nullptr)
    {
        this->name = name;
        this->type = type;
        this->next = next;
    }

    string getName()
    {
        return name;
    }

    string getType()
    {
        return type;
    }

    void setName(string name)
    {
        this->name = name;
    }

    void setType(string type)
    {
        this->type = type;
    }

    ~SymbolInfo()
    {
        if (next != nullptr)
        {
            delete next;
        }
    }

    void print()
    {
        if (type.substr(0, 8) == "FUNCTION")
        {
            stringstream ss(type);
            string keyword, returnType, token;
            vector<string> params;

            ss >> keyword >> returnType;

            while (ss >> token)
            {
                params.push_back(token);
            }

            cout << "<" << name << "," << keyword << "," << returnType << "<==(";
            for (size_t i = 0; i < params.size(); ++i)
            {
                cout << params[i];
                if (i != params.size() - 1)
                    cout << ",";
            }
            cout << ")> ";
        }
        else if (type.substr(0, 6) == "STRUCT" || type.substr(0, 5) == "UNION")
        {
            stringstream ss(type);
            string keyword, fieldType, fieldName;
            vector<pair<string, string>> fields;

            ss >> keyword;

            while (ss >> fieldType >> fieldName)
            {
                fields.emplace_back(fieldType, fieldName);
            }

            cout << "<" << name << "," << keyword << ",{";
            for (size_t i = 0; i < fields.size(); ++i)
            {
                cout << "(" << fields[i].first << "," << fields[i].second << ")";
                if (i != fields.size() - 1)
                    cout << ",";
            }
            cout << "}> ";
        }
        else
        {
            cout << "<" << name << "," << type << "> ";
        }
    }
};


