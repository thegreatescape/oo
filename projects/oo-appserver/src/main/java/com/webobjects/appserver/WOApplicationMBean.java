package com.webobjects.appserver;

import java.util.ArrayList;

public interface WOApplicationMBean {

	public boolean getAdaptorsDispatchRequestsConcurrently();
	public ArrayList getAdditionalAdaptors();
	public boolean getAllowsConcurrentRequestHandling();
	public String getApplicationBaseURL();
	public String getBaseURL();
	public String getCGIAdaptorURL();
	public ArrayList getClassPaths();
	public int getDefaultUndoStackLimit();
	public String getDirectConnectURL();
	public String getHost();
	public String getHostAddress();
	public boolean getIncludeCommentsInResponses();
	public boolean getIsCachingEnabled();
	public boolean getIsMonitorEnabled();
	public boolean getIsPageRefreshOnBacktrackEnabled();
	public boolean getIsRefusingNewSessions();
	public boolean getIsTerminating();
	public String getJMXDomain();
	public int getLifebeatDestinationPort();
	public int getLifebeatInterval();
	public int getListenQueueSize();
	public int getMaxSocketIdleTime();
	public int getMinimumActiveSessionsCount();
	public String getName();
	public String getOutputPath();
	public int getPageCacheSize();
	public int getPort();
	public String getServletConnectURL();
	public int getSessionTimeOut();
	public double getTimeOut();
	public String getWebObjectsVersion();
	public String getWebserverConnectURL();
	public int getWorkerThreadCountMax();
	public int getWorkerThreadCountMin();
}