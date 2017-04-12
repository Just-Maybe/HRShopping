package com.example.helloworld.huaruanshopping.presenter.biz;

import java.util.List;

/**
 * Created by helloworld on 2017/4/6.
 */

public interface IFragmentPostCommentBiz {
    void postComment(int pid,int uid,int star,String comment,List<String> imgPaths);
}
