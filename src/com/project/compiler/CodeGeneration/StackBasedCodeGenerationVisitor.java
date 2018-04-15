package com.project.compiler.CodeGeneration;

import java.util.Stack;

import com.project.compiler.Ast.AddOpNode;
import com.project.compiler.Ast.AssignStatNode;
import com.project.compiler.Ast.AstNode;
import com.project.compiler.Ast.ClassListNode;
import com.project.compiler.Ast.ClassNode;
import com.project.compiler.Ast.DimListNode;
import com.project.compiler.Ast.FCallNode;
import com.project.compiler.Ast.FuncDefListNode;
import com.project.compiler.Ast.FuncDefNode;
import com.project.compiler.Ast.IdNode;
import com.project.compiler.Ast.MultOpNode;
import com.project.compiler.Ast.NumNode;
import com.project.compiler.Ast.ParamListNode;
import com.project.compiler.Ast.ProgNode;
import com.project.compiler.Ast.ProgramBlockNode;
import com.project.compiler.Ast.PutStatNode;
import com.project.compiler.Ast.ReturnStatNode;
import com.project.compiler.Ast.StatBlockNode;
import com.project.compiler.Ast.TypeNode;
import com.project.compiler.Ast.VarDeclNode;
import com.project.compiler.SymbolTable.SymTabEntry;
import com.project.compiler.Visitor.VisitorBase;

/**
 * Visitor to generate moon code for simple expressions and assignment and put
 * statements. Also include code for function calls using a stack-based model.
 */

public class StackBasedCodeGenerationVisitor extends VisitorBase {

	public Stack<String> m_registerPool = new Stack<String>();
	public String m_moonExecCode = new String(); // moon instructions part
	public String m_moonDataCode = new String(); // moon data part
	public String m_mooncodeindent = new String("          ");
	//public String m_outputfilename = new String();
	public String outputstring = new String();

	public StackBasedCodeGenerationVisitor() {
		// create a pool of registers as a stack of Strings
		// assuming only r1, ..., r12 are available
		for (Integer i = 12; i >= 1; i--)
			m_registerPool.push("r" + i.toString());
	}

//	public StackBasedCodeGenerationVisitor(String p_filename) {
//		this.m_outputfilename = p_filename;
//		// create a pool of registers as a stack of Strings
//		// assuming only r1, ..., r12 are available
//		for (Integer i = 12; i >= 1; i--)
//			m_registerPool.push("r" + i.toString());
//	}

