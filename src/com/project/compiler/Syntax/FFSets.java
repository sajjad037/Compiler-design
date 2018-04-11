package com.project.compiler.Syntax;

import java.util.HashMap;

import com.project.compiler.Config.LexicalStatic;
import com.project.compiler.Model.Token;

/**
 * First and follow set class
 * @author SajjadAshrafCan
 *
 */
public class FFSets {
	HashMap<String, String> firstMap= new HashMap<String, String>(80);
	HashMap<String, String> followMap = new HashMap<String, String>(80);
	
	static String ffSet[][] = {
		 // Non-Terminal,				First Set,											Follow Set.
			{"prog",					"class id float int program",						"$"},
			{"classDecl",				"class",											""},
			{"funcDecl",				"id float int",										""},
			{"funcHead_Optional",		"sr",												"("},	
			{"funcHead",				"id float int",										""},
			{"funcDef",					"id float int",										""},
			{"funcBody",				"{",												""},
			{"varDecl_FuncDecl_List",	"id float int, { ;",								"}"},
			{"varDecl_FunctDef_Tail",	"[ ; sr (",											""},
			{"indiceIdnestList_Tail",	". [",												"="},
			{"varDeclStatement_Tail",	". [ = id",											""},
			{"varDecl_statement_List",	"float int id for if get put return",				"}"},
			{"statement",				"id for if get put return",							""},
			{"assignStatSemicolon",		"id",												""},
			{"remainingStatement",		"for if get put return",							""},
			{"assignStat",				"id",												""},
			{"statBlock",				"{ id for if get put return",						"; else"},
			{"expr",					"( floatNum intNum not id + -",						""},
			{"exp_Tail",				"eq geq gt leq lt neq",								"; ) ,"},
			{"relExpr",					"( floatNum intNum not id + -",						""},	
			{"arithExpr",				"( floatNum intNum not id + -",						""},
			{"arithExpr_Tail",			" + - or",											"] ; ) , eq geq gt leq lt neq"},	
			{"sign",					" + -",												""},	
			{"term",					"( floatNum intNum not id + -",						""},	
			{"term_Tail",				"* / and",											"] ; ) , eq geq gt leq lt neq + - or"},		
			{"factor",					"( floatNum intNum not id + -",						""},	
			{"functionCall",			"id",												""},	
			{"variable",				"id",												""},
			{"variable_Tail",			"( [ .",											"= ) ] ; , eq geq gt leq lt neq + - or * / and"},		
			{"idnest_List_Tail",		".",												"= ) ] ; , eq geq gt leq lt neq + - or * / and"},		
			{"idnest_",					".",												""},
			{"idnest_Tail",				"( [",												"= ) ] ; , eq geq gt leq lt neq + - or * / and ."},	
			{"factor_Tail",				"(",												""},	
			{"indice",					"[",												""},	
			{"arraySize",				"[",												""},	
			{"type",					"id float int",										""},
			{"numericType",				"float int",										""},		
			{"fParams",					"id float int",										")"},
			{"aParams",					"( floatNum intNum not id + -",						")"},	
			{"fParamsTail",				",",												""},			
			{"aParamsTail",				",",												""},			
			{"classDecl_List",			"class",											"id float int program"},			
			{"classDecl_Optional",		":",												"{"},				
			{"classDecl_Listtow",		",",												"{"},			
			{"funcDef_List",			"id float int",										"program"},			
			{"funcDecl_List",			"id float int",										""},		
			{"statement_List",			"id for if get put return",							"}"},			
			{"arraySize_List",			"[",												"; ) ,"},			
			{"indice_List",				"[",												" = ) ] ; , eq geq gt leq lt neq + - or * / and ."},			
			{"fParamsTail_List",		",",												")"},				
			{"aParamsTail_List",		",",												")"},			
			{"assignOp",				"=",												""},			
			{"relOp",					"eq geq gt leq lt neq",								""},		
			{"addOp",					"+ - or",											""},	
			{"multOp",					"* / and",											""},	
			{"id",						"id",												""}
	};		
	
	/**
	 * Constructor
	 * Fill up first and follow maps.
	 */
	public FFSets() {		
		for (int i = 0; i < ffSet.length; i++) {
			for (int j = 0; j < 3; j++) {
				firstMap.put(ffSet[i][0], ffSet[i][1]);
				followMap.put(ffSet[i][0], ffSet[i][2]);
			}
		}
	}

