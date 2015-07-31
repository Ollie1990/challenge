package com.thousandeyes.api.service;

import com.thousandeyes.api.dao.JdbcTweetDAO;
import com.thousandeyes.api.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Roberto on 30/07/2015.
 */
@Service
public class TweetService {
    private JdbcTweetDAO tweetDAO;

    public TweetService(){
        tweetDAO = new JdbcTweetDAO();
    }

    public List<Tweet> getTweetsByUser(long userId){
        return tweetDAO.getTweetsByUser(userId);
    }
}
