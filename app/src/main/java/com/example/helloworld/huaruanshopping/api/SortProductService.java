package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.ProductBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/3/8.
 */

public interface SortProductService {
//  
//    /api/products/category?cid=cid&pageNum=1
    @GET("api/products/category/{cid}")
    Observable<ProductBean> getSortListProduct(@Path("cid") int cid, @Query("pageNum") int pageNum);
}
