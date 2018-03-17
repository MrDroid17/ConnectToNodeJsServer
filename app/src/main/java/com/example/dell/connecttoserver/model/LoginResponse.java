package com.example.dell.connecttoserver.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dell on 14/3/18.
 */

public class LoginResponse {

    @SerializedName("success")
    private String isSuccess;

    @SerializedName("token")
    private String token;

    @SerializedName("user")
    private User user;


    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
