package com.example.dell.connecttoserver.model;

/**
 * Created by dell on 14/3/18.
 */

public class LoginResponse {

    private String id;
    private String token;

    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
