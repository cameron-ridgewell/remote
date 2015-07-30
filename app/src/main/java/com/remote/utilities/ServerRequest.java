package com.remote.utilities;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
/**
 * Created by cameronridgewell on 7/30/15.
 */
public class ServerRequest{
    private final String ip_address = "http://ps2-wintra.rhcloud.com";
    private final RequestLibrary svc = new RestAdapter.Builder()
            .setEndpoint(ip_address).build()
            .create(RequestLibrary.class);

    private static ExecutorService exec = Executors.newFixedThreadPool(1);

    private static ServerRequest instance = null;

    protected ServerRequest(){};

    public static ServerRequest getInstance() {
        if (instance == null) {
            return new ServerRequest();
        } else {
            return instance;
        }
    }

    public void addUser(final int user) {
        Callable c = new Callable() {
            @Override
            public String call() {
                svc.addUser(user, new Callback<String>() {
                    @Override
                    public void success(String user, Response response) {
                        Log.v("Retrofit Success", "User response");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Retrofit Error", "addUser Failed");
                    }
                });
                return "";
            }
        };
        try {
            exec.submit(c).get();
        } catch (ExecutionException e) {
            Log.e("Interrupted Exception", e.getMessage());
        } catch (InterruptedException e) {
            Log.e("Execution Exception", e.getMessage());
        }
    }
}