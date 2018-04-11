package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class VarElementNode extends AstNode {

	public VarElementNode() {
		this.setNodeType(NodeType.VarElementNode);
	}

	public void setVarElementNode(AstNode dataMember, AstNode fCall) {
		this.addChild(dataMember);
		this.addChild(fCall);

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