	/**
	 * match Token with FirstSet(grmrSymbol)
	 * -If grmrSymbol has no FirstSet return empty String.
	 *  IF FirstSet(grmrSymbol) is match with Token value or getDescription return null
	 *  - else return FirstSet(grmrSymbol) 
	 * @param grmrSymbol
	 * @param token
	 * @return
	 */
	public String matchFirst(String grmrSymbol, Token token) {
		String matchFirst = "";
		String firstSet = firstMap.get(grmrSymbol);

		if(firstSet == null){
			// for terminal symbols, eg: class program
			return matchFirst;
		}
		
		if(matchSlipt(firstSet, token))
		{
			return null;
		}
		else
		{
			return firstSet;
		}
	}

	/**
	 * match Token with FollowSet(grmrSymbol)
	 * @param grmrSymbol
	 * @param token
	 * @return
	 */
	public String matchFollow(String grmrSymbol, Token token) {
		String matchFollow = "";
		String followSet = followMap.get(grmrSymbol);
		if(followSet == null){
			return matchFollow;
		}
		
		if(matchSlipt(followSet, token))
		{
			return null;
		}
		else
		{
			return followSet;
		}
	}
	
	public boolean matchSlipt(String ffSet, Token token)
	{
		String ffSetArray[] = ffSet.split(" ");
		String symbol = "";
		for (int i = 0; i < ffSetArray.length; i++) {
			symbol = ffSetArray[i];
			
			if(match(symbol, token))
			{
				return true;
			}
//			
//			if(ffSetArray[i].equals("id")){
//				if(token.getDescription().equals(LexicalStatic.T_N_IDENTIFIER))
//					return null;
//			}else if(ffSetArray[i].equals("intNum")){
//				if(token.getDescription().equals(LexicalStatic.T_N_INTEGER))
//					return null;
//			}
//			else if(ffSetArray[i].equals("floatNum")){
//				if(token.getDescription().equals(LexicalStatic.T_N_FLOAT))
//					return null;
//			}
//			else if(ffSetArray[i].equals("eq")){
//				if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_EQUAL))
//					return null;
//			}
//			else if(ffSetArray[i].equals("geq")){
//				if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_GREATERTHAN_EQUAL))
//					return null;
//			}
//			else if(ffSetArray[i].equals("gt")){
//				if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_GREATERTHAN))
//					return null;
//			}
//			else if(ffSetArray[i].equals("leq")){
//				if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_LESSTHAN_EQUAL))
//					return null;
//			}
//			else if(ffSetArray[i].equals("lt")){
//				if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_LESSTHAN))
//					return null;
//			}
//			else if(ffSetArray[i].equals("neq")){
//				if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_NOT_EQUAL))
//					return null;
//			}
//			else if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_SCOPE_RESOLUTION)){  //::
//				return null;
//			}
//			else if(token.getValue().equals(ffSetArray[i])){
//				return null;
//			}
	}
		return false;
	}
	
	public boolean match(String grmrSymbol, Token token)
	{
		if (grmrSymbol.equals(token.getValue()))
		{
			return true;
		}
		else if (grmrSymbol.equals("sr"))
		{
			if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_SCOPE_RESOLUTION))
				return true;
		}
		else if (grmrSymbol.equals("id"))
		{
			if(token.getDescription().equals(LexicalStatic.T_N_IDENTIFIER))
				return true;
		}
		else if (grmrSymbol.equals("intNum"))
		{
			if(token.getDescription().equals(LexicalStatic.T_N_INTEGER))
			{
				return true;
			}
		}			
		else if (grmrSymbol.equals("floatNum"))
		{
			if(token.getDescription().equals(LexicalStatic.T_N_FLOAT))
			{
				return true;
			}
		}
		else if(grmrSymbol.equals("eq")){
			if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_EQUAL))
				return true;
		}
		else if(grmrSymbol.equals("geq")){
			if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_GREATERTHAN_EQUAL))
				return true;
		}
		else if(grmrSymbol.equals("gt")){
			if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_GREATERTHAN))
				return true;
		}
		else if(grmrSymbol.equals("leq")){
			if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_LESSTHAN_EQUAL))
				return true;
		}
		else if(grmrSymbol.equals("lt")){
			if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_LESSTHAN))
				return true;
		}
		else if(grmrSymbol.equals("neq")){
			if(token.getDescription().equals(LexicalStatic.T_N_OPERATOR_NOT_EQUAL))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Get First set of symbol 
	 * @param firstG
	 * @return
	 */
	public String getFirstSet(String firstG){
		return firstMap.get(firstG);
	}
	
	/**
	 * Get follow set of symbol
	 * @param firstG
	 * @return
	 */
	public String getFollowSet(String firstG){
		return followMap.get(firstG);
	}

}
