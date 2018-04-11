package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Visitor.VisitorBase;

public class ReturnStatNode extends AstNode {

	public ReturnStatNode() {
		this.setNodeType(NodeType.ReturnStatNode);
	}

	public void setReturnStatNode(Token token, AstNode expr, AstNode passNull) {
		this.setDataToken(token);
		this.addChild(expr);
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