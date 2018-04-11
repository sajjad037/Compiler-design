package com.project.compiler.Driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.compiler.Ast.ProgNode;
import com.project.compiler.Config.Enums;
import com.project.compiler.Lex.LexicalAnalyzer;
import com.project.compiler.Semantic.Errors;
import com.project.compiler.Semantic.SemanticAnalysis;
import com.project.compiler.Semantic.SymbolTable;
import com.project.compiler.Syntax.SyntacticAnalyzerRoughUpdate2;
import com.project.compiler.Utilities.FileStorage;
import com.project.compiler.Utilities.LoggerConfiguration;
import com.project.compiler.Utilities.PrintUtil;


/**
 * Driver Class for Semantic Analyzer
 * 
 * @author SajjadAshrafCan
 *
 */
public class SemanticDriver extends baseDriver {

	private static String className = LexDriver.class.getSimpleName();
	private static final Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private static String genericPath = "TestCases\\Semantic\\%d\\%s";
	private static LexicalAnalyzer lexicalAnalyzer = null;
	private static FileStorage fileStorage= new FileStorage();
	private static ArrayList<Errors> errorList = new ArrayList<Errors>();

	private static String[] testCases = { 
			String.format(genericPath, 1, "1_profExample.txt"),
//			String.format(genericPath, 1, "1_workingSample.txt"),			
//			String.format(genericPath, 1, "1_UndefinedID.txt"),
//			String.format(genericPath, 2, "2_UndeclaredVariableDecl.txt"), 
//			String.format(genericPath, 3, "3_DuplicateClassDeclError.txt"),
//			String.format(genericPath, 4, "4_VariableError.txt"), 
//			String.format(genericPath, 5, "5_UndefinedMember.txt"),
//			String.format(genericPath, 6, "6_DuplicateIDs.txt"),
//			String.format(genericPath, 7, "7_DuplicateParamsError.txt"),
//			String.format(genericPath, 8, "8_DuplicateClassMemberError.txt"),
//			String.format(genericPath, 9, "9_TYPE_CHECK_RETURN_TYPE.txt"),
//			String.format(genericPath, 10, "10_VariableDefinitionError.txt"),
//			String.format(genericPath, 11, "11_ArrayIndiceError.txt"),
//			String.format(genericPath, 12, "12_combinedSemanticErrors.txt"),
//			String.format(genericPath, 13, "13_FunctionCall.txt"),
//			String.format(genericPath, 14, "14_FunctionInvocationError.txt"),
//			String.format(genericPath, 15, "15_StatementError.txt"),
//			String.format(genericPath, 16, "16_TypeChecking.txt"),
//			String.format(genericPath, 17, "17_ValidTestCase.txt"),
//			String.format(genericPath, 18, "18_ValidTestCase1.txt"),
//			String.format(genericPath, 19, "19_WrongFunctionCall.txt")

	};

