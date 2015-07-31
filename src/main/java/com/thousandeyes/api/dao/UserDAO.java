package com.thousandeyes.api.dao;

import com.thousandeyes.api.model.User;

/**
 * Created by Roberto on 26/07/2015.
 */
public interface UserDAO extends SimpleDAO {
    User getUserById(long userId);
}
