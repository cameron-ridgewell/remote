package com.remote.utilities;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by cameronridgewell on 7/30/15.
 */
public interface RequestLibrary {
    /**
     * TV Commands
     */
    @POST("/tv/?command=power")
    public void toggleTVPower(Callback<String> success);

    @POST("/tv/?command=number")
    public void inputTVNumber(@Query("num") int num, Callback<String> success);

    /**
     * AV Commands
     */
    @POST("/av/?command=power")
    public void toggleAVPower(Callback<String> success);

    @POST("/av/?command=number")
    public void inputAVNumber(@Query("num") int num, Callback<String> success);

    /**
     * CM Commands
     */
    @POST("/cm/?command=power")
    public void toggleCMPower(Callback<String> success);

    @POST("/cm/?command=number")
    public void inputCMNumber(@Query("num") int num, Callback<String> success);


    /**
     * Other Commands
     */
}
