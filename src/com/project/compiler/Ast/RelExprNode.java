package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Visitor.VisitorBase;

public class RelExprNode extends AstNode {

	public RelExprNode() {
		this.setNodeType(NodeType.RelExprNode);
	}

	public void setRelExprNode(Token token, AstNode leftExpr, AstNode rightExpr) {
		this.setDataToken(token);
		this.addChild(leftExpr);
		this.addChild(rightExpr);
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
