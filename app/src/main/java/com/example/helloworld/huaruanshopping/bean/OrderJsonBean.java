package com.example.helloworld.huaruanshopping.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/3/30.
 */

public class OrderJsonBean implements Parcelable{

    /**
     * name : sysho
     * address : 清风阁
     * remark : 加两双筷子
     * phone : 123124325235
     * cart : [{"id":1,"number":43,"protype":{"id":4,"name":"老坛酸菜","pic":"4.jpg","inventory":100,"product":{"id":2,"name":"方便面","price":23}}},{"id":6,"number":3,"protype":{"id":4,"name":"烧烤味","pic":"4.jpg","inventory":50,"product":{"id":2,"name":"薯片","price":23}}}]
     */

    private String name;
    private String address;
    private String remark;
    private String phone;
    private List<CartBean.DataBean> cart;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CartBean.DataBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean.DataBean> cart) {
        this.cart = cart;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.remark);
        dest.writeString(this.phone);
        dest.writeList(this.cart);
    }

    public OrderJsonBean() {
    }

    protected OrderJsonBean(Parcel in) {
        this.name = in.readString();
        this.address = in.readString();
        this.remark = in.readString();
        this.phone = in.readString();
        this.cart = new ArrayList<CartBean.DataBean>();
        in.readList(this.cart, CartBean.DataBean.class.getClassLoader());
    }

    public static final Creator<OrderJsonBean> CREATOR = new Creator<OrderJsonBean>() {
        @Override
        public OrderJsonBean createFromParcel(Parcel source) {
            return new OrderJsonBean(source);
        }

        @Override
        public OrderJsonBean[] newArray(int size) {
            return new OrderJsonBean[size];
        }
    };
}
