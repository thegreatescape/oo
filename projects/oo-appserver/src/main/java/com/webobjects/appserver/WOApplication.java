package com.webobjects.appserver;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.appserver._private.NotImplementedException;
import com.webobjects.appserver._private.Util;
import com.webobjects.appserver._private.WODefaultAdaptor;
import com.webobjects.appserver.parser.woml.WOMLDefaultNamespaceProvider;
import com.webobjects.appserver.parser.woml.WOMLNamespaceProvider;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSKeyValueCoding;
import com.webobjects.foundation.NSKeyValueCodingAdditions;
import com.webobjects.foundation.NSMutableDictionary;

public class WOApplication implements WOApplicationMBean, NSKeyValueCoding, NSKeyValueCoding.ErrorHandling, NSKeyValueCodingAdditions {

	private static final Logger logger = LoggerFactory.getLogger( WOApplication.class );

	public static String ApplicationDidDispatchRequestNotification = "";
	public static String ApplicationDidFinishLaunchingNotification = "";
	public static String ApplicationWillDispatchRequestNotification = "";
	public static String ApplicationWillFinishLaunchingNotification = "";
	public static String CGIAdaptorURLProperty = "";
	public static String DirectoryAliasProperty = "";
	public static String DocumentRootProperty = "";

	private String _name = "DEFAULTNAME";

	private NSDictionary<String, WORequestHandler> _requestHandlers = new NSDictionary<String, WORequestHandler>();
	private String _directActionRequestHandlerKey = "wa";
	private static WOApplication _application;

	public static void main( String[] args ) {
		main( args, WOApplication.class );
	}

	public static void main( String[] args, Class applicationClass ) {
		WODefaultAdaptor.listen();

		try {
			_application = (WOApplication)applicationClass.newInstance();
		}
		catch( InstantiationException e ) {
			e.printStackTrace();
		}
		catch( IllegalAccessException e ) {
			e.printStackTrace();
		}

		_application.registerRequestHandler( new WODirectActionRequestHandler(), "wa" );
	}

	public WOApplication() {}

	public String defaultEncoding() {
		return "utf-8";
	}

	public int activeSessionsCount() {
		throw new NotImplementedException();
	}

	public String adaptor() {
		throw new NotImplementedException();
	}

	public String[] adaptorExtensions() {
		throw new NotImplementedException();
	}

	public String adaptorName() {
		throw new NotImplementedException();
	}

	public String adaptorPath() {
		throw new NotImplementedException();
	}

	public NSArray adaptors() {
		throw new NotImplementedException();
	}

	public boolean adaptorsDispatchRequestsConcurrently() {
		throw new NotImplementedException();
	}

	public WOAdaptor adaptorWithName( String aClassName, NSDictionary anArgsDictionary ) {
		throw new NotImplementedException();
	}

	public NSArray additionalAdaptors() {
		throw new NotImplementedException();
	}

	public String ajaxRequstHandlerKey() {
		throw new NotImplementedException();
	}

	public boolean allowsConcurrentRequestHandling() {
		throw new NotImplementedException();
	}

	public void appendToResponse( WOResponse response, WOContext context ) {
		throw new NotImplementedException();
	}

	public static WOApplication application() {
		return _application;
	}

	public String applicationBaseURL() {
		throw new NotImplementedException();
	}

	public String applicationExtension() {
		throw new NotImplementedException();
	}

	public WOAssociationFactory associationFactory() {
		throw new NotImplementedException();
	}

	public WOAssociationFactoryRegistry associationFactoryRegistry() {
		throw new NotImplementedException();
	}

	public boolean autoOpenClientApplication() {
		throw new NotImplementedException();
	}

	public boolean autoOpenInBrowser() {
		throw new NotImplementedException();
	}

	public void awake() {
		throw new NotImplementedException();
	}

	public String baseURL() {
		throw new NotImplementedException();
	}

	public static boolean canAccessFieldsDirectly() {
		throw new NotImplementedException();
	}

