parser grammar C8086Parser;

options {
	tokenVocab = C8086Lexer;
}

@parser::header {
    #include <iostream>
    #include <fstream>
    #include <string>
    #include <cstdlib>
    #include "C8086Lexer.h"
	#include "headers/str_list.cpp"
    #include "headers/2105110_SymbolTable.hpp"

    extern std::ofstream parserLogFile;
    extern std::ofstream errorFile;

    extern int syntaxErrorCount;
}

@parser::members {
    SymbolTable st = SymbolTable(11);
    int scope_flag = 0;
    int totalLines = 0; // Track total lines in the program
    // take a map for mapping variables if they are arrays or not
    std::map<std::string, bool> isArrayMap; // true if array, false if not

    // take a map for storing functions and their return types
    std::map<std::string, std::string> functionReturnTypeMap; // function name -> return type
    // take a map for total number of arguments in function
    std::map<std::string, int> functionArgumentCountMap; // function name -> argument count

    void writeIntoparserLogfFile(const std::string message) {
        if (!parserLogFile) {
            std::cout << "Error opening parserLogFile.txt" << std::endl;
            return;
        }

        parserLogFile << message << std::endl;
        parserLogFile.flush();
    }

    void writeIntoErrorFile(const std::string message) {
        if (!errorFile) {
            std::cout << "Error opening errorFile.txt" << std::endl;
            return;
        }
        errorFile << message << std::endl;
        errorFile.flush();
    }
}

start:
	program {
        writeIntoparserLogfFile("Parsing completed successfully with " + std::to_string(syntaxErrorCount) + " syntax errors.");
        writeIntoparserLogfFile("Total lines in the program: " + std::to_string(totalLines));
	};

program
	returns[str_list var_list]:
	p = program u = unit { 
        $var_list.set_variables($p.var_list.get_variables());
        $var_list.add("\n");
        $var_list.add($u.var_list.get_variables());
        $var_list.set_line_number($u.var_list.get_line_number());
        writeIntoparserLogfFile(
            "\nLine " + std::to_string($p.var_list.get_line_number()) + ": " +
            "program : program unit\n\n" +
            $var_list.get_list_as_string() + "\n\n"
        );
    }
	| unit {
        $var_list.set_variables($unit.var_list.get_variables());
        $var_list.set_line_number($unit.var_list.get_line_number());
        writeIntoparserLogfFile(
            "\nLine " + std::to_string($unit.var_list.get_line_number()) + ": " +
            "program : unit\n\n" +
            $var_list.get_list_as_string() + "\n\n"
        );
    };

unit
	returns[str_list var_list]:
	var_declaration {
        $var_list.set_variables($var_declaration.var_list.get_variables());
        $var_list.set_line_number($var_declaration.var_list.get_line_number());
        // Update total line count
        //totalLines = std::max(totalLines, $var_declaration.var_list.get_line_number());
        writeIntoparserLogfFile(
            "Line " + std::to_string($var_declaration.var_list.get_line_number()) + ": " +
            "unit : var_declaration\n\n" +
            $var_list.get_list_as_string() + "\n"
        );
    }
	| func_declaration { 
        $var_list.set_variables($func_declaration.var_list.get_variables());
        $var_list.set_line_number($func_declaration.var_list.get_line_number());
        // Update total line count
        //totalLines = std::max(totalLines, $func_declaration.var_list.get_line_number());
        writeIntoparserLogfFile(
            "Line " + std::to_string($func_declaration.var_list.get_line_number()) + ": " +
            "unit : func_declaration\n\n" +
            $var_list.get_list_as_string() + "\n"
        );
    }
	| func_definition { 
        $var_list.set_variables($func_definition.var_list.get_variables());
        $var_list.set_line_number($func_definition.var_list.get_line_number());
        // Update total line count
        //totalLines = std::max(totalLines, $func_definition.var_list.get_line_number());
        writeIntoparserLogfFile(
            "Line " + std::to_string($func_definition.var_list.get_line_number()) + ": " +
            "unit : func_definition\n\n" +
            $var_list.get_list_as_string() + "\n"
        );
    };

func_declaration
	returns[str_list var_list]:
	type_specifier ID LPAREN { st.EnterScope(); } parameter_list RPAREN { st.PrintAllScopeTable(parserLogFile); st.ExitScope(); 
		} SEMICOLON {
            // if the function is not in return type map, insert it
            if(functionReturnTypeMap.find($ID->getText()) == functionReturnTypeMap.end()) {
                functionReturnTypeMap[$ID->getText()] = $type_specifier.var_list.get_list_as_string();
                // calculate argument count
                int argCount = 0;
                // split the parameter_list by commas
                std::string paramList = $parameter_list.var_list.get_list_as_string();
                std::stringstream ss(paramList);
                std::string token;
                while (std::getline(ss, token, ',')) {
                    argCount++;
                }
                functionArgumentCountMap[$ID->getText()] = argCount;
            }
            $var_list.add($type_specifier.var_list.get_variables());
            $var_list.add(" ");
            $var_list.add($ID->getText());
            $var_list.add("(");
            $var_list.add($parameter_list.var_list.get_variables());
            $var_list.add(")");
            $var_list.add(";");
            $var_list.set_line_number($ID->getLine());
            writeIntoparserLogfFile(
                "Line " + std::to_string($ID->getLine()) + ": " +
                "func_declaration : type_specifier ID LPAREN parameter_list RPAREN SEMICOLON\n\n" +
                $var_list.get_list_as_string() + "\n"
            );
        }
	| type_specifier ID LPAREN RPAREN SEMICOLON {
            // if the function is not in return type map, insert it
            if(functionReturnTypeMap.find($ID->getText()) == functionReturnTypeMap.end()) {
                functionReturnTypeMap[$ID->getText()] = $type_specifier.var_list.get_list_as_string();
                // calculate argument count
                int argCount = 0;
                // split the parameter_list by commas
                std::string paramList = "";
                std::stringstream ss(paramList);
                std::string token;
                while (std::getline(ss, token, ',')) {
                    argCount++;
                }
                functionArgumentCountMap[$ID->getText()] = argCount;
            }
            $var_list.add($type_specifier.var_list.get_variables());
            $var_list.add(" ");
            $var_list.add($ID->getText());
            $var_list.add("(");
            $var_list.add(")");
            $var_list.add(";");
            $var_list.set_line_number($type_specifier.var_list.get_line_number());
            writeIntoparserLogfFile(
                "Line " + std::to_string($type_specifier.var_list.get_line_number()) + ": " +
                "func_declaration : type_specifier ID LPAREN RPAREN SEMICOLON\n\n" +
                $var_list.get_list_as_string() + "\n"
            );
        };

