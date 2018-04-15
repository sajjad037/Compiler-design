package com.project.compiler.Visitor;

import com.project.compiler.Ast.*;

public class VisitorBase {

	//public void visit(AST node)       			{};
	
	public void visit(AddOpNode node)       	{};
	public void visit(AParamNode node)       	{};
	public void visit(ArithExprNode node)       {};
	public void visit(AssignStatNode node)      {};
	public void visit(AstNode node)       		{};
	public void visit(ClassListNode node)       {};
	public void visit(ClassNode node)       	{};
	public void visit(DataMemberNode node)      {};
	public void visit(DimListNode node)       	{};
	public void visit(ExprNode node)       		{};
	public void visit(FactorNode node)       	{};
	public void visit(FCallNode node)       	{};
	public void visit(ForStatNode node)       	{};
	public void visit(FuncDeclListNode node)    {};
	public void visit(FuncDeclNode node)       	{};
	public void visit(FuncDefListNode node)     {};
	public void visit(FuncDefNode node)       	{};
	public void visit(FuncHeadNode node)       	{};
	public void visit(GetStatNode node)       	{};
	public void visit(IdNode node)       		{};
	public void visit(IfStatNode node)       	{};
	public void visit(IndexListNode node)       {};
	public void visit(InherList node)       	{};
	public void visit(MultOpNode node)       	{};
	public void visit(NotNode node)       		{};
	public void visit(NumNode node)       		{};
	public void visit(ParamListNode node)       {};
	public void visit(ProgNode node)       		{};
	public void visit(ProgramBlockNode node)	{};
	public void visit(PutStatNode node)       	{};
	public void visit(RelExprNode node)       	{};
	public void visit(ReturnStatNode node)      {};
	public void visit(SignNode node)       		{};
	public void visit(StatBlockNode node)       {};
	public void visit(TermNode node)       		{};
	public void visit(TypeNode node)       		{};
	public void visit(VarDeclListNode node)     {};
	public void visit(VarDeclNode node)       	{};
	public void visit(VarElementNode node)      {};
	public void visit(VarNode node)      	 	{};
	
//	public AstNode getIdNodeFrom(AstNode sourceNode, int occurrence)
//	{
//		AstNode astIdNode = new IdNode();
//		int count = 0;
//		for (AstNode child : sourceNode.getChildren()) {
//			if (child instanceof IdNode) {
//				++count;
//				if(occurrence == count)
//				{
//					astIdNode =  child;	
//				}				
//			}
//			getIdNodeFrom(child, occurrence);
//		}
//		
//		return astIdNode;
//	}
}
