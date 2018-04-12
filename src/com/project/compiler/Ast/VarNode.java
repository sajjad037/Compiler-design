package com.project.compiler.Ast;

import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class VarNode extends AstNode {

	public VarNode() {
		this.setNodeType(NodeType.VarNode);
	}

	public void setVarNode(List<AstNode> varElementsList) {

		for (AstNode varElement : varElementsList)
			this.addChild(varElement);
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
