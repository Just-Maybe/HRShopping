package com.example.helloworld.huaruanshopping.api;


import com.example.helloworld.huaruanshopping.bean.ProductBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/2/22.
 */

public interface HomeProductService {
    //    /api/products/flag?flag=flag&pageNum=1
    @GET("api/products/flag/{flag}")
    Observable<ProductBean> getHomeListProduct(@Path("flag") int flag, @Query("pageNum") int pageNum);
}
