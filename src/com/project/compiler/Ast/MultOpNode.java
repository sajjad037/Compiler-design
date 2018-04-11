package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Visitor.VisitorBase;

//import Visitors.Visitor;

public class MultOpNode extends AstNode {

	public MultOpNode() {
		this.setNodeType(NodeType.MultOpNode);
	}

	public void setMultOpNode(Token dateToken, AstNode leftChildTrem, AstNode rightChildFactor) {
		this.setDataToken(dateToken);
		this.addChild(leftChildTrem);
		this.addChild(rightChildFactor);
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
