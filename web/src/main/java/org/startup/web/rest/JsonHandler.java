package org.startup.web.rest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.startup.db.photo.PhotoException;
import org.startup.db.photo.PhotoService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Component
@Path( "api" )
public class JsonHandler {
    private final static Logger log = LoggerFactory.getLogger( JsonHandler.class );

    @Autowired
    private PhotoService photoService;

    @GET
    @Path( "/echo" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public String echo(@QueryParam( "msg" ) String msg ) {
        return "You said " + msg;
    }

    @POST
    @Path("/uploadUserPicture")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response postPhoto(@FormDataParam("file") InputStream uploadedInputStream,
                          @FormDataParam("file") FormDataContentDisposition fileDetail,
                          @FormDataParam("category") FormDataContentDisposition category,
                          @FormDataParam("amount") FormDataContentDisposition amount) {
        int status = 200;
        try {
            byte[] photo = IOUtils.toByteArray( uploadedInputStream );
            photoService.saveUserPhoto( photo );

            status = 200;
        } catch ( IOException e ) {
            log.error( "Photo wasn't saved: ", e );
        } catch ( PhotoException e ) {
            log.error( "Something happened while saving photo: ", e );
        }

        return Response.status( status ).entity( "хуйпизда" ).build();
    }

}