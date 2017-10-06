package io.github.woodenbell.pprint;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Contains pprint methods that print in a simple format, similar to Python
 * representation of data structures.
 * 
 * @author Gabriel C.
 * @since 1.1
 */

public class SimplePrint {

	/**
	 * Prints an object array in a simple format.
	 * 
	 * @param p
	 *            The object array.
	 */

	public static void pprint(Object[] p) {

		if (p == null)
			return;

		System.out.println("[" + Util.toListWithCommas(p) + "]");
	}

	/**
	 * Prints a List collection in a simple format.
	 * 
	 * @param p
	 *            The List object.
	 * 
	 * @see java.util.List
	 */

	public static void pprint(List<?> p) {

		if (p == null)
			return;

		System.out.println("[" + Util.toListWithCommas(p.toArray()) + "]");

	}

	/**
	 * Prints a Set collection in a simple format.
	 * 
	 * @param p
	 *            The Set object.
	 * @see java.util.Set
	 */

	public static void pprint(Set<?> p) {

		if (p == null)
			return;

		System.out.println("{" + Util.toListWithCommas(p.toArray()) + "}");
	}

	/**
	 * Prints a Queue collection in a simple format.
	 * 
	 * @param p
	 *            The Queue object.
	 * @see java.util.Queue
	 */

	public static void pprint(Queue<?> p) {

		if (p == null)
			return;

		System.out.println("[" + Util.toListWithCommas(p.toArray()) + "]");
	}

	/**
	 * Prints a Map collection in a simple format.
	 * 
	 * @param p
	 *            The map object.
	 * @see java.util.Map
	 */

	public static void pprint(Map<?, ?> p) {

		if (p == null)
			return;

		System.out.println("{\n" + Util.toKeyValListWithCommas(p.keySet().toArray(), p.values().toArray()) + "\n}");

	}

}
