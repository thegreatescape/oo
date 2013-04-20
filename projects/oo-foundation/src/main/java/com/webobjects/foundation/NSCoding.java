package com.webobjects.foundation;



public abstract interface NSCoding
{
  public abstract Class<?> classForCoder();
  public abstract void encodeWithCoder(NSCoder paramNSCoder);

}