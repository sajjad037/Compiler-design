package com.project.compiler.Config;

import java.util.Arrays;
import java.util.Collection;

/**
 * This Class contains the Configurations for Lexical Modules
 * @author SajjadAshrafCan
 *
 */
public class LexicalStatic {

	//Regex USed
	// * mean 0 or multiple
	// + mean 1 or multiple
	public final static String REGEX_LETTER = "[a-zA-Z]";
	public final static String REGEX_DIGIT = "[0-9]";
	public final static String REGEX_NONZERO = "[1-9]";
	public final static String REGEX_ALPHANUM = String.format("%s|%s|_", REGEX_LETTER, REGEX_DIGIT);

	public final static String REGEX_FRACTION = String.format(".%s*%s|.0", REGEX_DIGIT, REGEX_NONZERO);
	public final static String REGEX_INTEGER = String.format("%s%s*|0", REGEX_NONZERO, REGEX_DIGIT);
	public final static String REGEX_FLOAT = String.format("^((%s)(%s)([e][+|-](%s)){0,1})$", REGEX_INTEGER,
			REGEX_FRACTION, REGEX_INTEGER);
	// (([1-9][0-9]*|0)(\.[0-9]*[1-9]|.0)[e][+|-][1-9][0-9]*|0)

	public final static String REGEX_ID = String.format("^%s(%s)*$", REGEX_LETTER, REGEX_ALPHANUM);

	//Reserved Words
	public final static String R_W_IF="if";
	public final static String R_W_THEN="then";
	public final static String R_W_ELSE="else";
	public final static String R_W_FOR="for";
	public final static String R_W_CLASS="class";
	public final static String R_W_INT="int";
	public final static String R_W_FLOAT="float";
	public final static String R_W_GET="get";
	public final static String R_W_PUT="put";
	public final static String R_W_RETURN="return";
	public final static String R_W_PROGRAM="program";		
	public static Collection<String> RESERVE_WORDS = Arrays.asList(
			new String[] { R_W_IF, R_W_THEN, R_W_ELSE, R_W_FOR, R_W_CLASS, R_W_INT, R_W_FLOAT, R_W_GET, R_W_PUT, 
					R_W_RETURN, R_W_PROGRAM });

	//Logical Operators
	public final static String OPERATOR_AND = "and";
	public final static String OPERATOR_OR = "or";
	public final static String OPERATOR_NOT = "not";

	//Arithmetic Operators
	public final static char OPERATOR_ADD = '+';
	public final static char OPERATOR_SUB = '-';
	public final static char OPERATOR_MUL = '*';
	public final static char OPERATOR_DIV = '/';

	//Comparable Operators
	public final static char OPERATOR_EQUAL = '=';
	public final static char OPERATOR_GREATER_THAN = '>';
	public final static char OPERATOR_LESS_THAN = '<';

	//Punctuation
	public final static char PUNCTUATION_ROUND_LEFT_PARENTHESIS = '(';
	public final static char PUNCTUATION_ROUND_RIGHT_PARENTHESIS = ')';
	public final static char PUNCTUATION_SQUARE_LEFT_PARENTHESIS = '[';
	public final static char PUNCTUATION_SQUARE_RIGHT_PARENTHESIS = ']';
	public final static char PUNCTUATION_CURLY_LEFT_PARENTHESIS = '{';
	public final static char PUNCTUATION_CURLY_RIGHT_PARENTHESIS = '}';
	public final static char PUNCTUATION_SEMICOLON = ';';
	public final static char PUNCTUATION_COMMA = ',';
	public final static char PUNCTUATION_DOT = '.';
	public final static char PUNCTUATION_COLON = ':';
	