	public void visit(ProgNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// if the Visitor was given a file name,
		// then write the generated code into this file
		
		outputstring = String.format("%s \r\n %s", this.m_moonExecCode, this.m_moonDataCode);
		
//		if (!this.m_outputfilename.isEmpty()) {
//			File file = new File(this.m_outputfilename);
//			try (PrintWriter out = new PrintWriter(file)) {
//				out.println(this.m_moonExecCode);
//				out.println(this.m_moonDataCode);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	};

	public void visit(NumNode p_node) {
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// create a local variable and allocate a register to this
		// subcomputation
		String localregister1 = this.m_registerPool.pop();
		// generate code
		m_moonExecCode += m_mooncodeindent + "% processing: " + p_node.moonVarName + " := " + p_node.getData() + "\n";
		// create a value corresponding to the literal value
		m_moonExecCode += m_mooncodeindent + "addi " + localregister1 + ",r0," + p_node.getData() + "\n";
		// assign this value to a temporary variable (assumed to have been
		// previously created by the symbol table generator)
		m_moonExecCode += m_mooncodeindent + "sw " + p_node.symtabentry.m_offset + "(r14)," + localregister1 + "\n";
		// deallocate the register for the current node
		this.m_registerPool.push(localregister1);
	}

	public void visit(AddOpNode p_node) {
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// allocate registers to this subcomputation
		String localregister1 = this.m_registerPool.pop();
		String localregister2 = this.m_registerPool.pop();
		String localregister3 = this.m_registerPool.pop();
		String localregister4 = this.m_registerPool.pop();
		// generate code
		m_moonExecCode += m_mooncodeindent + "% processing: " + p_node.moonVarName + " := "
				+ p_node.getChildren().get(0).moonVarName + " + " + p_node.getChildren().get(1).moonVarName + "\n";
		// load the values of the operands into registers
		m_moonExecCode += m_mooncodeindent + "lw " + localregister2 + ","
				+ p_node.symtab.lookupName(p_node.getChildren().get(0).moonVarName).m_offset + "(r14)\n";
		m_moonExecCode += m_mooncodeindent + "lw " + localregister3 + ","
				+ p_node.symtab.lookupName(p_node.getChildren().get(1).moonVarName).m_offset + "(r14)\n";
		// add operands
		m_moonExecCode += m_mooncodeindent + "add " + localregister4 + "," + localregister2 + "," + localregister3
				+ "\n";
		// assign the result into a temporary variable (assumed to have been
		// previously created by the symbol table generator)
		m_moonExecCode += m_mooncodeindent + "sw " + p_node.symtab.lookupName(p_node.moonVarName).m_offset + "(r14),"
				+ localregister4 + "\n";
		// deallocate the registers
		this.m_registerPool.push(localregister1);
		this.m_registerPool.push(localregister2);
		this.m_registerPool.push(localregister3);
		this.m_registerPool.push(localregister4);
	}

	public void visit(MultOpNode p_node) {
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// create a local variable and allocate a register to this
		// subcomputation
		String localregister1 = this.m_registerPool.pop();
		String localregister2 = this.m_registerPool.pop();
		String localregister3 = this.m_registerPool.pop();
		String localregister4 = this.m_registerPool.pop();
		// generate code
		m_moonExecCode += m_mooncodeindent + "% processing: " + p_node.moonVarName + " := "
				+ p_node.getChildren().get(0).moonVarName + " * " + p_node.getChildren().get(1).moonVarName + "\n";
		// load the values of the operands into registers
		m_moonExecCode += m_mooncodeindent + "lw " + localregister2 + ","
				+ p_node.symtab.lookupName(p_node.getChildren().get(0).moonVarName).m_offset + "(r14)\n";
		m_moonExecCode += m_mooncodeindent + "lw " + localregister3 + ","
				+ p_node.symtab.lookupName(p_node.getChildren().get(1).moonVarName).m_offset + "(r14)\n";
		// multiply operands
		m_moonExecCode += m_mooncodeindent + "mul " + localregister4 + "," + localregister2 + "," + localregister3
				+ "\n";
		// assign the result into a temporary variable (assumed to have been
		// previously created by the symbol table generator)
		m_moonExecCode += m_mooncodeindent + "sw " + p_node.symtab.lookupName(p_node.moonVarName).m_offset + "(r14),"
				+ localregister4 + "\n";
		// deallocate the registers for the two children, and the current node
		this.m_registerPool.push(localregister1);
		this.m_registerPool.push(localregister2);
		this.m_registerPool.push(localregister3);
		this.m_registerPool.push(localregister4);
	}

	public void visit(AssignStatNode p_node) {
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// allocate local registers
		String localregister1 = this.m_registerPool.pop();
		String localregister2 = this.m_registerPool.pop();
		String localregister3 = this.m_registerPool.pop();
		// generate code
		m_moonExecCode += m_mooncodeindent + "% processing: " + p_node.getChildren().get(0).moonVarName + " := "
				+ p_node.getChildren().get(1).moonVarName + "\n";
		// load the assigned value into a register
		m_moonExecCode += m_mooncodeindent + "lw " + localregister2 + ","
				+ p_node.symtab.lookupName(p_node.getChildren().get(1).moonVarName).m_offset + "(r14)\n";
		// assign the value to the assigned variable
		m_moonExecCode += m_mooncodeindent + "sw "
				+ p_node.symtab.lookupName(p_node.getChildren().get(0).moonVarName).m_offset + "(r14)," + localregister2
				+ "\n";
		// deallocate local registers
		this.m_registerPool.push(localregister1);
		this.m_registerPool.push(localregister2);
		this.m_registerPool.push(localregister3);
	}

	public void visit(ProgramBlockNode p_node) {
		// generate moon program's entry point
		m_moonExecCode += m_mooncodeindent + "entry\n";
		// make the stack frame pointer (address stored in r14) point
		// to the top address allocated to the moon processor
		m_moonExecCode += m_mooncodeindent + "addi r14,r0,topaddr\n";
		// propagate acceptance of this visitor to all the children
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// generate moon program's end point
		m_moonDataCode += m_mooncodeindent + "% buffer space used for console output\n";
		// buffer used by the lib.m subroutines
		m_moonDataCode += String.format("%-10s", "buf") + "res 20\n";
		// halting point of the entire program
		m_moonExecCode += m_mooncodeindent + "hlt\n";
	}

	public void visit(PutStatNode p_node) {
		// First, propagate accepting the same visitor to all the children
		// This effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// Then, do the processing of this nodes' visitor
		// create a local variable and allocate a register to this
		// subcomputation
		String localregister1 = this.m_registerPool.pop();
		String localregister2 = this.m_registerPool.pop();
		// generate code
		m_moonExecCode += m_mooncodeindent + "% processing: put(" + p_node.getChildren().get(0).moonVarName + ")\n";
		// put the value to be printed into a register
		m_moonExecCode += m_mooncodeindent + "lw " + localregister1 + ","
				+ p_node.symtab.lookupName(p_node.getChildren().get(0).moonVarName).m_offset + "(r14)\n";
		m_moonExecCode += m_mooncodeindent + "% put value on stack\n";
		// make the stack frame pointer point to the called function's stack
		// frame
		m_moonExecCode += m_mooncodeindent + "addi r14,r14," + p_node.symtab.m_size + "\n";
		// copy the value to be printed in the called function's stack frame
		m_moonExecCode += m_mooncodeindent + "sw -8(r14)," + localregister1 + "\n";
		m_moonExecCode += m_mooncodeindent + "% link buffer to stack\n";
		m_moonExecCode += m_mooncodeindent + "addi " + localregister1 + ",r0, buf\n";
		m_moonExecCode += m_mooncodeindent + "sw -12(r14)," + localregister1 + "\n";
		m_moonExecCode += m_mooncodeindent + "% convert int to string for output\n";
		m_moonExecCode += m_mooncodeindent + "jl r15, intstr\n";
		// receive the return value in r13 and right away put it in the next
		// called function's stack frame
		m_moonExecCode += m_mooncodeindent + "sw -8(r14),r13\n";
		m_moonExecCode += m_mooncodeindent + "% output to console\n";
		m_moonExecCode += m_mooncodeindent + "jl r15, putstr\n";
		// make the stack frame pointer point back to the current function's
		// stack frame
		m_moonExecCode += m_mooncodeindent + "subi r14,r14," + p_node.symtab.m_size + "\n";
		// deallocate local register
		this.m_registerPool.push(localregister1);
		this.m_registerPool.push(localregister2);
	};

	public void visit(FuncDefNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		m_moonExecCode += m_mooncodeindent + "% processing function definition: " + p_node.moonVarName + "\n";
		// create the tag to jump onto
		// and copy the jumping-back address value in the called function's
		// stack frame
		m_moonExecCode += String.format("%-10s", p_node.getData()) + "sw -4(r14),r15\n";
		// generate the code for the function body
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// copy back the jumping-back address into r15
		m_moonExecCode += m_mooncodeindent + "lw r15,-4(r14)\n";
		// jump back to the calling function
		m_moonExecCode += m_mooncodeindent + "jr r15\n";
	};

	public void visit(ReturnStatNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		String localregister1 = this.m_registerPool.pop();
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		// copy the result of the return value into the first memory cell in the
		// current stack frame
		// this way, the return value is conveniently at the top of the calling
		// function's stack frame
		m_moonExecCode += m_mooncodeindent + "% processing: return(" + p_node.getChildren().get(0).moonVarName + ")\n";
		m_moonExecCode += m_mooncodeindent + "lw " + localregister1 + ","
				+ p_node.symtab.lookupName(p_node.getChildren().get(0).moonVarName).m_offset + "(r14)\n";
		m_moonExecCode += m_mooncodeindent + "sw " + "0(r14)," + localregister1 + "\n";
		this.m_registerPool.push(localregister1);
	}

	// public void visit(FuncCallNode p_node) {
	public void visit(FCallNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
		String localregister1 = this.m_registerPool.pop();
		// pass parameters
		// here we assume that the parameters are the size of a word,
		// which is not true for arrays and objects.
		// In those cases, a loop copying the values e.g. byte-by-byte is
		// necessary
		SymTabEntry tableentryofcalledfunction = p_node.symtab.lookupName(p_node.getData());
		int indexofparam = 0;
		m_moonExecCode += m_mooncodeindent + "% processing: function call to " + p_node.getChildren().get(0).moonVarName
				+ " \n";
		for (AstNode param : p_node.getChildren().get(1).getChildren()) {
			m_moonExecCode += m_mooncodeindent + "lw " + localregister1 + ","
					+ p_node.symtab.lookupName(param.moonVarName).m_offset + "(r14)\n";
			int offsetofparam = p_node.symtab.m_size
					+ tableentryofcalledfunction.m_subtable.m_symlist.get(indexofparam).m_offset;
			m_moonExecCode += m_mooncodeindent + "sw " + offsetofparam + "(r14)," + localregister1 + "\n";
			indexofparam++;
		}
		// make the stack frame pointer point to the called function's stack
		// frame
		m_moonExecCode += m_mooncodeindent + "addi r14,r14," + p_node.symtab.m_size + "\n";
		// jump to the called function's code
		// here the function's name is the label
		// a unique label generator is necessary in the general case
		m_moonExecCode += m_mooncodeindent + "jl r15," + p_node.getData() + "\n";
		// upon jumping back, set the stack frame pointer back to the current
		// function's stack frame
		m_moonExecCode += m_mooncodeindent + "subi r14,r14," + p_node.symtab.m_size + "\n";
		// copy the return value in memory space to store it on the current
		// stack frame
		// to evaluate the expression in which it is
		m_moonExecCode += m_mooncodeindent + "lw " + localregister1 + "," + p_node.symtab.m_size + "(r14)\n";
		m_moonExecCode += m_mooncodeindent + "sw " + p_node.symtab.lookupName(p_node.moonVarName).m_offset + "(r14),"
				+ localregister1 + "\n";
		this.m_registerPool.push(localregister1);
	};

	// Below are the visit methods for node types for which this visitor does
	// not apply. They still have to propagate acceptance of the visitor to
	// their children.
	public void visit(ClassListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(ClassNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(DimListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(FuncDefListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(ParamListNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	}

	// public void visit(DimNode p_node) {
	// // propagate accepting the same visitor to all the children
	// // this effectively achieves Depth-First AST Traversal
	// for (AstNode child : p_node.getChildren() )
	// child.accept(this);
	// };

	public void visit(VarDeclNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	}

	public void visit(IdNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	}

	public void visit(AstNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(StatBlockNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};

	public void visit(TypeNode p_node) {
		// propagate accepting the same visitor to all the children
		// this effectively achieves Depth-First AST Traversal
		for (AstNode child : p_node.getChildren())
			child.accept(this);
	};
	
	public String getOutPutString() {
		return this.outputstring;
	}
}
