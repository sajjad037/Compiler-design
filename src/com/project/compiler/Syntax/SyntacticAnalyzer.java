package com.project.compiler.Syntax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.project.compiler.Ast.*;
import com.project.compiler.Config.LexicalStatic;
import com.project.compiler.Config.Enums.ModuleType;
import com.project.compiler.Lex.LexicalAnalyzer;
import com.project.compiler.Model.Token;
import com.project.compiler.Semantic.Errors;


/**
 * This Class has Syntactic Analyzer Implementation
 * 
 * @author SajjadAshrafCan
 *
 */
public class SyntacticAnalyzer {

	// logging
	//private String className = LexicalAnalyzer.class.getSimpleName();
	private StringBuilder derivationTree = new StringBuilder("prog");
	private StringBuilder derivationLog = new StringBuilder();
	private StringBuilder errorsLog = new StringBuilder();
	private StringBuilder grammarParsingLog = new StringBuilder();
	private ArrayList<Errors> errorList = new ArrayList<Errors>();
	
	private String SYNTAX ="SYNTAX";
	private String ERROR ="ERROR";
	private String WARNING ="WARNING";
	private String INFO ="INFO";
	LexicalAnalyzer lexer = null;
	
	//private AST ast = new AST(); 

	// Syntax Analysis
	FFSets firstAndFollw = null;
	Token token = null;
	Token prevToken = new Token();

	/**
	 * Print the Grammer
	 * @param grammarLHS
	 * @param grammarRHS
	 */
	private void printGrammar(String grammarLHS, String grammarRHS) {
		derivationTree = new StringBuilder(derivationTree.toString().replaceFirst(grammarLHS, grammarRHS));
		derivationLog.append(String.format("[SYNTAX] %s \r\n", derivationTree.toString()));
	}
	
	/**
	 * Print log
	 * @param tag
	 * @param msg
	 */
	private void print(String tag, String msg){
		info(tag, msg);
	}
	
	/**
	 * Print Error or Warning
	 * @param tag
	 * @param msg
	 */
	private void errorOrWarning(String tag, String msg, int lineNumber){
		errorsLog.append(String.format("[%s] %s: %s \r\n", SYNTAX, tag, msg));
		//String description = String.format("%s", msg);
		errorList.add(new Errors(lineNumber, msg, tag, ModuleType.SYNTAX));
	}
	
	/**
	 * log warning
	 * @param tag
	 * @param msg
	 */
	private void warning(String tag, String msg){
		grammarParsingLog.append(String.format("[%s] %s: %s \r\n", tag, WARNING, msg));
	}
	
	/**
	 * log info
	 * @param tag
	 * @param msg
	 */
	private void info(String tag, String msg){
		grammarParsingLog.append(String.format("[%s] %s: %s \r\n", tag, INFO, msg));
	}
	
	
	/**
	 * Constructor 
	 * Initializing the Global variables
	 * @param lexer
	 */
	public SyntacticAnalyzer(LexicalAnalyzer lexer) {
		this.lexer = lexer;
		firstAndFollw = new FFSets();	
	}

	/**
	 * Get next available token
	 */
	private void getNextToken() {
		prevToken = token;
		try {
			token = lexer.getNextAvialableToken();
			System.out.println(token.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start parsing process
	 * @return
	 */
	public boolean parse(ProgNode prog) {
		getNextToken();
		if (token == null) {
			String msg = "Error in Input File";
			print(SYNTAX, msg);
			return false;
		}
		return startSymbol(prog);
	}

	/**
	 * Start symbol of program which is prog
	 * @return
	 */
	private boolean startSymbol(ProgNode prog) {
		printGrammar("prog", "prog");
		// semantics.moonData.add("%DATA");
		// semantics.moonCode.add("%CODE");
		return prog(prog);
	}

	/**
	 * prog -> classDecl_List funcDef_List 'program' funcBody ';' 
	 * @return
	 */
	private boolean prog(ProgNode prog) {
		printGrammar("prog", "classDecl_List funcDef_List 'program' funcBody ';'");
		IdNode semiColon = new IdNode(), programNode = new IdNode();
		FuncDefNode funcDefNode = new FuncDefNode();
			FuncDefListNode funcDefListNode = new FuncDefListNode();
			ClassNode classNode = new ClassNode();
			ClassListNode classlist = new ClassListNode();
			StatBlockNode statBlockNode = new StatBlockNode();
			
			// semantics.progDecl();
			 if (classDecl_List(classNode, classlist) 
					 && funcDef_List(funcDefNode, funcDefListNode) 
					 && matchTokenValue("program", programNode) 
					 && funcBody(statBlockNode) 
					 && matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, semiColon)) {
				 print(SYNTAX, "prog -> classDecl_List funcDef_List 'program' funcBody ';'");
				 //prog = new ProgNode(classlist, funcDefListNode, statBlockNode);
				 prog.setProgNode(classlist, funcDefListNode, statBlockNode);
			 return true;
			} else
			return false;
		}
		
		
	/**
	 * classDecl_List -> classDecl classDecl_List classDecl_List -> EPSILON
	 * 
	 * @return
	 */
	private boolean classDecl_List(ClassNode classNode, ClassListNode classlist) {
		ClassNode _classNode = new ClassNode();
		if (!skipErrors("classDecl", "classDecl_List")) {
			return false;
		}

		if (checkFirstSet("classDecl")) {
			printGrammar("classDecl_List", "ClassDecl classDecl_List");
			if (classDecl(_classNode)) {
				classlist.addChild(_classNode);
				if (classDecl_List(_classNode, classlist)) {
					print(SYNTAX, "classDecl_List -> classDecl classDecl_List");

					return true;
				} else
					return false;
			} else
				return false;
		} else
			return checkEpsilon("classDecl_List", classNode, classlist);
	}
	
	/**
	 * classDecl -> 'class' 'id' classDecl_Optional '{' varDecl_FuncDecl_List '}' ';'
	 * @return
	 */
	private boolean classDecl(ClassNode classNode) {
		//AstNode id = new AstNode(),  clp = new AstNode(), crp = new AstNode(), sc = new AstNode(), rwClass = new AstNode();
		AstNode id = new IdNode(),  clp = new IdNode(), crp = new IdNode(), sc = new IdNode(), rwClass = new IdNode();
		IdNode inherId = new IdNode();
		InherList inherList = new InherList();
		//AstNode astNode = new AstNode();
		AstNode astNode = new IdNode();
		VarDeclListNode varDeclListNode = new VarDeclListNode();
		FuncDeclListNode funcDeclListNode = new FuncDeclListNode();
		StatBlockNode statBlockNode = new StatBlockNode();
		
		if (!skipErrors("classDecl", "")) {
			return false;
		}
		if (checkFirstSet("class")) {
			printGrammar("ClassDecl", "class id classDecl_Optional { varDecl_FuncDecl_List } ;");
			if (matchReserveWordTokenType(LexicalStatic.R_W_CLASS, rwClass) 
				&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id)
				&& classDecl_Optional(inherId, inherList)
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_CURLY_LEFT_PARENTHESIS, clp))
				{
				//Symbol classSymb = symbol;
				if (varDecl_FuncDecl_List(astNode, varDeclListNode, funcDeclListNode, statBlockNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_CURLY_RIGHT_PARENTHESIS, crp)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc)) 
				{
					info(SYNTAX,"classDecl -> class id classDecl_Optional { varDecl_FuncDecl_List } ;");
					//semantics.setClassMemory(classSymb);
					//semantics.QuitPresentTable();
					 
					classNode.setClassNode(id, inherList, varDeclListNode, funcDeclListNode);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * funcDecl -> type id ( fParams ) ; 
	 * @return
	 */
//	private boolean funcDecl(FuncDeclNode funcDeclN) {
//		IdNode id = new IdNode(), rlp =new IdNode(), rrp = new IdNode(), sc = new IdNode();
//		ParamListNode paramList = new ParamListNode();
//		TypeNode typeNode = new TypeNode();
//		AstNode fParams = new AstNode();
//		
//		if (!skipErrors("type", "funcDecl")) {
//			return false;
//		}
//		
//		if(checkFirstSet("type")){
//			printGrammar("funcDecl", "type id ( fParams ) ;");
//			if(matchType(typeNode)
//			&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id) 
//			&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp))
//			{
//				if(fParams(paramList) )
//				{
//					if(paramList!= null && paramList.getChilderns() != null && paramList.getChilderns().size() > 0)
//					{
//						fParams = paramList.getChilderns().get(0);
//					}
//					if(matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp)
//							&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc))
//							{
//								info(SYNTAX,"funcDecl -> type id ( fParams ) ;");
//								
//								funcDeclN.setFuncDeclNode(typeNode, id, fParams);
//								
//								return true;
//							}
//				}
//			}		
//			
//		}
//				
//		return false;
//	}
	
	/**
	 * classDecl_Optional -> ':' 'id' classDecl_Listtow
	 * classDecl_Optional ->  EPSILON 
	 * @return
	 */
	private boolean classDecl_Optional(IdNode id, InherList inherList) {
		IdNode _id = new IdNode(), c = new IdNode();
		//IdNode _id = new IdNode();
		if (!skipErrors("classDecl_Optional", "classDecl_Listtow")) {
			return false;
		}

		if (checkFirstSet(":")) {
			printGrammar("classDecl_Optional", ": id classDecl_Listtow");
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_COLON, c)
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, _id)) {
				inherList.addChild(_id);
				
				if (classDecl_Listtow(_id, inherList)) {
					info(SYNTAX, "classDecl_Optional -> ':' 'id' classDecl_Listtow ");
					// inherList.addChild(id);

					return true;
				} else
					return false;
			} else
				return false;

		} else
			return checkEpsilon("classDecl_Optional", id, inherList);

	}
	
