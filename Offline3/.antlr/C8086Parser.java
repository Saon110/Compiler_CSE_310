// Generated from /media/saon/New Volume/3-1/CSE310/Offline3/C8086Parser.g4 by ANTLR 4.13.1

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class C8086Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LINE_COMMENT=1, BLOCK_COMMENT=2, STRING=3, WS=4, NEWLINE=5, DO=6, BREAK=7, 
		CONTINUE=8, IF=9, ELSE=10, FOR=11, WHILE=12, PRINTLN=13, RETURN=14, INT=15, 
		FLOAT=16, VOID=17, LPAREN=18, RPAREN=19, LCURL=20, RCURL=21, LTHIRD=22, 
		RTHIRD=23, SEMICOLON=24, COMMA=25, ADDOP=26, SUBOP=27, MULOP=28, INCOP=29, 
		DECOP=30, NOT=31, RELOP=32, LOGICOP=33, ASSIGNOP=34, ID=35, CONST_INT=36, 
		CONST_FLOAT=37;
	public static final int
		RULE_start = 0, RULE_program = 1, RULE_unit = 2, RULE_func_declaration = 3, 
		RULE_func_definition = 4, RULE_parameter_list = 5, RULE_compound_statement = 6, 
		RULE_var_declaration = 7, RULE_declaration_list_err = 8, RULE_type_specifier = 9, 
		RULE_declaration_list = 10, RULE_statements = 11, RULE_statement = 12, 
		RULE_expression_statement = 13, RULE_variable = 14, RULE_expression = 15, 
		RULE_logic_expression = 16, RULE_rel_expression = 17, RULE_simple_expression = 18, 
		RULE_term = 19, RULE_unary_expression = 20, RULE_factor = 21, RULE_argument_list = 22, 
		RULE_arguments = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "program", "unit", "func_declaration", "func_definition", "parameter_list", 
			"compound_statement", "var_declaration", "declaration_list_err", "type_specifier", 
			"declaration_list", "statements", "statement", "expression_statement", 
			"variable", "expression", "logic_expression", "rel_expression", "simple_expression", 
			"term", "unary_expression", "factor", "argument_list", "arguments"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'do'", "'break'", "'continue'", 
			"'if'", "'else'", "'for'", "'while'", "'println'", "'return'", "'int'", 
			"'float'", "'void'", "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", 
			"','", null, null, null, "'++'", "'--'", "'!'", null, null, "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LINE_COMMENT", "BLOCK_COMMENT", "STRING", "WS", "NEWLINE", "DO", 
			"BREAK", "CONTINUE", "IF", "ELSE", "FOR", "WHILE", "PRINTLN", "RETURN", 
			"INT", "FLOAT", "VOID", "LPAREN", "RPAREN", "LCURL", "RCURL", "LTHIRD", 
			"RTHIRD", "SEMICOLON", "COMMA", "ADDOP", "SUBOP", "MULOP", "INCOP", "DECOP", 
			"NOT", "RELOP", "LOGICOP", "ASSIGNOP", "ID", "CONST_INT", "CONST_FLOAT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "C8086Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public C8086Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			program(0);

			        writeIntoparserLogfFile("Parsing completed successfully with " + std::to_string(syntaxErrorCount) + " syntax errors.");
			        writeIntoparserLogfFile("Total lines in the program: " + std::to_string(totalLines));
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public str_list var_list;
		public ProgramContext p;
		public UnitContext unit;
		public UnitContext u;
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		return program(0);
	}

	private ProgramContext program(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ProgramContext _localctx = new ProgramContext(_ctx, _parentState);
		ProgramContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_program, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(52);
			((ProgramContext)_localctx).unit = unit();

			        _localctx.var_list.set_variables(((ProgramContext)_localctx).unit.var_list.get_variables());
			        _localctx.var_list.set_line_number(((ProgramContext)_localctx).unit.var_list.get_line_number());
			        writeIntoparserLogfFile(
			            "\nLine " + std::to_string(((ProgramContext)_localctx).unit.var_list.get_line_number()) + ": " +
			            "program : unit\n\n" +
			            _localctx.var_list.get_list_as_string() + "\n\n"
			        );
			    
			}
			_ctx.stop = _input.LT(-1);
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ProgramContext(_parentctx, _parentState);
					_localctx.p = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_program);
					setState(55);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(56);
					((ProgramContext)_localctx).u = ((ProgramContext)_localctx).unit = unit();
					 
					                  _localctx.var_list.set_variables(((ProgramContext)_localctx).p.var_list.get_variables());
					                  _localctx.var_list.add("\n");
					                  _localctx.var_list.add(((ProgramContext)_localctx).u.var_list.get_variables());
					                  _localctx.var_list.set_line_number(((ProgramContext)_localctx).u.var_list.get_line_number());
					                  writeIntoparserLogfFile(
					                      "\nLine " + std::to_string(((ProgramContext)_localctx).p.var_list.get_line_number()) + ": " +
					                      "program : program unit\n\n" +
					                      _localctx.var_list.get_list_as_string() + "\n\n"
					                  );
					              
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnitContext extends ParserRuleContext {
		public str_list var_list;
		public Var_declarationContext var_declaration;
		public Func_declarationContext func_declaration;
		public Func_definitionContext func_definition;
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public Func_declarationContext func_declaration() {
			return getRuleContext(Func_declarationContext.class,0);
		}
		public Func_definitionContext func_definition() {
			return getRuleContext(Func_definitionContext.class,0);
		}
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_unit);
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				((UnitContext)_localctx).var_declaration = var_declaration();

				        _localctx.var_list.set_variables(((UnitContext)_localctx).var_declaration.var_list.get_variables());
				        _localctx.var_list.set_line_number(((UnitContext)_localctx).var_declaration.var_list.get_line_number());
				        // Update total line count
				        //totalLines = std::max(totalLines, ((UnitContext)_localctx).var_declaration.var_list.get_line_number());
				        writeIntoparserLogfFile(
				            "Line " + std::to_string(((UnitContext)_localctx).var_declaration.var_list.get_line_number()) + ": " +
				            "unit : var_declaration\n\n" +
				            _localctx.var_list.get_list_as_string() + "\n"
				        );
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				((UnitContext)_localctx).func_declaration = func_declaration();
				 
				        _localctx.var_list.set_variables(((UnitContext)_localctx).func_declaration.var_list.get_variables());
				        _localctx.var_list.set_line_number(((UnitContext)_localctx).func_declaration.var_list.get_line_number());
				        // Update total line count
				        //totalLines = std::max(totalLines, ((UnitContext)_localctx).func_declaration.var_list.get_line_number());
				        writeIntoparserLogfFile(
				            "Line " + std::to_string(((UnitContext)_localctx).func_declaration.var_list.get_line_number()) + ": " +
				            "unit : func_declaration\n\n" +
				            _localctx.var_list.get_list_as_string() + "\n"
				        );
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				((UnitContext)_localctx).func_definition = func_definition();
				 
				        _localctx.var_list.set_variables(((UnitContext)_localctx).func_definition.var_list.get_variables());
				        _localctx.var_list.set_line_number(((UnitContext)_localctx).func_definition.var_list.get_line_number());
				        // Update total line count
				        //totalLines = std::max(totalLines, ((UnitContext)_localctx).func_definition.var_list.get_line_number());
				        writeIntoparserLogfFile(
				            "Line " + std::to_string(((UnitContext)_localctx).func_definition.var_list.get_line_number()) + ": " +
				            "unit : func_definition\n\n" +
				            _localctx.var_list.get_list_as_string() + "\n"
				        );
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_declarationContext extends ParserRuleContext {
		public str_list var_list;
		public Type_specifierContext type_specifier;
		public Token ID;
		public Parameter_listContext parameter_list;
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(C8086Parser.LPAREN, 0); }
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(C8086Parser.RPAREN, 0); }
		public TerminalNode SEMICOLON() { return getToken(C8086Parser.SEMICOLON, 0); }
		public Func_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_declaration; }
	}

	public final Func_declarationContext func_declaration() throws RecognitionException {
		Func_declarationContext _localctx = new Func_declarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_func_declaration);
		try {
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				((Func_declarationContext)_localctx).type_specifier = type_specifier();
				setState(76);
				((Func_declarationContext)_localctx).ID = match(ID);
				setState(77);
				match(LPAREN);
				 st.EnterScope(); 
				setState(79);
				((Func_declarationContext)_localctx).parameter_list = parameter_list(0);
				setState(80);
				match(RPAREN);
				 st.PrintAllScopeTable(parserLogFile); st.ExitScope(); 
						
				setState(82);
				match(SEMICOLON);

				            // if the function is not in return type map, insert it
				            if(functionReturnTypeMap.find(((Func_declarationContext)_localctx).ID->getText()) == functionReturnTypeMap.end()) {
				                functionReturnTypeMap[((Func_declarationContext)_localctx).ID->getText()] = ((Func_declarationContext)_localctx).type_specifier.var_list.get_list_as_string();
				                // calculate argument count
				                int argCount = 0;
				                // split the parameter_list by commas
				                std::string paramList = ((Func_declarationContext)_localctx).parameter_list.var_list.get_list_as_string();
				                std::stringstream ss(paramList);
				                std::string token;
				                while (std::getline(ss, token, ',')) {
				                    argCount++;
				                }
				                functionArgumentCountMap[((Func_declarationContext)_localctx).ID->getText()] = argCount;
				            }
				            _localctx.var_list.add(((Func_declarationContext)_localctx).type_specifier.var_list.get_variables());
				            _localctx.var_list.add(" ");
				            _localctx.var_list.add(((Func_declarationContext)_localctx).ID->getText());
				            _localctx.var_list.add("(");
				            _localctx.var_list.add(((Func_declarationContext)_localctx).parameter_list.var_list.get_variables());
				            _localctx.var_list.add(")");
				            _localctx.var_list.add(";");
				            _localctx.var_list.set_line_number(((Func_declarationContext)_localctx).ID->getLine());
				            writeIntoparserLogfFile(
				                "Line " + std::to_string(((Func_declarationContext)_localctx).ID->getLine()) + ": " +
				                "func_declaration : type_specifier ID LPAREN parameter_list RPAREN SEMICOLON\n\n" +
				                _localctx.var_list.get_list_as_string() + "\n"
				            );
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(85);
				((Func_declarationContext)_localctx).type_specifier = type_specifier();
				setState(86);
				((Func_declarationContext)_localctx).ID = match(ID);
				setState(87);
				match(LPAREN);
				setState(88);
				match(RPAREN);
				setState(89);
				match(SEMICOLON);

				            // if the function is not in return type map, insert it
				            if(functionReturnTypeMap.find(((Func_declarationContext)_localctx).ID->getText()) == functionReturnTypeMap.end()) {
				                functionReturnTypeMap[((Func_declarationContext)_localctx).ID->getText()] = ((Func_declarationContext)_localctx).type_specifier.var_list.get_list_as_string();
				                // calculate argument count
				                int argCount = 0;
				                // split the parameter_list by commas
				                std::string paramList = "";
				                std::stringstream ss(paramList);
				                std::string token;
				                while (std::getline(ss, token, ',')) {
				                    argCount++;
				                }
				                functionArgumentCountMap[((Func_declarationContext)_localctx).ID->getText()] = argCount;
				            }
				            _localctx.var_list.add(((Func_declarationContext)_localctx).type_specifier.var_list.get_variables());
				            _localctx.var_list.add(" ");
				            _localctx.var_list.add(((Func_declarationContext)_localctx).ID->getText());
				            _localctx.var_list.add("(");
				            _localctx.var_list.add(")");
				            _localctx.var_list.add(";");
				            _localctx.var_list.set_line_number(((Func_declarationContext)_localctx).type_specifier.var_list.get_line_number());
				            writeIntoparserLogfFile(
				                "Line " + std::to_string(((Func_declarationContext)_localctx).type_specifier.var_list.get_line_number()) + ": " +
				                "func_declaration : type_specifier ID LPAREN RPAREN SEMICOLON\n\n" +
				                _localctx.var_list.get_list_as_string() + "\n"
				            );
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_definitionContext extends ParserRuleContext {
		public str_list var_list;
		public Type_specifierContext type_specifier;
		public Token ID;
		public Parameter_listContext parameter_list;
		public Token RPAREN;
		public Compound_statementContext compound_statement;
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(C8086Parser.LPAREN, 0); }
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(C8086Parser.RPAREN, 0); }
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public Func_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_definition; }
	}

	public final Func_definitionContext func_definition() throws RecognitionException {
		Func_definitionContext _localctx = new Func_definitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_func_definition);
		try {
			setState(111);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				((Func_definitionContext)_localctx).type_specifier = type_specifier();
				setState(95);
				((Func_definitionContext)_localctx).ID = match(ID);
				setState(96);
				match(LPAREN);
				 
				        // Insert function name into symbol table before entering scope
				        SymbolInfo si = SymbolInfo(((Func_definitionContext)_localctx).ID->getText(), "ID");
				        SymbolInfo *lookup = st.LookUpInCurrentScope(((Func_definitionContext)_localctx).ID->getText());
				        if(lookup != nullptr) {
				            // Function already declared - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Func_definitionContext)_localctx).ID->getText());
				            syntaxErrorCount++;
				        } else {
				            st.Insert(si);
				        }
				        // Now enter scope for function body
				        st.EnterScope(); scope_flag = 1; 
				        
				setState(98);
				((Func_definitionContext)_localctx).parameter_list = parameter_list(0);
				setState(99);
				((Func_definitionContext)_localctx).RPAREN = match(RPAREN);
				setState(100);
				((Func_definitionContext)_localctx).compound_statement = compound_statement();

				        // if the function is not in return type map, insert it
				        if(functionReturnTypeMap.find(((Func_definitionContext)_localctx).ID->getText()) == functionReturnTypeMap.end()) {
				            functionReturnTypeMap[((Func_definitionContext)_localctx).ID->getText()] = ((Func_definitionContext)_localctx).type_specifier.var_list.get_list_as_string();
				            // calculate argument count
				            int argCount = 0;
				            // split the parameter_list by commas
				            std::string paramList = ((Func_definitionContext)_localctx).parameter_list.var_list.get_list_as_string();
				            std::stringstream ss(paramList);
				            std::string token;
				            while (std::getline(ss, token, ',')) {
				                argCount++;
				            }
				            functionArgumentCountMap[((Func_definitionContext)_localctx).ID->getText()] = argCount;
				        }
				        // check type mismatch for function return type using map

				        std::string returnType = functionReturnTypeMap[((Func_definitionContext)_localctx).ID->getText()];
				        if(returnType != ((Func_definitionContext)_localctx).type_specifier.var_list.get_list_as_string()) {
				            writeIntoErrorFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Type mismatch for function " + ((Func_definitionContext)_localctx).ID->getText()+"()");
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Type mismatch for function " + ((Func_definitionContext)_localctx).ID->getText()+"()\n");
				        }

				        // check if argument count matches
				        int argCount = functionArgumentCountMap[((Func_definitionContext)_localctx).ID->getText()];
				        int paramCount = 0;
				        std::string paramList = ((Func_definitionContext)_localctx).parameter_list.var_list.get_list_as_string();
				        std::stringstream ss(paramList);
				        std::string token;
				        while (std::getline(ss, token, ',')) {
				            paramCount++;
				        }
				        if(argCount != paramCount) {
				            writeIntoErrorFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Argument count mismatch for function " + ((Func_definitionContext)_localctx).ID->getText()+"()");
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Argument count mismatch for function " + ((Func_definitionContext)_localctx).ID->getText()+"()\n");
				        }

				        _localctx.var_list.add(((Func_definitionContext)_localctx).type_specifier.var_list.get_variables());
				        _localctx.var_list.add(" ");
				        _localctx.var_list.add(((Func_definitionContext)_localctx).ID->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((Func_definitionContext)_localctx).parameter_list.var_list.get_variables());
				        _localctx.var_list.add(")");
				        _localctx.var_list.add(((Func_definitionContext)_localctx).compound_statement.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Func_definitionContext)_localctx).RPAREN->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Func_definitionContext)_localctx).compound_statement.var_list.get_line_number()) + ": func_definition : type_specifier ID LPAREN parameter_list RPAREN compound_statement\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				((Func_definitionContext)_localctx).type_specifier = type_specifier();
				setState(104);
				((Func_definitionContext)_localctx).ID = match(ID);
				setState(105);
				match(LPAREN);

				        // Insert function name into symbol table before entering scope
				        SymbolInfo si = SymbolInfo(((Func_definitionContext)_localctx).ID->getText(), "ID");
				        SymbolInfo *lookup = st.LookUpInCurrentScope(((Func_definitionContext)_localctx).ID->getText());
				        if(lookup != nullptr) {
				            // Function already declared - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Func_definitionContext)_localctx).ID->getText());
				            syntaxErrorCount++;
				        } else {
				            st.Insert(si);
				        }
				        // Now enter scope for function body
				        st.EnterScope(); scope_flag = 1;
				      
				setState(107);
				((Func_definitionContext)_localctx).RPAREN = match(RPAREN);
				setState(108);
				((Func_definitionContext)_localctx).compound_statement = compound_statement();
				 
				        // if the function is not in return type map, insert it
				        if(functionReturnTypeMap.find(((Func_definitionContext)_localctx).ID->getText()) == functionReturnTypeMap.end()) {
				            functionReturnTypeMap[((Func_definitionContext)_localctx).ID->getText()] = ((Func_definitionContext)_localctx).type_specifier.var_list.get_list_as_string();
				            // calculate argument count
				            int argCount = 0;
				            // split the parameter_list by commas
				            std::string paramList = "";
				            std::stringstream ss(paramList);
				            std::string token;
				            while (std::getline(ss, token, ',')) {
				                argCount++;
				            }
				            functionArgumentCountMap[((Func_definitionContext)_localctx).ID->getText()] = argCount;
				        }
				        // check type mismatch for function return type using map
				        std::string returnType = functionReturnTypeMap[((Func_definitionContext)_localctx).ID->getText()];
				        if(returnType != ((Func_definitionContext)_localctx).type_specifier.var_list.get_list_as_string()) {
				            writeIntoErrorFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Type mismatch for function " + ((Func_definitionContext)_localctx).ID->getText()+"()");
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Type mismatch for function " + ((Func_definitionContext)_localctx).ID->getText()+"()\n");
				        }
				        // check if argument count matches
				        int argCount = functionArgumentCountMap[((Func_definitionContext)_localctx).ID->getText()];
				        int paramCount = 0;
				        std::string paramList = "";
				        std::stringstream ss(paramList);
				        std::string token;
				        while (std::getline(ss, token, ',')) {
				            paramCount++;
				        }
				        if(argCount != paramCount) {
				            writeIntoErrorFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Argument count mismatch for function " + ((Func_definitionContext)_localctx).ID->getText()+"()");
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((Func_definitionContext)_localctx).ID->getLine()) + ": Argument count mismatch for function " + ((Func_definitionContext)_localctx).ID->getText()+"()\n");
				        }
				        _localctx.var_list.add(((Func_definitionContext)_localctx).type_specifier.var_list.get_variables());
				        _localctx.var_list.add(" ");
				        _localctx.var_list.add(((Func_definitionContext)_localctx).ID->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(")");
				        _localctx.var_list.add(((Func_definitionContext)_localctx).compound_statement.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Func_definitionContext)_localctx).RPAREN->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Func_definitionContext)_localctx).compound_statement.var_list.get_line_number()) + ": func_definition : type_specifier ID LPAREN RPAREN compound_statement\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Parameter_listContext extends ParserRuleContext {
		public str_list var_list;
		public Parameter_listContext p;
		public Type_specifierContext type_specifier;
		public Token ID;
		public Token COMMA;
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode COMMA() { return getToken(C8086Parser.COMMA, 0); }
		public Parameter_listContext parameter_list() {
			return getRuleContext(Parameter_listContext.class,0);
		}
		public Parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_list; }
	}

	public final Parameter_listContext parameter_list() throws RecognitionException {
		return parameter_list(0);
	}

	private Parameter_listContext parameter_list(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Parameter_listContext _localctx = new Parameter_listContext(_ctx, _parentState);
		Parameter_listContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_parameter_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(114);
				((Parameter_listContext)_localctx).type_specifier = type_specifier();
				setState(115);
				((Parameter_listContext)_localctx).ID = match(ID);
				 
				            std::string type = ((Parameter_listContext)_localctx).type_specifier.var_list.get_list_as_string();
				            SymbolInfo si = SymbolInfo(((Parameter_listContext)_localctx).ID->getText(), type);
				            SymbolInfo *lookup = st.LookUpInCurrentScope(((Parameter_listContext)_localctx).ID->getText());
				            if(lookup != nullptr) {
				                // Parameter already exists in current scope - log error
				                writeIntoErrorFile("Error at line " + std::to_string(((Parameter_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Parameter_listContext)_localctx).ID->getText());
				                writeIntoparserLogfFile("Error at line " + std::to_string(((Parameter_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Parameter_listContext)_localctx).ID->getText());
				                syntaxErrorCount++;
				            } else {
				                st.Insert(si);
				            }
				            _localctx.var_list.add(((Parameter_listContext)_localctx).type_specifier.var_list.get_variables());
				            _localctx.var_list.add(" ");
				            _localctx.var_list.add(((Parameter_listContext)_localctx).ID->getText());
				            _localctx.var_list.set_line_number(((Parameter_listContext)_localctx).type_specifier.var_list.get_line_number());
				            writeIntoparserLogfFile(
				                "Line " + std::to_string(((Parameter_listContext)_localctx).type_specifier.var_list.get_line_number()) + ": " +
				                "parameter_list : type_specifier ID\n\n" +
				                _localctx.var_list.get_list_as_string() + "\n"
				            );
				        
				}
				break;
			case 2:
				{
				setState(118);
				((Parameter_listContext)_localctx).type_specifier = type_specifier();
				 

				            _localctx.var_list.add(((Parameter_listContext)_localctx).type_specifier.var_list.get_variables());
				            _localctx.var_list.set_line_number(((Parameter_listContext)_localctx).type_specifier.var_list.get_line_number());
				            writeIntoparserLogfFile(
				                "Line " + std::to_string(((Parameter_listContext)_localctx).type_specifier.var_list.get_line_number()) + ": " +
				                "parameter_list : type_specifier\n\n" +
				                _localctx.var_list.get_list_as_string() + "\n"
				            );
				        
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(136);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(134);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new Parameter_listContext(_parentctx, _parentState);
						_localctx.p = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_parameter_list);
						setState(123);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(124);
						((Parameter_listContext)_localctx).COMMA = match(COMMA);
						setState(125);
						((Parameter_listContext)_localctx).type_specifier = type_specifier();
						setState(126);
						((Parameter_listContext)_localctx).ID = match(ID);
						 
						                      std::string type = ((Parameter_listContext)_localctx).type_specifier.var_list.get_list_as_string();
						                      SymbolInfo si = SymbolInfo(((Parameter_listContext)_localctx).ID->getText(), type);
						                      SymbolInfo *lookup = st.LookUpInCurrentScope(((Parameter_listContext)_localctx).ID->getText());
						                      if(lookup != nullptr) {
						                          // Parameter already exists in current scope - log error
						                          writeIntoErrorFile("Error at line " + std::to_string(((Parameter_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Parameter_listContext)_localctx).ID->getText());
						                          writeIntoparserLogfFile("Error at line " + std::to_string(((Parameter_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Parameter_listContext)_localctx).ID->getText());
						                          syntaxErrorCount++;
						                      } else {
						                          st.Insert(si);
						                      }
						                      _localctx.var_list.add(((Parameter_listContext)_localctx).p.var_list.get_variables());
						                      _localctx.var_list.add(",");
						                      _localctx.var_list.add(((Parameter_listContext)_localctx).type_specifier.var_list.get_variables());
						                      _localctx.var_list.add(" ");
						                      _localctx.var_list.add(((Parameter_listContext)_localctx).ID->getText());
						                      _localctx.var_list.set_line_number(((Parameter_listContext)_localctx).type_specifier.var_list.get_line_number());
						                      writeIntoparserLogfFile(
						                          "Line " + std::to_string(((Parameter_listContext)_localctx).type_specifier.var_list.get_line_number()) + ": " +
						                          "parameter_list : parameter_list COMMA type_specifier ID\n\n" +
						                          _localctx.var_list.get_list_as_string() + "\n"
						                      );
						                   
						}
						break;
					case 2:
						{
						_localctx = new Parameter_listContext(_parentctx, _parentState);
						_localctx.p = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_parameter_list);
						setState(129);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(130);
						((Parameter_listContext)_localctx).COMMA = match(COMMA);
						setState(131);
						((Parameter_listContext)_localctx).type_specifier = type_specifier();
						 
						                      _localctx.var_list.add(((Parameter_listContext)_localctx).p.var_list.get_variables());
						                      _localctx.var_list.add(",");
						                      _localctx.var_list.add(((Parameter_listContext)_localctx).type_specifier.var_list.get_variables());
						                      _localctx.var_list.set_line_number(((Parameter_listContext)_localctx).COMMA->getLine());
						                      writeIntoparserLogfFile(
						                          "Line " + std::to_string(((Parameter_listContext)_localctx).COMMA->getLine()) + ": " +
						                          "parameter_list : parameter_list COMMA type_specifier\n\n" +
						                          _localctx.var_list.get_list_as_string() + "\n"
						                      );
						                  
						}
						break;
					}
					} 
				}
				setState(138);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Compound_statementContext extends ParserRuleContext {
		public str_list var_list;
		public Token LCURL;
		public StatementsContext statements;
		public Token RCURL;
		public TerminalNode LCURL() { return getToken(C8086Parser.LCURL, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode RCURL() { return getToken(C8086Parser.RCURL, 0); }
		public Compound_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_statement; }
	}

	public final Compound_statementContext compound_statement() throws RecognitionException {
		Compound_statementContext _localctx = new Compound_statementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_compound_statement);
		try {
			setState(148);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(139);
				((Compound_statementContext)_localctx).LCURL = match(LCURL);
				 if(scope_flag == 0) st.EnterScope(); 
				    scope_flag = 0;
				     
				setState(141);
				((Compound_statementContext)_localctx).statements = statements(0);
				setState(142);
				((Compound_statementContext)_localctx).RCURL = match(RCURL);
				 
				        _localctx.var_list.add(((Compound_statementContext)_localctx).LCURL->getText());
				        _localctx.var_list.add("\n");
				        _localctx.var_list.add(((Compound_statementContext)_localctx).statements.var_list.get_variables());
				        _localctx.var_list.add("\n");
				        _localctx.var_list.add(((Compound_statementContext)_localctx).RCURL->getText());
				        _localctx.var_list.set_line_number(((Compound_statementContext)_localctx).LCURL->getLine());
				        writeIntoparserLogfFile(
				            "Line " + std::to_string(((Compound_statementContext)_localctx).LCURL->getLine()) + ": compound_statement : LCURL statements RCURL\n\n" +
				            _localctx.var_list.get_list_as_string() + "\n"
				        );
				        st.PrintAllScopeTable(parserLogFile);
				        writeIntoparserLogfFile("\n");
				        st.ExitScope();
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				((Compound_statementContext)_localctx).LCURL = match(LCURL);
				setState(146);
				((Compound_statementContext)_localctx).RCURL = match(RCURL);
				 
				        _localctx.var_list.add(((Compound_statementContext)_localctx).LCURL->getText());
				        _localctx.var_list.add("\n");
				        _localctx.var_list.add(((Compound_statementContext)_localctx).RCURL->getText());
				        _localctx.var_list.set_line_number(((Compound_statementContext)_localctx).LCURL->getLine());
				        writeIntoparserLogfFile(
				            "Line " + std::to_string(((Compound_statementContext)_localctx).LCURL->getLine()) + ": compound_statement : LCURL RCURL\n\n" +
				            _localctx.var_list.get_list_as_string() + "\n"
				        );
				     
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_declarationContext extends ParserRuleContext {
		public str_list var_list;
		public Type_specifierContext t;
		public Declaration_listContext dl;
		public Token sm;
		public Declaration_list_errContext de;
		public Type_specifierContext type_specifier() {
			return getRuleContext(Type_specifierContext.class,0);
		}
		public Declaration_listContext declaration_list() {
			return getRuleContext(Declaration_listContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(C8086Parser.SEMICOLON, 0); }
		public Declaration_list_errContext declaration_list_err() {
			return getRuleContext(Declaration_list_errContext.class,0);
		}
		public Var_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_declaration; }
	}

	public final Var_declarationContext var_declaration() throws RecognitionException {
		Var_declarationContext _localctx = new Var_declarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var_declaration);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				((Var_declarationContext)_localctx).t = type_specifier();
				setState(151);
				((Var_declarationContext)_localctx).dl = declaration_list(0);
				setState(152);
				((Var_declarationContext)_localctx).sm = match(SEMICOLON);

				        std::string decl_list = ((Var_declarationContext)_localctx).dl.var_list.get_list_as_string();
				        std::stringstream ss(decl_list);
				        std::string item;
				        while (std::getline(ss, item, ',')) {
				            std::cout << "Item: " << item << std::endl;
				            // take the item until the LTHIRD
				            size_t lthird_pos = item.find('[');
				            if (lthird_pos != std::string::npos) {
				                item = item.substr(0, lthird_pos);
				            }
				            SymbolInfo si = SymbolInfo(item, ((Var_declarationContext)_localctx).t.var_list.get_list_as_string());
				            SymbolInfo *lookup = st.LookUpInCurrentScope(item);
				            if(lookup != nullptr) {
				                // Variable already exists in current scope - log error
				                writeIntoErrorFile("Error at line " + std::to_string(((Var_declarationContext)_localctx).dl.var_list.get_line_number()) + ": Multiple declaration of " + item);
				                writeIntoparserLogfFile("Error at line " + std::to_string(((Var_declarationContext)_localctx).dl.var_list.get_line_number()) + ": Multiple declaration of " + item);
				                syntaxErrorCount++;
				            } else {
				                cout<<"Inserting variable: " << item << " of type " << ((Var_declarationContext)_localctx).t.var_list.get_list_as_string() << endl;
				                st.Insert(si);
				            }
				        }

				        // variable type if void
				        if(((Var_declarationContext)_localctx).t.var_list.get_list_as_string() == "void") {
				            writeIntoErrorFile("Error at line " + std::to_string(((Var_declarationContext)_localctx).t.var_list.get_line_number()) + ": Variable type cannot be void\n");
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((Var_declarationContext)_localctx).t.var_list.get_line_number()) + ": Variable type cannot be void\n");
				        }
				        
				        _localctx.var_list.add(((Var_declarationContext)_localctx).t.var_list.get_variables());
				        _localctx.var_list.add(" ");
				        _localctx.var_list.add(((Var_declarationContext)_localctx).dl.var_list.get_variables());
				        _localctx.var_list.add(((Var_declarationContext)_localctx).sm->getText());
				        _localctx.var_list.set_line_number(((Var_declarationContext)_localctx).dl.var_list.get_line_number());
				        writeIntoparserLogfFile(
				            "Line " + std::to_string(((Var_declarationContext)_localctx).sm->getLine()) + ": var_declaration : type_specifier declaration_list SEMICOLON\n\n" +
				            _localctx.var_list.get_list_as_string() + "\n"
				        );

				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				((Var_declarationContext)_localctx).t = type_specifier();
				setState(156);
				((Var_declarationContext)_localctx).de = declaration_list_err();
				setState(157);
				((Var_declarationContext)_localctx).sm = match(SEMICOLON);

				        writeIntoErrorFile(
				            std::string("Line# ") + std::to_string(((Var_declarationContext)_localctx).sm->getLine()) +
				            " with error name: " + ((Var_declarationContext)_localctx).de.error_name +
				            " - Syntax error at declaration list of variable declaration"
				        );
				        syntaxErrorCount++;
				      
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaration_list_errContext extends ParserRuleContext {
		public std::string error_name;
		public Declaration_list_errContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_list_err; }
	}

	public final Declaration_list_errContext declaration_list_err() throws RecognitionException {
		Declaration_list_errContext _localctx = new Declaration_list_errContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaration_list_err);
		try {
			enterOuterAlt(_localctx, 1);
			{

			        ((Declaration_list_errContext)_localctx).error_name =  "Error in declaration list";
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Type_specifierContext extends ParserRuleContext {
		public str_list var_list;
		public Token INT;
		public Token FLOAT;
		public Token VOID;
		public TerminalNode INT() { return getToken(C8086Parser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(C8086Parser.FLOAT, 0); }
		public TerminalNode VOID() { return getToken(C8086Parser.VOID, 0); }
		public Type_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_specifier; }
	}

	public final Type_specifierContext type_specifier() throws RecognitionException {
		Type_specifierContext _localctx = new Type_specifierContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type_specifier);
		try {
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				((Type_specifierContext)_localctx).INT = match(INT);

				            _localctx.var_list.add(((Type_specifierContext)_localctx).INT->getText());
				            _localctx.var_list.set_line_number(((Type_specifierContext)_localctx).INT->getLine());
				            writeIntoparserLogfFile("Line " + std::to_string(((Type_specifierContext)_localctx).INT->getLine()) + ": type_specifier : INT\n");
				            writeIntoparserLogfFile(((Type_specifierContext)_localctx).INT->getText()+"\n");
				        
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				((Type_specifierContext)_localctx).FLOAT = match(FLOAT);

				            _localctx.var_list.add(((Type_specifierContext)_localctx).FLOAT->getText());
				            _localctx.var_list.set_line_number(((Type_specifierContext)_localctx).FLOAT->getLine());
				            writeIntoparserLogfFile("Line " + std::to_string(((Type_specifierContext)_localctx).FLOAT->getLine()) + ": type_specifier : FLOAT\n");
				            writeIntoparserLogfFile(((Type_specifierContext)_localctx).FLOAT->getText()+"\n");
				        
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 3);
				{
				setState(168);
				((Type_specifierContext)_localctx).VOID = match(VOID);

				            _localctx.var_list.add(((Type_specifierContext)_localctx).VOID->getText());
				            _localctx.var_list.set_line_number(((Type_specifierContext)_localctx).VOID->getLine());
				            writeIntoparserLogfFile("Line " + std::to_string(((Type_specifierContext)_localctx).VOID->getLine()) + ": type_specifier : VOID\n");
				            writeIntoparserLogfFile(((Type_specifierContext)_localctx).VOID->getText()+"\n");
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaration_listContext extends ParserRuleContext {
		public str_list var_list;
		public Declaration_listContext dl;
		public Token ID;
		public Token LTHIRD;
		public Token CONST_INT;
		public Token RTHIRD;
		public Token COMMA;
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode LTHIRD() { return getToken(C8086Parser.LTHIRD, 0); }
		public TerminalNode CONST_INT() { return getToken(C8086Parser.CONST_INT, 0); }
		public TerminalNode RTHIRD() { return getToken(C8086Parser.RTHIRD, 0); }
		public TerminalNode COMMA() { return getToken(C8086Parser.COMMA, 0); }
		public Declaration_listContext declaration_list() {
			return getRuleContext(Declaration_listContext.class,0);
		}
		public Declaration_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration_list; }
	}

	public final Declaration_listContext declaration_list() throws RecognitionException {
		return declaration_list(0);
	}

	private Declaration_listContext declaration_list(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Declaration_listContext _localctx = new Declaration_listContext(_ctx, _parentState);
		Declaration_listContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_declaration_list, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(173);
				((Declaration_listContext)_localctx).ID = match(ID);

				                // SymbolInfo si = SymbolInfo(((Declaration_listContext)_localctx).ID->getText(), "ID");
				                // SymbolInfo *lookup = st.LookUpInCurrentScope(((Declaration_listContext)_localctx).ID->getText());
				                // if(lookup != nullptr) {
				                //     // Variable already exists in current scope - log error
				                //     writeIntoErrorFile("Error at line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Declaration_listContext)_localctx).ID->getText());
				                //     writeIntoparserLogfFile("Error at line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Declaration_listContext)_localctx).ID->getText()+"\n");
				                //     syntaxErrorCount++;
				                // } else {
				                //     st.Insert(si);
				                // }
				                isArrayMap[((Declaration_listContext)_localctx).ID->getText()] = false;
								writeIntoparserLogfFile("Line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": declaration_list : ID\n");
				                writeIntoparserLogfFile(((Declaration_listContext)_localctx).ID->getText()+"\n");
								_localctx.var_list.add(((Declaration_listContext)_localctx).ID->getText());
				                _localctx.var_list.set_line_number(((Declaration_listContext)_localctx).ID->getLine());
							
				}
				break;
			case 2:
				{
				setState(175);
				((Declaration_listContext)_localctx).ID = match(ID);
				setState(176);
				((Declaration_listContext)_localctx).LTHIRD = match(LTHIRD);
				setState(177);
				((Declaration_listContext)_localctx).CONST_INT = match(CONST_INT);
				setState(178);
				((Declaration_listContext)_localctx).RTHIRD = match(RTHIRD);
				 
				                // SymbolInfo si = SymbolInfo(((Declaration_listContext)_localctx).ID->getText(), "ID_ARRAY");
				                // SymbolInfo *lookup = st.LookUpInCurrentScope(((Declaration_listContext)_localctx).ID->getText());
				                // if(lookup != nullptr) {
				                //     // Variable already exists in current scope - log error
				                //     writeIntoErrorFile("Error at line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Declaration_listContext)_localctx).ID->getText());
				                //     writeIntoparserLogfFile("Error at line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Declaration_listContext)_localctx).ID->getText()+"\n");
				                //     syntaxErrorCount++;
				                // } else {
				                //     st.Insert(si);
				                // }
				                isArrayMap[((Declaration_listContext)_localctx).ID->getText()] = true;
				                _localctx.var_list.add(((Declaration_listContext)_localctx).ID->getText());
				                _localctx.var_list.add(((Declaration_listContext)_localctx).LTHIRD->getText());
				                _localctx.var_list.add(((Declaration_listContext)_localctx).CONST_INT->getText());
				                _localctx.var_list.add(((Declaration_listContext)_localctx).RTHIRD->getText());
				                _localctx.var_list.set_line_number(((Declaration_listContext)_localctx).ID->getLine());
				                writeIntoparserLogfFile("Line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": declaration_list : ID LTHIRD CONST_INT RTHIRD\n");
				                writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(193);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new Declaration_listContext(_parentctx, _parentState);
						_localctx.dl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_declaration_list);
						setState(182);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(183);
						((Declaration_listContext)_localctx).COMMA = match(COMMA);
						setState(184);
						((Declaration_listContext)_localctx).ID = match(ID);

						                          // SymbolInfo si = SymbolInfo(((Declaration_listContext)_localctx).ID->getText(), "ID");
						                          // SymbolInfo *lookup = st.LookUpInCurrentScope(((Declaration_listContext)_localctx).ID->getText());
						                          // if(lookup != nullptr) {
						                          //     // Variable already exists in current scope - log error
						                          //     writeIntoErrorFile("Error at line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Declaration_listContext)_localctx).ID->getText());
						                          //     writeIntoparserLogfFile("Error at line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Declaration_listContext)_localctx).ID->getText()+"\n");
						                          //     syntaxErrorCount++;
						                          // } else {
						                          //     st.Insert(si);
						                          // }
						                          isArrayMap[((Declaration_listContext)_localctx).ID->getText()] = false;
						          				_localctx.var_list.set_variables(((Declaration_listContext)_localctx).dl.var_list.get_variables());
						                          _localctx.var_list.add(((Declaration_listContext)_localctx).COMMA->getText());
						          				_localctx.var_list.add(((Declaration_listContext)_localctx).ID->getText());
						                          _localctx.var_list.set_line_number(((Declaration_listContext)_localctx).ID->getLine());
						                          writeIntoparserLogfFile("Line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": declaration_list : declaration_list COMMA ID\n");
						                          writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
						          			
						}
						break;
					case 2:
						{
						_localctx = new Declaration_listContext(_parentctx, _parentState);
						_localctx.dl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_declaration_list);
						setState(186);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(187);
						((Declaration_listContext)_localctx).COMMA = match(COMMA);
						setState(188);
						((Declaration_listContext)_localctx).ID = match(ID);
						setState(189);
						((Declaration_listContext)_localctx).LTHIRD = match(LTHIRD);
						setState(190);
						((Declaration_listContext)_localctx).CONST_INT = match(CONST_INT);
						setState(191);
						((Declaration_listContext)_localctx).RTHIRD = match(RTHIRD);
						 
						                          // SymbolInfo si = SymbolInfo(((Declaration_listContext)_localctx).ID->getText(), "ID_ARRAY");
						                          // SymbolInfo *lookup = st.LookUpInCurrentScope(((Declaration_listContext)_localctx).ID->getText());
						                          // if(lookup != nullptr) {
						                          //     // Variable already exists in current scope - log error
						                          //     writeIntoErrorFile("Error at line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Declaration_listContext)_localctx).ID->getText());
						                          //     writeIntoparserLogfFile("Error at line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": Multiple declaration of " + ((Declaration_listContext)_localctx).ID->getText()+"\n");
						                          //     syntaxErrorCount++;
						                          // } else {
						                          //     st.Insert(si);
						                          // }
						                          isArrayMap[((Declaration_listContext)_localctx).ID->getText()] = true;
						          				_localctx.var_list.set_variables(((Declaration_listContext)_localctx).dl.var_list.get_variables());
						                          _localctx.var_list.add(((Declaration_listContext)_localctx).COMMA->getText());
						          				_localctx.var_list.add(((Declaration_listContext)_localctx).ID->getText());
						                          _localctx.var_list.add(((Declaration_listContext)_localctx).LTHIRD->getText());
						                          _localctx.var_list.add(((Declaration_listContext)_localctx).CONST_INT->getText());
						                          _localctx.var_list.add(((Declaration_listContext)_localctx).RTHIRD->getText());
						                          _localctx.var_list.set_line_number(((Declaration_listContext)_localctx).ID->getLine());
						                          writeIntoparserLogfFile("Line " + std::to_string(((Declaration_listContext)_localctx).ID->getLine()) + ": declaration_list : declaration_list COMMA ID LTHIRD CONST_INT RTHIRD\n");
						                          writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
						          			
						}
						break;
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public str_list var_list;
		public StatementsContext sts;
		public StatementContext statement;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
	}

	public final StatementsContext statements() throws RecognitionException {
		return statements(0);
	}

	private StatementsContext statements(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementsContext _localctx = new StatementsContext(_ctx, _parentState);
		StatementsContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_statements, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(199);
			((StatementsContext)_localctx).statement = statement();
			 
			        _localctx.var_list.set_variables(((StatementsContext)_localctx).statement.var_list.get_variables());
			        _localctx.var_list.set_line_number(((StatementsContext)_localctx).statement.var_list.get_line_number());
			        writeIntoparserLogfFile("Line " + std::to_string(((StatementsContext)_localctx).statement.var_list.get_line_number()) + ": statements : statement\n");
			        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
			     
			}
			_ctx.stop = _input.LT(-1);
			setState(208);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementsContext(_parentctx, _parentState);
					_localctx.sts = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_statements);
					setState(202);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(203);
					((StatementsContext)_localctx).statement = statement();
					 
					                  _localctx.var_list.set_variables(((StatementsContext)_localctx).sts.var_list.get_variables());
					                  _localctx.var_list.add("\n");
					                  _localctx.var_list.add(((StatementsContext)_localctx).statement.var_list.get_variables());
					                  _localctx.var_list.set_line_number(((StatementsContext)_localctx).statement.var_list.get_line_number());
					                  writeIntoparserLogfFile("Line " + std::to_string(((StatementsContext)_localctx).statement.var_list.get_line_number()) + ": statements : statements statement\n");
					                  writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
					               
					}
					} 
				}
				setState(210);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public str_list var_list;
		public Var_declarationContext var_declaration;
		public Expression_statementContext expression_statement;
		public Compound_statementContext compound_statement;
		public Token FOR;
		public ExpressionContext expression;
		public StatementContext st;
		public Token IF;
		public Token WHILE;
		public Token PRINTLN;
		public Token ID;
		public Token SEMICOLON;
		public Token RETURN;
		public Token DO;
		public Token LCURL;
		public Token RCURL;
		public Token BREAK;
		public Token CONTINUE;
		public Var_declarationContext var_declaration() {
			return getRuleContext(Var_declarationContext.class,0);
		}
		public List<Expression_statementContext> expression_statement() {
			return getRuleContexts(Expression_statementContext.class);
		}
		public Expression_statementContext expression_statement(int i) {
			return getRuleContext(Expression_statementContext.class,i);
		}
		public Compound_statementContext compound_statement() {
			return getRuleContext(Compound_statementContext.class,0);
		}
		public TerminalNode FOR() { return getToken(C8086Parser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(C8086Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(C8086Parser.RPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode IF() { return getToken(C8086Parser.IF, 0); }
		public TerminalNode ELSE() { return getToken(C8086Parser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(C8086Parser.WHILE, 0); }
		public TerminalNode PRINTLN() { return getToken(C8086Parser.PRINTLN, 0); }
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(C8086Parser.SEMICOLON, 0); }
		public TerminalNode RETURN() { return getToken(C8086Parser.RETURN, 0); }
		public TerminalNode DO() { return getToken(C8086Parser.DO, 0); }
		public TerminalNode LCURL() { return getToken(C8086Parser.LCURL, 0); }
		public TerminalNode RCURL() { return getToken(C8086Parser.RCURL, 0); }
		public TerminalNode BREAK() { return getToken(C8086Parser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(C8086Parser.CONTINUE, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_statement);
		try {
			setState(280);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				((StatementContext)_localctx).var_declaration = var_declaration();
				 
				        _localctx.var_list.set_variables(((StatementContext)_localctx).var_declaration.var_list.get_variables());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).var_declaration.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).var_declaration.var_list.get_line_number()) + ": statement : var_declaration\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				((StatementContext)_localctx).expression_statement = expression_statement();
				 
				        _localctx.var_list.set_variables(((StatementContext)_localctx).expression_statement.var_list.get_variables());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).expression_statement.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).expression_statement.var_list.get_line_number()) + ": statement : expression_statement\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				      
				     
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
				((StatementContext)_localctx).compound_statement = compound_statement();
				 
				        _localctx.var_list.set_variables(((StatementContext)_localctx).compound_statement.var_list.get_variables());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).compound_statement.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).compound_statement.var_list.get_line_number()) + ": statement : compound_statement\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(220);
				((StatementContext)_localctx).FOR = match(FOR);
				setState(221);
				match(LPAREN);
				setState(222);
				((StatementContext)_localctx).expression_statement = expression_statement();
				setState(223);
				((StatementContext)_localctx).expression_statement = expression_statement();
				setState(224);
				((StatementContext)_localctx).expression = expression();
				setState(225);
				match(RPAREN);
				setState(226);
				((StatementContext)_localctx).st = statement();
				 
				        _localctx.var_list.add(((StatementContext)_localctx).FOR->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((StatementContext)_localctx).expression_statement.var_list.get_variables());
				        _localctx.var_list.add(((StatementContext)_localctx).expression_statement.var_list.get_variables());
				        _localctx.var_list.add(((StatementContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(")");
				        _localctx.var_list.add(((StatementContext)_localctx).st.var_list.get_variables());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).FOR->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).FOR->getLine()) + ": statement : FOR LPAREN expression_statement expression_statement expression RPAREN statement\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(229);
				((StatementContext)_localctx).IF = match(IF);
				setState(230);
				match(LPAREN);
				setState(231);
				((StatementContext)_localctx).expression = expression();
				setState(232);
				match(RPAREN);
				setState(233);
				((StatementContext)_localctx).st = statement();
				 
				        _localctx.var_list.add(((StatementContext)_localctx).IF->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((StatementContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(")");
				        _localctx.var_list.add(((StatementContext)_localctx).st.var_list.get_variables());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).IF->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).IF->getLine()) + ": statement : IF LPAREN expression RPAREN statement\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(236);
				((StatementContext)_localctx).IF = match(IF);
				setState(237);
				match(LPAREN);
				setState(238);
				((StatementContext)_localctx).expression = expression();
				setState(239);
				match(RPAREN);
				setState(240);
				((StatementContext)_localctx).st = statement();
				setState(241);
				match(ELSE);
				setState(242);
				statement();
				 
				        _localctx.var_list.add(((StatementContext)_localctx).IF->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((StatementContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(")");
				        _localctx.var_list.add(((StatementContext)_localctx).st.var_list.get_variables());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).IF->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).IF->getLine()) + ": statement : IF LPAREN expression RPAREN statement ELSE statement\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(245);
				((StatementContext)_localctx).WHILE = match(WHILE);
				setState(246);
				match(LPAREN);
				setState(247);
				((StatementContext)_localctx).expression = expression();
				setState(248);
				match(RPAREN);
				setState(249);
				((StatementContext)_localctx).st = statement();
				 
				        _localctx.var_list.add(((StatementContext)_localctx).WHILE->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((StatementContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(")");
				        _localctx.var_list.add(((StatementContext)_localctx).st.var_list.get_variables());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).WHILE->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).WHILE->getLine()) + ": statement : WHILE LPAREN expression RPAREN statement\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(252);
				((StatementContext)_localctx).PRINTLN = match(PRINTLN);
				setState(253);
				match(LPAREN);
				setState(254);
				((StatementContext)_localctx).ID = match(ID);
				setState(255);
				match(RPAREN);
				setState(256);
				((StatementContext)_localctx).SEMICOLON = match(SEMICOLON);
				 
				        _localctx.var_list.add(((StatementContext)_localctx).PRINTLN->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((StatementContext)_localctx).ID->getText());
				        _localctx.var_list.add(")");
				        _localctx.var_list.add(((StatementContext)_localctx).SEMICOLON->getText());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).PRINTLN->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).PRINTLN->getLine()) + ": statement : PRINTLN LPAREN ID RPAREN SEMICOLON\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(258);
				((StatementContext)_localctx).RETURN = match(RETURN);
				setState(259);
				((StatementContext)_localctx).expression = expression();
				setState(260);
				match(SEMICOLON);
				 
				        _localctx.var_list.add(((StatementContext)_localctx).RETURN->getText());
				        _localctx.var_list.add(" ");
				        _localctx.var_list.add(((StatementContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(";");
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).RETURN->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).RETURN->getLine()) + ": statement : RETURN expression SEMICOLON\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				      
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(263);
				((StatementContext)_localctx).DO = match(DO);
				setState(264);
				((StatementContext)_localctx).LCURL = match(LCURL);
				setState(265);
				((StatementContext)_localctx).st = statement();
				setState(266);
				((StatementContext)_localctx).RCURL = match(RCURL);
				setState(267);
				((StatementContext)_localctx).WHILE = match(WHILE);
				setState(268);
				match(LPAREN);
				setState(269);
				((StatementContext)_localctx).expression = expression();
				setState(270);
				match(RPAREN);
				setState(271);
				match(SEMICOLON);
				 
				        _localctx.var_list.add(((StatementContext)_localctx).DO->getText());
				        _localctx.var_list.add(((StatementContext)_localctx).LCURL->getText());
				        _localctx.var_list.add(((StatementContext)_localctx).st.var_list.get_variables());
				        _localctx.var_list.add(((StatementContext)_localctx).RCURL->getText());
				        _localctx.var_list.add(((StatementContext)_localctx).WHILE->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((StatementContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(")"); 
				        _localctx.var_list.add(";");
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).DO->getLine()) + ": statement : DO LCURL statement RCURL WHILE LPAREN expression RPAREN\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).DO->getLine());
				     
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(274);
				((StatementContext)_localctx).BREAK = match(BREAK);
				setState(275);
				((StatementContext)_localctx).SEMICOLON = match(SEMICOLON);

				        _localctx.var_list.add(((StatementContext)_localctx).BREAK->getText());
				        _localctx.var_list.add(((StatementContext)_localctx).SEMICOLON->getText());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).BREAK->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).BREAK->getLine()) + ": statement : BREAK SEMICOLON\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(277);
				((StatementContext)_localctx).CONTINUE = match(CONTINUE);
				setState(278);
				((StatementContext)_localctx).SEMICOLON = match(SEMICOLON);

				        _localctx.var_list.add(((StatementContext)_localctx).CONTINUE->getText());
				        _localctx.var_list.add(((StatementContext)_localctx).SEMICOLON->getText());
				        _localctx.var_list.set_line_number(((StatementContext)_localctx).CONTINUE->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((StatementContext)_localctx).CONTINUE->getLine()) + ": statement : CONTINUE SEMICOLON\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Expression_statementContext extends ParserRuleContext {
		public str_list var_list;
		public Token SEMICOLON;
		public ExpressionContext expression;
		public TerminalNode SEMICOLON() { return getToken(C8086Parser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expression_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_statement; }
	}

	public final Expression_statementContext expression_statement() throws RecognitionException {
		Expression_statementContext _localctx = new Expression_statementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expression_statement);
		try {
			setState(288);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SEMICOLON:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				((Expression_statementContext)_localctx).SEMICOLON = match(SEMICOLON);
				 
				        _localctx.var_list.add(((Expression_statementContext)_localctx).SEMICOLON->getText());
				        _localctx.var_list.set_line_number(((Expression_statementContext)_localctx).SEMICOLON->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Expression_statementContext)_localctx).SEMICOLON->getLine()) + ": expression_statement : SEMICOLON\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case LPAREN:
			case ADDOP:
			case NOT:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(284);
				((Expression_statementContext)_localctx).expression = expression();
				setState(285);
				((Expression_statementContext)_localctx).SEMICOLON = match(SEMICOLON);
				 
				        // check for type mismatch
				        std::string expr = ((Expression_statementContext)_localctx).expression.var_list.get_list_as_string();
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
				                    writeIntoErrorFile("Error at line " + std::to_string(((Expression_statementContext)_localctx).expression.var_list.get_line_number()) + ": Type mismatch");
				                    syntaxErrorCount++;
				                    writeIntoparserLogfFile("Error at line " + std::to_string(((Expression_statementContext)_localctx).expression.var_list.get_line_number()) + ": Type mismatch\n");
				                    writeIntoparserLogfFile(((Expression_statementContext)_localctx).expression.var_list.get_list_as_string() + "\n");
				                } 
				            }
				                }
				            //  else {
				            //     // Variable not declared - log error
				            //     writeIntoErrorFile("Error at line " + std::to_string(((Expression_statementContext)_localctx).expression.var_list.get_line_number()) + ": Undeclared variable " + var_name);
				            //     syntaxErrorCount++;
				            //     writeIntoparserLogfFile(((Expression_statementContext)_localctx).expression.var_list.get_list_as_string() + "\n");
				            // }
				        }
				        // Add expression to variable list
				        _localctx.var_list.add(((Expression_statementContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(((Expression_statementContext)_localctx).SEMICOLON->getText());
				        _localctx.var_list.set_line_number(((Expression_statementContext)_localctx).SEMICOLON->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Expression_statementContext)_localctx).SEMICOLON->getLine()) + ": expression_statement : expression SEMICOLON\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public str_list var_list;
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	 
		public VariableContext() { }
		public void copyFrom(VariableContext ctx) {
			super.copyFrom(ctx);
			this.var_list = ctx.var_list;
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Just_variableContext extends VariableContext {
		public Token ID;
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public Just_variableContext(VariableContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class Array_variableContext extends VariableContext {
		public Token ID;
		public Token LTHIRD;
		public ExpressionContext expression;
		public Token RTHIRD;
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode LTHIRD() { return getToken(C8086Parser.LTHIRD, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RTHIRD() { return getToken(C8086Parser.RTHIRD, 0); }
		public Array_variableContext(VariableContext ctx) { copyFrom(ctx); }
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variable);
		try {
			setState(298);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new Just_variableContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				((Just_variableContext)_localctx).ID = match(ID);
				 
				        SymbolInfo *lookup = st.LookUp(((Just_variableContext)_localctx).ID->getText());
				        if(lookup == nullptr) {
				            // Variable not declared - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((Just_variableContext)_localctx).ID->getLine()) + ": Undeclared variable " + ((Just_variableContext)_localctx).ID->getText());
				            syntaxErrorCount++;
				        }
				        else if(isArrayMap[((Just_variableContext)_localctx).ID->getText()]) {
				            // Variable is an array but used as ID - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((Just_variableContext)_localctx).ID->getLine()) + ": type mismatch " + ((Just_variableContext)_localctx).ID->getText() + " is an array");
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((Just_variableContext)_localctx).ID->getLine()) + ": type mismatch " + ((Just_variableContext)_localctx).ID->getText() + " is an array\n");
				        }
				        _localctx.var_list.add(((Just_variableContext)_localctx).ID->getText());
				        _localctx.var_list.set_line_number(((Just_variableContext)_localctx).ID->getLine());
				        // Update total line count
				        //totalLines = std::max(totalLines, ((Just_variableContext)_localctx).ID->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Just_variableContext)_localctx).ID->getLine()) + ": variable : ID\n");
				        writeIntoparserLogfFile(((Just_variableContext)_localctx).ID->getText()+"\n");
				      
				}
				break;
			case 2:
				_localctx = new Array_variableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				((Array_variableContext)_localctx).ID = match(ID);
				setState(293);
				((Array_variableContext)_localctx).LTHIRD = match(LTHIRD);
				setState(294);
				((Array_variableContext)_localctx).expression = expression();
				setState(295);
				((Array_variableContext)_localctx).RTHIRD = match(RTHIRD);
				 
				        SymbolInfo *lookup = st.LookUp(((Array_variableContext)_localctx).ID->getText());
				        if(lookup == nullptr) {
				            // Array not declared - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((Array_variableContext)_localctx).ID->getLine()) + ": Undeclared variable " + ((Array_variableContext)_localctx).ID->getText());
				            syntaxErrorCount++;
				        }
				        // check if expression is integer
				        std::string expr = ((Array_variableContext)_localctx).expression.var_list.get_list_as_string();
				        // check if expression contains a decimal point
				        if(expr.find('.') != std::string::npos) {
				            // Expression is not an integer - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((Array_variableContext)_localctx).expression.var_list.get_line_number()) + ": Expression inside third brackets not an integer");
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((Array_variableContext)_localctx).expression.var_list.get_line_number()) + ": Expression inside third brackets not an integer\n");
				        }
				        // check isarray
				        if(!isArrayMap[((Array_variableContext)_localctx).ID->getText()]) {
				            // Variable is not an array but used as array - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((Array_variableContext)_localctx).ID->getLine()) + ": type mismatch " + ((Array_variableContext)_localctx).ID->getText() + " is not an array");
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((Array_variableContext)_localctx).ID->getLine()) + ": type mismatch " + ((Array_variableContext)_localctx).ID->getText() + " is not an array\n");
				        }
				        // Add variable to list
				        _localctx.var_list.add(((Array_variableContext)_localctx).ID->getText());
				        _localctx.var_list.add(((Array_variableContext)_localctx).LTHIRD->getText());
				        _localctx.var_list.add(((Array_variableContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(((Array_variableContext)_localctx).RTHIRD->getText());
				        _localctx.var_list.set_line_number(((Array_variableContext)_localctx).ID->getLine());
				        // Update total line count
				        //totalLines = std::max(totalLines, ((Array_variableContext)_localctx).ID->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Array_variableContext)_localctx).ID->getLine()) + ": variable : ID LTHIRD expression RTHIRD\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public str_list var_list;
		public Logic_expressionContext logic_expression;
		public VariableContext variable;
		public Token ASSIGNOP;
		public Logic_expressionContext logic_expression() {
			return getRuleContext(Logic_expressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ASSIGNOP() { return getToken(C8086Parser.ASSIGNOP, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expression);
		try {
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(300);
				((ExpressionContext)_localctx).logic_expression = logic_expression();
				 
				        _localctx.var_list.set_variables(((ExpressionContext)_localctx).logic_expression.var_list.get_variables());
				        _localctx.var_list.set_line_number(((ExpressionContext)_localctx).logic_expression.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((ExpressionContext)_localctx).logic_expression.var_list.get_line_number()) + ": expression : logic_expression\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				((ExpressionContext)_localctx).variable = variable();
				setState(304);
				((ExpressionContext)_localctx).ASSIGNOP = match(ASSIGNOP);
				setState(305);
				((ExpressionContext)_localctx).logic_expression = logic_expression();
				 
				        // check if the logic_expression is void return type function, using functionReturnTypeMap
				        std::string lexpr = ((ExpressionContext)_localctx).logic_expression.var_list.get_list_as_string();
				        size_t lparen_pos = lexpr.find('(');
				        if(lparen_pos != std::string::npos) {
				            std::string func_name = lexpr.substr(0, lparen_pos);
				            // check if function exists in functionReturnTypeMap
				            auto it = functionReturnTypeMap.find(func_name);
				            if(it != functionReturnTypeMap.end()) {
				                std::string return_type = it->second;
				                if(return_type == "void") {
				                    // Logic expression is a void return type function - log error
				                    writeIntoErrorFile("Error at line " + std::to_string(((ExpressionContext)_localctx).logic_expression.var_list.get_line_number()) + ": Type mismatch - " + func_name + " is a void function");
				                    syntaxErrorCount++;
				                    writeIntoparserLogfFile("Error at line " + std::to_string(((ExpressionContext)_localctx).logic_expression.var_list.get_line_number()) + ": Type mismatch - " + func_name + "  is a void function\n");
				                }
				            }
				        }

				        // Add variable, ASSIGNOP and logic_expression to variable list
				        _localctx.var_list.add(((ExpressionContext)_localctx).variable.var_list.get_variables());
				        _localctx.var_list.add(((ExpressionContext)_localctx).ASSIGNOP->getText());
				        _localctx.var_list.add(((ExpressionContext)_localctx).logic_expression.var_list.get_variables());
				        _localctx.var_list.set_line_number(((ExpressionContext)_localctx).variable.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((ExpressionContext)_localctx).variable.var_list.get_line_number()) + ": expression : variable ASSIGNOP logic_expression\n");
				        
				        // check for type mismatch by checking the type of variable and logic_expression
				         std::string var_name = ((ExpressionContext)_localctx).variable.var_list.get_list_as_string();
				        // find variable name , name is until LTHIRD
				        size_t lthird_pos = var_name.find('[');
				        if(lthird_pos != std::string::npos) {
				            var_name = var_name.substr(0, lthird_pos);
				        }
				        SymbolInfo *lookup = st.LookUp(var_name);
				        if(lookup == nullptr) {
				            // Variable not declared - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((ExpressionContext)_localctx).variable.var_list.get_line_number()) + ": Undeclared variable " + var_name);
				            syntaxErrorCount++;
				            writeIntoparserLogfFile("Error at line " + std::to_string(((ExpressionContext)_localctx).variable.var_list.get_line_number()) + ": Undeclared variable " + var_name + "\n");
				        } else {
				            std::string var_type = lookup->getType();
				            // check if logic_expression is of the same type
				            std::string logic_expr = ((ExpressionContext)_localctx).logic_expression.var_list.get_list_as_string();
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
				                    writeIntoErrorFile("Error at line " + std::to_string(((ExpressionContext)_localctx).logic_expression.var_list.get_line_number()) + ": Type mismatch\n ");
				                    syntaxErrorCount++;
				                    writeIntoparserLogfFile("Error at line " + std::to_string(((ExpressionContext)_localctx).logic_expression.var_list.get_line_number()) + ": Type mismatch\n ");
				                }
				            } 
				            }
				            
				        }
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Logic_expressionContext extends ParserRuleContext {
		public str_list var_list;
		public Rel_expressionContext rel_expression;
		public Rel_expressionContext re1;
		public Token LOGICOP;
		public Rel_expressionContext re2;
		public List<Rel_expressionContext> rel_expression() {
			return getRuleContexts(Rel_expressionContext.class);
		}
		public Rel_expressionContext rel_expression(int i) {
			return getRuleContext(Rel_expressionContext.class,i);
		}
		public TerminalNode LOGICOP() { return getToken(C8086Parser.LOGICOP, 0); }
		public Logic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_expression; }
	}

	public final Logic_expressionContext logic_expression() throws RecognitionException {
		Logic_expressionContext _localctx = new Logic_expressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_logic_expression);
		try {
			setState(318);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(310);
				((Logic_expressionContext)_localctx).rel_expression = rel_expression();
				 
				        _localctx.var_list.set_variables(((Logic_expressionContext)_localctx).rel_expression.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Logic_expressionContext)_localctx).rel_expression.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((Logic_expressionContext)_localctx).rel_expression.var_list.get_line_number()) + ": logic_expression : rel_expression\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(313);
				((Logic_expressionContext)_localctx).re1 = rel_expression();
				setState(314);
				((Logic_expressionContext)_localctx).LOGICOP = match(LOGICOP);
				setState(315);
				((Logic_expressionContext)_localctx).re2 = rel_expression();
				 
				        _localctx.var_list.set_variables(((Logic_expressionContext)_localctx).re1.var_list.get_variables());
				        _localctx.var_list.add(((Logic_expressionContext)_localctx).LOGICOP->getText());
				        _localctx.var_list.add(((Logic_expressionContext)_localctx).re2.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Logic_expressionContext)_localctx).LOGICOP->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Logic_expressionContext)_localctx).LOGICOP->getLine()) + ": logic_expression : rel_expression LOGICOP rel_expression\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Rel_expressionContext extends ParserRuleContext {
		public str_list var_list;
		public Simple_expressionContext simple_expression;
		public Simple_expressionContext se1;
		public Token RELOP;
		public Simple_expressionContext se2;
		public List<Simple_expressionContext> simple_expression() {
			return getRuleContexts(Simple_expressionContext.class);
		}
		public Simple_expressionContext simple_expression(int i) {
			return getRuleContext(Simple_expressionContext.class,i);
		}
		public TerminalNode RELOP() { return getToken(C8086Parser.RELOP, 0); }
		public Rel_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rel_expression; }
	}

	public final Rel_expressionContext rel_expression() throws RecognitionException {
		Rel_expressionContext _localctx = new Rel_expressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_rel_expression);
		try {
			setState(328);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				((Rel_expressionContext)_localctx).simple_expression = simple_expression(0);

				        _localctx.var_list.set_variables(((Rel_expressionContext)_localctx).simple_expression.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Rel_expressionContext)_localctx).simple_expression.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((Rel_expressionContext)_localctx).simple_expression.var_list.get_line_number()) + ": rel_expression : simple_expression\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				((Rel_expressionContext)_localctx).se1 = simple_expression(0);
				setState(324);
				((Rel_expressionContext)_localctx).RELOP = match(RELOP);
				setState(325);
				((Rel_expressionContext)_localctx).se2 = simple_expression(0);
				 
				        _localctx.var_list.set_variables(((Rel_expressionContext)_localctx).se1.var_list.get_variables());
				        _localctx.var_list.add(((Rel_expressionContext)_localctx).RELOP->getText());
				        _localctx.var_list.add(((Rel_expressionContext)_localctx).se2.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Rel_expressionContext)_localctx).RELOP->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Rel_expressionContext)_localctx).RELOP->getLine()) + ": rel_expression : simple_expression RELOP simple_expression\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Simple_expressionContext extends ParserRuleContext {
		public str_list var_list;
		public Simple_expressionContext se;
		public TermContext term;
		public Token ADDOP;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode ADDOP() { return getToken(C8086Parser.ADDOP, 0); }
		public Simple_expressionContext simple_expression() {
			return getRuleContext(Simple_expressionContext.class,0);
		}
		public Simple_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_expression; }
	}

	public final Simple_expressionContext simple_expression() throws RecognitionException {
		return simple_expression(0);
	}

	private Simple_expressionContext simple_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Simple_expressionContext _localctx = new Simple_expressionContext(_ctx, _parentState);
		Simple_expressionContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_simple_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(331);
			((Simple_expressionContext)_localctx).term = term(0);
			 
			        _localctx.var_list.set_variables(((Simple_expressionContext)_localctx).term.var_list.get_variables());
			        _localctx.var_list.set_line_number(((Simple_expressionContext)_localctx).term.var_list.get_line_number());
			        writeIntoparserLogfFile("Line " + std::to_string(((Simple_expressionContext)_localctx).term.var_list.get_line_number()) + ": simple_expression : term\n");
			        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
			     
			}
			_ctx.stop = _input.LT(-1);
			setState(341);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Simple_expressionContext(_parentctx, _parentState);
					_localctx.se = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_simple_expression);
					setState(334);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(335);
					((Simple_expressionContext)_localctx).ADDOP = match(ADDOP);
					setState(336);
					((Simple_expressionContext)_localctx).term = term(0);
					 
					                  _localctx.var_list.set_variables(((Simple_expressionContext)_localctx).se.var_list.get_variables());
					                  _localctx.var_list.add(((Simple_expressionContext)_localctx).ADDOP->getText());
					                  _localctx.var_list.add(((Simple_expressionContext)_localctx).term.var_list.get_variables());
					                  _localctx.var_list.set_line_number(((Simple_expressionContext)_localctx).se.var_list.get_line_number());
					                  writeIntoparserLogfFile("Line " + std::to_string(((Simple_expressionContext)_localctx).se.var_list.get_line_number()) + ": simple_expression : simple_expression ADDOP term\n");
					                  writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
					               
					}
					} 
				}
				setState(343);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermContext extends ParserRuleContext {
		public str_list var_list;
		public TermContext t;
		public Unary_expressionContext unary_expression;
		public Token MULOP;
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TerminalNode MULOP() { return getToken(C8086Parser.MULOP, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		return term(0);
	}

	private TermContext term(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TermContext _localctx = new TermContext(_ctx, _parentState);
		TermContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_term, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(345);
			((TermContext)_localctx).unary_expression = unary_expression();
			 
			        _localctx.var_list.set_variables(((TermContext)_localctx).unary_expression.var_list.get_variables());
			        _localctx.var_list.set_line_number(((TermContext)_localctx).unary_expression.var_list.get_line_number());
			        writeIntoparserLogfFile("Line " + std::to_string(((TermContext)_localctx).unary_expression.var_list.get_line_number()) + ": term : unary_expression\n");
			        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
			     
			}
			_ctx.stop = _input.LT(-1);
			setState(355);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					_localctx.t = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(348);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(349);
					((TermContext)_localctx).MULOP = match(MULOP);
					setState(350);
					((TermContext)_localctx).unary_expression = unary_expression();
					 
					                  //check if modulus by 0,  i.e unary_expression is 0
					                  std::string unary_expr = ((TermContext)_localctx).unary_expression.var_list.get_list_as_string();
					                  if(unary_expr == "0") {
					                      // Unary expression is 0 - log error
					                      writeIntoErrorFile("Error at line " + std::to_string(((TermContext)_localctx).MULOP->getLine()) + ": Modulus by zero is not allowed");
					                      syntaxErrorCount++;
					                      writeIntoparserLogfFile("Error at line " + std::to_string(((TermContext)_localctx).MULOP->getLine()) + ": Modulus by zero is not allowed\n");
					                  }

					                  // check if unary expression contain void return type function
					                  unary_expr = ((TermContext)_localctx).unary_expression.var_list.get_list_as_string();
					                  // find function name and check if it is void ,  use return type map
					                  size_t func_pos = unary_expr.find('(');
					                  if(func_pos != std::string::npos) {
					                      std::string func_name = unary_expr.substr(0, func_pos);
					                      // check if function name is in return type map
					                      if(functionReturnTypeMap.find(func_name) != functionReturnTypeMap.end()) {
					                          std::string return_type = functionReturnTypeMap[func_name];
					                          if(return_type == "void") {
					                              // Function returns void - log error
					                              writeIntoErrorFile("Error at line " + std::to_string(((TermContext)_localctx).MULOP->getLine()) + ": Function " + func_name + " returns void but used in expression");
					                              syntaxErrorCount++;
					                              writeIntoparserLogfFile("Error at line " + std::to_string(((TermContext)_localctx).MULOP->getLine()) + ": Function " + func_name + " returns void but used in expression\n");
					                          }
					                      }
					                  }
					                      
					                  _localctx.var_list.set_variables(((TermContext)_localctx).t.var_list.get_variables());
					                  _localctx.var_list.add(((TermContext)_localctx).MULOP->getText());
					                  _localctx.var_list.add(((TermContext)_localctx).unary_expression.var_list.get_variables());
					                  _localctx.var_list.set_line_number(((TermContext)_localctx).MULOP->getLine());
					                  writeIntoparserLogfFile("Line " + std::to_string(((TermContext)_localctx).MULOP->getLine()) + ": term : term MULOP unary_expression\n");
					                  // check if unary_expression is integer
					                  if(((TermContext)_localctx).unary_expression.var_list.get_list_as_string().find('.') != std::string::npos) {
					                      // Unary expression is not an integer - log error
					                      writeIntoErrorFile("Error at line " + std::to_string(((TermContext)_localctx).MULOP->getLine()) + ": Non-integer operand on modulus operator\n");
					                      writeIntoparserLogfFile("Error at line " + std::to_string(((TermContext)_localctx).MULOP->getLine()) + ": Non-integer operand on modulus operator\n");
					                      // Increment syntax error count
					                      syntaxErrorCount++;
					                  }
					                  writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
					               
					}
					} 
				}
				setState(357);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Unary_expressionContext extends ParserRuleContext {
		public str_list var_list;
		public Token ADDOP;
		public Unary_expressionContext unary_expression;
		public Token NOT;
		public FactorContext factor;
		public TerminalNode ADDOP() { return getToken(C8086Parser.ADDOP, 0); }
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(C8086Parser.NOT, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public Unary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expression; }
	}

	public final Unary_expressionContext unary_expression() throws RecognitionException {
		Unary_expressionContext _localctx = new Unary_expressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_unary_expression);
		try {
			setState(369);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADDOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				((Unary_expressionContext)_localctx).ADDOP = match(ADDOP);
				setState(359);
				((Unary_expressionContext)_localctx).unary_expression = unary_expression();
				 
				        _localctx.var_list.add(((Unary_expressionContext)_localctx).ADDOP->getText());
				        _localctx.var_list.add(((Unary_expressionContext)_localctx).unary_expression.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Unary_expressionContext)_localctx).ADDOP->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Unary_expressionContext)_localctx).ADDOP->getLine()) + ": unary_expression : ADDOP unary_expression\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				((Unary_expressionContext)_localctx).NOT = match(NOT);
				setState(363);
				((Unary_expressionContext)_localctx).unary_expression = unary_expression();
				 
				        _localctx.var_list.add(((Unary_expressionContext)_localctx).NOT->getText());
				        _localctx.var_list.add(((Unary_expressionContext)_localctx).unary_expression.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Unary_expressionContext)_localctx).NOT->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((Unary_expressionContext)_localctx).NOT->getLine()) + ": unary_expression : NOT unary_expression\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case LPAREN:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(366);
				((Unary_expressionContext)_localctx).factor = factor();
				 
				        _localctx.var_list.set_variables(((Unary_expressionContext)_localctx).factor.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Unary_expressionContext)_localctx).factor.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((Unary_expressionContext)_localctx).factor.var_list.get_line_number()) + ": unary_expression : factor\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				      
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FactorContext extends ParserRuleContext {
		public str_list var_list;
		public VariableContext variable;
		public Token ID;
		public Argument_listContext argument_list;
		public Token LPAREN;
		public ExpressionContext expression;
		public Token CONST_INT;
		public Token CONST_FLOAT;
		public Token INCOP;
		public Token DECOP;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(C8086Parser.LPAREN, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(C8086Parser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CONST_INT() { return getToken(C8086Parser.CONST_INT, 0); }
		public TerminalNode CONST_FLOAT() { return getToken(C8086Parser.CONST_FLOAT, 0); }
		public TerminalNode INCOP() { return getToken(C8086Parser.INCOP, 0); }
		public TerminalNode DECOP() { return getToken(C8086Parser.DECOP, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_factor);
		try {
			setState(397);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				((FactorContext)_localctx).variable = variable();

				        std::cout<<"testing children of factor\n";
				        if(auto *jv = dynamic_cast<C8086Parser::Just_variableContext*>(variable())){ 
				            std::cout<<"******found just_variable\n";
				            std::cout<<jv->getText()<<"\n";
				         }
						_localctx.var_list.add(((FactorContext)_localctx).variable.var_list.get_variables());
						_localctx.var_list.set_line_number(((FactorContext)_localctx).variable.var_list.get_line_number());
						writeIntoparserLogfFile("Line " + std::to_string(((FactorContext)_localctx).variable.var_list.get_line_number()) + ": factor : variable\n");
						writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(374);
				((FactorContext)_localctx).ID = match(ID);
				setState(375);
				match(LPAREN);
				setState(376);
				((FactorContext)_localctx).argument_list = argument_list();
				setState(377);
				match(RPAREN);
				 
				        // Check if function exists in symbol table
				        SymbolInfo *lookup = st.LookUp(((FactorContext)_localctx).ID->getText());
				        if(lookup == nullptr && lookup->getName() != "printf") {
				            // Function not declared - log error
				            writeIntoErrorFile("Error at line " + std::to_string(((FactorContext)_localctx).ID->getLine()) + ": Undeclared function " + ((FactorContext)_localctx).ID->getText());
				            syntaxErrorCount++;
				        }
				        // Even if there's an error, continue with the parsing
				        _localctx.var_list.add(((FactorContext)_localctx).ID->getText());
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((FactorContext)_localctx).argument_list.var_list.get_variables());
				        _localctx.var_list.add(")");
				        _localctx.var_list.set_line_number(((FactorContext)_localctx).ID->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((FactorContext)_localctx).ID->getLine()) + ": factor : ID LPAREN argument_list RPAREN\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				      
				     
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(380);
				((FactorContext)_localctx).LPAREN = match(LPAREN);
				setState(381);
				((FactorContext)_localctx).expression = expression();
				setState(382);
				match(RPAREN);
				 
				        _localctx.var_list.add("(");
				        _localctx.var_list.add(((FactorContext)_localctx).expression.var_list.get_variables());
				        _localctx.var_list.add(")");
				        _localctx.var_list.set_line_number(((FactorContext)_localctx).LPAREN->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((FactorContext)_localctx).LPAREN->getLine()) + ": factor : LPAREN expression RPAREN\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(385);
				((FactorContext)_localctx).CONST_INT = match(CONST_INT);
				 
				        _localctx.var_list.add(((FactorContext)_localctx).CONST_INT->getText());
				        _localctx.var_list.set_line_number(((FactorContext)_localctx).CONST_INT->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((FactorContext)_localctx).CONST_INT->getLine()) + ": factor : CONST_INT\n");
				        writeIntoparserLogfFile(((FactorContext)_localctx).CONST_INT->getText()+"\n");
				      
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(387);
				((FactorContext)_localctx).CONST_FLOAT = match(CONST_FLOAT);
				 
				        _localctx.var_list.add(((FactorContext)_localctx).CONST_FLOAT->getText());
				        _localctx.var_list.set_line_number(((FactorContext)_localctx).CONST_FLOAT->getLine());
				        writeIntoparserLogfFile("Line " + std::to_string(((FactorContext)_localctx).CONST_FLOAT->getLine()) + ": factor : CONST_FLOAT\n");
				        writeIntoparserLogfFile(((FactorContext)_localctx).CONST_FLOAT->getText()+"\n");
				      
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(389);
				((FactorContext)_localctx).variable = variable();
				setState(390);
				((FactorContext)_localctx).INCOP = match(INCOP);
				 
				        _localctx.var_list.add(((FactorContext)_localctx).variable.var_list.get_variables());
				        _localctx.var_list.add(((FactorContext)_localctx).INCOP->getText());
				        _localctx.var_list.set_line_number(((FactorContext)_localctx).variable.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((FactorContext)_localctx).variable.var_list.get_line_number()) + ": factor : variable INCOP\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(393);
				((FactorContext)_localctx).variable = variable();
				setState(394);
				((FactorContext)_localctx).DECOP = match(DECOP);
				 
				        _localctx.var_list.add(((FactorContext)_localctx).variable.var_list.get_variables());
				        _localctx.var_list.add(((FactorContext)_localctx).DECOP->getText());
				        _localctx.var_list.set_line_number(((FactorContext)_localctx).variable.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((FactorContext)_localctx).variable.var_list.get_line_number()) + ": factor : variable DECOP\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Argument_listContext extends ParserRuleContext {
		public str_list var_list;
		public ArgumentsContext arguments;
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_argument_list);
		try {
			setState(403);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
			case ADDOP:
			case NOT:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 1);
				{
				setState(399);
				((Argument_listContext)_localctx).arguments = arguments(0);
				 
				        _localctx.var_list.set_variables(((Argument_listContext)_localctx).arguments.var_list.get_variables());
				        _localctx.var_list.set_line_number(((Argument_listContext)_localctx).arguments.var_list.get_line_number());
				        writeIntoparserLogfFile("Line " + std::to_string(((Argument_listContext)_localctx).arguments.var_list.get_line_number()) + ": argument_list : arguments\n");
				        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
				     
				}
				break;
			case RPAREN:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public str_list var_list;
		public ArgumentsContext args;
		public Logic_expressionContext logic_expression;
		public Token COMMA;
		public Logic_expressionContext logic_expression() {
			return getRuleContext(Logic_expressionContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(C8086Parser.COMMA, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		return arguments(0);
	}

	private ArgumentsContext arguments(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, _parentState);
		ArgumentsContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_arguments, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(406);
			((ArgumentsContext)_localctx).logic_expression = logic_expression();
			 
			        _localctx.var_list.add(((ArgumentsContext)_localctx).logic_expression.var_list.get_variables());
			        _localctx.var_list.set_line_number(((ArgumentsContext)_localctx).logic_expression.var_list.get_line_number());
			        writeIntoparserLogfFile("Line " + std::to_string(((ArgumentsContext)_localctx).logic_expression.var_list.get_line_number()) + ": arguments : logic_expression\n");
			        writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
			     
			}
			_ctx.stop = _input.LT(-1);
			setState(416);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArgumentsContext(_parentctx, _parentState);
					_localctx.args = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_arguments);
					setState(409);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(410);
					((ArgumentsContext)_localctx).COMMA = match(COMMA);
					setState(411);
					((ArgumentsContext)_localctx).logic_expression = logic_expression();
					 
					                  _localctx.var_list.set_variables(((ArgumentsContext)_localctx).args.var_list.get_variables());
					                  _localctx.var_list.add(((ArgumentsContext)_localctx).COMMA->getText());
					                  _localctx.var_list.add(((ArgumentsContext)_localctx).logic_expression.var_list.get_variables());
					                  _localctx.var_list.set_line_number(((ArgumentsContext)_localctx).args.var_list.get_line_number());
					                  writeIntoparserLogfFile("Line " + std::to_string(((ArgumentsContext)_localctx).args.var_list.get_line_number()) + ": arguments : arguments COMMA logic_expression\n");
					                  writeIntoparserLogfFile(_localctx.var_list.get_list_as_string() + "\n");
					               
					}
					} 
				}
				setState(418);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return program_sempred((ProgramContext)_localctx, predIndex);
		case 5:
			return parameter_list_sempred((Parameter_listContext)_localctx, predIndex);
		case 10:
			return declaration_list_sempred((Declaration_listContext)_localctx, predIndex);
		case 11:
			return statements_sempred((StatementsContext)_localctx, predIndex);
		case 18:
			return simple_expression_sempred((Simple_expressionContext)_localctx, predIndex);
		case 19:
			return term_sempred((TermContext)_localctx, predIndex);
		case 23:
			return arguments_sempred((ArgumentsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean program_sempred(ProgramContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean parameter_list_sempred(Parameter_listContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean declaration_list_sempred(Declaration_listContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean statements_sempred(StatementsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean simple_expression_sempred(Simple_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean arguments_sempred(ArgumentsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u01a4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001<\b\u0001\n\u0001\f\u0001"+
		"?\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002J\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"]\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004p\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005z\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005"+
		"\u0087\b\u0005\n\u0005\f\u0005\u008a\t\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u0095\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u00a1\b\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u00ab\b\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b5\b\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005"+
		"\n\u00c2\b\n\n\n\f\n\u00c5\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00cf"+
		"\b\u000b\n\u000b\f\u000b\u00d2\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0003\f\u0119\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u0121\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u012b"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0135\b\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0003\u0010\u013f\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0149"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0154\b\u0012\n"+
		"\u0012\f\u0012\u0157\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005"+
		"\u0013\u0162\b\u0013\n\u0013\f\u0013\u0165\t\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0172\b\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u018e\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0003\u0016\u0194\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0005\u0017\u019f\b\u0017\n\u0017\f\u0017\u01a2\t\u0017\u0001\u0017\u0000"+
		"\u0007\u0002\n\u0014\u0016$&.\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.\u0000\u0000"+
		"\u01b7\u00000\u0001\u0000\u0000\u0000\u00023\u0001\u0000\u0000\u0000\u0004"+
		"I\u0001\u0000\u0000\u0000\u0006\\\u0001\u0000\u0000\u0000\bo\u0001\u0000"+
		"\u0000\u0000\ny\u0001\u0000\u0000\u0000\f\u0094\u0001\u0000\u0000\u0000"+
		"\u000e\u00a0\u0001\u0000\u0000\u0000\u0010\u00a2\u0001\u0000\u0000\u0000"+
		"\u0012\u00aa\u0001\u0000\u0000\u0000\u0014\u00b4\u0001\u0000\u0000\u0000"+
		"\u0016\u00c6\u0001\u0000\u0000\u0000\u0018\u0118\u0001\u0000\u0000\u0000"+
		"\u001a\u0120\u0001\u0000\u0000\u0000\u001c\u012a\u0001\u0000\u0000\u0000"+
		"\u001e\u0134\u0001\u0000\u0000\u0000 \u013e\u0001\u0000\u0000\u0000\""+
		"\u0148\u0001\u0000\u0000\u0000$\u014a\u0001\u0000\u0000\u0000&\u0158\u0001"+
		"\u0000\u0000\u0000(\u0171\u0001\u0000\u0000\u0000*\u018d\u0001\u0000\u0000"+
		"\u0000,\u0193\u0001\u0000\u0000\u0000.\u0195\u0001\u0000\u0000\u00000"+
		"1\u0003\u0002\u0001\u000012\u0006\u0000\uffff\uffff\u00002\u0001\u0001"+
		"\u0000\u0000\u000034\u0006\u0001\uffff\uffff\u000045\u0003\u0004\u0002"+
		"\u000056\u0006\u0001\uffff\uffff\u00006=\u0001\u0000\u0000\u000078\n\u0002"+
		"\u0000\u000089\u0003\u0004\u0002\u00009:\u0006\u0001\uffff\uffff\u0000"+
		":<\u0001\u0000\u0000\u0000;7\u0001\u0000\u0000\u0000<?\u0001\u0000\u0000"+
		"\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>\u0003\u0001"+
		"\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@A\u0003\u000e\u0007\u0000"+
		"AB\u0006\u0002\uffff\uffff\u0000BJ\u0001\u0000\u0000\u0000CD\u0003\u0006"+
		"\u0003\u0000DE\u0006\u0002\uffff\uffff\u0000EJ\u0001\u0000\u0000\u0000"+
		"FG\u0003\b\u0004\u0000GH\u0006\u0002\uffff\uffff\u0000HJ\u0001\u0000\u0000"+
		"\u0000I@\u0001\u0000\u0000\u0000IC\u0001\u0000\u0000\u0000IF\u0001\u0000"+
		"\u0000\u0000J\u0005\u0001\u0000\u0000\u0000KL\u0003\u0012\t\u0000LM\u0005"+
		"#\u0000\u0000MN\u0005\u0012\u0000\u0000NO\u0006\u0003\uffff\uffff\u0000"+
		"OP\u0003\n\u0005\u0000PQ\u0005\u0013\u0000\u0000QR\u0006\u0003\uffff\uffff"+
		"\u0000RS\u0005\u0018\u0000\u0000ST\u0006\u0003\uffff\uffff\u0000T]\u0001"+
		"\u0000\u0000\u0000UV\u0003\u0012\t\u0000VW\u0005#\u0000\u0000WX\u0005"+
		"\u0012\u0000\u0000XY\u0005\u0013\u0000\u0000YZ\u0005\u0018\u0000\u0000"+
		"Z[\u0006\u0003\uffff\uffff\u0000[]\u0001\u0000\u0000\u0000\\K\u0001\u0000"+
		"\u0000\u0000\\U\u0001\u0000\u0000\u0000]\u0007\u0001\u0000\u0000\u0000"+
		"^_\u0003\u0012\t\u0000_`\u0005#\u0000\u0000`a\u0005\u0012\u0000\u0000"+
		"ab\u0006\u0004\uffff\uffff\u0000bc\u0003\n\u0005\u0000cd\u0005\u0013\u0000"+
		"\u0000de\u0003\f\u0006\u0000ef\u0006\u0004\uffff\uffff\u0000fp\u0001\u0000"+
		"\u0000\u0000gh\u0003\u0012\t\u0000hi\u0005#\u0000\u0000ij\u0005\u0012"+
		"\u0000\u0000jk\u0006\u0004\uffff\uffff\u0000kl\u0005\u0013\u0000\u0000"+
		"lm\u0003\f\u0006\u0000mn\u0006\u0004\uffff\uffff\u0000np\u0001\u0000\u0000"+
		"\u0000o^\u0001\u0000\u0000\u0000og\u0001\u0000\u0000\u0000p\t\u0001\u0000"+
		"\u0000\u0000qr\u0006\u0005\uffff\uffff\u0000rs\u0003\u0012\t\u0000st\u0005"+
		"#\u0000\u0000tu\u0006\u0005\uffff\uffff\u0000uz\u0001\u0000\u0000\u0000"+
		"vw\u0003\u0012\t\u0000wx\u0006\u0005\uffff\uffff\u0000xz\u0001\u0000\u0000"+
		"\u0000yq\u0001\u0000\u0000\u0000yv\u0001\u0000\u0000\u0000z\u0088\u0001"+
		"\u0000\u0000\u0000{|\n\u0004\u0000\u0000|}\u0005\u0019\u0000\u0000}~\u0003"+
		"\u0012\t\u0000~\u007f\u0005#\u0000\u0000\u007f\u0080\u0006\u0005\uffff"+
		"\uffff\u0000\u0080\u0087\u0001\u0000\u0000\u0000\u0081\u0082\n\u0003\u0000"+
		"\u0000\u0082\u0083\u0005\u0019\u0000\u0000\u0083\u0084\u0003\u0012\t\u0000"+
		"\u0084\u0085\u0006\u0005\uffff\uffff\u0000\u0085\u0087\u0001\u0000\u0000"+
		"\u0000\u0086{\u0001\u0000\u0000\u0000\u0086\u0081\u0001\u0000\u0000\u0000"+
		"\u0087\u008a\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u000b\u0001\u0000\u0000\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008b\u008c\u0005\u0014\u0000\u0000"+
		"\u008c\u008d\u0006\u0006\uffff\uffff\u0000\u008d\u008e\u0003\u0016\u000b"+
		"\u0000\u008e\u008f\u0005\u0015\u0000\u0000\u008f\u0090\u0006\u0006\uffff"+
		"\uffff\u0000\u0090\u0095\u0001\u0000\u0000\u0000\u0091\u0092\u0005\u0014"+
		"\u0000\u0000\u0092\u0093\u0005\u0015\u0000\u0000\u0093\u0095\u0006\u0006"+
		"\uffff\uffff\u0000\u0094\u008b\u0001\u0000\u0000\u0000\u0094\u0091\u0001"+
		"\u0000\u0000\u0000\u0095\r\u0001\u0000\u0000\u0000\u0096\u0097\u0003\u0012"+
		"\t\u0000\u0097\u0098\u0003\u0014\n\u0000\u0098\u0099\u0005\u0018\u0000"+
		"\u0000\u0099\u009a\u0006\u0007\uffff\uffff\u0000\u009a\u00a1\u0001\u0000"+
		"\u0000\u0000\u009b\u009c\u0003\u0012\t\u0000\u009c\u009d\u0003\u0010\b"+
		"\u0000\u009d\u009e\u0005\u0018\u0000\u0000\u009e\u009f\u0006\u0007\uffff"+
		"\uffff\u0000\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u0096\u0001\u0000"+
		"\u0000\u0000\u00a0\u009b\u0001\u0000\u0000\u0000\u00a1\u000f\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a3\u0006\b\uffff\uffff\u0000\u00a3\u0011\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0005\u000f\u0000\u0000\u00a5\u00ab\u0006\t\uffff"+
		"\uffff\u0000\u00a6\u00a7\u0005\u0010\u0000\u0000\u00a7\u00ab\u0006\t\uffff"+
		"\uffff\u0000\u00a8\u00a9\u0005\u0011\u0000\u0000\u00a9\u00ab\u0006\t\uffff"+
		"\uffff\u0000\u00aa\u00a4\u0001\u0000\u0000\u0000\u00aa\u00a6\u0001\u0000"+
		"\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab\u0013\u0001\u0000"+
		"\u0000\u0000\u00ac\u00ad\u0006\n\uffff\uffff\u0000\u00ad\u00ae\u0005#"+
		"\u0000\u0000\u00ae\u00b5\u0006\n\uffff\uffff\u0000\u00af\u00b0\u0005#"+
		"\u0000\u0000\u00b0\u00b1\u0005\u0016\u0000\u0000\u00b1\u00b2\u0005$\u0000"+
		"\u0000\u00b2\u00b3\u0005\u0017\u0000\u0000\u00b3\u00b5\u0006\n\uffff\uffff"+
		"\u0000\u00b4\u00ac\u0001\u0000\u0000\u0000\u00b4\u00af\u0001\u0000\u0000"+
		"\u0000\u00b5\u00c3\u0001\u0000\u0000\u0000\u00b6\u00b7\n\u0004\u0000\u0000"+
		"\u00b7\u00b8\u0005\u0019\u0000\u0000\u00b8\u00b9\u0005#\u0000\u0000\u00b9"+
		"\u00c2\u0006\n\uffff\uffff\u0000\u00ba\u00bb\n\u0003\u0000\u0000\u00bb"+
		"\u00bc\u0005\u0019\u0000\u0000\u00bc\u00bd\u0005#\u0000\u0000\u00bd\u00be"+
		"\u0005\u0016\u0000\u0000\u00be\u00bf\u0005$\u0000\u0000\u00bf\u00c0\u0005"+
		"\u0017\u0000\u0000\u00c0\u00c2\u0006\n\uffff\uffff\u0000\u00c1\u00b6\u0001"+
		"\u0000\u0000\u0000\u00c1\u00ba\u0001\u0000\u0000\u0000\u00c2\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c4\u0015\u0001\u0000\u0000\u0000\u00c5\u00c3\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0006\u000b\uffff\uffff\u0000\u00c7\u00c8"+
		"\u0003\u0018\f\u0000\u00c8\u00c9\u0006\u000b\uffff\uffff\u0000\u00c9\u00d0"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cb\n\u0001\u0000\u0000\u00cb\u00cc\u0003"+
		"\u0018\f\u0000\u00cc\u00cd\u0006\u000b\uffff\uffff\u0000\u00cd\u00cf\u0001"+
		"\u0000\u0000\u0000\u00ce\u00ca\u0001\u0000\u0000\u0000\u00cf\u00d2\u0001"+
		"\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d0\u00d1\u0001"+
		"\u0000\u0000\u0000\u00d1\u0017\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d4\u0003\u000e\u0007\u0000\u00d4\u00d5\u0006"+
		"\f\uffff\uffff\u0000\u00d5\u0119\u0001\u0000\u0000\u0000\u00d6\u00d7\u0003"+
		"\u001a\r\u0000\u00d7\u00d8\u0006\f\uffff\uffff\u0000\u00d8\u0119\u0001"+
		"\u0000\u0000\u0000\u00d9\u00da\u0003\f\u0006\u0000\u00da\u00db\u0006\f"+
		"\uffff\uffff\u0000\u00db\u0119\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005"+
		"\u000b\u0000\u0000\u00dd\u00de\u0005\u0012\u0000\u0000\u00de\u00df\u0003"+
		"\u001a\r\u0000\u00df\u00e0\u0003\u001a\r\u0000\u00e0\u00e1\u0003\u001e"+
		"\u000f\u0000\u00e1\u00e2\u0005\u0013\u0000\u0000\u00e2\u00e3\u0003\u0018"+
		"\f\u0000\u00e3\u00e4\u0006\f\uffff\uffff\u0000\u00e4\u0119\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e6\u0005\t\u0000\u0000\u00e6\u00e7\u0005\u0012\u0000"+
		"\u0000\u00e7\u00e8\u0003\u001e\u000f\u0000\u00e8\u00e9\u0005\u0013\u0000"+
		"\u0000\u00e9\u00ea\u0003\u0018\f\u0000\u00ea\u00eb\u0006\f\uffff\uffff"+
		"\u0000\u00eb\u0119\u0001\u0000\u0000\u0000\u00ec\u00ed\u0005\t\u0000\u0000"+
		"\u00ed\u00ee\u0005\u0012\u0000\u0000\u00ee\u00ef\u0003\u001e\u000f\u0000"+
		"\u00ef\u00f0\u0005\u0013\u0000\u0000\u00f0\u00f1\u0003\u0018\f\u0000\u00f1"+
		"\u00f2\u0005\n\u0000\u0000\u00f2\u00f3\u0003\u0018\f\u0000\u00f3\u00f4"+
		"\u0006\f\uffff\uffff\u0000\u00f4\u0119\u0001\u0000\u0000\u0000\u00f5\u00f6"+
		"\u0005\f\u0000\u0000\u00f6\u00f7\u0005\u0012\u0000\u0000\u00f7\u00f8\u0003"+
		"\u001e\u000f\u0000\u00f8\u00f9\u0005\u0013\u0000\u0000\u00f9\u00fa\u0003"+
		"\u0018\f\u0000\u00fa\u00fb\u0006\f\uffff\uffff\u0000\u00fb\u0119\u0001"+
		"\u0000\u0000\u0000\u00fc\u00fd\u0005\r\u0000\u0000\u00fd\u00fe\u0005\u0012"+
		"\u0000\u0000\u00fe\u00ff\u0005#\u0000\u0000\u00ff\u0100\u0005\u0013\u0000"+
		"\u0000\u0100\u0101\u0005\u0018\u0000\u0000\u0101\u0119\u0006\f\uffff\uffff"+
		"\u0000\u0102\u0103\u0005\u000e\u0000\u0000\u0103\u0104\u0003\u001e\u000f"+
		"\u0000\u0104\u0105\u0005\u0018\u0000\u0000\u0105\u0106\u0006\f\uffff\uffff"+
		"\u0000\u0106\u0119\u0001\u0000\u0000\u0000\u0107\u0108\u0005\u0006\u0000"+
		"\u0000\u0108\u0109\u0005\u0014\u0000\u0000\u0109\u010a\u0003\u0018\f\u0000"+
		"\u010a\u010b\u0005\u0015\u0000\u0000\u010b\u010c\u0005\f\u0000\u0000\u010c"+
		"\u010d\u0005\u0012\u0000\u0000\u010d\u010e\u0003\u001e\u000f\u0000\u010e"+
		"\u010f\u0005\u0013\u0000\u0000\u010f\u0110\u0005\u0018\u0000\u0000\u0110"+
		"\u0111\u0006\f\uffff\uffff\u0000\u0111\u0119\u0001\u0000\u0000\u0000\u0112"+
		"\u0113\u0005\u0007\u0000\u0000\u0113\u0114\u0005\u0018\u0000\u0000\u0114"+
		"\u0119\u0006\f\uffff\uffff\u0000\u0115\u0116\u0005\b\u0000\u0000\u0116"+
		"\u0117\u0005\u0018\u0000\u0000\u0117\u0119\u0006\f\uffff\uffff\u0000\u0118"+
		"\u00d3\u0001\u0000\u0000\u0000\u0118\u00d6\u0001\u0000\u0000\u0000\u0118"+
		"\u00d9\u0001\u0000\u0000\u0000\u0118\u00dc\u0001\u0000\u0000\u0000\u0118"+
		"\u00e5\u0001\u0000\u0000\u0000\u0118\u00ec\u0001\u0000\u0000\u0000\u0118"+
		"\u00f5\u0001\u0000\u0000\u0000\u0118\u00fc\u0001\u0000\u0000\u0000\u0118"+
		"\u0102\u0001\u0000\u0000\u0000\u0118\u0107\u0001\u0000\u0000\u0000\u0118"+
		"\u0112\u0001\u0000\u0000\u0000\u0118\u0115\u0001\u0000\u0000\u0000\u0119"+
		"\u0019\u0001\u0000\u0000\u0000\u011a\u011b\u0005\u0018\u0000\u0000\u011b"+
		"\u0121\u0006\r\uffff\uffff\u0000\u011c\u011d\u0003\u001e\u000f\u0000\u011d"+
		"\u011e\u0005\u0018\u0000\u0000\u011e\u011f\u0006\r\uffff\uffff\u0000\u011f"+
		"\u0121\u0001\u0000\u0000\u0000\u0120\u011a\u0001\u0000\u0000\u0000\u0120"+
		"\u011c\u0001\u0000\u0000\u0000\u0121\u001b\u0001\u0000\u0000\u0000\u0122"+
		"\u0123\u0005#\u0000\u0000\u0123\u012b\u0006\u000e\uffff\uffff\u0000\u0124"+
		"\u0125\u0005#\u0000\u0000\u0125\u0126\u0005\u0016\u0000\u0000\u0126\u0127"+
		"\u0003\u001e\u000f\u0000\u0127\u0128\u0005\u0017\u0000\u0000\u0128\u0129"+
		"\u0006\u000e\uffff\uffff\u0000\u0129\u012b\u0001\u0000\u0000\u0000\u012a"+
		"\u0122\u0001\u0000\u0000\u0000\u012a\u0124\u0001\u0000\u0000\u0000\u012b"+
		"\u001d\u0001\u0000\u0000\u0000\u012c\u012d\u0003 \u0010\u0000\u012d\u012e"+
		"\u0006\u000f\uffff\uffff\u0000\u012e\u0135\u0001\u0000\u0000\u0000\u012f"+
		"\u0130\u0003\u001c\u000e\u0000\u0130\u0131\u0005\"\u0000\u0000\u0131\u0132"+
		"\u0003 \u0010\u0000\u0132\u0133\u0006\u000f\uffff\uffff\u0000\u0133\u0135"+
		"\u0001\u0000\u0000\u0000\u0134\u012c\u0001\u0000\u0000\u0000\u0134\u012f"+
		"\u0001\u0000\u0000\u0000\u0135\u001f\u0001\u0000\u0000\u0000\u0136\u0137"+
		"\u0003\"\u0011\u0000\u0137\u0138\u0006\u0010\uffff\uffff\u0000\u0138\u013f"+
		"\u0001\u0000\u0000\u0000\u0139\u013a\u0003\"\u0011\u0000\u013a\u013b\u0005"+
		"!\u0000\u0000\u013b\u013c\u0003\"\u0011\u0000\u013c\u013d\u0006\u0010"+
		"\uffff\uffff\u0000\u013d\u013f\u0001\u0000\u0000\u0000\u013e\u0136\u0001"+
		"\u0000\u0000\u0000\u013e\u0139\u0001\u0000\u0000\u0000\u013f!\u0001\u0000"+
		"\u0000\u0000\u0140\u0141\u0003$\u0012\u0000\u0141\u0142\u0006\u0011\uffff"+
		"\uffff\u0000\u0142\u0149\u0001\u0000\u0000\u0000\u0143\u0144\u0003$\u0012"+
		"\u0000\u0144\u0145\u0005 \u0000\u0000\u0145\u0146\u0003$\u0012\u0000\u0146"+
		"\u0147\u0006\u0011\uffff\uffff\u0000\u0147\u0149\u0001\u0000\u0000\u0000"+
		"\u0148\u0140\u0001\u0000\u0000\u0000\u0148\u0143\u0001\u0000\u0000\u0000"+
		"\u0149#\u0001\u0000\u0000\u0000\u014a\u014b\u0006\u0012\uffff\uffff\u0000"+
		"\u014b\u014c\u0003&\u0013\u0000\u014c\u014d\u0006\u0012\uffff\uffff\u0000"+
		"\u014d\u0155\u0001\u0000\u0000\u0000\u014e\u014f\n\u0001\u0000\u0000\u014f"+
		"\u0150\u0005\u001a\u0000\u0000\u0150\u0151\u0003&\u0013\u0000\u0151\u0152"+
		"\u0006\u0012\uffff\uffff\u0000\u0152\u0154\u0001\u0000\u0000\u0000\u0153"+
		"\u014e\u0001\u0000\u0000\u0000\u0154\u0157\u0001\u0000\u0000\u0000\u0155"+
		"\u0153\u0001\u0000\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156"+
		"%\u0001\u0000\u0000\u0000\u0157\u0155\u0001\u0000\u0000\u0000\u0158\u0159"+
		"\u0006\u0013\uffff\uffff\u0000\u0159\u015a\u0003(\u0014\u0000\u015a\u015b"+
		"\u0006\u0013\uffff\uffff\u0000\u015b\u0163\u0001\u0000\u0000\u0000\u015c"+
		"\u015d\n\u0001\u0000\u0000\u015d\u015e\u0005\u001c\u0000\u0000\u015e\u015f"+
		"\u0003(\u0014\u0000\u015f\u0160\u0006\u0013\uffff\uffff\u0000\u0160\u0162"+
		"\u0001\u0000\u0000\u0000\u0161\u015c\u0001\u0000\u0000\u0000\u0162\u0165"+
		"\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0163\u0164"+
		"\u0001\u0000\u0000\u0000\u0164\'\u0001\u0000\u0000\u0000\u0165\u0163\u0001"+
		"\u0000\u0000\u0000\u0166\u0167\u0005\u001a\u0000\u0000\u0167\u0168\u0003"+
		"(\u0014\u0000\u0168\u0169\u0006\u0014\uffff\uffff\u0000\u0169\u0172\u0001"+
		"\u0000\u0000\u0000\u016a\u016b\u0005\u001f\u0000\u0000\u016b\u016c\u0003"+
		"(\u0014\u0000\u016c\u016d\u0006\u0014\uffff\uffff\u0000\u016d\u0172\u0001"+
		"\u0000\u0000\u0000\u016e\u016f\u0003*\u0015\u0000\u016f\u0170\u0006\u0014"+
		"\uffff\uffff\u0000\u0170\u0172\u0001\u0000\u0000\u0000\u0171\u0166\u0001"+
		"\u0000\u0000\u0000\u0171\u016a\u0001\u0000\u0000\u0000\u0171\u016e\u0001"+
		"\u0000\u0000\u0000\u0172)\u0001\u0000\u0000\u0000\u0173\u0174\u0003\u001c"+
		"\u000e\u0000\u0174\u0175\u0006\u0015\uffff\uffff\u0000\u0175\u018e\u0001"+
		"\u0000\u0000\u0000\u0176\u0177\u0005#\u0000\u0000\u0177\u0178\u0005\u0012"+
		"\u0000\u0000\u0178\u0179\u0003,\u0016\u0000\u0179\u017a\u0005\u0013\u0000"+
		"\u0000\u017a\u017b\u0006\u0015\uffff\uffff\u0000\u017b\u018e\u0001\u0000"+
		"\u0000\u0000\u017c\u017d\u0005\u0012\u0000\u0000\u017d\u017e\u0003\u001e"+
		"\u000f\u0000\u017e\u017f\u0005\u0013\u0000\u0000\u017f\u0180\u0006\u0015"+
		"\uffff\uffff\u0000\u0180\u018e\u0001\u0000\u0000\u0000\u0181\u0182\u0005"+
		"$\u0000\u0000\u0182\u018e\u0006\u0015\uffff\uffff\u0000\u0183\u0184\u0005"+
		"%\u0000\u0000\u0184\u018e\u0006\u0015\uffff\uffff\u0000\u0185\u0186\u0003"+
		"\u001c\u000e\u0000\u0186\u0187\u0005\u001d\u0000\u0000\u0187\u0188\u0006"+
		"\u0015\uffff\uffff\u0000\u0188\u018e\u0001\u0000\u0000\u0000\u0189\u018a"+
		"\u0003\u001c\u000e\u0000\u018a\u018b\u0005\u001e\u0000\u0000\u018b\u018c"+
		"\u0006\u0015\uffff\uffff\u0000\u018c\u018e\u0001\u0000\u0000\u0000\u018d"+
		"\u0173\u0001\u0000\u0000\u0000\u018d\u0176\u0001\u0000\u0000\u0000\u018d"+
		"\u017c\u0001\u0000\u0000\u0000\u018d\u0181\u0001\u0000\u0000\u0000\u018d"+
		"\u0183\u0001\u0000\u0000\u0000\u018d\u0185\u0001\u0000\u0000\u0000\u018d"+
		"\u0189\u0001\u0000\u0000\u0000\u018e+\u0001\u0000\u0000\u0000\u018f\u0190"+
		"\u0003.\u0017\u0000\u0190\u0191\u0006\u0016\uffff\uffff\u0000\u0191\u0194"+
		"\u0001\u0000\u0000\u0000\u0192\u0194\u0001\u0000\u0000\u0000\u0193\u018f"+
		"\u0001\u0000\u0000\u0000\u0193\u0192\u0001\u0000\u0000\u0000\u0194-\u0001"+
		"\u0000\u0000\u0000\u0195\u0196\u0006\u0017\uffff\uffff\u0000\u0196\u0197"+
		"\u0003 \u0010\u0000\u0197\u0198\u0006\u0017\uffff\uffff\u0000\u0198\u01a0"+
		"\u0001\u0000\u0000\u0000\u0199\u019a\n\u0002\u0000\u0000\u019a\u019b\u0005"+
		"\u0019\u0000\u0000\u019b\u019c\u0003 \u0010\u0000\u019c\u019d\u0006\u0017"+
		"\uffff\uffff\u0000\u019d\u019f\u0001\u0000\u0000\u0000\u019e\u0199\u0001"+
		"\u0000\u0000\u0000\u019f\u01a2\u0001\u0000\u0000\u0000\u01a0\u019e\u0001"+
		"\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1/\u0001\u0000"+
		"\u0000\u0000\u01a2\u01a0\u0001\u0000\u0000\u0000\u001a=I\\oy\u0086\u0088"+
		"\u0094\u00a0\u00aa\u00b4\u00c1\u00c3\u00d0\u0118\u0120\u012a\u0134\u013e"+
		"\u0148\u0155\u0163\u0171\u018d\u0193\u01a0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}