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
    extern std::ofstream asmFile;
    extern std::ofstream tempAsm;

    extern int syntaxErrorCount;
}

@parser::members {
    
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

    string nextLabel() {
        return "L" + std::to_string(++labelCounter);
    }   

    int num_buckets = 11;

    SymbolTable st = SymbolTable(num_buckets); 
    int scope_flag = 0;
    int currentOffset = 0;
    int labelCounter = 0;
    int numParameters = 0;
    int inFunction = 0;
    int isVoidFunction = 0;
    std::vector<std::string> globalReturnLabels;
}

start: program;

program: program unit | unit;

unit: var_declaration | func_declaration | func_definition;

func_declaration:
	type_specifier ID LPAREN { st.EnterScope(); } parameter_list RPAREN { st.ExitScope(); }
		SEMICOLON
	| type_specifier ID LPAREN RPAREN SEMICOLON;

func_definition:
	type_specifier ID LPAREN { 
        SymbolInfo si  = SymbolInfo($ID->getText(), "ID" , false, false, true, false, 0);
        st.Insert(si);
        st.EnterScope(); scope_flag = 1; currentOffset = 0; numParameters = 0; inFunction = 1; isVoidFunction = ($type_specifier.type.get_variables()[0] == "void") ? 1 : 0;

        tempAsm << $ID->getText() << " PROC" << std::endl;
        if($ID->getText() == "main") {
            tempAsm << "\tMOV AX, @DATA" << std::endl;
            tempAsm << "\tMOV DS, AX" << std::endl;
        }
        tempAsm << "\tPUSH BP" << std::endl;
        tempAsm << "\tMOV BP, SP" << std::endl;

     } parameter_list {
        std::vector<std::string> params = $parameter_list.param_list.get_variables();
        //print the parameters
        for (const auto& param : params) {
            std::cout << "Parameter: fdfsdf " << param << std::endl;
        }
        globalReturnLabels.clear(); // clear the global return labels vector
        // reverse the order of parameters for correct stack alignment
        std::reverse(params.begin(), params.end());
        int offset = 4;
        for (const auto& param : params) {
            SymbolInfo si = SymbolInfo(param, "ID", false, false, false, true, offset);
            st.Insert(si);
            cout<< st.currentScopeId() << "currentScopeId" << std::endl;
            offset += 2;
            numParameters++;
            std::cout<< "Parameter: " << param << ", Offset: " << offset << std::endl;
        }
      } RPAREN compound_statement {
        if($ID->getText() == "main"){
            currentOffset -= 2; // main function has no parameters, so we can reduce the offset by 2
            // tempAsm << "\tPOP AX" << std::endl;
            for (const auto& label : globalReturnLabels) {
                tempAsm << label << ":" << std::endl;
            }
            tempAsm << "\tADD SP, " << currentOffset << std::endl;
            tempAsm << "\tPOP BP" << std::endl;
            tempAsm << "\tMOV AX, 4CH" << std::endl;
            tempAsm << "\tINT 21H" << std::endl;
            tempAsm << $ID->getText() << " ENDP" << std::endl;
        }
        else {
            for (const auto& label : globalReturnLabels) {
                tempAsm << label << ":" << std::endl;
            }
            globalReturnLabels.clear();
            tempAsm << "\tADD SP, " << currentOffset << std::endl;
            tempAsm << "\tPOP BP" << std::endl;
            tempAsm << "\tRET " << (numParameters * 2) << std::endl; // Return from function, cleaning up parameters
            tempAsm << $ID->getText() << " ENDP" << std::endl;
        }
      }
	| type_specifier ID LPAREN { 
        SymbolInfo si  = SymbolInfo($ID->getText(), "ID" , false, false,true, false, 0);
        st.Insert(si);
        st.EnterScope(); scope_flag = 1; currentOffset = 0; numParameters = 0; inFunction = 1; isVoidFunction = ($type_specifier.type.get_variables()[0] == "void") ? 1 : 0;

        tempAsm << $ID->getText() << " PROC" << std::endl;
        if($ID->getText() == "main") {
            tempAsm << "\tMOV AX, @DATA" << std::endl;
            tempAsm << "\tMOV DS, AX" << std::endl;
        }
        tempAsm << "\tPUSH BP" << std::endl;
        tempAsm << "\tMOV BP, SP" << std::endl;
     } RPAREN compound_statement {
        if($ID->getText() == "main"){
            currentOffset -= 2; // main function has no parameters, so we can reduce the offset by 2
            // tempAsm << "\tPOP AX" << std::endl;
            for (const auto& label : globalReturnLabels) {
                tempAsm << label << ":" << std::endl;
            }
            globalReturnLabels.clear();
            tempAsm << "\tADD SP, " << currentOffset << std::endl;
            tempAsm << "\tPOP BP" << std::endl;
            tempAsm << "\tMOV AX, 4CH" << std::endl;
            tempAsm << "\tINT 21H" << std::endl;
            tempAsm << $ID->getText() << " ENDP" << std::endl;
        }
        else {
            for (const auto& label : globalReturnLabels) {
                tempAsm << label << ":" << std::endl;
            }
            // make the global return label vector empty
            globalReturnLabels.clear();
            tempAsm << "\tADD SP, " << currentOffset << std::endl;
            tempAsm << "\tPOP BP" << std::endl;
            tempAsm << "\tRET " << std::endl;
            tempAsm << $ID->getText() << " ENDP" << std::endl;
        }
      };

