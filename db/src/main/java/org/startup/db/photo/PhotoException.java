package org.startup.db.photo;

public class PhotoException extends Exception{
    public PhotoException( String message, Throwable cause ) {
        super( message, cause );
    }

    public PhotoException( Throwable cause ) {
        super( cause );
    }
}
