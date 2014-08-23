package org.startup.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Path( "api" )
public class JsonHandler {
    private final static Logger log = LoggerFactory.getLogger( JsonHandler.class );


    @GET
    @Path( "/echo" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public String echo(@QueryParam( "msg" ) String msg ) {
        return "You said " + msg;
    }


}