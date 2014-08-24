package org.startup.entity;

import java.util.Date;

public class Act {
    private long userID;
    private final Alcohol alcohol;
    private final double volume;
    private String photoLink;
    private final Date time;

    private String comment;
    private double latitude;
    private double longitude;

    public long getUserID() {
        return userID;
    }

    public Act(long userID, Alcohol alcohol, double volume, String photoLink, Date time) {
        this.userID = userID;
        this.alcohol = alcohol;
        this.volume = volume;
        this.photoLink = photoLink;
        this.time = time;
    }

    public void setComment( String comment ) {
        this.comment = comment;
    }

    public void setLatitude( double latitude ) {
        this.latitude = latitude;
    }

    public void setLongitude( double longitude ) {
        this.longitude = longitude;
    }

    public Alcohol getAlcohol() {
        return alcohol;
    }

    public double getVolume() {
        return volume;
    }

    public Date getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Act act = (Act) o;

        if ( Double.compare( act.latitude, latitude ) != 0 ) return false;
        if ( Double.compare( act.longitude, longitude ) != 0 ) return false;
        if ( Double.compare( act.volume, volume ) != 0 ) return false;
        if ( !alcohol.equals( act.alcohol ) ) return false;
        if ( !comment.equals( act.comment ) ) return false;
        if ( !time.equals( act.time ) ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = alcohol.hashCode();
        temp = Double.doubleToLongBits( volume );
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + time.hashCode();
        temp = Double.doubleToLongBits( latitude );
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits( longitude );
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Act{" +
                "alcohol=" + alcohol +
                ", volume=" + volume +
                ", time=" + time +
                ", comment='" + comment + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public String getPhotoLink() {
        return photoLink;
    }
}