func_definition
	returns[str_list var_list]:
	type_specifier ID LPAREN { 
        // Insert function name into symbol table before entering scope
        SymbolInfo si = SymbolInfo($ID->getText(), "ID");
        SymbolInfo *lookup = st.LookUpInCurrentScope($ID->getText());
        if(lookup != nullptr) {
            // Function already declared - log error
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
            syntaxErrorCount++;
        } else {
            st.Insert(si);
        }
        // Now enter scope for function body
        st.EnterScope(); scope_flag = 1; 
        } parameter_list RPAREN compound_statement {
        // if the function is not in return type map, insert it
        if(functionReturnTypeMap.find($ID->getText()) == functionReturnTypeMap.end()) {
            functionReturnTypeMap[$ID->getText()] = $type_specifier.var_list.get_list_as_string();
            // calculate argument count
            int argCount = 0;
            // split the parameter_list by commas
            std::string paramList = $parameter_list.var_list.get_list_as_string();
            std::stringstream ss(paramList);
            std::string token;
            while (std::getline(ss, token, ',')) {
                argCount++;
            }
            functionArgumentCountMap[$ID->getText()] = argCount;
        }
        // check type mismatch for function return type using map

        std::string returnType = functionReturnTypeMap[$ID->getText()];
        if(returnType != $type_specifier.var_list.get_list_as_string()) {
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Type mismatch for function " + $ID->getText()+"()");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Type mismatch for function " + $ID->getText()+"()\n");
        }

        // check if argument count matches
        int argCount = functionArgumentCountMap[$ID->getText()];
        int paramCount = 0;
        std::string paramList = $parameter_list.var_list.get_list_as_string();
        std::stringstream ss(paramList);
        std::string token;
        while (std::getline(ss, token, ',')) {
            paramCount++;
        }
        if(argCount != paramCount) {
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Argument count mismatch for function " + $ID->getText()+"()");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Argument count mismatch for function " + $ID->getText()+"()\n");
        }

        $var_list.add($type_specifier.var_list.get_variables());
        $var_list.add(" ");
        $var_list.add($ID->getText());
        $var_list.add("(");
        $var_list.add($parameter_list.var_list.get_variables());
        $var_list.add(")");
        $var_list.add($compound_statement.var_list.get_variables());
        $var_list.set_line_number($RPAREN->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($compound_statement.var_list.get_line_number()) + ": func_definition : type_specifier ID LPAREN parameter_list RPAREN compound_statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| type_specifier ID LPAREN {
        // Insert function name into symbol table before entering scope
        SymbolInfo si = SymbolInfo($ID->getText(), "ID");
        SymbolInfo *lookup = st.LookUpInCurrentScope($ID->getText());
        if(lookup != nullptr) {
            // Function already declared - log error
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
            syntaxErrorCount++;
        } else {
            st.Insert(si);
        }
        // Now enter scope for function body
        st.EnterScope(); scope_flag = 1;
      } RPAREN compound_statement { 
        // if the function is not in return type map, insert it
        if(functionReturnTypeMap.find($ID->getText()) == functionReturnTypeMap.end()) {
            functionReturnTypeMap[$ID->getText()] = $type_specifier.var_list.get_list_as_string();
            // calculate argument count
            int argCount = 0;
            // split the parameter_list by commas
            std::string paramList = "";
            std::stringstream ss(paramList);
            std::string token;
            while (std::getline(ss, token, ',')) {
                argCount++;
            }
            functionArgumentCountMap[$ID->getText()] = argCount;
        }
        // check type mismatch for function return type using map
        std::string returnType = functionReturnTypeMap[$ID->getText()];
        if(returnType != $type_specifier.var_list.get_list_as_string()) {
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Type mismatch for function " + $ID->getText()+"()");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Type mismatch for function " + $ID->getText()+"()\n");
        }
        // check if argument count matches
        int argCount = functionArgumentCountMap[$ID->getText()];
        int paramCount = 0;
        std::string paramList = "";
        std::stringstream ss(paramList);
        std::string token;
        while (std::getline(ss, token, ',')) {
            paramCount++;
        }
        if(argCount != paramCount) {
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Argument count mismatch for function " + $ID->getText()+"()");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Argument count mismatch for function " + $ID->getText()+"()\n");
        }
        $var_list.add($type_specifier.var_list.get_variables());
        $var_list.add(" ");
        $var_list.add($ID->getText());
        $var_list.add("(");
        $var_list.add(")");
        $var_list.add($compound_statement.var_list.get_variables());
        $var_list.set_line_number($RPAREN->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($compound_statement.var_list.get_line_number()) + ": func_definition : type_specifier ID LPAREN RPAREN compound_statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

parameter_list
	returns[str_list var_list]:
	p = parameter_list COMMA type_specifier ID { 
            std::string type = $type_specifier.var_list.get_list_as_string();
            SymbolInfo si = SymbolInfo($ID->getText(), type);
            SymbolInfo *lookup = st.LookUpInCurrentScope($ID->getText());
            if(lookup != nullptr) {
                // Parameter already exists in current scope - log error
                writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
                writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
                syntaxErrorCount++;
            } else {
                st.Insert(si);
            }
            $var_list.add($p.var_list.get_variables());
            $var_list.add(",");
            $var_list.add($type_specifier.var_list.get_variables());
            $var_list.add(" ");
            $var_list.add($ID->getText());
            $var_list.set_line_number($type_specifier.var_list.get_line_number());
            writeIntoparserLogfFile(
                "Line " + std::to_string($type_specifier.var_list.get_line_number()) + ": " +
                "parameter_list : parameter_list COMMA type_specifier ID\n\n" +
                $var_list.get_list_as_string() + "\n"
            );
         }
	| p = parameter_list COMMA type_specifier { 
            $var_list.add($p.var_list.get_variables());
            $var_list.add(",");
            $var_list.add($type_specifier.var_list.get_variables());
            $var_list.set_line_number($COMMA->getLine());
            writeIntoparserLogfFile(
                "Line " + std::to_string($COMMA->getLine()) + ": " +
                "parameter_list : parameter_list COMMA type_specifier\n\n" +
                $var_list.get_list_as_string() + "\n"
            );
        }
	| type_specifier ID { 
            std::string type = $type_specifier.var_list.get_list_as_string();
            SymbolInfo si = SymbolInfo($ID->getText(), type);
            SymbolInfo *lookup = st.LookUpInCurrentScope($ID->getText());
            if(lookup != nullptr) {
                // Parameter already exists in current scope - log error
                writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
                writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
                syntaxErrorCount++;
            } else {
                st.Insert(si);
            }
            $var_list.add($type_specifier.var_list.get_variables());
            $var_list.add(" ");
            $var_list.add($ID->getText());
            $var_list.set_line_number($type_specifier.var_list.get_line_number());
            writeIntoparserLogfFile(
                "Line " + std::to_string($type_specifier.var_list.get_line_number()) + ": " +
                "parameter_list : type_specifier ID\n\n" +
                $var_list.get_list_as_string() + "\n"
            );
        }
	| type_specifier { 

            $var_list.add($type_specifier.var_list.get_variables());
            $var_list.set_line_number($type_specifier.var_list.get_line_number());
            writeIntoparserLogfFile(
                "Line " + std::to_string($type_specifier.var_list.get_line_number()) + ": " +
                "parameter_list : type_specifier\n\n" +
                $var_list.get_list_as_string() + "\n"
            );
        };

compound_statement
	returns[str_list var_list]:
	LCURL { if(scope_flag == 0) st.EnterScope(); 
    scope_flag = 0;
     } statements RCURL { 
        $var_list.add($LCURL->getText());
        $var_list.add("\n");
        $var_list.add($statements.var_list.get_variables());
        $var_list.add("\n");
        $var_list.add($RCURL->getText());
        $var_list.set_line_number($LCURL->getLine());
        writeIntoparserLogfFile(
            "Line " + std::to_string($LCURL->getLine()) + ": compound_statement : LCURL statements RCURL\n\n" +
            $var_list.get_list_as_string() + "\n"
        );
        st.PrintAllScopeTable(parserLogFile);
        writeIntoparserLogfFile("\n");
        st.ExitScope();
     }
	| LCURL RCURL { 
        $var_list.add($LCURL->getText());
        $var_list.add("\n");
        $var_list.add($RCURL->getText());
        $var_list.set_line_number($LCURL->getLine());
        writeIntoparserLogfFile(
            "Line " + std::to_string($LCURL->getLine()) + ": compound_statement : LCURL RCURL\n\n" +
            $var_list.get_list_as_string() + "\n"
        );
     };

var_declaration
	returns[str_list var_list]:
	t = type_specifier dl = declaration_list sm = SEMICOLON {
        std::string decl_list = $dl.var_list.get_list_as_string();
        std::stringstream ss(decl_list);
        std::string item;
        while (std::getline(ss, item, ',')) {
            std::cout << "Item: " << item << std::endl;
            // take the item until the LTHIRD
            size_t lthird_pos = item.find('[');
            if (lthird_pos != std::string::npos) {
                item = item.substr(0, lthird_pos);
            }
            SymbolInfo si = SymbolInfo(item, $t.var_list.get_list_as_string());
            SymbolInfo *lookup = st.LookUpInCurrentScope(item);
            if(lookup != nullptr) {
                // Variable already exists in current scope - log error
                writeIntoErrorFile("Error at line " + std::to_string($dl.var_list.get_line_number()) + ": Multiple declaration of " + item);
                writeIntoparserLogfFile("Error at line " + std::to_string($dl.var_list.get_line_number()) + ": Multiple declaration of " + item);
                syntaxErrorCount++;
            } else {
                cout<<"Inserting variable: " << item << " of type " << $t.var_list.get_list_as_string() << endl;
                st.Insert(si);
            }
        }

        // variable type if void
        if($t.var_list.get_list_as_string() == "void") {
            writeIntoErrorFile("Error at line " + std::to_string($t.var_list.get_line_number()) + ": Variable type cannot be void\n");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($t.var_list.get_line_number()) + ": Variable type cannot be void\n");
        }
        
        $var_list.add($t.var_list.get_variables());
        $var_list.add(" ");
        $var_list.add($dl.var_list.get_variables());
        $var_list.add($sm->getText());
        $var_list.set_line_number($dl.var_list.get_line_number());
        writeIntoparserLogfFile(
            "Line " + std::to_string($sm->getLine()) + ": var_declaration : type_specifier declaration_list SEMICOLON\n\n" +
            $var_list.get_list_as_string() + "\n"
        );

      }
	| t = type_specifier de = declaration_list_err sm = SEMICOLON {
        writeIntoErrorFile(
            std::string("Line# ") + std::to_string($sm->getLine()) +
            " with error name: " + $de.error_name +
            " - Syntax error at declaration list of variable declaration"
        );
        syntaxErrorCount++;
      };

