#!/bin/bash

# Path to ANTLR jar
ANTLR_JAR="/usr/local/lib/antlr-4.13.2-complete.jar"

# Generate C++ lexer and parser
java -Xmx500M -cp "$ANTLR_JAR:." org.antlr.v4.Tool -Dlanguage=Cpp C8086Lexer.g4
java -Xmx500M -cp "$ANTLR_JAR:." org.antlr.v4.Tool -Dlanguage=Cpp C8086Parser.g4

# Compile C++ source files
g++ -std=c++17 -w -I/usr/local/include/antlr4-runtime -c C8086Lexer.cpp C8086Parser.cpp Ctester.cpp

# Link object files into executable
g++ -std=c++17 -w C8086Lexer.o C8086Parser.o Ctester.o -L/usr/local/lib/ -lantlr4-runtime -o Ctester.out -pthread

# Set library path and run the executable
LD_LIBRARY_PATH=/usr/local/lib ./Ctester.out "$1"

# Compile and run optimizer.cpp
g++ -std=c++17 -w optimizer.cpp -o optimizer.out
./optimizer.out