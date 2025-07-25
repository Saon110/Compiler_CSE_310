#ifndef SymbolInfo_2105110_HPP
#define SymbolInfo_2105110_HPP

#include <iostream>
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
        
    }

    void print()
    {
        const int MAX_PARAMS = 100;

        size_t pos = 0;
        string token;
        string type_copy = type;

        if (type.substr(0, 8) == "FUNCTION")
        {
            string keyword = "FUNCTION";
            size_t keyword_pos = type.find(keyword);
            size_t return_type_end = type.find(" ", keyword_pos + keyword.length());
            string returnType = type.substr(keyword_pos + keyword.length(), return_type_end - keyword_pos - keyword.length());

            string params_str = type.substr(return_type_end);
            size_t start = 0;
            string params[MAX_PARAMS];
            int param_count = 0;

            while ((pos = params_str.find(" ")) != string::npos && param_count < MAX_PARAMS)
            {
                token = params_str.substr(0, pos);
                params[param_count++] = token;
                params_str.erase(0, pos + 1);
            }

            cout << "<" << name << "," << keyword << "," << returnType << "<==(";
            for (int i = 0; i < param_count; ++i)
            {
                cout << params[i];
                if (i != param_count - 1)
                    cout << ",";
            }
            cout << ")> ";
        }
        else if (type.substr(0, 6) == "STRUCT" || type.substr(0, 5) == "UNION")
        {
            string keyword = type.substr(0, type.find(" "));
            string fields_str = type.substr(type.find(" ") + 1);
            string fieldTypes[MAX_PARAMS];
            string fieldNames[MAX_PARAMS];
            int field_count = 0;

            size_t pos = 0;
            string field_str;
            while ((pos = fields_str.find(" ")) != string::npos && field_count < MAX_PARAMS)
            {
                field_str = fields_str.substr(0, pos);
                fieldTypes[field_count] = field_str;
                fields_str.erase(0, pos + 1);

                pos = fields_str.find(" ");
                if (pos != string::npos)
                {
                    fieldNames[field_count] = fields_str.substr(0, pos);
                    fields_str.erase(0, pos + 1);
                    field_count++;
                }
            }

            cout << "<" << name << "," << keyword << ",{";
            for (int i = 0; i < field_count; ++i)
            {
                cout << "(" << fieldTypes[i] << "," << fieldNames[i] << ")";
                if (i != field_count - 1)
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

#endif
