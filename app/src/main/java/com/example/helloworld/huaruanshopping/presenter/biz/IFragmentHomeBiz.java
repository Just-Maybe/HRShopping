package com.example.helloworld.huaruanshopping.presenter.biz;

import com.example.helloworld.huaruanshopping.listener.OnBaseListener;

/**
 * Created by helloworld on 2017/2/21.
 */

public interface IFragmentHomeBiz {
    void getHomePrudoct(int flag, int pageNum,boolean isLoadMore);

    void getMoreHomePrudoctData(int pageNum);
}
