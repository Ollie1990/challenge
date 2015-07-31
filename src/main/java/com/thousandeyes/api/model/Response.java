package com.thousandeyes.api.model;

/**
 * Created by Roberto on 31/07/2015.
 */
public class Response {
    private int httpCode;
    private String message;

    public Response(int httpCode, String message){
        this.httpCode = httpCode;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
