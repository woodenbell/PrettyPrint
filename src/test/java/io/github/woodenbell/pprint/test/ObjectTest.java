package io.github.woodenbell.pprint.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import io.github.woodenbell.pprint.ObjectPrint;
import io.github.woodenbell.pprint.PrettyPrintable;
import io.github.woodenbell.pprint.Util;

public class ObjectTest {

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

	@Test
	public void PPObjectTest() {
		
		System.out.println("- Testing custom object pprint");
		System.out.println();
		
		Person sherlock = new Person("Sherlock Holmes", 37, "Baker Street 221B");

		ObjectPrint.pprint(sherlock);

		System.out.println();
		System.out.println();

		ObjectPrint.pprint(sherlock, true);

		System.out.println();
		System.out.println();

		ObjectPrint.pprint(sherlock, true, true);

		System.out.println();
		System.out.println();

	}

	@Test
	public void ObjectArrayTest() {
		
		System.out.println("- Testing object array pprint");
		System.out.println();
		
		Object[] invited = new String[] { "Me", "You", "Everyone else" };

		ObjectPrint.pprint(invited);

		System.out.println();
		System.out.println();

		ObjectPrint.pprint(invited, true);

		System.out.println();
		System.out.println();

		ObjectPrint.pprint(invited, true, true, Util.TableFormat.EQUALS);

		System.out.println();
		System.out.println();
	}

	private static enum DataStructType {

		INTEGER, STRING, DATASTRUCT;

	}

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

	@Test
	public void RecursiveObjectTest() {
		
		System.out.println("- Testing custom object recursive pprint");
		System.out.println();
		
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

		ObjectPrint.pprintRecursive(people);

		System.out.println();
		System.out.println();

		ObjectPrint.pprintRecursive(people, '=');

		System.out.println();
		System.out.println();
	}

	@Test
	public void errorTest() {
		
		System.out.println("- Testing pprint with null values");
		System.out.println();

		Object[] objArray = null;
		ObjectPrint.pprint(objArray);

		PrettyPrintable customObj = null;

		ObjectPrint.pprint(customObj);
		ObjectPrint.pprintRecursive(customObj);

		PrettyPrintable customObj2 = new PrettyPrintable() {

			public boolean ppIsRecursive() {
				return false;
			}

			public boolean ppHasKeys() {
				return true;
			}

			public Object[] ppGetKeys() {
				return null;
			}

			public Object[] ppGetValues() {
				return null;
			}

		};

		ObjectPrint.pprint(customObj2);
		ObjectPrint.pprintRecursive(customObj2);

		PrettyPrintable customObj3 = new PrettyPrintable() {

			public boolean ppIsRecursive() {
				return false;
			}

			public boolean ppHasKeys() {
				return false;
			}

			public Object[] ppGetKeys() {
				return null;
			}

			public Object[] ppGetValues() {
				return new Object[] { null, null };
			}

		};

		ObjectPrint.pprint(customObj3);
		ObjectPrint.pprintRecursive(customObj3);

		Object[] arr = new Object[] { null, null };

		ObjectPrint.pprint(arr);

		PrettyPrintable customObj4 = new PrettyPrintable() {

			public boolean ppIsRecursive() {
				return false;
			}

			public boolean ppHasKeys() {
				return true;
			}

			public Object[] ppGetKeys() {
				return new Object[] { null, null };
			}

			public Object[] ppGetValues() {
				return new Object[] {};
			}

		};

		ObjectPrint.pprint(customObj4);
		ObjectPrint.pprintRecursive(customObj4);
	}

}
