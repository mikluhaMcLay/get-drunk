package org.startup.web.rest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.startup.db.ActDao;
import org.startup.db.UserDao;
import org.startup.db.photo.PhotoException;
import org.startup.db.photo.PhotoService;
import org.startup.entity.User;
import org.startup.web.dto.ProfileDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
@Path( "api" )
public class JsonHandler {
    private final static Logger log = LoggerFactory.getLogger( JsonHandler.class );

    @Autowired
    private PhotoService photoService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ActDao actDao;

    @GET
    @Path( "/echo" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public String echo( @QueryParam( "msg" ) String msg ) {
        return "You said " + msg;
    }

    @POST
    @Path( "/uploadUserPicture" )
    @Consumes( MediaType.MULTIPART_FORM_DATA )
    public Response postPhoto( @FormDataParam( "file" ) InputStream uploadedInputStream,
                               @FormDataParam( "file" ) FormDataContentDisposition fileDetail,
                               @FormDataParam( "category" ) String category,
                               @FormDataParam( "amount" ) Integer amount,
                               @FormDataParam( "amount_of" ) String amountOf,
                               @FormDataParam( "title" ) String title ) {

        int status = 200;
        try {
            byte[] photo = IOUtils.toByteArray( uploadedInputStream );
            photoService.saveUserPhoto( photo );

//            actDao.
            status = 200;
        } catch ( IOException e ) {
            log.error( "Photo wasn't saved: ", e );
        } catch ( PhotoException e ) {
            log.error( "Something happened while saving photo: ", e );
        }

        return Response.status( status ).entity( "хуйпизда" ).build();
    }

    @GET
    @Path( "/profile" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getProfile( @QueryParam( "id" ) Integer id ) {
        User user = userDao.getUser( id );
        int drinkTimes = userDao.getDrunkTimes( id );
        int drunkThisWeek = userDao.getDrunkThisWeek( id );
        int drunkAllTimes = userDao.getDrunkAllTime( id );
        userDao.getFavouriteDrink( id );

        ProfileDto profile = new ProfileDto(
                user.getPhotoLink(),
                user.getUsername(),
                drinkTimes,
                drunkThisWeek,
                drunkAllTimes,
                user.
        );

        return Response.ok( profile ).build();
    }

    @GET
    @Path( "/image" )
    @Produces( "image/jpeg" )
    public Response getPhoto( @QueryParam( "link" ) String link ) {
            try {
                byte[] photo = photoService.getUserPhoto( link );
                return Response.status( 200 ).header( "Content-Disposition", "attachment; filename=" + link )
                        .entity( new ByteArrayInputStream( photo ) ).build();
            } catch ( PhotoException e ) {
                log.error( "хуйня случилась: ",e );
                return Response.status( 500 ).build();
            }
    }

}