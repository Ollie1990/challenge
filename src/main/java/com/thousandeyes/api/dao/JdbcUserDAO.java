package com.thousandeyes.api.dao;

import com.thousandeyes.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Roberto on 26/07/2015.
 */
public class JdbcUserDAO implements UserDAO{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String GET_USER_BY_ID = "select * from users where id = :user_id";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User getUserById(long userId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", userId);
        User user = namedParameterJdbcTemplate.query(GET_USER_BY_ID, namedParameters, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setNickname(rs.getString(4));
                user.setTelephoneNumber(rs.getString(5));
                return user;
            }
        });
        return user;
    }
}
