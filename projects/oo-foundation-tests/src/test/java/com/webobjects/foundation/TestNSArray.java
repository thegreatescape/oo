package com.webobjects.foundation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSComparator;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;
import com.webobjects.foundation.NSRange;
import com.webobjects.foundation.NSSelectable;
import com.webobjects.foundation.NSSelector;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSComparator.ComparisonException;


public class TestNSArray extends BaseTestCase {

  public void testNSArray() {
    NSArray<Object> array = new NSArray<Object>();
    assertEquals(0, array.count());
  }

  public void testNSArrayIsImmutable() {
    NSArray<Object> array = new NSArray<Object>();
    try {
      array.add("abc");
      fail("NSArray is not immutable");
    } catch (UnsupportedOperationException e) {
    }
  }


  public void testNSArrayE() {
    NSArray<String> array = new NSArray<String>("abc");
    assertEquals(1, array.size());
    assertEquals("abc", array.objectAtIndex(0));
  }

  public void testNSArrayENull() {
    try {
      new NSArray<String>((String) null);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  public void testNSArrayEArray() {
    String[] str = new String[] { "abc", "def" };
    NSArray<String> array = new NSArray<String>(str);
    NSArray<String> array2 = new NSArray<String>(new String[] { "abc", "def" });

    assertEquals(2, array.size());
    assertEquals("abc", array.objectAtIndex(0));
    assertEquals("def", array.objectAtIndex(1));
    assertEquals(array, array2);

    array = new NSArray<String>(new String[] { "abc", null, "def", null, "ghi" });
    assertEquals(3, array.size());
    assertEquals("ghi", array.objectAtIndex(2));
  }
  
  public void testNSArrayEArrayNull() {
    NSArray<String> array = new NSArray<String>((String[]) null);
    assertEquals(0, array.size());
  }
  
  public void testNSArrayEVarArgs() {
    NSArray<String> array = new NSArray<String>("abc", "def", "ghi");
    assertEquals(3, array.size());
    assertEquals("abc", array.objectAtIndex(0));
    assertEquals("ghi", array.objectAtIndex(2));
  }

  public void testNSArrayEVarArgsNull() {
    NSArray<String> array = new NSArray<String>("abc", (String)null);
    assertEquals(1, array.size());
    assertEquals("abc", array.objectAtIndex(0));

    array = new NSArray<String>(null, (String)null);
    assertEquals(0, array.size());

    NSArray<String> array2 = new NSArray<String>((String)null, "abc");
    assertEquals(1, array2.size());

    array2 = new NSArray<String>((String)null, (String)null);
    assertEquals(0, array2.size());
  }
  
  public void testNSArrayEArrayNSRange() {
    String[] str = new String[] { "abc", "def" };
    NSRange range = new NSRange(1, 1);
    NSArray<String> array = new NSArray<String>(str, range);
    assertEquals(1, array.size());
    assertEquals("def", array.objectAtIndex(0));
  }

  public void testNSArrayEArrayNSRangeNull() {
    NSArray<String> array = new NSArray<String>((String[]) null, new NSRange(0, 0));
    assertEquals(0, array.size());

    array = new NSArray<String>(new String[] { "abc" }, (NSRange) null);
    assertEquals(0, array.size());
    
    array = new NSArray<String>((String[]) null, (NSRange) null);
    assertEquals(0, array.size());    
  }
  
  public void testNSArrayEArrayNSRangeInvalid() {
    try {
      new NSArray<String>(new String[] { "abc" }, new NSRange(1, 1));
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }
    
    try {
      new NSArray<String>((String[]) null, new NSRange(1, 1));
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }

  }
  
  public void testNSArrayNSArrayOfQextendsE() {
    NSArray<String> nsarray = new NSArray<String>(new String[] { "abc" });
    NSArray<String> array = new NSArray<String>(nsarray);
    assertEquals(1, array.size());
    assertEquals("abc", array.objectAtIndex(0));

    NSMutableArray<String> nsmutablearray = new NSMutableArray<String>(new String[] { "abc" });
    array = new NSArray<String>(nsmutablearray);
    assertEquals(1, array.size());
    nsmutablearray.add("def");
    assertEquals(1, array.size());

    nsmutablearray.set(0, "123");
    assertEquals("abc", array.objectAtIndex(0));
  }
  
  public void testNSArrayNSArrayOfQextendsENull() {
    NSArray<String> array = new NSArray<String>((NSArray<String>) null);
    assertEquals(0, array.count());
  }

  public void testNSArrayListOfQextendsEBoolean() {
    List<String> list = Arrays.asList("abc", "def");
    
    NSArray<String> array = new NSArray<String>(list, true);
    assertEquals(2, array.size());

    array = new NSArray<String>(list, false);
    assertEquals(2, array.size());

    NSArray<String> array2 = new NSArray<String>(array);
    assertTrue(array2.equals(array));

    list = Arrays.asList("abc", "def", null);
    array = new NSArray<String>(list, false);
    assertEquals(3, array.size());
  }

  public void testNSArrayListOfQextendsEBooleanNull() {
    try {
      new NSArray<String>((List<String>)null, true);
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }
    try {
      new NSArray<String>((List<String>)null, false);
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }  
  }
  
  public void testNSArrayListOfQextendsEBooleanInvalid() {
    List<String> list = Arrays.asList("abc", "def", null);
    
    try {
      new NSArray<String>(list, true);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  public void testNSArrayCollectionOfQextendsE() {
    Collection<String> list = Arrays.asList("abc", "def");

    NSArray<String> array = new NSArray<String>(list);
    assertEquals(2, array.size());

    NSArray<String> array2 = new NSArray<String>(array);
    assertTrue(array2.equals(array));
  }

  public void testNSArrayCollectionOfQextendsENull() {
    try {
      new NSArray<String>((List<String>)null);
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }
  }
  
  public void testNSArrayCollectionOfQextendsEInvalid() {
    Collection<String> list = Arrays.asList("abc", "def", null);

    try {
      new NSArray<String>(list);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  public void testNSArrayCollectionOfQextendsEBoolean() {
    Collection<String> collection = Arrays.asList("abc", "def");

    NSArray<String> array = new NSArray<String>(collection, false);
    assertEquals(2, array.size());
    assertTrue(Arrays.equals(collection.toArray(), array.toArray()));
    
    collection = Arrays.asList("abc", "def", null);
    array = new NSArray<String>(collection, false);
    assertEquals(3, array.size());
  }

  public void testNSArrayCollectionOfQextendsEBooleanNull() {
    try {
      new NSArray<String>((Collection<String>)null, true);
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }

    try {
      new NSArray<String>((Collection<String>)null, false);
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }
  }
  
  public void testNSArrayCollectionOfQextendsEBooleanInvalid() {
    Collection<String> collection = Arrays.asList("abc", "def", null);

    try {
      new NSArray<String>(collection, true);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  
  public void testNSArrayListOfQextendsENSRangeBoolean() {
    List<String> list = Arrays.asList("abc", null, "def");

    NSRange range = new NSRange(1, 2); // 1, 2

    NSArray<String> array = new NSArray<String>(list, range, true);
    assertEquals(1, array.size());
    
    array = new NSArray<String>(list, new NSRange(1,2), true);
    assertEquals(1, array.size());
    assertEquals("def", array.objectAtIndex(0));
    
    NSArray<String> array2 = new NSArray<String>(list, new NSRange(0, list.size()), true);
    assertEquals(2, array2.size());
    
    list = Arrays.asList("abc", "def", "ghi");
    
    array = new NSArray<String>(list, new NSRange(0, list.size()), false);
    assertEquals(3, array.size());
    
    array = new NSArray<String>(list, null, false);
    assertEquals(0, array.size()); 
  }

  public void testNSArrayListOfQextendsENSRangeBooleanNull() {
    try {
      new NSArray<String>((List<String>)null, new NSRange(0, 0), false);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
    
    List<String> list = Arrays.asList("abc");
    try {
      new NSArray<String>(list, new NSRange(4, 1), false);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }
  }

  public void testNSArrayListOfQextendsENSRangeBooleanInvalid() {
    List<String> list = Arrays.asList("abc", null, "def");
    NSRange range = new NSRange(1, 2); // 1, 2

    try {
      new NSArray<String>(list, range, false);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
        
    list = Arrays.asList("abc", "def", "ghi");
    
    try {
      new NSArray<String>(list, new NSRange(4, 1), false);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }    
  }
  
  public void testNSArrayVectorOfQextendsENSRangeBoolean() {
    Vector<String> vector = new Vector<String>();
    vector.add("abc");
    vector.add(null);
    vector.add("def");

    NSRange range = new NSRange(1, 2); // 1, 2

    NSArray<String> array = new NSArray<String>(vector, range, true);
    assertEquals(1, array.size());
    
    array = new NSArray<String>(vector, new NSRange(1,2), true);
    assertEquals(1, array.size());
    assertEquals("def", array.objectAtIndex(0));

    try {
      new NSArray<String>(vector, range, false);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
        
    vector.clear();
    vector.add("abc");
    vector.add("def");
    vector.add("ghi");
    
    array = new NSArray<String>(vector, new NSRange(0, vector.size()), false);
    assertEquals(3, array.size());
    
    array = new NSArray<String>(vector, null, false);
    assertEquals(0, array.size());
    
    try {
      new NSArray<String>((Vector<String>)null, range, false);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }  
  }

  public void testNSArrayVectorOfQextendsENSRangeBooleanNull() {
    Vector<String> vector = new Vector<String>();
    vector.add("abc");
    vector.add("def");
    vector.add("ghi");
    
    NSArray<String >array = new NSArray<String>(vector, null, false);
    assertEquals(0, array.size());
    
    try {
      new NSArray<String>((Vector<String>)null, new NSRange(0,0), false);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }  

    try {
      new NSArray<String>((Vector<String>)null, (NSRange)null, false);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }  
  }
  
  public void testNSArrayVectorOfQextendsENSRangeBooleanInvalid() {
    Vector<String> vector = new Vector<String>();
    vector.add("abc");
    vector.add(null);
    vector.add("def");

    NSRange range = new NSRange(1, 2); // 1, 2

    try {
      new NSArray<String>(vector, range, false);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
        
    vector = new Vector<String>();
    vector.add("abc");
    vector.add("def");
    vector.add("ghi");
    
    try {
      new NSArray<String>(vector, new NSRange(4, 1), false);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }    
  }

  public void testArrayByAddingObject() {
    NSArray<String> array = new NSArray<String>("abc");
    NSArray<String> array2 = array.arrayByAddingObject("def");
    assertEquals(2, array2.size());
  }

  public void testArrayByAddingObjectNull() {
    NSArray<String> array = new NSArray<String>("abc");

    try {
      array.arrayByAddingObject(null);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }
  
  @SuppressWarnings("unchecked")
  public void testArrayByAddingObjectsFromArray() {
    String[] combined = new String[] { "abc", "def", "ghi" };
    NSArray<String> array = new NSArray<String>("abc");
    NSArray<String> array2 = new NSArray<String>(new String[] { "def", "ghi" });
    NSArray<String> result = array.arrayByAddingObjectsFromArray(array2);
    assertEquals(3, result.size());
    assertEquals(result, new NSArray<String>(combined));
    result = array.arrayByAddingObjectsFromArray(null);
    assertEquals(array, result);
    
    result = NSArray.EmptyArray.arrayByAddingObjectsFromArray(array);
    assertEquals(array, result);
    
    result = array.arrayByAddingObjectsFromArray(NSArray.EmptyArray);
    assertEquals(array, result);
  }

  public void testArrayByAddingObjectsFromArrayNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc" });
    NSArray<String> result = array.arrayByAddingObjectsFromArray(null);
    assertEquals(array, result);
  }
  
  public void testArrayList() {
    NSArray<String> array = new NSArray<String>("abc");
    List<String> list = array.arrayList();
    assertTrue(list instanceof ArrayList<?>);

    ArrayList<String> expected = new ArrayList<String>();
    expected.add("abc");
    assertEquals(expected, list);
  }
  
  public void testClone() {
    NSArray<String> array = new NSArray<String>("abc");
    NSArray<String> clone = (NSArray<String>) array.clone();
    assertEquals(clone, array);
  }

  public void testComponentsJoinedByString() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    String joined = array.componentsJoinedByString("-");
    assertEquals("abc-def", joined);
  }

  public void testComponentsJoinedByStringNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    String joined = array.componentsJoinedByString(null);
    assertEquals("abcdef", joined);
  }
  
  public void testContainsObject() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    assertTrue(array.containsObject("def"));
    assertFalse(array.containsObject("123"));
  }

  public void testContainsObjectNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    assertFalse(array.containsObject(null));
  }
  
  public void testContains() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    assertTrue(array.contains(new String("abc")));
  }

  public void testContainsNull() {
    try {
      assertFalse(NSArray.EmptyArray.contains(null));
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }
  }
  
  public void testContainsAllCollection() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def", "ghi" });
    NSArray<String> array2 = new NSArray<String>(Arrays.asList("abc", "def"));
    assertTrue(array.containsAll(array2));
    
    array2 = new NSArray<String>(new String[] { "abc", "def", "xyz" });
    assertFalse(array.containsAll(array2));

    List<String> list = array2.subList(1, 3);
    assertFalse(list.containsAll(array));

    list = Arrays.asList("abc", "def", null);
    assertFalse(array.containsAll(list));
  }

  public void testContainsAllCollectionNull() {
    try {
      NSArray.emptyArray().containsAll(null);
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }
  }
  
  public void testCount() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    assertEquals(2, array.count());
    assertEquals(array.size(), array.count());
  }

  public void testEmptyArray() {
    assertEquals(0, NSArray.EmptyArray.size());
    assertEquals(0, NSArray.emptyArray().size());
    assertTrue(NSArray.emptyArray() == NSArray.EmptyArray);  
  }
  
  public void testIsEmpty() {
    assertTrue(NSArray.emptyArray().isEmpty());
    assertFalse(new NSArray<String>("abc").isEmpty());
  }
  
  public void testEquals() {
    NSArray<String> array = new NSArray<String>("abc");
    assertTrue(array.equals(array.clone()));
    assertFalse(array.equals("abc"));
    NSArray<String> array2 = new NSMutableArray<String>("def");
    array.hashCode();
    array2.hashCode();
    assertFalse(array.equals(array2));
  }

  public void testEqualsNull() {
    NSArray<String> array = new NSArray<String>("abc");
    assertFalse(array.equals(null));
  }

  
  public void testFirstObjectCommonWithArray() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    NSArray<String> array2 = new NSArray<String>(new String[] { "def", "ghi" });

    Object commonObject = array.firstObjectCommonWithArray(array2);
    assertEquals("def", commonObject);

    array2 = new NSArray<String>(new String[] { "ghi", "jkl" });
    commonObject = array.firstObjectCommonWithArray(array2);
    assertNull(commonObject);
  }

  public void testFirstObjectCommonWithArrayNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });

    String commonObject = array.firstObjectCommonWithArray(null);
    assertNull(commonObject);
  }
  
  public void testImmutableClone() {
    NSMutableArray<String> array = new NSMutableArray<String>("abc");
    NSArray<String> clone = array.immutableClone();
    assertEquals(array, clone);
    assertEquals(array.size(), clone.size());
    array.addObjects("def");
    assertEquals(1, clone.size());
  }

  public void testIndexOfIdenticalObjectObject() {
    Object obj = new Object();
    NSArray<Object> array = new NSArray<Object>(new Object[] { "abc", obj });
    int index = array.indexOfIdenticalObject(obj);
    assertEquals(1, index);
    
    index = array.indexOfIdenticalObject(new Object());
    assertEquals(NSArray.NotFound, index);

    index = array.indexOfIdenticalObject("ghi");
    assertEquals(NSArray.NotFound, index);
  }
  
  public void testIndexOfIdenticalObjectObjectNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc" });
    int index = array.indexOfIdenticalObject(null);
    assertEquals(NSArray.NotFound, index);
  }

