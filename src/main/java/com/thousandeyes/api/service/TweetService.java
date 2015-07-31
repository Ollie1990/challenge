package com.thousandeyes.api.service;

import com.thousandeyes.api.dao.JdbcTweetDAO;
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
    private JdbcTweetDAO tweetDAO;

    public List<Tweet> getTweetsByUser(long userId){
        return tweetDAO.getTweetsByUser(userId);
    }
}
