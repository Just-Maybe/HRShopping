package com.example.helloworld.huaruanshopping.presenter.implView;

import com.example.helloworld.huaruanshopping.bean.CartBean;

import java.util.List;

/**
 * Created by helloworld on 2017/3/13.
 */

public interface IFragmentCart {
    void getCartList(List<CartBean.DataBean> cartBeanList);
//    void orderNow();
    void onOrderSuccess();
}
