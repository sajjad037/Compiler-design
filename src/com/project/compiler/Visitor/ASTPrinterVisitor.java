package com.project.compiler.Visitor;

import java.io.File;
import java.io.PrintWriter;

import com.project.compiler.Ast.*;

public class ASTPrinterVisitor extends VisitorBase {

	
	public String outputstring = new String();

	public ASTPrinterVisitor() {
	}


	public void printLine(AstNode p_node) {
		for (int i = 0; i < AstNode.nodelevel; i++)
			outputstring += "  ";

		String toprint = String.format("%-25s", p_node.getClass().getName().replace("com.project.compiler.Ast", "AST"));
		for (int i = 0; i < AstNode.nodelevel; i++)
			toprint = toprint.substring(0, toprint.length() - 2);
		toprint += String.format("%-23s",
				(p_node.getData() == null || p_node.getData().isEmpty()) ? " | " : " | " + p_node.getData());
		toprint += String.format("%-15s",
				(p_node.getType() == null || p_node.getType().isEmpty()) ? " | " : " | " + p_node.getType());
		//toprint += (String.format("%-16s", (p_node.subtreeString == null || p_node.subtreeString.isEmpty()) ? " | "
		//		: " | " + (p_node.subtreeString.replaceAll("\\n+", ""))));
		toprint += (String.format("%s", (p_node.subtreeString == null || p_node.subtreeString.isEmpty()) ? " | "
				: " | " + (p_node.subtreeString.replaceAll("\\n+", ""))));

		outputstring += toprint + "\n";

		AstNode.nodelevel++;
		// List<AstNode> children = p_node.getChildren();
		// for (int i = 0; i < children.size(); i++ ){
		// children.get(i).printSubtree();
		// }
		AstNode.nodelevel--;
	}

	public void visit(ProgNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		outputstring += "=================================================================================================\r\n";
		outputstring += String.format("%-25s | %-20s | %-12s | %s \r\n", "Node type", "data", "type", "subtreestring");
		outputstring += "=================================================================================================\r\n";
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
		outputstring += "=================================================================================================\r\n";
//		if (!this.outputfilename.isEmpty()) {
//			File file = new File(this.outputfilename);
//			try (PrintWriter out = new PrintWriter(file)) {
//				out.println(this.outputstring);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	};

	public void visit(ProgramBlockNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(StatBlockNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(ClassNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(PutStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(FuncDefNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(ParamListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(TypeNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(VarDeclNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(DimListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(IdNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	}

	public void visit(NumNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	}

	public void visit(AddOpNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	}

	public void visit(MultOpNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	}

	public void visit(AssignStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	}

	// Below are the visit methods for node types for which this visitor does
	// not apply. They still have to propagate acceptance of the visitor to
	// their children.

	public void visit(FuncDefListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(ClassListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(AstNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(ReturnStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(AParamNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(ArithExprNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(DataMemberNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(ExprNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(FactorNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(FCallNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(ForStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(FuncDeclListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(FuncDeclNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(FuncHeadNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(GetStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(IfStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(IndexListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(InherList p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(NotNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(RelExprNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(SignNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(TermNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(VarDeclListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(VarElementNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};

	public void visit(VarNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		this.printLine(p_node);
		AstNode.nodelevel++;
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		AstNode.nodelevel--;
	};
	
	public String getOutPutString()
	{
		return this.outputstring;
	}
}
