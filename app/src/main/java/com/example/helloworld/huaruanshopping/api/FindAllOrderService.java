package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.bean.orderList;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/3/18.
 */

public interface FindAllOrderService {
//   /api/forders/user?id=1&pageNum=1&token=token

    /**
     * 获取全部订单
     *
     * @param id
     * @param pageNum
     * @param token   /api/forders/user?id=1&pageNum=1&token=token
     * @return
     */
    @GET("api/forders/user")
    Observable<orderList> getAllOrder(@Query("id") int id, @Query("pageNum") int pageNum, @Query("token") String token);


    /**
     * /api/forders/{fid}?id=1&token=token
     * 删除订单
     *
     * @param id
     * @param token
     * @return
     */
    @DELETE("api/forders/{fid}")
    Observable<Response> deleteOrder(@Path("fid") String fid, @Query("id") int id, @Query("token") String token);


    /**
     * /api/forders/{fid}?id=1&token=token
     * 取消订单
     *
     * @param id
     * @return
     */
    @PUT("api/forders/{fid}")
    Observable<Response> cancelOrder(@Path("fid") String fid, @Query("id") int id, @Query("token") String token);
}
