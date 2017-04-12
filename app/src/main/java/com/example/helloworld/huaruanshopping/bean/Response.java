package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by helloworld on 2017/3/13.
 */

public class Response {
    /**
     * status : success
     * msg : 成功添加至购物车
     */
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String msg;
    @SerializedName("data")
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
