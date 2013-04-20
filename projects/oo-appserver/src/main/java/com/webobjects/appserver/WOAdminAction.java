package com.webobjects.appserver;

import com.webobjects.appserver._private.NotImplementedException;

public class WOAdminAction extends WODirectAction {

	public WOAdminAction( WORequest request ) {
		super( request );
	}

	public WOActionResults instanceRequestAction() {
		throw new NotImplementedException();
	}

	public WOActionResults pingAction() {
		WOResponse r = new WOResponse();
		r.setContent( "ALIVE" );
		return r;
	}
}