package org.startup.web;

import org.eclipse.jetty.server.AbstractConnector;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class HttpServer {
    private final static Logger log = LoggerFactory.getLogger( HttpServer.class );

    public static final int PORT = 10000;
    public static final String HOST = "10.80.202.18";

    private Server server;
    @Autowired
    private CustomContextLoaderListener contextLoaderListener;

    @PostConstruct
    public void init() throws Exception {
        server = new Server();
        server.setConnectors( getConnectors() );

        WebAppContext handler = new WebAppContext();
        handler.setContextPath( "/" );
        handler.setDescriptor( getClass().getResource( "/webapp/WEB-INF/web.xml" ).toString() );
//        handler.setWar( getClass().getResource( "/webapp" ).toString() );
        handler.setResourceBase( getClass().getResource( "/webapp" ).toString() );

        handler.addEventListener( contextLoaderListener );
        handler.setServer( server );

        server.setHandler( handler );

        server.start();
        log.info( "Jetty http server started" );
    }

    @PreDestroy
    public void shutdown() {
        try {
            server.stop();
        } catch ( Exception e ) {
            log.error( "Exception caught when stopping jetty server: ", e );
        }
    }

    private Connector[] getConnectors() {
        AbstractConnector c = new SocketConnector();
        c.setMaxIdleTime( 3600000 );
        c.setSoLingerTime( -1 );
        c.setHost( HOST );
        c.setPort( PORT );

        return new Connector[]{ c };
    }
}
