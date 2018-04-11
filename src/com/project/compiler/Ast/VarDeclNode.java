package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

//import Visitors.Visitor;

public class VarDeclNode extends AstNode {

	/**
	 * Some intermediate nodes may contain additional members that store
	 * information aggregated by specific visitors. Here, a variable declaration
	 * record is stored, which stores information aggregated by the
	 * VarDeclVisitor.
	 */

	public VarDeclNode() {
		this.setNodeType(NodeType.VarDeclNode);
	}

	public VarDeclNode(AstNode type, AstNode id, AstNode dimList) {
		super(null);
		this.addChild(type);
		this.addChild(id);
		this.addChild(dimList);
	}

	public void setVarDeclNode(AstNode type, AstNode id, AstNode dimList) {
		this.addChild(type);
		this.addChild(id);
		this.addChild(dimList);
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