package com.project.compiler.JunitTests;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import com.project.compiler.Driver.CodeGenerationDriver;
import com.project.compiler.Utilities.LoggerConfiguration;

/**
 * 
 * @author SajjadAshrafCan
 *
 */
public class TestCasesCodeGenFileMatch extends baseTest {

	private String className = TestCasesCodeGenFileMatch.class.getSimpleName();
	private Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private String genericPathTestCases = "TestCases\\CodeGen\\%d\\%s";
	private String TEST_FILE = "MoonCode.m";

	private String[] testCases = { 
			String.format(genericPathTestCases, 1, "1_AirthExpression.txt"),
			String.format(genericPathTestCases, 2, "2_Expression.txt"), 
			String.format(genericPathTestCases, 3, "3_Fibonacci.txt"),
			String.format(genericPathTestCases, 4, "4_ARRAY_Test_1.txt"), 
			String.format(genericPathTestCases, 5, "5_ARRAY_Test_2.txt"),
			String.format(genericPathTestCases, 6, "6_ARRAY_Test_3.txt"),
			String.format(genericPathTestCases, 7, "7_Class_Array_Test.txt"),
			String.format(genericPathTestCases, 8, "8_FOR_IF_Test.txt"),
			String.format(genericPathTestCases, 9, "9_FOR_Test.txt"),
			String.format(genericPathTestCases, 10, "10_IF_TEST.txt"),
			String.format(genericPathTestCases, 11, "11_Inheritence.txt"),
			String.format(genericPathTestCases, 12, "12_Math_Test.txt"),
			String.format(genericPathTestCases, 13, "13_Class_Test.txt"),
			String.format(genericPathTestCases, 14, "14_Nested_LOOP.txt")
	};

	private String genericPathExpectedResult = "JunitResults\\CodeGen\\%d\\%s";

	/**
	 * This Test case, test the '1_AirthExpression'
	 */
	@Test
	public void testCaseAirthExpression() {

		int index = 1;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseAirthExpression"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}

	}

	/**
	 * This Test case, test the '2_Expression.txt'
	 */
	@Test
	public void testCaseExpression() {
		int index = 2;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseExpression"));

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
			CodeGenerationDriver codeGenerationDriver = new CodeGenerationDriver();
			codeGenerationDriver.execute(testFilePath, index);

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
	 * This Test case, test the '3_Fibonacci.txt'
	 */
	@Test
	public void testCaseFibonacci() {
		int index = 3;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseFibonacci"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '4_ARRAY_Test_1.txt'
	 */
	@Test
	public void testCaseArrayTest1() {
		int index = 4;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseArrayTest1"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '5_ARRAY_Test_2.txt'
	 */
	@Test
	public void testCaseArrayTest2() {
		int index = 5;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseArrayTest2"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '6_ARRAY_Test_3.txt'
	 */
	@Test
	public void testCaseArrayTest3() {
		int index = 6;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseArrayTest3"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '7_Class_Array_Test.txt'
	 */
	@Test
	public void testCaseClassArrayTest() {
		int index = 7;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseClassArrayTest"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	
	/**
	 * This Test case, test the '8_FOR_IF_Test.txt'
	 */
	@Test
	public void testCaseForIfTest() {
		int index = 8;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseForIfTest"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	

	/**
	 * This Test case, test the '9_FOR_Test.txt'
	 */
	@Test
	public void testCaseForTest() {
		int index = 9;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseForTest"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '10_IF_TEST.txt'
	 */
	@Test
	public void testCaseIftest() {
		int index = 10;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseIftest"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '11_Inheritence.txt'
	 */
	@Test
	public void testCaseInheritence() {
		int index = 11;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseInheritence"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '12_Math_Test.txt'
	 */
	@Test
	public void testCaseMathTest() {
		int index = 12;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseMathTest"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
	/**
	 * This Test case, test the '13_Class_Test.txt'
	 */
	@Test
	public void testCaseClassTest() {
		int index = 13;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseClassTest"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}

	/**
	 * This Test case, test the '14_Nested_LOOP.txt'
	 */
	@Test
	public void testCaseNestedLoop() {
		int index = 14;
		boolean status = false;

		try {
			assert (commonExecution(index, "testCaseNestedLoop"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			LOGGER.info("Unit test Cases statu: " + status);
			assertEquals(true, false);
		}
	}
	
}
