package com.webobjects.foundation;

public abstract interface _NSFoundationCollection {
  public abstract int _shallowHashCode();

  public enum NullHandling {
    CheckAndFail, CheckAndSkip, NoCheck
  }
}