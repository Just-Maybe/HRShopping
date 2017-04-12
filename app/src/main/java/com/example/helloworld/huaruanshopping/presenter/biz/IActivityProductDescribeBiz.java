package com.example.helloworld.huaruanshopping.presenter.biz;

/**
 * Created by helloworld on 2017/3/11.
 */

public interface IActivityProductDescribeBiz {
    void getProductDescribe(int pid);

    void addToCar(int ptid, int number, int id, String token);

}
