package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by helloworld on 2017/3/7.
 */

public class ProtypeSetBean {
    /**
     * id : 2
     * inventory : 748
     * name : 海鲜味
     * pic : 2.jpg
     */
    @SerializedName("id")
    private int id;
    @SerializedName("inventory")
    private int inventory;
    @SerializedName("name")
    private String name;
    @SerializedName("pic")
    private String pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
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
}
