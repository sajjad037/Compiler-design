package com.project.compiler.JunitTests;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.project.compiler.Driver.LexDriver;
import com.project.compiler.Utilities.LoggerConfiguration;

/**
 * 
 * @author SajjadAshrafCan
 *
 */
public class TestCasesLexFileMatch extends baseTest {

	private static String className = TestCasesLexFileMatch.class.getSimpleName();
	private Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private static String genericPathTestCases = "TestCases\\Lexical\\%d\\%s";
	private static String LEXICAL_TOKEN_FILE = "LEXICAL_Output.txt";

	private static String[] testCases = { 
			String.format(genericPathTestCases, 1, "1_LEXER_COMMENT_ERROR.txt"),
			String.format(genericPathTestCases, 2, "2_LEXER_TOKENS.txt"),
			String.format(genericPathTestCases, 3, "3_COMMENT_Test.txt"),
			String.format(genericPathTestCases, 4, "4_ERROR_Test.txt"),
			String.format(genericPathTestCases, 5, "5_VALID_IDENTIFIERS_TEST.txt"),
			String.format(genericPathTestCases, 6, "6_VALID_NUMBERS_TEST.txt"),
			String.format(genericPathTestCases, 7, "7_VALID_RESERVE_WORD_TEST.txt") 
	};

	private static String genericPathExpectedResult = "JunitResults\\Lexical\\%d\\%s";

	/**
	 * This Test case, test the '1_LEXER_COMMENT_ERROR'
	 */
	@Test
	public void testCaseCommentError() {

		int index = 1;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseCommentError"));

		} catch (Exception e) {
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
		int index = 2;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseLexerTokens"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	private boolean commonExecution(int index, String testName) {
		boolean status = false;
		try {
			LOGGER.info(".. Executing Unit Test Case " + testName + " ..");
			String testFilePath = testCases[index - 1];

			// Execute test
			LexDriver lexDriver = new LexDriver();
			lexDriver.execute(testFilePath, index);

			// Match the results
			String expectedTokenPath = String.format(genericPathExpectedResult, index, LEXICAL_TOKEN_FILE);
			String testTokenPath = String.format(genericPathTestCases, index, LEXICAL_TOKEN_FILE);
			status = sameContent(Paths.get(expectedTokenPath), Paths.get(testTokenPath));
			LOGGER.info("Unit test Cases statu: " + status);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			status = false;
		}
		return status;
	}

	/**
	 * This Test case, test the '3_COMMENT_Test.txt'
	 */
	@Test
	public void testCaseComment() {
		int index = 3;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseComment"));

		} catch (Exception e) {
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
		int index = 4;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseErrors"));

		} catch (Exception e) {
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
		int index = 5;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseValidIdentifiers"));

		} catch (Exception e) {
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
		int index = 6;
		boolean status = false;

		try {
			assert (commonExecution(index, "6_VALID_NUMBERS_TEST"));

		} catch (Exception e) {
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
		int index = 7;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseValidReserveWords"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

}