parameter_list
	returns[ str_list param_list ]:
	pl = parameter_list COMMA type_specifier ID {
        std::vector<std::string> params = $pl.param_list.get_variables();
        $param_list.set_variables(params);
        $param_list.add($ID->getText());
        cout<<"Parameter "<<$ID->getText() << std::endl;
     }
	| parameter_list COMMA type_specifier
	| type_specifier ID {
        $param_list.add($ID->getText());
        cout<<"Parameter: " << $ID->getText() << std::endl;
     }
	| type_specifier;

compound_statement:
	LCURL { 
            if(scope_flag == 0) st.EnterScope(); 
            scope_flag = 0;
        } statements RCURL { 
            // cout<< "In Function: " << inFunction << std::endl;
            // if(inFunction == 1){
            //     tempAsm << "\tPOP AX" << std::endl;
            //     inFunction = 0;
            //  }
            st.ExitScope();
        }
	| LCURL RCURL;

var_declaration:
	type_specifier declaration_list SEMICOLON
	| type_specifier declaration_list_err SEMICOLON;

declaration_list_err:;

type_specifier
	returns[str_list type]:
	INT {
    $type.add("INT");
 }
	| FLOAT {
    $type.add("FLOAT");
 }
	| VOID {
    $type.add("VOID");
 };

declaration_list:
	declaration_list COMMA ID { 

        int currentScope = st.currentScopeId();
        bool isGlobal  = false;
        if(currentScope == 1){ isGlobal = true; }
        SymbolInfo si = SymbolInfo($ID->getText(), "ID", false, isGlobal, false,false, currentOffset+2);
        if(!isGlobal){
            currentOffset += 2;
            tempAsm <<"\tSUB SP, 2" << std::endl;
        }
        else {
            asmFile << "\t" << $ID->getText() << " DW 1 DUP (0000H)" << std::endl;
        }
        st.Insert(si);

    }
	| declaration_list COMMA ID LTHIRD CONST_INT RTHIRD { 
        
        int currentScope = st.currentScopeId();
        bool isGlobal  = false;
        if(currentScope == 1){ isGlobal = true; }
        bool isArray = true;
        int size = std::stoi($CONST_INT->getText());
        SymbolInfo si = SymbolInfo($ID->getText(),"ID",isArray, isGlobal, false, false, currentOffset+size);
        if(!isGlobal){
            currentOffset += size;
            tempAsm << "\tSUB SP, "<< size*2 << std::endl;
         }
        else{
            asmFile << "\t" << $ID->getText() << " DW " << size << " DUP (000H)" << std::endl;
         }
        st.Insert(si);
    }
	| ID { 
        int currentScope = st.currentScopeId();
        bool isGlobal  = false;
        if(currentScope == 1){ isGlobal = true; }
        SymbolInfo si = SymbolInfo($ID->getText(), "ID", false, isGlobal, false, false, currentOffset+2);
        if(!isGlobal){
            currentOffset += 2;
            tempAsm <<"\tSUB SP, 2" << std::endl;
        }
        else {
            asmFile << "\t" << $ID->getText() << " DW 1 DUP (0000H)" << std::endl;
        }
        st.Insert(si);
     }
	| ID LTHIRD CONST_INT RTHIRD {
        cout<< "found " << $ID->getText() <<" array" << std::endl;
        int currentScope = st.currentScopeId();
        bool isGlobal  = false;
        if(currentScope == 1){ isGlobal = true; }
        bool isArray = true;
        int size = std::stoi($CONST_INT->getText());
        SymbolInfo si = SymbolInfo($ID->getText(),"ID",isArray, isGlobal, false, false, currentOffset+size);
        if(!isGlobal){
            currentOffset += size;
            tempAsm << "\tSUB SP, "<< size*2 << std::endl;
        }
        else{
            asmFile << "\t" << $ID->getText() << " DW " << size << " DUP (000H)" << std::endl;
        }
        st.Insert(si);
     };

