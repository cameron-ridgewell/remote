package com.remote.utilities;

import com.remote.R;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cameronridgewell on 7/31/15.
 */
public class StoredResources {
    private Map<String, Integer> deviceIcons = new HashMap<String, Integer>();

    private static StoredResources instance = null;

    protected StoredResources(){
        deviceIcons.put("Chromecast", R.drawable.ic_chrome_20);
        deviceIcons.put("XBox 360", R.drawable.ic_xbox_20);
        deviceIcons.put("Wii U", R.drawable.ic_wiiu_20);
        deviceIcons.put("HDMI Aux", R.drawable.ic_hdmi_20);
        deviceIcons.put("Television", R.drawable.ic_television_20);
        deviceIcons.put("Device...", R.drawable.ic_drawer);
    };

    public static StoredResources getInstance() {
        if (instance == null) {
            return new StoredResources();
        } else {
            return instance;
        }
    }

    public Map<String, Integer> getdeviceIconDict() {
        return deviceIcons;
    }
}
