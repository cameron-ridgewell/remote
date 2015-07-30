package com.remote;

import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class DownloadPage {
    private String url = "";

    public DownloadPage(String url) {
        this.url = url;
    }

    public void getData() {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://www.yahoo.com/");
        try {
            URL yahoo = new URL("http://www.yahoo.com/");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yahoo.openStream()));

            String inputLine;

            //while ((inputLine = in.readLine()) != null)
            //    System.out.println(inputLine);

            in.close();
        } catch (IOException e) {
            Log.e("IOException",e.getMessage());
        }
    }
}