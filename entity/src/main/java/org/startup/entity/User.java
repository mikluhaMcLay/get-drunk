package org.startup.entity;

public class User {

    private final String username;

    private final String photoLink;
    private int totalVolume;
    private int champValue;

    public User( String username, String photoLink) {
        this.username = username;
        this.photoLink = photoLink;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getUsername() {
        return username;
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
                ", photoLink='" + photoLink + '\'' +
                ", totalVolume=" + totalVolume +
                ", champValue=" + champValue +
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
