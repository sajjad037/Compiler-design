package com.project.compiler.Visitor;
import java.util.Stack;

import com.project.compiler.Ast.*;


/**
 * Visitor to generate moon code for simple expressions and assignment and put 
 * statements in the main program function.  
 * 
 */

public class CodeGenerationVisitor extends VisitorBase {
	
    public Stack<String> registerPool = new Stack<String>();
    public Integer       tempVarNum   = 0;
    public String        moonExecCode = new String(); // moon instructions part
    public String        moonDataCode = new String(); // moon data part
    
    public CodeGenerationVisitor() {
    	// create a pool of registers as a stack of Strings
    	// assuming only r1, ..., r12 are available
    	for (Integer i = 12; i>=1; i--)
    		registerPool.push("r" + i.toString());
    }
    
    public String getNewTempVarName(){
    	tempVarNum++;
    	return "t" + tempVarNum.toString();  
    }

	public void visit(VarDeclNode node){
		System.out.println("CodeGenerationVisitor visiting VarDeclNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		if (node.getChildren().get(0).getData() == "int")
			moonDataCode += "        % space for variable " + node.getChildren().get(1).getData() + "\n";
			moonDataCode += String.format("%-7s" ,node.getChildren().get(1).getData()) + " dw 0\n";
	}

	public void visit(IdNode node){
		System.out.println("CodeGenerationVisitor visiting IdNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		node.moonVarName = node.getData(); 
	}
	
	public void visit(NumNode node){
		System.out.println("CodeGenerationVisitor visiting IdNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// create a local variable and allocate a register to this subcomputation 
		node.localRegister = this.registerPool.pop();
		node.moonVarName   = this.getNewTempVarName();
		//generate code
		moonDataCode += "        % space for constant " + node.getData() + "\n";
		moonDataCode += String.format("%-7s",node.moonVarName) + " dw 0\n";
		moonExecCode += "        % processing: " + node.moonVarName  + " := " + node.getData() + "\n";
		moonExecCode += "        addi " + node.localRegister + ",r0," + node.getData() + "\n"; 
		moonExecCode += "        sw " + node.moonVarName + "(r0)," + node.localRegister + "\n";
		// deallocate the register for the current node
		this.registerPool.push(node.localRegister);
	}
	
	public void visit(AddOpNode node){
		System.out.println("CodeGenerationVisitor visiting AddOpNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// create a local variable and allocate a register to this subcomputation 
		node.localRegister      = this.registerPool.pop();
		node.leftChildRegister  = this.registerPool.pop();
		node.rightChildRegister = this.registerPool.pop();
		node.moonVarName        = this.getNewTempVarName();
		// generate code
		moonExecCode += "        % processing: " + node.moonVarName + " := " + node.getChildren().get(0).moonVarName + " + " + node.getChildren().get(1).moonVarName + "\n";
		moonExecCode += "        lw " + node.leftChildRegister + "," + node.getChildren().get(0).moonVarName + "(r0)\n";
		moonExecCode += "        lw " + node.rightChildRegister + "," + node.getChildren().get(1).moonVarName + "(r0)\n";
		moonExecCode += "        add " + node.localRegister + "," + node.leftChildRegister + "," + node.rightChildRegister + "\n"; 
		moonDataCode += "        % space for " + node.getChildren().get(0).moonVarName + " + " + node.getChildren().get(1).moonVarName + "\n";
		moonDataCode += String.format("%-7s",node.moonVarName) + " dw 0\n";
		moonExecCode += "        sw " + node.moonVarName + "(r0)," + node.localRegister + "\n";
		// deallocate the registers for the two children, and the current node
		this.registerPool.push(node.leftChildRegister);
		this.registerPool.push(node.rightChildRegister);
		this.registerPool.push(node.localRegister);
	}

	public void visit(MultOpNode node){ 
		System.out.println("CodeGenerationVisitor visiting MultOpNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor		
		// create a local variable and allocate a register to this subcomputation 
		node.localRegister      = this.registerPool.pop();
		node.leftChildRegister  = this.registerPool.pop();
		node.rightChildRegister = this.registerPool.pop();
		node.moonVarName        = this.getNewTempVarName();
		// generate code
		moonExecCode += "        % processing: " + node.moonVarName + " := " + node.getChildren().get(0).moonVarName + " * " + node.getChildren().get(1).moonVarName + "\n";
		moonExecCode += "        lw " + node.leftChildRegister + "," + node.getChildren().get(0).moonVarName + "(r0)\n";
		moonExecCode += "        lw " + node.rightChildRegister + "," + node.getChildren().get(1).moonVarName + "(r0)\n";
		moonExecCode += "        mul " + node.localRegister + "," + node.leftChildRegister + "," + node.rightChildRegister + "\n"; 
		moonDataCode += "        % space for " + node.getChildren().get(0).moonVarName + " * " + node.getChildren().get(1).moonVarName + "\n";
		moonDataCode += String.format("%-7s",node.moonVarName) + " dw 0\n";
		moonExecCode += "        sw " + node.moonVarName + "(r0)," + node.localRegister + "\n";
		// deallocate the registers for the two children, and the current node
		this.registerPool.push(node.leftChildRegister);
		this.registerPool.push(node.rightChildRegister);
		this.registerPool.push(node.localRegister);
	}
	
	public void visit(AssignStatNode node){ 
		System.out.println("CodeGenerationVisitor visiting AssignStatNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren() )
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// allocate local register
		node.localRegister = this.registerPool.pop();
		//generate code
		moonExecCode += "        % processing: "  + node.getChildren().get(0).moonVarName + " := " + node.getChildren().get(1).moonVarName + "\n";
		moonExecCode += "        lw " + node.localRegister + "," + node.getChildren().get(1).moonVarName + "(r0)\n";
		moonExecCode += "        sw " + node.getChildren().get(0).moonVarName + "(r0)," + node.localRegister + "\n";
		//deallocate local register
		this.registerPool.push(node.localRegister);		
	}
	
	public void visit(ProgramBlockNode node) {
		System.out.println("CodeGenerationVisitor visiting ProgramBlockNode");
		// generate moon program's entry point
		moonExecCode += "        entry\n";
		moonExecCode += "        addi r14,r0,topaddr\n";
		// propagate acceptance of this visitor to all the children
		for (AstNode child : node.getChildren())
			child.accept(this);
		// generate moon program's end point
		moonDataCode += "        % buffer space used for console output\n";
		moonDataCode += "        buf res 20\n";
		moonExecCode += "        hlt\n";
	}
	
	public void visit(PutStatNode node) {
		System.out.println("CodeGenerationVisitor visiting PutStatNode");
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor		
		// create a local variable and allocate a register to this subcomputation 
		node.localRegister      = this.registerPool.pop();
		//generate code
		moonExecCode += "        % processing: put("  + node.getChildren().get(0).moonVarName + ")\n";
		moonExecCode += "        lw " + node.localRegister + "," + node.getChildren().get(0).moonVarName + "(r0)\n";
		moonExecCode += "        % put value on stack\n";	
		moonExecCode += "        sw -8(r14)," + node.localRegister + "\n";
		moonExecCode += "        % link buffer to stack\n";	
		moonExecCode += "        addi " + node.localRegister + ",r0, buf\n";
		moonExecCode += "        sw -12(r14)," + node.localRegister + "\n";
		moonExecCode += "        % convert int to string for output\n";	
		moonExecCode += "        jl r15, intstr\n";	
		moonExecCode += "        sw -8(r14),r13\n";
		moonExecCode += "        % output to console\n";	
		moonExecCode += "        jl r15, putstr\n";
		//deallocate local register
		this.registerPool.push(node.localRegister);		
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

	public void visit(AstNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(ProgNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(StatBlockNode node) {
		for (AstNode child : node.getChildren())
			child.accept(this);
	};

	public void visit(TypeNode node) {
		for (AstNode child : node.getChildren() )
			child.accept(this);
    };
}
