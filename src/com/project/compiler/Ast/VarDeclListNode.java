package com.project.compiler.Ast;

import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

//import Visitors.Visitor;

public class VarDeclListNode extends AstNode {

	/**
	 * Some intermediate nodes may contain additional members that store
	 * information aggregated by specific visitors. Here, a variable declaration
	 * record is stored, which stores information aggregated by the
	 * VarDeclVisitor.
	 */

	public VarDeclListNode() {
		this.setNodeType(NodeType.VarDeclListNode);
	}

	public void setVarDeclListNode(List<AstNode> listOfVarDecl) {
		for (AstNode child : listOfVarDecl)
			this.addChild(child);
	}

	/**
	 * If a visitable class contains members that also may need to be visited,
	 * it should make these members to accept the visitor before itself being
	 * visited.
	 */
	public void accept(VisitorBase visitor) {
		visitor.visit(this);
	}

}