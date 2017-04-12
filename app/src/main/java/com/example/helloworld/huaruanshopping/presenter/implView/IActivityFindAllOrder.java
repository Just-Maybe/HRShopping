package com.example.helloworld.huaruanshopping.presenter.implView;

import com.example.helloworld.huaruanshopping.bean.orderList.DataBean;

import java.util.List;

/**
 * Created by helloworld on 2017/3/18.
 */

public interface IActivityFindAllOrder {
    void getAllOrder(List<DataBean> orderList);
    void onSuccessCancelOrder();
//    void onFailedDelete();
    void onSuccessGetAllOrder();
}
