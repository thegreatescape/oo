package testsuite;

import com.webobjects.foundation.TestNSArray;
import com.webobjects.foundation.TestNSDictionary;
import com.webobjects.foundation.TestNSMutableArray;
import com.webobjects.foundation.TestNSMutableDictionary;
import com.webobjects.foundation.TestNSMutableRange;
import com.webobjects.foundation.TestNSMutableSet;
import com.webobjects.foundation.TestNSNotificationCenter;
import com.webobjects.foundation.TestNSRange;
import com.webobjects.foundation.TestNSSelector;
import com.webobjects.foundation.TestNSSet;
import com.webobjects.foundation.TestNSTimestamp;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests extends TestSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite("Test for com.webobjects.foundation");
    //$JUnit-BEGIN$
    suite.addTestSuite(TestNSSelector.class);
    suite.addTestSuite(TestNSMutableSet.class);
    suite.addTestSuite(TestNSArray.class);
    suite.addTestSuite(TestNSTimestamp.class);
    suite.addTestSuite(TestNSMutableRange.class);
    suite.addTestSuite(TestNSMutableArray.class);
    suite.addTestSuite(TestNSDictionary.class);
    suite.addTestSuite(TestNSSet.class);
    suite.addTestSuite(TestNSRange.class);
    suite.addTestSuite(TestNSMutableDictionary.class);
    suite.addTestSuite(TestNSNotificationCenter.class);
    //$JUnit-END$
    return suite;
  }

}
