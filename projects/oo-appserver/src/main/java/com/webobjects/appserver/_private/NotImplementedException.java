package com.webobjects.appserver._private;

public class NotImplementedException extends RuntimeException {

	public NotImplementedException() {
		this( "Not implemented" );
	}

	public NotImplementedException( String message ) {
		super( message );
	}
}