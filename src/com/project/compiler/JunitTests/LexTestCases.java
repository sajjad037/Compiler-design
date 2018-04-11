package com.project.compiler.JunitTests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.project.compiler.Config.Enums;
import com.project.compiler.Lex.LexicalAnalyzer;
import com.project.compiler.Model.Token;
import com.project.compiler.Utilities.LoggerConfiguration;

/**
 * 
 * @author SajjadAshrafCan
 *
 */
public class LexTestCases {

	private static String className = LexTestCases.class.getSimpleName();
	private Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private static String genericPath = "TestCases\\Lexical\\%d\\%s";
	private static LexicalAnalyzer lexicalAnalyzer = null;
	private static String[] testCases = { String.format(genericPath, 1, "1_LEXER_COMMENT_ERROR.txt"),
			String.format(genericPath, 2, "2_LEXER_TOKENS.txt"), String.format(genericPath, 3, "3_COMMENT_Test.txt"),
			String.format(genericPath, 4, "4_ERROR_Test.txt"),
			String.format(genericPath, 5, "5_VALID_IDENTIFIERS_TEST.txt"),
			String.format(genericPath, 6, "6_VALID_NUMBERS_TEST.txt"),
			String.format(genericPath, 7, "7_VALID_RESERVE_WORD_TEST.txt") };
	private static ArrayList<Token> tokenList = null;

	/**
	 * This Test case, test the '1_LEXER_COMMENT_ERROR'
	 */
	@Test
	public void testCaseCommentError() {
		String path = testCases[0];
		int lexActualTokenCount = 25;
		int lexActualErrorTokenCount = 48;
		boolean status = false;
		try {

			LOGGER.info(".. Executing Unit Test Case testCaseCommentError ..");
			LOGGER.info("testCases file path: " + path);

			lexicalAnalyzer = new LexicalAnalyzer(path);
			int lexTokenCount = getTokenCount();
			int lexErrorTokenCount = lexicalAnalyzer.getErrorTokenList().size();
			status = lexActualTokenCount + lexActualErrorTokenCount == lexTokenCount + lexErrorTokenCount;
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '2_LEXER_TOKENS.txt'
	 */
	@Test
	public void testCaseLexerTokens() {
		String path = testCases[1];
		int lexActualTokenCount = 8;
		int lexActualErrorTokenCount = 187;
		boolean status = false;
		try {

			LOGGER.info(".. Executing Unit Test Case testCaseLexerTokens ..");
			LOGGER.info("testCases file path: " + path);

			lexicalAnalyzer = new LexicalAnalyzer(path);
			int lexTokenCount = getTokenCount();
			int lexErrorTokenCount = lexicalAnalyzer.getErrorTokenList().size();
			status = lexActualTokenCount + lexActualErrorTokenCount == lexTokenCount + lexErrorTokenCount;
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '3_COMMENT_Test.txt'
	 */
	@Test
	public void testCaseComment() {
		String path = testCases[2];
		int lexActualTokenCount = 9;
		int lexActualErrorTokenCount = 0;
		boolean status = false;
		try {

			LOGGER.info(".. Executing Unit Test Case testCaseComment ..");
			LOGGER.info("testCases file path: " + path);

			lexicalAnalyzer = new LexicalAnalyzer(path);
			int lexTokenCount = getTokenCount();
			int lexErrorTokenCount = lexicalAnalyzer.getErrorTokenList().size();
			status = lexActualTokenCount + lexActualErrorTokenCount == lexTokenCount + lexErrorTokenCount;
			// LOGGER.info("lexActualTokenCount: " + lexActualTokenCount+",
			// lexActualErrorTokenCount: "+lexActualErrorTokenCount+
			// "lexTokenCount: "+lexTokenCount+", lexErrorTokenCount:
			// "+lexErrorTokenCount);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '4_ERROR_Test.txt'
	 */
	@Test
	public void testCaseErrors() {
		String path = testCases[3];
		int lexActualTokenCount = 7;
		int lexActualErrorTokenCount = 42;
		boolean status = false;
		try {

			LOGGER.info(".. Executing Unit Test Case testCaseErrors ..");
			LOGGER.info("testCases file path: " + path);

			lexicalAnalyzer = new LexicalAnalyzer(path);
			int lexTokenCount = getTokenCount();
			int lexErrorTokenCount = lexicalAnalyzer.getErrorTokenList().size();
			status = lexActualTokenCount + lexActualErrorTokenCount == lexTokenCount + lexErrorTokenCount;
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '5_VALID_IDENTIFIERS_TEST.txt'
	 */
	@Test
	public void testCaseValidIdentifiers() {
		String path = testCases[4];
		int lexActualTokenCount = 63;
		int lexActualErrorTokenCount = 0;
		boolean status = false;
		try {

			LOGGER.info(".. Executing Unit Test Case testCaseValidIdentifiers ..");
			LOGGER.info("testCases file path: " + path);

			lexicalAnalyzer = new LexicalAnalyzer(path);
			int lexTokenCount = getTokenCount();
			int lexErrorTokenCount = lexicalAnalyzer.getErrorTokenList().size();
			status = lexActualTokenCount + lexActualErrorTokenCount == lexTokenCount + lexErrorTokenCount;
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '6_VALID_NUMBERS_TEST.txt'
	 */
	@Test
	public void testCaseValidNumbers() {
		String path = testCases[5];
		int lexActualTokenCount = 67;
		int lexActualErrorTokenCount = 8;
		boolean status = false;
		try {

			LOGGER.info(".. Executing Unit Test Case testCaseValidNumbers ..");
			LOGGER.info("testCases file path: " + path);

			lexicalAnalyzer = new LexicalAnalyzer(path);
			int lexTokenCount = getTokenCount();
			int lexErrorTokenCount = lexicalAnalyzer.getErrorTokenList().size();
			status = lexActualTokenCount + lexActualErrorTokenCount == lexTokenCount + lexErrorTokenCount;
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '7_VALID_RESERVE_WORD_TEST.txt'
	 */
	@Test
	public void testCaseValidReserveWords() {
		String path = testCases[6];
		int lexActualTokenCount = 60;
		int lexActualErrorTokenCount = 0;
		boolean status = false;
		try {

			LOGGER.info(".. Executing Unit Test Case testCaseValidReserveWords ..");
			LOGGER.info("testCases file path: " + path);

			lexicalAnalyzer = new LexicalAnalyzer(path);
			int lexTokenCount = getTokenCount();
			int lexErrorTokenCount = lexicalAnalyzer.getErrorTokenList().size();
			status = lexActualTokenCount + lexActualErrorTokenCount == lexTokenCount + lexErrorTokenCount;
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	/**
	 * Get token count
	 * @return
	 * @throws IOException
	 */
	private int getTokenCount() throws IOException {
		tokenList = new ArrayList<Token>();
		Token token = null;
		do {

			token = lexicalAnalyzer.getNextAvialableToken();

			tokenList.add(token);

		} while (token.getType() != Enums.TokenType.EOF);

		return tokenList.size();
	}
}
