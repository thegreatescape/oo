package com.webobjects.appserver._private;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

import org.apache.http.ConnectionClosedException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpServerConnection;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestHandler;
import org.apache.http.protocol.HttpRequestHandlerRegistry;
import org.apache.http.protocol.HttpService;
import org.apache.http.protocol.ImmutableHttpProcessor;
import org.apache.http.protocol.ResponseConnControl;
import org.apache.http.protocol.ResponseContent;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.protocol.ResponseServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webobjects.appserver.WOAdaptor;
import com.webobjects.appserver.WOApplication;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSData;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableDictionary;

public class WODefaultAdaptor extends WOAdaptor {

	public WODefaultAdaptor( String name, NSDictionary arguments ) {
		super( name, arguments );
	}

	private static final Logger logger = LoggerFactory.getLogger( WODefaultAdaptor.class );

	public static void listen() {
		try {
			Thread t = new RequestListenerThread( 1200 );
			t.setDaemon( false );
			t.start();
		}
		catch( Exception e ) {
			logger.error( "Error while registering WOAdaptor", e );
		}
	}

	static class Handler implements HttpRequestHandler {

		public void handle( HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext ) throws HttpException, IOException {
			WOResponse woresponse = WOApplication.application().dispatchRequest( httpRequestToWORequest( httpRequest ) );
			assignWOResponseToHttpResponse( woresponse, httpResponse );
		}

		private static WORequest httpRequestToWORequest( HttpRequest httpRequest ) {
			String method = httpRequest.getRequestLine().getMethod().toUpperCase(Locale.ENGLISH);
			String uri = httpRequest.getRequestLine().getUri();
			String httpVersion = httpRequest.getProtocolVersion().toString();
			NSDictionary headers = headers( httpRequest );
			NSData content = null;
			NSDictionary userInfo = null;
			WORequest woRequest = new WORequest( method, uri, httpVersion, headers, content, userInfo );
			return woRequest;
		}

		/**
		 * FIXME: We need to account for headers with multiple values.
		 */
		private static NSDictionary<String,NSArray<String>> headers( HttpRequest httpRequest ) {
			NSMutableDictionary<String,NSArray<String>> headers = new NSMutableDictionary<String, NSArray<String>>();

			for( HeaderIterator i = httpRequest.headerIterator() ; i.hasNext() ; ) {
				Header header = i.nextHeader();
				String name = header.getName();
				NSArray<String> values = new NSArray<String>( header.getValue() );
				headers.setObjectForKey( values, name );
				/*
				NSArray<HeaderElement> values = new NSArray<String>( header.getElements() );
				*/
			}

			return headers;
		}

		private static void assignWOResponseToHttpResponse( WOResponse woResponse, HttpResponse httpResponse ) {
			StringEntity entity = new StringEntity( woResponse.contentString(), ContentType.create( "text/html", WOApplication.application().defaultEncoding() ) );
			httpResponse.setEntity( entity );
			httpResponse.setStatusCode( woResponse.status() );
		}
	}

	static class RequestListenerThread extends Thread {

		private final ServerSocket serversocket;
		private final HttpParams params;
		private final HttpService httpService;

		public RequestListenerThread( int port ) throws IOException {
			this.serversocket = new ServerSocket( port );
			this.params = new SyncBasicHttpParams();
			this.params.setIntParameter( CoreConnectionPNames.SO_TIMEOUT, 5000 ).setIntParameter( CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8 * 1024 ).setBooleanParameter( CoreConnectionPNames.STALE_CONNECTION_CHECK, false ).setBooleanParameter( CoreConnectionPNames.TCP_NODELAY, true ).setParameter( CoreProtocolPNames.ORIGIN_SERVER, "HttpComponents/1.1" );

			// Set up the HTTP protocol processor
			HttpProcessor httpproc = new ImmutableHttpProcessor( new HttpResponseInterceptor[] {
			new ResponseDate(), new ResponseServer(), new ResponseContent(), new ResponseConnControl()
			} );

			// Set up request handlers
			HttpRequestHandlerRegistry reqistry = new HttpRequestHandlerRegistry();
			reqistry.register( "*", new Handler() );

			// Set up the HTTP service
			this.httpService = new HttpService( httpproc, new DefaultConnectionReuseStrategy(), new DefaultHttpResponseFactory(), reqistry, this.params );
		}

