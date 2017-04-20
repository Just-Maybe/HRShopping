package com.example.helloworld.huaruanshopping.api;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by helloworld on 2017/2/22.
 */

public class HttpMethods {
    //172.16.169.22
//    http://www.chjcal.cc/
//    http://192.168.191.3:8080/HR-Shop/
    public static final String BASE_URL = "http://www.chjcal.cc/";

    private static final int DEFAULT_TIMEOUT = 5;


    public HomeProductService homeProductApi;
    public CategoryService categoryService;
    public SortProductService sortProductService;
    public ProductDescribeService productDescribeService;
    public postProductService mPostProductService;
    public CartService cartService;
    public FindAllOrderService findAllOrderService;
    public updateInfoService updateInfoService;
    public OkHttpClient client;
//    private Gson gson;

    //构造方法是有
    private HttpMethods() {
        //手动创建一个OKHttpClient 并设置超时时间
        client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
//        gson = new GsonBuilder()
//                .setLenient()
//                .create();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //内部类 访问HttpMethods 时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }


    public HomeProductService getHomeProductApi() {
        if (homeProductApi == null) {
            homeProductApi = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build().create(HomeProductService.class);
        }
        return homeProductApi;
    }

    public CategoryService getCategoryService() {
        if (categoryService == null) {
            categoryService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(CategoryService.class);
        }
        return categoryService;
    }

    public SortProductService getSortProductService() {
        if (sortProductService == null) {
            sortProductService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(SortProductService.class);
        }
        return sortProductService;
    }

    public ProductDescribeService getProductDescribeService() {
        if (null == productDescribeService) {
            productDescribeService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ProductDescribeService.class);
        }
        return productDescribeService;
    }

    public postProductService getPostProductService() {
        if (null == mPostProductService) {
            mPostProductService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(postProductService.class);
        }
        return mPostProductService;
    }


    public CartService getCartService() {
        if (null == cartService) {
            cartService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(CartService.class);
        }
        return cartService;
    }

    public FindAllOrderService getFindAllOrderService() {
        if (null == findAllOrderService) {
            findAllOrderService = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(FindAllOrderService.class);
        }
        return findAllOrderService;
    }

    public updateInfoService getUpdateInfoService() {
        if (null == updateInfoService) {
            updateInfoService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build().create(updateInfoService.class);
        }
        return updateInfoService;
    }

    static class FileRequestBodyConverterFactory extends Converter.Factory {
        @Override
        public Converter<File, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return new FileRequestBodyConverter();
        }
    }

    static class FileRequestBodyConverter implements Converter<File, RequestBody> {

        @Override
        public RequestBody convert(File file) throws IOException {
            return RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        }
    }
}
