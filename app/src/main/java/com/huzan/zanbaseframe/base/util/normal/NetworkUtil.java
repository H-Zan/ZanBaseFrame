package com.huzan.zanbaseframe.base.util.normal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ZAN on 2017/7/18.
 * 
 */
public final class NetworkUtil {

    /**
     * 判断网络连接是否可用
     */
    public static Boolean isNetworkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo networkinfo = manager.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isConnected()  && networkinfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
