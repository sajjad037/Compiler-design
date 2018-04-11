package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Visitor.VisitorBase;

public class ForStatNode extends AstNode {

	public ForStatNode() {
		this.setNodeType(NodeType.ForStatNode);
	}

	public void setForStatNode(Token token, AstNode type, AstNode id, AstNode expr, AstNode relExpr, AstNode assignStat,
			AstNode statBlock) {
		this.setDataToken(token);
		this.addChild(type);
		this.addChild(id);
		this.addChild(expr);
		this.addChild(relExpr);
		this.addChild(assignStat);
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
