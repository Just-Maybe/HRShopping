package com.example.helloworld.huaruanshopping.presenter.biz;

import java.util.List;

/**
 * Created by helloworld on 2017/3/13.
 */

public interface IFragmentCartBiz {
    void getData(int userid, String token);

    void isEnoughProduct(List<Integer> ptids, List<Integer> numbers, int userid, String token);

}
