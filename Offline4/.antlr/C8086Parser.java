// Generated from /media/saon/New Volume/3-1/CSE310/Offline4/C8086Parser.g4 by ANTLR 4.13.1

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
			setState(51);
			unit();
			}
			_ctx.stop = _input.LT(-1);
			setState(57);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ProgramContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_program);
					setState(53);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(54);
					unit();
					}
					} 
				}
				setState(59);
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
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				var_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				func_declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				func_definition();
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
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				type_specifier();
				setState(66);
				match(ID);
				setState(67);
				match(LPAREN);
				 st.EnterScope(); 
				setState(69);
				parameter_list(0);
				setState(70);
				match(RPAREN);
				 st.ExitScope(); 
				setState(72);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				type_specifier();
				setState(75);
				match(ID);
				setState(76);
				match(LPAREN);
				setState(77);
				match(RPAREN);
				setState(78);
				match(SEMICOLON);
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
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				((Func_definitionContext)_localctx).type_specifier = type_specifier();
				setState(83);
				((Func_definitionContext)_localctx).ID = match(ID);
				setState(84);
				match(LPAREN);
				 
				        SymbolInfo si  = SymbolInfo(((Func_definitionContext)_localctx).ID->getText(), "ID" , false, false, true, false, 0);
				        st.Insert(si);
				        st.EnterScope(); scope_flag = 1; currentOffset = 0; numParameters = 0; inFunction = 1; isVoidFunction = (((Func_definitionContext)_localctx).type_specifier.type.get_variables()[0] == "void") ? 1 : 0;

				        tempAsm << ((Func_definitionContext)_localctx).ID->getText() << " PROC" << std::endl;
				        if(((Func_definitionContext)_localctx).ID->getText() == "main") {
				            tempAsm << "\tMOV AX, @DATA" << std::endl;
				            tempAsm << "\tMOV DS, AX" << std::endl;
				        }
				        tempAsm << "\tPUSH BP" << std::endl;
				        tempAsm << "\tMOV BP, SP" << std::endl;

				     
				setState(86);
				((Func_definitionContext)_localctx).parameter_list = parameter_list(0);

				        std::vector<std::string> params = ((Func_definitionContext)_localctx).parameter_list.param_list.get_variables();
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
				      
				setState(88);
				match(RPAREN);
				setState(89);
				compound_statement();

				        if(((Func_definitionContext)_localctx).ID->getText() == "main"){
				            currentOffset -= 2; // main function has no parameters, so we can reduce the offset by 2
				            // tempAsm << "\tPOP AX" << std::endl;
				            for (const auto& label : globalReturnLabels) {
				                tempAsm << label << ":" << std::endl;
				            }
				            tempAsm << "\tADD SP, " << currentOffset << std::endl;
				            tempAsm << "\tPOP BP" << std::endl;
				            tempAsm << "\tMOV AX, 4CH" << std::endl;
				            tempAsm << "\tINT 21H" << std::endl;
				            tempAsm << ((Func_definitionContext)_localctx).ID->getText() << " ENDP" << std::endl;
				        }
				        else {
				            for (const auto& label : globalReturnLabels) {
				                tempAsm << label << ":" << std::endl;
				            }
				            globalReturnLabels.clear();
				            tempAsm << "\tADD SP, " << currentOffset << std::endl;
				            tempAsm << "\tPOP BP" << std::endl;
				            tempAsm << "\tRET " << (numParameters * 2) << std::endl; // Return from function, cleaning up parameters
				            tempAsm << ((Func_definitionContext)_localctx).ID->getText() << " ENDP" << std::endl;
				        }
				      
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				((Func_definitionContext)_localctx).type_specifier = type_specifier();
				setState(93);
				((Func_definitionContext)_localctx).ID = match(ID);
				setState(94);
				match(LPAREN);
				 
				        SymbolInfo si  = SymbolInfo(((Func_definitionContext)_localctx).ID->getText(), "ID" , false, false,true, false, 0);
				        st.Insert(si);
				        st.EnterScope(); scope_flag = 1; currentOffset = 0; numParameters = 0; inFunction = 1; isVoidFunction = (((Func_definitionContext)_localctx).type_specifier.type.get_variables()[0] == "void") ? 1 : 0;

				        tempAsm << ((Func_definitionContext)_localctx).ID->getText() << " PROC" << std::endl;
				        if(((Func_definitionContext)_localctx).ID->getText() == "main") {
				            tempAsm << "\tMOV AX, @DATA" << std::endl;
				            tempAsm << "\tMOV DS, AX" << std::endl;
				        }
				        tempAsm << "\tPUSH BP" << std::endl;
				        tempAsm << "\tMOV BP, SP" << std::endl;
				     
				setState(96);
				match(RPAREN);
				setState(97);
				compound_statement();

				        if(((Func_definitionContext)_localctx).ID->getText() == "main"){
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
				            tempAsm << ((Func_definitionContext)_localctx).ID->getText() << " ENDP" << std::endl;
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
				            tempAsm << ((Func_definitionContext)_localctx).ID->getText() << " ENDP" << std::endl;
				        }
				      
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
		public str_list param_list;
		public Parameter_listContext pl;
		public Token ID;
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
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(103);
				type_specifier();
				setState(104);
				((Parameter_listContext)_localctx).ID = match(ID);

				        _localctx.param_list.add(((Parameter_listContext)_localctx).ID->getText());
				        cout<<"Parameter: " << ((Parameter_listContext)_localctx).ID->getText() << std::endl;
				     
				}
				break;
			case 2:
				{
				setState(107);
				type_specifier();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(119);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						_localctx = new Parameter_listContext(_parentctx, _parentState);
						_localctx.pl = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_parameter_list);
						setState(110);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(111);
						match(COMMA);
						setState(112);
						type_specifier();
						setState(113);
						((Parameter_listContext)_localctx).ID = match(ID);

						                  std::vector<std::string> params = ((Parameter_listContext)_localctx).pl.param_list.get_variables();
						                  _localctx.param_list.set_variables(params);
						                  _localctx.param_list.add(((Parameter_listContext)_localctx).ID->getText());
						                  cout<<"Parameter "<<((Parameter_listContext)_localctx).ID->getText() << std::endl;
						               
						}
						break;
					case 2:
						{
						_localctx = new Parameter_listContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_parameter_list);
						setState(116);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(117);
						match(COMMA);
						setState(118);
						type_specifier();
						}
						break;
					}
					} 
				}
				setState(123);
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
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				match(LCURL);
				 
				            if(scope_flag == 0) st.EnterScope(); 
				            scope_flag = 0;
				        
				setState(126);
				statements(0);
				setState(127);
				match(RCURL);
				 
				            // cout<< "In Function: " << inFunction << std::endl;
				            // if(inFunction == 1){
				            //     tempAsm << "\tPOP AX" << std::endl;
				            //     inFunction = 0;
				            //  }
				            st.ExitScope();
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(LCURL);
				setState(131);
				match(RCURL);
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
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				type_specifier();
				setState(135);
				declaration_list(0);
				setState(136);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				type_specifier();
				setState(139);
				declaration_list_err();
				setState(140);
				match(SEMICOLON);
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
		public str_list type;
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
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				match(INT);

				    _localctx.type.add("INT");
				 
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				match(FLOAT);

				    _localctx.type.add("FLOAT");
				 
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				match(VOID);

				    _localctx.type.add("VOID");
				 
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
		public Token ID;
		public Token CONST_INT;
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode LTHIRD() { return getToken(C8086Parser.LTHIRD, 0); }
		public TerminalNode CONST_INT() { return getToken(C8086Parser.CONST_INT, 0); }
		public TerminalNode RTHIRD() { return getToken(C8086Parser.RTHIRD, 0); }
		public Declaration_listContext declaration_list() {
			return getRuleContext(Declaration_listContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(C8086Parser.COMMA, 0); }
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
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(155);
				((Declaration_listContext)_localctx).ID = match(ID);
				 
				        int currentScope = st.currentScopeId();
				        bool isGlobal  = false;
				        if(currentScope == 1){ isGlobal = true; }
				        SymbolInfo si = SymbolInfo(((Declaration_listContext)_localctx).ID->getText(), "ID", false, isGlobal, false, false, currentOffset+2);
				        if(!isGlobal){
				            currentOffset += 2;
				            tempAsm <<"\tSUB SP, 2" << std::endl;
				        }
				        else {
				            asmFile << "\t" << ((Declaration_listContext)_localctx).ID->getText() << " DW 1 DUP (0000H)" << std::endl;
				        }
				        st.Insert(si);
				     
				}
				break;
			case 2:
				{
				setState(157);
				((Declaration_listContext)_localctx).ID = match(ID);
				setState(158);
				match(LTHIRD);
				setState(159);
				((Declaration_listContext)_localctx).CONST_INT = match(CONST_INT);
				setState(160);
				match(RTHIRD);

				        cout<< "found " << ((Declaration_listContext)_localctx).ID->getText() <<" array" << std::endl;
				        int currentScope = st.currentScopeId();
				        bool isGlobal  = false;
				        if(currentScope == 1){ isGlobal = true; }
				        bool isArray = true;
				        int size = std::stoi(((Declaration_listContext)_localctx).CONST_INT->getText());
				        SymbolInfo si = SymbolInfo(((Declaration_listContext)_localctx).ID->getText(),"ID",isArray, isGlobal, false, false, currentOffset+size);
				        if(!isGlobal){
				            currentOffset += size;
				            tempAsm << "\tSUB SP, "<< size*2 << std::endl;
				        }
				        else{
				            asmFile << "\t" << ((Declaration_listContext)_localctx).ID->getText() << " DW " << size << " DUP (000H)" << std::endl;
				        }
				        st.Insert(si);
				     
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(177);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(175);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new Declaration_listContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_declaration_list);
						setState(164);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(165);
						match(COMMA);
						setState(166);
						((Declaration_listContext)_localctx).ID = match(ID);
						 

						                  int currentScope = st.currentScopeId();
						                  bool isGlobal  = false;
						                  if(currentScope == 1){ isGlobal = true; }
						                  SymbolInfo si = SymbolInfo(((Declaration_listContext)_localctx).ID->getText(), "ID", false, isGlobal, false,false, currentOffset+2);
						                  if(!isGlobal){
						                      currentOffset += 2;
						                      tempAsm <<"\tSUB SP, 2" << std::endl;
						                  }
						                  else {
						                      asmFile << "\t" << ((Declaration_listContext)_localctx).ID->getText() << " DW 1 DUP (0000H)" << std::endl;
						                  }
						                  st.Insert(si);

						              
						}
						break;
					case 2:
						{
						_localctx = new Declaration_listContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_declaration_list);
						setState(168);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(169);
						match(COMMA);
						setState(170);
						((Declaration_listContext)_localctx).ID = match(ID);
						setState(171);
						match(LTHIRD);
						setState(172);
						((Declaration_listContext)_localctx).CONST_INT = match(CONST_INT);
						setState(173);
						match(RTHIRD);
						 
						                  
						                  int currentScope = st.currentScopeId();
						                  bool isGlobal  = false;
						                  if(currentScope == 1){ isGlobal = true; }
						                  bool isArray = true;
						                  int size = std::stoi(((Declaration_listContext)_localctx).CONST_INT->getText());
						                  SymbolInfo si = SymbolInfo(((Declaration_listContext)_localctx).ID->getText(),"ID",isArray, isGlobal, false, false, currentOffset+size);
						                  if(!isGlobal){
						                      currentOffset += size;
						                      tempAsm << "\tSUB SP, "<< size*2 << std::endl;
						                   }
						                  else{
						                      asmFile << "\t" << ((Declaration_listContext)_localctx).ID->getText() << " DW " << size << " DUP (000H)" << std::endl;
						                   }
						                  st.Insert(si);
						              
						}
						break;
					}
					} 
				}
				setState(179);
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
			setState(181);
			statement();
			}
			_ctx.stop = _input.LT(-1);
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_statements);
					setState(183);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					 tempAsm << nextLabel() << ":" << std::endl; 
					setState(185);
					statement();
					}
					} 
				}
				setState(190);
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
		public Token ID;
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
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
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
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				var_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				expression_statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(193);
				compound_statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(194);
				match(FOR);
				setState(195);
				match(LPAREN);

				            std::string condLabel = nextLabel();
				            std::string incLabel = nextLabel();
				            std::string endLabel = nextLabel();
				            std::string trueLabel = nextLabel();
				        
				setState(197);
				expression_statement();

				            tempAsm << condLabel << ":" << std::endl;
				        
				setState(199);
				expression_statement();

				            tempAsm << "\tPUSH AX" << std::endl; // Push the condition result
				            tempAsm << "\tPOP AX" << std::endl;
				            tempAsm << "\tCMP AX, 0" << std::endl;
				            tempAsm << "\tJE " << endLabel << std::endl;
				            tempAsm << "\tJMP " << trueLabel << std::endl;
				            tempAsm << incLabel << ":" << std::endl;
				            
				        
				setState(201);
				expression();

				            tempAsm << "\tPOP AX" << std::endl;
				            tempAsm << "\tJMP " << condLabel << std::endl;
				         
				setState(203);
				match(RPAREN);

				            tempAsm << trueLabel << ":" << std::endl;
				         
				setState(205);
				statement();

				            tempAsm << "\tJMP " << incLabel << std::endl;
				            tempAsm << endLabel << ":" << std::endl;
				        
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(208);
				match(IF);
				setState(209);
				match(LPAREN);
				setState(210);
				expression();
				setState(211);
				match(RPAREN);

				        std::string elseLabel = nextLabel();
				        tempAsm << "\tPOP AX" << std::endl;
				        tempAsm << "\tCMP AX, 0" << std::endl;
				        tempAsm << "\tJE " << elseLabel << std::endl;
				     
				setState(213);
				statement();

				        tempAsm << elseLabel << ":" << std::endl;
				     
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(216);
				match(IF);
				setState(217);
				match(LPAREN);
				setState(218);
				expression();
				setState(219);
				match(RPAREN);

				        std::string elseLabel = nextLabel();
				        std::string endLabel = nextLabel();
				        tempAsm << "\tPOP AX" << std::endl;
				        tempAsm << "\tCMP AX, 0" << std::endl;
				        tempAsm << "\tJE " << elseLabel << std::endl;
				     
				setState(221);
				statement();

				        tempAsm << "\tJMP " << endLabel << std::endl;
				        tempAsm << elseLabel << ":" << std::endl;
				     
				setState(223);
				match(ELSE);
				setState(224);
				statement();

				        tempAsm << endLabel << ":" << std::endl;
				     
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(227);
				match(WHILE);
				setState(228);
				match(LPAREN);

				        std::string condLabel = nextLabel();
				        std::string endLabel = nextLabel();
				        tempAsm << condLabel << ":" << std::endl;
				     
				setState(230);
				expression();

				        tempAsm << "\tPOP AX" << std::endl;
				        tempAsm << "\tCMP AX, 0" << std::endl;
				        tempAsm << "\tJE " << endLabel << std::endl;
				      
				setState(232);
				match(RPAREN);
				setState(233);
				statement();

				        tempAsm << "\tJMP " << condLabel << std::endl;
				        tempAsm << endLabel << ":" << std::endl;
				       
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(236);
				match(PRINTLN);
				setState(237);
				match(LPAREN);
				setState(238);
				((StatementContext)_localctx).ID = match(ID);
				setState(239);
				match(RPAREN);
				setState(240);
				match(SEMICOLON);

				        SymbolInfo* si = st.LookUp(((StatementContext)_localctx).ID->getText());
				        if(si == nullptr) {
				            writeIntoErrorFile("Error: Variable " + ((StatementContext)_localctx).ID->getText() + " not declared.");
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
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(242);
				match(RETURN);
				setState(243);
				expression();
				setState(244);
				match(SEMICOLON);

				        globalReturnLabels.push_back(nextLabel());
				        tempAsm << "\tPOP AX" << std::endl;
				        tempAsm << "\tJMP " << globalReturnLabels.back() << std::endl;
				     
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(247);
				match(DO);
				setState(248);
				match(LCURL);
				setState(249);
				statements(0);
				setState(250);
				match(RCURL);
				setState(251);
				match(WHILE);
				setState(252);
				match(LPAREN);
				setState(253);
				expression();
				setState(254);
				match(RPAREN);
				setState(255);
				match(SEMICOLON);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(257);
				match(BREAK);
				setState(258);
				match(SEMICOLON);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(259);
				match(CONTINUE);
				setState(260);
				match(SEMICOLON);
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
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SEMICOLON:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				match(SEMICOLON);
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
				setState(264);
				expression();
				setState(265);
				match(SEMICOLON);

				        tempAsm << "\tPOP AX" << std::endl;
				 
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
		public Token ID;
		public TerminalNode ID() { return getToken(C8086Parser.ID, 0); }
		public TerminalNode LTHIRD() { return getToken(C8086Parser.LTHIRD, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RTHIRD() { return getToken(C8086Parser.RTHIRD, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variable);
		try {
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				((VariableContext)_localctx).ID = match(ID);
				 
				        _localctx.var_list.add(((VariableContext)_localctx).ID->getText());
				     
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				((VariableContext)_localctx).ID = match(ID);
				setState(273);
				match(LTHIRD);
				setState(274);
				expression();
				setState(275);
				match(RTHIRD);
				 
				        _localctx.var_list.add(((VariableContext)_localctx).ID->getText());
				     
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
		public VariableContext variable;
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
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				logic_expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				((ExpressionContext)_localctx).variable = variable();
				setState(282);
				match(ASSIGNOP);
				setState(283);
				logic_expression(0);
				 
				        tempAsm << "\tPOP AX" << std::endl; // result of logic_expression
				        SymbolInfo* si = st.LookUp(((ExpressionContext)_localctx).variable.var_list.get_variables()[0]);
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
		public Token LOGICOP;
		public Rel_expressionContext rel_expression() {
			return getRuleContext(Rel_expressionContext.class,0);
		}
		public Logic_expressionContext logic_expression() {
			return getRuleContext(Logic_expressionContext.class,0);
		}
		public TerminalNode LOGICOP() { return getToken(C8086Parser.LOGICOP, 0); }
		public Logic_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_expression; }
	}

	public final Logic_expressionContext logic_expression() throws RecognitionException {
		return logic_expression(0);
	}

	private Logic_expressionContext logic_expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Logic_expressionContext _localctx = new Logic_expressionContext(_ctx, _parentState);
		Logic_expressionContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_logic_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(289);
			rel_expression();
			}
			_ctx.stop = _input.LT(-1);
			setState(299);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Logic_expressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_logic_expression);
					setState(291);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(292);
					((Logic_expressionContext)_localctx).LOGICOP = match(LOGICOP);

					                  // Generate labels for short-circuiting
					                  std::string trueLabel = nextLabel();
					                  std::string falseLabel = nextLabel();
					                  std::string endLabel = nextLabel();
					                  
					                  if(((Logic_expressionContext)_localctx).LOGICOP->getText() == "&&") {
					                      tempAsm << "\tPOP AX" << std::endl;
					                      tempAsm << "\tCMP AX, 0" << std::endl;
					                      tempAsm << "\tJE " << falseLabel << std::endl;  // Short-circuit to false

					                  } else if(((Logic_expressionContext)_localctx).LOGICOP->getText() == "||") {
					                      tempAsm << "\tPOP AX" << std::endl;
					                      tempAsm << "\tCMP AX, 0" << std::endl;
					                      tempAsm << "\tJNE " << trueLabel << std::endl; // Short-circuit to true
					                  }
					                  
					              
					setState(294);
					rel_expression();


					                  if(((Logic_expressionContext)_localctx).LOGICOP->getText() == "&&") {
					                      tempAsm << "\tJMP " << endLabel << std::endl;
					                      tempAsm << falseLabel << ":" << std::endl;
					                      tempAsm << "\tPUSH 0" << std::endl; // Result is false
					                      tempAsm << endLabel << ":" << std::endl;
					                  } else if(((Logic_expressionContext)_localctx).LOGICOP->getText() == "||") { 
					                      tempAsm << "\tJMP " << endLabel << std::endl;
					                      tempAsm << trueLabel << ":" << std::endl;
					                      tempAsm << "\tPUSH 1" << std::endl; // Result is true
					                      tempAsm << endLabel << ":" << std::endl;
					                  }
					              
					}
					} 
				}
				setState(301);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
	public static class Rel_expressionContext extends ParserRuleContext {
		public Token RELOP;
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
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(302);
				simple_expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				simple_expression(0);
				setState(304);
				((Rel_expressionContext)_localctx).RELOP = match(RELOP);
				setState(305);
				simple_expression(0);

				        tempAsm << "\tPOP BX" << std::endl;
				        tempAsm << "\tPOP AX" << std::endl;
				        std::string trueLabel = nextLabel();
				        std::string endLabel = nextLabel();
				        if(((Rel_expressionContext)_localctx).RELOP->getText() == "<") {
				            tempAsm << "\tCMP AX, BX" << std::endl;
				            tempAsm << "\tJL " << trueLabel << std::endl;
				        } else if(((Rel_expressionContext)_localctx).RELOP->getText() == "<=") {
				            tempAsm << "\tCMP AX, BX" << std::endl;
				            tempAsm << "\tJLE " << trueLabel << std::endl;
				        } else if(((Rel_expressionContext)_localctx).RELOP->getText() == ">") {
				            tempAsm << "\tCMP AX, BX" << std::endl;
				            tempAsm << "\tJG " << trueLabel << std::endl;
				        } else if(((Rel_expressionContext)_localctx).RELOP->getText() == ">=") {
				            tempAsm << "\tCMP AX, BX" << std::endl;
				            tempAsm << "\tJGE " << trueLabel << std::endl;
				        } else if(((Rel_expressionContext)_localctx).RELOP->getText() == "==") {
				            tempAsm << "\tCMP AX, BX" << std::endl;
				            tempAsm << "\tJE " << trueLabel << std::endl;
				        } else if(((Rel_expressionContext)_localctx).RELOP->getText() == "!=") {
				            tempAsm << "\tCMP AX, BX" << std::endl;
				            tempAsm << "\tJNE " << trueLabel << std::endl;
				        }
				        tempAsm << "\tPUSH 0" << std::endl; // false
				        tempAsm << "\tJMP " << endLabel << std::endl;
				        tempAsm << trueLabel << ":" << std::endl;
				        tempAsm << "\tPUSH 1" << std::endl; // true
				        tempAsm << endLabel << ":" << std::endl;
				     
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
		public Token ADDOP;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Simple_expressionContext simple_expression() {
			return getRuleContext(Simple_expressionContext.class,0);
		}
		public TerminalNode ADDOP() { return getToken(C8086Parser.ADDOP, 0); }
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
			setState(311);
			term(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(320);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Simple_expressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_simple_expression);
					setState(313);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(314);
					((Simple_expressionContext)_localctx).ADDOP = match(ADDOP);
					setState(315);
					term(0);

					                  tempAsm << "\tPOP BX" << std::endl;
					                  tempAsm << "\tPOP AX" << std::endl;
					                  if(((Simple_expressionContext)_localctx).ADDOP->getText() == "+") {
					                      tempAsm << "\tADD AX, BX" << std::endl;
					                  } else if(((Simple_expressionContext)_localctx).ADDOP->getText() == "-") {
					                      tempAsm << "\tSUB AX, BX" << std::endl;
					                  }
					                  tempAsm << "\tPUSH AX" << std::endl;
					               
					}
					} 
				}
				setState(322);
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
		public Token MULOP;
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode MULOP() { return getToken(C8086Parser.MULOP, 0); }
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
			setState(324);
			unary_expression();
			}
			_ctx.stop = _input.LT(-1);
			setState(333);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TermContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_term);
					setState(326);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(327);
					((TermContext)_localctx).MULOP = match(MULOP);
					setState(328);
					unary_expression();

					                  tempAsm << "\tPOP BX" << std::endl;
					                  tempAsm << "\tPOP AX" << std::endl;
					                  if(((TermContext)_localctx).MULOP->getText() == "*") {
					                      tempAsm << "\tMUL BX" << std::endl;
					                  } else if(((TermContext)_localctx).MULOP->getText() == "/") {
					                      // tempAsm << "\tXCHG AX, BX" << std::endl;
					                      tempAsm << "\tDIV BX" << std::endl;
					                  } else if(((TermContext)_localctx).MULOP->getText() == "%") {
					                      // tempAsm << "\tXCHG AX, BX" << std::endl;
					                      tempAsm << "\tMOV DX, 0" << std::endl; // Clear DX before division
					                      tempAsm << "\tDIV BX" << std::endl;
					                      tempAsm << "\tMOV AX, DX" << std::endl; // Remainder in DX
					                  }
					                  tempAsm << "\tPUSH AX" << std::endl;
					               
					}
					} 
				}
				setState(335);
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
		public Token ADDOP;
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
			setState(345);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADDOP:
				enterOuterAlt(_localctx, 1);
				{
				setState(336);
				((Unary_expressionContext)_localctx).ADDOP = match(ADDOP);
				setState(337);
				unary_expression();

				        tempAsm << "\tPOP AX" << std::endl;
				        if(((Unary_expressionContext)_localctx).ADDOP->getText() == "+") {
				            tempAsm << "\tPUSH AX" << std::endl; // No change for +
				        } else if(((Unary_expressionContext)_localctx).ADDOP->getText() == "-") {
				            tempAsm << "\tNEG AX" << std::endl; // Negate for -
				            tempAsm << "\tPUSH AX" << std::endl;
				        }
				     
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
				match(NOT);
				setState(341);
				unary_expression();

				        tempAsm << "\tPOP AX" << std::endl;
				        tempAsm << "\tNOT AX" << std::endl;
				        tempAsm << "\tPUSH AX" << std::endl;
				     
				}
				break;
			case LPAREN:
			case ID:
			case CONST_INT:
			case CONST_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				factor();
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
		public VariableContext variable;
		public Token ID;
		public Token CONST_INT;
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
			setState(371);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(347);
				((FactorContext)_localctx).variable = variable();

				        SymbolInfo* si = st.LookUp(((FactorContext)_localctx).variable.var_list.get_variables()[0]);

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
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(350);
				((FactorContext)_localctx).ID = match(ID);
				setState(351);
				match(LPAREN);
				setState(352);
				argument_list();
				setState(353);
				match(RPAREN);

				        tempAsm << "\tCALL " << ((FactorContext)_localctx).ID->getText() << std::endl;
				        tempAsm << "\tPUSH AX" << std::endl;

				     
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(356);
				match(LPAREN);
				setState(357);
				expression();
				setState(358);
				match(RPAREN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(360);
				((FactorContext)_localctx).CONST_INT = match(CONST_INT);
				 
				        tempAsm << "\tPUSH " << ((FactorContext)_localctx).CONST_INT->getText() << std::endl;
				     
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(362);
				match(CONST_FLOAT);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(363);
				((FactorContext)_localctx).variable = variable();
				setState(364);
				match(INCOP);

				        // Post-increment: push original value, then increment variable
				        SymbolInfo* si = st.LookUp(((FactorContext)_localctx).variable.var_list.get_variables()[0]);

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
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(367);
				((FactorContext)_localctx).variable = variable();
				setState(368);
				match(DECOP);

				        // Post-decrement: push original value, then decrement variable
				        SymbolInfo* si = st.LookUp(((FactorContext)_localctx).variable.var_list.get_variables()[0]);
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
			setState(375);
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
				setState(373);
				arguments(0);
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
		public Logic_expressionContext logic_expression() {
			return getRuleContext(Logic_expressionContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(C8086Parser.COMMA, 0); }
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
			setState(378);
			logic_expression(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(385);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArgumentsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_arguments);
					setState(380);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(381);
					match(COMMA);
					setState(382);
					logic_expression(0);
					}
					} 
				}
				setState(387);
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
		case 16:
			return logic_expression_sempred((Logic_expressionContext)_localctx, predIndex);
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
	private boolean logic_expression_sempred(Logic_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean simple_expression_sempred(Simple_expressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean term_sempred(TermContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean arguments_sempred(ArgumentsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001%\u0185\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"8\b\u0001\n\u0001\f\u0001;\t\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002@\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"Q\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004e\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005m\b\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005x\b\u0005\n\u0005\f\u0005{\t\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006\u0085\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u008f\b\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u0099\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u00a3\b\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u00b0"+
		"\b\n\n\n\f\n\u00b3\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u00bb\b\u000b\n\u000b\f\u000b\u00be"+
		"\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u0106\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u010d\b\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u0117\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u011f\b\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u012a\b\u0010\n\u0010"+
		"\f\u0010\u012d\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u0135\b\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012\u013f\b\u0012\n\u0012\f\u0012\u0142\t\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0005\u0013\u014c\b\u0013\n\u0013\f\u0013\u014f\t\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u015a\b\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0174\b\u0015"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u0178\b\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0180\b\u0017"+
		"\n\u0017\f\u0017\u0183\t\u0017\u0001\u0017\u0000\b\u0002\n\u0014\u0016"+
		" $&.\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.\u0000\u0000\u0198\u00000\u0001\u0000"+
		"\u0000\u0000\u00022\u0001\u0000\u0000\u0000\u0004?\u0001\u0000\u0000\u0000"+
		"\u0006P\u0001\u0000\u0000\u0000\bd\u0001\u0000\u0000\u0000\nl\u0001\u0000"+
		"\u0000\u0000\f\u0084\u0001\u0000\u0000\u0000\u000e\u008e\u0001\u0000\u0000"+
		"\u0000\u0010\u0090\u0001\u0000\u0000\u0000\u0012\u0098\u0001\u0000\u0000"+
		"\u0000\u0014\u00a2\u0001\u0000\u0000\u0000\u0016\u00b4\u0001\u0000\u0000"+
		"\u0000\u0018\u0105\u0001\u0000\u0000\u0000\u001a\u010c\u0001\u0000\u0000"+
		"\u0000\u001c\u0116\u0001\u0000\u0000\u0000\u001e\u011e\u0001\u0000\u0000"+
		"\u0000 \u0120\u0001\u0000\u0000\u0000\"\u0134\u0001\u0000\u0000\u0000"+
		"$\u0136\u0001\u0000\u0000\u0000&\u0143\u0001\u0000\u0000\u0000(\u0159"+
		"\u0001\u0000\u0000\u0000*\u0173\u0001\u0000\u0000\u0000,\u0177\u0001\u0000"+
		"\u0000\u0000.\u0179\u0001\u0000\u0000\u000001\u0003\u0002\u0001\u0000"+
		"1\u0001\u0001\u0000\u0000\u000023\u0006\u0001\uffff\uffff\u000034\u0003"+
		"\u0004\u0002\u000049\u0001\u0000\u0000\u000056\n\u0002\u0000\u000068\u0003"+
		"\u0004\u0002\u000075\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u0000"+
		"97\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:\u0003\u0001\u0000"+
		"\u0000\u0000;9\u0001\u0000\u0000\u0000<@\u0003\u000e\u0007\u0000=@\u0003"+
		"\u0006\u0003\u0000>@\u0003\b\u0004\u0000?<\u0001\u0000\u0000\u0000?=\u0001"+
		"\u0000\u0000\u0000?>\u0001\u0000\u0000\u0000@\u0005\u0001\u0000\u0000"+
		"\u0000AB\u0003\u0012\t\u0000BC\u0005#\u0000\u0000CD\u0005\u0012\u0000"+
		"\u0000DE\u0006\u0003\uffff\uffff\u0000EF\u0003\n\u0005\u0000FG\u0005\u0013"+
		"\u0000\u0000GH\u0006\u0003\uffff\uffff\u0000HI\u0005\u0018\u0000\u0000"+
		"IQ\u0001\u0000\u0000\u0000JK\u0003\u0012\t\u0000KL\u0005#\u0000\u0000"+
		"LM\u0005\u0012\u0000\u0000MN\u0005\u0013\u0000\u0000NO\u0005\u0018\u0000"+
		"\u0000OQ\u0001\u0000\u0000\u0000PA\u0001\u0000\u0000\u0000PJ\u0001\u0000"+
		"\u0000\u0000Q\u0007\u0001\u0000\u0000\u0000RS\u0003\u0012\t\u0000ST\u0005"+
		"#\u0000\u0000TU\u0005\u0012\u0000\u0000UV\u0006\u0004\uffff\uffff\u0000"+
		"VW\u0003\n\u0005\u0000WX\u0006\u0004\uffff\uffff\u0000XY\u0005\u0013\u0000"+
		"\u0000YZ\u0003\f\u0006\u0000Z[\u0006\u0004\uffff\uffff\u0000[e\u0001\u0000"+
		"\u0000\u0000\\]\u0003\u0012\t\u0000]^\u0005#\u0000\u0000^_\u0005\u0012"+
		"\u0000\u0000_`\u0006\u0004\uffff\uffff\u0000`a\u0005\u0013\u0000\u0000"+
		"ab\u0003\f\u0006\u0000bc\u0006\u0004\uffff\uffff\u0000ce\u0001\u0000\u0000"+
		"\u0000dR\u0001\u0000\u0000\u0000d\\\u0001\u0000\u0000\u0000e\t\u0001\u0000"+
		"\u0000\u0000fg\u0006\u0005\uffff\uffff\u0000gh\u0003\u0012\t\u0000hi\u0005"+
		"#\u0000\u0000ij\u0006\u0005\uffff\uffff\u0000jm\u0001\u0000\u0000\u0000"+
		"km\u0003\u0012\t\u0000lf\u0001\u0000\u0000\u0000lk\u0001\u0000\u0000\u0000"+
		"my\u0001\u0000\u0000\u0000no\n\u0004\u0000\u0000op\u0005\u0019\u0000\u0000"+
		"pq\u0003\u0012\t\u0000qr\u0005#\u0000\u0000rs\u0006\u0005\uffff\uffff"+
		"\u0000sx\u0001\u0000\u0000\u0000tu\n\u0003\u0000\u0000uv\u0005\u0019\u0000"+
		"\u0000vx\u0003\u0012\t\u0000wn\u0001\u0000\u0000\u0000wt\u0001\u0000\u0000"+
		"\u0000x{\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000yz\u0001\u0000"+
		"\u0000\u0000z\u000b\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000"+
		"|}\u0005\u0014\u0000\u0000}~\u0006\u0006\uffff\uffff\u0000~\u007f\u0003"+
		"\u0016\u000b\u0000\u007f\u0080\u0005\u0015\u0000\u0000\u0080\u0081\u0006"+
		"\u0006\uffff\uffff\u0000\u0081\u0085\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0005\u0014\u0000\u0000\u0083\u0085\u0005\u0015\u0000\u0000\u0084|\u0001"+
		"\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0085\r\u0001\u0000"+
		"\u0000\u0000\u0086\u0087\u0003\u0012\t\u0000\u0087\u0088\u0003\u0014\n"+
		"\u0000\u0088\u0089\u0005\u0018\u0000\u0000\u0089\u008f\u0001\u0000\u0000"+
		"\u0000\u008a\u008b\u0003\u0012\t\u0000\u008b\u008c\u0003\u0010\b\u0000"+
		"\u008c\u008d\u0005\u0018\u0000\u0000\u008d\u008f\u0001\u0000\u0000\u0000"+
		"\u008e\u0086\u0001\u0000\u0000\u0000\u008e\u008a\u0001\u0000\u0000\u0000"+
		"\u008f\u000f\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000"+
		"\u0091\u0011\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u000f\u0000\u0000"+
		"\u0093\u0099\u0006\t\uffff\uffff\u0000\u0094\u0095\u0005\u0010\u0000\u0000"+
		"\u0095\u0099\u0006\t\uffff\uffff\u0000\u0096\u0097\u0005\u0011\u0000\u0000"+
		"\u0097\u0099\u0006\t\uffff\uffff\u0000\u0098\u0092\u0001\u0000\u0000\u0000"+
		"\u0098\u0094\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000"+
		"\u0099\u0013\u0001\u0000\u0000\u0000\u009a\u009b\u0006\n\uffff\uffff\u0000"+
		"\u009b\u009c\u0005#\u0000\u0000\u009c\u00a3\u0006\n\uffff\uffff\u0000"+
		"\u009d\u009e\u0005#\u0000\u0000\u009e\u009f\u0005\u0016\u0000\u0000\u009f"+
		"\u00a0\u0005$\u0000\u0000\u00a0\u00a1\u0005\u0017\u0000\u0000\u00a1\u00a3"+
		"\u0006\n\uffff\uffff\u0000\u00a2\u009a\u0001\u0000\u0000\u0000\u00a2\u009d"+
		"\u0001\u0000\u0000\u0000\u00a3\u00b1\u0001\u0000\u0000\u0000\u00a4\u00a5"+
		"\n\u0004\u0000\u0000\u00a5\u00a6\u0005\u0019\u0000\u0000\u00a6\u00a7\u0005"+
		"#\u0000\u0000\u00a7\u00b0\u0006\n\uffff\uffff\u0000\u00a8\u00a9\n\u0003"+
		"\u0000\u0000\u00a9\u00aa\u0005\u0019\u0000\u0000\u00aa\u00ab\u0005#\u0000"+
		"\u0000\u00ab\u00ac\u0005\u0016\u0000\u0000\u00ac\u00ad\u0005$\u0000\u0000"+
		"\u00ad\u00ae\u0005\u0017\u0000\u0000\u00ae\u00b0\u0006\n\uffff\uffff\u0000"+
		"\u00af\u00a4\u0001\u0000\u0000\u0000\u00af\u00a8\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b3\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u0015\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b4\u00b5\u0006\u000b\uffff\uffff"+
		"\u0000\u00b5\u00b6\u0003\u0018\f\u0000\u00b6\u00bc\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b8\n\u0001\u0000\u0000\u00b8\u00b9\u0006\u000b\uffff\uffff\u0000"+
		"\u00b9\u00bb\u0003\u0018\f\u0000\u00ba\u00b7\u0001\u0000\u0000\u0000\u00bb"+
		"\u00be\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc"+
		"\u00bd\u0001\u0000\u0000\u0000\u00bd\u0017\u0001\u0000\u0000\u0000\u00be"+
		"\u00bc\u0001\u0000\u0000\u0000\u00bf\u0106\u0003\u000e\u0007\u0000\u00c0"+
		"\u0106\u0003\u001a\r\u0000\u00c1\u0106\u0003\f\u0006\u0000\u00c2\u00c3"+
		"\u0005\u000b\u0000\u0000\u00c3\u00c4\u0005\u0012\u0000\u0000\u00c4\u00c5"+
		"\u0006\f\uffff\uffff\u0000\u00c5\u00c6\u0003\u001a\r\u0000\u00c6\u00c7"+
		"\u0006\f\uffff\uffff\u0000\u00c7\u00c8\u0003\u001a\r\u0000\u00c8\u00c9"+
		"\u0006\f\uffff\uffff\u0000\u00c9\u00ca\u0003\u001e\u000f\u0000\u00ca\u00cb"+
		"\u0006\f\uffff\uffff\u0000\u00cb\u00cc\u0005\u0013\u0000\u0000\u00cc\u00cd"+
		"\u0006\f\uffff\uffff\u0000\u00cd\u00ce\u0003\u0018\f\u0000\u00ce\u00cf"+
		"\u0006\f\uffff\uffff\u0000\u00cf\u0106\u0001\u0000\u0000\u0000\u00d0\u00d1"+
		"\u0005\t\u0000\u0000\u00d1\u00d2\u0005\u0012\u0000\u0000\u00d2\u00d3\u0003"+
		"\u001e\u000f\u0000\u00d3\u00d4\u0005\u0013\u0000\u0000\u00d4\u00d5\u0006"+
		"\f\uffff\uffff\u0000\u00d5\u00d6\u0003\u0018\f\u0000\u00d6\u00d7\u0006"+
		"\f\uffff\uffff\u0000\u00d7\u0106\u0001\u0000\u0000\u0000\u00d8\u00d9\u0005"+
		"\t\u0000\u0000\u00d9\u00da\u0005\u0012\u0000\u0000\u00da\u00db\u0003\u001e"+
		"\u000f\u0000\u00db\u00dc\u0005\u0013\u0000\u0000\u00dc\u00dd\u0006\f\uffff"+
		"\uffff\u0000\u00dd\u00de\u0003\u0018\f\u0000\u00de\u00df\u0006\f\uffff"+
		"\uffff\u0000\u00df\u00e0\u0005\n\u0000\u0000\u00e0\u00e1\u0003\u0018\f"+
		"\u0000\u00e1\u00e2\u0006\f\uffff\uffff\u0000\u00e2\u0106\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e4\u0005\f\u0000\u0000\u00e4\u00e5\u0005\u0012\u0000\u0000"+
		"\u00e5\u00e6\u0006\f\uffff\uffff\u0000\u00e6\u00e7\u0003\u001e\u000f\u0000"+
		"\u00e7\u00e8\u0006\f\uffff\uffff\u0000\u00e8\u00e9\u0005\u0013\u0000\u0000"+
		"\u00e9\u00ea\u0003\u0018\f\u0000\u00ea\u00eb\u0006\f\uffff\uffff\u0000"+
		"\u00eb\u0106\u0001\u0000\u0000\u0000\u00ec\u00ed\u0005\r\u0000\u0000\u00ed"+
		"\u00ee\u0005\u0012\u0000\u0000\u00ee\u00ef\u0005#\u0000\u0000\u00ef\u00f0"+
		"\u0005\u0013\u0000\u0000\u00f0\u00f1\u0005\u0018\u0000\u0000\u00f1\u0106"+
		"\u0006\f\uffff\uffff\u0000\u00f2\u00f3\u0005\u000e\u0000\u0000\u00f3\u00f4"+
		"\u0003\u001e\u000f\u0000\u00f4\u00f5\u0005\u0018\u0000\u0000\u00f5\u00f6"+
		"\u0006\f\uffff\uffff\u0000\u00f6\u0106\u0001\u0000\u0000\u0000\u00f7\u00f8"+
		"\u0005\u0006\u0000\u0000\u00f8\u00f9\u0005\u0014\u0000\u0000\u00f9\u00fa"+
		"\u0003\u0016\u000b\u0000\u00fa\u00fb\u0005\u0015\u0000\u0000\u00fb\u00fc"+
		"\u0005\f\u0000\u0000\u00fc\u00fd\u0005\u0012\u0000\u0000\u00fd\u00fe\u0003"+
		"\u001e\u000f\u0000\u00fe\u00ff\u0005\u0013\u0000\u0000\u00ff\u0100\u0005"+
		"\u0018\u0000\u0000\u0100\u0106\u0001\u0000\u0000\u0000\u0101\u0102\u0005"+
		"\u0007\u0000\u0000\u0102\u0106\u0005\u0018\u0000\u0000\u0103\u0104\u0005"+
		"\b\u0000\u0000\u0104\u0106\u0005\u0018\u0000\u0000\u0105\u00bf\u0001\u0000"+
		"\u0000\u0000\u0105\u00c0\u0001\u0000\u0000\u0000\u0105\u00c1\u0001\u0000"+
		"\u0000\u0000\u0105\u00c2\u0001\u0000\u0000\u0000\u0105\u00d0\u0001\u0000"+
		"\u0000\u0000\u0105\u00d8\u0001\u0000\u0000\u0000\u0105\u00e3\u0001\u0000"+
		"\u0000\u0000\u0105\u00ec\u0001\u0000\u0000\u0000\u0105\u00f2\u0001\u0000"+
		"\u0000\u0000\u0105\u00f7\u0001\u0000\u0000\u0000\u0105\u0101\u0001\u0000"+
		"\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0106\u0019\u0001\u0000"+
		"\u0000\u0000\u0107\u010d\u0005\u0018\u0000\u0000\u0108\u0109\u0003\u001e"+
		"\u000f\u0000\u0109\u010a\u0005\u0018\u0000\u0000\u010a\u010b\u0006\r\uffff"+
		"\uffff\u0000\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u0107\u0001\u0000"+
		"\u0000\u0000\u010c\u0108\u0001\u0000\u0000\u0000\u010d\u001b\u0001\u0000"+
		"\u0000\u0000\u010e\u010f\u0005#\u0000\u0000\u010f\u0117\u0006\u000e\uffff"+
		"\uffff\u0000\u0110\u0111\u0005#\u0000\u0000\u0111\u0112\u0005\u0016\u0000"+
		"\u0000\u0112\u0113\u0003\u001e\u000f\u0000\u0113\u0114\u0005\u0017\u0000"+
		"\u0000\u0114\u0115\u0006\u000e\uffff\uffff\u0000\u0115\u0117\u0001\u0000"+
		"\u0000\u0000\u0116\u010e\u0001\u0000\u0000\u0000\u0116\u0110\u0001\u0000"+
		"\u0000\u0000\u0117\u001d\u0001\u0000\u0000\u0000\u0118\u011f\u0003 \u0010"+
		"\u0000\u0119\u011a\u0003\u001c\u000e\u0000\u011a\u011b\u0005\"\u0000\u0000"+
		"\u011b\u011c\u0003 \u0010\u0000\u011c\u011d\u0006\u000f\uffff\uffff\u0000"+
		"\u011d\u011f\u0001\u0000\u0000\u0000\u011e\u0118\u0001\u0000\u0000\u0000"+
		"\u011e\u0119\u0001\u0000\u0000\u0000\u011f\u001f\u0001\u0000\u0000\u0000"+
		"\u0120\u0121\u0006\u0010\uffff\uffff\u0000\u0121\u0122\u0003\"\u0011\u0000"+
		"\u0122\u012b\u0001\u0000\u0000\u0000\u0123\u0124\n\u0001\u0000\u0000\u0124"+
		"\u0125\u0005!\u0000\u0000\u0125\u0126\u0006\u0010\uffff\uffff\u0000\u0126"+
		"\u0127\u0003\"\u0011\u0000\u0127\u0128\u0006\u0010\uffff\uffff\u0000\u0128"+
		"\u012a\u0001\u0000\u0000\u0000\u0129\u0123\u0001\u0000\u0000\u0000\u012a"+
		"\u012d\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000\u012b"+
		"\u012c\u0001\u0000\u0000\u0000\u012c!\u0001\u0000\u0000\u0000\u012d\u012b"+
		"\u0001\u0000\u0000\u0000\u012e\u0135\u0003$\u0012\u0000\u012f\u0130\u0003"+
		"$\u0012\u0000\u0130\u0131\u0005 \u0000\u0000\u0131\u0132\u0003$\u0012"+
		"\u0000\u0132\u0133\u0006\u0011\uffff\uffff\u0000\u0133\u0135\u0001\u0000"+
		"\u0000\u0000\u0134\u012e\u0001\u0000\u0000\u0000\u0134\u012f\u0001\u0000"+
		"\u0000\u0000\u0135#\u0001\u0000\u0000\u0000\u0136\u0137\u0006\u0012\uffff"+
		"\uffff\u0000\u0137\u0138\u0003&\u0013\u0000\u0138\u0140\u0001\u0000\u0000"+
		"\u0000\u0139\u013a\n\u0001\u0000\u0000\u013a\u013b\u0005\u001a\u0000\u0000"+
		"\u013b\u013c\u0003&\u0013\u0000\u013c\u013d\u0006\u0012\uffff\uffff\u0000"+
		"\u013d\u013f\u0001\u0000\u0000\u0000\u013e\u0139\u0001\u0000\u0000\u0000"+
		"\u013f\u0142\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000"+
		"\u0140\u0141\u0001\u0000\u0000\u0000\u0141%\u0001\u0000\u0000\u0000\u0142"+
		"\u0140\u0001\u0000\u0000\u0000\u0143\u0144\u0006\u0013\uffff\uffff\u0000"+
		"\u0144\u0145\u0003(\u0014\u0000\u0145\u014d\u0001\u0000\u0000\u0000\u0146"+
		"\u0147\n\u0001\u0000\u0000\u0147\u0148\u0005\u001c\u0000\u0000\u0148\u0149"+
		"\u0003(\u0014\u0000\u0149\u014a\u0006\u0013\uffff\uffff\u0000\u014a\u014c"+
		"\u0001\u0000\u0000\u0000\u014b\u0146\u0001\u0000\u0000\u0000\u014c\u014f"+
		"\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014d\u014e"+
		"\u0001\u0000\u0000\u0000\u014e\'\u0001\u0000\u0000\u0000\u014f\u014d\u0001"+
		"\u0000\u0000\u0000\u0150\u0151\u0005\u001a\u0000\u0000\u0151\u0152\u0003"+
		"(\u0014\u0000\u0152\u0153\u0006\u0014\uffff\uffff\u0000\u0153\u015a\u0001"+
		"\u0000\u0000\u0000\u0154\u0155\u0005\u001f\u0000\u0000\u0155\u0156\u0003"+
		"(\u0014\u0000\u0156\u0157\u0006\u0014\uffff\uffff\u0000\u0157\u015a\u0001"+
		"\u0000\u0000\u0000\u0158\u015a\u0003*\u0015\u0000\u0159\u0150\u0001\u0000"+
		"\u0000\u0000\u0159\u0154\u0001\u0000\u0000\u0000\u0159\u0158\u0001\u0000"+
		"\u0000\u0000\u015a)\u0001\u0000\u0000\u0000\u015b\u015c\u0003\u001c\u000e"+
		"\u0000\u015c\u015d\u0006\u0015\uffff\uffff\u0000\u015d\u0174\u0001\u0000"+
		"\u0000\u0000\u015e\u015f\u0005#\u0000\u0000\u015f\u0160\u0005\u0012\u0000"+
		"\u0000\u0160\u0161\u0003,\u0016\u0000\u0161\u0162\u0005\u0013\u0000\u0000"+
		"\u0162\u0163\u0006\u0015\uffff\uffff\u0000\u0163\u0174\u0001\u0000\u0000"+
		"\u0000\u0164\u0165\u0005\u0012\u0000\u0000\u0165\u0166\u0003\u001e\u000f"+
		"\u0000\u0166\u0167\u0005\u0013\u0000\u0000\u0167\u0174\u0001\u0000\u0000"+
		"\u0000\u0168\u0169\u0005$\u0000\u0000\u0169\u0174\u0006\u0015\uffff\uffff"+
		"\u0000\u016a\u0174\u0005%\u0000\u0000\u016b\u016c\u0003\u001c\u000e\u0000"+
		"\u016c\u016d\u0005\u001d\u0000\u0000\u016d\u016e\u0006\u0015\uffff\uffff"+
		"\u0000\u016e\u0174\u0001\u0000\u0000\u0000\u016f\u0170\u0003\u001c\u000e"+
		"\u0000\u0170\u0171\u0005\u001e\u0000\u0000\u0171\u0172\u0006\u0015\uffff"+
		"\uffff\u0000\u0172\u0174\u0001\u0000\u0000\u0000\u0173\u015b\u0001\u0000"+
		"\u0000\u0000\u0173\u015e\u0001\u0000\u0000\u0000\u0173\u0164\u0001\u0000"+
		"\u0000\u0000\u0173\u0168\u0001\u0000\u0000\u0000\u0173\u016a\u0001\u0000"+
		"\u0000\u0000\u0173\u016b\u0001\u0000\u0000\u0000\u0173\u016f\u0001\u0000"+
		"\u0000\u0000\u0174+\u0001\u0000\u0000\u0000\u0175\u0178\u0003.\u0017\u0000"+
		"\u0176\u0178\u0001\u0000\u0000\u0000\u0177\u0175\u0001\u0000\u0000\u0000"+
		"\u0177\u0176\u0001\u0000\u0000\u0000\u0178-\u0001\u0000\u0000\u0000\u0179"+
		"\u017a\u0006\u0017\uffff\uffff\u0000\u017a\u017b\u0003 \u0010\u0000\u017b"+
		"\u0181\u0001\u0000\u0000\u0000\u017c\u017d\n\u0002\u0000\u0000\u017d\u017e"+
		"\u0005\u0019\u0000\u0000\u017e\u0180\u0003 \u0010\u0000\u017f\u017c\u0001"+
		"\u0000\u0000\u0000\u0180\u0183\u0001\u0000\u0000\u0000\u0181\u017f\u0001"+
		"\u0000\u0000\u0000\u0181\u0182\u0001\u0000\u0000\u0000\u0182/\u0001\u0000"+
		"\u0000\u0000\u0183\u0181\u0001\u0000\u0000\u0000\u001a9?Pdlwy\u0084\u008e"+
		"\u0098\u00a2\u00af\u00b1\u00bc\u0105\u010c\u0116\u011e\u012b\u0134\u0140"+
		"\u014d\u0159\u0173\u0177\u0181";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}