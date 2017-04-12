package com.example.helloworld.huaruanshopping.listener;

/**
 * Created by helloworld on 2017/2/22.
 */

public interface OnBaseListener {
    void onComplete();

    void onFauled();
    void onNext(Object object);
}
