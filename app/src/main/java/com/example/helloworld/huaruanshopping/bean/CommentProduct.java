package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/4/8.
 */

public class CommentProduct {

    /**
     * message : success
     * data : {"sorder":{"id":60,"price":63,"number":3,"protype":{"id":2,"name":"娴烽矞鍛�","pic":"/img/2.jpg","inventory":76,"product":{"id":1,"name":"1搴峰笀鍌呭ぇ鏂逛究闈�","price":22,"remark":"搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�","sales":0}}},"comment":{"id":1,"star":4,"comment":"杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮","create_date":1490962876000,"update_date":1491633677000,"flag":1,"user":{"id":1,"username":"aaaaaaaaa"},"product":{"id":1,"name":"1搴峰笀鍌呭ぇ鏂逛究闈�","price":22,"remark":"搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�","sales":0},"comment_pic_Set":[{"id":2,"pic":"2.jpg"},{"id":4,"pic":"4.jpg"},{"id":3,"pic":"3.jpg"},{"id":1,"pic":"1.jpg"}],"append_comment":null}}
     * error_code : 0
     */
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("error_code")
    private String error_code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public static class DataBean {
        /**
         * sorder : {"id":60,"price":63,"number":3,"protype":{"id":2,"name":"娴烽矞鍛�","pic":"/img/2.jpg","inventory":76,"product":{"id":1,"name":"1搴峰笀鍌呭ぇ鏂逛究闈�","price":22,"remark":"搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�","sales":0}}}
         * comment : {"id":1,"star":4,"comment":"杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮","create_date":1490962876000,"update_date":1491633677000,"flag":1,"user":{"id":1,"username":"aaaaaaaaa"},"product":{"id":1,"name":"1搴峰笀鍌呭ぇ鏂逛究闈�","price":22,"remark":"搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�","sales":0},"comment_pic_Set":[{"id":2,"pic":"2.jpg"},{"id":4,"pic":"4.jpg"},{"id":3,"pic":"3.jpg"},{"id":1,"pic":"1.jpg"}],"append_comment":null}
         */
        @SerializedName("sorder")
        private SorderBean sorder;
        @SerializedName("comment")
        private CommentBean comment;

        public SorderBean getSorder() {
            return sorder;
        }

        public void setSorder(SorderBean sorder) {
            this.sorder = sorder;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public static class SorderBean {
            /**
             * id : 60
             * price : 63.0
             * number : 3
             * protype : {"id":2,"name":"娴烽矞鍛�","pic":"/img/2.jpg","inventory":76,"product":{"id":1,"name":"1搴峰笀鍌呭ぇ鏂逛究闈�","price":22,"remark":"搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�","sales":0}}
             */

            private int id;
            @SerializedName("price")
            private double price;
            private int number;
            @SerializedName("protype")
            private ProtypeBean protype;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
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
                 * id : 2
                 * name : 娴烽矞鍛�
                 * pic : /img/2.jpg
                 * inventory : 76
                 * product : {"id":1,"name":"1搴峰笀鍌呭ぇ鏂逛究闈�","price":22,"remark":"搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�","sales":0}
                 */

                private int id;
                @SerializedName("name")
                private String name;
                @SerializedName("pic")
                private String pic;
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
                     * name : 1搴峰笀鍌呭ぇ鏂逛究闈�
                     * price : 22.0
                     * remark : 搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�
                     * sales : 0
                     */

                    private int id;
                    private String name;
                    private double price;
                    @SerializedName("remark")
                    private String remark;
                    private int sales;

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
                }
            }
        }

        public static class CommentBean {
            /**
             * id : 1
             * star : 4
             * comment : 杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮杩欐槸璇勮
             * create_date : 1490962876000
             * update_date : 1491633677000
             * flag : 1
             * user : {"id":1,"username":"aaaaaaaaa"}
             * product : {"id":1,"name":"1搴峰笀鍌呭ぇ鏂逛究闈�","price":22,"remark":"搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�","sales":0}
             * comment_pic_Set : [{"id":2,"pic":"2.jpg"},{"id":4,"pic":"4.jpg"},{"id":3,"pic":"3.jpg"},{"id":1,"pic":"1.jpg"}]
             * append_comment : null
             */
            @SerializedName("id")
            private int id;
            @SerializedName("star")
            private int star;
            @SerializedName("comment")
            private String comment;
            @SerializedName("create_date")
            private long create_date;
            @SerializedName("update_date")
            private long update_date;
            @SerializedName("flag")
            private int flag;
            @SerializedName("user")
            private UserBean user;
            private ProductBeanX product;
            private Object append_comment;
            @SerializedName("comment_pic_Set")
            private List<SorderBean.ProtypeBean> comment_pic_Set;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
            }

            public long getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(long update_date) {
                this.update_date = update_date;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public ProductBeanX getProduct() {
                return product;
            }

            public void setProduct(ProductBeanX product) {
                this.product = product;
            }

            public Object getAppend_comment() {
                return append_comment;
            }

            public void setAppend_comment(Object append_comment) {
                this.append_comment = append_comment;
            }

            public List<SorderBean.ProtypeBean> getComment_pic_Set() {
                return comment_pic_Set;
            }

            public void setComment_pic_Set(List<SorderBean.ProtypeBean> comment_pic_Set) {
                this.comment_pic_Set = comment_pic_Set;
            }

            public static class UserBean {
                /**
                 * id : 1
                 * username : aaaaaaaaa
                 */
                @SerializedName("id")
                private int id;
                @SerializedName("username")
                private String username;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }
            }

            public static class ProductBeanX {
                /**
                 * id : 1
                 * name : 1搴峰笀鍌呭ぇ鏂逛究闈�
                 * price : 22.0
                 * remark : 搴峰笀鍌� 澶ч浠ｇ孩鐑х墰鑲夐潰 124g*5/琚� 鏂逛究闈㈡场闈�
                 * sales : 0
                 */

                private int id;
                private String name;
                private double price;
                private String remark;
                private int sales;

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
            }
        }
    }
}
