#ifndef HASH_2105110_HPP
#define HASH_2105110_HPP

#include <bits/stdc++.h>
using namespace std;

class Hash
{
    string hash_function; // SDBMHash, DJBHash, BKDRHash, APHash
public:
    Hash(string hash_function = "SDBMHash")
    {
        this->hash_function = hash_function;
    }

    void setHashFunction(string hash_function)
    {
        this->hash_function = hash_function;
    }

    unsigned int getHashvalue(string name, unsigned int num_buckets)
    {
        if (hash_function == "SDBMHash")
        {
            return SDBMHash(name.c_str(), num_buckets);
        }
        else if (hash_function == "DJBHash")
        {
            return DJBHash(name, num_buckets);
        }
        else if (hash_function == "BKDRHash")
        {
            return BKDRHash(name, num_buckets);
        }
        else if (hash_function == "DEKHash")
        {
            return DEKHash(name, num_buckets);
        }
        else
        {
            cout << "Invalid hash function" << endl;
            return 0;
        }
    }

    // unsigned int SDBMHash(string str, unsigned int num_buckets)
    // {
    //     unsigned int hash = 0;
    //     unsigned int len = str.length();
    //     for (unsigned int i = 0; i < len; i++)
    //     {
    //         hash = ((str[i]) + (hash << 6) + (hash << 16) - hash) %
    //                num_buckets;
    //     }
    //     return hash;
    // }

    unsigned int SDBMHash(const char *p, unsigned int num_buckets) { 
        unsigned int hash = 0; 
        auto *str = (unsigned char *) p; 
        int c{}; 
        while ((c = *str++)) { 
            hash = c + (hash << 6) + (hash << 16) - hash; 
        } 
        return hash%num_buckets; 
    }

    unsigned int DJBHash(string str, unsigned int num_buckets) // https://www.partow.net/programming/
    {
        unsigned int hash = 5381;
        unsigned int len = str.length();
        for (unsigned int i = 0; i < len; i++)
        {
            hash = ((hash << 5) + hash) + str[i]; // hash * 33 + c
            hash %= num_buckets;
        }
        return hash % num_buckets;
    }

    unsigned int BKDRHash(string str, unsigned int num_buckets) // https://www.partow.net/programming/
    {
        unsigned int seed = 131; // 31 131 1313 13131 131313 etc..
        unsigned int hash = 0;
        unsigned int len = str.length();
        for (unsigned int i = 0; i < len; i++)
        {
            hash = (hash * seed) + str[i];
            hash %= num_buckets;
        }
        return hash % num_buckets;
    }

    // DEKHASH
    unsigned int DEKHash(string str, unsigned int num_buckets) // https://www.partow.net/programming/
    {
        unsigned int hash = 0;
        unsigned int len = str.length();
        for (unsigned int i = 0; i < len; i++)
        {
            hash = ((hash << 5) ^ (hash >> 27)) ^ str[i]; // hash * 31 + c
            hash %= num_buckets;
        }
        return hash % num_buckets;
    }
};


#endif 
