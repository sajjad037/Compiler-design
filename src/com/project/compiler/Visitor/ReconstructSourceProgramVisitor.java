package com.project.compiler.Visitor;

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

/**
 * Visitor to construct a string that represents the subexpression of the
 * subtree for which the current node is the head.
 * 
 * This applies only to nodes that are part of expressions, i.e. IdNode,
 * AddOpNode, MultOpNode, and AssignStatp_node.
 * 
 * Note that this is just as an example. Such functionality is not required in
 * the project.
 * 
 * However, note that this is essentially how the code generation phase will
 * eventually proceed.
 * 
 */

public class ReconstructSourceProgramVisitor extends VisitorBase {

	// public String m_outputfilename = new String();
	public String outputstring = new String();

	public ReconstructSourceProgramVisitor() {
	}

	public void visit(ProgNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		for (AstNode child : p_node.getChildren()) {
			p_node.subtreeString += child.subtreeString;
		}

		outputstring = p_node.subtreeString;
		// if (!this.m_outputfilename.isEmpty()) {
		// File file = new File(this.m_outputfilename);
		// try (PrintWriter out = new PrintWriter(file)) {
		// out.println(p_node.subtreeString);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
	};

	public void visit(ProgramBlockNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		p_node.subtreeString += "program{\n";
		for (AstNode child : p_node.getChildren()) {
			p_node.subtreeString += "  " + child.subtreeString + "\n";
		}
		p_node.subtreeString += "}\n";
	};

	public void visit(StatBlockNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		p_node.subtreeString += "{\n";
		for (AstNode child : p_node.getChildren()) {
			p_node.subtreeString += "  " + child.subtreeString + "\n";
		}
		p_node.subtreeString += "}\n";

	};

	public void visit(ClassNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		p_node.subtreeString += "class ";
		p_node.subtreeString += p_node.getChildren().get(0).getData() + "{\n";
		for (int childindex = 1; childindex < p_node.getChildren().size(); childindex++) {
			AstNode child = p_node.getChildren().get(childindex);
			p_node.subtreeString += "  " + child.subtreeString + "\n";
		}
		p_node.subtreeString += "}";

	};

	// public void visit(FuncCallNode p_node) {
	// // propagate accepting the same visitor to all the children
	// // this effectively achieves Depth-First AST Traversal
	// for (AstNode child : p_node.getChildren() )
	// child.accept(this);
	// p_node.subtreeString += p_node.getChildren().get(0).subtreeString;
	// p_node.subtreeString += p_node.getChildren().get(1).subtreeString;
	// };

	public void visit(PutStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		p_node.subtreeString += "put(";
		for (AstNode child : p_node.getChildren())
			p_node.subtreeString += child.subtreeString;
		p_node.subtreeString += ");";
	};

	public void visit(ReturnStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		p_node.subtreeString += "return(";
		for (AstNode child : p_node.getChildren())
			p_node.subtreeString += child.subtreeString;
		p_node.subtreeString += ");";
	};

	public void visit(FuncDefNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		for (AstNode child : p_node.getChildren())
			p_node.subtreeString += child.subtreeString;
	};

	public void visit(ParamListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		p_node.subtreeString += "(";
		boolean first = true;
		for (AstNode child : p_node.getChildren()) {
			if (first)
				p_node.subtreeString += child.subtreeString;
			else
				p_node.subtreeString += " " + child.subtreeString;
			first = false;
		}
		p_node.subtreeString += ")";
	};

	public void visit(TypeNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.accept(this);
		}
		p_node.setSubtreeString(p_node.getData() + " ");
	};

	public void visit(VarDeclNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		for (AstNode child : p_node.getChildren())
			p_node.subtreeString += child.subtreeString;
		p_node.subtreeString += ";";

	};

	public void visit(DimListNode node) {
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

	public void visit(IdNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		p_node.setSubtreeString(p_node.getData());
	}

	public void visit(NumNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		p_node.setSubtreeString(p_node.getData());
	}

	public void visit(AddOpNode node) {
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

	// Below are the visit methods for node types for which this visitor does
	// not apply. They still have to propagate acceptance of the visitor to
	// their children.

	public void visit(FuncDefListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		for (AstNode child : p_node.getChildren())
			p_node.subtreeString += child.subtreeString;
	};

	public void visit(ClassListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(AstNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	// public void visit(DimNode p_node) {
	// // propagate accepting the same visitor to all the children
	// // this effectively achieves Depth-First AST Traversal
	// for (AstNode child : p_node.getChildren())
	// child.accept(this);
	// p_node.subtreeString = p_node.getData();
	// };

	public void visit(AParamNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(ArithExprNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(DataMemberNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);

		// node.setSubtreeString(node.getChildren().get(0).getSubtreeString() +
		// node.getChildren().get(1).getSubtreeString());

		StringBuilder sb = new StringBuilder();

		if (node.getChildren().size() > 0) {
			sb.append(node.getChildren().get(0).getSubtreeString());
		}
		if (node.getChildren().size() > 1) {
			sb.append(node.getChildren().get(1).getSubtreeString());
		}
		node.setSubtreeString(sb.toString());
	};

	public void visit(ExprNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(FactorNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(FCallNode p_node) {

		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		p_node.subtreeString += p_node.getChildren().get(0).subtreeString;
		p_node.subtreeString += p_node.getChildren().get(1).subtreeString;
	};

	public void visit(ForStatNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(FuncDeclListNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(FuncDeclNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(FuncHeadNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(GetStatNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(IfStatNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(IndexListNode node) {
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

	public void visit(InherList p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(NotNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(RelExprNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(SignNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(TermNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(VarDeclListNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(VarElementNode p_node) {
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(VarNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	
		StringBuilder sb = new StringBuilder();
		for (AstNode child : node.getChildren()) {
			sb.append(child.getData());
		}
		// node.setSubtreeString(node.getChildren().get(0).getSubtreeString());
		node.setSubtreeString(sb.toString());
	};

	public String getOutPutString() {
		return this.outputstring;
	}
}
