package com.thousandeyes.api.dao;

import com.thousandeyes.api.model.Tweet;

import java.util.List;

/**
 * Created by Roberto on 25/07/2015.
 */
public interface TweetDAO extends SimpleDAO{

    List<Tweet> getTweetsByUser(long userId);

    List<Tweet> getTweetsByUserFilteredByKey(long userId, String search);
}
