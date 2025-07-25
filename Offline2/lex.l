%option noyywrap

%x CONSTANT_CHAR_STATE
%x STRING_STATE
%x SINGLE_LINE_COMMENT_STATE
%x MULTI_LINE_COMMENT_STATE

%{

#include<bits/stdc++.h>
#include "headers/2105110_SymbolTable.hpp"

using namespace std;

int line_count = 1;
int error_count = 0;

string current_const_char = "";
string current_string = "";
string current_single_line_comment = "";
string current_multi_line_comment = "";

string current_converted_text = "";

int current_string_line_count = 0;
int current_lexeme_start_line_number = 0;


ofstream tokenout("2105110_token.txt");
ofstream logout("2105110_log.txt");


int number_of_buckets = 7;

string convert_to_uppercase(string s)
{
	transform(s.begin(), s.end(), s.begin(), ::toupper);
    return s;
}

SymbolTable st = SymbolTable(number_of_buckets);



void write_token_to_file(string token, string lexeme)
{
    string out;

    if(token == lexeme){
        out = "<"+convert_to_uppercase(token)+">";
    }
    else out = "<"+token+", "+lexeme+">";

    tokenout<<out<<" ";
}

string escape_char_for_lexeme_display(char c) {
    switch(c) {
        case '\n': return "'\\n'";
        case '\t': return "'\\t'";
        case '\r': return "'\\r'";
        case '\0': return "'\\0'";
        case '\v': return "'\\v'";
        case '\b': return "'\\b'";
        case '\f': return "'\\f'";
        case '\a': return "'\\a'";
        case '\\': return "'\\\\'";
        case '\'': return "'\\\''";
        default: return "'" + string(1, c) + "'";
    }
}

char get_ascii_from_escape_sequence(string text)
{
	char ch = text[1];
	switch (ch)
	{
	case '0': return '\0';
	case 'v': return '\v';
	case 'b': return '\b';
	case 'r': return '\r';
	case 'f': return '\f';
	case 'a': return '\a';
	case '\'': return '\'';
	case '\\': return '\\';
	case 't': return '\t';
	case 'n': return '\n';
	case '\"': return '\"';
	default: return '#';
	}
}


void write_log_entry(string token, string lexeme)
{
    int line_number = line_count;
    if (token == "STRING" ||
        token == "COMMENT")
    {
        // line_number = current_lexeme_start_line_number;
    }



    logout << "Line no " << line_number << ": Token <" << convert_to_uppercase(token) << "> Lexeme ";

    if (token == "CONST_CHAR") {
        char ch = current_converted_text[1];
		string converted_text = current_converted_text.substr(1, current_converted_text.length() - 2);
        string escaped = escape_char_for_lexeme_display(ch);
        logout << escaped << " found --> <CONST_CHAR, " << converted_text << ">";
    }
	else if (token == "STRING") {
    string processed_lexeme = lexeme.substr(1, lexeme.length() - 2);
    string formatted_lexeme = "";
    for (size_t i = 0; i < processed_lexeme.length(); ++i) {
        if (processed_lexeme[i] == '\\' && i + 1 < processed_lexeme.length()) {
            string escape_seq = processed_lexeme.substr(i, 2);
            char ch = get_ascii_from_escape_sequence(escape_seq);
            switch (ch) {
                case '\n':
                case '\t':
                    formatted_lexeme += "    ";
                    break;
                case '#':
                    formatted_lexeme += '\\';
                    break;
                default:
                    formatted_lexeme += ch;
                    break;
            }
            i++;
        } else {
            formatted_lexeme += processed_lexeme[i];
        }
    }
    logout << lexeme << " found --> <STRING, " << formatted_lexeme << ">";
}
  else {
        logout << lexeme << " found";
    }

    logout << "\n\n";
}







void process_token_lexeme_pair(string token, string lexeme)
{
    if (token == "CONST_CHAR") {
        // Write actual character (may be '\n', '\t', etc.)
        write_token_to_file(token, string(1, current_converted_text[1]));

        // Write escaped lexeme (e.g., '\n') to log
        write_log_entry(token, lexeme);  // lexeme = "'\\n'" or "'p'"

        // Insert original source lexeme into symbol table
        SymbolInfo symbol(lexeme, token);
        bool success = st.Insert(symbol, logout);
        if (success) {
            st.PrintAllScopeTable(logout);
            logout << endl;
        }
        return;
    }

    // Normal tokens
    write_token_to_file(token, lexeme);
    write_log_entry(token, lexeme);

    if (token == "LCURL") {
        st.EnterScope();
    } else if (token == "RCURL") {
        st.ExitScope();
    }

    if (token == "ID" || token == "CONST_INT" || token == "CONST_FLOAT") {
        SymbolInfo symbol(lexeme, token);
        bool success = st.Insert(symbol, logout);
        if (success) {
            st.PrintAllScopeTable(logout);
            logout << endl;
        }
    }
}



void log_lexical_error(string error_name, string text, int line = line_count) {
    error_count++;
    logout << "Error at line no " << line << ": " << error_name << " " << text << "\n\n";
}

void process_const_char_token()
{
	int len = current_converted_text.length();
	if (len == 2)
	{
		log_lexical_error("Empty character constant error", current_const_char);
	}
	else if (len > 3)
	{
		log_lexical_error("Multi character constant error", current_const_char);
	}
	else
	{
		char ch = current_converted_text[1];
		if (ch == '#')
		{
			log_lexical_error("Unrecognized character", current_const_char);
		}
		else
		{
			// cout << "Const char************: " << string(1, current_converted_text[1]) << endl;
			process_token_lexeme_pair("CONST_CHAR", current_const_char);
		}
	}
}

void process_string_token()
{
	string token = "STRING";
	write_token_to_file(token, current_converted_text);
	write_log_entry(token, current_string);
}

void process_single_line_comment()
{
	write_log_entry("COMMENT", current_single_line_comment);
}

void process_multi_line_comment()
{
	write_log_entry("COMMENT", current_multi_line_comment);
}



%}

