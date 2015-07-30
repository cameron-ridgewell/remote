package com.remote.utilities;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by cameronridgewell on 7/30/15.
 */
public interface RequestLibrary {
    @POST("/user/?type=new")
    public void addUser(@Body int user, Callback<String> success);
}
