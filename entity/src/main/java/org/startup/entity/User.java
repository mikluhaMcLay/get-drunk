package org.startup.entity;

public class User {
    private final String username;
    private final byte[] photo;

    public User( String username, byte[] photo ) {
        this.username = username;
        this.photo = photo;
    }

    public User( String username ) {
        this.username = username;
        photo = new byte[]{ };
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        User user = (User) o;

        if ( username != null ? !username.equals( user.username ) : user.username != null ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", has photo=" + (photo.length > 0) +
                '}';
    }
}
