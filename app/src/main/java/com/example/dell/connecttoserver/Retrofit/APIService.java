package com.example.dell.connecttoserver.Retrofit;

import com.example.dell.connecttoserver.model.Login;
import com.example.dell.connecttoserver.model.LoginResponse;
import com.example.dell.connecttoserver.model.UserApiResponse;
import com.example.dell.connecttoserver.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by dell on 14/3/18.
 */

public interface APIService {

    @POST("users/register")
    Call<UserApiResponse> registerUser(@Body User user);

    /*@POST("users/authenticate")
    Call<User> authenticateUser(@Field("username") String username,
                        @Field("password") String password);*/

    @POST("users/authenticate")
    Call<LoginResponse> authenticateUser(@Body Login login);

    @GET("users/profile")
    Call<User> getProfile(@Header("Authorisation") String authToken);


}
