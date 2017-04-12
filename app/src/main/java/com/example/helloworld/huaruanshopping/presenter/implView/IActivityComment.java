package com.example.helloworld.huaruanshopping.presenter.implView;

import com.example.helloworld.huaruanshopping.bean.CommentProduct.DataBean;

/**
 * Created by helloworld on 2017/4/6.
 */

public interface IActivityComment {
    void onSuccessPostComment();

    void onFailedPostComment();

    void getCommentProduct(DataBean dataBean);
}
