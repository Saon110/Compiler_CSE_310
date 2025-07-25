# CSE 310 - Compiler Design Course Repository

This repository contains all assignments and projects for CSE 310 (Compiler Design) course.

## Repository Structure

### 📁 Offline1 - Symbol Table Implementation

Implementation of a symbol table data structure with hash table-based scope management.

**Key Components:**

- `SymbolTable.cpp` - Main symbol table class with scope management
- `ScopeTable.cpp` - Individual scope implementation with hash table
- `SymbolInfo.cpp` - Symbol information storage class  
- `Hash.cpp` - Hash function implementations
- `test.cpp` - Test cases for symbol table functionality

**Features:**

- Multiple hash functions (SDBM, DJB2, etc.)
- Collision handling with chaining
- Nested scope management
- Symbol insertion, deletion, and lookup operations

### 📁 Offline2 - Lexical Analyzer (Flex)

Implementation of a lexical analyzer using Flex for tokenizing C-like language.

**Key Components:**

- `lex.l` - Flex specification file with token definitions
- `2105110.l` - Student implementation
- Token recognition for:
  - Keywords, identifiers, operators
  - String and character literals
  - Comments (single-line and multi-line)
  - Numbers (integers, floats)

**Output Files:**

- `2105110_token.txt` - Generated tokens
- `2105110_log.txt` - Lexical analysis log

### 📁 Offline3 - Parser Implementation (ANTLR)

Syntax analyzer implementation using ANTLR4 for parsing C-like language.

**Key Components:**

- `C8086Lexer.g4` - ANTLR lexer grammar
- `C8086Parser.g4` - ANTLR parser grammar  
- `Ctester.cpp` - Parser testing framework
- Symbol table integration for semantic analysis

**Features:**

- Context-free grammar for C-like language
- Abstract Syntax Tree (AST) generation
- Error recovery and reporting
- Semantic analysis integration

### 📁 Offline4 - Intermediate Code Generation

Implementation of intermediate code generation and optimization.

**Key Components:**

- `optimizer.cpp` - Code optimization algorithms
- `icg.out` - Intermediate code generator output
- `emu8086_output.txt` - 8086 assembly output
- `printProc.lib` - Utility procedures

**Features:**

- Three-address code generation
- Basic code optimization techniques
- Target code generation for 8086 architecture

### 📁 antlr_intro++_files

ANTLR tutorial and reference materials.

## 🚀 How to Run

### Offline1 (Symbol Table)

```bash
cd Offline1
g++ -o test test.cpp
./test
```

### Offline2 (Lexical Analyzer)

```bash
cd Offline2
flex lex.l
g++ -o lexer lex.yy.c
./lexer < input.txt
```

### Offline3 (Parser)

```bash
cd Offline3
./run-script.sh
```

### Offline4 (Code Generation)

```bash
cd Offline4
./run-script.sh
```

## 📋 Requirements

- **C++ Compiler**: GCC 7.0+ or Clang 6.0+
- **Flex**: Version 2.6+
- **ANTLR4**: Version 4.8+
- **Java**: JDK 8+ (for ANTLR)

## 🔧 Build Tools

Each offline includes build scripts:

- `run-script.sh` - Main build and run script
- `clean-script.sh` - Clean generated files
- `Makefile` - Alternative build system (where applicable)

## 📊 Testing

Sample input/output files are provided in each offline directory:

- `input*.txt` - Test input files
- `output*.txt` - Expected output files
- `sample_io/` - Additional test cases

## 📖 Documentation

- **Assignment Specifications**: PDF files in each offline directory
- **Code Comments**: Inline documentation for key functions
- **Reports**: Generated analysis reports in `report.txt` files

## 🎯 Learning Objectives

1. **Symbol Table Management**: Understanding scope rules and symbol resolution
2. **Lexical Analysis**: Pattern matching and token generation
3. **Syntax Analysis**: Grammar design and parsing techniques
4. **Semantic Analysis**: Type checking and error detection
5. **Code Generation**: Intermediate and target code production
6. **Optimization**: Basic code improvement techniques

## 👨‍💻 Student Information

- **Student ID**: 2105110
- **Course**: CSE 310 - Compiler Design
- **Semester**: 3-1

## 📄 License

This repository is for educational purposes as part of CSE 310 coursework.

---

**Note**: This repository contains academic assignments. Please respect academic integrity policies when using this code as reference.