package com.webobjects.appserver;

import com.webobjects.appserver._private.NotImplementedException;
import com.webobjects.foundation.NSDictionary;

public abstract class WOAdaptor {

	private String _name;
	private NSDictionary _arguments = new NSDictionary();

	// WOListenQueueSize
	protected static int DefaultListenQueueSize = 32;

	public WOAdaptor(String name, NSDictionary arguments) {
		_name = name;
		_arguments = arguments;
	}

	public boolean dispatchesRequestsConcurrently() {
		throw new NotImplementedException();
	}

	public boolean doesBusyRunOnce() {
		throw new NotImplementedException();
	}

	public int port() {
		throw new NotImplementedException();
	}

	public abstract void registerForEvents();

	public void runOnce() {
		throw new NotImplementedException();
	}

	@Override
	public String toString() {
		throw new NotImplementedException();
	}

	public abstract void unregisterForEvents();
}
