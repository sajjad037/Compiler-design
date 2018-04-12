package com.project.compiler.Ast;
//import Visitors.Visitor;

import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class InherList extends AstNode {

	public InherList() {
		this.setNodeType(NodeType.InherList);
	}

	public void setInherList(List<AstNode> listOfIds) {
		for (AstNode child : listOfIds)
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