	/**
	 * classDecl_Listtow -> , id classDecl_Listtow 
     * classDecl_Listtow -> EPSILON 
	 * @return
	 */
	private boolean classDecl_Listtow(IdNode id, InherList inherList) {
		IdNode _id = new IdNode(), comma = new IdNode();
		if (!skipErrors("classDecl_Listtow", "classDecl_Listtow")) {
			return false;
		}

		if (checkFirstSet(",")) {
			printGrammar("classDecl_Listtow", ", id classDecl_Listtow");

			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_COMMA, comma)
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, _id)) {
				inherList.addChild(_id);
				if (classDecl_Listtow(_id, inherList)) {
					info(SYNTAX, "classDecl_Listtow -> , id classDecl_Listtow");

					return true;
				} else
					return false;
			} else
				return false;

		} else
			return checkEpsilon("classDecl_Listtow", id, inherList);
	}
	
	/**
	 * varDecl_FuncDecl_List -> type id varDecl_FunctDef_Tail
	 * varDecl_FuncDecl_List -> funcBody ; varDecl_FuncDecl_List
	 * varDecl_FuncDecl_List -> ; varDecl_FuncDecl_List
	 * varDecl_FuncDecl_List -> EPSILON 
	 * @return
	 */
	private boolean varDecl_FuncDecl_List(AstNode astNode, VarDeclListNode varDeclListNode, FuncDeclListNode funcDeclListNode, StatBlockNode statBlockNode){
		IdNode id = new IdNode(), sc = new IdNode(), sc2 =new IdNode();
		TypeNode typeNode = new TypeNode(); 
		//StatBlockNode _statBlockNode = null;
		if (!skipErrors("type", "varDecl_FuncDecl_List")) {
			return false;
		}
		
		if (checkFirstSet("type")) {
			printGrammar("varDecl_FuncDecl_List", "type id varDecl_FunctDef_Tail");
			if (matchType(typeNode)
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id))
			{
				//astNode = new AstNode();
				astNode = new IdNode();
				astNode.addChild(typeNode);
				astNode.addChild(id);
				
				if (varDecl_FunctDef_Tail(astNode, varDeclListNode, funcDeclListNode, statBlockNode)) {
					info(SYNTAX,"varDecl_FuncDecl_List -> type id varDecl_FunctDef_Tail");
					return true;
				} else {
					return false;
				}
			}
			else {
				return false;
			}
				
		}
		else if(checkFirstSet("funcBody"))
		{
			printGrammar("varDecl_FuncDecl_List", "funcBody ; varDecl_FuncDecl_List");
			if(funcBody(statBlockNode) 
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc)
				&& varDecl_FuncDecl_List(statBlockNode, varDeclListNode, funcDeclListNode, statBlockNode))
			{
				info(SYNTAX,"varDecl_FuncDecl_List -> funcBody ; varDecl_FuncDecl_List");
				return true;
			}
			else
			{
				return false;
			}
			
		}
		else if(checkFirstSet(";")){
			printGrammar("varDecl_FuncDecl_List", "varDecl_FuncDecl_List -> ; varDecl_FuncDecl_List");
			if(matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc2)
					&& varDecl_FuncDecl_List(astNode, varDeclListNode, funcDeclListNode, statBlockNode))
				{
					info(SYNTAX,"varDecl_FuncDecl_List -> ; varDecl_FuncDecl_List");
					return true;
				}
				else
				{
					return false;
				}
		}			
		else 
			return checkEpsilon("varDecl_FuncDecl_List");	

	}
	
	/**
	 * varDecl_FunctDef_Tail  -> funcHead_Optional ( fParams ) varDecl_FuncDecl_List
	 * varDecl_FunctDef_Tail  -> arraySize_List ; varDecl_FuncDecl_List
	 * @return
	 */
	private boolean varDecl_FunctDef_Tail(AstNode astNode, VarDeclListNode varDeclListNode, FuncDeclListNode funcDeclListNode, StatBlockNode statBlockNode){
		IdNode rlp = new IdNode(), rrp = new IdNode(), sc= new IdNode(), funcName = new IdNode(); 
		//AstNode funcParams = new AstNode();
		ParamListNode paramList = new ParamListNode();
		NumNode numNode = new NumNode();
		DimListNode dimList = new DimListNode();
		
		if (!skipErrors("varDecl_FunctDef_Tail", "")) {
			return false;
		}
		if (checkFirstSet("funcHead_Optional") || checkFollowSet("funcHead_Optional")) {
			printGrammar("varDecl_FunctDef_Tail", "funcHead_Optional ( fParams ) varDecl_FuncDecl_List");
			if (funcHead_Optional(funcName) && matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp)
					&& fParams(paramList)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp)) {
				
//				if (paramList != null && paramList.getChilderns() != null && paramList.getChilderns().size() > 0) {
//					funcParams = paramList.getChilderns().get(0);
//				}
				if (funcName != null && funcName.getDataToken() != null) {
					funcDeclListNode.addChild(new FuncHeadNode(astNode.getChildren().get(0), // typeNode
							astNode.getChildren().get(1), // id
							funcName, paramList));
				} else {
					funcDeclListNode.addChild(new FuncDeclNode(astNode.getChildren().get(0), // typeNode
							astNode.getChildren().get(1), // id
							paramList));
				}
				
				if (varDecl_FuncDecl_List(astNode, varDeclListNode, funcDeclListNode, statBlockNode)) {

					
					info(SYNTAX, "varDecFunDef1 -> ( fParams ) funcBody ; funcDefList");
					

					return true;
				}
			}
			
		} else if (checkFirstSet("varDecl_FunctDef_Tail")) {
			printGrammar("varDecl_FunctDef_Tail", "arraySizeList ; varDecl_FuncDecl_List ");
			if (arraySize_List(numNode, dimList)) {				
				if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc))
				{
					varDeclListNode.addChild(new VarDeclNode(astNode.getChildren().get(0), astNode.getChildren().get(1), dimList));
					
					if(varDecl_FuncDecl_List(astNode, varDeclListNode, funcDeclListNode, statBlockNode)) {
						info(SYNTAX, "varDecl_FunctDef_Tail -> arraySizeList ; varDecl_FuncDecl_List");					
						
						return true;
					} else
						return false;
				}
				
				else
					return false;
				
						
						
					
			} else
				return false;		
			}
		return false;
	}
	
	/**
	 * funcHead_Optional -> sr id 
	 * funcHead_Optional -> EPSILON 
	 * @return
	 */
	private boolean funcHead_Optional(IdNode id){
		IdNode sr = new IdNode();
		if (!skipErrors("funcHead_Optional", "funcHead_Optional")) {
			return false;
		}
		if(checkFirstSet("sr")){
			printGrammar("funcHead_Optional", "sr id");
			if(matchTokenType(LexicalStatic.T_N_OPERATOR_SCOPE_RESOLUTION, sr)
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id) )
			{
				info(SYNTAX, "funcHead_Optional -> sr id");
				return true;
			}
			else
				return false;
		}
		else 
			return checkEpsilon("funcHead_Optional");			
	}
	
	//
	/**
	 * funcHead -> type id funcHead_Optional ( fParams ) 
	 * @return
	 */
	private boolean funcHead(FuncHeadNode funcHeadNode){
		IdNode id = new IdNode(), rlp = new IdNode(), rrp = new IdNode();
		IdNode funcName = new IdNode();
		//AstNode funcParams = new AstNode();
		ParamListNode paramList = new ParamListNode();
		TypeNode typeNode = new TypeNode();
		
		if (!skipErrors("type", "funcHead")) {
			return false;
		}
		
		if(checkFirstSet("type")){
			printGrammar("funcHead", "type id funcHead_Optional ( fParams )");
			if(matchType(typeNode) 
				&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id) 
				&& funcHead_Optional(funcName) 
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp)
				&& fParams(paramList)
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp)
			)
			{
//				if(paramList!= null && paramList.getChilderns() != null && paramList.getChilderns().size() > 0)
//				{
//					funcParams = paramList.getChilderns().get(0);
//				}
				
				info(SYNTAX,
						"funcHead -> type id funcHead_Optional ( fParams )");
				funcHeadNode.setFuncHeadNode(typeNode, id, funcName, paramList);
					return true;
			}
		}
				
		return false;
	}

	/**
	 * funcDef -> funcHead funcBody ; 
	 * @return
	 */
	private boolean funcDef(FuncDefNode funcDefNode)
	{
		IdNode sc = new IdNode();
		FuncHeadNode funcHeadNode = new FuncHeadNode();
		StatBlockNode statBlockNode = new StatBlockNode();
		if (!skipErrors("funcHead", "funcBody")) {
			return false;
		}
		
		if(checkFirstSet("funcHead")){
			printGrammar("funcDef", "funcHead funcBody ;");
			if(funcHead(funcHeadNode) 				
				&& funcBody(statBlockNode) 
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc)
			)			
			{
				info(SYNTAX,
						"funcDef -> funcHead funcBody ;");
				funcDefNode.setFuncDefNode(funcHeadNode, statBlockNode);
					return true;
			}
		}
				
		return false;
	}
	
	/**
	 * funcBody -> { varDecl_statement_List } 
	 * @return
	 */
	private boolean funcBody(StatBlockNode statBlockNode)
	{
		IdNode clp = new IdNode(), crp = new IdNode();
		if (!skipErrors("{", "")) {
			return false;
		}
		
		if(checkFirstSet("{")){
			printGrammar("funcBody", "{ varDecl_statement_List }");
			if( matchTokenType(LexicalStatic.T_N_PUNCTUATION_CURLY_LEFT_PARENTHESIS, clp)				
				&& varDecl_statement_List(statBlockNode) 
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_CURLY_RIGHT_PARENTHESIS, crp)
			)			
			{
				info(SYNTAX, "funcBody -> { varDecl_statement_List }");
					return true;
			}
		}			
	
		return false;
	}
	
	/**
	 * indiceIdnestList_Tail  -> . id indiceIdnestList_Tail
	 * indiceIdnestList_Tail  -> [ arithExpr ] indiceIdnestList_Tail
	 * indiceIdnestList_Tail  -> EPSILON
	 * @return
	 */
	private boolean indiceIdnestList_Tail(AstNode id, VarNode varNode)
	{
		IdNode _id = new IdNode(), dot = new IdNode(), slp = new IdNode(), srp = new IdNode();
		ArithExprNode arithExprNode = new ArithExprNode();
		DataMemberNode dataMemberNode = new DataMemberNode();
		
		if (!skipErrors(".", "indiceIdnestList_Tail")) {
			return false;
		}
		
		if(checkFirstSet(".")){
			printGrammar("indiceIdnestList_Tail", ". id indiceIdnestList_Tail");
			if(matchTokenType(LexicalStatic.T_N_PUNCTUATION_DOT, dot) 
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, _id)  
					&& indiceIdnestList_Tail(_id, varNode) 
			  )
			{
				info(SYNTAX, "indiceIdnestList_Tail  -> . id indiceIdnestList_Tail");
				return true;
			}
			else
				return false;
		}
		else if(checkFirstSet("["))
		{
			printGrammar("indiceIdnestList_Tail", "[ arithExpr ] indiceIdnestList_Tail");
			if(matchTokenType(LexicalStatic.T_N_PUNCTUATION_SQUARE_LEFT_PARENTHESIS, slp)
					&& arithExpr(arithExprNode) 
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SQUARE_RIGHT_PARENTHESIS, srp)
					&& indiceIdnestList_Tail(id, varNode)
			  )
			{
				info(SYNTAX, "indiceIdnestList_Tail  -> [ arithExpr ] indiceIdnestList_Tail");
				dataMemberNode.setDataMemberNode(id, arithExprNode);
				varNode.addChild(dataMemberNode);
				return true;
			}
			else 
				return false;
		}		
		else 
		{
			varNode.addChild(id);
			return checkEpsilon("indiceIdnestList_Tail", id, varNode);
		}
	}

	/**
	 * varDeclStatement_Tail  -> id arraySize_List ; varDecl_statement_List
	 * varDeclStatement_Tail  -> indiceIdnestList_Tail assignOp expr ; statement_List 
	 * @return
	 */	
	private boolean varDeclStatement_Tail(AstNode astNode, StatBlockNode statBlockNode)
	{
		IdNode id = new IdNode(), sc = new IdNode(), sc2 = new IdNode(), assignOp = new IdNode();
		NumNode numNode = new NumNode();
		DimListNode dimList = new DimListNode();
		ExprNode exprNode = new ExprNode();
		AssignStatNode assignStatNode = new AssignStatNode();
		VarNode varNode = new VarNode();  
		if (!skipErrors("varDeclStatement_Tail", "varDeclStatement_Tail")) {
			return false;
		}
		if (checkFirstSet("id")) {
			printGrammar("varDeclStatement_Tail", "id arraySize_List ; varDecl_statement_List");
			if(matchTokenType(LexicalStatic.T_N_IDENTIFIER, id) 
				&& arraySize_List(numNode, dimList) 
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc))
			{
				statBlockNode.addChild(new VarDeclNode(astNode, id, dimList));
				
				if(varDecl_statement_List(statBlockNode))
				{
					info(SYNTAX, "varDeclStatement_Tail  -> id arraySize_List ; varDecl_statement_List");
					return true;
				}
			}
			
		}
		else if(checkFirstSet("indiceIdnestList_Tail") || checkFollowSet("indiceIdnestList_Tail")){
			printGrammar("varDeclStatement_Tail", "indiceIdnestList_Tail assignOp expr ; statement_List");
			if(indiceIdnestList_Tail(astNode, varNode)) 
			{
				if(assignOp(assignOp)) 
				{
					//Symbol prevSymb = new Symbol();
					//copySymbol(prevSymb, symbol);
					//semantics.pushOffset(symbol);
					if (expr(exprNode))  
					{
						// semantics.checkDataTypes(prevSymb, expr);
						assignStatNode.setAssignStatNode(assignOp.getDataToken(), varNode, exprNode);
						statBlockNode.addChild(assignStatNode);
						
						if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc2) 
								&& statement_List(statBlockNode) 
						 ) {
							info(SYNTAX, "varDeclStatement_Tail  -> indiceIdnestList_Tail assignOp expr ; statement_List");
							return true;
						}
					}
				}
			}		
		}
		return false;
	}

	/**
	 * varDecl_statement_List -> numericType id arraySize_List ; varDecl_statement_List
	 * varDecl_statement_List -> id varDeclStatement_Tail
	 * varDecl_statement_List -> remainingStatement statement_List
	 * varDecl_statement_List -> EPSILON
	 * @return
	 */
	private boolean varDecl_statement_List(StatBlockNode statBlockNode)
	{
		IdNode id = new IdNode(), sc = new IdNode(), id2 = new IdNode();
		TypeNode typeNode = new TypeNode();
		NumNode numNode = new NumNode();
		DimListNode dimList = new DimListNode();
		
		if (!skipErrors("varDecl_statement_List", "varDecl_statement_List")) {
			return false;
		}
		
		if(checkFirstSet("numericType"))
		{
			printGrammar("varDecl_statement_List", "numericType id arraySize_List ; varDecl_statement_List");
			if(numericType(typeNode) 
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id) 
					&& arraySize_List(numNode, dimList) 
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc)
					)
			{
				statBlockNode.addChild(new VarDeclNode(typeNode, id, dimList));
				
				if(varDecl_statement_List(statBlockNode))
				{
					info(SYNTAX, "varDecl_statement_List -> numericType id arraySize_List ; varDecl_statement_List");
					
					return true;				
				}
				else
					return false;
			}
			else
				
					
					
					return false;
		}
		else if(checkFirstSet("id"))
		{
			printGrammar("varDecl_statement_List", "id varDeclStatement_Tail");
			if(matchTokenType(LexicalStatic.T_N_IDENTIFIER, id2) 
					&& varDeclStatement_Tail(id2, statBlockNode) 
			  )
			{
				info(SYNTAX, "varDecl_statement_List -> id varDeclStatement_Tail");
				return true;				
			}
			else
				return false;
		}
		else if(checkFirstSet("remainingStatement"))
		{
			printGrammar("varDecl_statement_List", "remainingStatement statement_List");
			if(remainingStatement(statBlockNode) && statement_List(statBlockNode))
			{
				info(SYNTAX, "varDecl_statement_List -> remainingStatement statement_List");
				return true;				
			}
			else
				return false;
		}		
		
		else 
			return checkEpsilon("varDecl_statement_List");
	}
	
	/**
	 * statement -> assignStatSemicolon
	 * statement -> remainingStatement 
	 * @return
	 */
	private boolean statement(StatBlockNode statBlockNode)
	{
		AssignStatNode assignStat = new AssignStatNode();
		if (!skipErrors("statement", "")) {
			return false;
		}
		if(checkFirstSet("assignStatSemicolon"))
		{
			printGrammar("statement", "assignStatSemicolon");
			if(assignStatSemicolon(assignStat) ){
				info(SYNTAX, "statement -> assignStatSemicolon");
				statBlockNode.addChild(assignStat);
				return true;				
			}
			else
				return false;
		}
		else if(checkFirstSet("remainingStatement"))
		{
			printGrammar("statement", "remainingStatement");
			if(remainingStatement(statBlockNode))
			{
				info(SYNTAX, "statement -> remainingStatement");
				return true;				
			}
			else
				return false;
		} 
		return false;
	}
	
	/**
	 * assignStatSemicolon -> variable assignOp expr ;
	 * @return
	 */
	private boolean assignStatSemicolon(AssignStatNode assignStat)
	{
		IdNode sc = new IdNode(), assignOp = new IdNode();
		VarNode varNode = new VarNode();
		ExprNode exprNode = new ExprNode();
		//Symbol expr = new Symbol();
		if (!skipErrors("assignStatSemicolon", "")) {
			return false;
		}
		if (checkFirstSet("variable")) {
			printGrammar("assignStatSemicolon", "variable assignOp expr ;");
			if (variable(varNode)) {
				// System.out.println(symbol.toString());
				//Symbol variable = new Symbol();
				//copySymbol(variable, symbol);
				//semantics.pushOffset(variable);
				if (assignOp(assignOp)) {
					if (expr(exprNode)) {
						if(matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc))
						{
							assignStat.setAssignStatNode(assignOp.getDataToken(), varNode, varNode);
							info(SYNTAX,"assignStatSemicolon -> variable assignOp expr ;");
							//semantics.checkDataTypes(variable, expr);
							//assignStat =  new  AssignStatNode(assignOp.getDataToken(), leftChild, rightChild);
							return true;
						}
					}
				} else
					return false;
			}
		}
		return false;
	}

	/**
	 * assignStat -> variable assignOp expr
	 * @return
	 */
	private boolean assignStat(AssignStatNode assignStatNode)
	{
		IdNode  assignOp = new IdNode();
		VarNode varNode = new VarNode();
		ExprNode exprNode = new ExprNode();
		//Symbol expr = new Symbol();
		if (!skipErrors("assignStat", "")) {
			return false;
		}
		if (checkFirstSet("variable")) {
			printGrammar("assignStat", "variable assignOp expr");
			if (variable(varNode)) {
				// System.out.println(symbol.toString());
				//Symbol variable = new Symbol();
				//copySymbol(variable, symbol);
				//semantics.pushOffset(variable);
				if (assignOp(assignOp)) {
					if (expr(exprNode)) {
						info(SYNTAX,"assignStat -> assignStat -> variable assignOp expr");
						//semantics.checkDataTypes(variable, expr);
						assignStatNode.setAssignStatNode(assignOp.getDataToken(), varNode, exprNode);
						return true;
					}
					else 
						return false;
						
				} else
					return false;
			}
		}
		return false;
	}

	/**
	 * remainingStatement -> for ( type id assignOp expr ; relExpr ; assignStat ) statBlock ;
	 * remainingStatement -> if ( expr ) then statBlock else statBlock ;
	 * remainingStatement -> get ( variable ) ;
	 * remainingStatement -> put ( expr ) ;
	 * remainingStatement -> return ( expr ) ; 
	 * 
	 * @return
	 */
	private boolean remainingStatement(StatBlockNode statBlockNode)
	{
		IdNode id = new IdNode(), rlp = new IdNode(), rrp = new IdNode(), sc = new IdNode(), sc2 = new IdNode(), sc3= new IdNode(), 
		rlp2 = new IdNode(), rrp2 = new IdNode(), sc4 =new IdNode(), rlp3 = new IdNode(), rrp3 = new IdNode(), sc5 = new IdNode(), 
		rlp4 = new IdNode(), rrp4 = new IdNode(), sc6 = new IdNode(), rlp5 = new IdNode(), rrp5 = new IdNode(), sc7 = new IdNode(), 
		rwFor = new IdNode(), rwIF = new IdNode(), rwThen = new IdNode(), rwElse = new IdNode(), rwGet = new IdNode(), 
		rwPut = new IdNode(), rwReturn = new IdNode(), assignOperation = new IdNode();
		TypeNode typeNode = new TypeNode();
		VarNode varNode = new VarNode();
		ExprNode exprNode = new ExprNode();
		RelExprNode relExprNode = new RelExprNode();
		AssignStatNode assignStatNode  = new AssignStatNode();
		StatBlockNode _statBlockNode = new StatBlockNode();
		ForStatNode forStatNode = new ForStatNode();
		IfStatNode ifStatNode = new IfStatNode();
		StatBlockNode ifStatBlockNode1 = new StatBlockNode();
		StatBlockNode ifStatBlockNode2 = new StatBlockNode();
		GetStatNode getStatNode = new GetStatNode();
		PutStatNode putStatNode = new PutStatNode();
		ReturnStatNode returnStatNode = new ReturnStatNode(); 
		//Symbol expr = new Symbol();
		if (!skipErrors("remainingStatement", "")) {
			return false;
		}
		
		if (checkFirstSet("for")) {
			printGrammar("remainingStatement", "for ( type id assignOp expr ; relExpr ; assignStat ) statBlock ;");
			if (matchReserveWordTokenType(LexicalStatic.R_W_FOR, rwFor)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp) 
					&& matchType(typeNode)
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id)
					&& assignOp(assignOperation)) 
			{
				//Symbol prevSymb = new Symbol();
				//copySymbol(prevSymb, symbol);
				if (expr(exprNode)) {
					//semantics.checkDataTypes(prevSymb, expr);
					if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc)) {
						//String forAddr = "FOR_START_" + semantics.addrCount++;
						//semantics.moonCode.add(forAddr + " \t%  FOR LOOP BEGIN");
						//Symbol relExpr = new Symbol();
						if (relExpr(relExprNode)
								&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc2)) {
							//String endFor = "FOR_END_" + semantics.addrCount++;
							//semantics.moonCode.add("\t%  FOR CODE");
							//semantics.loadWord(relExpr, "r1", "");
							//semantics.moonCode.add("\t\t" + "bz" + "\tr1, "+ "\t" + endFor);
							if (assignStat(assignStatNode)
									&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp)
									&& statBlock(_statBlockNode)
									&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc3)) {
								info(SYNTAX,"remainingStatement ->for ( type id assignOp expr ; relExpr ; assignStat ) statBlock ;");
								// code generation
								//semantics.moonCode.add("\t\t" + "j\t" + forAddr);
								//semantics.moonCode.add("\t%  END FOR LOOP");
								//semantics.moonCode.add(endFor);
								forStatNode.setForStatNode(rwFor.getDataToken(), typeNode, id, exprNode, relExprNode, assignStatNode, _statBlockNode);
								statBlockNode.addChild(forStatNode);
								return true;
							}
							else 
								return false;
							
						}
					} 
				}
				
			}
		}
		else if (checkFirstSet("if")) {
			printGrammar("remainingStatement", "if ( expr ) then statBlock else statBlock ;");
			if (matchReserveWordTokenType(LexicalStatic.R_W_IF, rwIF)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp2) 
					&& expr(exprNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp2)
					&& matchReserveWordTokenType(LexicalStatic.R_W_THEN, rwThen)) {
				//String elseAddr = semantics.genCodeIf(expr);
				if (statBlock(ifStatBlockNode1) && matchReserveWordTokenType(LexicalStatic.R_W_ELSE, rwElse)) {
					//String elseEndAddr = semantics.genCodeElse(expr, elseAddr);
					if (statBlock(ifStatBlockNode2) && matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc4)) {
						info(SYNTAX,
										"remainingStatement -> if ( expr ) then statBlock else statBlock ;");
						//semantics.genCodeEndThen(expr, elseEndAddr);
						ifStatNode.setIfStatNode(rwIF.getDataToken(), exprNode, ifStatBlockNode1, ifStatBlockNode1);
						statBlockNode.addChild(ifStatNode);
						return true;
					} else
						return false;
				}
			}			
		}
		else if (checkFirstSet("get")) {
			printGrammar("remainingStatement", "get ( variable ) ;");
			if (matchReserveWordTokenType(LexicalStatic.R_W_GET, rwGet)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp3) 
					&& variable(varNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp3)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc5)) {
				info(SYNTAX,
						"remainingStatement -> get ( variable ) ;");
				//semantics.genGetCode(symbol);
				getStatNode.setGetStatNode(rwGet.getDataToken(), varNode, null);
				statBlockNode.addChild(getStatNode);
				return true;
			} else
				return false;
		}
		else if (checkFirstSet("put")) {
			printGrammar("remainingStatement", "put ( expr ) ;");
			if (matchReserveWordTokenType(LexicalStatic.R_W_PUT, rwPut)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp4)  
					&& expr(exprNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp4)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc6)) {
				info(SYNTAX,
						"remainingStatement -> put ( expr ) ;");
				//semantics.genPutCode(expr);
				putStatNode.setPutStatNode(rwPut.getDataToken(), exprNode, null);				
				statBlockNode.addChild(putStatNode);
				return true;
			} else
				return false;
			
		}
		else if (checkFirstSet("return")) {
			printGrammar("remainingStatement", "return ( expr ) ;");
			if (matchReserveWordTokenType(LexicalStatic.R_W_RETURN, rwReturn) 
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp5)  
					&& expr(exprNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp5)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SEMICOLON, sc7)) {
				info(SYNTAX,
						"remainingStatement -> return ( expr ) ;");
				returnStatNode.setReturnStatNode(rwReturn.getDataToken(), exprNode, null);
				statBlockNode.addChild(returnStatNode);
				return true;
			} else
				return false;
		}
		
		return false;
	}
	
	/**
	 * statBlock -> { statement_List } 
	 * statBlock -> statement
	 * statBlock -> EPSILON
	 * @return
	 */
	private boolean statBlock(StatBlockNode statBlockNode)
	{
		IdNode clp = new IdNode(), crp = new IdNode();
		if (!skipErrors("statBlock", "statBlock")) {
			return false;
		}
		if (checkFirstSet("{")) {
			printGrammar("statBlock", " { statement_List }");
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_CURLY_LEFT_PARENTHESIS, clp) 
					&& statement_List(statBlockNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_CURLY_RIGHT_PARENTHESIS, crp)) {
				info(SYNTAX,"statBlock -> { statement_List }");
				return true;
			} else
				return false;
		} else if (checkFirstSet("statement")) {
			printGrammar("statBlock", "statement");
			if (statement(statBlockNode)) {
				info(SYNTAX, "statBlock -> statement");
				return true;
			} else
				return false;
		} 
		else
			return checkEpsilon("statBlock");
		
	}
	
	/**
	 * expr -> arithExpr exp_Tail 
	 * @return
	 */
	private boolean expr(ExprNode exprNode) {
		ArithExprNode arithExprNode = new ArithExprNode();
		RelExprNode relExprNode = new RelExprNode();
		
		if (!skipErrors("arithExpr", "")) {
			return false;
		}
	
		if (checkFirstSet("arithExpr")) {
			printGrammar("expr", "arithExpr exp_Tail");
			//Symbol arithExpr = new Symbol();
			//Symbol subExpr = new Symbol();
			if (arithExpr(arithExprNode) 
				 //&& subExpr(arithExpr, subExpr)
				&& exp_Tail(arithExprNode, relExprNode)) 
			{
				info(SYNTAX,"expr -> arithExpr exp_Tail");
				//copySymbol(expr, subExpr);
				if(relExprNode != null && relExprNode.getChildren() != null && relExprNode.getChildren().size() > 0)
				{
					exprNode.setExprNode(relExprNode);
				}
				else
				{
					exprNode.setExprNode(arithExprNode);
				}
				
				return true;
			} else
				return false;
		}
		return false;
	}
	
	/**
	 * exp_Tail -> relOp arithExpr
	 * exp_Tail -> EPSILON 
	 * @return
	 */
	private boolean exp_Tail(ArithExprNode arithExprNode, RelExprNode relExprNode) {
		IdNode relop = new IdNode();
		ArithExprNode _arithExprNode = new ArithExprNode();
		if (!skipErrors("relOp", "exp_Tail")) {
			return false;
		}
		
		if (checkFirstSet("relOp")) {
			printGrammar("exp_Tail", "relOp arithExpr");
			//Symbol arithExpr2 = new Symbol();
			//Token operToken = copyToken(token);
			//semantics.pushOffset(arithExpr);
			if (matchTokenType("relOp", relop) && arithExpr(_arithExprNode)) {
				info(SYNTAX, "exp_Tail -> relOp arithExpr");
				relExprNode.setRelExprNode(relop.getDataToken(), arithExprNode, _arithExprNode);
				
				//Symbol result = semantics.addOpComp(arithExpr, operToken, arithExpr2);
				//copySymbol(subExpr, result);
				return true;
			} else
				return false;
		} 
		else 
		{
			return checkEpsilon("exp_Tail");
		}
					
	}
	
	/**
	 * relExpr -> arithExpr relOp arithExpr 
	 * @return
	 */
	private boolean relExpr(RelExprNode relExprNode)
	{
		IdNode relop = new IdNode();
		ArithExprNode arithExprNode = new ArithExprNode();
		ArithExprNode _arithExprNode = new ArithExprNode();
		if (!skipErrors("arithExpr", "")) {
			return false;
		}
		if (checkFirstSet("arithExpr")) {
			//Symbol arithExprL = new Symbol();
			//Symbol arithExprR = new Symbol();
			printGrammar("relExpr", "arithExpr relOp arithExpr");
			if (arithExpr(arithExprNode)) {
				//Token operToken = copyToken(token);
				if (matchTokenType("relOp", relop) && arithExpr(_arithExprNode)) {
					info(SYNTAX, "relExpr -> arithExpr relOp arithExpr");
					relExprNode.setRelExprNode(relop.getDataToken(), arithExprNode, _arithExprNode);
					//Symbol result = semantics.addOpComp(arithExprL, operToken, arithExprR);
					// Code change for the for loop code generation
					// copySymbol(result, arithExprR);
					//copySymbol(relExpr, result);
					return true;
				}
			} else
				return false;
		}
		return false;
	}
	
	/**
	 * arithExpr -> term arithExpr_Tail
	 * @return
	 */
	private boolean arithExpr(ArithExprNode arithExprNode)
	{
		TermNode termNode = new TermNode();
		//AddOpNode addOpNode = null;
		if (!skipErrors("term", "arithExpr_Tail")) {
			return false;
		}
		if (checkFirstSet("term")) {
			//Symbol term = new Symbol();
			//Symbol arithExprRight = new Symbol();
			printGrammar("arithExpr", "term arithExpr_Tail");
			if (term(termNode) && arithExpr_Tail(termNode, arithExprNode)) {
				info(SYNTAX,"arithExpr -> term arithExpr_Tail");
				//copySymbol(arithExpr, arithExprRight);				
				//arithExprNode = new ArithExprNode(Arrays.asList(addOpNode));
				
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * arithExpr_Tail -> addOp term arithExpr_Tail
	 * arithExpr_Tail -> EPSILON
	 * @return
	 */
	private boolean  arithExpr_Tail(TermNode termNode, ArithExprNode arithExprNode)
	{
		IdNode addOp = new IdNode();
		TermNode _termNode = new TermNode();
		AddOpNode addOpNode = new AddOpNode();
		
		if (!skipErrors("addOp", "arithExpr_Tail")) {
			return false;
		}
		
		if (checkFirstSet("addOp")) {
			printGrammar("arithExpr_Tail", "addOp term arithExpr_Tail");
			//Symbol arithExprRight2 = new Symbol();
			//Symbol term2 = new Symbol();
			//Token operToken = copyToken(token);
			//semantics.pushOffset(term);
			if (matchTokenType("addOp", addOp) 
					&& term(_termNode)
					&& arithExpr_Tail(_termNode, arithExprNode)) {
				info(SYNTAX, "arithExpr_Tail -> addOp term arithExpr_Tail");
				// Symbol compSymb = new Symbol();
				// copySymbol(compSymb, arithExprRight2);
				// compare addition data types
				//Symbol result = semantics.addOpComp(term, operToken, arithExprRight2);
				//copySymbol(arithExprRight, result);
				addOpNode.setAddOpNode(addOp.getDataToken(), termNode, _termNode);
				arithExprNode.addChild(addOpNode);
				
				return true;
			} else
				return false;
		} else 
		{
			arithExprNode.addChild(termNode); //Duplicate for loop 1
			return checkEpsilon("arithExpr_Tail", termNode, arithExprNode);
		}
//			if (checkFollowSet("arithExpr_Tail")) {
//			printGrammar("arithExpr_Tail", "");
//			info(SYNTAX,"arithExpr_Tail -> EPSILON");
//			//copySymbol(arithExprRight, term);
//			arithExprNode= termNode;
//			return true;
//		} 
			
		//return false;
	}
	
	/**
	 * term -> factor term_Tail
	 * @return
	 */
	private boolean term(TermNode termNode)
	{
		FactorNode factorNode = new FactorNode(); 
		if (!skipErrors("factor", "")) {
			return false;
		}
		if (checkFirstSet("factor")) {
			//Symbol factor = new Symbol();
			//Symbol termFactor = new Symbol();
			printGrammar("term", "factor term_Tail");
			if (factor(factorNode) && term_Tail(factorNode, termNode)) {
				info(SYNTAX, "term -> factor term_Tail");
				//copySymbol(term, termFactor);
				return true;
			} else
				return false;
		}
		return false;
	}
	
	/**
	 * term_Tail -> multOp factor term_Tail
	 * term_Tail -> EPSILON
	 * @return
	 */
	private boolean term_Tail(FactorNode factorNode, TermNode termNode)
	{
		IdNode multOp = new IdNode();
		MultOpNode multOpNode = new MultOpNode(); 
		FactorNode _factorNode = new FactorNode();
		if (!skipErrors("multOp", "term_Tail")) {
			return false;
		}
		if (checkFirstSet("multOp")) {
			//Symbol factor2 = new Symbol();
			//Symbol termFactor2 = new Symbol();
			//Token operToken = copyToken(token);
			//semantics.pushOffset(factor);
			printGrammar("term_Tail", "multOp factor term_Tail");
			if (matchTokenType("multOp", multOp)
					&& factor(_factorNode)
					&& term_Tail(_factorNode, termNode)) {
				info(SYNTAX, "term_Tail -> multOp factor term_Tail");
				//Symbol result = semantics.addOpComp(factor, operToken, termFactor2);
				//copySymbol(termFactor, result);
				multOpNode.setMultOpNode(multOp.getDataToken(), factorNode, _factorNode);
				termNode.addChild(multOpNode);
				
				return true;
			} else
				return false;
		} else
		{
			termNode.addChild(factorNode);
			return checkEpsilon("term_Tail", factorNode, termNode);
		}
			
//		if (checkFollowSet("term_Tail")) {
//			printGrammar("term_Tail", "");
//			info(SYNTAX, "term_Tail -> EPSILON");
//			//copySymbol(termFactor, factor);
//			return true;
//		}
		//return false;
	}
    
 
	/**
	 * factor -> ( arithExpr )
	 * factor -> floatNum
	 * factor -> intNum 
	 * factor -> not factor
	 * factor -> functionCall
	 * factor -> sign factor
	 * @return
	 */
	private boolean factor(FactorNode factorNode)
	{
		IdNode rlp = new IdNode(), rrp = new IdNode(), not = new IdNode(), sign = new IdNode(), floatNumber = new IdNode(), 
		intNumber = new IdNode();
		ArithExprNode arithExprNode = new ArithExprNode();
		FactorNode _factorNode = new FactorNode();
		NotNode notNode = new NotNode();
		NumNode numnode = new NumNode();
		SignNode signNode = new SignNode();
		VarNode varNode = new VarNode();
		
		if (!skipErrors("factor", "")) {
			return false;
		}
		
		if (checkFirstSet("(")) {
			//Symbol arithExpr = new Symbol();
			printGrammar("factor", "( arithExpr )");
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp) 
					&& arithExpr(arithExprNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp)) {
				info(SYNTAX, "factor -> ( arithExpr )");
				factorNode.addChild(arithExprNode);
				//copySymbol(factor, arithExpr);
				return true;
			} else
				return false;
		}
		else if (checkFirstSet("not")) {
			printGrammar("factor", "not factor");
			if (matchTokenType(LexicalStatic.T_N_OPERATOR_NOT, not) 
					&& factor(_factorNode)) {
				info(SYNTAX, "factor -> not factor");				
				notNode.setNotNode(not.getDataToken(), _factorNode);
				factorNode.addChild(notNode);
				//semantics.genNotCode(factor);
				return true;
			} else
				return false;
		}
		else if (checkFirstSet("floatNum")) {
			printGrammar("factor", "floatNum");
			if (floatNum(floatNumber)) {
				info(SYNTAX, "factor -> floatNum");
				// A - 4 set the num type according to the input
				//factor.setDataType(copyToken(prevToken));
				//factor.setToken(copyToken(prevToken));
				//factor.symbolType = SYMBOLTYPE.NUM;
				//if (prevToken.getDesc().equals("T_INTEGER")) {
				//	factor.getDataType().setValue("int");
				//} else if (prevToken.getDesc().equals("T_FLOAT")) {
				//	factor.getDataType().setValue("float");
				//}
				numnode.setNumNode(floatNumber.getDataToken());
				factorNode.addChild(numnode);
				return true;
			} else
				return false;
		}
		else if (checkFirstSet("intNum")) {
			printGrammar("factor", "intNum");
			if (intNum(intNumber)) {
				info(SYNTAX, "factor -> intNum");
				// A - 4 set the num type according to the input
				//factor.setDataType(copyToken(prevToken));
				//factor.setToken(copyToken(prevToken));
				//factor.symbolType = SYMBOLTYPE.NUM;
				//if (prevToken.getDesc().equals("T_INTEGER")) {
				//	factor.getDataType().setValue("int");
				//} else if (prevToken.getDesc().equals("T_FLOAT")) {
				//	factor.getDataType().setValue("float");
				//}
				numnode.setNumNode(intNumber.getDataToken());
				factorNode.addChild(numnode);
				return true;
			} else
				return false;
		}
		else if (checkFirstSet("sign")) {
			printGrammar("factor", "sign factor");
			//Token sign = copyToken(token);
			if (matchSign(sign) && factor(_factorNode)) {
				info(SYNTAX,"factor -> sign factor");
				//if (sign.getValue().equals("-")) {
				//	semantics.genSignCode(factor);
				//}
				signNode.setSignNode(sign.getDataToken(), _factorNode);				
				factorNode.addChild(signNode);
				return true;
			} else
				return false;
		}
		else if (checkFirstSet("functionCall")) {
			printGrammar("factor", "functionCall");
			//Token sign = copyToken(token);
			if (functionCall(varNode)) {
				info(SYNTAX,"factor -> functionCall");
				//if (sign.getValue().equals("-")) {
				//	semantics.genSignCode(factor);
				//}
//				if(factorNode == null)
//				{
//					factorNode = new FactorNode();
//				}				
				factorNode.addChild(varNode);
				
				return true;
			} else
				return false;
		}
		
		return false;
	}
	
	/**
	 * functionCall   -> variable
	 * @return
	 */
	private boolean functionCall(VarNode var)
	{
		if (!skipErrors("variable", "")) {
			return false;
		}
		if (checkFirstSet("variable")) {
			//printGrammar("A_P_T", ", expr");
			printGrammar("functionCall", ", variable");
			//Symbol expr = new Symbol();
			if (variable(var)) {
				info(SYNTAX, "functionCall   -> variable");
				//semantics.aParams.add(expr);
				//fCallNode = new FCallNode(id, aParamsList)
				return true;
			} else
				return false;
		}
		return false;
	}
	
	/**
	 * variable -> id variable_Tail
	 * @return
	 */
	private boolean variable(VarNode var)
	{
		IdNode id = new IdNode();
		VarElementNode varElement = new VarElementNode();
		
		if (!skipErrors("id", "")) {
			return false;
		}
		if (checkFirstSet("id")) {
			printGrammar("variable", "id variable_Tail");
			if (matchTokenType(LexicalStatic.T_N_IDENTIFIER, id)) {
				if (variable_Tail(id, varElement)) {
					var.setVarNode(Arrays.asList(varElement));
					info(SYNTAX,"variable -> id variable_Tail");
					return true;				
				}
				 else
						return false;
			}
		}
		
		return false;
	}
	
	/**
	 * variable_Tail -> ( aParams ) idnest_List_Tail
	 * variable_Tail -> indice_List idnest_List_Tail
	 * @return
	 */
	private boolean variable_Tail(IdNode id, VarElementNode varElement)
	{
		IdNode rlp = new IdNode(), rrp = new IdNode();
		ExprNode exprNode = new ExprNode();
		AParamNode aParamNode = new AParamNode();
		FCallNode fCallNode  = new FCallNode();
		DataMemberNode dataMemberNode = new DataMemberNode(); 
		ArithExprNode  arithExprNode = new  ArithExprNode();
		IndexListNode  indexListNode = new IndexListNode();
		if (!skipErrors("variable_Tail", "idnest_List_Tail")) {
			return false;
		}
		
		if (checkFirstSet("(")) {
			printGrammar("variable_Tail", "( aParams ) idnest_List_Tail");
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp)
				&& aParams(exprNode, aParamNode)
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp)
				)
			{
				fCallNode.setFCallNode(id, aParamNode);
				varElement.addChild(fCallNode);
				
				if(idnest_List_Tail(varElement)) {
					
					
					info(SYNTAX,"variable_Tail -> ( aParams ) idnest_List_Tail");
					return true;
			}
					
			}
			else
				return false;
		}
		else if (checkFirstSet("indice_List") || checkFollowSet("indice_List")) {
			printGrammar("variable_Tail", "indice_List idnest_List_Tail");
			if(indice_List(arithExprNode, indexListNode)){
				
				dataMemberNode.setDataMemberNode(id, indexListNode);
				varElement.addChild(dataMemberNode);
				
				if(idnest_List_Tail(varElement)){
					
						
					
					info(SYNTAX,"variable_Tail -> indice_List idnest_List_Tail");
					return true;	
				}
				else 
					return false;
			}
		}
		
		return false;		
	}
	
	/**
	 * idnest_List_Tail -> idnest_ idnest_List_Tail
	 * idnest_List_Tail -> EPSILON
	 * @return
	 */
	private boolean idnest_List_Tail(VarElementNode varElement)
	{
		if (!skipErrors("idnest_", "idnest_List_Tail")) {
			return false;
		}
		
		if(checkFirstSet("idnest_"))
		{
			printGrammar("idnest_List_Tail", "idnest_ idnest_List_Tail");
			if(idnest_(varElement) && idnest_List_Tail(varElement))
			{
				info(SYNTAX,"idnest_List_Tail -> idnest_ idnest_List_Tail");
				return true;
			}
			else
				return false;
		}
		else
			return checkEpsilon("idnest_List_Tail");
					
	}

	/**
	 * idnest_ -> . id idnest_Tail
	 * @return
	 */
	private boolean  idnest_(VarElementNode varElementNode)
	{
		IdNode id = new IdNode();
		IdNode dot = new IdNode();
		if (!skipErrors(".", "idnest_")) {
			return false;
		}
		
		if(checkFirstSet("."))
		{
			printGrammar("idnest_", ". id idnest_Tail");
			if(matchTokenType(LexicalStatic.T_N_PUNCTUATION_DOT, dot)
				&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id)
				&& idnest_Tail(id, varElementNode))
			{
				info(SYNTAX,"idnest_ -> . id idnest_Tail");
				return true;
			}
			else
				return false;
		}
		
		return false;
	}
	
	/**
	 * idnest_Tail -> ( aParams ) 
	 * idnest_Tail -> indice_List
	 * @return
	 */
	private boolean  idnest_Tail(IdNode id, VarElementNode varElementNode)
	{
		IdNode rlp = new IdNode(), rrp = new IdNode();
		FCallNode fCallNode =  new  FCallNode(); 
		DataMemberNode dataMemberNode = new DataMemberNode();
		ExprNode exprNode = new ExprNode();
		AParamNode aParamNode = new AParamNode();
		ArithExprNode arithExprNode = new ArithExprNode();
		IndexListNode indexListNode = new IndexListNode(); 
		if (!skipErrors("idnest_Tail", "idnest_Tail")) {
			return false;
		}
		
		if(checkFirstSet("(")){
			printGrammar("idnest_Tail", "( aParams )");
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp)
				&& aParams(exprNode, aParamNode)
				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp)) 
			{
				fCallNode.setFCallNode(id, aParamNode);
				varElementNode.addChild(fCallNode);
				info(SYNTAX," idnest_Tail -> ( aParams )");
				
				return true;				
			}
			 else
					return false;
		}
		else if(checkFirstSet("indice_List")){
			printGrammar("idnest_Tail", "indice_List");
			if (indice_List(arithExprNode, indexListNode)) 
			{
				dataMemberNode.setDataMemberNode(id, indexListNode);
				varElementNode.addChild(dataMemberNode);
				info(SYNTAX," idnest_Tail -> indice_List");
				return true;				
			}
			 else
					return false;
		}
		
		return false;
	}
	
	/**
	 * indice -> [ arithExpr ]
	 * @return
	 */
	private boolean indice(ArithExprNode arithExprNode)
	{
		IdNode slp = new IdNode(), srp = new IdNode();
		if (!skipErrors("indice", "")) {
			return false;
		}
		
		if (checkFirstSet("[")) {
			// A -4 attribute migration
			//Symbol prevSymb = new Symbol();
			//copySymbol(prevSymb, symbol);
			//Symbol arithExpr = new Symbol();
			//semantics.pushReg("r11");
			// A - 4
			printGrammar("indice", "[ arithExpr ]");
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_SQUARE_LEFT_PARENTHESIS, slp) 
					&& arithExpr(arithExprNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SQUARE_RIGHT_PARENTHESIS, srp)) {
				info(SYNTAX, "indice -> [ arithExpr ]");
				//semantics.checkIndex(arithExpr);
				//copySymbol(symbol, prevSymb);
				//semantics.popReg("r11");
				return true;
			} else
				return false;
		}
		return false;
	}	
	
	/**
	 * aParamsTail -> ',' expr
	 * @return
	 */
	private boolean aParamsTail(ExprNode exprNode) {
		IdNode comma = new IdNode();
		if (!skipErrors(",", "")) {
			return false;
		}
		if (checkFirstSet(",")) {
			//printGrammar("A_P_T", ", expr");
			printGrammar("aParamsTail", ", expr");
			//Symbol expr = new Symbol();
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_COMMA, comma) && expr(exprNode)) {
				info(SYNTAX,"aParamsTail -> , expr");
				//semantics.aParams.add(expr);
				return true;
			} else
				return false;
		}
		return false;
	}
	
	/**
	 * arraySize_List -> arraySize arraySize_List
	 * arraySize_List -> EPSILON
	 * @return
	 */
	private boolean arraySize_List(NumNode numNode, DimListNode dimList) {
		NumNode _numNode = new NumNode();
		if (!skipErrors("arraySize", "arraySize_List")) {
			return false;
		}
		if (checkFirstSet("arraySize")) {
			printGrammar("arraySize_List", "arraySize arraySize_List");
			if (arraySize(_numNode))
			{
				dimList.addChild(_numNode);
				if(arraySize_List(_numNode, dimList)) {
					info(SYNTAX,
							"arraySize_List -> arraySize arraySize_List");					
					return true;
				} else
					return false;
			}
			else
				return false;
					
		} 
		else return checkEpsilon("arraySize_List", numNode, dimList);
	}
		
	/**
	 * arraySize  -> '[' 'intValue' ']' 
	 * @return
	 */
	private boolean arraySize(NumNode numNode) {
		IdNode slp = new IdNode(), srp = new IdNode(); 
		if (!skipErrors("[", "")) {
			return false;
		}
		
		if (checkFirstSet("arraySize")) {
			printGrammar("arraySize", " [ "+LexicalStatic.T_N_INTEGER+" ] ");
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_SQUARE_LEFT_PARENTHESIS, slp)
					//--&& matchTokenType("T_INTEGER", SYMBOLTYPE.ARRAYSIZE)
					&& matchTokenType(LexicalStatic.T_N_INTEGER, numNode)
					&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_SQUARE_RIGHT_PARENTHESIS, srp)) {
				info(SYNTAX,"arraySize -> [ intValue ]");
				
				return true;
			} else
				return false;
		}
		return false;
	}
	
	/**
	 * fParams -> type id arraySize_List fParamsTail_List
	 * fParams -> EPSILON
	 * @return
	 */
	private boolean fParams(ParamListNode paramList)
	{
		IdNode id = new IdNode();
		TypeNode typeNode = new TypeNode();
		NumNode numNode = new NumNode(); 
	    DimListNode dimList = new DimListNode();
	    
	    VarDeclNode funcParams = new VarDeclNode();
	    
		if (!skipErrors("type", "fParams")) {
			return false;
		}
		
		if (checkFirstSet("type")) {
			printGrammar("fParams", "type id arraySize_List fParamsTail_List");
			if (matchType(typeNode)
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id)
					&& arraySize_List(numNode, dimList) ) {
				// A -4 Semantic rules - function overloading checking
				// System.out.println(symbol.getToken().getValue());
				// semantics.isFunctionReDefined(symbol);
				
				funcParams.setVarDeclNode(typeNode, id, dimList);
				paramList.addChild(funcParams);
				if(fParamsTail_List(funcParams, paramList))
				{
					info(SYNTAX,"fParams -> type id arraySize_List fParamsTail_List");
					return true;
				}
				else
				{
					return false;
				}
				
			} else
				return false;
		} 
		else
			return checkEpsilon("fParams");
		
	}
	
	/**
	 * aParams -> expr aParamsTail_List
	 * aParams -> EPSILON
	 * @return
	 */
	private boolean aParams(ExprNode exprNode, AParamNode aParamNode)
	{
		if (!skipErrors("expr", "aParams")) {
			return false;
		}
		ExprNode _exprNode = new ExprNode();
		if (checkFirstSet("expr")) {
			printGrammar("aParams", "expr aParamsTail_List");
			// A - 4
			//Symbol expr = new Symbol();
			//semantics.aParams.add(expr);
			// A - 4
			if (expr(_exprNode)) {
				if (aParamsTail_List(_exprNode, aParamNode)) {
					info(SYNTAX, "aParams -> expr aParamsTail_List");					
					return true;
				}
				else
					return false;
				
			} else
				return false;
		} 
		else
			return checkEpsilon("aParams", exprNode, aParamNode);
	}
	
	/**
	 * fParamsTail -> , type id arraySize_List
	 * @return
	 */
	private boolean fParamsTail(VarDeclNode funcParams) {
		IdNode comma = new IdNode() , id  = new IdNode();
		TypeNode typeNode = new TypeNode();
		NumNode numNode = new NumNode();
		DimListNode dimList = new DimListNode();
		if (!skipErrors(",", "")) {
			return false;
		}
		
		if (checkFirstSet(",")) {
			printGrammar("fParamsTail", ", type id arraySize_List");
			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_COMMA, comma) 
					&& matchType(typeNode)
					&& matchTokenType(LexicalStatic.T_N_IDENTIFIER, id)
					&& arraySize_List(numNode, dimList)) {
				info(SYNTAX,"fParamsTail -> , type id arraySize_List");
				funcParams.setVarDeclNode(typeNode, id, dimList);
				return true;
			} else
				return false;
		}
		return false;
	}
	
	/**
	 * funcDef_List -> funcDef funcDef_List
	 * funcDef_List -> EPSILON
	 * @return
	 */
	private boolean funcDef_List(FuncDefNode funcDefNode, FuncDefListNode funcDefListNode)
	{
		FuncDefNode _funcDefNode = new FuncDefNode();
		if (!skipErrors("funcDef", "funcDef_List")) {
			return false;
		}
		
		if (checkFirstSet("funcDef")) {
			printGrammar("funcDef_List", "funcDef funcDef_List");
			if (funcDef(_funcDefNode))
			{
				funcDefListNode.addChild(_funcDefNode);
				
				if(funcDef_List(_funcDefNode, funcDefListNode)) {					
					info(SYNTAX,"funcDef_List -> funcDef funcDef_List");
					return true;
				} else
					return false;
			}
			else
				return false;				
					
		}
		else 
			return checkEpsilon("funcDef_List", funcDefNode, funcDefListNode);
	}	
	
	/**
	 * statement_List -> statement statement_List
	 * statement_List -> EPSILON
	 * @return
	 */
	private boolean statement_List(StatBlockNode statBlockNode)
	{
		if (!skipErrors("statement", "statement_List")) {
			return false;
		}
		
		if (checkFirstSet("statement")) {
			printGrammar("statement_List", "statement statement_List");
			if (statement(statBlockNode) && statement_List(statBlockNode)) {
				info(SYNTAX,"statement_List -> statement statement_List");
				return true;
			} else
				return false;
		}
		else 
			return checkEpsilon("statement_List");
	}
	
	/**
	 * indice_List  -> indice indice_List  
	 * indice_List  -> EPSILON 
	 * @return
	 */
	private boolean indice_List(ArithExprNode  arithExprNode, IndexListNode indexListNode)
	{
		ArithExprNode  _arithExprNode = new ArithExprNode();
		if (!skipErrors("indice", "indice_List")) {
			return false;
		}
		
		if (checkFirstSet("indice")) {
			printGrammar("indice_List", "indice indice_List");
			if (indice(_arithExprNode))
			{
				indexListNode.addChild(_arithExprNode);
				if(indice_List(_arithExprNode, indexListNode)) {
					info(SYNTAX,"indice_List  -> indice indice_List");
					
					
					return true;
				} else
					return false;
			}
			else
				return false;
				
				
		}
		else 
			return checkEpsilon("indice_List", arithExprNode, indexListNode);
	}
	
	/**
	 * fParamsTail_List -> fParamsTail fParamsTail_List
	 * fParamsTail_List -> EPSILON 
	 * @return
	 */
	private boolean fParamsTail_List(VarDeclNode funcParams, ParamListNode paramList)
	{
		VarDeclNode _funcParams = new VarDeclNode();
		if (!skipErrors("fParamsTail", "fParamsTail_List")) {
			return false;
		}
		
		if (checkFirstSet("fParamsTail")) {
			printGrammar("fParamsTail_List", "fParamsTail fParamsTail_List");
			if (fParamsTail(_funcParams) && fParamsTail_List(_funcParams, paramList)) {
				info(SYNTAX,"fParamsTail_List -> fParamsTail fParamsTail_List");
				paramList.addChild(_funcParams);
				
				return true;
			} else
				return false;
		}
		else 
			return checkEpsilon("fParamsTail_List", funcParams, paramList);
	}
	
	/**
	 * aParamsTail_List -> aParamsTail aParamsTail_List
	 * aParamsTail_List -> EPSILON
	 * @return
	 */
	private boolean aParamsTail_List(ExprNode exprNode, AParamNode aParamNode)
	{
		ExprNode _exprNode = new ExprNode();
		
		if (!skipErrors("aParamsTail", "aParamsTail_List")) {
			return false;
		}		
		if (checkFirstSet("aParamsTail")) {
			printGrammar("aParamsTail_List", "aParamsTail aParamsTail_List");
			if (aParamsTail(_exprNode) && aParamsTail_List(_exprNode, aParamNode)) {
				info(SYNTAX,"aParamsTail_List -> aParamsTail aParamsTail_List");
				aParamNode.addChild(exprNode);
				return true;
			} else
				return false;
		}
		else 
			return checkEpsilon("aParamsTail_List", exprNode, aParamNode);
	}
	
	/**
	 * relOp -> 'eq' 
	 * relOp -> 'neq'
	 * relOp -> 'lt'
	 * relOp -> 'gt'
	 * relOp -> 'leq'
	 * relOp -> 'geq'
	 * addOp -> '+' 
	 * addOp -> '-' 
	 * addOp -> 'or'
	 * multOp -> '*' 
	 * multOp -> '/'
	 * multOp -> 'and'
	 * @param tokenType
	 * @return
	 */
	private boolean matchTokenType(String tokenType, AstNode idNode) {
			if (tokenType.equals("relOp")
					&& token.getDescription().toString().startsWith("OPERATOR_REL_")) {
				printGrammar("relOp", token.getValue());
				info(SYNTAX,"relOp -> " + token.getValue());
				idNode.setDataToken(token);
				getNextToken();
				return true;
			} else if (tokenType.equals("addOp")
					&& (token.getValue().equals("+")
							|| token.getValue().equals("-") || token.getValue()
							.equals("or"))) {
				info(SYNTAX, "addOp" + " -> " + token.getValue());
				printGrammar("addOp", token.getValue());
				idNode.setDataToken(token);
				getNextToken();
				return true;
			} else if (tokenType.equals("multOp")
					&& (token.getValue().equals("*")
							|| token.getValue().equals("/") || token.getValue()
							.equals("and"))) {
				info(SYNTAX, "multOp -> "+ token.getValue());
				printGrammar("multOp", token.getValue());
				idNode.setDataToken(token);
				getNextToken();
				return true;
			} else if (tokenType.equals(token.getDescription())) {
				info(SYNTAX, token.getDescription() + " -> "+ token.getValue());
				if (token.getDescription().equals(LexicalStatic.T_N_IDENTIFIER)) {
					printGrammar("id", token.getValue());
				} else if (token.getDescription().equals(LexicalStatic.T_N_INTEGER)) {
					printGrammar(LexicalStatic.T_N_INTEGER, token.getValue());
				} else if (token.getDescription().equals(LexicalStatic.T_N_FLOAT)) {
					printGrammar(LexicalStatic.T_N_FLOAT, token.getValue());
				}
				idNode.setDataToken(token);
				getNextToken();
				return true;
			} else {
				errorOrWarning(ERROR,
						"IN LINE NUMBER:\t" + token.getLine()
								+ ":\tExpected One of these token Type:\t"
								+ tokenType, token.getLine());
			}
			if (token.getValue().equals("$")) {
				errorOrWarning(WARNING, "REACHED END OF FILE", Integer.MAX_VALUE);
				warning(SYNTAX, "REACHED END OF FILE");
				return false;
			}
			errorOrWarning(ERROR, "INVALID TOKEN MATCH: "
					+ token.getDescription() + "\t" + token.getValue(), token.getLine());
			// getNextToken();
			return true;
		}
	
	/**
	 * factor_Tail -> ( aParams ) 
	 * factor_Tail -> EPSILON
	 * @return
	 */
