package com.example.helloworld.huaruanshopping.presenter.implView;

import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.bean.ProductListBean;

/**
 * Created by helloworld on 2017/3/11.
 */

public interface IActivityProductDescribe {
    void getProduceDescribeData(ProductBean.DataBean productBean);
    void OnSuccessAddToCar();
    void OnFailedAddToCar();
}
