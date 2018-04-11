package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class FuncDefNode extends AstNode {

	/**
	 * Some intermediate nodes may contain additional members that store
	 * information aggregated by specific visitors. Here, a variable declaration
	 * record is stored, which stores information aggregated by the
	 * VarDeclVisitor. In the project, this record would be added to a symbol
	 * table.
	 */

	public FuncDefNode() {
		this.setNodeType(NodeType.FuncDefNode);
	}

	public void setFuncDefNode(AstNode funcHeadNode, AstNode statBlock) {
		this.addChild(funcHeadNode);
		this.addChild(statBlock);
	}

	/**
	 * If a visitable class contains members that also may need to be visited,
	 * it should make these members to accept the visitor before itself being
	 * visited.
	 */
	public void accept(VisitorBase visitor) {
		for (AstNode child : this.getChildren())
			child.accept(visitor);
		visitor.visit(this);
	}

}