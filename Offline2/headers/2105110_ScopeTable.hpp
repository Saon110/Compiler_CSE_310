#ifndef SCOPETABLE_2105110_HPP
#define SCOPETABLE_2105110_HPP

#include "2105110_SymbolInfo.hpp"
#include "2105110_Hash.hpp"
using namespace std;

class ScopeTable
{
private:
    int id;
    int num_buckets;
    SymbolInfo **table;
    int num_of_child;
    ScopeTable *parent_scope;
    int label;
    Hash hash;
    int collision_count ;
    string display_id;

public:
    ScopeTable(int n, ScopeTable *parent_scope = nullptr, int label = 0, string hash_function = "SDBMHash")
    {
        this->num_buckets = n;
        this->parent_scope = parent_scope;
        this->num_of_child = 0;
        this->label = label;
        this->collision_count = 0;
        table = new SymbolInfo *[num_buckets];
        hash.setHashFunction(hash_function);

        for (int i = 0; i < num_buckets; i++)
        {
            table[i] = nullptr;
        }

        id = 0;
        if (parent_scope != nullptr)
        {
            
            parent_scope->num_of_child++;
            this->display_id = parent_scope->get_id() + "." + to_string(parent_scope->getNumOfChild());
            id = parent_scope->id + 1;
            cout << "\tScopeTable# " << label << " created" << endl;
        }
        else
        {
            this->display_id = "1";
            id = 1;
            cout << "\tScopeTable# " << label << " created" << endl;
        }
    }

    bool insert(SymbolInfo &symbol, ostream& out = cout)
    {
        unsigned int index = hash.getHashvalue(symbol.getName(), num_buckets);
        SymbolInfo *new_symbol = new SymbolInfo(symbol.getName(), symbol.getType());
        int i = 0;
        if (table[index] == nullptr)
        {
            table[index] = new_symbol;
            cout << "\tInserted in ScopeTable# " << display_id << " at position " << index << ", 1" << endl;
            return true;
        }

        else
        {
            SymbolInfo *temp = table[index];
            while (temp != nullptr)
            {
                if (temp->getName() == symbol.getName())
                {
                    // cout << "\t'" << symbol.getName() << "' already exists in the current ScopeTable" << endl;
                    symbol.print(out);
                    out<< " already exists in ScopeTable# " << display_id << " at position " << index  << ", " << i << endl<<endl;

                    delete new_symbol;
                    return false;
                }
                if (temp->next == nullptr)
                    break;
                temp = temp->next;
                i++;
            }
            temp->next = new_symbol;
            collision_count++;
            cout << "\tInserted in ScopeTable# " << label << " at position " << index  << ", " << i << endl;
            return true;
        }
    }

    SymbolInfo *LookUp(string name)
    {
        unsigned int index = hash.getHashvalue(name, num_buckets);
        SymbolInfo *temp = table[index];
        int i = 1;
        while (temp != nullptr)
        {
            if (temp->getName() == name)
            {
                cout << "\t'" << name << "' found in ScopeTable# " << label << " at position " << index + 1 << ", " << i << endl;
                return temp;
            }
            temp = temp->next;
            i++;
        }
        return nullptr;
    }

    bool Delete(string name)
    {
        unsigned int index = hash.getHashvalue(name, num_buckets);
        SymbolInfo *temp = table[index];
        SymbolInfo *prev = nullptr;
        int i = 1;
        while (temp != nullptr)
        {
            if (temp->getName() == name)
            {
                if (prev == nullptr)
                {
                    table[index] = temp->next;
                }
                else
                {
                    prev->next = temp->next;
                }
                delete temp;
                cout << "\tDeleted '" << name << "' from ScopeTable# " << label << " at position " << index + 1 << ", " << i << endl;
                return true;
            }
            prev = temp;
            temp = temp->next;
            i++;
        }
        cout << "\tNot found in the current ScopeTable" << endl;
        return false;
    }

    void print(ostream& out, int depth = 0)
    {
        string indent(depth, '\t'); 
        out << indent << "ScopeTable # " << display_id << endl;
        for (int i = 0; i < num_buckets; i++)
        {
            if (table[i] != nullptr)
            {
                out << indent << i << " --> ";
                SymbolInfo *temp = table[i];
                while (temp != nullptr)
                {

                    temp->print(out);
                    temp = temp->next;
                }
                out << endl;
            }
            else
            {
                // out << indent << i + 1 << "--> " << endl;
            }
        }
    }

    ~ScopeTable()
    {
        for (int i = 0; i < num_buckets; i++)
        {
            SymbolInfo *temp = table[i];
            if (temp != nullptr)
            {
                while(temp != nullptr)
                {
                    SymbolInfo *next = temp->next;
                    delete temp;
                    temp = next;
                }
            }
        }
        delete[] table;
        cout << "\tScopeTable# " << label << " removed" << endl;
    }

    string get_id()
    {
        return display_id;
    }

    int getCollisionCount()
    {
        return collision_count;
    }

    int getId()
    {
        return id;
    }

    int getNumOfChild()
    {
        return num_of_child;
    }

    void setNumOfChild(int num)
    {
        num_of_child = num;
    }

    ScopeTable *getParentScope()
    {
        return parent_scope;
    }

    void setParentScope(ScopeTable *parent_scope)
    {
        this->parent_scope = parent_scope;
    }

    void setId(int id)
    {
        this->id = id;
    }

    void setNumBuckets(int num_buckets)
    {
        this->num_buckets = num_buckets;
    }

    int getNumBuckets()
    {
        return num_buckets;
    }

    SymbolInfo **getTable()
    {
        return table;
    }

    void setTable(SymbolInfo **table)
    {
        this->table = table;
    }
};

#endif