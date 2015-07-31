package com.thousandeyes.api.model;

/**
 * Created by Roberto on 31/07/2015.
 */
public class FollowPayload {
    private Follower follower;
    private String token;

    public Follower getFollower() {
        return follower;
    }

    public void setFollower(Follower follower) {
        this.follower = follower;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