//	private boolean factor_Tail(AParamNode aParamNode)
//	{
//		IdNode rlp = null, rrp = null; 
//		ExprNode exprNode = null;
//		if (!skipErrors("factor_Tail", "factor_Tail")) {
//			return false;
//		}
//		
//		if(checkFirstSet("(")){
//			printGrammar("factor_Tail", "( aParams )");
//			if (matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_LEFT_PARENTHESIS, rlp)
//				&& aParams(exprNode, aParamNode)
//				&& matchTokenType(LexicalStatic.T_N_PUNCTUATION_ROUND_RIGHT_PARENTHESIS, rrp)) 
//			{
//				info(SYNTAX," factor_Tail -> ( aParams )");
//				return true;				
//			}
//			 else
//					return false;
//		}
//		else
//			return checkEpsilon("factor_Tail");		
//	}
	
	/**
	 * funcDecl_List -> funcDecl funcDecl_List
	 * funcDecl_List -> EPSILON
	 * @return
	 */
//	private boolean funcDecl_List(FuncDeclNode funcDeclN, FuncDeclListNode funcDeclLst)
//	{
//		FuncDeclNode _funcDeclN =null;
//		if (!skipErrors("funcDecl", "funcDecl_List")) {
//			return false;
//		}
//		
//		if (checkFirstSet("funcDecl")) {
//			printGrammar("funcDecl_List", "funcDecl funcDecl_List");
//			if (funcDecl(_funcDeclN) && funcDecl_List(_funcDeclN, funcDeclLst)) {
//				
//				if(funcDeclLst == null)
//				{
//					funcDeclLst = new FuncDeclListNode();
//				}
//				funcDeclLst.addChild(funcDeclN);
//				info(SYNTAX,"funcDecl_List -> funcDecl funcDecl_List");
//				return true;
//			} else
//				return false;
//		}
//		else 
//			return checkEpsilon("funcDecl_List", funcDeclN, funcDeclLst);
//	}
	
	/**
	 * match for  Reserve Word Tokens
	 * @param reserveWord
	 * @return
	 */
	private boolean matchReserveWordTokenType(String reserveWord, AstNode rWord)
	{
		return matchTokenType(LexicalStatic.T_N_RESERVE_WORD+reserveWord.toUpperCase(), rWord);
	}
	
	/**
	 * copy Token
	 * @param token
	 * @return
	 */
