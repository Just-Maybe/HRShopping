package com.example.helloworld.huaruanshopping.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by helloworld on 2017/3/22.
 */

public class SharedPreferencesUtils {
    private static final String FiLE_NAME = "share_data";

    /**
     * 保存数据的方法
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setParam(Context context, String key, Object value) {
        String type = value.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FiLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) value);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) value);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) value);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) value);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) value);
        }
        editor.commit();
    }


    public static Object getParam(Context context, String key, Object defaultValue) {
        String type = defaultValue.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(FiLE_NAME, Context.MODE_PRIVATE);
        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultValue);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultValue);
        }
        return null;
    }
}
