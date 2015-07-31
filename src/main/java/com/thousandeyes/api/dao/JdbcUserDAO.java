package com.thousandeyes.api.dao;

import com.thousandeyes.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roberto on 26/07/2015.
 */
@Repository("userDao")
public class JdbcUserDAO implements UserDAO{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String GET_USER_BY_ID = "select * from users where id = :user_id";
    private final String GET_USERS_BY_IDS= "select * from users where id IN ( :ids )";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public JdbcUserDAO(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

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

    public List<User> getUsersByIds(List<Long> ids) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("ids", ids);
        List<User> users = namedParameterJdbcTemplate.query(GET_USERS_BY_IDS, namedParameters, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setNickname(rs.getString(4));
                user.setTelephoneNumber(rs.getString(5));
                return user;
            }
        });
        return users;
    }
}
