#!/bin/bash
g++ -fsanitize=address report_generator.cpp -o report_generator
./report_generator "input.txt" "report.txt"