package com.example.helloworld.huaruanshopping.util;

import android.content.Context;

/**
 * Created by helloworld on 2017/1/22.
 */

public class DensityUtil {
    /**
     * 从dp 转成为px
     *
     * @param context
     * @param dpvalue
     * @return
     */
    public static int dip2px(Context context, float dpvalue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpvalue * scale + 0.5f);
    }

    /**
     *  从px 转为 dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale * 0.5f);
    }
}
