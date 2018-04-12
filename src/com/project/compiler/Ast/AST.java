//package com.project.compiler.Ast;
//
//import com.project.compiler.Config.Enums.NodeType;
//import com.project.compiler.Model.Token;
//
//public class AST {
//
//	private AstNode root;
//
//	public static AstNode makeNode(Token token, NodeType type) {
////		switch (type) {
////		case id:
////			AstNode nodeID = new AstNode(token, NodeType.id);
////			return nodeID;
////
////		case intNum:
////			AstNode nodeIntNum = new AstNode(token, NodeType.intNum);
////			return nodeIntNum;
////
////		case op:
////			AstNode nodeOP = new AstNode(token, NodeType.op);
////			return nodeOP;		
////
////		case Null:
////			AstNode nodeNull = new AstNode(token, NodeType.Null);
////			return nodeNull;
////
////		default:
////			AstNode nodeDefault = new AstNode(token, type);
////			return nodeDefault;
////		}
//		return null;
//	}
//
//	public static AstNode makeFamily(NodeType type, AstNode[] familyMemebrs) {
//		AstNode node = makeNode(null, type);
//		for (AstNode astNode : familyMemebrs) {
//			node.adoptChildren(astNode);
//		}
//		return node;
//	}
//	
//	public static AstNode makeFamily(AstNode parentNode, AstNode[] familyMemebrs) {
//		for (AstNode astNode : familyMemebrs) {
//			parentNode.adoptChildren(astNode);
//		}
//		return parentNode;
//	}
//	
//
//	/**
//	 * @return the root
//	 */
//	public AstNode getRoot() {
//		return root;
//	}
//
//	/**
//	 * @param root
//	 *            the root to set
//	 */
//	public void setRoot(AstNode root) {
//		this.root = root;
//	}
//
//}
