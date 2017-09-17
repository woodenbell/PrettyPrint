package io.github.woodenbell.pprint;

/**
 * Utility class for the package.
 * 
 * @author WoodenBell
 * @version 1.0
 */

public class Util {
	
	/**
	 * Enumeration representing table printing formats.
	 * @author WoodenBell
	 */
	
	public static enum TableFormat {
		
		/**
		 * UNDERSCORE TableFormat
		 * Top/Bottom border character: _
		 * Right/Left border and division character: |
		 */
		
		UNDERSCORE('_', '|'), 
		
		/**
		 * HYPHEN TableFormat
		 * Top/Bottom border character: -
		 * Right/Left border and division character: |
		 */
		
		HYPHEN('-', '|'), 
		
		/**
		 * EQUALS TableFormat
		 * Top/Bottom border character: =
		 * Right/Left border and division character: |
		 */
		
		EQUALS('=', '|');

		char border, division;
		
		/**
		 * Creates a table format with given border and division.
		 * @param border The character to be used as top and bottom border.
		 * @param division The character to be used as division to the elements.
		 */
		
		TableFormat(char border, char division) {
			this.border = border;
			this.division = division;
		}
	}
	
	/**
	 * Generates padding for table printing.
	 * 
	 * @param c
	 *            The character used in padding.
	 * @param times
	 *            The padding size, or the number of times the character will be
	 *            multiplied.
	 * @return A string containing the padding made with the character.
	 */

	public static String makePadding(char c, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= times; i++)
			sb.append(c);
		return sb.toString();
	}
}
