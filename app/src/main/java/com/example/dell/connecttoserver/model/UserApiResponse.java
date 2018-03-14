package com.example.dell.connecttoserver.model;

/**
 * Created by dell on 14/3/18.
 */

public class UserApiResponse {
    private boolean isSuccess;
    private String message;

    public UserApiResponse(boolean isBoolean, String message) {
        this.isSuccess = isBoolean;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