  public void testIndexOfIdenticalObjectObjectNSRange() {
    String def = "def";
    NSArray<String> array = new NSArray<String>(new String[] { "abc", def });
    NSRange range = new NSRange(1, 1);
    int index = array.indexOfIdenticalObject(def, range);
    assertEquals(1, index);

    range = NSRange.ZeroRange;
    index = array.indexOfIdenticalObject(def, range);
    assertEquals(NSArray.NotFound, index);
  }
  
  public void testIndexOfIdenticalObjectObjectNSRangeNull() {
    String def = "def";
    NSArray<String> array = new NSArray<String>(new String[] { "abc", def });
    NSRange range = new NSRange(1, 1);
    
    int index = array.indexOfIdenticalObject(null, range);
    assertEquals(NSArray.NotFound, index);
    
    index = array.indexOfIdenticalObject(def, null);
    assertEquals(NSArray.NotFound, index);    

    index = array.indexOfIdenticalObject(null, null);
    assertEquals(NSArray.NotFound, index); 
  }
  
  public void testIndexOfIdenticalObjectObjectNSRangeInvalid() {
    String def = "def";
    NSArray<String> array = new NSArray<String>(new String[] { "abc", def });
    try {
      array.indexOfIdenticalObject(def, new NSRange(2, 1));
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  public void testIndexOfObject() {
    String def = "def";
    NSArray<String> array = new NSArray<String>(new String[] { "abc", def });
    int index = array.indexOf(def);
    assertEquals(1, index);
    
    index = array.indexOf(new String("def"));
    assertEquals(1, index);
  }

  public void testIndexOfObjectNull() {
    try {
      NSArray.emptyArray().indexOf(null);
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }
  }
  
  public void testIndexOfObjectObject() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    int index = array.indexOfObject("def");
    assertEquals(1, index);

    index = array.indexOfObject(new String("def"));
    assertEquals(1, index);

    index = array.indexOfIdenticalObject("ghi");
    assertEquals(NSArray.NotFound, index);
  }
  
  public void testIndexOfObjectObjectNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });

    int index = array.indexOfObject(null);
    assertEquals(NSArray.NotFound, index);
  }


  public void testIndexOfObjectObjectNSRange() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    NSRange range = new NSRange(1, 1); // 1
    int index = array.indexOfObject("def", range);
    assertEquals(1, index);

    index = array.indexOfObject("def", new NSRange(0, 2));
    assertEquals(1, index);

    index = array.indexOfObject("def", new NSRange(0, 1));
    assertEquals(NSArray.NotFound, index);

    range = NSRange.ZeroRange;
    index = array.indexOfObject("def", range);
    assertEquals(NSArray.NotFound, index);
  }

  public void testIndexOfObjectObjectNSRangeNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    NSRange range = new NSRange(1, 1); // 1

    int index = array.indexOfObject("def", null);
    assertEquals(NSArray.NotFound, index);

    index = array.indexOfObject(null, range);
    assertEquals(NSArray.NotFound, index);

    index = array.indexOfObject(null, null);
    assertEquals(NSArray.NotFound, index);
  }

  public void testIndexOfObjectObjectNSRangeInvalid() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });

    try {
      array.indexOfObject("def", new NSRange(2, 1));
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }
  
  public void testIsEqualToArray() {
    NSArray<String> array = new NSArray<String>("abc");
    NSArray<String> clone = array.immutableClone();
    assertTrue(array.isEqualToArray(clone));

    clone = new NSArray<String>("def");
    assertFalse(array.isEqualToArray(clone));
    assertFalse(array.isEqualToArray(NSArray.emptyArray()));
    assertFalse(array.isEqualToArray(null));
  }

  public void testIsEqualToArrayNull() {
    NSArray<String> array = new NSArray<String>("abc");
    assertFalse(array.isEqualToArray(null));
  }
  
  public static class TestSelectable implements NSSelectable {
    public ArrayList<String> array = new ArrayList<String>();

    public TestSelectable() {
    }

    public void invoke(String value) {
      array.add(value);
    }
    
    public void invoke() {
      array.add("invoked");
    }
  }

  public void testLastIndexOfObject() {
    NSArray<String> array = new NSArray<String>(new String[] {"a", "b", "c", "a"});
    assertEquals(3, array.lastIndexOf("a"));
    assertEquals(2, array.lastIndexOf("c"));
  }
  
  public void testLastIndexOfObjectNull() {
    try {
      NSArray.emptyArray().lastIndexOf(null);
      fail("NullPointerException expected");
    } catch (NullPointerException e) {
    }
  }
  
  public void testLastObject() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def", "ghi" });
    assertEquals("ghi", array.lastObject());
    assertEquals(null, NSArray.emptyArray().lastObject());
  }
  
  public void testMakeObjectsPerformSelector() {
    TestSelectable selectable = new TestSelectable();
    NSArray<Object> array = new NSArray<Object>(selectable);
    NSSelector<?> selector = new NSSelector<Void>("invoke", new Class[] { String.class });
    array.makeObjectsPerformSelector(selector, new Object[] { "abc" });
    assertTrue(selectable.array.contains("abc"));
  }

  public void testMakeObjectsPerformSelectorNull() {
    NSArray<String> array = new NSArray<String>("abc");
    
    try {
      array.makeObjectsPerformSelector(null, (Object[])null);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  public void testMakeObjectsPerformSelectorInvalid() {
    TestSelectable selectable = new TestSelectable();
    NSArray<Object> array = new NSArray<Object>(selectable);
    NSSelector<?> selector = new NSSelector<Void>("missiginvoke", new Class[] { String.class });
    try {
      array.makeObjectsPerformSelector(selector, new Object[] { "abc" });
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }

    NSArray<String> array2 = new NSArray<String>("abc");
    try {
      array2.makeObjectsPerformSelector(new NSSelector<Void>("invoke"), (Object[])null);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
    
  }

  public void testMutableClone() {
    NSArray<String> array = new NSArray<String>("abc");
    NSMutableArray<String> clone = array.mutableClone();
    assertEquals(1, clone.size());
    assertEquals("abc", clone.objectAtIndex(0));
    
    clone.set(0, "def");
    assertEquals("abc", array.objectAtIndex(0));
    assertEquals("def", clone.objectAtIndex(0));
  }

  public void testObjectAtIndex() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def", "ghi" });
    assertEquals("abc", array.objectAtIndex(0));
  }
  
  public void testObjectAtIndexInvalid() {    
    try {
      NSArray.emptyArray().objectAtIndex(0);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }

    try {
      new NSArray<String>("abc").objectAtIndex(1);
      fail("IndexOutOfBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }
  }

  public void testObjects() {
    String[] strings = new String[] { "abc", "def" };
    NSArray<String> array = new NSArray<String>(strings);
    Object[] objects = array.objects();
    assertEquals(strings, objects);
    
    objects[0] = "xyz";
    assertEquals("abc", array.objectAtIndex(0));
  }

  public void testObjectsNSRange() {
    String[] strings = new String[] { "abc", "def" };
    NSArray<String> array = new NSArray<String>(strings);
    NSRange range = new NSRange(1, 1);
    Object[] objects = array.objects(range);
    assertEquals(1, objects.length);
    assertEquals(strings[1], objects[0]);
  }
  
  public void testObjectsNSRangeNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });

    Object[] objects = array.objects((NSRange)null);
    assertEquals(0, objects.length);
  }


  public void testObjectEnumerator() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    Enumeration<String> e = array.objectEnumerator();
    assertTrue(e.hasMoreElements());
    assertEquals("abc", e.nextElement());
    assertEquals("def", e.nextElement());
    assertFalse(e.hasMoreElements());
    try {
      e.nextElement();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException ex) {
    }
  }

  public void testReverseObjectEnumerator() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    Enumeration<String> e = array.reverseObjectEnumerator();
    assertTrue(e.hasMoreElements());
    assertEquals("def", e.nextElement());
    assertEquals("abc", e.nextElement());
    assertFalse(e.hasMoreElements());
    try {
      e.nextElement();
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException ex) {
    }
  }

  public void testSortedArrayUsingComparatorString() throws ComparisonException {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    NSArray<String> sorted;

    sorted = array.sortedArrayUsingComparator(NSComparator.AscendingStringComparator);
    assertNotSame(array, sorted);
    assertEquals("abc", sorted.objectAtIndex(0));
    assertEquals("def", sorted.objectAtIndex(1));

    sorted = array.sortedArrayUsingComparator(NSComparator.DescendingStringComparator);
    assertEquals("def", sorted.objectAtIndex(0));
    assertEquals("abc", sorted.objectAtIndex(1));

    array = new NSArray<String>(new String[] { "abc", "DEF" });
    sorted = array.sortedArrayUsingComparator(NSComparator.AscendingCaseInsensitiveStringComparator);
    assertEquals("abc", sorted.objectAtIndex(0));
    assertEquals("DEF", sorted.objectAtIndex(1));

    sorted = array.sortedArrayUsingComparator(NSComparator.DescendingCaseInsensitiveStringComparator);
    assertEquals("DEF", sorted.objectAtIndex(0));
    assertEquals("abc", sorted.objectAtIndex(1));
  }
  
  public void testSortedArrayUsingComparatorNull() throws ComparisonException {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "xyz", "def" });
    try {
      array.sortedArrayUsingComparator(null);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  
  public void testSortedArrayUsingComparatorNumber() throws ComparisonException {
    NSArray<Integer> array = new NSArray<Integer>(new Integer[] { 1, 2 });
    NSArray<Integer> sorted = array.sortedArrayUsingComparator(NSComparator.AscendingNumberComparator);
    assertEquals(Integer.valueOf(1), sorted.objectAtIndex(0));
    assertEquals(Integer.valueOf(2), sorted.objectAtIndex(1));

    sorted = array.sortedArrayUsingComparator(NSComparator.DescendingNumberComparator);
    assertEquals(Integer.valueOf(2), sorted.objectAtIndex(0));
    assertEquals(Integer.valueOf(1), sorted.objectAtIndex(1));
  }
  
  public void testSortedArrayUsingComparatorTimestamp() throws ComparisonException {
    NSTimestamp earlierTime = new NSTimestamp();
    NSTimestamp laterTime = earlierTime.timestampByAddingGregorianUnits(0, 1, 0, 0, 0, 0);
    NSArray<NSTimestamp> array = new NSArray<NSTimestamp>(new NSTimestamp[] { earlierTime, laterTime });
    NSArray<NSTimestamp> sorted = array.sortedArrayUsingComparator(NSComparator.AscendingTimestampComparator);
    assertEquals(earlierTime, sorted.objectAtIndex(0));
    assertEquals(laterTime, sorted.objectAtIndex(1));

    sorted = array.sortedArrayUsingComparator(NSComparator.DescendingTimestampComparator);
    assertEquals(laterTime, sorted.objectAtIndex(0));
    assertEquals(earlierTime, sorted.objectAtIndex(1));
  }

  public void testSubarrayWithRange() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    NSArray<String> subArray = array.subarrayWithRange(new NSRange(1, 1));
    assertEquals(1, subArray.size());
    assertEquals("def", subArray.objectAtIndex(0));
    
    try {
      subArray.set(0, "xyz");
      fail("UnsupportedOperationException expected");
    } catch (UnsupportedOperationException e) {
    }
  }
  
  public void testSubarrayWithRangeNull() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def" });
    NSArray<String> subArray;
    subArray = array.subarrayWithRange(null);
    assertEquals(0, subArray.size()); 
  }

  public void testSubList() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def", "ghi", "jkl" });
    List<String> sublist = array.subList(2, 4);
    assertEquals(2, sublist.size());
    assertEquals("ghi", sublist.get(0));
    
    try {
      sublist.add("mno");
      fail("UnsupportedOperationException expected");
    } catch (UnsupportedOperationException e) {
    }
    
    try {
      sublist.set(0, "mno");
      fail("UnsupportedOperationException expected");
    } catch (UnsupportedOperationException e) {
    }

    try {
      sublist.remove("ghi");
      fail("UnsupportedOperationException expected");
    } catch (UnsupportedOperationException e) {
    }

    try {
      sublist.clear();
      fail("UnsupportedOperationException expected");
    } catch (UnsupportedOperationException e) {
    }
  }
  
  public void testSubListOutOfBounds() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def", "ghi", "jkl" });
    
    try {
      array.subList(-1, 1);
      fail("IndexOutofBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }

    try {
      array.subList(1, 5);
      fail("IndexOutofBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }

    try {
      array.subList(5, 1);
      fail("IndexOutofBoundsException expected");
    } catch (IndexOutOfBoundsException e) {
    }
  }

  public void testValueForKey() {
    NSDictionary<?, ?> dicts[] = new NSDictionary[] { new NSDictionary<String, String>("val1", "key"), new NSDictionary<String, String>("val2", "key") };
    NSArray<NSDictionary<?, ?>> array = new NSArray<NSDictionary<?, ?>>(dicts);
    NSArray<?> result = (NSArray<?>) array.valueForKey("key");
    assertEquals(2, result.size());
    assertEquals("val1", result.objectAtIndex(0));
    assertEquals("val2", result.objectAtIndex(1));
    assertEquals(2, result.valueForKey("count"));
  }

  public void testValueForKeyPath() {
    NSDictionary<?, ?>[] dicts = new NSDictionary[] { new NSDictionary<String, Integer>(2, "key"), new NSDictionary<String, Integer>(4, "key") };
    NSArray<?> array = new NSArray<NSDictionary<?, ?>>(dicts);

    NSDictionary<?, ?> subDict = new NSDictionary<String, Integer>(2, "subkey");
    NSDictionary<?, ?> dict = new NSDictionary<String, NSDictionary<?, ?>>(subDict, "key");
    array = new NSArray<NSDictionary<?, ?>>(dict);
    NSArray<?> values = (NSArray<?>) array.valueForKeyPath("key.subkey");
    assertEquals(1, values.size());
    assertEquals(2, values.objectAtIndex(0));
  }
  
  public void testNullOperator() {
    try {
      NSArray.emptyArray().valueForKeyPath("@");
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  public void testCountOperator() {
    NSDictionary<?, ?>[] dicts = new NSDictionary[] { new NSDictionary<String, Integer>(2, "key"), new NSDictionary<String, Integer>(4, "key") };
    NSArray<?> array = new NSArray<NSDictionary<?, ?>>(dicts);
    int count = (Integer) array.valueForKeyPath("@count");
    assertEquals(2, count);    

    count = (Integer) array.valueForKey("@count");
    assertEquals(2, count);
    
    count = (Integer) NSArray.emptyArray().valueForKey("@count");
    assertEquals(0, count);
  }
  
  public void testSumOperator() {
    NSDictionary<?, ?>[] dicts = new NSDictionary[] { new NSDictionary<String, Integer>(2, "key"), new NSDictionary<String, Integer>(4, "key") };
    NSArray<?> array = new NSArray<NSDictionary<?, ?>>(dicts);

    BigDecimal sum = (BigDecimal) array.valueForKeyPath("@sum.key");
    assertEquals(6, sum.intValue());

    sum  = (BigDecimal) new NSArray<Integer>(new Integer[] { 1, 2, 3 }).valueForKeyPath("@sum");
    assertEquals(6, sum.intValue());

    sum = (BigDecimal) NSArray.emptyArray().valueForKeyPath("@sum.key");
    assertEquals(0, sum.intValue());

    sum = (BigDecimal) NSArray.emptyArray().valueForKeyPath("@sum");
    assertEquals(0, sum.intValue());

    sum = (BigDecimal) NSArray.emptyArray().valueForKeyPath("@sum.");
    assertEquals(0, sum.intValue());
  }
  
  public void testAvgOperator() {
    NSDictionary<?, ?>[] dicts = new NSDictionary[] { new NSDictionary<String, Integer>(2, "key"), new NSDictionary<String, Integer>(4, "key") };
    NSArray<?> array = new NSArray<NSDictionary<?, ?>>(dicts);

    BigDecimal avg = (BigDecimal) array.valueForKeyPath("@avg.key");
    assertEquals(3, avg.intValue());
    
    avg = (BigDecimal) array.valueForKeyPath("@avg.unknownkey");
    assertEquals(0, avg.intValue());

    avg = (BigDecimal) new NSArray<Integer>(new Integer[] { 1, 2, 3 }).valueForKeyPath("@avg");
    assertEquals(2, avg.intValue());
    
    avg = (BigDecimal) new NSArray<String>(new String[] { "1", "2", "3" }).valueForKeyPath("@avg");
    assertEquals(2, avg.intValue());
    
    avg = (BigDecimal) NSArray.emptyArray().valueForKeyPath("@avg.key");
    assertEquals(null, avg);
    
    avg = (BigDecimal) NSArray.emptyArray().valueForKeyPath("@avg");
    assertEquals(null, avg);
    
    try {
      avg = (BigDecimal) array.valueForKeyPath("@avg");
      fail("IllegalStateException expected");
    } catch (IllegalStateException e) {
    }
  }
  
  public void testMinOperator() {
    NSDictionary<?, ?>[] dicts = new NSDictionary[] { new NSDictionary<String, Integer>(2, "key"), new NSDictionary<String, Integer>(4, "key") };
    NSArray<?> array = new NSArray<NSDictionary<?, ?>>(dicts);

    int min = ((Integer) array.valueForKeyPath("@min.key")).intValue();
    assertEquals(2, min);
  }
  
  public void testMaxOperator() {
    NSDictionary<?, ?>[] dicts = new NSDictionary[] { new NSDictionary<String, Integer>(2, "key"), new NSDictionary<String, Integer>(4, "key") };
    NSArray<?> array = new NSArray<NSDictionary<?, ?>>(dicts);
    int max = ((Integer) array.valueForKeyPath("@max.key")).intValue();
    assertEquals(4, max);
  }
  
  public void testTakeValueForKeyPath() {
    NSMutableDictionary<?, ?> subDict = new NSMutableDictionary<String, Integer>(2, "subkey");
    NSMutableDictionary<?, ?> dict = new NSMutableDictionary<String, NSDictionary<?, ?>>(subDict, "key");
    NSArray<?> array = new NSArray<NSDictionary<?, ?>>(dict);
    array.takeValueForKeyPath(3, "key.subkey");
    assertEquals(3, subDict.objectForKey("subkey"));
  }

  public void testVector() {
    String[] strings = new String[] { "abc", "def" };
    NSArray<String> array = new NSArray<String>(strings);
    Vector<String> vector = array.vector();
    assertEquals(strings.length, vector.size());
    assertEquals(strings[0], vector.get(0));
    assertEquals(strings[1], vector.get(1));
  }

  public void testClear() {
    try {
      NSArray<String> array = new NSArray<String>(new String[] { "abc", "def"});
      array.clear();
      fail("Clear should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }

  public void testAddObject() {
    try {
      NSArray.emptyArray().add("abc");
      fail("Add should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }

  public void testAddIntObject() {
    try {
      NSArray.emptyArray().add(0, "abc");
      fail("Add should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }

  public void testAddAllCollection() {
    try {
      List<String> list = Arrays.asList("abc", "def");
      NSArray.emptyArray().addAll(list);
      fail("AddAll should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }

  public void testAddAllIntCollection() {
    try {
      List<String> list = Arrays.asList("abc", "def");
      NSArray.emptyArray().addAll(0, list);
      fail("AddAll should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }
  
  public void testGetInt() {
    try {
      NSArray.EmptyArray.get(-1);
      fail("Get should throw IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
    }
    try {
      NSArray.EmptyArray.get(1);
      fail("Get should throw IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
    }
  }

  public void testRemoveInt() {
    try {
      NSArray<String> array = new NSArray<String>(new String[] { "abc", "def"});
      array.remove(0);
      fail("Remove should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }

  public void testRemoveObject() {
    try {
      NSArray<String> array = new NSArray<String>(new String[] { "abc", "def"});
      array.remove("abc");
      fail("Remove should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }

  public void testRemoveAllCollection() {
    try {
      List<String> list = Arrays.asList("abc", "def");
      NSArray<String> array = new NSArray<String>(list);
      array.removeAll(list);
      fail("RemoveAll should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }

  public void testRetainAllCollection() {
    try {
      List<String> list = Arrays.asList("abc", "def");
      NSArray<String> array = new NSArray<String>(new String[] { "abc", "def", "ghi"});
      array.retainAll(list);
      fail("RetainAll should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
  }

  public void testSetIntObjectInvalid() {
    try {
      new NSArray<String>("abc").set(0, "def");
      fail("Set should throw UnsupportedOperationException");
    } catch (UnsupportedOperationException e) {
    }
    try {
      new NSArray<String>("abc").set(-1, "def");
      fail("Set should throw IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
    } catch (UnsupportedOperationException e) {
    }
    
    try {
      new NSArray<String>("abc").set(2, "def");
      fail("Set should throw IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
    } catch (UnsupportedOperationException e) {
    }
  }

  private static final String prefix = "0123456789012345678901234567890123456789012345678|" + "0123456789012345678901234567890123456789012345678|"
      + "0123456789012345678901234567890123456789012345678|" + "0123456789012345678901234567890123456789012345678|"
      + "0123456789012345678901234567890123456789012345678|";

  private static final String multiPrefix = "01234567890123456789012345678901234567890123456||" + "01234567890123456789012345678901234567890123456||"
      + "01234567890123456789012345678901234567890123456||" + "01234567890123456789012345678901234567890123456||"
      + "01234567890123456789012345678901234567890123456||";

  public void testComponentsSeparatedByString() {
    String string;
    NSArray<String> array;

    array = NSArray.componentsSeparatedByString("", "|");
    assertEquals(0, array.count());
    array = NSArray.componentsSeparatedByString(null, "|");
    assertEquals(0, array.count());

    string = prefix;
    array = NSArray.componentsSeparatedByString(string, "");
    assertEquals(1, array.count());
    array = NSArray.componentsSeparatedByString(string, null);
    assertEquals(1, array.count());
    array = NSArray.componentsSeparatedByString(string, "X");
    assertEquals(1, array.count());

    string = prefix + "||||X";

    array = NSArray.componentsSeparatedByString(string, "|");
    assertEquals(255, string.length());
    assertEquals(10, array.count());
    assertEquals("X", array.objectAtIndex(9));

    string = prefix + "|||||";

    array = NSArray.componentsSeparatedByString(string, "|");
    assertEquals(255, string.length());
    assertEquals(11, array.count());
    assertEquals("", array.objectAtIndex(10));

    string = prefix + "X|||||";

    array = NSArray.componentsSeparatedByString(string, "|");
    assertEquals(256, string.length());
    assertEquals(11, array.count());
    assertEquals("", array.objectAtIndex(10));
    assertEquals("X", array.objectAtIndex(5));

    string = prefix + "||||X|";

    array = NSArray.componentsSeparatedByString(string, "|");
    assertEquals(256, string.length());
    assertEquals(11, array.count());
    assertEquals("", array.objectAtIndex(10));
    assertEquals("X", array.objectAtIndex(9));

    string = prefix + "|||||X";

    array = NSArray.componentsSeparatedByString(string, "|");
    assertEquals(256, string.length());
    assertEquals(11, array.count());
    assertEquals("", array.objectAtIndex(9));
    assertEquals("X", array.objectAtIndex(10));

    string = multiPrefix + "X|||||||||";
    array = NSArray.componentsSeparatedByString(string, "||");
    assertEquals(255, string.length());
    assertEquals(10, array.count());
    assertEquals("|", array.objectAtIndex(9));

    string = multiPrefix + "||||||||||";

    array = NSArray.componentsSeparatedByString(string, "||");
    assertEquals(255, string.length());
    assertEquals(11, array.count());
    assertEquals("", array.objectAtIndex(10));

    string = multiPrefix + "X||||||||||";

    array = NSArray.componentsSeparatedByString(string, "||");
    assertEquals(256, string.length());
    assertEquals(11, array.count());
    assertEquals("", array.objectAtIndex(10));
    assertEquals("X", array.objectAtIndex(5));

    string = multiPrefix + "||||||||X||";

    array = NSArray.componentsSeparatedByString(string, "||");
    assertEquals(256, string.length());
    assertEquals(11, array.count());
    assertEquals("", array.objectAtIndex(10));
    assertEquals("X", array.objectAtIndex(9));

    string = multiPrefix + "||||||||||X";

    array = NSArray.componentsSeparatedByString(string, "||");
    assertEquals(256, string.length());
    assertEquals(11, array.count());
    assertEquals("", array.objectAtIndex(9));
    assertEquals("X", array.objectAtIndex(10));
  }
  
  public void testComponentsSeparatedByStringNull() {
    NSArray<String> array = NSArray.componentsSeparatedByString("a,b,c", null);
    assertEquals(new NSArray<String>(new String[] { "a,b,c" }), array);    
  }

  
  public void test_MutableComponentsSeparatedByString() {
    NSArray<String> array = NSArray._mutableComponentsSeparatedByString("a,b,c", ",");
    array.add("d");
    assertEquals(array, new NSArray<String>(new String[] { "a", "b", "c", "d" }));

    array = NSArray._mutableComponentsSeparatedByString("a||b||c", "||");
    array.add("d");
    assertEquals(new NSArray<String>(new String[] { "a", "b", "c", "d" }), array);
  }
  
  public void testIterator() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def", "ghi" });
    Iterator<String> i = array.iterator();
    assertTrue(i.hasNext());
    NSMutableArray<String> array2 = new NSMutableArray<String>();
    while (i.hasNext())
      array2.add(i.next());
    assertEquals(array, array2);
  }

  public void testIteratorInvalid() {
    NSArray<String> array = new NSArray<String>(new String[] { "abc", "def", "ghi" });
    Iterator<String> i = array.iterator();
    i.next();
    try {
      i.remove();
      fail("UnsupportedOperationException expected");
    } catch (UnsupportedOperationException e) {
    }
  }
  
  public void testToArray() {
    String[] str = new String[] { "abc", "def", "ghi" };
    NSArray<String> array = new NSArray<String>(str);
    Object[] array2 = array.toArray();
    assertEquals(str, array2);    
    array2[0] = "xyz";
    assertEquals("abc", array.objectAtIndex(0));
  }
  
  public void testToArrayTArray() {
    String[] str = new String[] { "abc", "def", "ghi" };
    NSArray<String> array = new NSArray<String>(str);
    Object[] array2 = array.toArray(new String[array.size()]);
    assertEquals(str, array2);    
    array2[0] = "xyz";
    assertEquals(str[0], array.objectAtIndex(0));
    
    try {
      array2[0] = new Object();
      fail("ArrayStoreException expected");
    } catch (ArrayStoreException e) {
    }
    
    array2 = array.toArray(new String[0]);
    assertEquals(str, array2);
  }
  
  /* Cannot test this on GWT because it attempts to cast 'null' to an array */  
  public void testToArrayTArrayNull() {
//    String[] str = new String[] { "abc", "def", "ghi" };
//    NSArray<String> array = new NSArray<String>(str);
//    try {
//      array.toArray(null);
//      fail("NullPointerException expected");
//    } catch (NullPointerException e) {
//    }
  }

}
