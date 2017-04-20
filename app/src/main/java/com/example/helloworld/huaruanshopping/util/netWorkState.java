package com.example.helloworld.huaruanshopping.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by helloworld on 2017/4/19.
 */

public class netWorkState {
    public static boolean isNetWorkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//       return networkInfo.getType()==ConnectivityManager.TYPE_MOBILE; 判断是否未移动网络
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

}