	public String cgiAdaptorURL() {
		throw new NotImplementedException();
	}

	protected URL combinedComponentPathURL( WOResourceManager resourceManager, String templateName, String frameworkName, String language, boolean refreshProjectOnCacheMiss ) {
		throw new NotImplementedException();
	}

	public String componentRequestHandlerKey() {
		throw new NotImplementedException();
	}

	public String contextClassName() {
		throw new NotImplementedException();
	}

	public WOContext createContextForRequest( WORequest request ) {
		throw new NotImplementedException();
	}

	protected WOAssociationFactoryRegistry createDefaultAssociationFactoryRegistry() {
		throw new NotImplementedException();
	}

	protected WOMLDefaultNamespaceProvider createDefaultNamespaceProvider() {
		throw new NotImplementedException();
	}

	public WORequest createRequest( String method, String url, String httpVersion, Map headers, NSData content, Map info ) {
		throw new NotImplementedException();
	}

	public WOResourceManager createResourceManager() {
		throw new NotImplementedException();
	}

	public WOResponse createResponseInContext( WOContext context ) {
		throw new NotImplementedException();
	}

	public WOSession createSessionForRequest( WORequest request ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void debugString( String string ) {
		throw new NotImplementedException();
	}

	public WOAdaptor defaultAdaptor() {
		throw new NotImplementedException();
	}

	public WORequestHandler defaultRequestHandler() {
		throw new NotImplementedException();
	}

	public String defaultRequestHandlerClassName() {
		throw new NotImplementedException();
	}

	public Number defaultUndoStackLimit() {
		throw new NotImplementedException();
	}

	private String directActionRequestHandlerKey() {
		return _directActionRequestHandlerKey;
	}

	public String directConnectURL() {
		throw new NotImplementedException();
	}

	public WOResponse dispatchRequest( WORequest request ) {
		WORequestHandler r = requestHandlerForKey( directActionRequestHandlerKey() );
		return r.handleRequest( request );
	}

	public String documentRoot() {
		throw new NotImplementedException();
	}

	public WOElement dynamicElementWithName( String name, NSDictionary associations, WOElement element, NSArray languages ) {
		throw new NotImplementedException();
	}

	public String frameworksBaseURL() {
		throw new NotImplementedException();
	}

	public boolean getAdaptorsDispatchRequestsConcurrently() {
		throw new NotImplementedException();
	}

	public ArrayList getAdditionalAdaptors() {
		throw new NotImplementedException();
	}

	public String getAgentID() {
		throw new NotImplementedException();
	}

	public boolean getAllowsConcurrentRequestHandling() {
		throw new NotImplementedException();
	}

	public String getApplicationBaseURL() {
		throw new NotImplementedException();
	}

	public String getApplicationExtension() {
		throw new NotImplementedException();
	}

	public boolean getAutoOpenClientApplication() {
		throw new NotImplementedException();
	}

	public String getBaseURL() {
		throw new NotImplementedException();
	}

	public String getCGIAdaptorURL() {
		throw new NotImplementedException();
	}

	public ArrayList getClassPaths() {
		throw new NotImplementedException();
	}

	public String getComponentRequestHandlerKey() {
		throw new NotImplementedException();
	}

	public String getDefaultAdaptor() {
		throw new NotImplementedException();
	}

	public int getDefaultUndoStackLimit() {
		throw new NotImplementedException();
	}

	public String getDirectConnectURL() {
		throw new NotImplementedException();
	}

	public HashMap getFrameworkVersions() {
		throw new NotImplementedException();
	}

	public String getHost() {
		throw new NotImplementedException();
	}

	public String getHostAddress() {
		throw new NotImplementedException();
	}

	public boolean getIncludeCommentsInResponses() {
		throw new NotImplementedException();
	}

	public boolean getIsCachingEnabled() {
		throw new NotImplementedException();
	}

	public boolean getIsMonitorEnabled() {
		throw new NotImplementedException();
	}

	public boolean getIsPageRefreshOnBacktrackEnabled() {
		throw new NotImplementedException();
	}

	public boolean getIsRefusingNewSessions() {
		throw new NotImplementedException();
	}

	public boolean getIsTerminating() {
		throw new NotImplementedException();
	}

	public String getJMXDomain() {
		throw new NotImplementedException();
	}

	public int getLifebeatDestinationPort() {
		throw new NotImplementedException();
	}

	public int getLifebeatInterval() {
		throw new NotImplementedException();
	}

	public int getListenQueueSize() {
		throw new NotImplementedException();
	}

	public int getMaxSocketIdleTime() {
		throw new NotImplementedException();
	}

	public MBeanServer getMBeanServer() {
		throw new NotImplementedException();
	}

	public int getMinimumActiveSessionsCount() {
		throw new NotImplementedException();
	}

	public String getName() {
		throw new NotImplementedException();
	}

	public String getOutputPath() {
		throw new NotImplementedException();
	}

	public int getPageCacheSize() {
		throw new NotImplementedException();
	}

	public int getPageFragmentCacheSize() {
		throw new NotImplementedException();
	}

	public int getPort() {
		throw new NotImplementedException();
	}

	public String getServletConnectURL() {
		throw new NotImplementedException();
	}

	public int getSessionTimeOut() {
		throw new NotImplementedException();
	}

	public double getTimeOut() {
		throw new NotImplementedException();
	}

	public String getWebObjectsVersion() {
		throw new NotImplementedException();
	}

	public String getWebserverConnectURL() {
		throw new NotImplementedException();
	}

	public int getWorkerThreadCountMax() {
		throw new NotImplementedException();
	}

	public int getWorkerThreadCountMin() {
		throw new NotImplementedException();
	}

	public WOResponse handleActionRequestError( WORequest aRequest, Exception exception, String reason, WORequestHandler aHandler, String actionClassName, String actionName, Class actionClass, WOAction actionInstance ) {
		throw new NotImplementedException();
	}

	public WOResponse handleException( Exception exception, WOContext context ) {
		throw new NotImplementedException();
	}

	public NSMutableDictionary handleMalformedCookieString( RuntimeException anException, String cookieString, NSMutableDictionary aReturnDict ) {
		throw new NotImplementedException();
	}

	public WOResponse handlePageRestorationErrorInContext( WOContext context ) {
		throw new NotImplementedException();
	}

	public Object handleQueryWithUnboundKey( String key ) {
		throw new NotImplementedException();
	}

	public WORequestHandler handlerForRequest( WORequest request ) {
		throw new NotImplementedException();
	}

	public WOResponse handleSessionCreationErrorInContext( WOContext context ) {
		throw new NotImplementedException();
	}

	public WOResponse handleSessionRestorationErrorInContext( WOContext context ) {
		throw new NotImplementedException();
	}

	public void handleTakeValueForUnboundKey( Object value, String key ) {
		throw new NotImplementedException();
	}

	public String host() {
		throw new NotImplementedException();
	}

	public InetAddress hostAddress() {
		throw new NotImplementedException();
	}

	public boolean includeCommentsInResponse() {
		throw new NotImplementedException();
	}

	public String instanceIdKey() {
		throw new NotImplementedException();
	}

	public WOActionResults invokeAction( WORequest request, WOContext context ) {
		throw new NotImplementedException();
	}

	public boolean isCachingEnabled() {
		throw new NotImplementedException();
	}

	public boolean isConcurrentRequestHandlingEnabled() {
		throw new NotImplementedException();
	}

	public boolean isDebuggingEnabled() {
		throw new NotImplementedException();
	}

	public boolean isDirectConnectEnabled() {
		throw new NotImplementedException();
	}

	public boolean isMonitorEnabled() {
		throw new NotImplementedException();
	}

	public boolean isPageRefreshOnBacktrackEnabled() {
		throw new NotImplementedException();
	}

	public boolean isRefusingNewSessions() {
		throw new NotImplementedException();
	}

	public boolean isTerminating() {
		throw new NotImplementedException();
	}

	public String[] launchArguments() {
		throw new NotImplementedException();
	}

	@Deprecated
	public static int licensedRequestLimit() {
		throw new NotImplementedException();
	}

	@Deprecated
	public static int licensedRequestWindow() {
		throw new NotImplementedException();
	}

	@Deprecated
	public static int licensingAllowsMultipleInstances() {
		throw new NotImplementedException();
	}

	@Deprecated
	public static int licensingAllowsMultipleThreads() {
		throw new NotImplementedException();
	}

	public int lifebeatDestinationPort() {
		throw new NotImplementedException();
	}

	public boolean lifebeatEnabled() {
		throw new NotImplementedException();
	}

	public int lifebeatInterval() {
		throw new NotImplementedException();
	}

	public Number listenQueueSize() {
		throw new NotImplementedException();
	}

	public NSArray loadFrameworks() {
		throw new NotImplementedException();
	}

	@Deprecated
	public void lock() {
		throw new NotImplementedException();
	}

	public void logSetValueForDeclarationNamed( String aDeclarationName, String aDeclarationType, String aBindingName, String anAssociationDescription, Object aValue ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void logString( String string ) {
		throw new NotImplementedException();
	}

	public void logTakeValueForDeclarationNamed( String aDeclarationName, String aDeclarationType, String aBindingName, String anAssociationDescription, Object aValue ) {
		throw new NotImplementedException();
	}

	public Number maxSocketIdleTime() {
		throw new NotImplementedException();
	}

	public int minimumActiveSessionCount() {
		throw new NotImplementedException();
	}

	@Deprecated
	public boolean monitorEnabled() {
		throw new NotImplementedException();
	}

	@Deprecated
	public String monitorHost() {
		throw new NotImplementedException();
	}

	@Deprecated
	public boolean monitoringEnabled() {
		throw new NotImplementedException();
	}

	public String name() {
		return _name;
	}

	public WOMLNamespaceProvider namespaceProvider() {
		throw new NotImplementedException();
	}

	public WODynamicURL newDynamicURL() {
		throw new NotImplementedException();
	}

	public WODynamicURL newDynamicURL( String url ) {
		throw new NotImplementedException();
	}

	public String number() {
		throw new NotImplementedException();
	}

	public String outputPath() {
		throw new NotImplementedException();
	}

	public int pageCacheSize() {
		throw new NotImplementedException();
	}

	public int pageFragmentCacheSize() {
		throw new NotImplementedException();
	}

	public WOComponent pageWithName( String name, WOContext context ) {
		throw new NotImplementedException();
	}

	public String path() {
		throw new NotImplementedException();
	}

	public int permanentPageCacheSize() {
		throw new NotImplementedException();
	}

	public Number port() {
		throw new NotImplementedException();
	}

	@Deprecated
	public static void primeApplication( String mainBundlePath, String nameOfApplicationSubclass ) {
		throw new NotImplementedException();
	}

	public static void primeApplication( String mainBundleName, URL mainBundlePathURL, String nameOfApplicationSubclass ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public boolean printsHTMLParserDiagnostics() {
		throw new NotImplementedException();
	}

	public NSArray projectSearchPath() {
		throw new NotImplementedException();
	}

	public String recordingPath() {
		throw new NotImplementedException();
	}

	public void refuseNewSessions( boolean value ) {
		throw new NotImplementedException();
	}

	public NSArray<String> registeredRequestHandlerKeys() {
		throw new NotImplementedException();
	}

	public void registerMBean( Object aMBean, ObjectName aName ) {
		throw new NotImplementedException();
	}

	public void registerMBean( Object aMBean, String aDomainName, String aMBeanName ) {
		throw new NotImplementedException();
	}

	public void registerRequestHandler( WORequestHandler requestHandler, String key ) {
		_requestHandlers = Util.dictionaryBySettingObjectForKey( _requestHandlers, new WODirectActionRequestHandler(), "wa" );
	}

	public WORequestHandler removeRequestHandlerForKey( String key ) {
		WORequestHandler handler = requestHandlerForKey( key );
		_requestHandlers = Util.dictionaryByRemovingObjectForKey( _requestHandlers, key );
		return handler;
	}

	public WORequestHandler requestHandlerForKey( String key ) {
		return _requestHandlers.objectForKey( key );
	}

	public Object requestHandlingLock() {
		throw new NotImplementedException();
	}

	public WOResourceManager resourceManager() {
		throw new NotImplementedException();
	}

	public String resourceRequestHandlerKey() {
		throw new NotImplementedException();
	}

	public WOResponse responseForComponentWithName( String name, Map bindings, Map headers, Map userInfo, String uriPrefix, String appName ) {
		throw new NotImplementedException();
	}

	public WOResponse responseForDirectActionWithNameAndClass( String actionName, String className, Map formValueDict, InputStream contentStream, Map headers, Map userInfo, String uriPrefix, String appName ) {
		throw new NotImplementedException();
	}

	public WOSession restoreSessionWithID( String aSessionID, WOContext aContext ) {
		throw new NotImplementedException();
	}

	public void run() {
		throw new NotImplementedException();
	}

	public void saveSessionForContext( WOContext context ) {
		throw new NotImplementedException();
	}

	public String servletConnectURL() {
		throw new NotImplementedException();
	}

	public String sessionIdKey() {
		throw new NotImplementedException();
	}

	public WOSessionStore sessionStore() {
		throw new NotImplementedException();
	}

	public String sessionStoreClassName() {
		throw new NotImplementedException();
	}

	public Number sessionTimeOut() {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setAdaptor( String aString ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setAdditionalAdaptors( NSArray a ) {
		throw new NotImplementedException();
	}

	public void setAllowsConcurrentRequestHandling( boolean value ) {
		throw new NotImplementedException();
	}

	public void setApplicationBaseURL( String aString ) {
		throw new NotImplementedException();
	}

	public void setAutoOpenClientApplication( boolean value ) {
		throw new NotImplementedException();
	}

	public void setAutoOpenInBrowser( boolean value ) {
		throw new NotImplementedException();
	}

	public void setCachingEnabled( boolean value ) {
		throw new NotImplementedException();
	}

	public void setCGIAdaptorURL( String value ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setComponentRequestHandlerKey( String key ) {
		throw new NotImplementedException();
	}

	public void setContextClassName( String value ) {
		throw new NotImplementedException();
	}

	public void setDefaultRequestHandler( WORequestHandler requestHandler ) {
		throw new NotImplementedException();
	}

	public void setDefaultUndoStackLimit( int stackLimit ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setDirectActionRequestHandlerKey( String key ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setDirectConnectEnabled( boolean value ) {
		throw new NotImplementedException();
	}

	public void setFrameworksBaseURL( String value ) {
		throw new NotImplementedException();
	}

	public void setIncludeCommentsInResponse( boolean value ) {
		throw new NotImplementedException();
	}

	public void setJMXDomain( String name ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setListenQueueSize( Number value ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setLoadFrameworks( NSArray a ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setMaxSocketIdleTime( Number value ) {
		throw new NotImplementedException();
	}

	public void setMinimumActiveSessionsCount( int value ) {
		throw new NotImplementedException();
	}

	public void setMonitorEnabled( boolean value ) {
		throw new NotImplementedException();
	}

	public void setMonitorHost( String value ) {
		throw new NotImplementedException();
	}

	public void setNamespaceProvider( WOMLNamespaceProvider value ) {
		throw new NotImplementedException();
	}

	public void setPageCacheSize( int value ) {
		throw new NotImplementedException();
	}

	public void setPageFragmentCacheSize( int value ) {
		throw new NotImplementedException();
	}

	public void setPageRefreshOnBacktrackEnabled( boolean value ) {
		throw new NotImplementedException();
	}

	public void setPermanentPageCacheSize( int value ) {
		throw new NotImplementedException();
	}

	public void setPort( Number port ) {
		throw new NotImplementedException();
	}

	public void setPrintsHTMLParserDiagnostics( boolean value ) {
		throw new NotImplementedException();
	}

	public void setProjectSearchPath( NSArray value ) {
		throw new NotImplementedException();
	}

	public void setRecordingPath( String value ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setResourceManager( WOResourceManager value ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setResourceRequestHandlerKey( String value ) {
		throw new NotImplementedException();
	}

	public void setSessionStore( WOSessionStore sessionStore ) {
		throw new NotImplementedException();
	}

	public void setSessionStoreClassName( String value ) {
		throw new NotImplementedException();
	}

	public void setSessionTimeOut( Number value ) {
		throw new NotImplementedException();
	}

	public void setSMTPHost( String value ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setSocketCacheSize( Number value ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setSocketMonitorSleepTime( Number value ) {
		throw new NotImplementedException();
	}

	public void setStatisticsStore( WOStatisticsStore value ) {
		throw new NotImplementedException();
	}

	public void setTimeOut( double value ) {
		throw new NotImplementedException();
	}

	public void setWebServiceRequestHandlerKey( String key ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void setWorkerThreadCount( Number threadCount ) {
		throw new NotImplementedException();
	}

	public void setWorkerThreadCountMax( Number threadCount ) {
		throw new NotImplementedException();
	}

	public void setWorkerThreadCountMin( Number threadCount ) {
		throw new NotImplementedException();
	}

	public Object sharedEditingContext() {
		throw new NotImplementedException( "Sorry, we do not support creation of editing contexts." );
	}

	public boolean shouldRestoreSessionOnCleanEntry( WORequest aRequest ) {
		throw new NotImplementedException();
	}

	public void sleep() {
		throw new NotImplementedException();
	}

	public String SMTPHost() {
		throw new NotImplementedException();
	}

	@Deprecated
	public Number socketCacheSize() {
		throw new NotImplementedException();
	}

	@Deprecated
	public Number socketMonitorSleepTime() {
		throw new NotImplementedException();
	}

	public NSDictionary statistics() {
		throw new NotImplementedException();
	}

	public WOStatisticsStore statisticsStore() {
		throw new NotImplementedException();
	}

	public String streamActionRequestHandlerKey() {
		throw new NotImplementedException();
	}

	public void takeValueForKey( Object value, String key ) {
		throw new NotImplementedException();
	}

	public void takeValueForKeyPath( Object value, String keyPath ) {
		throw new NotImplementedException();
	}

	public void takeValuesFromRequest( WORequest request, WOContext context ) {
		throw new NotImplementedException();
	}

	public void terminate() {
		throw new NotImplementedException();
	}

	@Deprecated
	public void terminateAfterTimeInterval( double interval ) {
		throw new NotImplementedException();
	}

	public double timeOut() {
		throw new NotImplementedException();
	}

	@Override
	public String toString() {
		return Objects.toString( this );
	}

	public void unableToSetNullForKey( String key ) {
		throw new NotImplementedException();
	}

	@Deprecated
	public void unlock() {
		throw new NotImplementedException();
	}

	public void unregisterMBean( ObjectName aName ) {
		throw new NotImplementedException();
	}

	public void validationFailedWithException( Throwable t, Object value, String keyPath, WOComponent component, WOSession session ) {
		throw new NotImplementedException();
	}

	public Object valueForKey( String key ) {
		throw new NotImplementedException();
	}

	public Object valueForKeyPath( String key ) {
		throw new NotImplementedException();
	}

	public boolean wasMainInvoked() {
		throw new NotImplementedException();
	}

	public String webserverConnectURL() {
		throw new NotImplementedException();
	}

	public String webServiceRequestHandlerKey() {
		throw new NotImplementedException();
	}

	@Deprecated
	public Number workerThreadCount() {
		throw new NotImplementedException();
	}

	public Number workerThreadCountMax() {
		throw new NotImplementedException();
	}

	public Number workerThreadCountMin() {
		throw new NotImplementedException();
	}
}