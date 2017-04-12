package com.example.helloworld.huaruanshopping.presenter.biz;

import java.util.List;

/**
 * Created by helloworld on 2017/4/6.
 */

public interface IActivityCommentBiz {
    void postComment(int pid, int uid, int star, String comment, List<String> imgList);

    void postAppendComment(int comment_id, int pid, int uid, String append_comment, List<String> imgList);

    void getCommentProduct(int pid, int uid);

}
