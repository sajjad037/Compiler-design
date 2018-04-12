package com.project.compiler.Driver;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.compiler.Ast.ProgNode;
import com.project.compiler.Config.Enums;
import com.project.compiler.Lex.LexicalAnalyzer;
import com.project.compiler.Syntax.SyntacticAnalyzerRoughUpdate;
import com.project.compiler.Utilities.FileStorage;
import com.project.compiler.Utilities.LoggerConfiguration;

/**
 * Driver Class for Syntactic Analyzer
 * 
 * @author SajjadAshrafCan
 *
 */
public class SyntacticDriver extends baseDriver {

	private static String className = LexDriver.class.getSimpleName();
	private static final Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private static String genericPath = "TestCases\\Syntactic\\%d\\%s";
	private static LexicalAnalyzer lexicalAnalyzer = null;
	private static FileStorage fileStorage= new FileStorage();

	private static String[] testCases = { 
			String.format(genericPath, 1, "1_Sample.txt"),
//			String.format(genericPath, 1, "1_workingSample.txt"),
//			String.format(genericPath, 1, "1_SYNTAX_ERROR.txt"),
			String.format(genericPath, 2, "2_NoClassDecl.txt"), 
			String.format(genericPath, 3, "3_SYNTAX_VALID.txt"),
			String.format(genericPath, 4, "4_NoProgBody.txt"), 
			String.format(genericPath, 5, "5_VariableError.txt"),
			String.format(genericPath, 6, "6_VariableDefinitionError.txt"),
			String.format(genericPath, 7, "7_IF_Conditional_Statements.txt"),
			String.format(genericPath, 8, "8_FOR_Loop_Statement.txt"),
			String.format(genericPath, 9, "9_ClassMembers.txt"),
			String.format(genericPath, 10, "10_ClassDeclError.txt"),
			String.format(genericPath, 11, "11_ArraySizeError.txt"),
			String.format(genericPath, 12, "12_ComplexExpression_Arrays.txt"),
			String.format(genericPath, 13, "13_FuncBodyError.txt"),
			String.format(genericPath, 14, "14_StatementError.txt"),
			String.format(genericPath, 15, "15_ValidTestCase1.txt"),
			String.format(genericPath, 16, "16_ValidTestCase2.txt"),
			String.format(genericPath, 17, "17_ValidTestCase3.txt"),
			String.format(genericPath, 18, "18_ProgramGivenInAssignment2.txt")

	};

	/**
	 * Main Method The Execute the Syntactic Analyzer Program
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SyntacticDriver syntacticDriver = new SyntacticDriver();
		syntacticDriver.execute();
	}

	@Override
	void execute() {
		try {
			LOGGER.info(".. Starting ...");
			int index = 0;
			for (String path : testCases) {
				++index;
				execute(path, index);

			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "an exception was thrown", e);
			e.printStackTrace();
		}

	}
	
	public void execute(String path, int index) {
		LOGGER.info(
				"..........................Executing Test Case " + (index) + " ..........................");
		LOGGER.info("testCases file path: " + path);

		lexicalAnalyzer = new LexicalAnalyzer(path);
		String syntax_GrammarParsingLogPath = path.replaceFirst("[^\\\\]*$",
				Enums.ModuleType.SYNTAX.toString() + "_grammarParsing.txt");
		String syntax_ErrorsLogPath = path.replaceFirst("[^\\\\]*$",
				Enums.ModuleType.SYNTAX.toString() + "_errors.txt");
		String syntax_DerivationLogPath = path.replaceFirst("[^\\\\]*$",
				Enums.ModuleType.SYNTAX.toString() + "_derivation.txt");
		String syntax_AbstractSyntaxTreePath = path.replaceFirst("[^\\\\]*$",
				Enums.ModuleType.SYNTAX.toString() + "_abstractSyntaxTree.txt");
		ProgNode prog = new ProgNode();

		SyntacticAnalyzerRoughUpdate sParser = new SyntacticAnalyzerRoughUpdate(lexicalAnalyzer);
		sParser.parse(prog);

		// save derivation
		fileStorage.saveTxtFile(syntax_DerivationLogPath, sParser.getDerivationLog());
		System.out.println("\r\n");
		LOGGER.info("Derivations are save to: " + syntax_DerivationLogPath);

		// save Errors
		fileStorage.saveTxtFile(syntax_ErrorsLogPath, sParser.getErrorsLog());
		System.out.println("\r\n");
		LOGGER.info("Errors are save to: " + syntax_ErrorsLogPath);

		// save Grammar Parsing
		fileStorage.saveTxtFile(syntax_GrammarParsingLogPath, sParser.getGrammarParsingLog());
		System.out.println("\r\n");
		LOGGER.info(" Grammar Parsing logs are save to: " + syntax_GrammarParsingLogPath);

		// save Grammar Parsing
		fileStorage.saveTxtFile(syntax_AbstractSyntaxTreePath, prog.toString());
		System.out.println("\r\n");
		LOGGER.info("Abstract Syntax Tree is save to: " + syntax_AbstractSyntaxTreePath);
	}

}