KEYWORD			"if"|"do"|"float"|"switch"|"for"|"int"|"void"|"default"|"else"|"break"|"double"|"case"|"while"|"char"|"return"|"continue"
ADDOP			"+"|"-"
MULOP			"*"|"/"|"%"
INCOP			"++"|"--"
RELOP			"<"|"<="|">"|">="|"=="|"!=" 
ASSIGNOP		"="
LOGICOP			"&&"|"||"
BITOP			"&"|"|"|"^"|"<<"|">>"
NOT				"!"
LPAREN 			"("
RPAREN			")"
LCURL			"{"
RCURL			"}"
LTHIRD 		"["
RTHIRD 		"]"
COMMA 			","
SEMICOLON		";"
WHITESPACE 		[ \t\f\r\v]
ALPHABET		[a-zA-Z]
DIGIT 			[0-9]
ALPHANUMERIC			{ALPHABET}|{DIGIT}			
CONST_INT				{DIGIT}+
DECIMAL_FRACTION		\.{DIGIT}+
FLOAT_NUMBER			{DIGIT}*{DECIMAL_FRACTION}
EXPONENT				[eE][+-]?{DIGIT}+
CONST_FLOAT 			({DIGIT}+|{FLOAT_NUMBER}){EXPONENT}?
REDUNDANT_DECIMAL_FLOAT	{DIGIT}*(\.{DIGIT}*){2,}{EXPONENT}?
FRACTIONAL_EXPONENT		[eE][+-]?{DIGIT}*{DECIMAL_FRACTION}+
ILLFORMED_NUMBER		({DIGIT}+|{FLOAT_NUMBER}){FRACTIONAL_EXPONENT}
IDENTIFIER		({ALPHABET}|"_")({ALPHANUMERIC}|"_")*
INVALID_ID_SUFFIX_NUM_PREFIX	({DIGIT}+|{FLOAT_NUMBER}){IDENTIFIER}
BACKSLASH				"\\"
NON_BACKSLASH			[^\\]
SINGLE_QUOTE			"\'"
SPECIAL_CHARACTER	    "\\0"|"\\v"|"\\b"|"\\r"|"\\f"|"\\a"|"\\\'"|"\\\\"|"\\t"|"\\n"|"\\\""
DOUBLE_QUOTE			"\""
NEWLINE 				"\n"|"\r\n"
MULTICHAR_LITERAL		'[{NON_BACKSLASH}|{SPECIAL_CHARACTER}]{2,}'	

