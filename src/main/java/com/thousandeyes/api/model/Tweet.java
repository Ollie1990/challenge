package com.thousandeyes.api.model;

import java.sql.Timestamp;

/**
 * Created by Roberto on 25/07/2015.
 */
public class Tweet {
    private long id;
    private Timestamp timestamp;
    private long userId;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
