package com.project.compiler.JunitTests;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.project.compiler.Driver.SyntacticDriver;
import com.project.compiler.Utilities.LoggerConfiguration;

/**
 * 
 * @author SajjadAshrafCan
 *
 */
public class TestCasesSyntaxFileMatch extends baseTest {

	private String className = TestCasesSyntaxFileMatch.class.getSimpleName();
	private Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private String genericPathTestCases = "TestCases\\Syntactic\\%d\\%s";
	private String TEST_FILE = "SYNTAX_abstractSyntaxTree.txt";

	private String[] testCases = { 
			String.format(genericPathTestCases, 1, "1_Sample.txt"),
//			String.format(genericPathTestCases, 1, "1_workingSample.txt"),
//			String.format(genericPathTestCases, 1, "1_SYNTAX_ERROR.txt"),
			String.format(genericPathTestCases, 2, "2_NoClassDecl.txt"), 
			String.format(genericPathTestCases, 3, "3_SYNTAX_VALID.txt"),
			String.format(genericPathTestCases, 4, "4_NoProgBody.txt"), 
			String.format(genericPathTestCases, 5, "5_VariableError.txt"),
			String.format(genericPathTestCases, 6, "6_VariableDefinitionError.txt"),
			String.format(genericPathTestCases, 7, "7_IF_Conditional_Statements.txt"),
			String.format(genericPathTestCases, 8, "8_FOR_Loop_Statement.txt"),
			String.format(genericPathTestCases, 9, "9_ClassMembers.txt"),
			String.format(genericPathTestCases, 10, "10_ClassDeclError.txt"),
			String.format(genericPathTestCases, 11, "11_ArraySizeError.txt"),
			String.format(genericPathTestCases, 12, "12_ComplexExpression_Arrays.txt"),
			String.format(genericPathTestCases, 13, "13_FuncBodyError.txt"),
			String.format(genericPathTestCases, 14, "14_StatementError.txt"),
			String.format(genericPathTestCases, 15, "15_ValidTestCase1.txt"),
			String.format(genericPathTestCases, 16, "16_ValidTestCase2.txt"),
			String.format(genericPathTestCases, 17, "17_ValidTestCase3.txt"),
			String.format(genericPathTestCases, 18, "18_ProgramGivenInAssignment2.txt")
	};

	private String genericPathExpectedResult = "JunitResults\\Syntactic\\%d\\%s";

	/**
	 * This Test case, test the '1_Sample'
	 */
	@Test
	public void testCaseSample() {

		int index = 1;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseSample"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}

	}

	/**
	 * This Test case, test the '2_NoClassDecl.txt'
	 */
	@Test
	public void testCaseNoClassDecl() {
		int index = 2;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseNoClassDecl"));

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
			SyntacticDriver syntacticDriver = new SyntacticDriver();
			syntacticDriver.execute(testFilePath, index);

			// Match the results
			String expectedTokenPath = String.format(genericPathExpectedResult, index, TEST_FILE);
			String testTokenPath = String.format(genericPathTestCases, index, TEST_FILE);
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
	 * This Test case, test the '3_SYNTAX_VALID.txt'
	 */
	@Test
	public void testCaseSyntaxValid() {
		int index = 3;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseSyntaxValid"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '4_NoProgBody.txt'
	 */
	@Test
	public void testCaseNoProgBody() {
		int index = 4;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseNoProgBody"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '5_VariableError.txt'
	 */
	@Test
	public void testCaseVariableError() {
		int index = 5;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseVariableError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '6_VariableDefinitionError.txt'
	 */
	@Test
	public void testCaseVariableDefinitionError() {
		int index = 6;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseVariableDefinitionError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '7_IF_Conditional_Statements.txt'
	 */
	@Test
	public void testCaseIfConditionalStatements() {
		int index = 7;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseIfConditionalStatements"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	
	/**
	 * This Test case, test the '8_FOR_Loop_Statement.txt'
	 */
	@Test
	public void testCaseForLoopStatement() {
		int index = 8;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseForLoopStatement"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	

	/**
	 * This Test case, test the '9_ClassMembers.txt'
	 */
	@Test
	public void testCaseClassMembers() {
		int index = 9;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseClassMembers"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '10_ClassDeclError.txt'
	 */
	@Test
	public void testCaseClassDeclError() {
		int index = 10;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseClassDeclError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '11_ArraySizeError.txt'
	 */
	@Test
	public void testCaseArraySizeError() {
		int index = 11;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseArraySizeError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '12_ComplexExpression_Arrays.txt'
	 */
	@Test
	public void testCaseComplexExpressionArrays() {
		int index = 12;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseComplexExpressionArrays"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '13_FuncBodyError.txt'
	 */
	@Test
	public void testCaseFuncBodyError() {
		int index = 13;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseFuncBodyError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '14_StatementError.txt'
	 */
	@Test
	public void testCaseStatementError() {
		int index = 14;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseStatementError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '15_ValidTestCase1.txt'
	 */
	@Test
	public void testCaseValidTestCase1() {
		int index = 15;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseValidTestCase1"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '16_ValidTestCase2.txt'
	 */
	@Test
	public void testCaseValidTestCase2() {
		int index = 16;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseValidTestCase2"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '17_ValidTestCase3.txt'
	 */
	@Test
	public void testCaseValidTestCase3() {
		int index = 17;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseValidTestCase3"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '18_ProgramGivenInAssignment2.txt'
	 */
	@Test
	public void testCaseProgramGivenInAssignment2() {
		int index = 18;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseProgramGivenInAssignment2"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
}
