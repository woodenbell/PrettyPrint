package io.github.woodenbell.pprint.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

import io.github.woodenbell.pprint.SimplePrint;


public class SimpleTest {

	@Test
	public void arrayTest() {
		
		System.out.println("- Testing object array simple pprint");
		System.out.println();
		
		Object[] numbers = new Integer[] {2, 5, 6, 9};
		SimplePrint.pprint(numbers);
		
		System.out.println();
		System.out.println();
		
		Object[] names = new String[] {"wow", "much", null, "array"};
		SimplePrint.pprint(names);
		
		System.out.println();
		System.out.println();
	}
	
	@Test
	public void listTest() {
		
		System.out.println("- Testing List simple pprint");
		System.out.println();
		
		ArrayList<String> words = new ArrayList<>();
		words.add("world");
		words.add("cup");
		words.add("head");
		
		SimplePrint.pprint(words);
		
		System.out.println();
		System.out.println();
		
		ArrayList<Double> money = new ArrayList<>();
		money.add(37.8);
		money.add(9.43);
		money.add(null);
		money.add(6.0);
		
		SimplePrint.pprint(money);
		
		System.out.println();
		System.out.println();
	}
	
	
	@Test
	public void setTest() {
		
		System.out.println("- Testing Set simple pprint");
		System.out.println();
		
		HashSet<String> words = new HashSet<>();
		words.add("really");
		words.add("more");
		words.add("words");
		
		SimplePrint.pprint(words);
		
		System.out.println();
		System.out.println();
		
		HashSet<Double> money = new HashSet<>();
		money.add(2.8);
		money.add(0.0);
		money.add(null);
		money.add(3.0);
		
		SimplePrint.pprint(money);
		
		System.out.println();
		System.out.println();
		
	}
	
	@Test
	public void mapTest() {
		
		System.out.println("- Testing Map simple pprint");
		System.out.println();
		
		HashMap<String, Integer> ages = new HashMap<>();
		ages.put("John", 31);
		ages.put(null, 0);
		ages.put("James", 22);
		ages.put("Unknown", null);
		ages.put(null,  null);
		ages.put("Pyro", 20);
		
		SimplePrint.pprint(ages);
		
		System.out.println();
		System.out.println();
	}
}
