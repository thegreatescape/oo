package com.webobjects.appserver;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;

import com.webobjects.appserver._private.NotImplementedException;
import com.webobjects.appserver._private.Util;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSKeyValueCodingAdditions;

public class WOMessage implements NSKeyValueCoding, NSKeyValueCoding.ErrorHandling, NSKeyValueCodingAdditions {

	private static final Logger logger = LoggerFactory.getLogger( WOMessage.class );

	public static int HTTP_STATUS_FORBIDDEN = 403;
	public static int HTTP_STATUS_FOUND = 302;
	public static int HTTP_STATUS_INTERNAL_ERROR = 500;
	public static int HTTP_STATUS_MOVED_PERMANENTLY = 301;
	public static int HTTP_STATUS_NO_CONTENT = 204;
	public static int HTTP_STATUS_NOT_FOUND = 404;
	public static int HTTP_STATUS_OK = 200;

	protected static boolean[] map;
	protected static String TheDefaultResponseEncoding = defaultEncoding();

	private NSArray<WOCookie> _cookies = new NSArray<WOCookie>();
	private NSDictionary<String, NSArray<String>> _headers = new NSDictionary<String, NSArray<String>>();
	private NSData _content = NSData.EmptyData;
	private String _contentEncoding = WOApplication.application().defaultEncoding();
	private String _httpVersion;
	private NSDictionary _userInfo;

	public void addCookie( WOCookie cookie ) {
		_cookies = _cookies.arrayByAddingObject( cookie );
	}

	public void appendContentCharacter( char addition ) {
		throw new NotImplementedException();
	}

	public void appendContentBytes( byte[] toAppend ) {
		throw new NotImplementedException();
	}

	public void appendContentData( NSData addition ) {
		throw new NotImplementedException();
	}

	public void appendContentDOMDocumentFragment( DocumentFragment aDocumentFragment ) {
		throw new NotImplementedException();
	}

	public void appendContentHTMLAttributeValue( String aString ) {
		throw new NotImplementedException();
	}

	public void appendContentHTMLString( String aString ) {
		throw new NotImplementedException();
	}

	public void appendContentString( String stringToAppend ) {
		logger.debug( "appendContentString: {}", stringToAppend );

		try {
			byte[] bytesToAppend = stringToAppend.getBytes( contentEncoding() );
			appendContentData( new NSData( bytesToAppend ) );
		}
		catch( UnsupportedEncodingException e ) {
			e.printStackTrace();
		}
	}

	public void appendHeader( String header, String key ) {
		throw new NotImplementedException();
	}

	public void appendHeaders( List headers, String key ) {
		throw new NotImplementedException();
	}

	boolean canAccessFieldsDirectly() {
		throw new NotImplementedException();
	}

	@Override
	public Object clone() {
		throw new NotImplementedException();
	}

	public NSData content() {
		return _content;
	}

	public Document contentAsDOMDocument() {
		throw new NotImplementedException();
	}

	public String contentEncoding() {
		return _contentEncoding;
	}

	public String contentString() {
		try {
			return new String( content().bytes(), contentEncoding() );
		}
		catch( UnsupportedEncodingException e ) {
			throw new RuntimeException( "An error occurred while attempting to encode the content as a string", e );
		}
	}

	public NSArray cookies() {
		return _cookies;
	}

	public static String defaultEncoding() {
		return WOApplication.application().defaultEncoding();
	}

	public static String defaultHeaderEncoding() {
		return WOApplication.application().defaultEncoding();
	}

	public static String defaultURLEncoding() {
		return WOApplication.application().defaultEncoding();
	}

	@Override
	public boolean equals( Object obj ) {
		return Objects.equals( obj, this );
	}

	public Object handleQueryWithUnboundKey( String key ) {
		throw new NotImplementedException();
	}

	public void handleTakeValueForUnboundKey( Object value, String key ) {
		throw new NotImplementedException();
	}

	public boolean hasHeaderForKey( String key ) {
		throw new NotImplementedException();
	}

	public String headerForKey( Object key ) {
		throw new NotImplementedException();
	}

	public String headerForKey( Object key, String defaultValue ) {
		throw new NotImplementedException();
	}

	public NSArray headerKeys() {
		throw new NotImplementedException();
	}

	public NSDictionary headers() {
		return _headers;
	}

	public NSArray headersForKey( String key ) {
		throw new NotImplementedException();
	}

	public String httpVersion() {
		return _httpVersion;
	}

	public void removeCookie( WOCookie aCookie ) {
		throw new NotImplementedException();
	}

	public void removeHeadersForKey( String key ) {
		throw new NotImplementedException();
	}

	protected static boolean requiresHTMLEscaping() {
		throw new NotImplementedException();
	}

	public void setContent( byte[] bytes ) {
		setContent( new NSData( bytes ) );
	}

	public void setContent( char[] chars ) {
		for( char character : chars ) {
			appendContentCharacter( character );
		}
	}

	public void setContent( NSData value ) {
		setContent( value.bytes() );
	}

	public void setContent( String value ) {
		if( value == null ) {
			value = "";
		}

		try {
			setContent( value.getBytes( WOApplication.application().defaultEncoding() ) );
		}
		catch( UnsupportedEncodingException e ) {
			throw new RuntimeException( "An error occurred while attempting to convert string content to data", e );
		}
	}

	public void setContentDOMDocument() {
		throw new NotImplementedException();
	}

	public void setContentEncoding( String encoding ) {
		_contentEncoding = encoding;
	}

	public static void setDefaultEncoding( String encoding ) {
		throw new NotImplementedException();
	}

	public static void setDefaultURLEncoding( String encoding ) {
		throw new NotImplementedException();
	}

	public static void setDefaultHeaderEncoding( String encoding ) {
		throw new NotImplementedException();
	}

	public void setHeader( String value, String key ) {
		throw new NotImplementedException();
	}

	public void setHeaders( List values, String key ) {
		throw new NotImplementedException();
	}

	public void setHeaders( Map headers ) {
		_headers = NSDictionary.asDictionary( headers );
	}

	public void setHTTPVersion( String value ) {
		_httpVersion = value;
	}

	public void setStorePageInBacktrackCache( boolean value ) {
		throw new NotImplementedException();
	}

	public void setUserInfo( Map map ) {
		_userInfo = NSDictionary.asDictionary( map );
	}

	public void setUserInfoForKey( Object value, String key ) {
		setUserInfo( Util.dictionaryBySettingObjectForKey( userInfo(), value, key ) );
	}

	public boolean storePageInBacktrackCache() {
		throw new NotImplementedException();
	}

	public static String stringByEscapingHTMLAttributeValue( String string ) {
		throw new NotImplementedException();
	}

	public static String stringByEscapingHTMLString( String string ) {
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

	public NSDictionary userInfo() {
		return _userInfo;
	}

	public Object userInfoForKey( String key ) {
		return userInfo().objectForKey( key );
	}

	public Object valueForKey( String key ) {
		throw new NotImplementedException();
	}

	public Object valueForKeyPath( String keyPath ) {
		throw new NotImplementedException();
	}
}