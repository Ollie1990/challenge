package com.thousandeyes.api.dao;

/**
 * Created by Roberto on 31/07/2015.
 */
public interface TokenDAO extends SimpleDAO {

    boolean checkToken(String token);
}
