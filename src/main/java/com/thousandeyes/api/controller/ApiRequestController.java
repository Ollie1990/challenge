package com.thousandeyes.api.controller;

import com.thousandeyes.api.model.*;
import com.thousandeyes.api.service.FollowerService;
import com.thousandeyes.api.service.TokenService;
import com.thousandeyes.api.service.TweetService;
import com.thousandeyes.api.service.UserService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
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
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(method = RequestMethod.GET)
    public String homePageRedirect(){
        return "Welcome to the home page of Challenge API";
    }

    @RequestMapping(value = "/api/getFollowers", method = RequestMethod.GET)
    public Object getFollowers(@RequestParam(value="userId") long userId,
                               @RequestParam(value="token") String token,
                               @RequestParam(required = false) boolean expand) {
        if (!validate(token))
            return new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ErrorResponse.UNAUTHORIZED);
        List<Long> followers = followerService.getFollowers(userId);
        if (expand && !followers.isEmpty()){
            List<User> detailedFollowers = userService.getUsers(followers);
            return detailedFollowers;
        }
        else
            return followers;
    }

    @RequestMapping(value = "/api/getFollowing", method = RequestMethod.GET)
    public Object getFollowing(@RequestParam(value="userId") long userId,
                                   @RequestParam(value="token") String token,
                                   @RequestParam(required = false) boolean expand) {
        if (!validate(token))
            return new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ErrorResponse.UNAUTHORIZED);
        List<Long> following = followerService.getFollowing(userId);
        if (expand && !following.isEmpty()){
            List<User> detailedFollowing = userService.getUsers(following);
            return detailedFollowing;
        }
        else
            return following;
    }

    @RequestMapping(value = "/api/follow", method = RequestMethod.POST)
    public Object follow(@RequestBody FollowPayload payload) {
        long toFollowUserId = payload.getFollower().getUserId();
        long followerId = payload.getFollower().getFollowerId();
        String token = payload.getToken();
        if (!validate(token))
            return new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ErrorResponse.UNAUTHORIZED);
        if (toFollowUserId == followerId)
            return new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, ErrorResponse.BAD_REQ_SAME_IDS);
        boolean usersAreValid = userService.areValidUsers(toFollowUserId, followerId);
        if (!usersAreValid)
            return new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, ErrorResponse.USERS_NOT_VALID);
        boolean done = followerService.startFollowing(followerId, toFollowUserId);
        if (done)
            return new Response(HttpServletResponse.SC_OK,
                String.format("Now %s follows %s", followerId, toFollowUserId));
        else
            return new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    String.format("%s already follows %s", followerId, toFollowUserId));
    }

    @RequestMapping(value = "/api/unfollow", method = RequestMethod.POST)
    public Object unfollow(@RequestBody FollowPayload payload) {
        long toUnfollowUserId = payload.getFollower().getUserId();
        long followerId = payload.getFollower().getFollowerId();
        String token = payload.getToken();
        if (!validate(token))
            return new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ErrorResponse.UNAUTHORIZED);
        if (toUnfollowUserId == followerId)
            return new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, ErrorResponse.BAD_REQ_SAME_IDS);
        boolean done = followerService.stopFollowing(followerId, toUnfollowUserId);
        if (done)
            return new Response(HttpServletResponse.SC_OK,
                    String.format("Now %s doesn't follow %s anymore", followerId, toUnfollowUserId));
        else
            return new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    String.format("%s is already not following %s", followerId, toUnfollowUserId));
    }

    @RequestMapping(value = "/api/tweets", method = RequestMethod.GET)
    public Object getTweets(@RequestParam(value="userId") long userId,
                            @RequestParam(value="token") String token,
                            @RequestParam(required = false) String search) {
        if (!validate(token))
            return new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ErrorResponse.UNAUTHORIZED);
        List<Tweet> tweets;
        if (search != null && !search.isEmpty()){
            tweets = tweetService.getTweetsByUserAndTextKey(userId, search);
        }
        else {
            tweets = tweetService.getTweetsByUser(userId);
        }
        return tweets;
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handleBadRequestException(Exception e) {
        return new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "The request is malformed.");
    }

    @ExceptionHandler(value = {IllegalStateException.class, SQLException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleInternalServerErrorException(Exception e) {
        return new ErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error. Something" +
                " went wrong in your request.");
    }

    @RequestMapping(value = "/**")
    public Object handleNotFoundException(Exception e) {
        return new ErrorResponse(HttpServletResponse.SC_NOT_FOUND, "The requested endpoint does not exists.");
    }

    private boolean validate(String token) {
        if (token == null || token.length() != 16)
            return false;
        return tokenService.tokenIsValid(token);
    }
}
