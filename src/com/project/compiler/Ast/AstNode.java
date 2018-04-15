package com.project.compiler.Ast;

import java.util.ArrayList;
import java.util.List;

import com.project.compiler.Config.Enums.NodeType;
import com.project.compiler.Model.Token;
import com.project.compiler.Model.VariablesData;
import com.project.compiler.SymbolTable.SymTab;
import com.project.compiler.SymbolTable.SymTabEntry;
import com.project.compiler.Visitor.VisitorBase;


public abstract class AstNode {

	private AstNode rightSibling;
	private AstNode leftMostSibling;
	private AstNode leftMostChilder;
	//private AstNode parent;
	private AstNode lastAddedSibling;
	
	
	private ArrayList<AstNode> childerns = new ArrayList<AstNode>();
	private ArrayList<VariablesData> variables = new ArrayList<VariablesData>();
	private AstNode parent = null;
	public String data = null;
	public static int nodelevel = 0;

	private boolean hasChildern;
	private Token dataToken;
	private Token typeToken;
	private NodeType nodeType = NodeType.IdNode;
	private boolean isLastnode = true;

	// The following data members have been added
	// during the implementation of the visitors
	// These could be added using a decorator pattern
	// triggered by a visitor
	public String type = null;
	public String subtreeString = new String();
	public SymTab symtab = null;
	public SymTabEntry symtabentry = null;
    public  String localRegister      = new String(); 
    public  String leftChildRegister  = new String(); 
    public  String rightChildRegister = new String(); 
    public  String moonVarName        = new String();


	public AstNode() {
	}

	public AstNode(Token dataToken) {
		this.setDataToken(dataToken);

		// this.nodeType = nodeType;
	}

	// public AstNode(String data) {
	// this.setData(data);
	// }

	// public AstNode(String data, Token token) {
	// this.setData(data);
	// this.token = token;
	// }

	// public AstNode(String data, AstNode parent) {
	// this.setData(data);
	// this.setParent(parent);
	// parent.addChild(this);
	// }

	public AstNode(Token dataToken, AstNode parent) {
		this.setDataToken(dataToken);

		this.setParent(parent);
		parent.addChild(this);
	}

	public AstNode(Token dataToken, Token typeToken) {
		this.setDataToken(dataToken);

		this.setTypeToken(typeToken);

	}

	/**
	 * make Sibling
	 * 
	 * @param newSibling
	 */
	public void makeSibling(AstNode newSibling) {
		// Link Current right with New Left most.
		if (newSibling.getLeftMostSibling() == null) {
			rightSibling = newSibling;
		} else {
			rightSibling = newSibling.getLeftMostSibling();
		}

		// Point to Current parent
		newSibling.setParent(parent);

		// Point new sibling left most to current left most
		if (leftMostSibling == null) {
			newSibling.setLeftMostSibling(this);
		} else {
			newSibling.setLeftMostSibling(leftMostSibling);
		}

		if (lastAddedSibling != null) {
			lastAddedSibling.setLastnode(false);
		}
		isLastnode = false;
		lastAddedSibling = newSibling;
	}

	/**
	 * adopt Children
	 * 
	 * @param newChild
	 */
	public void adoptChildren(AstNode newChild) {
		// Point new sibling left most to current left most
		if (leftMostChilder == null) {
			newChild.setParent(this);
			if (newChild.getLeftMostSibling() == null) {
				leftMostChilder = newChild;
			} else {
				leftMostChilder = newChild.getLeftMostSibling();
			}

		} else {
			leftMostChilder.makeSibling(newChild);
			newChild.setParent(this);
		}
		addChild(newChild);
		hasChildern = true;
	}
	

	/**
	 * @return the rightSibling
	 */
	public AstNode getRightSibling() {
		return rightSibling;
	}

	/**
	 * @param rightSibling
	 *            the rightSibling to set
	 */
	public void setRightSibling(AstNode rightSibling) {
		this.rightSibling = rightSibling;
	}

	/**
	 * @return the leftMostSibling
	 */
	public AstNode getLeftMostSibling() {
		return leftMostSibling;
	}

	/**
	 * @param leftMostSibling
	 *            the leftMostSibling to set
	 */
	public void setLeftMostSibling(AstNode leftMostSibling) {
		this.leftMostSibling = leftMostSibling;
	}

	/**
	 * @return the parent
	 */
	public AstNode getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(AstNode parent) {
		this.parent = parent;
	}

	/**
	 * @return the dataToken
	 */
	public Token getDataToken() {
		return dataToken;
	}

	/**
	 * @param dataToken
	 *            the dataToken to set
	 */
	public void setDataToken(Token dataToken) {
		this.dataToken = dataToken;

		if (dataToken != null) {
			this.setData(dataToken.getValue());
		} else {
			this.setData("");
		}
	}

