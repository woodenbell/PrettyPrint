### The Java PrettyPrint library
![PPrint icon](https://woodenbell.github.io/static/images/pprint_icon.png)

#### Features of pprint
- Collection printing
- Printing options, like enumeration (or key printing) and table-format
- Custom pretty printing for objects!
- Recursive printing for objects!

**Examples [here](#examples)**
___

## Using prettyPrint
PrettyPrint, independent of the object type, is used in the following forms:

**PPrintClass represents the class corresponding to the print type (Can be either ObjectPrint or CollectionPrint)**
 
`PPrintClass.prettyPrint(object, enumerated, table, tableFormat);`
#### Prints without enumeration (or keys)
`PPrintClass.prettyPrint(someObject);`
    
#### Prints with enumeration
`PPrintClass.prettyPrint(someObject, true);`
    
#### Prints with enumeration and in a table-format
_UNDERSCORE_ table format is used  
`PPrintClass.prettyPrint(someObject, true, true);`
    
##### Prints with enumeration and in spefic table-format
_EQUALS_ table format is used  
`PPrintClass.prettyPrint(someObject, true, true, Util.TableFormat.EQUALS);`
___
## TableFormat options  
**There are 3 options for table format**:

- **Util.TableFormat.UNDERSCORE** 
   **(default TableFormat option)**   
   Top/bottom border char: `_`  
   Right/left border char: `|`
  
- **Util.TableFormat.EQUALS**   
   Top/bottom border char: `=`  
   Right/left border char: `|`
  
- **Util.TableFormat.HYPHEN**   
   Top/bottom border char: `-`  
   Right/left border char: `|`

## Object Printing
To be pretty printed, an object must implement the `PrettyPrintable` interface. It's methods are described below:
- **ppIsRecursive()** - Must return `true` if the object accepts recursive printing, otherwise `false`.
  
- **ppHasKeys()** - Must return `true` if the object will use keys (called when `enumerated` is `true` on `prettyPrint`), also if this methods returns `true` then `ppGetKeys()` cannot return `null`, otherwise the `prettyPrint` method will return immediately (cannot use keys that are `null`). Returning `false` will tell the method to use the index before each value when `enumerated` is `true`.
  
- **ppGetKeys()** - Must return an array of objects that are going to be used as keys, otherwise
returns `null`.
  
- **ppGetValues()** - Must return an array of objects containing the fields of the object that are going to be printed.
  

**Having implemented all the methods, you can print the object just like shown [above](#using-prettyprint), in the following way:**

    import io.github.woodenbell.pprint.ObjectPrint
    
    ObjectPrint.prettyPrint(myobj); //Default printing
    ObjectPrint.prettyRecursivePrint(myObj); //Recursive printing

*PrettyPrint* also supports *Object* array printing, that works the same way as *Sets* described below, by using the *prettyPrint* method of the **ObjectPrint** class.

### Recursive object print
Recursive print is only supported for objects, that must implement `PrettyPrintable`.  
Recursive printing works just like normal printing, except when a field that also implements
`PrettyPrintable` is going to be printed (a field returned by `ppGetValues()`).
The recursive print then goes down one level in depth, jumping one line and padding 4 times the depth with the *padding character*

**Example:**
Value: 5  
Outer:  
----Inner: "padding char -"  
Outer2:  
----Inner2:  
--------Inner3: "depth=2, 2 * 4 = 8 padding chars"  

You can find an example of recursive *prettyprint* in action [here](#recursive-object-printing)

## Collection printing
Collection printing is separed in two types of collections:
- The ones that use keys: **Map**
- The ones that don't: **Set, List and Queue**

Printing a *Map* has the option `withKeys` instead of `enumerated`. The *Sets, Lists and Queues*
will have their indexes printed.  

  
Collections use the **CollectionPrint** class, just like below:

    import io.github.woodenbell.pprint.CollectionPrint
    
    CollectionPrint.prettyPrint(myMap, true); //Prints the map with it's keys and values
    CollectionPrint.prettyPrint(myList, true); //Prints the list elements with their indexes

**Collections unfortunately doesn't support recursive print, by the fact that collections, differently from objects that implement PrettyPrintable, have different ways to get keys and values. One example is Map and Set, because in map an Iterator is needed, while Set can be iterated by a for loop**
___


## Examples

**The following examples are tanken from the tests**

### Object printing
    
    import io.github.woodenbell.pprint.ObjectPrint;
    import io.github.woodenbell.pprint.PrettyPrintable;
    
    
    private static class Person implements PrettyPrintable {
		String name;
		int age;
		String address;

	    Person(String name, int age, String address) {
		    this.name = name;
		    this.age = age;
		    this.address = address;
	    	}

		public boolean ppIsRecursive() {
		    return false;
	    }

		public boolean ppHasKeys() {
		    return true;
	    }

	    public Object[] ppGetKeys() {
		    return new String[] { "Name", "Age", "Address" };
		}

	    public Object[] ppGetValues() {
		    return new Object[] { name, age, address };
	    }

    }
	    
    Person sherlock = new Person("Sherlock Holmes", 37, "Baker Street 221B");
	        
	ObjectPrint.prettyPrint(sherlock);
	    	
	/*
	* Output:
	*
	* Sherlock Holmes  
    * 37               
    * Baker Street 221B
	*/
	    	
	ObjectPrint.prettyPrint(sherlock, true);
	    	
	/*
	* Output:
	*
	* Name: Sherlock Holmes  
    * Age: 37               
    * Address: Baker Street 221B
	*/
	    	
	ObjectPrint.prettyPrint(sherlock, true, true, Util.TableFormat.UNDERSCORE);
	    	
	/*
	* Output:
	* ____________________________
    * |Name    |Sherlock Holmes  |
    * |Age     |37               |
    * |Address |Baker Street 221B|
    * ____________________________
	*/

	    
### Object array printing

    import io.github.woodenbell.pprint.ObjectPrint;
    
    Object[] people = new String[] {"Me", "You", "Everyone else"};
    
    ObjectPrint.prettyPrint(invited);

	/*
	* Me
	* You
	* Everyone else
	*/

	ObjectPrint.prettyPrint(invited, true);
        
    /*
    * 0: Me
    * 1: You
    * 2: Everyone else
    */

	ObjectPrint.prettyPrint(invited, true, true, Util.TableFormat.EQUALS);
	
	/*
	* ==================
    * |1 |Me           |
    * |2 |You          |
    * |3 |Everyone else|
    * ==================
	*/

### Recursive object printing

    import io.github.woodenbell.pprint.ObjectPrint;
    import io.github.woodenbell.pprint.PrettyPrintable;
    import java.util.ArrayList;
    
    // Simulates a JSON-like structure
    
    private static class DataStruct implements PrettyPrintable {

		List<Object> values;
		List<String> keys;
		List<DataStructType> types;

		public DataStruct() {
			keys = new ArrayList<>();
			values = new ArrayList<>();
			types = new ArrayList<>();
		}

		public void addInt(String key, int n) {
			keys.add(key);
			values.add(n);
			types.add(DataStructType.INTEGER);
		}

		public void addString(String key, String str) {
			keys.add(key);
			values.add(str);
			types.add(DataStructType.STRING);
		}

		public void addDataStruct(String key, DataStruct ds) {
			keys.add(key);
			values.add(ds);
			types.add(DataStructType.DATASTRUCT);
		}

		@SuppressWarnings("unused")
		public Integer getInt(String key) {

			int index = -1;

			for (int i = 0; i <= keys.size(); i++) {
				if (keys.get(i).equals(key))
					index = i;
			}

			if (index == -1)
				return null;

			if (types.get(index) == DataStructType.INTEGER)
				return (Integer) values.get(index);
			else
				return null;
		}

		@SuppressWarnings("unused")
		public String getStr(String key) {

			int index = -1;

			for (int i = 0; i <= keys.size(); i++) {
				if (keys.get(i).equals(key))
					index = i;
			}

			if (index == -1)
				return null;

			if (types.get(index) == DataStructType.STRING)
				return (String) values.get(index);
			else
				return null;
		}

		@SuppressWarnings("unused")
		public DataStruct getDataStruct(String key) {

			int index = -1;

			for (int i = 0; i <= keys.size(); i++) {
				if (keys.get(i).equals(key))
					index = i;
			}

			if (index == -1)
				return null;

			if (types.get(index) == DataStructType.DATASTRUCT)
				return (DataStruct) values.get(index);
			else
				return null;
		}

		public boolean ppIsRecursive() {
			return true;
		}

		public boolean ppHasKeys() {
			return true;
		}

		public Object[] ppGetKeys() {

			Object[] k = new Object[keys.size()];

			for (int i = 0; i < k.length; i++)
				k[i] = keys.get(i);

			return k;
		}

		public Object[] ppGetValues() {
			Object[] v = new Object[values.size()];

			for (int i = 0; i < v.length; i++)
				v[i] = values.get(i);

			return v;
		}

	}
    
    /*
    * Creates a people structure in the following form (JSON representation):
    * {
    *   "Total": 5,
    *   "Janine": "Old friend",
    *   "Job":
    *   {
    *       "Jon": "Boss",
    *       "Alan": 32,
    *       "Friends":
    *           {
    *               "Paul": 40,
    *               "Jake": 31
    *           }
    *   }
    * }
    
    DataStruct people = new DataStruct();
	people.addInt("Total", 5);
	people.addString("Janine", "Old friend");

	DataStruct jobPeople = new DataStruct();

	jobPeople.addString("Jon", "Boss");
	jobPeople.addInt("Alan", 32);

	DataStruct jobFriends = new DataStruct();

	jobFriends.addInt("Paul", 40);
	jobFriends.addInt("Jake", 31);

	jobPeople.addDataStruct("Friends", jobFriends);

	people.addDataStruct("Job", jobPeople);
	
	ObjectPrint.prettyPrintRecursive(people);
	
	/* Output (default padding character is '-'
	*
	* Total:  5
    * Janine: Old friend
    * Job:    
    * ----Jon:     Boss
    * ----Alan:    32
    * ----Friends: 
    * --------Paul: 40
    * --------Jake: 31
    *
    */


		
    

    
	    


