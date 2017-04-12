package com.example.helloworld.huaruanshopping.presenter.implView;

import com.example.helloworld.huaruanshopping.bean.ProductBean;

import java.util.List;

/**
 * Created by helloworld on 2017/2/21.
 */

public interface IFragmentBaseView {
    void showLoading();

    void hideLoading();

    void showFailedError();

    void showData(List<ProductBean.DataBean> list,boolean isLoadMore);
}
