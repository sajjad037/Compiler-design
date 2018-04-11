package com.project.compiler.Semantic;

import com.project.compiler.Config.Enums.ModuleType;
import com.project.compiler.Model.Token;

public class Errors {
	
	private int lineNumber;
	private String description;
	private String tag;
	private ModuleType errorType;
	private Token token;
	
	/**
	 * @param lineNumber
	 * @param description
	 * @param errorType
	 */
	public Errors(int lineNumber, String description, String tag, ModuleType errorType) {
		super();
		this.lineNumber = lineNumber;
		this.description = description;
		this.tag = tag;
		this.errorType = errorType;
	}
	/**
	 * @param lineNumber
	 * @param description
	 * @param errorType
	 * @param token
	 */
	public Errors(int lineNumber, String description, String tag, ModuleType errorType, Token token) {
		super();
		this.lineNumber = lineNumber;
		this.description = description;
		this.tag = tag;
		this.errorType = errorType;
		this.token = token;
	}
	/**
	 * @return the lineNumber
	 */
	public int getLineNumber() {
		return lineNumber;
	}
	/**
	 * @param lineNumber the lineNumber to set
	 */
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the errorType
	 */
	public ModuleType getErrorType() {
		return errorType;
	}
	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(ModuleType errorType) {
		this.errorType = errorType;
	}
	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(Token token) {
		this.token = token;
	}
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	

}

