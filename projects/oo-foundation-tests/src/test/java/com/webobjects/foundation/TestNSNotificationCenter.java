package com.webobjects.foundation;

import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSNotification;
import com.webobjects.foundation.NSNotificationCenter;
import com.webobjects.foundation.NSSelectable;
import com.webobjects.foundation.NSSelector;

public class TestNSNotificationCenter extends BaseTestCase {

  public static class TestObserver implements NSSelectable {
    boolean invoked = false;
    Object userInfo = null;
    public void invoked(NSNotification notification) {
      invoked = true;
      userInfo = notification.userInfo();
    }
  }
  
  public void testDefaultCenter() {
    NSNotificationCenter nc = NSNotificationCenter.defaultCenter();
    assertNotNull(nc);
  }

  public void testAddObserver() {
    NSNotificationCenter nc = NSNotificationCenter.defaultCenter();
    TestObserver observer = new TestObserver();
    nc.addObserver(observer, new NSSelector<Void>("invoked", new Class[] { NSNotification.class }), "test", null);
    try {
      nc.addObserver(observer, new NSSelector<Void>("invoked", new Class[] { Object.class }), "test", null);
      fail("IllegalArgumentException expected");
    } catch (IllegalArgumentException e) {
    }
  }

  public void testPostNotificationNSNotification() {
    NSNotificationCenter nc = NSNotificationCenter.defaultCenter();
    TestObserver observer = new TestObserver();
    nc.addObserver(observer, new NSSelector<Void>("invoked", new Class[] { NSNotification.class }), "test", null);
    NSNotification notification = new NSNotification("test", null);
    nc.postNotification(notification);
    assertTrue(observer.invoked);
  }

  public void testPostNotificationStringObject() {
    NSNotificationCenter nc = NSNotificationCenter.defaultCenter();
    TestObserver observer = new TestObserver();
    nc.addObserver(observer, new NSSelector<Void>("invoked", new Class[] { NSNotification.class }), "test", null);
    nc.postNotification("test", this);
    assertTrue(observer.invoked);
  }

  public void testPostNotificationStringObjectNSDictionaryOfStringObject() {
    NSNotificationCenter nc = NSNotificationCenter.defaultCenter();
    TestObserver observer = new TestObserver();
    nc.addObserver(observer, new NSSelector<Void>("invoked", new Class[] { NSNotification.class }), "test", null);
    NSDictionary<String, Object> userInfo = new NSDictionary<String, Object>("value", "key");
    nc.postNotification("test", this, userInfo);
    assertTrue(observer.invoked);
    assertEquals(userInfo, observer.userInfo);
  }

  public void testRemoveObserverObject() {
    NSNotificationCenter nc = NSNotificationCenter.defaultCenter();
    TestObserver observer = new TestObserver();
    nc.addObserver(observer, new NSSelector<Void>("invoked", new Class[] { NSNotification.class }), "test", null);
    nc.removeObserver(observer);
    nc.postNotification("test", this);
    assertFalse(observer.invoked);
  }

  public void testRemoveObserverObjectStringObject() {
    NSNotificationCenter nc = NSNotificationCenter.defaultCenter();
    TestObserver observer1 = new TestObserver();
    TestObserver observer2 = new TestObserver();
    nc.addObserver(observer1, new NSSelector<Void>("invoked", new Class[] { NSNotification.class }), "test", null);
    nc.addObserver(observer2, new NSSelector<Void>("invoked", new Class[] { NSNotification.class }), "test", null);
    nc.removeObserver(observer2, "test", null);
    nc.postNotification("test", this);
    assertTrue(observer1.invoked);
    assertFalse(observer2.invoked);
  }

}
