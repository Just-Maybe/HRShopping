package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/3/15.
 */

public class OrderProducts {

    /**
     * name : sysho
     * address : 清风阁
     * remark : 加两双筷子
     * phone : 15626401344
     * cart : [{"id":3,"number":2,"protype":{"id":1,"name":"香辣","pic":"/img/1.jpg","inventory":64,"product":{"id":1,"name":"方便面","price":23}}},{"id":5,"number":3,"protype":{"id":6,"name":"乳酸菌","pic":"/img/6.jpg","inventory":754,"product":{"id":15,"name":"蒙牛","price":40}}}]
     */
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("remark")
    private String remark;
    @SerializedName("phone")
    private String phone;
    @SerializedName("cart")
    private List<CartBean> cart;

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

    public List<CartBean> getCart() {
        return cart;
    }

    public void setCart(List<CartBean> cart) {
        this.cart = cart;
    }

    public static class CartBean {
        /**
         * id : 3
         * number : 2
         * protype : {"id":1,"name":"香辣","pic":"/img/1.jpg","inventory":64,"product":{"id":1,"name":"方便面","price":23}}
         */
        @SerializedName("id")
        private int id;
        @SerializedName("number")
        private int number;
        @SerializedName("protype")
        private ProtypeBean protype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public ProtypeBean getProtype() {
            return protype;
        }

        public void setProtype(ProtypeBean protype) {
            this.protype = protype;
        }

        public static class ProtypeBean {
            /**
             * id : 1
             * name : 香辣
             * pic : /img/1.jpg
             * inventory : 64
             * product : {"id":1,"name":"方便面","price":23}
             */
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("pic")
            private String pic;
            @SerializedName("inventory")
            private int inventory;
            @SerializedName("product")
            private ProductBean product;

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

            public ProductBean getProduct() {
                return product;
            }

            public void setProduct(ProductBean product) {
                this.product = product;
            }

            public static class ProductBean {
                /**
                 * id : 1
                 * name : 方便面
                 * price : 23
                 */
                @SerializedName("id")
                private int id;
                @SerializedName("name")
                private String name;
                @SerializedName("price")
                private int price;

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

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }
            }
        }
    }
}
