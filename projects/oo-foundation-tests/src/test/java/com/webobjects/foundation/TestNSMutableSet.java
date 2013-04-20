package com.webobjects.foundation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableSet;
import com.webobjects.foundation.NSSet;



public class TestNSMutableSet extends BaseTestCase {

	public void testNSMutableSet() {
		NSMutableSet<?> set = new NSMutableSet<Object>();
		assertTrue(set.isEmpty());
	}

	public void testNSMutableSetCollectionOfQextendsE() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("abc");
		list.add("abc");
		
		NSMutableSet<String> set = new NSMutableSet<String>(list);
		assertEquals(1, set.size());
		assertTrue(set.contains("abc"));
	}

	public void testNSMutableSetE() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		assertTrue(set.contains("abc"));
	}

	public void testNSMutableSetEArray() {
		String[] strings = new String[] {"abc", "abc"};
		NSMutableSet<String> set = new NSMutableSet<String>(strings);
		assertEquals(1, set.size());
		assertTrue(set.contains("abc"));
	}

	public void testNSMutableSetInt() {
		NSMutableSet<?> set = new NSMutableSet<Object>(1);
		assertTrue(set.isEmpty());
	}

	public void testNSMutableSetNSArrayOfQextendsE() {
		NSMutableArray<String> list = new NSMutableArray<String>();
		list.add("abc");
		list.add("abc");
		
		NSMutableSet<String> set = new NSMutableSet<String>(list);
		assertEquals(1, set.size());
		assertTrue(set.contains("abc"));
	}

	public void testNSMutableSetNSSetOfQextendsE() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		NSMutableSet<String> copy = new NSMutableSet<String>(set);
		assertEquals(1, copy.size());
		assertTrue(copy.contains("abc"));
	}

	public void testNSMutableSetSetOfQextendsEBoolean() {
		Set<String> source = new HashSet<String>();
		source.add("abc");
		source.add(null);
		
		NSMutableSet<String> set = new NSMutableSet<String>(source, true);
		assertEquals(1, set.size());
		assertTrue(set.contains("abc"));
		
		try {
			set = new NSMutableSet<String>(source, false);
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
		}
	}

	public void testAddObject() {
		NSMutableSet<String> set = new NSMutableSet<String>();
		set.addObject("abc");
		assertTrue(set.contains("abc"));
	}

	public void testAddObjectsFromArray() {
		NSMutableArray<String> list = new NSMutableArray<String>();
		list.add("abc");
		list.add("abc");
		
		NSMutableSet<String> set = new NSMutableSet<String>();
		set.addObjectsFromArray(list);
		assertEquals(1, set.size());
		assertTrue(set.contains("abc"));
	}

	public void testRemoveAllObjects() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		set.removeAllObjects();
		assertTrue(set.isEmpty());
	}

	public void testRemoveObject() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		Object removed = set.removeObject("abc");
		assertTrue(set.isEmpty());
		assertEquals("abc", removed);
	}

	public void testSubtractSet() {
		NSMutableSet<String> otherSet = new NSMutableSet<String>();
		otherSet.add("abc");
		otherSet.add("123");
		
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		set.subtractSet(otherSet);
		assertTrue(set.isEmpty());
	}

	public void testUnionSet() {
		NSMutableSet<String> otherSet = new NSMutableSet<String>();
		otherSet.add("abc");
		otherSet.add("123");
		
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		set.unionSet(otherSet);
		assertEquals(2, set.size());
		assertTrue(set.contains("abc"));
		assertTrue(set.contains("123"));
	}

	public void testSetSet() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		NSMutableSet<String> otherSet = new NSMutableSet<String>("123");
		otherSet.add("def");
		
		set.setSet(otherSet);
		assertEquals(otherSet, set);
	}

  public void testClone() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		NSMutableSet<String> clone = (NSMutableSet<String>) set.clone();
		assertEquals(set, clone);
		
		assertNotSame(set, clone);
	}

	public void testImmutableClone() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		NSSet<String> clone = set.immutableClone();
		assertEquals(1, clone.size());
		assertTrue(clone.contains("abc"));
		
		assertNotSame(set, clone);
		
		assertEquals(NSSet.class, clone.getClass());
	}

	public void testClear() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		set.clear();
		assertTrue(set.isEmpty());
	}

	public void testAdd() {
		NSMutableSet<String> set = new NSMutableSet<String>();
		set.add("abc");
		assertTrue(set.contains("abc"));
	}

	public void testAddAllCollection() {
		NSMutableArray<String> list = new NSMutableArray<String>();
		list.add("abc");
		list.add("abc");
		
		NSMutableSet<String> set = new NSMutableSet<String>();
		set.addAll(list);
		assertEquals(1, set.size());
		assertTrue(set.contains("abc"));
	}

	public void testRemove() {
		NSMutableSet<String> set = new NSMutableSet<String>("abc");
		boolean removed = set.remove("abc");
		assertTrue(set.isEmpty());
		assertTrue(removed);
	}

	public void testRemoveAllCollection() {
		NSMutableArray<String> list = new NSMutableArray<String>();
		list.add("abc");
		list.add("123");
		
		NSMutableSet<String> set = new NSMutableSet<String>();
		set.addAll(list);
		set.removeAll(list);
		assertTrue(set.isEmpty());
	}

	public void testRetainAllCollection() {
		NSMutableArray<String> list = new NSMutableArray<String>();
		list.add("abc");
		list.add("123");
		
		NSMutableSet<String> set = new NSMutableSet<String>();
		set.addAll(list);
		
		list.remove("123");	
		set.retainAll(list);
		
		assertEquals(1, set.size());
		assertTrue(set.contains("abc"));
	}

}
