package com.thousandeyes.api.service;

import com.thousandeyes.api.dao.TweetDAO;
import com.thousandeyes.api.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roberto on 30/07/2015.
 */
@Service
@Transactional
public class TweetService {
    @Autowired
    private TweetDAO tweetDAO;

    public List<Tweet> getTweetsByUser(long userId){
        return tweetDAO.getTweetsByUser(userId);
    }

    public List<Tweet> getTweetsByUserAndTextKey(long userId, String search) {
        return tweetDAO.getTweetsByUserFilteredByKey(userId, search);
    }
}
