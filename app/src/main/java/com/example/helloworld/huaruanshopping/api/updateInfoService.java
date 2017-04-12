package com.example.helloworld.huaruanshopping.api;

import com.example.helloworld.huaruanshopping.bean.Response;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by helloworld on 2017/3/22.
 */

public interface updateInfoService {

    //    /api/users/updateUsername?id=1&username=username
    @PUT("api/users/updateUsername")
    Observable<Response> updateUserName(@Query("id") int id, @Query("username") String username);

    //    api/users/upload
//    上传文件
    @Multipart
    @POST("api/users/upload/{id}/")
    Observable<Response> uploadHeanIcon(@Path("id") int id,@Part MultipartBody.Part file);
}
