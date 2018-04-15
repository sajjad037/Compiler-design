package com.project.compiler.CodeGeneration;

import java.util.ArrayList;

import com.project.compiler.Ast.AstNode;
import com.project.compiler.Semantic.Errors;
import com.project.compiler.SymbolTable.SymTabCreationVisitor;
import com.project.compiler.Visitor.ASTPrinterVisitor;
import com.project.compiler.Visitor.ReconstructSourceProgramVisitor;
import com.project.compiler.Visitor.TypeChecking;

public class MoonCodeGenerator {

	private ArrayList<Errors> errorList = new ArrayList<Errors>();
	private StringBuilder errorsLog = new StringBuilder();
	private String astAfterTCandCSPVisitor = new String();
	private String symbolTableStr = new String();
	private String computeMemSizeStr = new String();
	private String tagBaseCode = new String();
	private String stackBaseCode = new String();

	public void starVisiting(AstNode prog) {
		
		ASTPrinterVisitor astPrinterVisitor = new ASTPrinterVisitor();
		ReconstructSourceProgramVisitor reconstructSourceProgramVisitor = new ReconstructSourceProgramVisitor();
		TypeChecking typeCheckingVisitor = new TypeChecking();
		SymTabCreationVisitor symTabCreationVisitor = new SymTabCreationVisitor();
		ComputeMemSizeVisitor computeMemSizeVisitor = new ComputeMemSizeVisitor();
		TagsBasedCodeGenerationVisitor tagsBasedCodeGenerationVisitor = new TagsBasedCodeGenerationVisitor();
		StackBasedCodeGenerationVisitor stackBasedCodeGenerationVisitor = new StackBasedCodeGenerationVisitor();

		prog.accept(reconstructSourceProgramVisitor);
		prog.accept(typeCheckingVisitor);
		prog.accept(astPrinterVisitor);
		setAstAfterTCandCSPVisitor(astPrinterVisitor.getOutPutString());

		prog.accept(symTabCreationVisitor);
		setSymbolTableStr(symTabCreationVisitor.getOutPutString());

		prog.accept(computeMemSizeVisitor);
		setComputeMemSizeStr(computeMemSizeVisitor.getOutPutString());

		prog.accept(tagsBasedCodeGenerationVisitor);
		setTagBaseCode(tagsBasedCodeGenerationVisitor.getOutPutString());

		prog.accept(stackBasedCodeGenerationVisitor);
		setStackBaseCode(stackBasedCodeGenerationVisitor.getOutPutString());
	}

	/**
	 * @return the astAfterTCandCSPVisitor
	 */
	public String getAstAfterTCandCSPVisitor() {
		return astAfterTCandCSPVisitor;
	}

	/**
	 * @param astAfterTCandCSPVisitor
	 *            the astAfterTCandCSPVisitor to set
	 */
	private void setAstAfterTCandCSPVisitor(String astAfterTCandCSPVisitor) {
		this.astAfterTCandCSPVisitor = astAfterTCandCSPVisitor;
	}

	/**
	 * @return the symbolTableStr
	 */
	public String getSymbolTableStr() {
		return symbolTableStr;
	}

	/**
	 * @param symbolTableStr
	 *            the symbolTableStr to set
	 */
	private void setSymbolTableStr(String symbolTableStr) {
		this.symbolTableStr = symbolTableStr;
	}

	/**
	 * @return the computeMemSizeStr
	 */
	public String getComputeMemSizeStr() {
		return computeMemSizeStr;
	}

	/**
	 * @param computeMemSizeStr
	 *            the computeMemSizeStr to set
	 */
	private void setComputeMemSizeStr(String computeMemSizeStr) {
		this.computeMemSizeStr = computeMemSizeStr;
	}

	/**
	 * @return the tagBaseCode
	 */
	public String getTagBaseCode() {
		return tagBaseCode;
	}

	/**
	 * @param tagBaseCode
	 *            the tagBaseCode to set
	 */
	private void setTagBaseCode(String tagBaseCode) {
		this.tagBaseCode = tagBaseCode;
	}

	/**
	 * @return the stackBaseCode
	 */
	public String getStackBaseCode() {
		return stackBaseCode;
	}

	/**
	 * @param stackBaseCode
	 *            the stackBaseCode to set
	 */
	private void setStackBaseCode(String stackBaseCode) {
		this.stackBaseCode = stackBaseCode;
	}

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
}
