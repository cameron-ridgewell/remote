package com.remote.utilities;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by cameronridgewell on 7/30/15.
 */
public interface CommandRequestLibrary {
    /**
     * TV Commands
     */
    @POST("/tv/?cmd=power")
    public void toggleTVPower(
            Callback<JsonObject> success);

    @POST("/tv/?cmd=number")
    public void inputTVNumber(
            @Query("num") int num,
            Callback<JsonObject> success);

    /**
     * AV Commands
     */
    @POST("/av/?cmd=power")
    public void toggleAVPower(
            Callback<JsonObject> success);

    @POST("/av/?cmd=number")
    public void inputAVNumber(
            @Query("num") int num,
            Callback<JsonObject> success);

    @POST("/av/?cmd=changeHDMI")
    public void changeHDMI(
            @Query("port") String port,
            Callback<JsonObject> success);

    @POST("/av/?cmd=volumeUp")
    public void increaseAVVolume(
            Callback<JsonObject> success);

    @POST("/av/?cmd=volumeDown")
    public void decreaseAVVolume(
            Callback<JsonObject> success);

    /**
     * CM Commands
     */
    @POST("/cm/?cmd=power")
    public void toggleCMPower(
            Callback<JsonObject> success);

    @POST("/cm/?cmd=number")
    public void inputCMNumber(
            @Query("num") int num,
            Callback<JsonObject> success);

    /**
     * Other Commands
     */

    @POST("/login/")
    public void login(
        Callback<JsonObject> success);
}
