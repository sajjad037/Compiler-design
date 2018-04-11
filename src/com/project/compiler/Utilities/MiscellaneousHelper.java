package com.project.compiler.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this class is a miscellaneous helper class. It contains some extra frequently
 * used Function which is mostly used independently
 * 
 * @author SajjadAshrafCan
 * 
 */
public class MiscellaneousHelper {

	/**
	 * 
	 * Retrun ture when string value is double
	 * 
	 * @param new_value
	 *            a String
	 * @return booleran
	 */
	public boolean isDouble(String new_value) {
		try {
			Double.parseDouble(new_value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * this method removes character from start to left
	 * 
	 * @param new_inputString
	 *            input string
	 * @param new_characters
	 *            characters to be removed
	 * @return string the result
	 */
	public String RemoveCharacterFromStrartorLeft(String new_inputString, String new_characters) {
		return new_inputString.replaceAll("^" + new_characters + "+", "");
		// return InputString.replaceAll("^0+(?!$)", "");
	}

	/**
	 * the method removes character from end or right
	 * 
	 * @param new_inputString
	 *            input string
	 * @param new_characters
	 *            characters to be removed
	 * @return string the result
	 */
	public String RemoveCharacterFromEndorRight(String new_inputString, String new_characters) {
		return new_inputString.replaceAll("\\" + new_characters + "+$", "");
	}

	/**
	 * this method removes character from both end
	 * 
	 * @param new_inputString
	 *            input string
	 * @param new_characters
	 *            characters to be removed
	 * @return string the result
	 */
	public String RemoveCharacterFromBothEnd(String new_inputString, String new_characters) {
		return new_inputString.replaceAll("^\\" + new_characters + "+|\\" + new_characters + "+$", "");
	}

	/**
	 * This method return date in string form. The date format is depend upon
	 * the input Provied by user.
	 * 
	 * @param new_format
	 *            new format
	 * @return date
	 */
	public String getCurrentDateStr(String new_format) {
		return new SimpleDateFormat(new_format).format(new Date());
	}

}
