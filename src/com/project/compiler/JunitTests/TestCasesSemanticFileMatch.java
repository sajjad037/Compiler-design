package com.project.compiler.JunitTests;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.project.compiler.Driver.SemanticDriver;
import com.project.compiler.Utilities.LoggerConfiguration;

/**
 * 
 * @author SajjadAshrafCan
 *
 */
public class TestCasesSemanticFileMatch extends baseTest {

	private String className = TestCasesSemanticFileMatch.class.getSimpleName();
	private Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private String genericPathTestCases = "TestCases\\Semantic\\%d\\%s";
	private String TEST_FILE = "SEMANTIC_AST_SymbolTable.txt";

	private String[] testCases = { 
			String.format(genericPathTestCases, 1, "1_profExample.txt"),
//			String.format(genericPathTestCases, 1, "1_workingSample.txt"),			
//			String.format(genericPathTestCases, 1, "1_UndefinedID.txt"),
			String.format(genericPathTestCases, 2, "2_UndeclaredVariableDecl.txt"), 
			String.format(genericPathTestCases, 3, "3_DuplicateClassDeclError.txt"),
			String.format(genericPathTestCases, 4, "4_VariableError.txt"), 
			String.format(genericPathTestCases, 5, "5_UndefinedMember.txt"),
			String.format(genericPathTestCases, 6, "6_DuplicateIDs.txt"),
			String.format(genericPathTestCases, 7, "7_DuplicateParamsError.txt"),
			String.format(genericPathTestCases, 8, "8_DuplicateClassMemberError.txt"),
			String.format(genericPathTestCases, 9, "9_TYPE_CHECK_RETURN_TYPE.txt"),
			String.format(genericPathTestCases, 10, "10_VariableDefinitionError.txt"),
			String.format(genericPathTestCases, 11, "11_ArrayIndiceError.txt"),
			//String.format(genericPathTestCases, 12, "12_combinedSemanticErrors.txt"),
			String.format(genericPathTestCases, 12, "12_UndefinedID.txt"),
			String.format(genericPathTestCases, 13, "13_FunctionCall.txt"),
			String.format(genericPathTestCases, 14, "14_FunctionInvocationError.txt"),
			String.format(genericPathTestCases, 15, "15_StatementError.txt"),
			String.format(genericPathTestCases, 16, "16_TypeChecking.txt"),
			//String.format(genericPathTestCases, 17, "17_ValidTestCase.txt"),
			String.format(genericPathTestCases, 17, "17_workingSample.txt"),
			String.format(genericPathTestCases, 18, "18_ValidTestCase1.txt"),
			String.format(genericPathTestCases, 19, "19_WrongFunctionCall.txt")
	};

	private String genericPathExpectedResult = "JunitResults\\Semantic\\%d\\%s";

	/**
	 * This Test case, test the '1_profExample'
	 */
	@Test
	public void testCaseProfExample() {

		int index = 1;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseProfExample"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}

	}

	/**
	 * This Test case, test the '2_UndeclaredVariableDecl.txt'
	 */
	@Test
	public void testCaseUndeclaredVariableDecl() {
		int index = 2;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseUndeclaredVariableDecl"));

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
			SemanticDriver semanticDriver = new SemanticDriver();
			semanticDriver.execute(testFilePath, index);

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
	 * This Test case, test the '3_DuplicateClassDeclError.txt'
	 */
	@Test
	public void testCaseDuplicateClassDeclError() {
		int index = 3;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseDuplicateClassDeclError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '4_VariableError.txt'
	 */
	@Test
	public void testCaseVariableError() {
		int index = 4;
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
	 * This Test case, test the '5_UndefinedMember.txt'
	 */
	@Test
	public void testCaseUndefinedMember() {
		int index = 5;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseUndefinedMember"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '6_DuplicateIDs.txt'
	 */
	@Test
	public void testCaseDuplicateIDs() {
		int index = 6;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseDuplicateIDs"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '7_DuplicateParamsError.txt'
	 */
	@Test
	public void testCaseDuplicateParamsError() {
		int index = 7;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseDuplicateParamsError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	
	/**
	 * This Test case, test the '8_DuplicateClassMemberError.txt'
	 */
	@Test
	public void testCaseDuplicateClassMemberError() {
		int index = 8;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseDuplicateClassMemberError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	

	/**
	 * This Test case, test the '9_TYPE_CHECK_RETURN_TYPE.txt'
	 */
	@Test
	public void testCaseTypeCheckReturnType() {
		int index = 9;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseTypeCheckReturnType"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '10_VariableDefinitionError.txt'
	 */
	@Test
	public void testCaseVariableDefinitionError() {
		int index = 10;
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
	 * This Test case, test the '11_ArrayIndiceError.txt'
	 */
	@Test
	public void testCaseArrayIndiceError() {
		int index = 11;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseArrayIndiceError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '12_UndefinedID.txt'
	 */
	@Test
	public void testCaseUndefinedID() {
		int index = 12;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseUndefinedID"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '13_FunctionCall.txt'
	 */
	@Test
	public void testCaseFunctionCall() {
		int index = 13;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseFunctionCall"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '14_FunctionInvocationError.txt'
	 */
	@Test
	public void testCaseFunctionInvocationError() {
		int index = 14;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseFunctionInvocationError"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '15_StatementError.txt'
	 */
	@Test
	public void testCaseStatementError() {
		int index = 15;
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
	 * This Test case, test the '16_TypeChecking.txt'
	 */
	@Test
	public void testCaseTypeChecking() {
		int index = 16;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseTypeChecking"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '17_workingSample.txt'
	 */
	@Test
	public void testCaseWorkingSample() {
		int index = 17;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseWorkingSample"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '18_ValidTestCase1.txt'
	 */
	@Test
	public void testCaseValidTestCase1() {
		int index = 18;
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
	 * This Test case, test the '19_WrongFunctionCall.txt'
	 */
	@Test
	public void testCaseWrongFunctionCall() {
		int index = 19;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseWrongFunctionCall"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
}
