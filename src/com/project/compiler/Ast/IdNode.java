package com.project.compiler.Ast;
//import Visitors.Visitor;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Visitor.VisitorBase;

public class IdNode extends AstNode {

	public IdNode() {
		this.setNodeType(NodeType.IdNode);
	}

	public void setIdNode(Token dataToken) {
		this.setDataToken(dataToken);
	}

	public void setIdNode(Token dataToken, Token typeToken) {
		this.setDataToken(dataToken);
		this.setTypeToken(typeToken);
	}

	/**
	 * Every class that may be visited by any visitor needs
	 * to implement the accept method, that calls the appropriate
	 * visit method in the passed visitor, achieving double
	 * dispatch. 
	 * 
	 */
	public void accept(VisitorBase visitor) {
		visitor.visit(this);
	}
}
