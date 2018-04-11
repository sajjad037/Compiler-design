package com.project.compiler.Driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.compiler.Config.Enums;
import com.project.compiler.Config.Enums.TokenPrintFormat;
import com.project.compiler.Lex.LexicalAnalyzer;
import com.project.compiler.Model.Token;
import com.project.compiler.Utilities.FileStorage;
import com.project.compiler.Utilities.LoggerConfiguration;

/**
 * Driver Class for Lexical Analyzer
 * 
 * @author SajjadAshrafCan
 *
 */
public class LexDriver extends baseDriver {

	private static String className = LexDriver.class.getSimpleName();
	private static final Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private static String genericPath = "TestCases\\Lexical\\%d\\%s";
	private static LexicalAnalyzer lexicalAnalyzer = null;
	private static FileStorage fileStorage= new FileStorage();

	private static String[] testCases = { 
			String.format(genericPath, 1, "1_LEXER_COMMENT_ERROR.txt"),
			String.format(genericPath, 2, "2_LEXER_TOKENS.txt"),
			String.format(genericPath, 3, "3_COMMENT_Test.txt"), 
			String.format(genericPath, 4, "4_ERROR_Test.txt"),
			String.format(genericPath, 5, "5_VALID_IDENTIFIERS_TEST.txt"),
			String.format(genericPath, 6, "6_VALID_NUMBERS_TEST.txt"),
			String.format(genericPath, 7, "7_VALID_RESERVE_WORD_TEST.txt")
	};
	private static ArrayList<Token> tokenList = null;

	/**
	 * Main Method The Execute the Lex Driver Program
	 * 
	 * @param arg
	 * @throws IOException
	 * @throws SecurityException
	 */
	public static void main(String[] arg) throws SecurityException, IOException {

		LexDriver lexDriver = new LexDriver();
		lexDriver.execute();
	}

	@Override
	void execute() {
		try {
			LOGGER.info(".. Starting ...");
			int index = 0;
			for (String path : testCases) {
				
				LOGGER.info("..........................Executing Test Case "+(++index)+" ..........................");
				LOGGER.info("testCases file path: "+path);
				
				lexicalAnalyzer = new LexicalAnalyzer(path);
				tokenList = new ArrayList<Token>();
				Token token = null;
				do{

					token = lexicalAnalyzer.getNextAvialableToken();
					tokenList.add(token);

				} 
				while (token.getType() != Enums.TokenType.EOF);

				//Save Tokens
				String tokenOutputPath = String.format(genericPath, index, Enums.ModuleType.LEXICAL.toString()+"_Output.txt");
				String tokenResultText =printTokens("Tokens", tokenList, TokenPrintFormat.REQUIRED);
				fileStorage.saveTxtFile(tokenOutputPath, tokenResultText);
				System.out.println(tokenResultText);
				System.out.println("\r\n");
				LOGGER.info("Tokens save to: "+tokenOutputPath);
				
				//Save Lex Errors in Files
				String errorOutputPath = String.format(genericPath, index, Enums.ModuleType.LEXICAL.toString()+"_ErrorOutput.txt");
				String errorsResultText =printTokens("Errors", lexicalAnalyzer.getErrorTokenList(),  TokenPrintFormat.REQUIRED);
				fileStorage.saveTxtFile(errorOutputPath, errorsResultText);
				System.out.println(errorsResultText);
				System.out.println("\r\n");
				LOGGER.info("Lex Errors save to: "+errorOutputPath);
				
				//Save AtoCC
				String atoCCPath = String.format(genericPath, index, Enums.ModuleType.LEXICAL.toString()+"_AtoCCOutput.txt");
				String atoCCResultText =printTokens("Tokens", tokenList, TokenPrintFormat.ATOCC);
				fileStorage.saveTxtFile(atoCCPath, atoCCResultText);
				System.out.println(atoCCResultText);
				System.out.println("\r\n");
				LOGGER.info("AtoCC Format save to: "+tokenOutputPath);
				
				
			}
			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "an exception was thrown", e);
			e.printStackTrace();
		}
		
	}

	

}
