package com.less.tplayer.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.less.tplayer.TpApplication;

public class NetUtils {
    /**
     * 判断是否有网络连接
     * @return
     */
    public static boolean hasInternet() {
        ConnectivityManager cm = (ConnectivityManager) TpApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isAvailable() && info.isConnected();
    }

    @Deprecated
    public static boolean hasConnect(Context context){
        boolean wifiConnected = isWIFIConnected(context);
        boolean mobileConnected = isMOBILEConnected(context);
        boolean wiredConnected = isWiredConnected(context);
        if(!wifiConnected && !mobileConnected && !wiredConnected){
            // 如果都没有连接，提示用户当前没有网络
            return false;
        }
        return true;
    }


    public static boolean isWiredConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    public static boolean isMOBILEConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(networkInfo !=null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    public static boolean isWIFIConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(networkInfo !=null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }
}