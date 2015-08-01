package com.thousandeyes.api.service;

import com.thousandeyes.api.model.User;

import java.util.List;

/**
 * Created by Roberto on 01/08/2015.
 */
public interface UserService {
    List<User> getUsers(List<Long> ids);

    boolean areValidUsers(long userId1, long userId2);
}
