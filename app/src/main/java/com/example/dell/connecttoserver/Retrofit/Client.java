package com.example.dell.connecttoserver.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 14/3/18.
 */

public class Client {

    //public static final String BASE_URL = "http://192.168.0.108:3000/";
    /***
     * localhost to test on emulator is 10.0.2.2
     */
    public static final String BASE_URL = "http://10.0.2.2:3000/";

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
