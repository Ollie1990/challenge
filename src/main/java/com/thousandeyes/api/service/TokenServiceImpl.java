package com.thousandeyes.api.service;

import com.thousandeyes.api.cache.MemcacheManager;
import com.thousandeyes.api.dao.TokenDAO;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Roberto on 31/07/2015.
 */
@Service
@Transactional
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenDAO tokenDAO;
    @Autowired
    private MemcacheManager memcacheManager;

    public boolean tokenIsValid(String token){
        //check first in cache, then DB if needed
        boolean isValid = memcacheManager.checkToken(token);
        if (isValid)
            return true;
        else {
            isValid = tokenDAO.checkToken(token);
            if (isValid){
                memcacheManager.addTokenInCache(token);
            }
            return isValid;
        }
    }
}
