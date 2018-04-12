package com.project.compiler.Ast;

import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

//import Visitors.Visitor;

public class ParamListNode extends AstNode {

	public ParamListNode() {
		this.setNodeType(NodeType.ParamListNode);
	}

	public void setParamListNode(List<AstNode> listOfParamNodes) {
		for (AstNode child : listOfParamNodes)
			this.addChild(child);
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