%%

{WHITESPACE} 	{
                /* Ignore whitespace */
				}

{NEWLINE} 	{
				line_count++;
				current_lexeme_start_line_number = line_count;
			}

{KEYWORD}	{
				process_token_lexeme_pair(yytext,yytext);
			}

{ADDOP}		{
				process_token_lexeme_pair("ADDOP",yytext);
			}

{MULOP}		{
				process_token_lexeme_pair("MULOP",yytext);
			}

{INCOP}		{
				process_token_lexeme_pair("INCOP",yytext);
			}

{RELOP}		{
				process_token_lexeme_pair("RELOP",yytext);
			}

{ASSIGNOP}	{
				process_token_lexeme_pair("ASSIGNOP",yytext);
			}

{LOGICOP}	{
				process_token_lexeme_pair("LOGICOP",yytext);
			}

{BITOP}		{
				process_token_lexeme_pair("BITOP",yytext);
			}

{NOT}		{
				process_token_lexeme_pair("NOT",yytext);
			}

{LPAREN}	{
				process_token_lexeme_pair("LPAREN",yytext);
			}

{RPAREN}	{
				process_token_lexeme_pair("RPAREN",yytext);
			}

{LCURL}		{
				process_token_lexeme_pair("LCURL",yytext);
			}

{RCURL}		{
				process_token_lexeme_pair("RCURL",yytext);
			}

{LTHIRD}	{
				process_token_lexeme_pair("LTHIRD",yytext);
			}

{RTHIRD}	{
				process_token_lexeme_pair("RTHIRD",yytext);
			}
			
{COMMA}		{
				process_token_lexeme_pair("COMMA",yytext);
			}

{SEMICOLON}	{
				process_token_lexeme_pair("SEMICOLON",yytext);
			}

{CONST_INT}	{
				process_token_lexeme_pair("CONST_INT",yytext);
			}

{CONST_FLOAT} {
			  	   process_token_lexeme_pair("CONST_FLOAT",yytext);
			  }

{REDUNDANT_DECIMAL_FLOAT} 	{
								log_lexical_error("Too many decimal points", yytext);
							}

{ILLFORMED_NUMBER} 	{
						log_lexical_error("Ill formed number", yytext);
					}

{IDENTIFIER}	{
					process_token_lexeme_pair("ID",yytext);
				}

{INVALID_ID_SUFFIX_NUM_PREFIX}	{
									log_lexical_error("Invalid prefix on ID or invalid suffix on Number", yytext);
								}

{MULTICHAR_LITERAL}	{
						log_lexical_error("Multi character constant error", yytext);
					}

{SINGLE_QUOTE}	{
					BEGIN CONSTANT_CHAR_STATE;
					current_const_char = yytext;
					current_converted_text = yytext;
				} 

