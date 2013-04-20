package com.webobjects.appserver;

public class WOResponse extends WOMessage implements WOActionResults {

	private int _status = 200;

	public int status() {
		return _status;
	}

	public void setStatus( int value ) {
		_status = value;
	}

	public WOResponse generateResponse() {
		return this;
	}
}