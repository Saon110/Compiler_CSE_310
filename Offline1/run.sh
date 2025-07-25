#!/bin/bash
g++ -fsanitize=address test.cpp -o test
./test "input.txt" "output.txt" "SDBMHash"