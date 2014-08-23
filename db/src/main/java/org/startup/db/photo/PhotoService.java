package org.startup.db.photo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Component
public class PhotoService {
    private static final Logger log = LoggerFactory.getLogger( PhotoService.class );

    public static final String USER_DIR = "users/";
    public static final String ACT_DIR = "acts/";
    public static final String ALCO_ITEM = "items/";

    public static final int LINK_LENGTH = 30;
    private final RandomString randomString = new RandomString( LINK_LENGTH );

    @PostConstruct
    public void init() throws IOException {
        createIfNotExists( USER_DIR );
        createIfNotExists( ACT_DIR );
        createIfNotExists( ALCO_ITEM );
    }

    private void createIfNotExists( String path ) throws IOException {
        File f = new File( path );

        if ( !f.exists() ) {
            f.mkdirs();
        }
    }

    private void saveToFile( String path, byte[] content ) throws PhotoException {
        File file = new File( path );
        try ( FileOutputStream fos = new FileOutputStream( file ) ) {
            fos.write( content );
            fos.flush();
        } catch ( IOException e ) {
            log.error( "Save photo {} error: {}", path, e );
            throw new PhotoException( e );
        }
    }

    public String saveUserPhoto( byte[] content ) throws PhotoException {
        String link = savePhoto( USER_DIR, content );
        return link;
    }

    private String savePhoto( String dir, byte[] content ) throws PhotoException {
        String link = randomString.nextString();
        saveToFile( dir + link, content );
        log.info( "Saved photo: {}", link );
        return link;
    }

    public String saveActPhoto( byte[] content ) throws PhotoException {
        return savePhoto( ACT_DIR, content );
    }

    public String saveAlcoItem( byte[] content ) throws PhotoException {
        return savePhoto( ALCO_ITEM, content );
    }

    private byte[] getPhoto( String path ) throws PhotoException {
        Path photoPath = Paths.get( path );
        byte[] data = new byte[0];
        try {
            data = Files.readAllBytes( photoPath );
        } catch ( IOException e ) {
            log.error( "Can't read photo {} cause", path, e );
        }
        log.info( "read {} bytes from file {}", data.length, path );

        return data;
    }

    public byte[] getUserPhoto( String link ) throws PhotoException {
        return getPhoto( USER_DIR + link );
    }

    public byte[] getActPhoto( String link ) throws PhotoException {
        return getPhoto( ACT_DIR + link );
    }

    public byte[] getAlcoItemPhoto( String link ) {
        return getAlcoItemPhoto( ALCO_ITEM + link );
    }

    public static class RandomString {
        private static final char[] symbols;

        static {
            StringBuilder tmp = new StringBuilder();
            for ( char ch = '0'; ch <= '9'; ++ch )
                tmp.append( ch );
            for ( char ch = 'a'; ch <= 'z'; ++ch )
                tmp.append( ch );
            symbols = tmp.toString().toCharArray();
        }

        private final Random random = new Random();

        private final char[] buf;

        private RandomString( int length ) {
            if ( length < 1 )
                throw new IllegalArgumentException( "length < 1: " + length );
            buf = new char[length];
        }

        public String nextString() {
            for ( int idx = 0; idx < buf.length; ++idx )
                buf[idx] = symbols[random.nextInt( symbols.length )];
            return new String( buf );
        }
    }

}
