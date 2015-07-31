package com.thousandeyes.api.dao;

import com.thousandeyes.api.model.Tweet;
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
import java.util.List;

/**
 * Created by Roberto on 25/07/2015.
 */
@Repository("tweetDao")
public class JdbcTweetDAO implements TweetDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String GET_TWEETS = "select * from tweets " +
            "where (user_id=:user_id OR user_id IN (" +
            "select user_id from followers where follower_id=:user_id))";
    // fulltext index defined for content column
    private final String GET_TWEETS_FILTERED_BY_SEARCH = GET_TWEETS + " AND MATCH content AGAINST " +
            "( :search_key IN NATURAL LANGUAGE MODE);";

    @Autowired
    private DataSource dataSource;

    @Autowired
    public JdbcTweetDAO(DataSource dataSource){
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

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

    @Override
    public List<Tweet> getTweetsByUserFilteredByKey(long userId, String search) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", userId).
                addValue("search_key", search);
        List<Tweet> tweets = namedParameterJdbcTemplate.query(GET_TWEETS_FILTERED_BY_SEARCH, namedParameters,
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
