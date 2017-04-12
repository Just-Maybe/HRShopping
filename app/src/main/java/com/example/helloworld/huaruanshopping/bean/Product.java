package com.example.helloworld.huaruanshopping.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by helloworld on 2017/1/17.
 */

public class Product implements Serializable {
    private BigDecimal price;
    private String name;
    private String No;
    private String pic_url;
    private Boolean commend;
    private Boolean open;
    private String remark;
    private Integer id;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
