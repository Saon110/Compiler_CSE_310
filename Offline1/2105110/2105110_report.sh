#!/bin/bash
g++ -fsanitize=address 2105110_report_generator.cpp -o 2105110_report_generator
./2105110_report_generator "2105110_report_custom_input.txt" "report.txt"