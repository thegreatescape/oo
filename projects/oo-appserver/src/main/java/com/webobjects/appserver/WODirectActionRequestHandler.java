package com.webobjects.appserver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WODirectActionRequestHandler extends WORequestHandler {

	private static final Logger logger = LoggerFactory.getLogger( WODirectActionRequestHandler.class );

	@Override
	public WOResponse handleRequest( WORequest request ) {
		String directActionClassName = "com.webobjects.appserver.WODirectAction";
		String directActionMethodName = "default";
		WOActionResults results = invokeDirectAction( directActionClassName, directActionMethodName, request );
		return results.generateResponse();
	}

	private WOActionResults invokeDirectAction( String directActionClassName, String directActionName, WORequest request ) {

		try {
			Class<? extends WODirectAction> directActionClass = (Class<? extends WODirectAction>)Class.forName( directActionClassName );
			Constructor<? extends WODirectAction> constructor = directActionClass.getConstructor( WORequest.class );
			WODirectAction newInstance = constructor.newInstance( request );
			return newInstance.performActionNamed( directActionName );
		}
		catch( ClassNotFoundException e ) {
			logger.error( "The direct action class {} was not found", directActionClassName, e );
			e.printStackTrace();
		}
		catch( NoSuchMethodException e ) {
			e.printStackTrace();
		}
		catch( SecurityException e ) {
			e.printStackTrace();
		}
		catch( InstantiationException e ) {
			e.printStackTrace();
		}
		catch( IllegalAccessException e ) {
			e.printStackTrace();
		}
		catch( IllegalArgumentException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( InvocationTargetException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}