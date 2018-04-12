package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Visitor.VisitorBase;

public class AssignStatNode extends AstNode {

	public AssignStatNode() {
		this.setNodeType(NodeType.AssignStatNode);
	}

	public void setAssignStatNode(Token token, AstNode leftChild, AstNode rightChild) {
		this.setDataToken(token);
		this.addChild(leftChild);
		this.addChild(rightChild);
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
