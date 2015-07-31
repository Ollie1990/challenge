package com.thousandeyes.api.service;

import com.thousandeyes.api.dao.UserDAO;
import com.thousandeyes.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Roberto on 31/07/2015.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> getUsers(List<Long> ids) {
        return userDAO.getUsersByIds(ids);
    }
}
