package com.project.compiler.Driver;

import java.util.ArrayList;

import com.project.compiler.Config.Enums.TokenPrintFormat;
import com.project.compiler.Model.Token;
import com.project.compiler.Semantic.Errors;

/**
 * Base call for driver classes
 * @author Sajjad Ashraf
 *
 */
public abstract class baseDriver {
	
	
	abstract void execute();

	
	/**
	 * Get Formated Erros
	 * @param errLst
	 * @return
	 */
	protected String getFormatedErros(ArrayList<Errors> errLst)
	{
		StringBuilder sb = new StringBuilder();
	
		for (Errors e : errLst) {
			sb.append(String.format("[%5s]", e.getTag()) 
					 + "\t" + String.format("%5s", e.getErrorType().toString())
					+ "\t" + String.format("Line # : %5s", ""+e.getLineNumber()) + 
					"\t" + String.format("%s", e.getDescription()) +"\t\r\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * Converts Tokens Lists into Printable form.
	 * 
	 * @param TokenTitle
	 * @param tokens
	 * @return
	 */
	protected String printTokens(String TokenTitle, ArrayList<Token> tokens, TokenPrintFormat printFormat) {
		StringBuilder stringBuilder = new StringBuilder();
		int index = 0;
		switch (printFormat) {
		case ATOCC:
			for (Token t : tokens) {
				stringBuilder.append(String.format("%s+", t.getDescription()));
			}
			break;
			
		case REQUIRED:
			stringBuilder.append(
					"----------------------------------- " + TokenTitle + " ---------------------------------------\n");
			stringBuilder.append(
					String.format("%3s", "S.#") + "\t" + String.format("%43s", "Type") + "\t" + String.format("%20s", "Value") + "\t"  + String.format("%5s", "Line#")+"\t\n");
			stringBuilder.append("----------------------------------------------------------------------------------\n");
			for (Token t : tokens) {
				stringBuilder.append(String.format("%3s", "" + (++index)) 
						 + "\t" + String.format("%43s", t.getDescription())
						+ "\t" + String.format("%20s", t.getValue()) + "\t" + String.format("%5s", t.getLine()) +"\t\n");
			}
			break;	
			
		case ENHANCE:
			stringBuilder.append(
					"----------------------------------- " + TokenTitle + " ---------------------------------------\n");
			stringBuilder.append(
					String.format("%5s", "S.#") + "\t" + String.format("%5s", "Line#") + "\t" + String.format("%5s", "Type")
							+ "\t" + String.format("%43s", "Descrption") + "\t" + String.format("%5s", "Value") + "\t\n");
			stringBuilder.append("----------------------------------------------------------------------------------\n");
			
			for (Token t : tokens) {
				stringBuilder.append(String.format("%5s", "" + (++index)) + "\t" + String.format("%5s", t.getLine()) + "\t"
						+ String.format("%5s", t.getType().toString()) + "\t" + String.format("%43s", t.getDescription())
						+ "\t" + String.format("%5s", t.getValue()) + "\t\n");
			}
			break;	

		default:
			break;
		}
				
		return stringBuilder.toString();
	}

}
