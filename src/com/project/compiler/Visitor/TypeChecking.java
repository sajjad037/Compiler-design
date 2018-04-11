package com.project.compiler.Visitor;
import com.project.compiler.Ast.*;
import com.project.compiler.Config.LexicalStatic;

/**
 * Visitor to compute the type of subexpressions and assignment statements. 
 * 
 * This applies only to nodes that are part of expressions and assignment statements i.e.
 * AddOpNode, MultOpNode, and AssignStatNode. 
 * 
 */

public class TypeChecking extends VisitorBase {

	public void visit(AddOpNode node){
		System.out.println("TypeCheckingVisitor Visiting AddOppNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		String leftOperandType  = node.getChildren().get(0).getType();
		String rightOperandType = node.getChildren().get(1).getType();
		
		//String leftOperandType  = getIdNodeFrom(node, 1).getType(); // First or Left Child
		//String rightOperandType = getIdNodeFrom(node, 2).getType();// 2nd or Right Child
		if( leftOperandType == rightOperandType )
			node.setType(leftOperandType);
		else{
			node.setType("typeerror");
			System.out.println("TYPE ERROR DETECTED between " 
					+ node.getChildren().get(0).getData()
					+  " and "
					+ node.getChildren().get(1).getData()
					);
		}
	}

	public void visit(MultOpNode node){ 
		System.out.println("TypeCheckingVisitor Visiting MultOpNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		String leftOperandType  = node.getChildren().get(0).getType();
		String rightOperandType = node.getChildren().get(1).getType();
		if( leftOperandType == rightOperandType )
			node.setType(leftOperandType);
		else{
			node.setType("typeerror");
			System.out.println("TYPE ERROR DETECTED between " 
								+ node.getChildren().get(0).getData()
								+  " and "
								+ node.getChildren().get(1).getData()
								);
		}
	}
	
	public void visit(AssignStatNode node){ 
		System.out.println("TypeCheckingVisitor Visiting AssignStatNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		String leftOperandType  = node.getChildren().get(0).getType();
		String rightOperandType = node.getChildren().get(1).getType();
		//String leftOperandType  = getIdNodeFrom(node, 1).getType(); // First or Left Child
		//String rightOperandType = getIdNodeFrom(node, 2).getType();// 2nd or Right Child
		if( leftOperandType == rightOperandType )
			node.setType(leftOperandType);
		else{
			node.setType("typeerror");
			System.out.println("TYPE ERROR DETECTED between " 
								+ node.getChildren().get(0).getData()
								+  " and "
								+ node.getChildren().get(1).getData()
								);
		}
	}
	
	public void visit(VarNode node) {
		System.out.println("TypeCheckingVisitor Visiting VarNode");
		for (AstNode child : node.getChildren())
			child.accept(this);

		node.setType(node.getChildren().get(0).getType());
	};
	
	public void visit(DataMemberNode node) {
		System.out.println("TypeCheckingVisitor Visiting DataMemberNode");
		
		for (AstNode child : node.getChildren())
			child.accept(this);

		node.setType(node.getChildren().get(0).getType());
	};
	
	public void visit(IdNode node) {
		System.out.println("TypeCheckingVisitor Visiting IdNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		
		// Then, do the processing of this nodes' visitor
		//node.setSubtreeString(node.getData());
//		if(node.getDataToken().getDescription().equals(LexicalStatic.T_N_INTEGER))
//		{
//			node.setType("int");
//		}
//		else if(node.getDataToken().getDescription().equals(LexicalStatic.T_N_FLOAT))
//		{
//			node.setType("float");
//		}
		
	}
	
	// Below are the visit methods for node types for which this visitor does not apply
	// They still have to propagate acceptance of the visitor to their children.
	public void visit(ClassListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ClassNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(DimListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(FuncDefListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(FuncDefNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

//	public void visit(IdNode node) {
//		for (AstNode child : node.getChildren())
//			child.accept(this);
//	};

	public void visit(AstNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(NumNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ProgNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ProgramBlockNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(PutStatNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(StatBlockNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(TypeNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(VarDeclNode node) {
			for (AstNode child : node.getChildren() )
				child.accept(this);
	 }; 
}
