package com.project.compiler.Visitor;

import java.util.ArrayList;

import com.project.compiler.Ast.AParamNode;
import com.project.compiler.Ast.AddOpNode;
import com.project.compiler.Ast.ArithExprNode;
import com.project.compiler.Ast.AssignStatNode;
import com.project.compiler.Ast.AstNode;
import com.project.compiler.Ast.ClassListNode;
import com.project.compiler.Ast.ClassNode;
import com.project.compiler.Ast.DataMemberNode;
import com.project.compiler.Ast.DimListNode;
import com.project.compiler.Ast.ExprNode;
import com.project.compiler.Ast.FCallNode;
import com.project.compiler.Ast.FactorNode;
import com.project.compiler.Ast.ForStatNode;
import com.project.compiler.Ast.FuncDeclListNode;
import com.project.compiler.Ast.FuncDeclNode;
import com.project.compiler.Ast.FuncDefListNode;
import com.project.compiler.Ast.FuncDefNode;
import com.project.compiler.Ast.FuncHeadNode;
import com.project.compiler.Ast.GetStatNode;
import com.project.compiler.Ast.IdNode;
import com.project.compiler.Ast.IfStatNode;
import com.project.compiler.Ast.IndexListNode;
import com.project.compiler.Ast.InherList;
import com.project.compiler.Ast.MultOpNode;
import com.project.compiler.Ast.NotNode;
import com.project.compiler.Ast.NumNode;
import com.project.compiler.Ast.ParamListNode;
import com.project.compiler.Ast.ProgNode;
import com.project.compiler.Ast.ProgramBlockNode;
import com.project.compiler.Ast.PutStatNode;
import com.project.compiler.Ast.RelExprNode;
import com.project.compiler.Ast.ReturnStatNode;
import com.project.compiler.Ast.SignNode;
import com.project.compiler.Ast.StatBlockNode;
import com.project.compiler.Ast.TermNode;
import com.project.compiler.Ast.TypeNode;
import com.project.compiler.Ast.VarDeclListNode;
import com.project.compiler.Ast.VarDeclNode;
import com.project.compiler.Ast.VarElementNode;
import com.project.compiler.Ast.VarNode;
import com.project.compiler.Config.LexicalStatic;
import com.project.compiler.Config.Enums.ModuleType;
import com.project.compiler.Model.Token;
import com.project.compiler.Model.VariablesData;
import com.project.compiler.Semantic.Errors;

/**
 * Visitor to compute the type of subexpressions and assignment statements.
 * 
 * This applies only to nodes that are part of expressions and assignment
 * statements i.e. AddOpNode, MultOpNode, and AssignStatNode.
 * 
 */

public class TypeCheckingVisitor extends VisitorBase {

	ArrayList<VariablesData> variables = new ArrayList<VariablesData>();
	public String errors = new String();
	private ArrayList<Errors> errorList = new ArrayList<Errors>();
	private String ERROR ="ERROR";
	
	private void errorOrWarning(String tag, String msg, int lineNumber){
		this.errors+=String.format("[%s] %s: %s \r\n", ModuleType.SEMANTIC.toString(), tag, msg);
		errorList.add(new Errors(lineNumber, msg, tag, ModuleType.SEMANTIC));
	}
	
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
		
			String msg = "AddOpNode type error:  " 
					+ node.getChildren().get(0).getData()
					+ "(" + node.getChildren().get(0).getType() + ")"
					+  " and "
					+ node.getChildren().get(1).getData()
					+ "(" + node.getChildren().get(1).getType() + ")"
					+ "\r\n";
			errorOrWarning(ERROR, msg, node.getDataToken() == null ? node.getChildren().get(0).getDataToken().getLine() : node.getDataToken().getLine());
			
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
			String msg = "MultOpNode type error: " 
					+ node.getChildren().get(0).getData()
					+ "(" + node.getChildren().get(0).getType() + ")"
					+  " and "
					+ node.getChildren().get(1).getData()
					+ "(" + node.getChildren().get(1).getType() + ")"
					+ "\r\n";
			
			errorOrWarning(ERROR, msg, node.getDataToken() == null ? node.getChildren().get(0).getDataToken().getLine() : node.getDataToken().getLine());
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
			String msg = "AssignStatNode type error: " 
					+ node.getChildren().get(0).getData()
					+ "(" + node.getChildren().get(0).getType() + ")"
					+  " and "
					+ node.getChildren().get(1).getData()
					+ "(" + node.getChildren().get(1).getType() + ")"
					+ "\n";
			
			errorOrWarning(ERROR, msg, node.getDataToken() == null ? node.getChildren().get(0).getDataToken().getLine() : node.getDataToken().getLine());
		}
	}

	public void visit(VarNode node) {
		System.out.println("TypeCheckingVisitor Visiting VarNode");
		for (AstNode child : node.getChildren())
			child.accept(this);

		if (node.getChildren().get(0) instanceof IdNode) {
			String str = getVaraibleType(node.getChildren().get(0).getDataToken());
			if (!str.equals("")) {
				node.getChildren().get(0).setType(str);
				node.setType(str);
			} else {
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
			if (!str.equals("")) {
				node.getChildren().get(0).setType(str);
				node.setType(str);
			} else {
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

		// Variable List to node
		node.setVariables(variables);

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
		node.setVariables(variables);

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
		
		if(node.getDataToken().getDescription().equals(LexicalStatic.T_N_INTEGER))
		{
			node.setType("int");
		} 
		else if(node.getDataToken().getDescription().equals(LexicalStatic.T_N_FLOAT))
		{
			node.setType("float");	
		}
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

		// Variable List to node
		node.setVariables(variables);

		// Check for duplicate declaration

		// Check for undefined
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

		if(nameToken != null && typeToken != null)
		{
			variables.add(new VariablesData(nameToken.getValue(), typeToken.getValue(), nameToken, typeToken));
		}
	};

	public void visit(AParamNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ArithExprNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ExprNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(FactorNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
		
		if (node.getChildren().get(0) instanceof NumNode) {
			String str = node.getChildren().get(0).getType();
			node.setType(str);
		}
	};

	public void visit(FCallNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ForStatNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(FuncDeclListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(FuncDeclNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(FuncHeadNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(GetStatNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(IfStatNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(IndexListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(InherList node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(NotNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ParamListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(RelExprNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ReturnStatNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(SignNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(TermNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
		
		if (node.getChildren().get(0) instanceof NumNode) {
			String str = node.getChildren().get(0).getType();
			node.setType(str);
		}
	};

	public void visit(VarDeclListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(VarElementNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
		
		if (node.getChildren().get(0) instanceof DataMemberNode) {
			node.setType(node.getChildren().get(0).getType());
		}
	};

	private String addUndefinedVaraible(Token nameToken) {
		String type = "Undefined";
		variables.add(new VariablesData(nameToken.getValue(), type, nameToken));
		return type;
	}

	private String getVaraibleType(Token variableToken) {
		for (VariablesData variablesData : variables) {
			if (variablesData.getNameToken().getValue().equals(variableToken.getValue())) {
				return variablesData.getType();
			}
		}
		return "";
	}
	
	public String getErrors() {
		return this.errors;
	}
	

	/**
	 * @return the errorList
	 */
	public ArrayList<Errors> getErrorList() {
		return errorList;
	}
}
