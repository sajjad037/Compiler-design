package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class DataMemberNode extends AstNode {

	public DataMemberNode() {
		this.setNodeType(NodeType.DataMemberNode);
	}

	public void setDataMemberNode(AstNode id, AstNode indexList) {
		this.addChild(id);
		this.addChild(indexList);
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
