package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Visitor.VisitorBase;

//import Visitors.Visitor;

public class TypeNode extends AstNode {

	public TypeNode() {
		this.setNodeType(NodeType.TypeNode);
	}

	public void setTypeNode(Token dataToken) {
		this.setDataToken(dataToken);
	}

	/**
	 * Every class that will be visited needs an accept method, which then calls
	 * the specific visit method in the visitor, achieving double dispatch.
	 */
	 public void accept(VisitorBase visitor) {
	 visitor.visit(this);
	 }
}
