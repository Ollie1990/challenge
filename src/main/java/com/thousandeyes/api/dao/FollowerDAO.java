package com.thousandeyes.api.dao;

import java.util.List;

/**
 * Created by Roberto on 26/07/2015.
 */
public interface FollowerDAO extends SimpleDAO{

    void startFollowing(long userId, long toFollowUserId);

    void stopFollowing(long userId, long toUnfollowUserId);

    List<Long> getFollowers(long userId);

    List<Long> getFollowing(long userId);
}
