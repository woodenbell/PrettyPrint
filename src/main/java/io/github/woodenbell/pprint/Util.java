package io.github.woodenbell.pprint;

/**
 * Utility class for the package.
 * 
 * @author Gabriel C.
 */

public class Util {

	/**
	 * Enumeration representing table printing formats.
	 * 
	 * @author WoodenBell
	 */

	public static enum TableFormat {

		/**
		 * UNDERSCORE TableFormat Top/Bottom border character: _ Right/Left border and
		 * division character: |
		 */

		UNDERSCORE('_', '|'),

		/**
		 * HYPHEN TableFormat Top/Bottom border character: - Right/Left border and
		 * division character: |
		 */

		HYPHEN('-', '|'),

		/**
		 * EQUALS TableFormat Top/Bottom border character: = Right/Left border and
		 * division character: |
		 */

		EQUALS('=', '|');

		char border, division;

		/**
		 * Creates a table format with given border and division.
		 * 
		 * @param border
		 *            The character to be used as top and bottom border.
		 * @param division
		 *            The character to be used as division to the elements.
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

	protected static String makePadding(char c, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= times; i++)
			sb.append(c);
		return sb.toString();
	}

	/**
	 * Creates a comma separated list.
	 * @param l The array that will be used to create the list string.
	 * @return The list string.
	 * @since 1.1
	 */

	protected static String toListWithCommas(Object[] l) {

		StringBuilder sb = new StringBuilder();

		for (Object o : l) {
			
			if (o == null) {
				sb.append("null, ");
			} else if(o instanceof String) {
				sb.append("\"" + o.toString() + "\", ");
			} else {
				sb.append(o.toString() + ", ");
			}
				
		}

		return sb.substring(0, sb.length() - 2);
		
	}

	/**
	 * Creates a key-value pair list represented in string.
	 * 
	 * @param ks
	 *            The key array that will be used to create the key-value list.
	 * @param vs
	 *            The value array that will be used to create the key-value list.
	 * @return The string key-value pair list, separated by newline characters.
	 * @since 1.1
	 */

	protected static String toKeyValListWithCommas(Object[] ks, Object[] vs) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ks.length; i++) {

			if (ks[i] == null) {
				sb.append("null: ");
			} else if (ks[i] instanceof String) {
				sb.append("\"" + ks[i].toString() + "\": ");
			} else {
				sb.append(ks[i].toString() + ": ");
			}

			if (vs[i] == null) {
				sb.append("null,\n");
			} else if (vs[i] instanceof String) {
				sb.append("\"" + vs[i].toString() + "\",\n");
			} else {
				sb.append(vs[i].toString() + ",\n");
			}

		}

		return sb.substring(0, sb.length() - 2);

	}
}
