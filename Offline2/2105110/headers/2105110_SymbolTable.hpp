#ifndef SYMBOLTABLE_2105110_HPP
#define SYMBOLTABLE_2105110_HPP

#include "2105110_ScopeTable.hpp"

using namespace std;

class SymbolTable
{
    ScopeTable *current_scope;
    int num_buckets;
    int num_scopes;
    string hash_function;
    int total_collisions;

public:
    SymbolTable(int n, int num_scopes = 1, string hash_function = "SDBMHash")
    {
        num_buckets = n;
        this->num_scopes = num_scopes;
        this->hash_function = hash_function;
        total_collisions = 0;
        current_scope = new ScopeTable(num_buckets, nullptr, num_scopes, hash_function);
    }

    void EnterScope()
    {
        num_scopes++;
        ScopeTable *new_scope = new ScopeTable(num_buckets, current_scope, num_scopes, hash_function);
        current_scope = new_scope;
    }

    void ExitScope()
    {
        if (current_scope->getParentScope() != nullptr)
        {
            ScopeTable *temp = current_scope;
            current_scope = current_scope->getParentScope();
            delete temp;
        }
        else
        {
            cout << "\tNo scope to exit" << endl;
        }
    }

    bool Insert(SymbolInfo &symbol, ostream& out = cout)
    {
        if (current_scope != nullptr)
        {
            int before = current_scope->getCollisionCount();
            bool inserted = current_scope->insert(symbol, out);
            int after = current_scope->getCollisionCount();
            total_collisions += (after - before);
            return inserted;
        }
        else
        {
            cout << "No scope to insert" << endl;
            return false;
        }
    }

    bool Remove(string name)
    {
        if (current_scope != nullptr)
        {
            return current_scope->Delete(name);
        }
        else
        {
            cout << "No scope to remove from" << endl;
            return false;
        }
    }

    SymbolInfo *LookUp(string name)
    {
        ScopeTable *temp = current_scope;
        while (temp != nullptr)
        {
            SymbolInfo *symbol = temp->LookUp(name);
            if (symbol != nullptr)
            {
                return symbol;
            }
            temp = temp->getParentScope();
        }
        cout << "\t'" << name << "' not found in any of the ScopeTables" << endl;
        return nullptr;
    }

    void PrintCurrentScopeTable(ostream& out)
    {
        if (current_scope != nullptr)
        {
            current_scope->print(out);
        }
        else
        {
            cout << "No scope to print" << endl;
        }
    }

    void PrintAllScopeTable(ostream& out)
    {
        int depth = 0;
        ScopeTable *temp = current_scope;
        while (temp != nullptr)
        {
            temp->print(out,depth);
            temp = temp->getParentScope();
            // depth++;
        }
    }

    int getTotalCollisions()
    {
        return total_collisions;
    }

    int getNumScopes()
    {
        return num_scopes;
    }

    ~SymbolTable()
    {
        while (current_scope != nullptr)
        {
            ScopeTable *temp = current_scope;
            current_scope = current_scope->getParentScope();
            delete temp;
        }
    }
};

#endif // SYMBOLTABLE_2105110_HPP