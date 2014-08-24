package org.startup.web.rest;

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
import org.startup.entity.Act;
import org.startup.entity.Alcohol;
import org.startup.entity.AlkoCategory;
import org.startup.entity.Brand;
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
import java.util.Date;
import java.util.Random;

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
                               @FormDataParam( "alcoName" ) String alcoName,
                               @FormDataParam( "brand" ) String brand,
                               @FormDataParam( "category" ) String category,
                               @FormDataParam( "amount" ) Integer amount,
                               @FormDataParam( "amount_of" ) String amountOf,
                               @FormDataParam( "title" ) String title,
                               @FormDataParam( "timestamp" ) Long timestamp,
                               @FormDataParam( "userId" ) Long userId ) {
        int status = 200;
        try {
            boolean hasNulls = checkOnNulls( uploadedInputStream, alcoName, brand, category, amount, title,
                    timestamp, userId );
            if ( hasNulls ) {
                return Response.status( 400 ).entity( "fail" ).build();
            }
            byte[] photo = IOUtils.toByteArray( uploadedInputStream );
            String link = photoService.saveUserPhoto( photo );

            AlkoCategory alkoCategory = AlkoCategory.lookup( category );
            Brand alcoBrand = Brand.valueOf( brand );
            Alcohol alcohol = new Alcohol( alcoName, alkoCategory, alcoBrand );

            Act act = new Act(
                    userId,
                    alcohol,
                    amount * somethingToLiter( amountOf ),
                    link,
                    new Date( timestamp )

            );
            actDao.saveAct( act );

            status = 200;
        } catch ( IOException e ) {
            log.error( "Photo wasn't saved: ", e );
        } catch ( PhotoException e ) {
            log.error( "Something happened while saving photo: ", e );
        }

        return Response.status( status ).entity( "success" ).build();
    }


    @GET
    @Path( "/profile" )
    @Produces( MediaType.APPLICATION_JSON )
    public Response getProfile( @QueryParam( "id" ) Integer id ) {
        User user = userDao.getUser( id );
        int drinkTimes = userDao.getDrunkTimes( id );
        int drunkThisWeek = userDao.getDrunkThisWeek( id );
        int drunkAllTimes = userDao.getDrunkAllTime( id );
        String favouriteDrink = userDao.getFavouriteDrink( id );

        ProfileDto profile = new ProfileDto(
                user.getPhotoLink(),
                user.getUsername(),
                drinkTimes,
                drunkThisWeek,
                drunkAllTimes,
                favouriteDrink
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
            log.error( "хуйня случилась: ", e );
            return Response.status( 500 ).build();
        }
    }

    private double somethingToLiter( String something ) {
        return new Random().nextDouble() * 0.7;
    }

    private boolean checkOnNulls( Object... values ) {
        for ( Object v : values ) {
            if ( v == null ) {
                return true;
            }
        }
        return false;
    }

}