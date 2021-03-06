package com.project.compiler.Driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.compiler.Ast.ProgNode;
import com.project.compiler.CodeGeneration.MoonCodeGenerator;
import com.project.compiler.Config.Enums;
import com.project.compiler.Lex.LexicalAnalyzer;
import com.project.compiler.Semantic.Errors;
import com.project.compiler.Semantic.SemanticAnalysis;
import com.project.compiler.Semantic.SymbolTable;
import com.project.compiler.Syntax.SyntacticAnalyzerRoughUpdate2;
import com.project.compiler.Utilities.FileStorage;
import com.project.compiler.Utilities.LoggerConfiguration;
import com.project.compiler.Utilities.PrintUtil;
import com.project.compiler.Visitor.ASTPrinterVisitor;



/**
 * Driver Class for Moon Code Generator
 * 
 * @author SajjadAshrafCan
 *
 */
public class CodeGenerationDriver extends baseDriver {

	private static String className = LexDriver.class.getSimpleName();
	private static final Logger LOGGER = new LoggerConfiguration().configuredLogger(Logger.getLogger(className));
	private static String genericPath = "TestCases\\CodeGen\\%d\\%s";
	private static LexicalAnalyzer lexicalAnalyzer = null;
	SyntacticAnalyzerRoughUpdate2 sParser = null;
	private static FileStorage fileStorage= new FileStorage();
	private static ArrayList<Errors> errorList = new ArrayList<Errors>();
	private static boolean sementicActionApporch = false;

	private static String[] testCases = { 
			String.format(genericPath, 1, "1_AirthExpression.txt"),
			String.format(genericPath, 2, "2_Expression.txt"), 
			String.format(genericPath, 3, "3_Fibonacci.txt"),
			String.format(genericPath, 4, "4_ARRAY_Test_1.txt"), 
			String.format(genericPath, 5, "5_ARRAY_Test_2.txt"),
			String.format(genericPath, 6, "6_ARRAY_Test_3.txt"),
			String.format(genericPath, 7, "7_Class_Array_Test.txt"),
			String.format(genericPath, 8, "8_FOR_IF_Test.txt"),
			String.format(genericPath, 9, "9_FOR_Test.txt"),
			String.format(genericPath, 10, "10_IF_TEST.txt"),
			String.format(genericPath, 11, "11_Inheritence.txt"),
			String.format(genericPath, 12, "12_Math_Test.txt"),
			String.format(genericPath, 13, "13_Class_Test.txt"),
			String.format(genericPath, 14, "14_Nested_LOOP.txt")

	};

