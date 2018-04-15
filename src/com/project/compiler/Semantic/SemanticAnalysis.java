package com.project.compiler.Semantic;

import java.util.ArrayList;

import com.project.compiler.Ast.AstNode;
import com.project.compiler.SymbolTable.SymTabCreationVisitor;
import com.project.compiler.Visitor.ConstructAssignmentAndExpressionStatements;
import com.project.compiler.Visitor.TypeChecking;




public class SemanticAnalysis {
	
	private ArrayList<Errors> errorList = new ArrayList<Errors>();
	private StringBuilder errorsLog = new StringBuilder();
	private String treeAfterCSVisitor = new String();
	private String treeAfterTCVisitor = new String();
	private String symbolTableStr = new String();
	
	public void starVisiting(AstNode prog)
	{
		System.out.println("==VISITING TREE WITH STRING CONSTRUCTION VISITOR======");
		ConstructAssignmentAndExpressionStatements CSVisitor = new ConstructAssignmentAndExpressionStatements(); 
		prog.accept(CSVisitor);
		setTreeAfterCSVisitor(prog.toString());
//		//System.out.println(getTreeAfterCSVisitor());
		System.out.println("==TREE VISITED WITH STRING CONSTRUCTION VISITOR=======");
		
		
		System.out.println("==VISITING TREE WITH TYPE CHECKING VISITOR======");
		TypeChecking TCVisitor = new TypeChecking(); 
		prog.accept(TCVisitor);
		setTreeAfterTCVisitor(prog.toString());
		//System.out.println(getTreeAfterTCVisitor());
		System.out.println("==TREE VISITED WITH TYPE CHECKING VISITOR=======");
		
		System.out.println("==VISITING TREE WITH SYMBOL TABLE VISITOR======");
		SymTabCreationVisitor STCVisitor = new SymTabCreationVisitor(); 
		//prog.accept(STCVisitor);
		//setSymbolTableStr(prog.symtab.toString());
		System.out.println("==TREE VISITED WITH SYMBOL TABLE VISITOR=======");
	}

	/**
	 * Print Error or Warning
	 * @param tag
	 * @param msg
	 */
//	private void errorOrWarning(String tag, String msg, int lineNumber){
//		errorsLog.append(String.format("[%s] %s: %s \r\n", ModuleType.SEMANTIC.toString(), tag, msg));
//		errorList.add(new Errors(lineNumber, msg, tag, ModuleType.SYNTAX));
//	}
	
	/**
	 * Get Errors Log
	 * @return
	 */
	public String getErrorsLog()
	{
		return errorsLog.toString();
	}

	/**
	 * @return the errorList
	 */
	public ArrayList<Errors> getErrorList() {
		return errorList;
	}

	/**
	 * @return the treeAfterCSVisitor
	 */
	public String getTreeAfterCSVisitor() {
		return treeAfterCSVisitor;
	}

	/**
	 * @param treeAfterCSVisitor the treeAfterCSVisitor to set
	 */
	private void setTreeAfterCSVisitor(String treeAfterCSVisitor) {
		this.treeAfterCSVisitor = treeAfterCSVisitor;
	}

	/**
	 * @return the treeAfterTCVisitor
	 */
	public String getTreeAfterTCVisitor() {
		return treeAfterTCVisitor;
	}

	/**
	 * @param treeAfterTCVisitor the treeAfterTCVisitor to set
	 */
	private void setTreeAfterTCVisitor(String treeAfterTCVisitor) {
		this.treeAfterTCVisitor = treeAfterTCVisitor;
	}

	/**
	 * @return the symbolTableStr
	 */
	public String getSymbolTableStr() {
		return symbolTableStr;
	}

	/**
	 * @param symbolTableStr the symbolTableStr to set
	 */
	private void setSymbolTableStr(String symbolTableStr) {
		this.symbolTableStr = symbolTableStr;
	}
}
