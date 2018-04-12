package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class ExprNode extends AstNode {

	public ExprNode() {
		this.setNodeType(NodeType.ExprNode);
	}

	public void setExprNode(AstNode arithOrRelExpr) {
		this.addChild(arithOrRelExpr);
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
