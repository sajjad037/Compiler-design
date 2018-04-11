package com.project.compiler.Model;

import com.project.compiler.Config.Enums.TokenType;

/**
 * Model Class for Token
 * 
 * @author SajjadAshrafCan
 *
 */

public class Token {

	private String description;
	private TokenType type;
	private String value;
	private int line;
	private int column;

	/**
	 * Default Constructor
	 */
	public Token() {

	}

	/**
	 * Copy Constructor
	 * 
	 * @param name
	 * @param description
	 * @param type
	 * @param token
	 * @param value
	 * @param line
	 * @param column
	 */
	public Token(String description, TokenType type, String value, int line, int column) {
		super();
		// this.name = name;
		this.description = description;
		this.type = type;
		this.value = value;
		this.line = line;
		this.column = column;
	}

	/**
	 * Copy Constructor
	 * 
	 * @param line
	 * @param value
	 * @param description
	 * @param type
	 */
	public Token(int line, String value, String description, TokenType type) {
		this.line = line;
		this.value = value;
		this.description = description;
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the type
	 */
	public TokenType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TokenType type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(int line) {
		this.line = line;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Token [description=" + description + ", type=" + type + ", value=" + value + ", line=" + line
				+ ", column=" + column + "]";
	}

	
}
