package com.thousandeyes.api.service;

import com.thousandeyes.api.dao.FollowerDAO;
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

    public void startFollowing(long userId, long toFollowUserId){
        followerDAO.startFollowing(userId, toFollowUserId);
    }

    public void stopFollowing(long userId, long toUnfollowUserId){
        followerDAO.stopFollowing(userId, toUnfollowUserId);
    }

    public List<Long> getFollowers(long userId){
        return followerDAO.getFollowers(userId);
    }

    public List<Long> getFollowing(long userId){
        return followerDAO.getFollowing(userId);
    }
}
