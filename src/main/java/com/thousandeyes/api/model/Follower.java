package com.thousandeyes.api.model;

/**
 * Created by Roberto on 26/07/2015.
 */
public class Follower {
    private long userId;
    private long followerId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }
}