	//Token Names
	//Operators
	public final static String T_N_OPERATOR_ADD = "OPERATOR_ADD";
	public final static String T_N_OPERATOR_SUB = "OPERATOR_SUB";
	public final static String T_N_OPERATOR_MUL = "OPERATOR_MUL";
	public final static String T_N_OPERATOR_DIV = "OPERATOR_DIV";
	public final static String T_N_OPERATOR_EQUAL = "OPERATOR_REL_EQUAL";
	public final static String T_N_OPERATOR_NOT_EQUAL = "OPERATOR_REL_NOT_EQUAL";
	public final static String T_N_OPERATOR_ASSIGNMENT = "OPERATOR_ASSIGN_EQUAL";
	public final static String T_N_OPERATOR_LESSTHAN_EQUAL = "OPERATOR_REL_LESSTHAN_EQUAL";
	public final static String T_N_OPERATOR_LESSTHAN = "OPERATOR_REL_LESSTHAN";
	public final static String T_N_OPERATOR_GREATERTHAN_EQUAL = "OPERATOR_REL_GREATERTHAN_EQUAL";
	public final static String T_N_OPERATOR_GREATERTHAN = "OPERATOR_REL_GREATERTHAN";	
	public final static String T_N_OPERATOR_SCOPE_RESOLUTION = "OPERATOR_SCOPE_RESOLUTION";	
	public final static String T_N_OPERATOR_AND = "OPERATOR_AND";
	public final static String T_N_OPERATOR_OR = "OPERATOR_OR";
	public final static String T_N_OPERATOR_NOT = "OPERATOR_NOT";
	
	//Comments
	public final static String T_N_SINGLE_LINE_COMMENT = "SINGLE_LINE_COMMENT";
	public final static String T_N_MULTIPLE_LINE_COMMENT_START= "MULTIPLE_LINE_COMMENT_START";
	public final static String T_N_NESTED_MULTIPLE_LINE_COMMENT_START= "NESTED_MULTIPLE_LINE_COMMENT_START LEVEL:\t";
	public final static String T_N_MULTIPLE_LINE_COMMENT_END= "MULTIPLE_LINE_COMMENT_END";
	public final static String T_N_NESTED_MULTIPLE_LINE_COMMENT_END= "NESTED_MULTIPLE_LINE_COMMENT_END LEVEL:\t";
	public final static String T_N_MULTIPLE_LINE_COMMENT_NOT_ENDED= "\tMULIPLE LINE COMMENTS ARE NOT ENDED";
	
	//PUNCTUATION
	public final static String T_N_PUNCTUATION_COLON = "PUNCTUATION_COLON";
	public final static String T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS = "PUNCTUATION_ROUND_LEFT_PARENTHESIS";
	public final static String T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS = "PUNCTUATION_ROUND_RIGHT_PARENTHESIS";
	public final static String T_N_PUNCTUATION_SQUARE_LEFT_PARENTHESIS = "PUNCTUATION_SQUARE_LEFT_PARENTHESIS";
	public final static String T_N_PUNCTUATION_SQUARE_RIGHT_PARENTHESIS = "PUNCTUATION_SQUARE_RIGHT_PARENTHESIS";
	public final static String T_N_PUNCTUATION_CURLY_LEFT_PARENTHESIS = "PUNCTUATION_CURLY_LEFT_PARENTHESIS";
	public final static String T_N_PUNCTUATION_CURLY_RIGHT_PARENTHESIS = "PUNCTUATION_CURLY_RIGHT_PARENTHESIS";
	public final static String T_N_PUNCTUATION_SEMICOLON = "PUNCTUATION_SEMICOLON";
	public final static String T_N_PUNCTUATION_COMMA = "PUNCTUATION_COMMA";
	public final static String T_N_PUNCTUATION_DOT = "PUNCTUATION_DOT";
	
	public final static String T_N_FLOAT = "FLOAT";
	public final static String T_N_INTEGER = "INTEGER";
	public final static String T_N_RESERVE_WORD = "RESERVE_WORD_";
	public final static String T_N_IDENTIFIER = "IDENTIFIER";
	public final static String T_N_EOF_ENCOUNTERED= "END OF FILE ENCOUNTERED";
	
	//Errors Message
	public final static String T_E_INVALID_CHARACTER = "INVALID_CHARACTER";
	public final static String T_E_INVALID_FLOAT = "INVALID_FLOAT";
	public final static String T_E_INVALID_FLOAT_EXPO = "INVALID_FLOAT_EXPONENTIAL";
	public final static String T_E_INVALID_INTEGER = "INVALID_INTEGER";
	public final static String T_E_INVALID_IDENTIFIER = "INVALID_IDENTIFIER";
}
