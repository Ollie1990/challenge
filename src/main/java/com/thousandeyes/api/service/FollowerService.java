package com.thousandeyes.api.service;

import java.util.List;

/**
 * Created by Roberto on 01/08/2015.
 */
public interface FollowerService {

    boolean startFollowing(long userId, long toFollowUserId);

    boolean stopFollowing(long userId, long toUnfollowUserId);

    List<Long> getFollowers(long userId);

    List<Long> getFollowing(long userId);
}
