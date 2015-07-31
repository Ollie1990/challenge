package com.thousandeyes.api.controller;

import com.thousandeyes.api.model.ErrorResponse;
import com.thousandeyes.api.model.Tweet;
import com.thousandeyes.api.service.FollowerService;
import com.thousandeyes.api.service.TweetService;
import com.thousandeyes.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Roberto on 26/07/2015.
 */
@RestController
@RequestMapping("/")
public class ApiRequestController {
    @Autowired
    private FollowerService followerService;
    @Autowired
    private TweetService tweetService;
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String homePageRedirect(ModelMap model){
        model.put("welcomeMessage", "Welcome to the home page of Challenge API");
        return "Welcome to the home page of Challenge API";
    }

    @RequestMapping(value = "/api/getFollowers", method = RequestMethod.GET)
    public Object getFollowers(@RequestParam(value="userId") long userId,
                                              @RequestParam(value="token") String token) {
        if (!validate(token))
            return new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ErrorResponse.UNAUTHORIZED);
        //validate userId
        if (!validateUserId(userId))
            return new ErrorResponse(HttpServletResponse.SC_NOT_FOUND,
                    String.format(ErrorResponse.ENTITY_NOT_FOUND, "User Id"));
        List<Long> followers = followerService.getFollowers(userId);
        // maybe it can be retrieved the full user info given the id, using userService
        return followers;
    }

    private boolean validateUserId(long userId) {
        //todo
        return true;
    }

    private boolean validate(String token) {
        if (token == null || token.length() != 16)
            return false;
        //check in DB --> cached in memory, memcache?
        return true;
    }

    @RequestMapping(value = "/api/getFollowing", method = RequestMethod.GET)
    public List<Long> getFollowing(@RequestParam(value="userId") long userId) {
        //validate token
        //validate userId
        List<Long> following = followerService.getFollowing(userId);
        return following;
    }

    @RequestMapping(value = "/api/follow", method = RequestMethod.POST)
    public void follow(long toFollowUserId, long followerId) {
        //validate token
        //validate userId (?)
        followerService.startFollowing(followerId, toFollowUserId);
    }

    @RequestMapping(value = "/api/unfollow", method = RequestMethod.POST)
    public void unfollow(long toUnfollowUserId, long followerId) {
        //validate token
        //validate userId (?)
        followerService.stopFollowing(followerId, toUnfollowUserId);
    }

    @RequestMapping(value = "/api/tweets", method = RequestMethod.GET)
    public Object getTweets(@RequestParam(value="userId") long userId) {
        //validate token
        //validate userId
        List<Tweet> tweets = tweetService.getTweetsByUser(userId);
        return tweets;
    }
}
