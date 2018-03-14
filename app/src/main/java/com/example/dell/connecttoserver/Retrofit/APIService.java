package com.example.dell.connecttoserver.Retrofit;

import com.example.dell.connecttoserver.model.UserApiResponse;
import com.example.dell.connecttoserver.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by dell on 14/3/18.
 */

public interface APIService {

    @POST("users/register")
    Call<UserApiResponse> registerUser(@Body User user);

    @POST("users/authenticate")
    Call<User> authenticateUser(@Field("username") String username,
                        @Field("password") String password);

}
