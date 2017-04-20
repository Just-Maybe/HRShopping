package com.example.helloworld.huaruanshopping.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/2/21.
 */

public class ProductBean {


    /**
     * message : success
     * data : [{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0,"protypeSet":[{"id":2,"name":"海鲜味","pic":"/img/2.jpg","inventory":76},{"id":3,"name":"烧烤味","pic":"/img/3.jpg","inventory":100},{"id":1,"name":"香辣","pic":"/img/1.jpg","inventory":57}]},{"id":6,"name":"大1111凤凰茶","price":43,"remark":"潮州凤凰单枞茶黄枝香单枞茶 凤凰单丛凤凰茶单从单枞250克×2袋","sales":11,"protypeSet":[{"id":18,"name":"浓厚","pic":"/img/13.jpg","inventory":75}]},{"id":7,"name":"1123小冰红茶","price":64,"remark":"统一 冰红茶 250ml*24盒/箱 柠檬味茶饮料 更冰爽","sales":4,"protypeSet":[{"id":25,"name":"冰红茶1","pic":"/img/20.jpg","inventory":75}]}]
     * error_code : 0
     */
    @SerializedName("message")
    private String message;
    @SerializedName("error_code")
    private String error_code;
    @SerializedName("data")
    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * name : 1康师傅大方便面
         * price : 22.0
         * remark : 康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面
         * sales : 0
         * protypeSet : [{"id":2,"name":"海鲜味","pic":"/img/2.jpg","inventory":76},{"id":3,"name":"烧烤味","pic":"/img/3.jpg","inventory":100},{"id":1,"name":"香辣","pic":"/img/1.jpg","inventory":57}]
         */
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("price")
        private double price;
        @SerializedName("remark")
        private String remark;
        @SerializedName("sales")
        private int sales;
        @SerializedName("protypeSet")
        private ArrayList<ProtypeSetBean> protypeSet;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public ArrayList<ProtypeSetBean> getProtypeSet() {
            return protypeSet;
        }

        public void setProtypeSet(ArrayList<ProtypeSetBean> protypeSet) {
            this.protypeSet = protypeSet;
        }

        public static class ProtypeSetBean implements Serializable {
            /**
             * id : 2
             * name : 海鲜味
             * pic : /img/2.jpg
             * inventory : 76
             */
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("pic")
            private String pic;
            @SerializedName("inventory")
            private int inventory;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getInventory() {
                return inventory;
            }

            public void setInventory(int inventory) {
                this.inventory = inventory;
            }


        }

    }
}
