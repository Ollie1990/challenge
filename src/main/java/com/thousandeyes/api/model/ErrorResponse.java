package com.thousandeyes.api.model;

/**
 * Created by Roberto on 31/07/2015.
 */
public class ErrorResponse {
    private int errorCode;
    private String message;

    public static final String ENTITY_NOT_FOUND = "%s not found";
    public static final String UNAUTHORIZED = "You have no authorization to make the request. " +
            "Make sure your API token is correct.";


    public ErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