<CONSTANT_CHAR_STATE>{

	{SINGLE_QUOTE}	{
						current_const_char += yytext;
						current_converted_text += yytext;
						process_const_char_token();
						BEGIN INITIAL;			// end this state
					}
	 {NEWLINE} {
        log_lexical_error("Unterminated character", current_const_char, current_lexeme_start_line_number);
        line_count++;
        current_lexeme_start_line_number = line_count;
        BEGIN INITIAL;
    }
    <<EOF>> {
        log_lexical_error("Unterminated character", current_const_char, current_lexeme_start_line_number);
        BEGIN INITIAL;
    }
	{SPECIAL_CHARACTER}	{
							current_const_char += yytext;
							current_converted_text += get_ascii_from_escape_sequence(yytext);
						}
	\\{NON_BACKSLASH}	{
							current_const_char += yytext;
							current_converted_text += get_ascii_from_escape_sequence(yytext);
						}
	{NON_BACKSLASH}	{
						current_const_char += yytext;
						current_converted_text += yytext;
					}
}

{DOUBLE_QUOTE}	{
					BEGIN STRING_STATE;
					current_string = yytext;
					current_converted_text = "";
					current_string_line_count = 1;
					current_lexeme_start_line_number = line_count;
				}
<STRING_STATE>{
	{DOUBLE_QUOTE}	{
						current_string += yytext;
						process_string_token();
						BEGIN INITIAL;
					}
	{BACKSLASH}{NEWLINE} {
								current_string += yytext;
								line_count++;
								current_string_line_count++;
							}
	{NEWLINE} {
        log_lexical_error("Unterminated string", current_string, current_lexeme_start_line_number);
        line_count++;
        current_lexeme_start_line_number = line_count;
        BEGIN INITIAL;
    }
	<<EOF>> {
        log_lexical_error("Unterminated string", current_string, current_lexeme_start_line_number);
        BEGIN INITIAL;
    }

	{SPECIAL_CHARACTER}	{
							current_converted_text += get_ascii_from_escape_sequence(yytext);
							current_string += yytext;
						}
	. 	{
			current_string += yytext;
			current_converted_text += yytext;
		}
}

\/\/	{
			BEGIN SINGLE_LINE_COMMENT_STATE;
			current_single_line_comment = yytext;
			current_lexeme_start_line_number = line_count;
		}

<SINGLE_LINE_COMMENT_STATE>{
	{BACKSLASH}{NEWLINE} 	{
								current_single_line_comment += yytext;
								line_count++;
							}
	{NEWLINE}	{
					process_single_line_comment();
					line_count++;
					current_lexeme_start_line_number = line_count;
					BEGIN INITIAL;
				}
	<<EOF>>		{
					process_single_line_comment();
					BEGIN INITIAL;
				}
	. 	{
			current_single_line_comment += yytext;
		}
}

\/\*	{
			BEGIN MULTI_LINE_COMMENT_STATE;
			current_multi_line_comment = yytext;
			current_lexeme_start_line_number = line_count;
		}

<MULTI_LINE_COMMENT_STATE>{
	\*\/	{
				current_multi_line_comment += yytext;
				process_multi_line_comment();
				BEGIN INITIAL;
			}
	{NEWLINE}	{
					current_multi_line_comment += yytext;
					line_count++;
				}
	 <<EOF>> {
        log_lexical_error("Unterminated comment", current_multi_line_comment, current_lexeme_start_line_number);
        BEGIN INITIAL;
    }
	. 	{
			current_multi_line_comment += yytext;
		}
}

. {
    log_lexical_error("Unrecognized character", yytext, line_count);
}


%%

int main(int argc,char *argv[]){
	
	if(argc != 2){
		cout<<"Sorry! Name of the input file must be provided. Please Try again."<<endl;
		return 0;
	}
	
	FILE *fin;

	fin = fopen(argv[1],"r");

	if(fin == NULL){
		cout<<"Sorry! The input file cannot be found."<<endl;
		return 0;
	}

	yyin= fin;
	yylex();

	st.PrintAllScopeTable(logout);
	logout<<endl;

	logout<<"Total lines: "<<line_count<<endl;
	logout<<"Total errors: "<<error_count<<endl;

	fclose(yyin);
	tokenout.close();
	logout.close();

	return 0;
}

