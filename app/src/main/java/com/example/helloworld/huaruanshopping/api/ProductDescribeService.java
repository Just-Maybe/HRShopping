package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.Comment;
import com.example.helloworld.huaruanshopping.bean.CommentProduct;
import com.example.helloworld.huaruanshopping.bean.ProductBean;
import com.example.helloworld.huaruanshopping.bean.Response;
import com.example.helloworld.huaruanshopping.bean.productDescribe;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/3/11.
 */

public interface ProductDescribeService {
    //    /product_getProduct.action?pid=1
//    http://119.29.24.119/api/products/details/1
    @GET("api/products/details/{id}")
    Observable<ProductBean> getProductDescribe(@Path("id") int id);

    //    获取评论列表
//    http://www.chjcal.cc/api/comments/1?pageNum=1
    @GET("api/comments/{pid}")
    Observable<Comment> getComment(@Path("pid") int pid, @Query("pageNum") int pageNum);

    //    http://localhost:8080/HR-Shop/api/comments/comment加参数
    @Multipart
    @POST("api/comments/comment")
    Observable<Response> postComment(@Query("pid") int pid,
                                     @Query("uid") int uid,
                                     @Query("star") int star,
                                     @Query("comment") String comment,
                                     @Part List<MultipartBody.Part> files);

    //    http://localhost:8080/HR-Shop/api/comments/append_comment?comment_id=7&pid=1&uid=7&append_Comment=追加评论追加评论追加评论追加评论追加评论追加评论追加评论
    @Multipart
    @POST("api/comments/append_comment")
    Observable<Response> postAppendComment(@Query("comment_id") int comment_id,
                                           @Query("pid") int pid,
                                           @Query("uid") int uid,
                                           @Query("append_Comment") String append_comment,
                                           @Part List<MultipartBody.Part> files);

    //    评论上面的商品详情
//    http://localhost:8080/HR-Shop/api/sorders/60?pid=1&uid=1
    @GET("api/sorders/60")
    Observable<CommentProduct> getCommentProduct(@Query("pid") int pid, @Query("uid") int uid);
}
