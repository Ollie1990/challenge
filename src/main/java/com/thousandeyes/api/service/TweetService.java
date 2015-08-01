package com.thousandeyes.api.service;

import com.thousandeyes.api.model.Tweet;

import java.util.List;

/**
 * Created by Roberto on 30/07/2015.
 */
public interface TweetService {

    List<Tweet> getTweetsByUser(long userId);

    List<Tweet> getTweetsByUserAndTextKey(long userId, String search);
}
