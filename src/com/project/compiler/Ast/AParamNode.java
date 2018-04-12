package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class AParamNode extends AstNode {

	public AParamNode() {
		this.setNodeType(NodeType.AParamNode);
	}

	public void SetAParamNode(AstNode expr, AstNode passNull) {
		this.addChild(expr);

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
