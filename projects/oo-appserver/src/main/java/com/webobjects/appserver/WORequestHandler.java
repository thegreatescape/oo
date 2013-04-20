package com.webobjects.appserver;

public abstract class WORequestHandler {

	protected WORequestHandler() {
		super();
	}

	public abstract WOResponse handleRequest( WORequest request );
}
