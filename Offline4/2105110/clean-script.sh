#!/bin/bash

# Enable extended globbing for pattern matching
shopt -s extglob

# Loop through all files that do NOT match *.sh, *.g4, *pdf, *.txt, icg.out, printProc.lib, or Ctester.cpp
for file in !(*.sh|*.g4|*pdf|*.txt|icg.out|printProc.lib|Ctester.cpp|optimizer.cpp); do
    # Only delete if it's a regular file
    if [[ -f "$file" ]]; then
        rm -f "$file"
    fi
done

rm -f code.asm optcode.asm error.txt log.txt parsetree.txt recsrc.txt token.txt code.txt

# Remove the 'output' directory if it exists
rm -rf output
