package com.example.helloworld.huaruanshopping.test;

import com.example.helloworld.huaruanshopping.bean.CartBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by helloworld on 2017/3/16.
 */

public class Example {
    static String jsonString = "{\"name\": \"sysho\",\"address\":\"清风阁\",\"remark\":\"加两双筷子\",\"phone\": \"123124325235\",\"cart\":[{\"id\":1,\"number\":43,\"protype\":{\"id\": 4,\"name\":\"老坛酸菜\",\"pic\":\"4.jpg\",\"inventory\":100,\"product\":{\"id\":2,\"name\":\"方便面\",\"price\": 23}}},{\"id\": 6,\"number\": 3,\"protype\":{\"id\": 4,\"name\":\"烧烤味\",\"pic\":\"4.jpg\",\"inventory\":50,\"product\":{\"id\": 2,\"name\":\"薯片\",\"price\": 23}}}]}";

    public interface BlogService {
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @FormUrlEncoded
        @POST("/forder_saveOrder.action")
        Call<Result<CartBean>> createListCart(@Field("order_json") String mListCart, @Field("userid") int userid);
    }

    public static void main(String[] args) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.16.169.22:8080")
                //可以接收自定义的Gson，当然也可以不传
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        ListCart listCart = gson.fromJson(jsonString, ListCart.class);
        BlogService service = retrofit.create(BlogService.class);
        Call<Result<CartBean>> call = service.createListCart(jsonString, 1);
        call.enqueue(new Callback<Result<CartBean>>() {
            @Override
            public void onResponse(Call<Result<CartBean>> call, Response<Result<CartBean>> response) {
                Result<CartBean> result = response.body();
                System.out.println(result);
            }

            @Override
            public void onFailure(Call<Result<CartBean>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
