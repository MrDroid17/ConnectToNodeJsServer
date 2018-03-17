package com.example.dell.connecttoserver.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dell on 14/3/18.
 */

public class UserApiResponse {

    @SerializedName("success")
    private boolean isSuccess;

    @SerializedName("msg")
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
