package com.project.compiler.Visitor;

import java.util.ArrayList;

import com.project.compiler.Ast.AddOpNode;
import com.project.compiler.Ast.AssignStatNode;
import com.project.compiler.Ast.AstNode;
import com.project.compiler.Ast.ClassListNode;
import com.project.compiler.Ast.ClassNode;
import com.project.compiler.Ast.DataMemberNode;
import com.project.compiler.Ast.DimListNode;
import com.project.compiler.Ast.FuncDefListNode;
import com.project.compiler.Ast.FuncDefNode;
import com.project.compiler.Ast.IdNode;
import com.project.compiler.Ast.MultOpNode;
import com.project.compiler.Ast.NumNode;
import com.project.compiler.Ast.ProgNode;
import com.project.compiler.Ast.ProgramBlockNode;
import com.project.compiler.Ast.PutStatNode;
import com.project.compiler.Ast.StatBlockNode;
import com.project.compiler.Ast.TypeNode;
import com.project.compiler.Ast.VarDeclNode;
import com.project.compiler.Ast.VarNode;
import com.project.compiler.Model.Token;
import com.project.compiler.Model.VariablesData;

/**
 * Visitor to compute the type of subexpressions and assignment statements.
 * 
 * This applies only to nodes that are part of expressions and assignment
 * statements i.e. AddOpNode, MultOpNode, and AssignStatNode.
 * 
 */

public class TypeChecking extends VisitorBase {

	ArrayList<VariablesData> variables = new ArrayList<VariablesData>();

	public void visit(AddOpNode node) {
		System.out.println("TypeCheckingVisitor Visiting AddOppNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		String leftOperandType = node.getChildren().get(0).getType();
		String rightOperandType = node.getChildren().get(1).getType();
		if (leftOperandType.equals(rightOperandType))
			node.setType(leftOperandType);
		else {
			node.setType("typeerror");
			System.out.println("TYPE ERROR DETECTED between " + node.getChildren().get(0).getData() + " and "
					+ node.getChildren().get(1).getData());
		}
	}

	public void visit(MultOpNode node) {
		System.out.println("TypeCheckingVisitor Visiting MultOpNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		String leftOperandType = node.getChildren().get(0).getType();
		String rightOperandType = node.getChildren().get(1).getType();
		if (leftOperandType.equals(rightOperandType))
			node.setType(leftOperandType);
		else {
			node.setType("typeerror");
			System.out.println("TYPE ERROR DETECTED between " + node.getChildren().get(0).getData() + " and "
					+ node.getChildren().get(1).getData());
		}
	}

	public void visit(AssignStatNode node) {
		System.out.println("TypeCheckingVisitor Visiting AssignStatNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		String leftOperandType = node.getChildren().get(0).getType();
		String rightOperandType = node.getChildren().get(1).getType();
		if (leftOperandType.equals(rightOperandType))
			node.setType(leftOperandType);
		else {
			node.setType("typeerror");
			System.out.println("TYPE ERROR DETECTED between " + node.getChildren().get(0).getData() + " and "
					+ node.getChildren().get(1).getData());
		}
	}
	
	public void visit(VarNode node) {
		System.out.println("TypeCheckingVisitor Visiting VarNode");
		for (AstNode child : node.getChildren())
			child.accept(this);

		if (node.getChildren().get(0) instanceof IdNode) {
			String str = getVaraibleType(node.getChildren().get(0).getDataToken());
			if(!str.equals(""))
			{
				node.getChildren().get(0).setType(str);
				node.setType(str);
			}
			else
			{
				str = addUndefinedVaraible(node.getChildren().get(0).getDataToken());
				node.getChildren().get(0).setType(str);
				node.setType(str);
			}
			
		}
		
	};
	
	public void visit(DataMemberNode node) {
		System.out.println("TypeCheckingVisitor Visiting DataMemberNode");
		
		for (AstNode child : node.getChildren())
			child.accept(this);

		if (node.getChildren().get(0) instanceof IdNode) {
			String str = getVaraibleType(node.getChildren().get(0).getDataToken());
			if(!str.equals(""))
			{
				node.getChildren().get(0).setType(str);
				node.setType(str);
			}
			else
			{
				str = addUndefinedVaraible(node.getChildren().get(0).getDataToken());
				node.getChildren().get(0).setType(str);
				node.setType(str);
				
			}
			
		}
		
	};

	// Below are the visit methods for node types for which this visitor does
	// not apply
	// They still have to propagate acceptance of the visitor to their children.
	public void visit(ClassListNode node) {
		// System.out.println("TypeCheckingVisitor Visiting ClassListNode");
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ClassNode node) {
		System.out.println("TypeCheckingVisitor Visiting ClassNode");
		variables = new ArrayList<VariablesData>();
		for (AstNode child : node.getChildren())
			child.accept(this);

		// Check for Duplicate

		// Check for undefined
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
		System.out.println("TypeCheckingVisitor Visiting FuncDefNode: ");
		variables = new ArrayList<VariablesData>();

		for (AstNode child : node.getChildren())
			child.accept(this);

		// Variable List to node

		// Check for duplicate declaration

		// Check for undefined
	};

	public void visit(IdNode node) {
		System.out.println("TypeCheckingVisitor Visiting IdNode: " + node.getData());
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

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
		System.out.println("TypeCheckingVisitor Visiting ProgramBlockNode");
		variables = new ArrayList<VariablesData>();
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
		System.out.println("TypeCheckingVisitor Visiting VarDeclNode");
		for (AstNode child : node.getChildren())
			child.accept(this);

		Token typeToken = node.getChildren().get(0).getDataToken();
		Token nameToken = node.getChildren().get(1).getDataToken();

		variables.add(new VariablesData(nameToken.getValue(), typeToken.getValue(), nameToken, typeToken));
	};

	public String addUndefinedVaraible(Token nameToken) {
		String type = "Undefined";
		variables.add(new VariablesData(nameToken.getValue(), type, nameToken));
		return type;
	}

	public String getVaraibleType(Token variableToken) {
		for (VariablesData variablesData : variables) {
			if (variablesData.getNameToken().getValue().equals(variableToken.getValue())) {
				return variablesData.getType();
			}
		}
		return "";
	}
}
