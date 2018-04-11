package com.project.compiler.Ast;

import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Visitor.VisitorBase;

public class ClassListNode extends AstNode {

	// No token then pass null.
	public ClassListNode() {
		this.setNodeType(NodeType.ClassListNode);
	}

	public void setClassListNode(Token token, List<AstNode> listOfClassNodes) {
		for (AstNode child : listOfClassNodes)
			this.addChild(child);
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
