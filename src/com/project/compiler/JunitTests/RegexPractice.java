package com.project.compiler.JunitTests;

import java.io.IOException;

import com.project.compiler.Model.Token;

public class RegexPractice {

	
	static String value = "";
	
	public static void main(String[] arg) throws SecurityException, IOException {
//		System.out.println(String.format("letter: %s", LexicalStatic.REGEX_LETTER));
//		System.out.println(String.format("digit: %s", LexicalStatic.REGEX_DIGIT));
//		System.out.println(String.format("nonZero: %s", LexicalStatic.REGEX_NONZERO));
//		System.out.println(String.format("alphanum: %s", LexicalStatic.REGEX_ALPHANUM));
//		System.out.println(String.format("fraction: %s", LexicalStatic.REGEX_FRACTION));
//		System.out.println(String.format("integer: %s", LexicalStatic.REGEX_INTEGER));
//		System.out.println(String.format("floatType: %s", LexicalStatic.REGEX_FLOAT));
//		System.out.println(String.format("id: %s", LexicalStatic.REGEX_ID));
//				
//		value="a";
//		System.out.println(String.format("letter: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_LETTER, value) ));
//		
//		value="0";
//		System.out.println(String.format("digit: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_DIGIT, value) ));
//		
//		value="1";
//		System.out.println(String.format("nonZero: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_NONZERO, value) ));
//		
//		value="_";
//		System.out.println(String.format("alphanum: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_ALPHANUM, value) ));
//		
//		value="A";
//		System.out.println(String.format("alphanum: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_ALPHANUM, value) ));
//		
//		value="1";
//		System.out.println(String.format("alphanum: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_ALPHANUM, value) ));
//		
//		value=".0000";
//		System.out.println(String.format("fraction: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_FRACTION, value) ));
//		
//		value=".0001";
//		System.out.println(String.format("fraction: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_FRACTION, value) ));
//		
//		value="10";
//		System.out.println(String.format("integer: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_INTEGER, value) ));
//		
//		value="10.0001e-10";
//		System.out.println(String.format("floatType: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_FLOAT, value) ));
//		
//		value="10.0001";
//		System.out.println(String.format("floatType: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_FLOAT, value) ));
//		
//		value="10.000";
//		System.out.println(String.format("floatType: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_FLOAT, value) ));
//		
//		value="A0_a_b0A0_";
//		System.out.println(String.format("id: %s, %s", value, Pattern.matches(LexicalStatic.REGEX_ID, value) ));
		//String path ="TestCases\\Syntactic\\18\\18_ProgramGivenInAssignment2.txt";
		//String grammarParsingLogPath = path.replaceFirst("[^\\\\]*$", "grammarParsing.log");
		//System.out.println(grammarParsingLogPath);
		
		Token t = new Token();
		Token t2 = new Token();
		//t.setColumn(10);
		//System.out.println(t.getColumn());
		//test(t, t2);
		//t.setColumn(11);
		if((t = test(t))!= null)
		{
			System.out.println("Not Null"+t.getColumn());	
		}
		else		
		System.out.println("Null"+t2.getColumn());
	}
	
	// private static void test(Token t, Token t2)
	// {
	// t.setColumn(11);
	// }
	//
	private static Token test(Token t)
	{
		t.setColumn(11);
		if(t.getColumn() == 11)
		{
			return t;
		}
		else 
			return null;
		
	}

}
