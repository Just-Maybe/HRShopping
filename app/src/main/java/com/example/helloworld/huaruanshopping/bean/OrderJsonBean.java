package com.example.helloworld.huaruanshopping.bean;

import java.util.List;

/**
 * Created by helloworld on 2017/3/30.
 */

public class OrderJsonBean {


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
         * id : 1
         * number : 43
         * protype : {"id":4,"name":"老坛酸菜","pic":"4.jpg","inventory":100,"product":{"id":2,"name":"方便面","price":23}}
         */

        private int id;
        private int number;
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
             * id : 4
             * name : 老坛酸菜
             * pic : 4.jpg
             * inventory : 100
             * product : {"id":2,"name":"方便面","price":23}
             */

            private int id;
            private String name;
            private String pic;
            private int inventory;
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
                 * id : 2
                 * name : 方便面
                 * price : 23
                 */

                private int id;
                private String name;
                private double price;

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
            }
        }
    }
}
