package com.study.android.zhangsht.hqs.utils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by zhangsht on 2017/5/18.
 */

public class GetLocalWifiIP extends Activity {
    public String getIP() {
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        int ipText = info.getIpAddress();
        return ipText + "";
    }
}
