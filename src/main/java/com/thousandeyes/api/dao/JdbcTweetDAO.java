package com.thousandeyes.api.dao;

import com.thousandeyes.api.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roberto on 25/07/2015.
 */
public class JdbcTweetDAO implements TweetDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String GET_TWEETS = "select * from tweets" +
            "where user_id = :user_id OR user_id IN (" +
            "select user_id from followers where follower_id = :user_id)";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Tweet> getTweetsByUser(long userId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", userId);
        List<Tweet> tweets = namedParameterJdbcTemplate.query(GET_TWEETS, namedParameters,
                new RowMapper<Tweet>() {
                    @Override
                    public Tweet mapRow(ResultSet resultSet, int i) throws SQLException {
                        Tweet tweet = new Tweet();
                        tweet.setId(resultSet.getLong(1));
                        tweet.setTimestamp(resultSet.getTimestamp(2));
                        tweet.setUserId(resultSet.getLong(3));
                        tweet.setContent(resultSet.getString(4));
                        return tweet;
                    }
                });
        return tweets;
    }
}
