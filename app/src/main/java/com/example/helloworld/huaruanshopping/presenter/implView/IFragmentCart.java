package com.example.helloworld.huaruanshopping.presenter.implView;

import com.example.helloworld.huaruanshopping.bean.CartBean;
import com.example.helloworld.huaruanshopping.bean.orderList;

import java.util.List;

/**
 * Created by helloworld on 2017/3/13.
 */

public interface IFragmentCart {
    void getCartList(List<orderList.DataBean.SorderSetBean> cartBeanList);
//    void orderNow();
    void onOrderSuccess();
}