statements:
	statement
	| statements { tempAsm << nextLabel() << ":" << std::endl; } statement;

statement:
	var_declaration
	| expression_statement
	| compound_statement
	| FOR LPAREN {
            std::string condLabel = nextLabel();
            std::string incLabel = nextLabel();
            std::string endLabel = nextLabel();
            std::string trueLabel = nextLabel();
        } expression_statement {
            tempAsm << condLabel << ":" << std::endl;
        } expression_statement {
            tempAsm << "\tPUSH AX" << std::endl; // Push the condition result
            tempAsm << "\tPOP AX" << std::endl;
            tempAsm << "\tCMP AX, 0" << std::endl;
            tempAsm << "\tJE " << endLabel << std::endl;
            tempAsm << "\tJMP " << trueLabel << std::endl;
            tempAsm << incLabel << ":" << std::endl;
            
        } expression {
            tempAsm << "\tPOP AX" << std::endl;
            tempAsm << "\tJMP " << condLabel << std::endl;
         } RPAREN {
            tempAsm << trueLabel << ":" << std::endl;
         } statement {
            tempAsm << "\tJMP " << incLabel << std::endl;
            tempAsm << endLabel << ":" << std::endl;
        }
	| IF LPAREN expression RPAREN {
        std::string elseLabel = nextLabel();
        tempAsm << "\tPOP AX" << std::endl;
        tempAsm << "\tCMP AX, 0" << std::endl;
        tempAsm << "\tJE " << elseLabel << std::endl;
     } statement {
        tempAsm << elseLabel << ":" << std::endl;
     }
	| IF LPAREN expression RPAREN {
        std::string elseLabel = nextLabel();
        std::string endLabel = nextLabel();
        tempAsm << "\tPOP AX" << std::endl;
        tempAsm << "\tCMP AX, 0" << std::endl;
        tempAsm << "\tJE " << elseLabel << std::endl;
     } statement {
        tempAsm << "\tJMP " << endLabel << std::endl;
        tempAsm << elseLabel << ":" << std::endl;
     } ELSE statement {
        tempAsm << endLabel << ":" << std::endl;
     }
	| WHILE LPAREN {
        std::string condLabel = nextLabel();
        std::string endLabel = nextLabel();
        tempAsm << condLabel << ":" << std::endl;
     } expression {
        tempAsm << "\tPOP AX" << std::endl;
        tempAsm << "\tCMP AX, 0" << std::endl;
        tempAsm << "\tJE " << endLabel << std::endl;
      } RPAREN statement {
        tempAsm << "\tJMP " << condLabel << std::endl;
        tempAsm << endLabel << ":" << std::endl;
       }
	| PRINTLN LPAREN ID RPAREN SEMICOLON {
        SymbolInfo* si = st.LookUp($ID->getText());
        if(si == nullptr) {
            writeIntoErrorFile("Error: Variable " + $ID->getText() + " not declared.");
            syntaxErrorCount++;
        } else {
            if (si->getIsParameter()) {
                tempAsm << "\tMOV AX, [BP+" << si->getOffset() << "]" << std::endl;
            } else if(si->getIsGlobal()) {
                tempAsm << "\tMOV AX, " << si->getName() << std::endl;
            } else {
                tempAsm << "\tMOV AX, [BP-" << si->getOffset() << "]" << std::endl;
            }
            tempAsm << "\tCALL print_output" << std::endl;
            tempAsm << "\tCALL new_line" << std::endl;
        }
     }
	| RETURN expression SEMICOLON {
        globalReturnLabels.push_back(nextLabel());
        tempAsm << "\tPOP AX" << std::endl;
        tempAsm << "\tJMP " << globalReturnLabels.back() << std::endl;
     }
	| DO LCURL statements RCURL WHILE LPAREN expression RPAREN SEMICOLON
	| BREAK SEMICOLON
	| CONTINUE SEMICOLON;

