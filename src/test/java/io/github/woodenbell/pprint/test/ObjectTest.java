package io.github.woodenbell.pprint.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import io.github.woodenbell.pprint.*;

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

		Person sherlock = new Person("Sherlock Holmes", 37, "Baker Street 221B");

		ObjectPrint.prettyPrint(sherlock);

		System.out.println();
		System.out.println();

		ObjectPrint.prettyPrint(sherlock, true);

		System.out.println();
		System.out.println();

		ObjectPrint.prettyPrint(sherlock, true, true);

		System.out.println();
		System.out.println();

	}

	@Test
	public void ObjectArrayTest() {

		Object[] invited = new String[] { "Me", "You", "Everyone else" };

		ObjectPrint.prettyPrint(invited);

		System.out.println();
		System.out.println();

		ObjectPrint.prettyPrint(invited, true);

		System.out.println();
		System.out.println();

		ObjectPrint.prettyPrint(invited, true, true, Util.TableFormat.EQUALS);

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

		System.out.println();
		System.out.println();

		ObjectPrint.prettyPrintRecursive(people, '=');

		System.out.println();
		System.out.println();
	}

	@Test
	public void errorTest() {

		Object[] objArray = null;
		ObjectPrint.prettyPrint(objArray);

		PrettyPrintable customObj = null;

		ObjectPrint.prettyPrint(customObj);
		ObjectPrint.prettyPrintRecursive(customObj);

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

		ObjectPrint.prettyPrint(customObj2);
		ObjectPrint.prettyPrintRecursive(customObj2);

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

		ObjectPrint.prettyPrint(customObj3);
		ObjectPrint.prettyPrintRecursive(customObj3);

		Object[] arr = new Object[] { null, null };

		ObjectPrint.prettyPrint(arr);

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

		ObjectPrint.prettyPrint(customObj4);
		ObjectPrint.prettyPrintRecursive(customObj4);
	}

}
