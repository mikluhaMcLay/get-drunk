package org.startup.assembly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Main {
    private final static Logger log = LoggerFactory.getLogger( Main.class );

    public static void main( String[] args ) {
    	Thread.setDefaultUncaughtExceptionHandler(new DefaultExceptionHandler());
        try {
            AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext( "applicationContext.xml" );

            log.info( "started at {}", new Date() );
        } catch ( Exception e ) {
            log.error( "Error during initializing spring context", e );
        }
    }
}

class DefaultExceptionHandler implements Thread.UncaughtExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	public void uncaughtException(Thread t, Throwable e) {
		log.error("Exception in thread['" + t.getName() + " (id=" + t.getId() + ")']: ", e);
	}
}