	/**
	 * @return the typeToken
	 */
	public Token getTypeToken() {
		return typeToken;
	}

	/**
	 * @param typeToken
	 *            the typeToken to set
	 */
	public void setTypeToken(Token typeToken) {
		this.typeToken = typeToken;

		if (typeToken != null) {
			this.setType(typeToken.getValue());
		} else {
			this.setType("");
		}
	}

	/**
	 * @return the leftMostChilder
	 */
	public AstNode getLeftMostChilder() {
		return leftMostChilder;
	}

	/**
	 * @param leftMostChilder
	 *            the leftMostChilder to set
	 */
	public void setLeftMostChilder(AstNode leftMostChilder) {
		this.leftMostChilder = leftMostChilder;
	}

	/**
	 * @return the hasChildern
	 */
	public boolean isHasChildern() {
		return hasChildern;
	}

	/**
	 * @param hasChildern
	 *            the hasChildern to set
	 */
	public void setHasChildern(boolean hasChildern) {
		this.hasChildern = hasChildern;
	}

	/**
	 * @return the lastAddedSibling
	 */
	public AstNode getLastAddedSibling() {
		return lastAddedSibling;
	}

	/**
	 * @param lastAddedSibling
	 *            the lastAddedSibling to set
	 */
	public void setLastAddedSibling(AstNode lastAddedSibling) {
		this.lastAddedSibling = lastAddedSibling;
	}

	// /**
	// * @return the nodeType
	// */
	// public NodeType getNodeType() {
	// return nodeType;
	// }
	//
	// /**
	// * @param nodeType
	// * the nodeType to set
	// */
	// public void setNodeType(NodeType nodeType) {
	// this.nodeType = nodeType;
	// }

	/**
	 * @return the isLastnode
	 */
	public boolean isLastnode() {
		return isLastnode;
	}

	/**
	 * @param isLastnode
	 *            the isLastnode to set
	 */
	public void setLastnode(boolean isLastnode) {
		this.isLastnode = isLastnode;
	}

	/**
	 * @return the childerns
	 */
	public ArrayList<AstNode> getChildren() {
		return childerns;
	}

