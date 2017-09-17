package io.github.woodenbell.pprint;

/**
 * Interface to be implemented by objects that support prettyPrint.
 * 
 * @author WoodenBell
 * @see io.github.woodenbell.pprint.ObjectPrint
 */

public interface PrettyPrintable {

	/**
	 * Used to tell if the object supports recursive printing.
	 * 
	 * @return If the object supports recursive printing.
	 * @see io.github.woodenbell.pprint.ObjectPrint#prettyPrintRecursive(PrettyPrintable)
	 */

	public boolean ppIsRecursive();

	/**
	 * Used to tell if the object uses keys or number indexes when enumerated is
	 * used.
	 * 
	 * @return If the object uses keys when pretty printing.
	 */

	public boolean ppHasKeys();

	/**
	 * Method to return the object printing keys.
	 * 
	 * @return The keys of object's elements.
	 */

	public Object[] ppGetKeys();

	/**
	 * Method to return the object printing values.
	 * 
	 * @return The keys of object's values.
	 */

	public Object[] ppGetValues();
}
