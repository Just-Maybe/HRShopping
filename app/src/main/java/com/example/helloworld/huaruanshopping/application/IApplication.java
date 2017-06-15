package com.example.helloworld.huaruanshopping.application;

import android.app.Application;

import com.example.helloworld.huaruanshopping.R;
import com.example.helloworld.huaruanshopping.view.GlideGalleryfinalImageLoader;

import c.b.BP;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;


/**
 * Created by helloworld on 2017/3/25.
 */

public class IApplication extends Application {
    String TAG = "111";
    private final static String ApplicationID = "fdb8f630d7926a5ad3e02cdcdde36d4b";

    @Override
    public void onCreate() {
        super.onCreate();
        initGalleryFinal();
        BP.init(ApplicationID);
    }

    private void initGalleryFinal() {
        ThemeConfig theme = new ThemeConfig.Builder()
                .setEditPhotoBgTexture(getResources().getDrawable(R.drawable.business_bg)).build();
//        //配置功能
        GlideGalleryfinalImageLoader imageLoader = new GlideGalleryfinalImageLoader();
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
//                .setEnableRotate(true)
//                .setCropSquare(true)
//                .setEnablePreview(true)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageLoader, theme)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(true)
                .build();
        GalleryFinal.init(coreConfig);
    }
}