		@Override
		public void run() {
			logger.info( "Listening on port " + serversocket.getLocalPort() );

			while( !Thread.interrupted() ) {
				try {
					// Set up HTTP connection
					Socket socket = this.serversocket.accept();
					DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
					System.out.println( "Incoming connection from " + socket.getInetAddress() );
					conn.bind( socket, this.params );

					// Start worker thread
					Thread t = new WorkerThread( this.httpService, conn );
					t.setDaemon( true );
					t.start();
				}
				catch( InterruptedIOException ex ) {
					break;
				}
				catch( IOException e ) {
					System.err.println( "I/O error initialising connection thread: " + e.getMessage() );
					break;
				}
			}
		}
	}

	static class WorkerThread extends Thread {

		private final HttpService httpservice;
		private final HttpServerConnection conn;

		public WorkerThread( final HttpService httpservice, final HttpServerConnection conn ) {
			super();
			this.httpservice = httpservice;
			this.conn = conn;
		}

		@Override
		public void run() {
			System.out.println( "New connection thread" );
			HttpContext context = new BasicHttpContext( null );
			try {
				while( !Thread.interrupted() && this.conn.isOpen() ) {
					this.httpservice.handleRequest( this.conn, context );
				}
			}
			catch( ConnectionClosedException ex ) {
				System.err.println( "Client closed connection" );
			}
			catch( IOException ex ) {
				System.err.println( "I/O error: " + ex.getMessage() );
			}
			catch( HttpException ex ) {
				System.err.println( "Unrecoverable HTTP protocol violation: " + ex.getMessage() );
			}
			finally {
				try {
					this.conn.shutdown();
				}
				catch( IOException ignore ) {}
			}
		}
	}

	@Override
	public void registerForEvents() {
		throw new NotImplementedException();
	}

	@Override
	public void unregisterForEvents() {
		throw new NotImplementedException();
	}

	/*
	    static class HttpFileHandler implements HttpRequestHandler  {

	        private final String docRoot;

	        public HttpFileHandler(final String docRoot) {
	            super();
	            this.docRoot = docRoot;
	        }

	        public void handle(
	                final HttpRequest request,
	                final HttpResponse response,
	                final HttpContext context) throws HttpException, IOException {

	            String method = request.getRequestLine().getMethod().toUpperCase(Locale.ENGLISH);
	            if (!method.equals("GET") && !method.equals("HEAD") && !method.equals("POST")) {
	                throw new MethodNotSupportedException(method + " method not supported");
	            }
	            String target = request.getRequestLine().getUri();

	            if (request instanceof HttpEntityEnclosingRequest) {
	                HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
	                byte[] entityContent = EntityUtils.toByteArray(entity);
	                System.out.println("Incoming entity content (bytes): " + entityContent.length);
	            }

	            final File file = new File(this.docRoot, URLDecoder.decode(target, "UTF-8"));
	            if (!file.exists()) {

	                response.setStatusCode(HttpStatus.SC_NOT_FOUND);
	                StringEntity entity = new StringEntity(
	                        "<html><body><h1>File" + file.getPath() +
	                        " not found</h1></body></html>",
	                        ContentType.create("text/html", "UTF-8"));
	                response.setEntity(entity);
	                System.out.println("File " + file.getPath() + " not found");

	            } else if (!file.canRead() || file.isDirectory()) {

	                response.setStatusCode(HttpStatus.SC_FORBIDDEN);
	                StringEntity entity = new StringEntity(
	                        "<html><body><h1>Access denied</h1></body></html>",
	                        ContentType.create("text/html", "UTF-8"));
	                response.setEntity(entity);
	                System.out.println("Cannot read file " + file.getPath());

	            } else {

	                response.setStatusCode(HttpStatus.SC_OK);
	                FileEntity body = new FileEntity(file, ContentType.create("text/html", (Charset) null));
	                response.setEntity(body);
	                System.out.println("Serving file " + file.getPath());
	            }
	        }

	    }
	*/
}