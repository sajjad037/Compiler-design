package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class ClassNode extends AstNode {

	public ClassNode() {
		this.setNodeType(NodeType.ClassNode);
	}

	public void setClassNode(AstNode id, AstNode inherList, AstNode varDeclList, AstNode funcDeclList) {
		this.addChild(id);
		this.addChild(inherList);
		this.addChild(varDeclList);
		this.addChild(funcDeclList);

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
