package com.remote.utilities;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.remote.R;

/**
 * Created by cameronridgewell on 3/23/15.
 */
public class AppData {
    public static AppData instance = null;
    private Gson gson = new Gson();
    private Map<String,Long> commands;

    public static AppData getInstance() {
        if (instance == null) {
            return new AppData();
        } else {
            return instance;
        }
    }

    public User getThisUser(Context context) {
        String user = "";
        try {
            FileInputStream fis = context.openFileInput(
                    context.getResources().getString(R.string.resource_file));
            int ch;
            StringBuffer fileContent = new StringBuffer("");
            while ((ch = fis.read()) != -1) {
                fileContent.append((char) ch);
            }
            user = fileContent.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user .equals("")) {
            Log.e("Storage Error", "User id could not be read from internal storage");
        }
        return gson.fromJson(user, User.class);
    }

    public Map<String, Long> getCommands() {
        return commands;
    }
}
