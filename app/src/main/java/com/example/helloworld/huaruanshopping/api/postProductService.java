package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.Response;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by helloworld on 2017/3/13.
 */

public interface postProductService {
    //   

    //     /api/protypes/cart/add?ptid=1&number=1&id=1&token=token
    @FormUrlEncoded
    @POST("api/protypes/cart/add")
    Observable<Response> getPostRespnose(@Field("ptid") int ptid, @Field("number") int number, @Field("id") int id, @Field("token") String token);

}
