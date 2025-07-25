#!/bin/bash
g++ -fsanitize=address 2105110_main.cpp -o 2105110_main
./2105110_main "input1.txt" "output1.txt" "SDBMHash"