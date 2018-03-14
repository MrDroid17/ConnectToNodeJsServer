package com.example.dell.connecttoserver.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 14/3/18.
 */

public class Client {
                                        // for real device 192.168.0.108
    public static final String BASE_URL = "http://192.168.0.108:3000/";
    public static Retrofit retrofit= null;

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
