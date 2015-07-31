package com.thousandeyes.api.service;

import com.thousandeyes.api.dao.FollowerDAO;
import com.thousandeyes.api.model.Follower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roberto on 30/07/2015.
 */
@Service
@Transactional
public class FollowerService {
    @Autowired
    private FollowerDAO followerDAO;

    public boolean startFollowing(long userId, long toFollowUserId){
        Follower f = followerDAO.getFollowerByKey(toFollowUserId, userId);
        if (f == null){
            followerDAO.startFollowing(userId, toFollowUserId);
            return true;
        }
        else
            return false;
    }

    public boolean stopFollowing(long userId, long toUnfollowUserId){
        Follower f = followerDAO.getFollowerByKey(toUnfollowUserId, userId);
        if (f != null) {
            followerDAO.stopFollowing(userId, toUnfollowUserId);
            return true;
        }
        else
            return false;
    }

    public List<Long> getFollowers(long userId){
        return followerDAO.getFollowers(userId);
    }

    public List<Long> getFollowing(long userId){
        return followerDAO.getFollowing(userId);
    }
}
