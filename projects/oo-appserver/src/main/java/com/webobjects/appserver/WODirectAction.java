package com.webobjects.appserver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.appserver._private.NotImplementedException;

public class WODirectAction extends WOAction {

	private static final Logger logger = LoggerFactory.getLogger( WODirectAction.class );

	public WODirectAction( WORequest request ) {
		super( request );
	}

	@Override
	public WOActionResults performActionNamed( String actionName ) {
		try {
			String methodName = actionName + "Action";
			Method m = getClass().getMethod( methodName, null );
			Object invoke = m.invoke( this, null );
			return (WOActionResults)invoke;
		}
		catch( NoSuchMethodException e ) {
			e.printStackTrace();
		}
		catch( SecurityException e ) {
			e.printStackTrace();
		}
		catch( IllegalAccessException e ) {
			e.printStackTrace();
		}
		catch( IllegalArgumentException e ) {
			e.printStackTrace();
		}
		catch( InvocationTargetException e ) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getSessionIDForRequest( WORequest request ) {
		throw new NotImplementedException();
	}

	public WOActionResults defaultAction() {
		WOResponse r = new WOResponse();
		r.setContent( "I am the default action" );
		r.setStatus( 200 );
		return r;
	}
}