//	private Token copyToken(Token token) {
//		Token cpyToken = new Token();
//		if (token != null)
//			cpyToken = new Token(token.getLine(), token.getValue(),
//					token.getDescription(), token.getType());
//		return cpyToken;
//	}
	
	/**
	 * match Token Value
	 * @param tokenType
	 * @return
	 */
	private boolean matchTokenValue(String tokenType, IdNode tvNode) {
		if (tokenType.equals(token.getValue())) {
			info(SYNTAX, token.getDescription() + " -> "
					+ token.getValue());
			if (token.getValue().equals("program")) {
				//symbol = new Symbol();
				//symbol.setDataType(copyToken(prevToken));
				//symbol.setToken(copyToken(token));
				//semantics.programDecl(symbol);
			}
			tvNode.setIdNode(token);
			getNextToken();
			return true;
		} else {
			errorOrWarning(ERROR,
					"IN LINE NUMBER:\t" + token.getLine()
							+ ":\tExpected One of these token Type:\t"
							+ tokenType, token.getLine());
		}
		if (token.getValue().equals("$")) {
			errorOrWarning(WARNING, "REACHED END OF FILE", Integer.MAX_VALUE);
			warning(SYNTAX, "REACHED END OF FILE");
			return false;
		}
		getNextToken();
		return true;
		
	}
	
	/**
	 * type -> id 
	 * type -> numericType
	 * @return
	 */
	private boolean matchType(TypeNode typeNode) {		
		if (token.getDescription().equals(LexicalStatic.T_N_IDENTIFIER)) {
			info(SYNTAX, "type -> id"
					+ "\t->\t" + token.getValue());
			printGrammar("type", token.getValue());
			typeNode.setTypeNode(token);
			// if (!semantics.isDataTypeDefined(token)) {
			// System.out.println("UNDEFINED DATA TYPE:\t" + token.getValue());
			// }
			getNextToken();
			return true;
		}
		else 
		{
			printGrammar("type", "numericType");
			return numericType(typeNode);
		}
	}
	
	/**
	 * floatNum -> float
	 * @return
	 */
	private boolean floatNum(IdNode floatNumber) {
		if (token.getDescription().equals(LexicalStatic.T_N_FLOAT)) {
			info(SYNTAX,
					"floatNum -> "+LexicalStatic.T_N_FLOAT);
			printGrammar("floatNum", token.getValue());
			floatNumber.setIdNode(token);
			getNextToken();
			return true;
		} else {
			errorOrWarning("ERROR","IN LINE NUMBER:\t" + token.getLine()
							+ ":\tExpected One of these token Type:\t"
							+ "float", token.getLine());
		}
		if (token.getValue().equals("$")) {
			errorOrWarning(WARNING, "REACHED END OF FILE", Integer.MAX_VALUE);
			warning(SYNTAX, "REACHED END OF FILE");
			return false;
		}
		getNextToken();
		return true;
	}
	
	/**
	 * intNum -> int
	 * @return
	 */
	private boolean intNum(IdNode intNum) {
		if (token.getDescription().equals(LexicalStatic.T_N_INTEGER)) {
			info(SYNTAX,
					"intNum -> "+LexicalStatic.T_N_INTEGER);
			printGrammar("intNum", token.getValue());
			intNum.setIdNode(token);
			getNextToken();
			return true;
		}  else {
			errorOrWarning(ERROR, "IN LINE NUMBER:\t" + token.getLine()
							+ ":\tExpected One of these token Type:\t"
							+ "int", token.getLine());
		}
		if (token.getValue().equals("$")) {
			errorOrWarning(WARNING, "REACHED END OF FILE", Integer.MAX_VALUE);
			warning(SYNTAX, "REACHED END OF FILE");
			return false;
		}
		getNextToken();
		return true;
	}

	/**
	 * numericType -> 'float'
	 * numericType -> 'int'
	 * @return
	 */
	private boolean numericType(TypeNode  numericT) {	
		if (token.getDescription().equals(LexicalStatic.T_N_RESERVE_WORD+LexicalStatic.R_W_INT.toUpperCase())) {
			info(SYNTAX, "numericType -> int");
			printGrammar("numericType", token.getValue());
			numericT.setTypeNode(token);
			getNextToken();
			return true;
		} else if (token.getDescription().equals(LexicalStatic.T_N_RESERVE_WORD+LexicalStatic.R_W_FLOAT.toUpperCase())) {
			info(SYNTAX,"numericType -> float");
			printGrammar("numericType", token.getValue());
			numericT.setTypeNode(token);
			getNextToken();
			return true;
		} 
		else {
			errorOrWarning(ERROR,"IN LINE NUMBER:\t" + token.getLine()
							+ ":\tExpected One of these token Type:\t"
							+ "float | int", token.getLine());
			if (token.getValue().equals("$")) {
				warning(SYNTAX,
						"REACHED END OF FILE");
				errorOrWarning(WARNING,
						"REACHED END OF FILE", Integer.MAX_VALUE);
				return false;
			}
			errorOrWarning(WARNING,
					"SKIPPING TOKEN:\t" + token.getValue()
							+ "\tAT LINE NUMBER:\t" + token.getLine(), token.getLine());
			getNextToken();
		}
		return true;
	}
	
	/**
	 * sign -> +
	 * sign -> - 
	 * @return
	 */
	private boolean matchSign(IdNode sign) {
		if (token.getValue().equals("+")) {
			print(SYNTAX, "sign -> +");
			printGrammar("sign", token.getValue());
			sign.setIdNode(token);
			getNextToken();
			return true;
		} else if (token.getValue().equals("-")) {
			print(SYNTAX, "sign -> -");
			printGrammar("sign", token.getValue());
			sign.setIdNode(token);
			getNextToken();
			return true;
		} else {
			errorOrWarning(ERROR, "IN LINE NUMBER:\t" + token.getLine()
							+ ":\tExpected One of these token Type:\t"
							+ "+ | - ", token.getLine());
		}
		if (token.getValue().equals("$")) {
			errorOrWarning(WARNING, "REACHED END OF FILE", Integer.MAX_VALUE);
			warning(SYNTAX, "REACHED END OF FILE");
			return false;
		}
		getNextToken();
		return true;
	}
	
	/**
	 * assignOp -> =
	 * @return
	 */
	private boolean assignOp(IdNode assign)
	{
		return matchTokenValue("=" , assign);
	}
	
	/**
	 * Skip Token to avoid errors. 
	 * Return true, if token is exist in either in FirstSet orFollowSet.
	 * else skip the token until get the token is exist in either in FirstSet orFollowSet.
	 * @param firstG
	 * @param followG
	 * @return
	 */
	private boolean skipErrors(String firstG, String followG) {
		
		if (checkFirstSet(firstG) || checkFollowSet(followG))
			return true;

		String firstSet = firstAndFollw.getFirstSet(firstG);
		String followSet = firstAndFollw.getFollowSet(followG);

		String ffSet = "";
		if (firstSet != null) {
			ffSet += firstSet;
		}
		if (followSet != null) {
			ffSet += " " + followSet;
		}
		//String ffArraySet[] = ffSet.split(" ");
		
		errorOrWarning(ERROR, "IN LINE NUMBER:\t"
				+ token.getLine() + ":\tExpected One of these tokens:\t"
				+ ffSet, token.getLine());
		
		while (!token.getValue().equals("$")) {
			if (checkFirstSet(firstG) || checkFollowSet(followG)) {
				errorOrWarning(WARNING,"RESUME PARSING FROM  TOKEN:\t" + token.getValue()
				+ "\tAT LINE NUMBER:\t" + token.getLine(), token.getLine());
				
				return true;
			} else {
				errorOrWarning(WARNING, "SKIPPING TOKEN:\t" + token.getValue()
								+ "\tAT LINE NUMBER:\t" + token.getLine(), token.getLine());
				getNextToken();
			}
		}
		if (token.getValue().equals("$")) {
			warning(SYNTAX,"REACHED END OF FILE");
			errorOrWarning(WARNING, "REACHED END OF FILE", Integer.MAX_VALUE);
		}

		return false;
	}
	

	/**
	 * Check the first set
	 * Return true grmrSymbol matched token value or return of FirstSet is null.
	 * else return false
	 * @param grmrSymbol
	 * @return
	 */
	private boolean checkFirstSet(String grmrSymbol) {
		String firstValue = firstAndFollw.matchFirst(grmrSymbol, token);
		if (firstValue == "") {
//			if (grmrSymbol.equals(token.getValue()))
//			{
//				return true;
//			}
//			else if (grmrSymbol.equals("sr"))
//			{
//				if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_SCOPE_RESOLUTION))
//					return true;
//			}
//			else if (grmrSymbol.equals("id"))
//			{
//				if(token.getDescription().equals(LexicalStatic.T_N_IDENTIFIER))
//					return true;
//			}
//			else if (grmrSymbol.equals("intNum"))
//			{
//				if(token.getDescription().equals(LexicalStatic.T_N_INTEGER))
//				{
//					return true;
//				}
//			}			
//			else if (grmrSymbol.equals("floatNum"))
//			{
//				if(token.getDescription().equals(LexicalStatic.T_N_FLOAT))
//				{
//					return true;
//				}
//			}
			return firstAndFollw.match(grmrSymbol, token);
			
			//return false;
		} else if (firstValue != null)
			return false;
		return true;
	}

	/**
	 * Return true if result of FollowSet is null
	 * @param grmrSymbol
	 * @return
	 */
	private boolean checkFollowSet(String grmrSymbol) {
		String followValue = firstAndFollw.matchFollow(grmrSymbol, token);
		if (followValue == null)
		{
			return true;
		}
		else if (followValue == "") {
				return firstAndFollw.match(grmrSymbol, token);
		}
		
		return false;
	}
	
	/**
	 * Check Epsilon case
	 * @param nonTerminalname
	 * @return
	 */
	private boolean checkEpsilon(String nonTerminalname)
	{
		if (checkFollowSet(nonTerminalname)) {
			printGrammar(nonTerminalname, "");
			print(SYNTAX, nonTerminalname+" -> EPSILON");
			return true;
		}
		return false;
	}
	
	/**
	 * Check Epsilon case and replace AstNodes
	 * @param nonTerminalname
	 * @return
	 */
	private boolean checkEpsilon(String nonTerminalname, AstNode nod1, AstNode nod2)
	{
		if (checkFollowSet(nonTerminalname)) {
			printGrammar(nonTerminalname, "");
			print(SYNTAX, nonTerminalname+" -> EPSILON");
			nod2 = nod1;
			return true;
		}
		return false;
	}

	/**
	 * Get Derivation Log
	 * @return
	 */
	public String getDerivationLog()
	{
		return derivationLog.toString();
	}
	
	/**
	 * Get Errors Log
	 * @return
	 */
	public String getErrorsLog()
	{
		return errorsLog.toString();
	}
	
	/**
	 * Get Grammar Parsing Log
	 * @return
	 */
	public String getGrammarParsingLog()
	{
		return grammarParsingLog.toString();
	}

	/**
	 * @return the errorList
	 */
	public ArrayList<Errors> getErrorList() {
		return errorList;
	}
		
	
}

