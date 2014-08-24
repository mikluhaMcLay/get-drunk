package org.startup.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileDto {
    private final String photoLink;
    private final String username;
    private final int drunkTimes;
    private final int drunkThisWeek;
    private final int drunkAllTime;
    private final String favoriteDrink;

    public ProfileDto( String photoLink, String username, int drunkTimes, int drunkThisWeek, int drunkAllTime, String favoriteDrink ) {
        this.photoLink = photoLink;
        this.username = username;
        this.drunkTimes = drunkTimes;
        this.drunkThisWeek = drunkThisWeek;
        this.drunkAllTime = drunkAllTime;
        this.favoriteDrink = favoriteDrink;
    }

    @JsonProperty("image")
    public String getPhotoLink() {
        return photoLink;
    }

    @JsonProperty("fullname")
    public String getUsername() {
        return username;
    }

    @JsonProperty("drunk_times")
    public int getDrunkTimes() {
        return drunkTimes;
    }

    @JsonProperty("drunk_this_week")
    public int getDrunkThisWeek() {
        return drunkThisWeek;
    }

    @JsonProperty("drunk_all_time")
    public int getDrunkAllTime() {
        return drunkAllTime;
    }

    @JsonProperty("favorite_drink")
    public String getFavoriteDrink() {
        return favoriteDrink;
    }
}
