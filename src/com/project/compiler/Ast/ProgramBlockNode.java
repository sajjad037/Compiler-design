package com.project.compiler.Ast;

import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class ProgramBlockNode extends AstNode {

	public ProgramBlockNode() {
		this.setNodeType(NodeType.ProgramBlockNode);
	}

	public void setProgramBlockNode(List<AstNode> listOfStatOrVarDeclNodes) {
		for (AstNode child : listOfStatOrVarDeclNodes)
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
