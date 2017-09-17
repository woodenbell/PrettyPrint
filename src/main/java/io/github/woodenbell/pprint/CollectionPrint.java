package io.github.woodenbell.pprint;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * PrettyPrint class for collections.
 * 
 * @author WoodenBell
 * @version 1.0
 *
 */

public class CollectionPrint {

	/**
	 * Overloaded prettyPrint for List collection with default options.
	 * 
	 * @param p
	 *            The List object.
	 */

	public static void prettyPrint(List<?> p) {
		prettyPrint(p, false, false, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for List collection with enumeration option.
	 * 
	 * @param p
	 *            The List object.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 */

	public static void prettyPrint(List<?> p, boolean enumerated) {
		prettyPrint(p, false, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for List collection with option to default table
	 * format.
	 * 
	 * @param p
	 *            The List object.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 * @see io.github.woodenbell.pprint.Util.TableFormat#UNDERSCORE
	 */

	public static void prettyPrint(List<?> p, boolean tableLike, boolean enumerated) {
		prettyPrint(p, tableLike, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Prints a List collection with options to show it's elements.
	 * 
	 * @param p
	 *            The List object.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 * @param tableFormat
	 *            The table format.
	 * @see io.github.woodenbell.pprint.Util.TableFormat
	 * @see java.util.List
	 */

	public static void prettyPrint(List<?> p, boolean tableLike, boolean enumerated, Util.TableFormat tableFormat) {

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

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 4 + (p.size() + "").length()));

				for (Object o : p) {

					System.out.printf("%1s%-" + ((p.size() + "").length() + 1) + "s%1s", tableFormat.division, i,
							tableFormat.division);
					System.out.printf("%-" + maxCharLen + "s%1s\n", o.toString(), tableFormat.division);

					i++;
				}

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 4 + (p.size() + "").length()));

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
					System.out.printf("%-" + ((p.size() + "").length() + 2) + "s", i + ": ");
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

	/**
	 * Overloaded prettyPrint for Map collection with default options.
	 * 
	 * @param p
	 *            The Map object.
	 */

	public static void prettyPrint(Map<?, ?> p) {
		prettyPrint(p, false, false, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for Map collection with key printing option.
	 * 
	 * @param p
	 *            The Map object.
	 * @param withKeys
	 *            If the key corresponding each element should be printed.
	 */

	public static void prettyPrint(Map<?, ?> p, boolean withKeys) {
		prettyPrint(p, false, withKeys, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for Map collection with option to default table
	 * format.
	 * 
	 * @param p
	 *            The Map object.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param withKeys
	 *            If the key corresponding each element should be printed.
	 * @see io.github.woodenbell.pprint.Util.TableFormat#UNDERSCORE
	 */

	public static void prettyPrint(Map<?, ?> p, boolean tableLike, boolean withKeys) {
		prettyPrint(p, tableLike, withKeys, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Prints a Map collection with options to show it's elements.
	 * 
	 * @param p
	 *            The Map object.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param withKeys
	 *            If the key corresponding each element should be printed.
	 * @param tableFormat
	 *            The table format.
	 * @see io.github.woodenbell.pprint.Util.TableFormat
	 * @see java.util.Map
	 */

	public static void prettyPrint(Map<?, ?> p, boolean tableLike, boolean withKeys, Util.TableFormat tableFormat) {

		if (p == null)
			return;

		if (withKeys && p.containsKey(null))
			return;
		if (p.containsValue(null))
			return;

		int maxCharLenKeys = 0;
		int maxCharLenVals = 0;

		Iterator<?> iterKeys = p.keySet().iterator();
		Iterator<?> iterVals = p.values().iterator();
		Object curr;

		while (withKeys && iterKeys.hasNext()) {
			curr = iterKeys.next();
			if (curr.toString().length() > maxCharLenKeys)
				maxCharLenKeys = curr.toString().length();
		}

		while (iterVals.hasNext()) {
			curr = iterVals.next();
			if (curr.toString().length() > maxCharLenVals)
				maxCharLenVals = curr.toString().length();
		}

		iterKeys = p.keySet().iterator();

		if (tableLike) {

			if (withKeys) {

				System.out.println(Util.makePadding(tableFormat.border, maxCharLenVals + maxCharLenKeys + 4));

				while (iterKeys.hasNext()) {

					curr = iterKeys.next();

					System.out.printf("%1s%-" + maxCharLenKeys + "s%1s", tableFormat.division, curr.toString(),
							tableFormat.division);
					System.out.printf("%-" + maxCharLenVals + "s%1s\n", p.get(curr).toString(), tableFormat.division);

				}

				System.out.println(Util.makePadding(tableFormat.border, maxCharLenVals + maxCharLenKeys + 4));

			} else {

				System.out.println(Util.makePadding(tableFormat.border, maxCharLenKeys + 2));

				while (iterKeys.hasNext()) {

					curr = iterKeys.next();

					System.out.printf("%1s%-" + maxCharLenKeys + "s%1s\n", tableFormat.division, p.get(curr).toString(),
							tableFormat.division);
				}

				System.out.println(Util.makePadding(tableFormat.border, maxCharLenVals + 2));

			}
		} else {

			if (withKeys) {

				while (iterKeys.hasNext()) {

					curr = iterKeys.next();

					System.out.printf("%-" + (maxCharLenKeys + 2) + "s", curr.toString() + ": ");
					System.out.printf("%-" + maxCharLenVals + "s\n", p.get(curr).toString());
				}

			} else {

				while (iterKeys.hasNext()) {

					curr = iterKeys.next();
					System.out.printf("%-" + maxCharLenVals + "s\n", p.get(curr).toString());
				}
			}
		}
	}

	/**
	 * Overloaded prettyPrint for Set collection with default options.
	 * 
	 * @param p
	 *            The Set object.
	 */

	public static void prettyPrint(Set<?> p) {
		prettyPrint(p, false, false, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for Set collection with enumeration option.
	 * 
	 * @param p
	 *            The Set object.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 */

	public static void prettyPrint(Set<?> p, boolean enumerated) {
		prettyPrint(p, false, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for Set collection with option to default table
	 * format.
	 * 
	 * @param p
	 *            The Set object.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 * @see io.github.woodenbell.pprint.Util.TableFormat#UNDERSCORE
	 */

	public static void prettyPrint(Set<?> p, boolean tableLike, boolean enumerated) {
		prettyPrint(p, tableLike, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Prints a Set collection with options to show it's elements.
	 * 
	 * @param p
	 *            The Set object.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 * @param tableFormat
	 *            The table format.
	 * @see io.github.woodenbell.pprint.Util.TableFormat
	 * @see java.util.Set
	 */

	public static void prettyPrint(Set<?> p, boolean tableLike, boolean enumerated, Util.TableFormat tableFormat) {

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

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 4 + (p.size() + "").length()));

				for (Object o : p) {

					System.out.printf("%1s%-" + ((p.size() + "").length() + 1) + "s%1s", tableFormat.division, i,
							tableFormat.division);
					System.out.printf("%-" + maxCharLen + "s%1s\n", o.toString(), tableFormat.division);

					i++;
				}

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 4 + (p.size() + "").length()));

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
					System.out.printf("%-" + ((p.size() + "").length() + 2) + "s", i + ": ");
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

	/**
	 * Overloaded prettyPrint for Queue collection with default options.
	 * 
	 * @param p
	 *            The Queue object.
	 */

	public static void prettyPrint(Queue<?> p) {
		prettyPrint(p, false, false, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for Queue collection with enumeration option.
	 * 
	 * @param p
	 *            The Queue object.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 */

	public static void prettyPrint(Queue<?> p, boolean enumerated) {
		prettyPrint(p, false, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Overloaded prettyPrint for Queue collection with option to default table
	 * format.
	 * 
	 * @param p
	 *            The Queue object.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 * @see io.github.woodenbell.pprint.Util.TableFormat#UNDERSCORE
	 */

	public static void prettyPrint(Queue<?> p, boolean tableLike, boolean enumerated) {
		prettyPrint(p, tableLike, enumerated, Util.TableFormat.UNDERSCORE);
	}

	/**
	 * Prints a Queue collection with options to show it's elements.
	 * 
	 * @param p
	 *            The Queue object.
	 * @param tableLike
	 *            If the collection should be printed in a table format.
	 * @param enumerated
	 *            If the index of each element should be printed.
	 * @param tableFormat
	 *            The table format.
	 * @see io.github.woodenbell.pprint.Util.TableFormat
	 * @see java.util.Queue
	 */

	public static void prettyPrint(Queue<?> p, boolean tableLike, boolean enumerated, Util.TableFormat tableFormat) {

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

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 4 + (p.size() + "").length()));

				for (Object o : p) {

					System.out.printf("%1s%-" + ((p.size() + "").length() + 1) + "s%1s", tableFormat.division, i,
							tableFormat.division);
					System.out.printf("%-" + maxCharLen + "s%1s\n", o.toString(), tableFormat.division);

					i++;
				}

				System.out.println(Util.makePadding(tableFormat.border, maxCharLen + 4 + (p.size() + "").length()));

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
					System.out.printf("%-" + ((p.size() + "").length() + 2) + "s", i + ": ");
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