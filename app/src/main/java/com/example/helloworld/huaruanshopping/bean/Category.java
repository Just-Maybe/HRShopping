package com.example.helloworld.huaruanshopping.bean;

/**
 * Created by helloworld on 2017/2/25.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Category implements Serializable {

    /**
     * message : success
     * data : [{"id":1,"type":"方便面","hot":true},{"id":2,"type":"薯片","hot":true},{"id":3,"type":"饮料","hot":true},{"id":4,"type":"饼干","hot":true},{"id":5,"type":"糖","hot":true},{"id":6,"type":"茶叶","hot":true},{"id":7,"type":"牛奶","hot":true}]
     * error_code : 0
     */
    @SerializedName("message")
    private String message;
    @SerializedName("error_code")
    private String error_code;
    @SerializedName("data")
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * type : 方便面
         * hot : true
         */
        @SerializedName("id")
        private int id;
        @SerializedName("type")
        private String type;
        @SerializedName("hot")
        private boolean hot;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isHot() {
            return hot;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }
    }
}