declaration_list_err
	returns[std::string error_name]:
	{
        $error_name = "Error in declaration list";
    };

type_specifier
	returns[str_list var_list]:
	INT {
            $var_list.add($INT->getText());
            $var_list.set_line_number($INT->getLine());
            writeIntoparserLogfFile("Line " + std::to_string($INT->getLine()) + ": type_specifier : INT\n");
            writeIntoparserLogfFile($INT->getText()+"\n");
        }
	| FLOAT {
            $var_list.add($FLOAT->getText());
            $var_list.set_line_number($FLOAT->getLine());
            writeIntoparserLogfFile("Line " + std::to_string($FLOAT->getLine()) + ": type_specifier : FLOAT\n");
            writeIntoparserLogfFile($FLOAT->getText()+"\n");
        }
	| VOID {
            $var_list.add($VOID->getText());
            $var_list.set_line_number($VOID->getLine());
            writeIntoparserLogfFile("Line " + std::to_string($VOID->getLine()) + ": type_specifier : VOID\n");
            writeIntoparserLogfFile($VOID->getText()+"\n");
        };
// declaration_list : declaration_list COMMA ID | declaration_list COMMA ID LTHIRD CONST_INT RTHIRD
// | ID | ID LTHIRD CONST_INT RTHIRD

declaration_list
	returns[str_list var_list]:
	dl = declaration_list COMMA ID {
                // SymbolInfo si = SymbolInfo($ID->getText(), "ID");
                // SymbolInfo *lookup = st.LookUpInCurrentScope($ID->getText());
                // if(lookup != nullptr) {
                //     // Variable already exists in current scope - log error
                //     writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
                //     writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText()+"\n");
                //     syntaxErrorCount++;
                // } else {
                //     st.Insert(si);
                // }
                isArrayMap[$ID->getText()] = false;
				$var_list.set_variables($dl.var_list.get_variables());
                $var_list.add($COMMA->getText());
				$var_list.add($ID->getText());
                $var_list.set_line_number($ID->getLine());
                writeIntoparserLogfFile("Line " + std::to_string($ID->getLine()) + ": declaration_list : declaration_list COMMA ID\n");
                writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
			}
	| dl = declaration_list COMMA ID LTHIRD CONST_INT RTHIRD { 
                // SymbolInfo si = SymbolInfo($ID->getText(), "ID_ARRAY");
                // SymbolInfo *lookup = st.LookUpInCurrentScope($ID->getText());
                // if(lookup != nullptr) {
                //     // Variable already exists in current scope - log error
                //     writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
                //     writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText()+"\n");
                //     syntaxErrorCount++;
                // } else {
                //     st.Insert(si);
                // }
                isArrayMap[$ID->getText()] = true;
				$var_list.set_variables($dl.var_list.get_variables());
                $var_list.add($COMMA->getText());
				$var_list.add($ID->getText());
                $var_list.add($LTHIRD->getText());
                $var_list.add($CONST_INT->getText());
                $var_list.add($RTHIRD->getText());
                $var_list.set_line_number($ID->getLine());
                writeIntoparserLogfFile("Line " + std::to_string($ID->getLine()) + ": declaration_list : declaration_list COMMA ID LTHIRD CONST_INT RTHIRD\n");
                writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
			}
	| ID {
                // SymbolInfo si = SymbolInfo($ID->getText(), "ID");
                // SymbolInfo *lookup = st.LookUpInCurrentScope($ID->getText());
                // if(lookup != nullptr) {
                //     // Variable already exists in current scope - log error
                //     writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
                //     writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText()+"\n");
                //     syntaxErrorCount++;
                // } else {
                //     st.Insert(si);
                // }
                isArrayMap[$ID->getText()] = false;
				writeIntoparserLogfFile("Line " + std::to_string($ID->getLine()) + ": declaration_list : ID\n");
                writeIntoparserLogfFile($ID->getText()+"\n");
				$var_list.add($ID->getText());
                $var_list.set_line_number($ID->getLine());
			}
	| ID LTHIRD CONST_INT RTHIRD { 
                // SymbolInfo si = SymbolInfo($ID->getText(), "ID_ARRAY");
                // SymbolInfo *lookup = st.LookUpInCurrentScope($ID->getText());
                // if(lookup != nullptr) {
                //     // Variable already exists in current scope - log error
                //     writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText());
                //     writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": Multiple declaration of " + $ID->getText()+"\n");
                //     syntaxErrorCount++;
                // } else {
                //     st.Insert(si);
                // }
                isArrayMap[$ID->getText()] = true;
                $var_list.add($ID->getText());
                $var_list.add($LTHIRD->getText());
                $var_list.add($CONST_INT->getText());
                $var_list.add($RTHIRD->getText());
                $var_list.set_line_number($ID->getLine());
                writeIntoparserLogfFile("Line " + std::to_string($ID->getLine()) + ": declaration_list : ID LTHIRD CONST_INT RTHIRD\n");
                writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

statements
	returns[str_list var_list]:
	statement { 
        $var_list.set_variables($statement.var_list.get_variables());
        $var_list.set_line_number($statement.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($statement.var_list.get_line_number()) + ": statements : statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| sts = statements statement { 
        $var_list.set_variables($sts.var_list.get_variables());
        $var_list.add("\n");
        $var_list.add($statement.var_list.get_variables());
        $var_list.set_line_number($statement.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($statement.var_list.get_line_number()) + ": statements : statements statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

statement
	returns[str_list var_list]:
	var_declaration { 
        $var_list.set_variables($var_declaration.var_list.get_variables());
        $var_list.set_line_number($var_declaration.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($var_declaration.var_list.get_line_number()) + ": statement : var_declaration\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| expression_statement { 
        $var_list.set_variables($expression_statement.var_list.get_variables());
        $var_list.set_line_number($expression_statement.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($expression_statement.var_list.get_line_number()) + ": statement : expression_statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
      
     }
	| compound_statement { 
        $var_list.set_variables($compound_statement.var_list.get_variables());
        $var_list.set_line_number($compound_statement.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($compound_statement.var_list.get_line_number()) + ": statement : compound_statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| FOR LPAREN expression_statement expression_statement expression RPAREN st = statement { 
        $var_list.add($FOR->getText());
        $var_list.add("(");
        $var_list.add($expression_statement.var_list.get_variables());
        $var_list.add($expression_statement.var_list.get_variables());
        $var_list.add($expression.var_list.get_variables());
        $var_list.add(")");
        $var_list.add($st.var_list.get_variables());
        $var_list.set_line_number($FOR->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($FOR->getLine()) + ": statement : FOR LPAREN expression_statement expression_statement expression RPAREN statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| IF LPAREN expression RPAREN st = statement { 
        $var_list.add($IF->getText());
        $var_list.add("(");
        $var_list.add($expression.var_list.get_variables());
        $var_list.add(")");
        $var_list.add($st.var_list.get_variables());
        $var_list.set_line_number($IF->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($IF->getLine()) + ": statement : IF LPAREN expression RPAREN statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| IF LPAREN expression RPAREN st = statement ELSE statement { 
        $var_list.add($IF->getText());
        $var_list.add("(");
        $var_list.add($expression.var_list.get_variables());
        $var_list.add(")");
        $var_list.add($st.var_list.get_variables());
        $var_list.set_line_number($IF->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($IF->getLine()) + ": statement : IF LPAREN expression RPAREN statement ELSE statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| WHILE LPAREN expression RPAREN st = statement { 
        $var_list.add($WHILE->getText());
        $var_list.add("(");
        $var_list.add($expression.var_list.get_variables());
        $var_list.add(")");
        $var_list.add($st.var_list.get_variables());
        $var_list.set_line_number($WHILE->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($WHILE->getLine()) + ": statement : WHILE LPAREN expression RPAREN statement\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| PRINTLN LPAREN ID RPAREN SEMICOLON { 
        $var_list.add($PRINTLN->getText());
        $var_list.add("(");
        $var_list.add($ID->getText());
        $var_list.add(")");
        $var_list.add($SEMICOLON->getText());
        $var_list.set_line_number($PRINTLN->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($PRINTLN->getLine()) + ": statement : PRINTLN LPAREN ID RPAREN SEMICOLON\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| RETURN expression SEMICOLON { 
        $var_list.add($RETURN->getText());
        $var_list.add(" ");
        $var_list.add($expression.var_list.get_variables());
        $var_list.add(";");
        $var_list.set_line_number($RETURN->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($RETURN->getLine()) + ": statement : RETURN expression SEMICOLON\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
      }
	| DO LCURL st = statements RCURL WHILE LPAREN expression RPAREN SEMICOLON { 
        $var_list.add($DO->getText());
        $var_list.add($LCURL->getText());
        $var_list.add($st.var_list.get_variables());
        $var_list.add($RCURL->getText());
        $var_list.add($WHILE->getText());
        $var_list.add("(");
        $var_list.add($expression.var_list.get_variables());
        $var_list.add(")"); 
        $var_list.add(";");
        writeIntoparserLogfFile("Line " + std::to_string($DO->getLine()) + ": statement : DO LCURL statement RCURL WHILE LPAREN expression RPAREN\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
        $var_list.set_line_number($DO->getLine());
     }
     // break statement
    // | do_while_statement {
    //     $var_list.set_variables($do_while_statement.var_list.get_variables());
    //     $var_list.set_line_number($do_while_statement.var_list.get_line_number());
    //     writeIntoparserLogfFile("Line " + std::to_string($do_while_statement.var_list.get_line_number()) + ": statement : do_while_statement\n");
    //     writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
    //  }
     // break statement 
    | BREAK SEMICOLON {
        $var_list.add($BREAK->getText());
        $var_list.add($SEMICOLON->getText());
        $var_list.set_line_number($BREAK->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($BREAK->getLine()) + ": statement : BREAK SEMICOLON\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
     // continue statement
    | CONTINUE SEMICOLON {
        $var_list.add($CONTINUE->getText());
        $var_list.add($SEMICOLON->getText());
        $var_list.set_line_number($CONTINUE->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($CONTINUE->getLine()) + ": statement : CONTINUE SEMICOLON\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

expression_statement
	returns[str_list var_list]:
	SEMICOLON { 
        $var_list.add($SEMICOLON->getText());
        $var_list.set_line_number($SEMICOLON->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($SEMICOLON->getLine()) + ": expression_statement : SEMICOLON\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| expression SEMICOLON { 
        // check for type mismatch
        std::string expr = $expression.var_list.get_list_as_string();
        // find the variable name and assigned value as string . can be done by splitting on ASSIGNOP
        size_t assign_pos = expr.find('=');
        if(assign_pos != std::string::npos) {
            std::string var_name = expr.substr(0, assign_pos);
            // take name upto first LTHIRD if present
            size_t lthird_pos = var_name.find('[');
            if(lthird_pos != std::string::npos) {
                var_name = var_name.substr(0, lthird_pos);
            }
            std::string assigned_value = expr.substr(assign_pos + 1);
            // lookup variable in symbol table
            SymbolInfo *lookup = st.LookUp(var_name);
            if(lookup != nullptr) {
                std::string var_type = lookup->getType();
                // check if assigned value is of the same type 
                // first check if there is no other symbol other than . and digits
                bool is_valid = true;
                for(char c : assigned_value) {
                    if(!isdigit(c) && c != '.') {
                        is_valid = false;
                        break;
                    }
                }
                if(is_valid) {
                    if(var_type == "int" && assigned_value.find('.') != std::string::npos) {
                    // Variable is of type INT but assigned value is FLOAT - log error
                    writeIntoErrorFile("Error at line " + std::to_string($expression.var_list.get_line_number()) + ": Type mismatch");
                    syntaxErrorCount++;
                    writeIntoparserLogfFile("Error at line " + std::to_string($expression.var_list.get_line_number()) + ": Type mismatch\n");
                    writeIntoparserLogfFile($expression.var_list.get_list_as_string() + "\n");
                } 
            }
                }
            //  else {
            //     // Variable not declared - log error
            //     writeIntoErrorFile("Error at line " + std::to_string($expression.var_list.get_line_number()) + ": Undeclared variable " + var_name);
            //     syntaxErrorCount++;
            //     writeIntoparserLogfFile($expression.var_list.get_list_as_string() + "\n");
            // }
        }
        // Add expression to variable list
        $var_list.add($expression.var_list.get_variables());
        $var_list.add($SEMICOLON->getText());
        $var_list.set_line_number($SEMICOLON->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($SEMICOLON->getLine()) + ": expression_statement : expression SEMICOLON\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

variable
	returns[str_list var_list]:
	ID { 
        SymbolInfo *lookup = st.LookUp($ID->getText());
        if(lookup == nullptr) {
            // Variable not declared - log error
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Undeclared variable " + $ID->getText());
            syntaxErrorCount++;
        }
        else if(isArrayMap[$ID->getText()]) {
            // Variable is an array but used as ID - log error
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": type mismatch " + $ID->getText() + " is an array");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": type mismatch " + $ID->getText() + " is an array\n");
        }
        $var_list.add($ID->getText());
        $var_list.set_line_number($ID->getLine());
        // Update total line count
        //totalLines = std::max(totalLines, $ID->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($ID->getLine()) + ": variable : ID\n");
        writeIntoparserLogfFile($ID->getText()+"\n");
      } # just_variable
	| ID LTHIRD expression RTHIRD { 
        SymbolInfo *lookup = st.LookUp($ID->getText());
        if(lookup == nullptr) {
            // Array not declared - log error
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Undeclared variable " + $ID->getText());
            syntaxErrorCount++;
        }
        // check if expression is integer
        std::string expr = $expression.var_list.get_list_as_string();
        // check if expression contains a decimal point
        if(expr.find('.') != std::string::npos) {
            // Expression is not an integer - log error
            writeIntoErrorFile("Error at line " + std::to_string($expression.var_list.get_line_number()) + ": Expression inside third brackets not an integer");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($expression.var_list.get_line_number()) + ": Expression inside third brackets not an integer\n");
        }
        // check isarray
        if(!isArrayMap[$ID->getText()]) {
            // Variable is not an array but used as array - log error
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": type mismatch " + $ID->getText() + " is not an array");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($ID->getLine()) + ": type mismatch " + $ID->getText() + " is not an array\n");
        }
        // Add variable to list
        $var_list.add($ID->getText());
        $var_list.add($LTHIRD->getText());
        $var_list.add($expression.var_list.get_variables());
        $var_list.add($RTHIRD->getText());
        $var_list.set_line_number($ID->getLine());
        // Update total line count
        //totalLines = std::max(totalLines, $ID->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($ID->getLine()) + ": variable : ID LTHIRD expression RTHIRD\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     } # array_variable;

expression
	returns[str_list var_list]:
	logic_expression { 
        $var_list.set_variables($logic_expression.var_list.get_variables());
        $var_list.set_line_number($logic_expression.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($logic_expression.var_list.get_line_number()) + ": expression : logic_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| variable ASSIGNOP logic_expression { 
        // check if the logic_expression is void return type function, using functionReturnTypeMap
        std::string lexpr = $logic_expression.var_list.get_list_as_string();
        size_t lparen_pos = lexpr.find('(');
        if(lparen_pos != std::string::npos) {
            std::string func_name = lexpr.substr(0, lparen_pos);
            // check if function exists in functionReturnTypeMap
            auto it = functionReturnTypeMap.find(func_name);
            if(it != functionReturnTypeMap.end()) {
                std::string return_type = it->second;
                if(return_type == "void") {
                    // Logic expression is a void return type function - log error
                    writeIntoErrorFile("Error at line " + std::to_string($logic_expression.var_list.get_line_number()) + ": Type mismatch - " + func_name + " is a void function");
                    syntaxErrorCount++;
                    writeIntoparserLogfFile("Error at line " + std::to_string($logic_expression.var_list.get_line_number()) + ": Type mismatch - " + func_name + "  is a void function\n");
                }
            }
        }

        // Add variable, ASSIGNOP and logic_expression to variable list
        $var_list.add($variable.var_list.get_variables());
        $var_list.add($ASSIGNOP->getText());
        $var_list.add($logic_expression.var_list.get_variables());
        $var_list.set_line_number($variable.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($variable.var_list.get_line_number()) + ": expression : variable ASSIGNOP logic_expression\n");
        
        // check for type mismatch by checking the type of variable and logic_expression
         std::string var_name = $variable.var_list.get_list_as_string();
        // find variable name , name is until LTHIRD
        size_t lthird_pos = var_name.find('[');
        if(lthird_pos != std::string::npos) {
            var_name = var_name.substr(0, lthird_pos);
        }
        SymbolInfo *lookup = st.LookUp(var_name);
        if(lookup == nullptr) {
            // Variable not declared - log error
            writeIntoErrorFile("Error at line " + std::to_string($variable.var_list.get_line_number()) + ": Undeclared variable " + var_name);
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($variable.var_list.get_line_number()) + ": Undeclared variable " + var_name + "\n");
        } else {
            std::string var_type = lookup->getType();
            // check if logic_expression is of the same type
            std::string logic_expr = $logic_expression.var_list.get_list_as_string();
            // check if logic_expression contains a decimal point and also check it only contains digits and decimal point
            bool is_valid = true;
            for(char c : logic_expr) {
                if(!isdigit(c) && c != '.') {
                    is_valid = false;
                    break;
                }
            }
            if(is_valid){
                if(logic_expr.find('.') != std::string::npos ) {
                // Logic expression is not an integer - log error
                if(var_type == "int") {
                    writeIntoErrorFile("Error at line " + std::to_string($logic_expression.var_list.get_line_number()) + ": Type mismatch\n ");
                    syntaxErrorCount++;
                    writeIntoparserLogfFile("Error at line " + std::to_string($logic_expression.var_list.get_line_number()) + ": Type mismatch\n ");
                }
            } 
            }
            
        }
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

logic_expression
	returns[str_list var_list]:
	rel_expression { 
        $var_list.set_variables($rel_expression.var_list.get_variables());
        $var_list.set_line_number($rel_expression.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($rel_expression.var_list.get_line_number()) + ": logic_expression : rel_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| re1 = rel_expression LOGICOP re2 = rel_expression { 
        $var_list.set_variables($re1.var_list.get_variables());
        $var_list.add($LOGICOP->getText());
        $var_list.add($re2.var_list.get_variables());
        $var_list.set_line_number($LOGICOP->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($LOGICOP->getLine()) + ": logic_expression : rel_expression LOGICOP rel_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

rel_expression
	returns[str_list var_list]:
	simple_expression {
        $var_list.set_variables($simple_expression.var_list.get_variables());
        $var_list.set_line_number($simple_expression.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($simple_expression.var_list.get_line_number()) + ": rel_expression : simple_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| se1 = simple_expression RELOP se2 = simple_expression { 
        $var_list.set_variables($se1.var_list.get_variables());
        $var_list.add($RELOP->getText());
        $var_list.add($se2.var_list.get_variables());
        $var_list.set_line_number($RELOP->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($RELOP->getLine()) + ": rel_expression : simple_expression RELOP simple_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

simple_expression
	returns[str_list var_list]:
	term { 
        $var_list.set_variables($term.var_list.get_variables());
        $var_list.set_line_number($term.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($term.var_list.get_line_number()) + ": simple_expression : term\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| se = simple_expression ADDOP term { 
        $var_list.set_variables($se.var_list.get_variables());
        $var_list.add($ADDOP->getText());
        $var_list.add($term.var_list.get_variables());
        $var_list.set_line_number($se.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($se.var_list.get_line_number()) + ": simple_expression : simple_expression ADDOP term\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

term
	returns[str_list var_list]:
	unary_expression { 
        $var_list.set_variables($unary_expression.var_list.get_variables());
        $var_list.set_line_number($unary_expression.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($unary_expression.var_list.get_line_number()) + ": term : unary_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| t = term MULOP unary_expression { 
        //check if modulus by 0,  i.e unary_expression is 0
        std::string unary_expr = $unary_expression.var_list.get_list_as_string();
        if(unary_expr == "0") {
            // Unary expression is 0 - log error
            writeIntoErrorFile("Error at line " + std::to_string($MULOP->getLine()) + ": Modulus by zero is not allowed");
            syntaxErrorCount++;
            writeIntoparserLogfFile("Error at line " + std::to_string($MULOP->getLine()) + ": Modulus by zero is not allowed\n");
        }

        // check if unary expression contain void return type function
        unary_expr = $unary_expression.var_list.get_list_as_string();
        // find function name and check if it is void ,  use return type map
        size_t func_pos = unary_expr.find('(');
        if(func_pos != std::string::npos) {
            std::string func_name = unary_expr.substr(0, func_pos);
            // check if function name is in return type map
            if(functionReturnTypeMap.find(func_name) != functionReturnTypeMap.end()) {
                std::string return_type = functionReturnTypeMap[func_name];
                if(return_type == "void") {
                    // Function returns void - log error
                    writeIntoErrorFile("Error at line " + std::to_string($MULOP->getLine()) + ": Function " + func_name + " returns void but used in expression");
                    syntaxErrorCount++;
                    writeIntoparserLogfFile("Error at line " + std::to_string($MULOP->getLine()) + ": Function " + func_name + " returns void but used in expression\n");
                }
            }
        }
            
        $var_list.set_variables($t.var_list.get_variables());
        $var_list.add($MULOP->getText());
        $var_list.add($unary_expression.var_list.get_variables());
        $var_list.set_line_number($MULOP->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($MULOP->getLine()) + ": term : term MULOP unary_expression\n");
        // check if unary_expression is integer
        if($unary_expression.var_list.get_list_as_string().find('.') != std::string::npos) {
            // Unary expression is not an integer - log error
            writeIntoErrorFile("Error at line " + std::to_string($MULOP->getLine()) + ": Non-integer operand on modulus operator\n");
            writeIntoparserLogfFile("Error at line " + std::to_string($MULOP->getLine()) + ": Non-integer operand on modulus operator\n");
            // Increment syntax error count
            syntaxErrorCount++;
        }
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

unary_expression
	returns[str_list var_list]:
	ADDOP unary_expression { 
        $var_list.add($ADDOP->getText());
        $var_list.add($unary_expression.var_list.get_variables());
        $var_list.set_line_number($ADDOP->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($ADDOP->getLine()) + ": unary_expression : ADDOP unary_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| NOT unary_expression { 
        $var_list.add($NOT->getText());
        $var_list.add($unary_expression.var_list.get_variables());
        $var_list.set_line_number($NOT->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($NOT->getLine()) + ": unary_expression : NOT unary_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| factor { 
        $var_list.set_variables($factor.var_list.get_variables());
        $var_list.set_line_number($factor.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($factor.var_list.get_line_number()) + ": unary_expression : factor\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
      };

factor
	returns[str_list var_list]:
	variable {
        std::cout<<"testing children of factor\n";
        if(auto *jv = dynamic_cast<C8086Parser::Just_variableContext*>(variable())){ 
            std::cout<<"******found just_variable\n";
            std::cout<<jv->getText()<<"\n";
         }
		$var_list.add($variable.var_list.get_variables());
		$var_list.set_line_number($variable.var_list.get_line_number());
		writeIntoparserLogfFile("Line " + std::to_string($variable.var_list.get_line_number()) + ": factor : variable\n");
		writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
	}
	| ID LPAREN argument_list RPAREN { 
        // Check if function exists in symbol table
        SymbolInfo *lookup = st.LookUp($ID->getText());
        if(lookup == nullptr && lookup->getName() != "printf") {
            // Function not declared - log error
            writeIntoErrorFile("Error at line " + std::to_string($ID->getLine()) + ": Undeclared function " + $ID->getText());
            syntaxErrorCount++;
        }
        // Even if there's an error, continue with the parsing
        $var_list.add($ID->getText());
        $var_list.add("(");
        $var_list.add($argument_list.var_list.get_variables());
        $var_list.add(")");
        $var_list.set_line_number($ID->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($ID->getLine()) + ": factor : ID LPAREN argument_list RPAREN\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
      
     }
	| LPAREN expression RPAREN { 
        $var_list.add("(");
        $var_list.add($expression.var_list.get_variables());
        $var_list.add(")");
        $var_list.set_line_number($LPAREN->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($LPAREN->getLine()) + ": factor : LPAREN expression RPAREN\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| CONST_INT { 
        $var_list.add($CONST_INT->getText());
        $var_list.set_line_number($CONST_INT->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($CONST_INT->getLine()) + ": factor : CONST_INT\n");
        writeIntoparserLogfFile($CONST_INT->getText()+"\n");
      }
	| CONST_FLOAT { 
        $var_list.add($CONST_FLOAT->getText());
        $var_list.set_line_number($CONST_FLOAT->getLine());
        writeIntoparserLogfFile("Line " + std::to_string($CONST_FLOAT->getLine()) + ": factor : CONST_FLOAT\n");
        writeIntoparserLogfFile($CONST_FLOAT->getText()+"\n");
      }
	| variable INCOP { 
        $var_list.add($variable.var_list.get_variables());
        $var_list.add($INCOP->getText());
        $var_list.set_line_number($variable.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($variable.var_list.get_line_number()) + ": factor : variable INCOP\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| variable DECOP { 
        $var_list.add($variable.var_list.get_variables());
        $var_list.add($DECOP->getText());
        $var_list.set_line_number($variable.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($variable.var_list.get_line_number()) + ": factor : variable DECOP\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

argument_list
	returns[str_list var_list]:
	arguments { 
        $var_list.set_variables($arguments.var_list.get_variables());
        $var_list.set_line_number($arguments.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($arguments.var_list.get_line_number()) + ": argument_list : arguments\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	|;

arguments
	returns[str_list var_list]:
	args = arguments COMMA logic_expression { 
        $var_list.set_variables($args.var_list.get_variables());
        $var_list.add($COMMA->getText());
        $var_list.add($logic_expression.var_list.get_variables());
        $var_list.set_line_number($args.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($args.var_list.get_line_number()) + ": arguments : arguments COMMA logic_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     }
	| logic_expression { 
        $var_list.add($logic_expression.var_list.get_variables());
        $var_list.set_line_number($logic_expression.var_list.get_line_number());
        writeIntoparserLogfFile("Line " + std::to_string($logic_expression.var_list.get_line_number()) + ": arguments : logic_expression\n");
        writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
     };

// do_while_statement
// 	returns[str_list var_list]:
// 	DO LCURL st = statements RCURL WHILE LPAREN expression RPAREN SEMICOLON {
//             $var_list.add($DO->getText());
//             $var_list.add($LCURL->getText());
//             $var_list.add($st.var_list.get_variables());
//             $var_list.add($RCURL->getText());
//             $var_list.add($WHILE->getText());
//             $var_list.add("(");
//             $var_list.add($expression.var_list.get_variables());
//             $var_list.add(")");
//             $var_list.add($SEMICOLON->getText());
//             $var_list.set_line_number($DO->getLine());
//             writeIntoparserLogfFile("Line " + std::to_string($DO->getLine()) + ": do_while_statement : DO LCURL statements RCURL WHILE LPAREN expression RPAREN SEMICOLON\n");
//             writeIntoparserLogfFile($var_list.get_list_as_string() + "\n");
//         };