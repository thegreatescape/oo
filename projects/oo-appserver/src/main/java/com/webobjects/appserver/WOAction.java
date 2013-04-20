package com.webobjects.appserver;

import java.util.Objects;

import com.webobjects.appserver._private.NotImplementedException;
import com.webobjects.foundation.NSArray;

public abstract class WOAction {

	public static String actionText = "Goober";

	private WORequest _request;

	public WOAction( WORequest request ) {
		_request = request;
	}

	public static boolean canAccessFieldsDirectly() {
		throw new NotImplementedException();
	}

	public WOContext context() {
		throw new NotImplementedException();
	}

	@Deprecated
	public static void debugString( String string ) {
		throw new NotImplementedException();
	}

	public WOSession existingSession() {
		throw new NotImplementedException();
	}

	public abstract String getSessionIDForRequest( WORequest request );

	public Object handleQueryWithUnboundKey( String key ) {
		throw new NotImplementedException();
	}

	public void handleTakeValueForUnboundKey( Object value, String key ) {
		throw new NotImplementedException();
	}

	public void initializeRequestSessionIDInContext( WOContext context ) {
		throw new NotImplementedException();
	}

	public NSArray languages() {
		throw new NotImplementedException();
	}

	@Deprecated
	public static void logString( String string ) {
		throw new NotImplementedException();
	}

	public WOComponent pageWithName( String pageName ) {
		throw new NotImplementedException();
	}

	public abstract WOActionResults performActionNamed( String name );

	public WORequest request() {
		return _request;
	}

	public WOSession session() {
		throw new NotImplementedException();
	}

	public void setLanguages( NSArray languages ) {
		throw new NotImplementedException();
	}

	public void takeValueForKey( Object value, String key ) {
		throw new NotImplementedException();
	}

	public void takeValueForKeyPath( Object value, String keyPath ) {
		throw new NotImplementedException();
	}

	@Override
	public String toString() {
		return Objects.toString( this );
	}

	public void unableToSetNullForKey( String key ) {
		throw new NotImplementedException();
	}

	public Object validateTakeValueForKeyPath( Object value, String keyPath ) {
		throw new NotImplementedException();
	}

	public Object validateValueForKey( Object value, String key ) {
		throw new NotImplementedException();
	}

	public Object valueForKey( String key ) {
		throw new NotImplementedException();
	}

	public Object valueForKeyPath( String keyPath ) {
		throw new NotImplementedException();
	}
}