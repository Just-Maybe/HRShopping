package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/2/21.
 */

public class ProductListBean {
    /**
     * commend : true
     * create_date : 2017-03-07T14:10:05
     * id : 1
     * name : 康师傅方便面
     * open : true
     * price : 21.0
     * protypeSet : [{"id":2,"inventory":748,"name":"海鲜味","pic":"2.jpg"},{"id":3,"inventory":18,"name":"烧烤味","pic":"3.jpg"},{"id":1,"inventory":10,"name":"香辣","pic":"1.jpg"}]
     * remark : 康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面
     */
    @SerializedName("commend")
    private boolean commend;
    @SerializedName("create_date")
    private String create_date;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("open")
    private boolean open;
    @SerializedName("price")
    private double price;
    @SerializedName("remark")
    private String remark;
    @SerializedName("sales")
    private String sales;
    @SerializedName("protypeSet")
    private List<ProtypeSetBean> protypeSet;

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public boolean isCommend() {
        return commend;
    }

    public void setCommend(boolean commend) {
        this.commend = commend;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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

    public List<ProtypeSetBean> getProtypeSet() {
        return protypeSet;
    }

    public void setProtypeSet(List<ProtypeSetBean> protypeSet) {
        this.protypeSet = protypeSet;
    }
}