	/**
	 * Main Method The Execute the Moon Code Generator
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		sementicActionApporch =false;
		CodeGenerationDriver codeGenerationDriver = new CodeGenerationDriver();
		codeGenerationDriver.execute();

	}
	
	@Override
	void execute() {
		try
		{
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
	
	private void saveErrorCollections(ArrayList<Errors> errorList, String allerrorsLogPath, String errorsLogPath, String errorContent)
	{
		//Saving errors		
		//fileStorage.saveTxtFile(errorsLogPath, errorContent);
		//System.out.println("\r\n");
		//LOGGER.info(Enums.ModuleType.SEMANTIC.toString()+" Errors are save to: "+errorsLogPath);
		
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
	
	public void execute(String path, int index) {
		
		LOGGER.info("..........................Executing Test Case " + (++index) + " ..........................");
		LOGGER.info("testCases file path: " + path);

		// AST Implementation
		String abstractSyntaxTreePath = path.replaceFirst("[^\\\\]*$", "Ast.txt");
		String astSymbolTablePath = path.replaceFirst("[^\\\\]*$", "SymbolTable.txt");
		String astErrorsLogPath = path.replaceFirst("[^\\\\]*$", "Errors.txt");
		String astAllerrorsLogPath = path.replaceFirst("[^\\\\]*$", "AllErrors.txt");
		String astMemSizetblPath = path.replaceFirst("[^\\\\]*$", "MemorySize.txt");
		String astTagMoonCodePath = path.replaceFirst("[^\\\\]*$", "TagMoonCode.m");
		String astMoonCodePath = path.replaceFirst("[^\\\\]*$", "MoonCode.m");
		//String astStackMoonCodePath = path.replaceFirst("[^\\\\]*$", "StackMoonCode.m");
		
		ProgNode prog = new ProgNode();
		lexicalAnalyzer = new LexicalAnalyzer(path);
		sParser = new SyntacticAnalyzerRoughUpdate2(lexicalAnalyzer);
		sParser.parse(prog);
		
		//Saving AST or Table
		fileStorage.saveTxtFile(abstractSyntaxTreePath, prog.toString());
		LOGGER.info("Abstract Syntax Tree is save to: "+abstractSyntaxTreePath);
		
		
		try
		{			
			MoonCodeGenerator moonCodeGenerator= new MoonCodeGenerator();
			moonCodeGenerator.starVisiting(prog);
			
			//Saving AST or Table
			fileStorage.saveTxtFile(abstractSyntaxTreePath, moonCodeGenerator.getAstAfterTCandCSPVisitor());
			LOGGER.info("Abstract Syntax Tree (After Programing Reconstruction and Type Checking) is save to: "+abstractSyntaxTreePath);
			
			//Saving AST Symbol Table  		
			fileStorage.saveTxtFile(astSymbolTablePath, moonCodeGenerator.getSymbolTableStr());
			LOGGER.info("Symbol table is save to: "+astSymbolTablePath);
			
			//Saving Memory Size table
			fileStorage.saveTxtFile(astMemSizetblPath, moonCodeGenerator.getComputeMemSizeStr());
			LOGGER.info("Memory Size table is save to: "+astMemSizetblPath);
					
			//Saving Moon Code Tag Implementation
			fileStorage.saveTxtFile(astTagMoonCodePath, moonCodeGenerator.getTagBaseCode());
			LOGGER.info("Moon Code Tag Implementation is save to: "+astTagMoonCodePath);
			
			//Saving Moon Code Stack Implementation
			//fileStorage.saveTxtFile(astStackMoonCodePath, moonCodeGenerator.getStackBaseCode());
			//LOGGER.info("Moon Code Stack Implementation is save to: "+astStackMoonCodePath);
			
			//Saving Moon Code
			fileStorage.saveTxtFile(astMoonCodePath, moonCodeGenerator.getStackBaseCode());
			LOGGER.info("Moon Code is save to: "+astMoonCodePath);
			
			//Collecting errors
			errorList= new ArrayList<Errors>();
			errorList.addAll(lexicalAnalyzer.getErrorList());
			errorList.addAll(sParser.getErrorList());
			errorList.addAll(moonCodeGenerator.getErrorList());
			saveErrorCollections(errorList, astAllerrorsLogPath, astErrorsLogPath, moonCodeGenerator.getErrorsLog());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		if(sementicActionApporch)
		{
						
			String errorsLogPath = path.replaceFirst("[^\\\\]*$", "Errors.txt");
			String allerrorsLogPath = path.replaceFirst("[^\\\\]*$", "AllErrors.txt");
			String symbolTablePath = path.replaceFirst("[^\\\\]*$", "SymbolTable.txt");
			String symbolTableHtmlPath = path.replaceFirst("[^\\\\]*$", "SymbolTable.html");
			String moonCodePath = path.replaceFirst("[^\\\\]*$", "MoonCode.m");
			
			// 1st order parsing						
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
			
			//Saving Moon Code		
			fileStorage.saveTxtFile(moonCodePath, String.format("%s \r\n %s", sParser.semantics.dataToString(), sParser.semantics.codeToString()));
			LOGGER.info("Moon Code save to: "+moonCodePath);
					
			//Collecting errors
			errorList.addAll(lexicalAnalyzer.getErrorList());
			errorList.addAll(sParser.getErrorList());
			errorList.addAll(sParser.semantics.getErrorList());
			saveErrorCollections(errorList, allerrorsLogPath, errorsLogPath, sParser.semantics.getErrorsLog());
			
		}		
		
	}
	

}
