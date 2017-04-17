package com.example.helloworld.huaruanshopping.presenter.biz;

/**
 * Created by helloworld on 2017/4/15.
 */

public interface IActivityOrderBiz {
    void pay(boolean alipayOrWechatPay);

    void orderProducts(int id, String json, String token, boolean alipayOrWechatPay);

    void orderResult(String bombId,String fid);
}
