package org.startup.entity;

public class User {
    private final String username;
    private byte[] photo;

    private final String photoLink;
    private int totalVolume;
    private int champValue;

    public User( String username, String photoLink) {
        this.username = username;
        this.photoLink = photoLink;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoLink() {
        return photoLink;
    }
//
//    public User( String username ) {
//        this.username = username;
//        photo = new byte[]{ };
//    }

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

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public int getChampValue() {
        return champValue;
    }

    public void setChampValue(int champValue) {
        this.champValue = champValue;
    }
}