	/**
	 * @param childerns
	 *            the child to set
	 */
	public void addChild(AstNode child) {
		child.setParent(this);
		this.childerns.add(child);
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the nodelevel
	 */
	public static int getNodelevel() {
		return nodelevel;
	}

	/**
	 * @param nodelevel
	 *            the nodelevel to set
	 */
	public static void setNodelevel(int nodelevel) {
		AstNode.nodelevel = nodelevel;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the subtreeString
	 */
	public String getSubtreeString() {
		return subtreeString;
	}

	/**
	 * @param subtreeString
	 *            the subtreeString to set
	 */
	public void setSubtreeString(String subtreeString) {
		this.subtreeString = subtreeString;
	}

	/**
	 * @return the nodeType
	 */
	public NodeType getNodeType() {
		return nodeType;
	}

	/**
	 * @param nodeType
	 *            the nodeType to set
	 */
	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public boolean isRoot() {
		return (this.parent == null);
	}

	public boolean isLeaf() {
		if (this.childerns.size() == 0)
			return true;
		else
			return false;
	}

	public void removeParent() {
		this.parent = null;
	}
	
	/**
	 * @return the variables
	 */
	public ArrayList<VariablesData> getVariables() {
		return variables;
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(ArrayList<VariablesData> variables) {
		this.variables = variables;
	}
	
	/**
	 * @param variables the variables to set
	 */
	public void addVariables(VariablesData variables) {
		this.variables.add(variables);
	}

	

//	public void print() {
//		System.out.println("===================================================================================");
//		System.out.println("Node type                 | data  | type      | subtreestring | symtabentry");
//		System.out.println("===================================================================================");
//		this.printSubtree();
//		System.out.println("===================================================================================");
//
//	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"=================================================================================================\r\n");
		//sb.append(String.format("%-25s | %-20s | %-12s | %-16s | %s \r\n", "Node type", "data", "type", "subtreestring", "symtabentry"));
		sb.append(String.format("%-25s | %-20s | %-12s | %s \r\n", "Node type", "data", "type", "subtreestring"));
		sb.append(
				"=================================================================================================\r\n");
		sb.append(this.toSubString());
		sb.append(
				"=================================================================================================\r\n");
		return sb.toString();

	}

//	public void printSubtree() {
//		for (int i = 0; i < AstNode.nodelevel; i++)
//			System.out.print("  ");
//
//		String toprint = String.format("%-25s", this.getClass().getName().replace("com.project.compiler.Ast", "AST"));
//		for (int i = 0; i < AstNode.nodelevel; i++)
//			toprint = toprint.substring(0, toprint.length() - 2);
//		toprint += String.format("%-8s",
//				(this.getData() == null || this.getData().isEmpty()) ? " | " : " | " + this.getData());
//		toprint += String.format("%-12s",
//				(this.getType() == null || this.getType().isEmpty()) ? " | " : " | " + this.getType());
//		toprint += String.format("%-16s",
//				(this.subtreeString == null || this.subtreeString.isEmpty()) ? " | " : " | " + this.subtreeString);
//		toprint += (this.symtabentry == null) ? " | " : " | " +
//		 this.symtabentry.m_entry;
//		//toprint += " | ";
//
//		System.out.println(toprint);
//
//		AstNode.nodelevel++;
//		List<AstNode> children = this.getChildren();
//		for (int i = 0; i < children.size(); i++) {
//			children.get(i).printSubtree();
//		}
//		AstNode.nodelevel--;
//	}

	public String toSubString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < AstNode.nodelevel; i++)
			sb.append("  ");

		//System.out.println("this.getClass().getName(): "+this.getClass().getName());
		String toprint = String.format("%-25s", this.getClass().getName().replace("com.project.compiler.Ast", "AST"));
		//String toprint = String.format("%-25s", this.getClass().getName());
		for (int i = 0; i < AstNode.nodelevel; i++) {
			if (toprint.length() > 2)
				toprint = toprint.substring(0, toprint.length() - 2);
		}
		toprint += String.format("%-23s",
				(this.getData() == null || this.getData().isEmpty()) ? " | " : " | " + this.getData());
		toprint += String.format("%-15s",
				(this.getType() == null || this.getType().isEmpty()) ? " | " : " | " + this.getType());
		//toprint += String.format("%-19s"  , (this.subtreeString == null || this.subtreeString.isEmpty()) ? " | " : " | " + this.subtreeString);
        toprint += (String.format("%s" , (this.subtreeString == null || this.subtreeString.isEmpty()) ? " | " : " | " + (this.subtreeString.replaceAll("\\n+",""))));
		 //toprint += (this.symtabentry == null) ? " | " : " | " + this.symtabentry.m_entry;
		//toprint += " | ";

		sb.append(toprint + "\r\n");

		AstNode.nodelevel++;
		List<AstNode> children = this.getChildren();
		for (int i = 0; i < children.size(); i++) {
			sb.append(children.get(i).toSubString());
		}
		AstNode.nodelevel--;

		return sb.toString();
	}

	public void setAddOpNode(Token token, AstNode arithExpr, AstNode term) {
		this.setDataToken(token);
		this.addChild(arithExpr);
		this.addChild(term);
	}

	public void SetAParamNode(AstNode expr, AstNode passNull) {
		this.addChild(expr);

	}

	public void setArithExprNode(List<AstNode> listAstNode) {

		for (AstNode child : listAstNode)
			this.addChild(child);

	}

	public void setAssignStatNode(Token token, AstNode leftChild, AstNode rightChild) {
		this.setDataToken(token);
		this.addChild(leftChild);
		this.addChild(rightChild);
	}

	public void setClassListNode(Token token, List<AstNode> listOfClassNodes) {
		for (AstNode child : listOfClassNodes)
			this.addChild(child);
	}

	public void setClassNode(AstNode id, AstNode inherList, AstNode varDeclList, AstNode funcDeclList) {
		this.addChild(id);
		this.addChild(inherList);
		this.addChild(varDeclList);
		this.addChild(funcDeclList);

	}

	public void setDataMemberNode(AstNode id, AstNode indexList) {
		this.addChild(id);
		this.addChild(indexList);
	}

	public void setDimListNode(List<AstNode> listOfDimNodes) {
		for (AstNode child : listOfDimNodes)
			this.addChild(child);
	}

	public void setFactorNode(List<AstNode> listAstNode) {
		for (AstNode child : listAstNode)
			this.addChild(child);

	}

	public void setFCallNode(AstNode id, AstNode aParamsList) {
		this.addChild(id);
		this.addChild(aParamsList);
	}

	public void setForStatNode(Token token, AstNode type, AstNode id, AstNode expr, AstNode relExpr, AstNode assignStat,
			AstNode statBlock) {
		this.setDataToken(token);
		this.addChild(type);
		this.addChild(id);
		this.addChild(expr);
		this.addChild(relExpr);
		this.addChild(assignStat);
		this.addChild(statBlock);
	}

	public void setFuncDeclListNode(List<AstNode> listOfFuncDeclNodes) {
		for (AstNode child : listOfFuncDeclNodes)
			this.addChild(child);
	}

