package com.webobjects.appserver;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;

import com.webobjects.appserver._private.NotImplementedException;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSNumberFormatter;
import com.webobjects.foundation.NSTimestamp;
import com.webobjects.foundation.NSTimestampFormatter;

public class WORequest extends WOMessage {

	private String _method;
	private String _uri;

	protected WORequest() {}

	public WORequest( String method, String url, String httpVersion, Map headers, NSData content, Map userInfo ) {
		setMethod( method );
		_uri = url;
		setHTTPVersion( httpVersion );
		setHeaders( headers );
		setContent( content );
		setUserInfo( userInfo );
	}

	public String adaptorPrefix() {
		throw new NotImplementedException();
	}

	public static String ajaxFormSubmitKey() {
		throw new NotImplementedException();
	}

	public static String applicationName() {
		throw new NotImplementedException();
	}

	public static int applicationNumber() {
		throw new NotImplementedException();
	}

	public static int applicationURLPrefix() {
		throw new NotImplementedException();
	}

	public NSArray browserLanguages() {
		throw new NotImplementedException();
	}

	@Override
	public Object clone() {
		throw new NotImplementedException();
	}

	public InputStream contentInputStream() {
		throw new NotImplementedException();
	}

	protected String contentType() {
		throw new NotImplementedException();
	}

	public WOContext context() {
		throw new NotImplementedException();
	}

	@Override
	public NSArray cookies() {
		throw new NotImplementedException();
	}

	public String cookieValueForKey( String key ) {
		throw new NotImplementedException();
	}

	public NSDictionary cookieValues() {
		throw new NotImplementedException();
	}

	public NSArray cookieValuesForKey() {
		throw new NotImplementedException();
	}

	public NSTimestamp dateFormValueForKey( String key, NSTimestampFormatter dateFormatter ) {
		throw new NotImplementedException();
	}

	public NSTimestamp dateFormValueForKey( String key, SimpleDateFormat dateFormatter ) {
		throw new NotImplementedException();
	}

	public String defaultFormValueEncoding() {
		throw new NotImplementedException();
	}

	protected String detectFormValuesEncoding( String content ) {
		throw new NotImplementedException();
	}

	public String formValueEncoding() {
		throw new NotImplementedException();
	}

	public String formValueForKey( String key ) {
		throw new NotImplementedException();
	}

	public NSArray<String> formValueKeys( String key ) {
		throw new NotImplementedException();
	}

	public NSDictionary<String,Object> formValues( String key ) {
		throw new NotImplementedException();
	}

	public NSArray formValuesForKey( String key ) {
		throw new NotImplementedException();
	}

	protected NSDictionary getFormValuesFromMultipartFormData() {
		throw new NotImplementedException();
	}

	protected NSDictionary getFormValuesFromURLEncoding() {
		throw new NotImplementedException();
	}

	public boolean isAjaxSubmit() {
		throw new NotImplementedException();
	}

	public boolean isFormValueEncodingDetectionEnabled() {
		throw new NotImplementedException();
	}

	@Deprecated
	public boolean isFromClientComponent() {
		throw new NotImplementedException();
	}

	public boolean isMultipartFormData() {
		throw new NotImplementedException();
	}

	protected boolean isRequestFromFormSubmission() {
		throw new NotImplementedException();
	}

	public boolean isSecure() {
		throw new NotImplementedException();
	}

	public boolean isSessionIDInRequest() {
		throw new NotImplementedException();
	}

	public boolean isUsingWebServer() {
		throw new NotImplementedException();
	}

	protected boolean isWhitespaceOrNull( char value ) {
		throw new NotImplementedException();
	}

	public void setMethod( String value ) {
		_method = value;
	}

	public String method() {
		return _method;
	}

	public WOMultipartIterator multipartIterator() {
		throw new NotImplementedException();
	}

	public Number numericFormValueForKey( String key, NSNumberFormatter formatter ) {
		throw new NotImplementedException();
	}

	public WOAdaptor originatingAdaptor() {
		throw new NotImplementedException();
	}

	public String queryString() {
		throw new NotImplementedException();
	}

	protected String removeCRLF( String value ) {
		throw new NotImplementedException();
	}

	public String requestHandlerKey() {
		throw new NotImplementedException();
	}

	public String requestHandlerPath() {
		throw new NotImplementedException();
	}

	public NSArray<String> requestHandlerPathArray() {
		throw new NotImplementedException();
	}

	public String sessionID() {
		throw new NotImplementedException();
	}

	public static void setAjaxFormSubmitKey( String value ) {
		throw new NotImplementedException();
	}

	public void setContext( WOContext context ) {
		throw new NotImplementedException();
	}

	public void setDefaultFormValueEncoding( String encoding ) {
		throw new NotImplementedException();
	}

	protected void setFormValueEncoding( String encoding ) {
		throw new NotImplementedException();
	}

	public void setFormValueEncodingDetactionEnabled( boolean flag ) {
		throw new NotImplementedException();
	}

	public String stringformValueForKey( String key ) {
		throw new NotImplementedException();
	}

	@Override
	public String toString() {
		return Objects.toString( this );
	}

	public String uri() {
		return _uri;
	}
}