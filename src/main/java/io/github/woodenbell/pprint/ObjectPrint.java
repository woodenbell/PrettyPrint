package io.github.woodenbell.pprint;

/*
* Copyright 2017 WoodenBell
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/

/**
 * Class with methods to print objects that are not collections.
 * 
 * @author WoodenBell
 * @version 1.0
 */

public class ObjectPrint {

	/**
	 * Overloaded prettyPrint for object with default options.
	 * 
	 * @param p
	 *            The object that implements PrettyPrintable.
	 */
	public static void prettyPrint(PrettyPrintable p) {
		prettyPrint(p, false, false, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for object with enumeration/key option.
	 * 
	 * @param p
	 *            The object that implements PrettyPrintable.
	 * @param enumerated
	 *            If the index (or keys) should be included before each element.
	 */

	public static void prettyPrint(PrettyPrintable p, boolean enumerated) {
		prettyPrint(p, false, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for object with option to default table format.
	 * 
	 * @param p
	 *            The object that implements PrettyPrintable.
	 * @param enumerated
	 *            If the index (or keys) should be included before each element.
	 * @param tableLike
	 *            If the object should be printed in a table format.
	 * @see io.github.woodenbell.pprint.Util.TableFormat#UNDERSCORE
	 */

	public static void prettyPrint(PrettyPrintable p, boolean tableLike, boolean enumerated) {
		prettyPrint(p, tableLike, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * PrettyPrint method for object.
	 * 
	 * @param p
	 *            The object that implements PrettyPrintable.
	 * @param enumerated
	 *            If the index (or keys) should be included before each element.
	 * @param tableLike
	 *            If the object should be printed in a table format.
	 * @param tableFormat
	 *            The table format.
	 * @see io.github.woodenbell.pprint.Util.TableFormat
	 * @see io.github.woodenbell.pprint.PrettyPrintable
	 */

	public static void prettyPrint(PrettyPrintable p, boolean tableLike, boolean enumerated,
			Util.TableFormat tableFormat) {

		if (p == null)
			return;

		if (p.ppGetValues() == null && (p.ppHasKeys() && p.ppGetKeys() == null))
			return;

		if (p.ppHasKeys()) {
			for (Object ob : p.ppGetKeys()) {
				if (ob == null)
					return;
			}
		}

		for (Object ob : p.ppGetValues()) {
			if (ob == null)
				return;
		}

		int maxCharLenKeys = 0;
		int maxCharLenVals = 0;

		for (Object obj : p.ppGetValues()) {
			if (obj.toString().length() > maxCharLenVals)
				maxCharLenVals = obj.toString().length();
		}

		if (enumerated && p.ppHasKeys()) {
			for (Object obj : p.ppGetKeys()) {
				if (obj.toString().length() > maxCharLenKeys)
					maxCharLenKeys = obj.toString().length();
			}
		}

		if (tableLike) {

			if (enumerated) {

				if (p.ppHasKeys()) {

					System.out.println(Util.makePadding(tableFormat.border, maxCharLenKeys + maxCharLenVals + 4));

					int i = 0;
					for (Object o : p.ppGetValues()) {
						System.out.printf("%1s%-" + (maxCharLenKeys + 1) + "s%1s", tableFormat.division,
								p.ppGetKeys()[i], tableFormat.division);
						System.out.printf("%-" + maxCharLenVals + "s%1s\n", o.toString(), tableFormat.division);
						i++;
					}

					System.out.println(Util.makePadding(tableFormat.border, maxCharLenKeys + maxCharLenVals + 4));

				} else {

					int i = 0;

					System.out.println(Util.makePadding(tableFormat.border,
							maxCharLenVals + 4 + (p.ppGetValues().length + "").length()));

					for (Object o : p.ppGetValues()) {

						System.out.printf("%1s%-" + ((p.ppGetValues().length + "").length() + 1) + "s%1s",
								tableFormat.division, i, tableFormat.division);
						System.out.printf("%-" + maxCharLenVals + "s%1s\n", o.toString(), tableFormat.division);

						i++;
					}

					System.out.println(Util.makePadding(tableFormat.border,
							maxCharLenVals + 4 + (p.ppGetValues().length + "").length()));
				}

			} else {

				System.out.println(Util.makePadding(tableFormat.border, maxCharLenVals + 3));

				for (Object o : p.ppGetValues()) {
					System.out.printf("%1s%-" + maxCharLenVals + "s%1s\n", tableFormat.division, o.toString(),
							tableFormat.division);
				}

				System.out.println(Util.makePadding(tableFormat.border, maxCharLenVals + 3));

			}
		} else {

			if (enumerated) {

				if (p.ppHasKeys()) {
					int i = 0;
					for (Object o : p.ppGetValues()) {
						System.out.printf("%-" + (maxCharLenKeys + 2) + "s", p.ppGetKeys()[i].toString() + ": ");
						System.out.printf("%-" + maxCharLenVals + "s\n", o.toString());
						i++;
					}

				} else {
					int i = 0;
					for (Object o : p.ppGetValues()) {
						System.out.printf("%-" + ((p.ppGetValues().length + "").length() + 1 + 2) + "s", i + ": ");
						System.out.printf("%-" + maxCharLenVals + "s\n", o.toString());
						i++;
					}
				}

			} else {
				for (Object o : p.ppGetValues()) {
					System.out.printf("%-" + maxCharLenVals + "s\n", o.toString());
				}
			}
		}
	}

	/**
	 * 
	 * 
	 * /** Overloaded prettyPrintRecursive to PrettyPrintable objects with default
	 * padding character.
	 * 
	 * @param p
	 *            The PrettyPrintable object.
	 */

	public static void prettyPrintRecursive(PrettyPrintable p) {
		prettyPrintRecursive(p, '-', 0);
	}

	/**
	 * Prints the PretttyPrintable object applying prettyPrint to PrettyPrintable
	 * fields.
	 * 
	 * @param p
	 *            The PrettyPrintable object.
	 * 
	 * @param paddingChar
	 *            The character used in padding to indicate lower levels in the
	 *            printing structure.
	 * 
	 * @see io.github.woodenbell.pprint.PrettyPrintable
	 */

	public static void prettyPrintRecursive(PrettyPrintable p, char paddingChar) {
		prettyPrintRecursive(p, paddingChar, 0);
	}

	/**
	 * Invisible overload of prettyPrintRecursion used in recursion to print the
	 * padding based in depth.
	 * 
	 * @param p
	 *            The PrettyPrintable object.
	 * @param paddingChar
	 *            The character used in padding to indicate lower levels in the
	 *            printing structure.
	 * @param depth
	 *            The recursion depth, used for padding.
	 */

	private static void prettyPrintRecursive(PrettyPrintable p, char paddingChar, int depth) {

		if (p == null || !p.ppIsRecursive() || (p.ppHasKeys() && p.ppGetKeys() == null) || p.ppGetValues() == null)
			return;

		if (p.ppHasKeys()) {
			for (Object ob : p.ppGetKeys()) {
				if (ob == null)
					return;
			}
		}

		for (Object ob : p.ppGetValues()) {
			if (ob == null)
				return;
		}

		int maxCharLenKeys = 0;

		if (p.ppHasKeys()) {
			for (Object obj : p.ppGetKeys()) {
				if (obj.toString().length() > maxCharLenKeys)
					maxCharLenKeys = obj.toString().length();
			}
		}

		if (p.ppHasKeys()) {

			int i = 0;

			for (Object o : p.ppGetValues()) {

				System.out.print(Util.makePadding(paddingChar, depth * 4));

				System.out.printf("%-" + (maxCharLenKeys + 2) + "s", p.ppGetKeys()[i].toString() + ": ");

				if (o instanceof PrettyPrintable) {

					System.out.println();
					prettyPrintRecursive((PrettyPrintable) o, paddingChar, depth + 1);

				} else
					System.out.println(o.toString());

				i++;
			}

		} else {

			int i = 0;

			for (Object o : p.ppGetValues()) {

				System.out.print(Util.makePadding(paddingChar, depth * 4));

				System.out.printf("%-" + ((p.ppGetValues().length + "").length() + 1 + 2) + "s", i + ": ");

				if (o instanceof PrettyPrintable) {

					System.out.println();
					prettyPrintRecursive((PrettyPrintable) o, paddingChar, depth + 1);

				} else
					System.out.println(o.toString());

				i++;
			}
		}
	}

	/**
	 * Overloaded prettyPrint for Object array with default options.
	 * 
	 * @param p
	 *            The object array.
	 */

	public static void prettyPrint(Object[] p) {
		prettyPrint(p, false, false, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for Object array with enumeration option.
	 * 
	 * @param p
	 *            The Object array.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 */

	public static void prettyPrint(Object[] p, boolean enumerated) {
		prettyPrint(p, false, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for Object array with option to default table format.
	 * 
	 * @param p
	 *            The Object array.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 * @see io.github.woodenbell.pprint.Util.TableFormat#UNDERSCORE
	 */

	public static void prettyPrint(Object[] p, boolean tableLike, boolean enumerated) {
		prettyPrint(p, tableLike, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Prints a Object array with options to show it's elements.
	 * 
	 * @param p
	 *            The Object array.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 * @param tableFormat
	 *            The table format.
	 * @see io.github.woodenbell.pprint.Util.TableFormat
	 */

	public static void prettyPrint(Object[] p, boolean tableLike, boolean enumerated, Util.TableFormat tableFormat) {

		if (p == null)
			return;

		for (Object ob : p) {
			if (ob == null)
				return;
		}

		int maxCharLen = 0;

		for (Object obj : p) {
			if (obj.toString().length() > maxCharLen)
				maxCharLen = obj.toString().length();
		}

		if (tableLike) {
			if (enumerated) {

				int i = 0;

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 4 + (p.length + "").length()));

				for (Object o : p) {

					System.out.printf("%1s%-" + ((p.length + "").length() + 1) + "s%1s", tableFormat.division, i,
							tableFormat.division);
					System.out.printf("%-" + maxCharLen + "s%1s\n", o.toString(), tableFormat.division);

					i++;
				}

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 4 + (p.length + "").length()));

			} else {

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 2));

				for (Object o : p) {
					System.out.printf("%1s%-" + maxCharLen + "s%1s\n", tableFormat.division, o.toString(),
							tableFormat.division);
				}

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 2));

			}
		} else {
			if (enumerated) {
				int i = 0;
				for (Object o : p) {
					System.out.printf("%-" + ((p.length + "").length() + 2) + "s", i + ": ");
					System.out.printf("%-" + maxCharLen + "s\n", o.toString());
					i++;
				}
			} else {
				for (Object o : p) {
					System.out.printf("%-" + maxCharLen + "s\n", o.toString());
				}
			}
		}
	}
}
