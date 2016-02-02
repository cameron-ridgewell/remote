package com.remote.utilities;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.GET;
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
    @POST("/av/?cmd=powerOn")
    public void setAVPowerOn(
            Callback<String> success);

    @POST("/av/?cmd=powerOff")
    public void setAVPowerOff(
            Callback<String> success);

    @GET("/av/?cmd=getAVPowerStatus")
    public void getAVPowerStatus(
            Callback<String> success);

    @GET("/av/?cmd=getAVChannel")
    public void getAVChannel(
            Callback<String> success);

    @POST("/av/?cmd=changeHDMI")
    public void setAVChannel(
            @Query("channel") String channel,
            Callback<String> success);

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

    @POST("/av/?cmd=volumeSet")
    public void setAVVolume(@Query("volume") double volume,
            Callback<String> success);

    @POST("/av/?cmd=volumeMute")
    public void setAVMute(Callback<String> success);

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
