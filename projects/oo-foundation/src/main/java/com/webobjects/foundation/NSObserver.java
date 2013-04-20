package com.webobjects.foundation;

import com.webobjects.foundation.NSKeyValueObserving.KeyValueChange;

public interface NSObserver {

  public void observeValueForKeyPath(String keyPath, NSObservable targetObject, KeyValueChange changes, Object context);

}