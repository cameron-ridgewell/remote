package com.remote.utilities;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by cameronridgewell on 7/30/15.
 */
public interface RequestLibrary {
    /**
     * TV Commands
     */
    @POST("/tv/?command=power")
    public void toggleTVPower(Callback<String> success);

    /**
     * AV Commands
     */
    @POST("/tv/?command=power")
    public void toggleAVPower(Callback<String> success);

    /**
     * CM Commands
     */
    @POST("/cm/?command=power")
    public void toggleCMPower(Callback<String> success);

    /**
     * Other Commands
     */
}
