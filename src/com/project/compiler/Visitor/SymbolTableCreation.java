package com.project.compiler.Visitor;

import com.project.compiler.Ast.*;
import com.project.compiler.Semantic.SymTab;
import com.project.compiler.Semantic.SymTabEntry;

/**
 * Visitor to create symbol tables and their entries.  
 * 
 * This concerns only nodes that either:  
 * 
 * (1) represent identifier declarations/definitions, in which case they need to assemble 
 * a symbol table record to be inserted in a symbol table. These are:  VarDeclNode, ClassNode,  
 * and FuncDefNode. 
 * 
 * (2) represent a scope, in which case they need to create a new symbol table, and then 
 * insert the symbol table entries they get from their children. These are:  ProgNode, ClassNode, 
 * FuncDefNode, and StatBlockNode.   
 */

public class SymbolTableCreation extends VisitorBase {

	public void visit(ProgNode node){
		System.out.println("SymTabCreationVisitor visiting ProgNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		node.symtab = new SymTab("global");
		// for classes, loop over all class declaration nodes
		for (AstNode classelt : node.getChildren().get(0).getChildren())
			//add the symbol table entry of each class in the global symbol table
			node.symtab.addEntry(classelt.symtabentry);
		// for function definitions, loop over all function definition nodes
		for (AstNode fndefelt : node.getChildren().get(1).getChildren())
			//add the symbol table entry of each function definition in the global symbol table
			node.symtab.addEntry(fndefelt.symtabentry);
		// for the program function, get its local symbol table from node 2 and create an entry for it in the global symbol table
		// first, get the table and change its name
		SymTab table = node.getChildren().get(2).symtab; 
		table.m_name = "program"; 
		node.symtab.addEntry("function:program", table);
	};

	public void visit(StatBlockNode node){
		System.out.println("SymTabCreationVisitor visiting StatBlockNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		node.symtab = new SymTab();
		// add the symbol table entries of all the variables declared in the statement block
		for (AstNode stat : node.getChildren()){
			if (stat.symtabentry != null) 
				node.symtab.addEntry(stat.symtabentry);
		}		
	};
	
	public void visit(ProgramBlockNode node){
		System.out.println("SymTabCreationVisitor visiting ProgramBlockNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		node.symtab = new SymTab();
		// add the symbol table entries of all the variables declared in the statement block
		for (AstNode stat : node.getChildren()){
			if (stat.symtabentry != null) 
				node.symtab.addEntry(stat.symtabentry);
		}		
	};

	public void visit(FuncDefNode node){
		System.out.println("SymTabCreationVisitor visiting FuncDefNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		String fname = node.getChildren().get(1).getData();
		node.symtab = new SymTab(fname);
		String declrecstring; 
		declrecstring = "function:";
		// function return value
		declrecstring += node.getChildren().get(0).getData() + ':';
		// function name
		declrecstring += node.getChildren().get(1).getData() + ':';
		// loop over function parameter list
		for (AstNode param : node.getChildren().get(2).getChildren()){
			// parameter type
			declrecstring += param.getChildren().get(0).getData() + ':';
			// parameter name
			declrecstring += param.getChildren().get(1).getData() + ':';	
			// parameter dimension list
			for (AstNode dim : param.getChildren().get(2).getChildren())
				// parameter dimension
				declrecstring += dim.getData() + ':'; 
		}
		// the symbol table of the function is the symbol table of its statement block 
		// first, get the table and adapt its name to the function
		SymTab table = node.getChildren().get(3).symtab; 
		table.m_name = node.getChildren().get(1).getData(); 
		node.symtabentry = new SymTabEntry(declrecstring, table);
		// add parameters of the function as local variables in the local symbol table
		for (AstNode param : node.getChildren().get(2).getChildren())
		node.symtabentry.m_subtable.addEntry(param.symtabentry);
	};
	
	public void visit(ClassNode node){
		System.out.println("SymTabCreationVisitor visiting ClassNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// get the class name from node 0
		String classname = node.getChildren().get(0).getData();
		// create a new table with the class name
		node.symtab = new SymTab(classname);
		// loop over all children of the class and migrate their symbol table entries in class table
		for (AstNode member : node.getChildren()){
			if (member.symtabentry != null) 
				node.symtab.addEntry(member.symtabentry);
		}
		// create the symbol table entry for the class
		node.symtabentry = new SymTabEntry("class:" + classname, node.symtab);
	};
	
	public void visit(VarDeclNode node){
		System.out.println("SymTabCreationVisitor visiting VarDeclNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// aggregate information from the subtree
		String declrecstring; 
		// identify what kind of record that is
		declrecstring = "localvar:";
		// get the type from the first child node and aggregate here 
		declrecstring += node.getChildren().get(0).getData() + ':';
		// get the id from the second child node and aggregate here
		declrecstring += node.getChildren().get(1).getData() + ':';
		// loop over the list of dimension nodes and aggregate here 
		for (AstNode dim : node.getChildren().get(2).getChildren())
			declrecstring += dim.getData() + ':';
		// create the symbol table entry for this variable
		// it will be picked-up by another node above later
		node.symtabentry = new SymTabEntry(declrecstring, null);
	}
	
	// Below are the visit methods for node types for which this visitor does
	// not apply. They still have to propagate acceptance of the visitor to
	// their children.
	public void visit(AddOpNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(AssignStatNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ClassListNode node) {
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

	public void visit(IdNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(MultOpNode node) {
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

	public void visit(PutStatNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(TypeNode node) {
			for (AstNode child : node.getChildren() )
				child.accept(this);
	 };
}
