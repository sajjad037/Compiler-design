package com.project.compiler.SymbolTable;

import java.util.Vector;

import com.project.compiler.Ast.*;
import com.project.compiler.Visitor.VisitorBase;

/**
 * Visitor to create symbol tables and their entries.
 * 
 * This concerns only nodes that either:
 * 
 * (1) represent identifier declarations/definitions, in which case they need to
 * assemble a symbol table record to be inserted in a symbol table. These are:
 * VarDeclNode, ClassNode, and FuncDefp_node.
 * 
 * (2) represent a scope, in which case they need to create a new symbol table,
 * and then insert the symbol table entries they get from their children. These
 * are: ProgNode, ClassNode, FuncDefNode, and StatBlockp_node.
 */

public class SymTabCreationVisitor extends VisitorBase {

	public Integer m_tempVarNum = 0;
	// public String m_outputfilename = new String();
	public String outputstring = new String();

	public SymTabCreationVisitor() {
	}

	public String getNewTempVarName() {
		m_tempVarNum++;
		return "t" + m_tempVarNum.toString();
	}

	public void visit(ProgNode p_node) {
		p_node.symtab = new SymTab(0, "global", null);
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			// make all children use this scopes' symbol table
			child.symtab = p_node.symtab;
			child.accept(this);
		}
		
		outputstring = p_node.symtab.toString();
		
//		if (!this.m_outputfilename.isEmpty()) {
//			File file = new File(this.m_outputfilename);
//			try (PrintWriter out = new PrintWriter(file)) {
//				out.println(p_node.symtab);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	};

	public void visit(StatBlockNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(ProgramBlockNode p_node) {
		SymTab localtable = new SymTab(1, "program", p_node.symtab);
		p_node.symtabentry = new FuncEntry("void", "program", new Vector<VarEntry>(), localtable);
		p_node.symtab.addEntry(p_node.symtabentry);
		p_node.symtab = localtable;
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(ClassNode p_node) {
		String classname = p_node.getChildren().get(0).getData();
		SymTab localtable = new SymTab(1, classname, p_node.symtab);
		p_node.symtabentry = new ClassEntry(classname, localtable);
		p_node.symtab.addEntry(p_node.symtabentry);
		p_node.symtab = localtable;
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(FuncDefNode p_node) {
		String ftype = p_node.getChildren().get(0).getData();
		String fname = p_node.getChildren().get(1).getData();
		SymTab localtable = new SymTab(1, fname, p_node.symtab);
		Vector<VarEntry> paramlist = new Vector<VarEntry>();
		for (AstNode param : p_node.getChildren().get(2).getChildren()) {
			// parameter dimension list
			Vector<Integer> dimlist = new Vector<Integer>();
			for (AstNode dim : param.getChildren().get(2).getChildren()) {
				// parameter dimension
				Integer dimval = Integer.parseInt(dim.getData());
				dimlist.add(dimval);
			}
			paramlist.add((VarEntry) p_node.symtabentry);
		}
		p_node.symtabentry = new FuncEntry(ftype, fname, paramlist, localtable);
		p_node.symtab.addEntry(p_node.symtabentry);
		p_node.symtab = localtable;
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(VarDeclNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
		// Then, do the processing of this nodes' visitor
		// aggregate information from the subtree
		// get the type from the first child node and aggregate here
		String vartype = p_node.getChildren().get(0).getData();
		// get the id from the second child node and aggregate here
		String varid = p_node.getChildren().get(1).getData();
		// loop over the list of dimension nodes and aggregate here
		Vector<Integer> dimlist = new Vector<Integer>();
		for (AstNode dim : p_node.getChildren().get(2).getChildren()) {
			// parameter dimension
			Integer dimval = Integer.parseInt(dim.getData());
			dimlist.add(dimval);
		}
		// create the symbol table entry for this variable
		// it will be picked-up by another node above later
		p_node.symtabentry = new VarEntry("var", vartype, varid, dimlist);
		p_node.symtab.addEntry(p_node.symtabentry);
	}

	public void visit(AddOpNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
		String tempvarname = this.getNewTempVarName();
		p_node.moonVarName = tempvarname;
		p_node.symtabentry = new VarEntry("tempvar", p_node.getType(), p_node.moonVarName,
				p_node.symtab.lookupName(p_node.getChildren().get(0).moonVarName).m_dims);
		p_node.symtab.addEntry(p_node.symtabentry);
	};

	public void visit(MultOpNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
		String tempvarname = this.getNewTempVarName();
		p_node.moonVarName = tempvarname;
		String vartype = p_node.getType();
		Vector<Integer> dimlist = new Vector<Integer>();
		for (AstNode dim : p_node.getChildren().get(1).getChildren()) {
			// parameter dimension
			if ((dim instanceof NumNode)) {
				Integer dimval = Integer.parseInt(dim.getData());
				dimlist.add(dimval);
			}			
//			else if ((dim instanceof IdNode && dim.getType()!= null && (dim.getType().equals("int") || dim.getType().equals("float")))) {
//				Integer dimval = Integer.parseInt(dim.getData());
//				dimlist.add(dimval);
//			}
			else if(dim.getChildren().size() > 0)
			{
				if ((dim.getChildren().get(0) instanceof NumNode)) {
					Integer dimval = Integer.parseInt(dim.getChildren().get(0).getData());
					dimlist.add(dimval);
				}
//				else if ((dim.getChildren().get(0) instanceof IdNode)) {
//					AstNode node = dim.getChildren().get(0);
//					if(node.getType()!= null && (node.getType().equals("int") || node.getType().equals("float")))
//					{
//						Integer dimval = Integer.parseInt(node.getData());
//						dimlist.add(dimval);
//					}
//					
//				}
			}
			
			
		}
		p_node.symtabentry = new VarEntry("tempvar", vartype, p_node.moonVarName, dimlist);
		p_node.symtab.addEntry(p_node.symtabentry);
	};

	public void visit(NumNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
		String tempvarname = this.getNewTempVarName();
		p_node.moonVarName = tempvarname;
		String vartype = p_node.getType();
		p_node.symtabentry = new VarEntry("litval", vartype, p_node.moonVarName, new Vector<Integer>());
		p_node.symtab.addEntry(p_node.symtabentry);
	};

	// Below are the visit methods for node types for which this visitor does
	// not apply. They still have to propagate acceptance of the visitor to
	// their children.

	public void visit(AssignStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(ClassListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(DimListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(FuncDefListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(IdNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
		p_node.moonVarName = p_node.data;
	};

	public void visit(AstNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(PutStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(TypeNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	};

	public void visit(ParamListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren()) {
			child.symtab = p_node.symtab;
			child.accept(this);
		}
	}

	// public void visit(DimNode p_node) {
	// // propagate accepting the same visitor to all the children
	// // this effectively achieves Depth-First AST Traversal
	// for (AstNode child : p_node.getChildren()) {
	// child.symtab = p_node.symtab;
	// child.accept(this);
	// }
	// };

	// public void visit(FuncCallNode p_node) {
	public void visit(FCallNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		String tempvarname = this.getNewTempVarName();
		p_node.moonVarName = tempvarname;
		String vartype = p_node.getType();
		p_node.symtabentry = new VarEntry("retval", vartype, p_node.moonVarName, new Vector<Integer>());
		p_node.symtab.addEntry(p_node.symtabentry);
	};

	public void visit(ReturnStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public String getOutPutString() {
		return this.outputstring;
	}
}
