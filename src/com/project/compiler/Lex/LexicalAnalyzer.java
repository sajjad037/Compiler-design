package com.project.compiler.Lex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.project.compiler.Config.LexicalStatic;
import com.project.compiler.Config.Enums.ModuleType;
import com.project.compiler.Config.Enums.TokenType;
import com.project.compiler.Model.Token;
import com.project.compiler.Semantic.Errors;
import com.project.compiler.Utilities.LoggerConfiguration;

/**
 * This Class has Lexical Analyzer Implementation
 * 
 * @author SajjadAshrafCan
 *
 */
public class LexicalAnalyzer {

	private Vector<Token> tokens;
	private StreamTokenizer st;
	private boolean eof = false;

	private ArrayList<Token> tokenList = new ArrayList<Token>();
	private ArrayList<Token> erroList = new ArrayList<Token>();
	private ArrayList<Token> commentList = new ArrayList<Token>();
	
	private String className = LexicalAnalyzer.class.getSimpleName();
	private Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));

	/**
	 * Default Constructor
	 * 
	 */
	public LexicalAnalyzer() {
	}

	/**
	 * Copy Constructor, it perform some basic loading steps like file reading
	 * etc..
	 *
	 * @param inputFile
	 */
	public LexicalAnalyzer(String inputFile) {
		
		tokens = new Vector<Token>();
		tokenList = new ArrayList<Token>();

		try {

			FileReader fileReader = new FileReader(inputFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			st = new StreamTokenizer(bufferedReader);

			// Resets this tokenizer's syntax table to Ordinary
			st.resetSyntax();
			st.eolIsSignificant(true);

			// Consider 0-9, a-z, A-Z and _ as word Chars
			st.wordChars('0', '9');
			st.wordChars('a', 'z');
			st.wordChars('A', 'Z');
			st.wordChars('_', '_');

			// Don't convert program text into upper or lower case
			st.lowerCaseMode(false);

			st.whitespaceChars(0, 9);
			st.whitespaceChars(11, 32);
			st.ordinaryChar(32);
			st.ordinaryChar(9);
			
			LOGGER.info(".. Ready ...");

		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	/**
	 * It provides the next available valid token
	 * 
	 * @return
	 * @throws IOException
	 */
	public Token getNextAvialableToken() throws IOException {
		Token token = null;
		boolean isValidToken = false;
		do {
			token = getNextToken();
			if (token.getType() == TokenType.TOKEN) {
				isValidToken = true;
				tokens.add(token);
			} else if (token.getType() == TokenType.ERROR) {
				erroList.add(token);
			} else if (token.getType() == TokenType.COMMENT) {
				commentList.add(token);
			} else if (token.getType() == TokenType.EOF) {
				tokens.add(token);
				isValidToken = true;
			}
		} while (!isValidToken);
		return token;
	}

	/**
	 * It provides the next token
	 * 
	 * @return
	 * @throws IOException
	 */
	private Token getNextToken() throws IOException {
		Token nextToken = null;
		if (!eof) {
			while (tokenList == null || tokenList.isEmpty() || tokenList.size() == 0) {
				int token = st.nextToken();
				eof = tokenization(token, st, eof);
			}
		}
		nextToken = tokenList.get(0);
		tokenList.remove(0);
		return nextToken;
	}

	/**
	 * Convert input stream into tokens.
	 * 
	 * @param token
	 * @param st
	 * @param eof
	 * @return
	 */
	private boolean tokenization(int token, StreamTokenizer st, boolean eof) {
		switch (token) {
		case StreamTokenizer.TT_WORD:
			convertWordstoToken(st);
			break;
		case StreamTokenizer.TT_EOF:
			eof = true;
			tokenList.add(new Token(st.lineno(), "$", "T_EOF", TokenType.EOF));
			break;
		case StreamTokenizer.TT_EOL:
			break;
		default:
			char tokenValue = (char) token;
			conovertOrdinaryCharsToToken(st, tokenValue);
		}
		return eof;
	}

	/**
	 * Convert Characters input into Tokens
	 * 
	 * @param st
	 * @param tokenValue
	 */
	private void conovertOrdinaryCharsToToken(StreamTokenizer st, char tokenValue) {
		switch (tokenValue) {
		case LexicalStatic.OPERATOR_ADD:
			tokenList.add(new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_OPERATOR_ADD, TokenType.TOKEN));
			break;
		case LexicalStatic.OPERATOR_SUB:
			tokenList.add(new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_OPERATOR_SUB, TokenType.TOKEN));
			break;
		case LexicalStatic.OPERATOR_MUL:
			tokenList.add(new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_OPERATOR_MUL, TokenType.TOKEN));
			break;
		case LexicalStatic.OPERATOR_DIV:
			int divLineNum = st.lineno();
			try {
				char nextToken = (char) st.nextToken();

				switch (nextToken) {
				// Check for Single Line Comment '//'
				case LexicalStatic.OPERATOR_DIV:
					tokenList.add(new Token(st.lineno(), "//", LexicalStatic.T_N_SINGLE_LINE_COMMENT, TokenType.COMMENT));
					while (st.nextToken() != StreamTokenizer.TT_EOL) {
					}
					break;

				// Check for Comment Block '/* ... */'
				case LexicalStatic.OPERATOR_MUL:

					tokenList.add(new Token(st.lineno(), "/*", LexicalStatic.T_N_MULTIPLE_LINE_COMMENT_START, TokenType.COMMENT));
					int commmentCount = 1;
					char n1Token = (char) st.nextToken();
					char n2Token = (char) st.nextToken();
					while (commmentCount != 0) {
						if (n1Token == '/' && n2Token == '*') {
							tokenList.add(new Token(st.lineno(), "/*",
									LexicalStatic.T_N_NESTED_MULTIPLE_LINE_COMMENT_START + commmentCount, TokenType.COMMENT));
							commmentCount++;
							n1Token = (char) st.nextToken();
							n2Token = (char) st.nextToken();
						} else if (n1Token == '*' && n2Token == '/') {
							commmentCount--;
							if (commmentCount == 0) {
								tokenList.add(
										new Token(st.lineno(), "*/", LexicalStatic.T_N_MULTIPLE_LINE_COMMENT_END, TokenType.COMMENT));
								break;
							} else {
								tokenList.add(new Token(st.lineno(), "*/",
										LexicalStatic.T_N_NESTED_MULTIPLE_LINE_COMMENT_END + commmentCount,
										TokenType.COMMENT));
							}
							n1Token = (char) st.nextToken();
							n2Token = (char) st.nextToken();
						} else if (n2Token == (char) StreamTokenizer.TT_EOF
								|| n1Token == (char) StreamTokenizer.TT_EOF) {
							if (commmentCount != 0) {
								tokenList.add(new Token(st.lineno(), LexicalStatic.T_N_EOF_ENCOUNTERED,
										commmentCount + LexicalStatic.T_N_MULTIPLE_LINE_COMMENT_NOT_ENDED, TokenType.EOF));
							}
							break;
						} else {
							n1Token = n2Token;
							n2Token = (char) st.nextToken();
						}
					}
					break;
				default:
					tokenList.add(new Token(divLineNum, "" + tokenValue, LexicalStatic.T_N_OPERATOR_DIV, TokenType.TOKEN));
					st.pushBack();
					break;

				}

			} catch (IOException e) {
				st.pushBack();
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			break;
		case LexicalStatic.OPERATOR_EQUAL:
			int eqlLineNum = st.lineno();
			try {
				char nextToken = (char) st.nextToken();
				switch (nextToken) {
				// Check For Equal '=='
				case LexicalStatic.OPERATOR_EQUAL:
					tokenList.add(new Token(st.lineno(), "==", LexicalStatic.T_N_OPERATOR_EQUAL, TokenType.TOKEN));
					break;
				// Otherwise Assignment Operator '='
				default:
					tokenList.add(new Token(eqlLineNum, "" + tokenValue, LexicalStatic.T_N_OPERATOR_ASSIGNMENT, TokenType.TOKEN));
					st.pushBack();
					break;
				}
			} catch (IOException e) {
				st.pushBack();
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			break;
		case LexicalStatic.OPERATOR_LESS_THAN:
			int ltLineNum = st.lineno();
			try {
				char nextToken = (char) st.nextToken();

				switch (nextToken) {
				// Check For not equal '<>' to
				case LexicalStatic.OPERATOR_GREATER_THAN:
					tokenList.add(new Token(st.lineno(), "<>", LexicalStatic.T_N_OPERATOR_NOT_EQUAL, TokenType.TOKEN));
					break;

				// Check For Less than equal to '<=' to
				case LexicalStatic.OPERATOR_EQUAL:
					tokenList.add(new Token(st.lineno(), "<=", LexicalStatic.T_N_OPERATOR_LESSTHAN_EQUAL, TokenType.TOKEN));
					break;

				// Confirmed Less than '<'
				default:
					tokenList.add(new Token(ltLineNum, "<", LexicalStatic.T_N_OPERATOR_LESSTHAN, TokenType.TOKEN));
					st.pushBack();
					break;
				}

			} catch (IOException e) {
				st.pushBack();
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			break;
		case LexicalStatic.OPERATOR_GREATER_THAN:
			int gtLineNum = st.lineno();
			try {

				char nextToken = (char) st.nextToken();
				switch (nextToken) {

				// Check for Greater Than Equal to '>='
				case LexicalStatic.OPERATOR_EQUAL:
					tokenList.add(new Token(st.lineno(), ">=", LexicalStatic.T_N_OPERATOR_GREATERTHAN_EQUAL, TokenType.TOKEN));
					break;

				// Confirmed Greater Than '>'
				default:
					tokenList.add(new Token(gtLineNum, "" + tokenValue, LexicalStatic.T_N_OPERATOR_GREATERTHAN, TokenType.TOKEN));
					st.pushBack();
					break;
				}
			} catch (IOException e) {
				st.pushBack();
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			break;
		case LexicalStatic.PUNCTUATION_COLON:
			try {
				int colonLineNum = st.lineno();
				char nextToken = (char) st.nextToken();
				switch (nextToken) {

				// Check for Double Colon Scope Resolution Operator '::'
				case LexicalStatic.PUNCTUATION_COLON:
					tokenList.add(
							new Token(st.lineno(), "::", LexicalStatic.T_N_OPERATOR_SCOPE_RESOLUTION, TokenType.TOKEN));
					break;

				// Confirmed PUNCTUATION COLON ':'
				default:
					tokenList.add(new Token(colonLineNum, "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_COLON, TokenType.TOKEN));
					st.pushBack();
					break;
				}
			} catch (IOException e) {
				st.pushBack();
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			break;
		case LexicalStatic.PUNCTUATION_ROUND_LEFT_PARENTHESIS:
			tokenList.add(
					new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, TokenType.TOKEN));
			break;
		case LexicalStatic.PUNCTUATION_ROUND_RIGHT_PARENTHESIS:
			tokenList.add(
					new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, TokenType.TOKEN));
			break;
		case LexicalStatic.PUNCTUATION_SQUARE_LEFT_PARENTHESIS:
			tokenList.add(
					new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_SQUARE_LEFT_PARENTHESIS, TokenType.TOKEN));
			break;
		case LexicalStatic.PUNCTUATION_SQUARE_RIGHT_PARENTHESIS:
			tokenList.add(
					new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_SQUARE_RIGHT_PARENTHESIS, TokenType.TOKEN));
			break;
		case LexicalStatic.PUNCTUATION_CURLY_LEFT_PARENTHESIS:
			tokenList.add(
					new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_CURLY_LEFT_PARENTHESIS, TokenType.TOKEN));
			break;
		case LexicalStatic.PUNCTUATION_CURLY_RIGHT_PARENTHESIS:
			tokenList.add(
					new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_CURLY_RIGHT_PARENTHESIS, TokenType.TOKEN));
			break;
		case LexicalStatic.PUNCTUATION_SEMICOLON:
			tokenList.add(new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_SEMICOLON, TokenType.TOKEN));
			break;
		case LexicalStatic.PUNCTUATION_COMMA:
			tokenList.add(new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_COMMA, TokenType.TOKEN));
			break;
		case LexicalStatic.PUNCTUATION_DOT:

			/*try {
				int dotLineNum = st.lineno();

				// Check For fraction
				if (st.nextToken() == StreamTokenizer.TT_WORD) {
					String nextStr = "." + st.sval;
					if (Pattern.matches(LexicalStatic.REGEX_FRACTION, nextStr)) {
						tokenList.add(new Token(st.lineno(), "" + nextStr, LexicalStatic.T_N_FRACTION, TokenType.TOKEN));
					}
				} else {
					tokenList.add(new Token(dotLineNum, "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_DOT, TokenType.TOKEN));
					st.pushBack();
				}
			} catch (IOException e) {
				st.pushBack();
				LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			}*/
			
			tokenList.add(new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_N_PUNCTUATION_DOT, TokenType.TOKEN));			
			break;
		default:
			if (tokenValue > 32) {
				tokenList.add(new Token(st.lineno(), "" + tokenValue, LexicalStatic.T_E_INVALID_CHARACTER, TokenType.ERROR));
			}
		}
	}

	/**
	 * Convert Words input to Tokens
	 * 
	 * @param st
	 */
	private void convertWordstoToken(StreamTokenizer st) {
		String strValue = st.sval;
		int lineNum = st.lineno();

		if (LexicalStatic.RESERVE_WORDS.contains(strValue)) {
			tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_N_RESERVE_WORD + strValue.toUpperCase(), TokenType.TOKEN));
		} else if (strValue.equals(LexicalStatic.OPERATOR_AND)) {
			tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_N_OPERATOR_AND, TokenType.TOKEN));
		} else if (strValue.equals(LexicalStatic.OPERATOR_OR)) {
			tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_N_OPERATOR_OR, TokenType.TOKEN));
		} else if (strValue.equals(LexicalStatic.OPERATOR_NOT)) {
			tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_N_OPERATOR_NOT, TokenType.TOKEN));
		}

		// Check for Digit [0-9] OR noneZero [1-9]
		else if (Pattern.matches(LexicalStatic.REGEX_DIGIT + "+", strValue)
				|| Pattern.matches(LexicalStatic.REGEX_NONZERO + "+", strValue)) {

			// Check Integer
			if (Pattern.matches(LexicalStatic.REGEX_INTEGER, strValue)) {

				try {
					// Get Next token '.' to Check For friction
					char dotToken = (char) st.nextToken();
					if (dotToken == LexicalStatic.PUNCTUATION_DOT) {
						int intDotLineNum = st.lineno();
						String intDotStr = strValue + dotToken;

						// Check For fraction
						if (st.nextToken() == StreamTokenizer.TT_WORD) {
							int fricLineNum = st.lineno();
							String fricStr = dotToken + st.sval;

							// Check for after '.' part of Friction
							if (Pattern.matches(LexicalStatic.REGEX_FRACTION, fricStr)) {

								tokenList.add(new Token(fricLineNum, strValue + fricStr, LexicalStatic.T_N_FLOAT, TokenType.TOKEN));
								// Need to see Here for exponential float not
								// working
							}
							// validate if 'e' inside of value
							//else if (fricStr.contains("e")) {
							else if (hasExponent(fricStr, 'e')) {

								// Get Next for Sign '+|-' token
								char signToken = (char) st.nextToken();
								if (signToken == '+' || signToken == '-') {
									int signLine = st.lineno();
									String signStr = strValue + fricStr + signToken;

									// Now Check for Integer Part of Exponential
									if (st.nextToken() == StreamTokenizer.TT_WORD) {
										String floaExptstr = signStr + st.sval;

										if (Pattern.matches(LexicalStatic.REGEX_FLOAT, floaExptstr)) {
											tokenList
													.add(new Token(st.lineno(), floaExptstr, LexicalStatic.T_N_FLOAT, TokenType.TOKEN));
										} else {
											tokenList.add(new Token(signLine, signStr, LexicalStatic.T_E_INVALID_FLOAT_EXPO,
													TokenType.ERROR));
											st.pushBack(); // for after sign characters
										}
									} else {
										tokenList.add(new Token(fricLineNum, strValue +fricStr, LexicalStatic.T_E_INVALID_FLOAT_EXPO,
												TokenType.ERROR));
										st.pushBack(); // for after sign characters b/c it is not word
									}
								} else {
									tokenList.add(new Token(fricLineNum, strValue +fricStr, LexicalStatic.T_E_INVALID_FLOAT_EXPO,
											TokenType.ERROR));
									st.pushBack();//after 'e' because sings '+|-' is not there
								}
							} else {
								tokenList.add(
										new Token(intDotLineNum, strValue + fricStr, LexicalStatic.T_E_INVALID_FLOAT, TokenType.ERROR));
								//st.pushBack();//because 'e' is not there 
							}
						} else {
							tokenList.add(new Token(intDotLineNum, intDotStr, LexicalStatic.T_E_INVALID_FLOAT, TokenType.ERROR));
							st.pushBack();// after dot it is not word token
						}

					} else {
						tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_N_INTEGER, TokenType.TOKEN));
						st.pushBack();
					}
				} catch (IOException e) {
					st.pushBack();
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}
			} else {
				tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_E_INVALID_INTEGER, TokenType.ERROR));
			}
		} else if (strValue.startsWith("_")) {
			if(strValue.length() > 1){
				tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_E_INVALID_IDENTIFIER, TokenType.ERROR));
			}
			else
			{
				tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_E_INVALID_CHARACTER, TokenType.ERROR));
			}
			
		} else if (Pattern.matches(LexicalStatic.REGEX_ID, strValue)) {
			tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_N_IDENTIFIER, TokenType.TOKEN));
		} else {
			tokenList.add(new Token(lineNum, strValue, LexicalStatic.T_E_INVALID_IDENTIFIER, TokenType.ERROR));
		}
	}

	/**
	 * Check value has proper exponential part
	 * @param value
	 * @return
	 */
	private boolean hasExponent(String val, char toCheck){
		boolean restul = false;
	    int count = 0;
	    int intCount=0;
	    //Remove point
	    String value =val.replace(".", ""); 
	    int length = value.length();
	    //How manay Time has 'e'
	    for (int i = 0; i < value.length(); i++) {
	        if (value.charAt(i) == toCheck) {
	            count++;
	        }
	        
	        if (Character.isDigit(value.charAt(i))) { 
	        	intCount++;
	        }
	    }
	    if(count == 1 && intCount == (length - 1) ){
	    	restul = true; 
	    }
		return restul;
	}
	
	/**
	 * Get Errors list
	 * 
	 * @return
	 */
	public ArrayList<Token> getErrorTokenList() {
		return erroList;
	}
	
	public ArrayList<Errors> getErrorList() {
		
		ArrayList<Errors> errList =  new ArrayList<>();
		for (Token errorsToken : erroList) {
			errList.add(new Errors(errorsToken.getLine(), errorsToken.getDescription(), "ERROR", ModuleType.LEXICAL));
		}
		return errList;
	}

	/**
	 * Get Comments list
	 * 
	 * @return
	 */
	public ArrayList<Token> getCommentList() {
		return commentList;
	}
}
