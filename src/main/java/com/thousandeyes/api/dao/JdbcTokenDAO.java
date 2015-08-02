package com.thousandeyes.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Roberto on 31/07/2015.
 */
@Repository("tokenDao")
public class JdbcTokenDAO implements TokenDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String GET_TOKEN = "select count(*) from apitoken where token = :token";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public JdbcTokenDAO(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public boolean checkToken(String token) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("token", token);
        int countToken = namedParameterJdbcTemplate.queryForObject(GET_TOKEN, namedParameters, Integer.class);
        return countToken > 0;
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
