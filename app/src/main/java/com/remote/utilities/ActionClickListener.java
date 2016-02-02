package com.remote.utilities;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cameronridgewell on 1/27/16.
 */
public abstract class ActionClickListener implements View.OnClickListener {
    public Map<String, View> viewLibrary = new HashMap<>();

    public ActionClickListener() {}

    public ActionClickListener(String label, View object) {
        this.viewLibrary.put(label, object);
    }

    public ActionClickListener(Map<String, View> inputLibrary) {
        this.viewLibrary = inputLibrary;
    }

    public void addItem(String label, View object) {
        this.viewLibrary.put(label, object);
    }

    public Map getLibrary() {
        return this.viewLibrary;
    }
}