	public void setFuncDeclNode(AstNode type, AstNode id, AstNode paramList) {
		this.addChild(type);
		this.addChild(id);
		this.addChild(paramList);
	}

	public void setFuncDefListNode(List<AstNode> listOfFuncDefNodes) {
		for (AstNode child : listOfFuncDefNodes)
			this.addChild(child);
	}

	public void setFuncDefNode(AstNode funcHeadNode, AstNode statBlock) {
		this.addChild(funcHeadNode);
		this.addChild(statBlock);
	}

	public void setFuncHeadNode(AstNode type, AstNode classIdOrFuncId, AstNode scIDorNull, AstNode paramList) {
		this.addChild(type);
		this.addChild(classIdOrFuncId);
		this.addChild(scIDorNull);
		this.addChild(paramList);
	}

	public void setGetStatNode(Token token, AstNode var, AstNode passNull) {
		this.setDataToken(token);
		this.addChild(var);
	}

	public void setIdNode(Token dataToken, Token typeToken) {
		this.setDataToken(dataToken);
		this.setTypeToken(typeToken);
	}

	public void setIfStatNode(Token token, AstNode relExpr, AstNode ifStatBlock, AstNode elseStatBlock) {
		this.setDataToken(token);
		this.addChild(relExpr);
		this.addChild(ifStatBlock);
		this.addChild(elseStatBlock);
	}

	public void setIndexListNode(List<AstNode> arithExprList) {

		for (AstNode arithExpr : arithExprList)
			this.addChild(arithExpr);
	}

	public void setInherList(List<AstNode> listOfIds) {
		for (AstNode child : listOfIds)
			this.addChild(child);
	}

	public void setMultOpNode(Token dateToken, AstNode leftChildTrem, AstNode rightChildFactor) {
		this.setDataToken(dateToken);
		this.addChild(leftChildTrem);
		this.addChild(rightChildFactor);
	}

	public void setNotNode(Token token, AstNode factor) {
		this.setDataToken(token);
		this.addChild(factor);

	}

	public void setNumNode(Token dataToken) {
		this.setDataToken(dataToken);
	}

	public void setParamListNode(List<AstNode> listOfParamNodes) {
		for (AstNode child : listOfParamNodes)
			this.addChild(child);
	}

	public void setProgNode(AstNode classlist, AstNode funcdeflist, AstNode statblock) {
		this.addChild(classlist);
		this.addChild(funcdeflist);
		this.addChild(statblock);
	}

	public void setPutStatNode(Token token, AstNode expr, AstNode passNull) {
		this.setDataToken(token);
		this.addChild(expr);
	}

	public void setRelExprNode(Token token, AstNode leftExpr, AstNode rightExpr) {
		this.setDataToken(token);
		this.addChild(leftExpr);
		this.addChild(rightExpr);
	}

	public void setReturnStatNode(Token token, AstNode expr, AstNode passNull) {
		this.setDataToken(token);
		this.addChild(expr);
	}

	public void setSignNode(Token token, AstNode factor) {
		this.setDataToken(token);
		this.addChild(factor);
	}

	public void setStatBlockNode(List<AstNode> listOfStatOrVarDeclNodes) {
		for (AstNode child : listOfStatOrVarDeclNodes)
			this.addChild(child);
	}

	public void setTermNode(List<AstNode> listAstNode) {
		for (AstNode child : listAstNode)
			this.addChild(child);

	}

	public void setTypeNode(Token dataToken) {
		this.setDataToken(dataToken);
	}

	public void setVarDeclListNode(List<AstNode> listOfVarDecl) {
		for (AstNode child : listOfVarDecl)
			this.addChild(child);
	}

	public void setVarDeclNode(AstNode type, AstNode id, AstNode dimList) {
		this.addChild(type);
		this.addChild(id);
		this.addChild(dimList);
	}

	public void setVarElementNode(AstNode dataMember, AstNode fCall) {
		this.addChild(dataMember);
		this.addChild(fCall);

	}

	public void setVarNode(List<AstNode> varElementsList) {

		for (AstNode varElement : varElementsList)
			this.addChild(varElement);
	}
	
	public void setIdNode(Token dataToken) {
		this.setDataToken(dataToken);
	}
	
	public void setExprNode(AstNode arithOrRelExpr) {
		this.addChild(arithOrRelExpr);
	}

	public void setProgramBlockNode(List<AstNode> listOfStatOrVarDeclNodes) {
		for (AstNode child : listOfStatOrVarDeclNodes)
			this.addChild(child);
	}
	
	 /**
     * Every class that will be visited needs an accept method, which 
     * then calls the specific visit method in the visitor, achieving
     * double dispatch. 
     */    
    public void accept(VisitorBase visitor) {
		visitor.visit(this);
	}
}