expression_statement:
	SEMICOLON
	| expression SEMICOLON {
        tempAsm << "\tPOP AX" << std::endl;
 };

variable
	returns[ str_list var_list ]:
	ID { 
        $var_list.add($ID->getText());
     }
	| ID LTHIRD expression RTHIRD { 
        $var_list.add($ID->getText());
     };

expression:
	logic_expression
	| variable ASSIGNOP logic_expression { 
        tempAsm << "\tPOP AX" << std::endl; // result of logic_expression
        SymbolInfo* si = st.LookUp($variable.var_list.get_variables()[0]);
        if(si->getIsArray()) {
            if(si->getIsGlobal()){
                // tempAsm << "\tPOP AX" << std::endl; // logic_expression result
                tempAsm << "\tPOP BX" << std::endl; // index 
                tempAsm << "\tSHL BX, 1" << std::endl; // multiply index by 2 for word size
                tempAsm << "\tMOV " << si->getName() << "[BX], AX" << std::endl;
                // tempAsm << "\tPUSH AX" << std::endl; // push the value back to stack
            } else {

                tempAsm << "\tPOP BX" << std::endl; // index
                tempAsm << "\tSHL BX, 1" << std::endl; // multiply index by 2 for word size
                tempAsm << "\tMOV DX, " << si->getOffset() << std::endl; // offset
                tempAsm << "\tSUB BX, DX" << std::endl; // calculate the address
                tempAsm << "\tMOV SI, BX" << std::endl; // store the address in SI
                tempAsm << "\tMOV [BP+SI], AX" << std::endl;

             }
        } else if(si->getIsParameter()) {

            tempAsm << "\tMOV [BP+" << si->getOffset() << "], AX" << std::endl;
        } else if(si->getIsGlobal()) {
            tempAsm << "\tMOV " << si->getName() << ", AX" << std::endl;
        } else {

            tempAsm << "\tMOV [BP-" << si->getOffset() << "], AX" << std::endl;
        }
        tempAsm << "\tPUSH AX" << std::endl;
     };

// logic_expression:
// 	rel_expression
// 	| rel_expression LOGICOP rel_expression {
//         tempAsm << "\tPOP BX" << std::endl;
//         tempAsm << "\tPOP AX" << std::endl;
//         std::string trueLabel = nextLabel();
//         std::string endLabel = nextLabel();
//         if($LOGICOP->getText() == "&&") {
//             tempAsm << "\tCMP AX, 0" << std::endl;
//             tempAsm << "\tJE " << endLabel << std::endl;
//             tempAsm << "\tCMP BX, 0" << std::endl;
//             tempAsm << "\tJE " << endLabel << std::endl;
//             tempAsm << "\tPUSH 1" << std::endl; // true
//             tempAsm << "\tJMP " << trueLabel << std::endl;
//             tempAsm << endLabel << ":" << std::endl;
//             tempAsm << "\tPUSH 0" << std::endl; // false
//             tempAsm << trueLabel << ":" << std::endl;
//         } else if($LOGICOP->getText() == "||") {
//             tempAsm << "\tCMP AX, 0" << std::endl;
//             tempAsm << "\tJNE " << trueLabel << std::endl;    
//             tempAsm << "\tCMP BX, 0" << std::endl;
//             tempAsm << "\tJNE " << trueLabel << std::endl;
//             tempAsm << "\tPUSH 0" << std::endl; // false
//             tempAsm << "\tJMP " << endLabel << std::endl;
//             tempAsm << trueLabel << ":" << std::endl;
//             tempAsm << "\tPUSH 1" << std::endl; // true
//             tempAsm << endLabel << ":" << std::endl;
//         }
//      };

