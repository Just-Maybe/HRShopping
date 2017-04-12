package com.example.helloworld.huaruanshopping.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by helloworld on 2017/3/22.
 */

public class address implements Parcelable {
    private String name;
    private String address;
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public address() {
    }

    public address(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
    }
    public void setAddress(address address){
        this.name = address.getName();
        this.address = address.getAddress();
        this.phone = address.getPhone();
    }
    public static final Creator<address> CREATOR = new Creator<address>() {
        @Override
        public address createFromParcel(Parcel in) {
            return new address(in);
        }

        @Override
        public address[] newArray(int size) {
            return new address[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phone);
    }
}
