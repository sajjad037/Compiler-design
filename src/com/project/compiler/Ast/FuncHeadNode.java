package com.project.compiler.Ast;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Visitor.VisitorBase;

public class FuncHeadNode extends AstNode {

	/**
	 * Some intermediate nodes may contain additional members that store
	 * information aggregated by specific visitors. Here, a variable declaration
	 * record is stored, which stores information aggregated by the
	 * VarDeclVisitor. In the project, this record would be added to a symbol
	 * table.
	 */

	public FuncHeadNode() {
		this.setNodeType(NodeType.FuncHeadNode);
	}

	public FuncHeadNode(AstNode type, AstNode classIdOrFuncId, AstNode scIDorNull, AstNode paramList) {
		super(null);
		this.addChild(type);
		this.addChild(classIdOrFuncId);
		this.addChild(scIDorNull);
		this.addChild(paramList);
	}

	public void setFuncHeadNode(AstNode type, AstNode classIdOrFuncId, AstNode scIDorNull, AstNode paramList) {
		this.addChild(type);
		this.addChild(classIdOrFuncId);
		this.addChild(scIDorNull);
		this.addChild(paramList);
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
