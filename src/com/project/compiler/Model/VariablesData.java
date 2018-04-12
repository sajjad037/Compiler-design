package com.project.compiler.Model;

import com.project.compiler.Config.Enums;
import com.project.compiler.Config.Enums.NodeType;

public class VariablesData {
	String name;
	String type;
	Token nameToken;
	Token typeToken;
	//Enums.NodeType nodeType;	
	
	/**
	 * 
	 */
	public VariablesData() {
		super();
	}
	/**
	 * @param name
	 * @param type
	 * @param nameToken
	 */
	public VariablesData(String name, String type, Token nameToken) {
		super();
		this.name = name;
		this.type = type;
		this.nameToken = nameToken;
	}
	/**
	 * @param name
	 * @param type
	 * @param nameToken
	 * @param typeToken
	 * @param nodeType
	 */
	public VariablesData(String name, String type, Token nameToken, Token typeToken) {
		super();
		this.name = name;
		this.type = type;
		this.nameToken = nameToken;
		this.typeToken = typeToken;
		//this.nodeType = nodeType;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the nameToken
	 */
	public Token getNameToken() {
		return nameToken;
	}
	/**
	 * @param nameToken the nameToken to set
	 */
	public void setNameToken(Token nameToken) {
		this.nameToken = nameToken;
	}
	/**
	 * @return the typeToken
	 */
	public Token getTypeToken() {
		return typeToken;
	}
	/**
	 * @param typeToken the typeToken to set
	 */
	public void setTypeToken(Token typeToken) {
		this.typeToken = typeToken;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VariablesData [name=" + name + ", type=" + type + ", nameToken=" + nameToken + ", typeToken="
				+ typeToken + "]";
	}
//	/**
//	 * @return the nodeType
//	 */
//	public Enums.NodeType getNodeType() {
//		return nodeType;
//	}
//	/**
//	 * @param nodeType the nodeType to set
//	 */
//	public void setNodeType(Enums.NodeType nodeType) {
//		this.nodeType = nodeType;
//	}
//	
	
	

}