logic_expression:
    rel_expression
    | logic_expression LOGICOP {
        // Generate labels for short-circuiting
        std::string trueLabel = nextLabel();
        std::string falseLabel = nextLabel();
        std::string endLabel = nextLabel();
        
        if($LOGICOP->getText() == "&&") {
            tempAsm << "\tPOP AX" << std::endl;
            tempAsm << "\tCMP AX, 0" << std::endl;
            tempAsm << "\tJE " << falseLabel << std::endl;  // Short-circuit to false

        } else if($LOGICOP->getText() == "||") {
            tempAsm << "\tPOP AX" << std::endl;
            tempAsm << "\tCMP AX, 0" << std::endl;
            tempAsm << "\tJNE " << trueLabel << std::endl; // Short-circuit to true
        }
        
    } rel_expression {

        if($LOGICOP->getText() == "&&") {
            tempAsm << "\tJMP " << endLabel << std::endl;
            tempAsm << falseLabel << ":" << std::endl;
            tempAsm << "\tPUSH 0" << std::endl; // Result is false
            tempAsm << endLabel << ":" << std::endl;
        } else if($LOGICOP->getText() == "||") { 
            tempAsm << "\tJMP " << endLabel << std::endl;
            tempAsm << trueLabel << ":" << std::endl;
            tempAsm << "\tPUSH 1" << std::endl; // Result is true
            tempAsm << endLabel << ":" << std::endl;
        }
    };

rel_expression:
	simple_expression
	| simple_expression RELOP simple_expression {
        tempAsm << "\tPOP BX" << std::endl;
        tempAsm << "\tPOP AX" << std::endl;
        std::string trueLabel = nextLabel();
        std::string endLabel = nextLabel();
        if($RELOP->getText() == "<") {
            tempAsm << "\tCMP AX, BX" << std::endl;
            tempAsm << "\tJL " << trueLabel << std::endl;
        } else if($RELOP->getText() == "<=") {
            tempAsm << "\tCMP AX, BX" << std::endl;
            tempAsm << "\tJLE " << trueLabel << std::endl;
        } else if($RELOP->getText() == ">") {
            tempAsm << "\tCMP AX, BX" << std::endl;
            tempAsm << "\tJG " << trueLabel << std::endl;
        } else if($RELOP->getText() == ">=") {
            tempAsm << "\tCMP AX, BX" << std::endl;
            tempAsm << "\tJGE " << trueLabel << std::endl;
        } else if($RELOP->getText() == "==") {
            tempAsm << "\tCMP AX, BX" << std::endl;
            tempAsm << "\tJE " << trueLabel << std::endl;
        } else if($RELOP->getText() == "!=") {
            tempAsm << "\tCMP AX, BX" << std::endl;
            tempAsm << "\tJNE " << trueLabel << std::endl;
        }
        tempAsm << "\tPUSH 0" << std::endl; // false
        tempAsm << "\tJMP " << endLabel << std::endl;
        tempAsm << trueLabel << ":" << std::endl;
        tempAsm << "\tPUSH 1" << std::endl; // true
        tempAsm << endLabel << ":" << std::endl;
     };

