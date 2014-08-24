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
import org.startup.entity.*;
import org.startup.web.dto.ProfileDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

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
                               @FormDataParam( "title" ) String title,
                               @FormDataParam( "userID" ) Long userID
                               ) {
        int status = 200;
        String photoLink = "";
        try {
            byte[] photo = IOUtils.toByteArray( uploadedInputStream );
            photoLink = photoService.saveUserPhoto(photo);

            status = 200;
        } catch ( IOException e ) {
            log.error( "Photo wasn't saved: ", e );
        } catch ( PhotoException e ) {
            log.error( "Something happened while saving photo: ", e );
        }

        Act act = new Act(
                userID,
                new Alcohol(title,
                        AlkoCategory.valueOf(category),
                        Brand.valueOf(title)),
                getVolume(amount, amountOf),
                photoLink,
                new Date());
        actDao.saveAct(act);
        return Response.status( status ).entity( "хуйпизда" ).build();
    }

    private double getVolume(Integer amount, String amountOf) {
        return 1;
    }

    @GET
    @Path( "/profile" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getProfile( @QueryParam( "id" ) Integer id ) {
        User user = userDao.getUser(id);
        ProfileDto profile = new ProfileDto(
                "goc0g6uh1an2wik9d3l5rtzn2xdges",
                user.getUsername(),
                userDao.getDrunkTimes(id),
                2000,
                3000,
                "vodka"
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