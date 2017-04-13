package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.CartBean;
import com.example.helloworld.huaruanshopping.bean.Category;
import com.example.helloworld.huaruanshopping.bean.Response;

import java.util.List;

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
 * Created by helloworld on 2017/3/13.
 */

public interface CartService {
    ///    /api/carts/user?id=1&token=532a8a18b75079da0c48414014600600d64737f36e330997
    @GET("api/carts/user")
    Observable<CartBean> getListCart(@Query("id") int id, @Query("token") String token);

    //    修改购物车商品数量(数量，购物项id)
//    /api/carts/{id}?number=1
    @PUT("api/carts/{id}")
    Observable<Response> updateCart(@Path("id") int id, @Query("number") int number);

    //    删除购物车Item
//    /api/carts/{id}
    @DELETE("api/carts/{id}")
    Observable<Response> deleteCartItem(@Path("id") int id);

//   /cart_placeOrderImmediately.action?ptids=1,2,3&numbers=1,2,1
// &suserid=1&token=532a8a18b75079da0c48414014600600d64737f36e330997

    //    判断是否有库存
//    /api/carts/buy?ptids=ptids&numbers=numbers&id=1&token=token
    @GET("api/carts/buy")
    Observable<Response> orderImmediately(@Query("ptids") String ptids,
                                          @Query("numbers") String numbers,
                                          @Query("id") int userid,
                                          @Query("token") String token);

    //    /api/forders/user?id=1&order_json=order_json&token=token
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @FormUrlEncoded
    @POST("api/forders/user")
    Observable<Response> order(@Field("id") int id,
                               @Field("order_json") String order_json,
                               @Field("token") String token);


}