simple_expression:
	term
	| simple_expression ADDOP term {
        tempAsm << "\tPOP BX" << std::endl;
        tempAsm << "\tPOP AX" << std::endl;
        if($ADDOP->getText() == "+") {
            tempAsm << "\tADD AX, BX" << std::endl;
        } else if($ADDOP->getText() == "-") {
            tempAsm << "\tSUB AX, BX" << std::endl;
        }
        tempAsm << "\tPUSH AX" << std::endl;
     };

term:
	unary_expression
	| term MULOP unary_expression {
        tempAsm << "\tPOP BX" << std::endl;
        tempAsm << "\tPOP AX" << std::endl;
        if($MULOP->getText() == "*") {
            tempAsm << "\tMUL BX" << std::endl;
        } else if($MULOP->getText() == "/") {
            // tempAsm << "\tXCHG AX, BX" << std::endl;
            tempAsm << "\tDIV BX" << std::endl;
        } else if($MULOP->getText() == "%") {
            // tempAsm << "\tXCHG AX, BX" << std::endl;
            tempAsm << "\tMOV DX, 0" << std::endl; // Clear DX before division
            tempAsm << "\tDIV BX" << std::endl;
            tempAsm << "\tMOV AX, DX" << std::endl; // Remainder in DX
        }
        tempAsm << "\tPUSH AX" << std::endl;
     };

unary_expression:
	ADDOP unary_expression {
        tempAsm << "\tPOP AX" << std::endl;
        if($ADDOP->getText() == "+") {
            tempAsm << "\tPUSH AX" << std::endl; // No change for +
        } else if($ADDOP->getText() == "-") {
            tempAsm << "\tNEG AX" << std::endl; // Negate for -
            tempAsm << "\tPUSH AX" << std::endl;
        }
     }
	| NOT unary_expression {
        tempAsm << "\tPOP AX" << std::endl;
        tempAsm << "\tNOT AX" << std::endl;
        tempAsm << "\tPUSH AX" << std::endl;
     }
	| factor;

