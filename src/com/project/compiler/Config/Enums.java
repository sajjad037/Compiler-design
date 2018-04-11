package com.project.compiler.Config;

/**
 * This Class contains the All then Enums used in the application.
 * 
 * @author SajjadAshrafCan
 *
 */
public class Enums {

	public enum TokenType {
		TOKEN, ERROR, COMMENT, EOL, EOF
	}

//	public enum NodeType {
//		prog, classList, funcDefList, statBlock, classDecl, funcDef, inherList, membList, type, id, intNum, op, Null, scopeSpec, 
//		fparmList, membDecl, varDecl, funcDecl, dimList, statOrVarDecl, ifStat, relExpr, assignStat, var, expr, forStat, getStat, 
//		putStat, returnStat, arithExpr, addOp, term, relOp, multOp, factor, fCall, num, sign, varElement, dataMember, indexList, 
//		aParams, EPSILON
//	}

	public enum NodeType {
		AddOpNode, AParamNode, ArithExprNode, AssignStatNode, AST, AstNode, ClassListNode, ClassNode, DataMemberNode, 
		DimListNode, ExprNode, FactorNode, FCallNode, ForStatNode, FuncDeclListNode, FuncDeclNode, FuncDefListNode, 
		FuncDefNode, FuncHeadNode, GetStatNode, IdNode, IfStatNode, IndexListNode, InherList, MultOpNode, NotNode, NumNode, 
		ParamListNode, ProgNode, PutStatNode, RelExprNode, ReturnStatNode, SignNode, StatBlockNode, TermNode, TypeNode, 
		VarDeclListNode, VarDeclNode, VarElementNode, VarNode, ProgramBlockNode
	}
	
	public enum ModuleType {
		LEXICAL , SYNTAX, SEMANTIC, SEMANTIC_ACTION, SEMANTIC_AST , CODEGENERATION
	}

	public enum TokenSubType {
		ID, INTEGER, FLOAT, OPERATOR, RESERVE_WORD, PUNCTUATION, NONE
	}

	public enum TokenPrintFormat {
		REQUIRED, ENHANCE, ATOCC
	}
}
