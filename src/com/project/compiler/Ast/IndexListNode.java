package com.project.compiler.Ast;

import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class IndexListNode extends AstNode {

	public IndexListNode() {
		this.setNodeType(NodeType.IndexListNode);
	}

	public void setIndexListNode(List<AstNode> arithExprList) {

		for (AstNode arithExpr : arithExprList)
			this.addChild(arithExpr);
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
