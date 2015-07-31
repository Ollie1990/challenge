package com.thousandeyes.api.dao;

import com.thousandeyes.api.model.Follower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roberto on 26/07/2015.
 */
@Repository("followerDao")
public class JdbcFollowerDAO implements FollowerDAO {
    private static final String GET_ROW_BY_ID = "select * from followers where user_id= :user_id AND " +
            "follower_id= :follower_id" ;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insert;
    private final String GET_FOLLOWERS = "select * from followers where user_id = :user_id";
    private final String GET_FOLLOWING = "select * from followers where follower_id = :follower_id";
    @Autowired
    private DataSource dataSource;

    @Autowired
    public JdbcFollowerDAO(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insert = new SimpleJdbcInsert(dataSource).withTableName("followers");
    }

    public void setDataSource(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insert = new SimpleJdbcInsert(dataSource).withTableName("followers");
    }

    @Override
    public void startFollowing(long userId, long toFollowUserId) {
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("user_id", toFollowUserId);
        parameters.put("follower_id", userId);
        insert.execute(parameters);
    }

    @Override
    public void stopFollowing(long userId, long toUnfollowUserId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(
                "delete from followers where user_id = ? and follower_id = ?",
                toUnfollowUserId, userId);
    }

    @Override
    public List<Long> getFollowers(long userId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", userId);
        List<Long> followers = namedParameterJdbcTemplate.query(GET_FOLLOWERS, namedParameters,
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Long(rs.getLong(2));
                    }
                });
        return followers;

    }

    @Override
    public List<Long> getFollowing(long userId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("follower_id", userId);
        List<Long> followers = namedParameterJdbcTemplate.query(GET_FOLLOWING, namedParameters,
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Long(rs.getLong(1));
                    }
                });
        return followers;
    }

    @Override
    public Follower getFollowerByKey(long userId, long followerId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("follower_id", followerId).
                addValue("user_id", userId);
        List<Follower> follower = namedParameterJdbcTemplate.query(GET_ROW_BY_ID, namedParameters,
                new RowMapper<Follower>() {
                    @Override
                    public Follower mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Follower follower = new Follower();
                               follower.setUserId(rs.getLong(1));
                            follower.setFollowerId(rs.getLong(2));
                            return follower;
                    }
                }
        );

        if (follower.isEmpty())
            return null;
        return follower.get(0);
    }


    public DataSource getDataSource() {
        return dataSource;
    }
}
