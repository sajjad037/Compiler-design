package com.project.compiler.Ast;

import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class FuncDeclListNode extends AstNode {
	
	public FuncDeclListNode() {
		this.setNodeType(NodeType.FuncDeclListNode);
	}

	public void setFuncDeclListNode(List<AstNode> listOfFuncDeclNodes) {
		for (AstNode child : listOfFuncDeclNodes)
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
