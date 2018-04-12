package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class FCallNode extends AstNode {

	public FCallNode() {
		this.setNodeType(NodeType.FCallNode);
	}

	public void setFCallNode(AstNode id, AstNode aParamsList) {
		this.addChild(id);
		this.addChild(aParamsList);
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