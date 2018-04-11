package com.project.compiler.Visitor;

import com.project.compiler.Ast.*;

/**
 * Visitor to construct a string that represents the subexpression of the
 * subtree for which the current node is the head.
 * 
 * This applies only to nodes that are part of expressions, i.e. IdNode,
 * AddOpNode, MultOpNode, and AssignStatNode.
 * 
 * Note that this is just as an example. Such functionality is not required in
 * the project.
 * 
 * However, note that this is essentially how the code generation phase will
 * eventually proceed.
 * 
 */

public class ConstructAssignmentAndExpressionStatements extends VisitorBase {

	public void visit(IdNode node) {
		System.out.println("ConstructAssignmentAndExpressionStringVisitor Visiting IdNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		node.setSubtreeString(node.getData());
	}

	public void visit(NumNode node) {
		System.out.println("ConstructAssignmentAndExpressionStringVisitor Visiting NumNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		node.setSubtreeString(node.getData());
	}

	public void visit(AddOpNode node) {
		System.out.println("ConstructAssignmentAndExpressionStringVisitor Visiting AddOpNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		StringBuilder sb = new StringBuilder();

		if (node.getChildren().size() > 0) {
			sb.append(node.getChildren().get(0).getSubtreeString());
		}
		if (node.getData() != null) {
			sb.append(node.getData());
		}
		if (node.getChildren().size() > 1) {
			sb.append(node.getChildren().get(1).getSubtreeString());
		}
		node.setSubtreeString(sb.toString());
	}

	public void visit(MultOpNode node) {
		System.out.println("ConstructAssignmentAndExpressionStringVisitor Visiting MultOpNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		StringBuilder sb = new StringBuilder();

		if (node.getChildren().size() > 0) {
			sb.append(node.getChildren().get(0).getSubtreeString());
		}
		if (node.getData() != null) {
			sb.append(node.getData());
		}
		if (node.getChildren().size() > 1) {
			sb.append(node.getChildren().get(1).getSubtreeString());
		}
		node.setSubtreeString(sb.toString());
	}

	public void visit(AssignStatNode node) {
		System.out.println("ConstructAssignmentAndExpressionStringVisitor Visiting AssignStatNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		StringBuilder sb = new StringBuilder();

		if (node.getChildren().size() > 0) {
			sb.append(node.getChildren().get(0).getSubtreeString());
		}
		if (node.getData() != null) {
			sb.append(node.getData());
		}
		if (node.getChildren().size() > 1) {
			sb.append(node.getChildren().get(1).getSubtreeString());
		}
		node.setSubtreeString(sb.toString());
	}

	public void visit(DataMemberNode node) {
		System.out.println("ConstructAssignmentAndExpressionStringVisitor Visiting DataMemberNode");
		for (AstNode child : node.getChildren())
			child.accept(this);

		//node.setSubtreeString(node.getChildren().get(0).getSubtreeString() + node.getChildren().get(1).getSubtreeString());
		
		StringBuilder sb = new StringBuilder();

		if (node.getChildren().size() > 0) {
			sb.append(node.getChildren().get(0).getSubtreeString());
		}
		if (node.getChildren().size() > 1) {
			sb.append(node.getChildren().get(1).getSubtreeString());
		}
		node.setSubtreeString(sb.toString());
	};

	public void visit(IndexListNode node) {
		System.out.println("ConstructAssignmentAndExpressionStringVisitor Visiting IndexListNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		StringBuilder sb = new StringBuilder();
		for (AstNode child : node.getChildren()) {
			sb.append(String.format("[%s]", child.getData()));
		}
		node.setSubtreeString(sb.toString());
	}

	public void visit(DimListNode node) {
		System.out.println("ConstructAssignmentAndExpressionStringVisitor Visiting DimListNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		StringBuilder sb = new StringBuilder();
		for (AstNode child : node.getChildren()) {
			sb.append(String.format("[%s]", child.getData()));
		}
		node.setSubtreeString(sb.toString());
	}

	public void visit(VarNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);

		StringBuilder sb = new StringBuilder();
		for (AstNode child : node.getChildren()) {
			sb.append(child.getData());
		}
		//node.setSubtreeString(node.getChildren().get(0).getSubtreeString());
		node.setSubtreeString(sb.toString());
	};

	// Below are the visit methods for node types for which this visitor does
	// not apply. They still have to propagate acceptance of the visitor to
	// their children.
	public void visit(ClassListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ClassNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	// public void visit(DimListNode node) {
	// for (AstNode child : node.getChildren())
	// child.accept(this);
	// };

	public void visit(FuncDefListNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(FuncDefNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(AstNode node) {
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
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

}
