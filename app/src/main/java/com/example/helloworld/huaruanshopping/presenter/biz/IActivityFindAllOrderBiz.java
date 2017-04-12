package com.example.helloworld.huaruanshopping.presenter.biz;

/**
 * Created by helloworld on 2017/3/18.
 */

public interface IActivityFindAllOrderBiz {
    void getAllOrder(int userid, int pageNum, String token,String flag);
    void deleteOrder(String fid,int id,String token);
    void cancelOrder(String fid,int id,String token);
}