	/**
	 * Main Method The Execute the Semantic Analyzer Program
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		SemanticDriver semanticDriver = new SemanticDriver();
		semanticDriver.execute();

	}
	
	@Override
	void execute() {
		try
		{
			LOGGER.info(".. Starting ...");
			int index = 0;
			for (String path : testCases) {

				LOGGER.info("..........................Executing Test Case " + (++index) + " ..........................");
				LOGGER.info("testCases file path: " + path);

				ProgNode prog = new ProgNode();
				
				lexicalAnalyzer = new LexicalAnalyzer(path);			
				String errorsLogPath = path.replaceFirst("[^\\\\]*$", Enums.ModuleType.SEMANTIC_ACTION.toString() + "_Errors.txt");
				String allerrorsLogPath = path.replaceFirst("[^\\\\]*$", Enums.ModuleType.SEMANTIC_ACTION.toString() + "_AllErrors.txt");
				String symbolTablePath = path.replaceFirst("[^\\\\]*$", Enums.ModuleType.SEMANTIC_ACTION.toString() + "_SymbolTable.txt");
				String symbolTableHtmlPath = path.replaceFirst("[^\\\\]*$", Enums.ModuleType.SEMANTIC_ACTION.toString() + "_SymbolTable.html");
				
				// 1st order parsing
				SyntacticAnalyzerRoughUpdate2 sParser = new SyntacticAnalyzerRoughUpdate2(lexicalAnalyzer);
				sParser.parse(prog);			
				SymbolTable firstTable = sParser.semantics.mainTable.clone();
				PrintUtil.isLog = true;
							
				// 2nd order parsing
				lexicalAnalyzer = new LexicalAnalyzer(path);
				sParser = new SyntacticAnalyzerRoughUpdate2(lexicalAnalyzer);
				prog = new ProgNode();
				sParser.parse(prog, firstTable);
				//sParser.semantics.printSymbolTable();
				ArrayList<String> symTblStr = sParser.semantics.symbolTableToString();
				
				//Saving Simple SymbolTable		
				fileStorage.saveTxtFile(symbolTablePath, symTblStr.get(0));
				LOGGER.info("Simple Symbol Table save to: "+symbolTablePath);
				
				//Saving HTML SymbolTable		
				fileStorage.saveTxtFile(symbolTableHtmlPath, symTblStr.get(1));
				LOGGER.info("HTML Symbol Table save to: "+symbolTableHtmlPath);
				
				//sParser.semantics.printData();
				//sParser.semantics.printCode();
				
				//Collecting errors
				errorList.addAll(lexicalAnalyzer.getErrorList());
				errorList.addAll(sParser.getErrorList());
				errorList.addAll(sParser.semantics.getErrorList());
				saveErrorCollections(errorList, allerrorsLogPath, errorsLogPath, sParser.semantics.getErrorsLog());
				
				// AST Implementation
				String abstractSyntaxTreePath = path.replaceFirst("[^\\\\]*$", Enums.ModuleType.SEMANTIC_AST.toString() + "_Ast.txt");
				String astSymbolTablePath = path.replaceFirst("[^\\\\]*$", Enums.ModuleType.SEMANTIC_AST.toString() + "_SymbolTable.txt");
				String astErrorsLogPath = path.replaceFirst("[^\\\\]*$", Enums.ModuleType.SEMANTIC_AST.toString() + "_Errors.txt");
				String astAllerrorsLogPath = path.replaceFirst("[^\\\\]*$", Enums.ModuleType.SEMANTIC_AST.toString() + "_AllErrors.txt");

				
				SemanticAnalysis semanticAnalysis= new SemanticAnalysis();
				semanticAnalysis.starVisiting(prog);
				
				//Saving AST or Table  		
				fileStorage.saveTxtFile(abstractSyntaxTreePath, prog.toString());
				LOGGER.info("Abstract Syntax Tree is save to: "+abstractSyntaxTreePath);
				
				//Saving AST Symbol Table  		
				fileStorage.saveTxtFile(astSymbolTablePath, semanticAnalysis.getSymbolTableStr());
				LOGGER.info("Abstract Syntax Tree is save to: "+astSymbolTablePath);
		
				//Collecting errors
				errorList= new ArrayList<Errors>();
				errorList.addAll(lexicalAnalyzer.getErrorList());
				errorList.addAll(sParser.getErrorList());
				errorList.addAll(semanticAnalysis.getErrorList());
				saveErrorCollections(errorList, astAllerrorsLogPath, astErrorsLogPath, semanticAnalysis.getErrorsLog());


			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "an exception was thrown", e);
			e.printStackTrace();
		}
		
	}
	
	private void saveErrorCollections(ArrayList<Errors> errorList, String allerrorsLogPath, String errorsLogPath, String errorContent)
	{
		//Saving errors		
		fileStorage.saveTxtFile(errorsLogPath, errorContent);
		System.out.println("\r\n");
		LOGGER.info(Enums.ModuleType.SEMANTIC.toString()+" Errors are save to: "+errorsLogPath);
		
		//Sorting Errors by line number
		Collections.sort(errorList, new Comparator<Errors>() {

			@Override
			public int compare(Errors o1, Errors o2) {
				return (int) (o1.getLineNumber() - (o2.getLineNumber()));

			}
		});
		
		//Saving All errors
		fileStorage.saveTxtFile(allerrorsLogPath, getFormatedErros(errorList));
		System.out.println("\r\n");
		LOGGER.info("All Errors are save to: "+allerrorsLogPath);
	}
	

}