factor:
	variable {
        SymbolInfo* si = st.LookUp($variable.var_list.get_variables()[0]);

        if(si->getIsArray()) {
            // For arrays, we need to handle the index
            tempAsm << "\tPOP BX" << std::endl; // index
            tempAsm << "\tSHL BX, 1" << std::endl; // multiply index by 2 for word size
            if(si->getIsGlobal()){
                tempAsm << "\tMOV AX, " << si->getName() << "[BX]" << std::endl;
            } else {
                tempAsm << "\tMOV DX, " << si->getOffset() << std::endl; // offset
                tempAsm << "\tSUB BX, DX" << std::endl; // calculate the address
                tempAsm << "\tMOV SI, BX" << std::endl; // store the address in SI
                tempAsm << "\tMOV AX, [BP+SI]" << std::endl;
            }
        } else if(si->getIsParameter()) {
            tempAsm << "\tMOV AX, [BP+" << si->getOffset() << "]" << std::endl;
        } else if(si->getIsGlobal()) {
            tempAsm << "\tMOV AX, " << si->getName() << std::endl;
        }  else {
            tempAsm << "\tMOV AX, [BP-" << si->getOffset() << "]" << std::endl;
        }
        tempAsm << "\tPUSH AX" << std::endl;
     }
	| ID LPAREN argument_list RPAREN {
        tempAsm << "\tCALL " << $ID->getText() << std::endl;
        tempAsm << "\tPUSH AX" << std::endl;

     }
	| LPAREN expression RPAREN
	| CONST_INT { 
        tempAsm << "\tPUSH " << $CONST_INT->getText() << std::endl;
     }
	| CONST_FLOAT
	| variable INCOP {
        // Post-increment: push original value, then increment variable
        SymbolInfo* si = st.LookUp($variable.var_list.get_variables()[0]);

        if(si->getIsArray()) {
            // For arrays, we need to handle the index
            tempAsm << "\tPOP BX" << std::endl; // index
            tempAsm << "\tSHL BX, 1" << std::endl; // multiply index by 2 for word size
            if(si->getIsGlobal()){
                tempAsm << "\tMOV AX, " << si->getName() << "[BX]" << std::endl;
                tempAsm << "\tPUSH AX" << std::endl;
                tempAsm << "\tINC AX" << std::endl;
                tempAsm << "\tMOV " << si->getName() << "[BX], AX" << std::endl;
            } else {
                tempAsm << "\tMOV DX, " << si->getOffset() << std::endl; // offset
                tempAsm << "\tSUB BX, DX" << std::endl; // calculate the address
                tempAsm << "\tMOV SI, BX" << std::endl; // store the address in SI
                tempAsm << "\tMOV AX, [BP+SI]" << std::endl;
                tempAsm << "\tPUSH AX" << std::endl;
                tempAsm << "\tINC AX" << std::endl;
                tempAsm << "\tMOV [BP+SI], AX" << std::endl;
            }
         } else if(si->getIsParameter()) {
            tempAsm << "\tMOV AX, [BP+" << si->getOffset() << "]" << std::endl;
            tempAsm << "\tPUSH AX" << std::endl;
            tempAsm << "\tINC AX" << std::endl;
            tempAsm << "\tMOV [BP+" << si->getOffset() << "], AX" << std::endl;
        }else if(si->getIsGlobal()) {
            tempAsm << "\tMOV AX, " << si->getName() << std::endl;
            tempAsm << "\tPUSH AX" << std::endl;
            tempAsm << "\tINC AX" << std::endl;
            tempAsm << "\tMOV " << si->getName() << ", AX" << std::endl;
        } else {
            tempAsm << "\tMOV AX, [BP-" << si->getOffset() << "]" << std::endl;
            tempAsm << "\tPUSH AX" << std::endl;
            tempAsm << "\tINC AX" << std::endl;
            tempAsm << "\tMOV [BP-" << si->getOffset() << "], AX" << std::endl;
        }
     }
	| variable DECOP {
        // Post-decrement: push original value, then decrement variable
        SymbolInfo* si = st.LookUp($variable.var_list.get_variables()[0]);
        if(si->getIsArray()) {
            tempAsm << "\tPOP BX" << std::endl; // index
            tempAsm << "\tSHL BX, 1" << std::endl; // multiply index by 2 for word size
            if(si->getIsGlobal()){
                tempAsm << "\tMOV AX, " << si->getName() << "[BX]" << std::endl;
                tempAsm << "\tPUSH AX" << std::endl;
                tempAsm << "\tDEC AX" << std::endl;
                tempAsm << "\tMOV " << si->getName() << "[BX], AX" << std::endl;
            } else {
                tempAsm << "\tMOV DX, " << si->getOffset() << std::endl; // offset
                tempAsm << "\tSUB BX, DX" << std::endl; // calculate the address
                tempAsm << "\tMOV SI, BX" << std::endl; // store the address in SI
                tempAsm << "\tMOV AX, [BP+SI]" << std::endl;
                tempAsm << "\tPUSH AX" << std::endl;
                tempAsm << "\tDEC AX" << std::endl;
                tempAsm << "\tMOV [BP+SI], AX" << std::endl;
            }
        } else if(si->getIsParameter()) {
            tempAsm << "\tMOV AX, [BP+" << si->getOffset() << "]" << std::endl;
            tempAsm << "\tPUSH AX" << std::endl;
            tempAsm << "\tDEC AX" << std::endl;
            tempAsm << "\tMOV [BP+" << si->getOffset() << "], AX" << std::endl;
        } else if(si->getIsGlobal()) {
            tempAsm << "\tMOV AX, " << si->getName() << std::endl;
            tempAsm << "\tPUSH AX" << std::endl;
            tempAsm << "\tDEC AX" << std::endl;
            tempAsm << "\tMOV " << si->getName() << ", AX" << std::endl;
        } else {
            tempAsm << "\tMOV AX, [BP-" << si->getOffset() << "]" << std::endl;
            tempAsm << "\tPUSH AX" << std::endl;
            tempAsm << "\tDEC AX" << std::endl;
            tempAsm << "\tMOV [BP-" << si->getOffset() << "], AX" << std::endl;
        }
     };

argument_list: arguments |;

arguments: arguments COMMA logic_expression | logic_expression;