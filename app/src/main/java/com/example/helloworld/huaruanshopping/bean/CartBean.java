package com.example.helloworld.huaruanshopping.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helloworld on 2017/3/13.
 */

public class CartBean implements Parcelable {


    /**
     * id : 2
     * number : 1
     * protype : {"id":2,"name":"海鲜味","pic":"/img/2.jpg","inventory":76,"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}}
     */
    @SerializedName("id")
    private int id;
    @SerializedName("number")
    private int number;
    @SerializedName("protype")
    private ProtypeBean protype;
    /**
     * message : success
     * data : [{"id":2,"number":1,"protype":{"id":2,"name":"海鲜味","pic":"/img/2.jpg","inventory":76,"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}}},{"id":24,"number":30,"protype":{"id":1,"name":"香辣","pic":"/img/1.jpg","inventory":57,"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}}},{"id":6,"number":2,"protype":{"id":5,"name":"烧烤味","pic":"/img/5.jpg","inventory":57,"product":{"id":2,"name":"小乐事薯片","price":23,"remark":"乐事薯片40g*10包组合整箱办公室休闲膨化零食品送女友批发大礼包","sales":10}}},{"id":9,"number":1,"protype":{"id":4,"name":"芥末味","pic":"/img/4.jpg","inventory":260,"product":{"id":2,"name":"小乐事薯片","price":23,"remark":"乐事薯片40g*10包组合整箱办公室休闲膨化零食品送女友批发大礼包","sales":10}}},{"id":5,"number":3,"protype":{"id":6,"name":"乳酸菌","pic":"/img/6.jpg","inventory":754,"product":{"id":15,"name":"大蒙牛","price":40,"remark":"蒙牛牛奶，喝了力大无穷，快来试试","sales":34}}},{"id":10,"number":2,"protype":{"id":7,"name":"纯牛奶","pic":"/img/7.jpg","inventory":64,"product":{"id":14,"name":"大伊利","price":52,"remark":"宜养乳酸菌牛奶酸酸甜甜超好喝100mlx20瓶酸奶饮品","sales":61}}},{"id":11,"number":1,"protype":{"id":13,"name":"脉动","pic":"/img/8.jpg","inventory":322,"product":{"id":9,"name":"大瓶脉动","price":25,"remark":"达能 脉动维生素饮料青柠味 600ml*4/组 健康饮料","sales":88}}},{"id":29,"number":2,"protype":{"id":19,"name":"脉动1","pic":"/img/14.jpg","inventory":65,"product":{"id":9,"name":"大瓶脉动","price":25,"remark":"达能 脉动维生素饮料青柠味 600ml*4/组 健康饮料","sales":88}}},{"id":12,"number":1,"protype":{"id":14,"name":"绿茶1","pic":"/img/9.jpg","inventory":335,"product":{"id":3,"name":"小绿茶","price":645,"remark":"Hbuy荷兰 原装进口 Wicky桃子味 冰绿茶 营养果汁饮料 200ml*6盒","sales":20}}},{"id":13,"number":1,"protype":{"id":15,"name":"奥利奥1","pic":"/img/10.jpg","inventory":77,"product":{"id":4,"name":"大奥利奥","price":43,"remark":"巧克力夹心饼干oreo巧轻薄脆","sales":32}}}]
     * error_code : 0
     */
    @SerializedName("message")
    private String message;
    @SerializedName("error_code")
    private String error_code;
    @SerializedName("data")
    private List<orderList.DataBean.SorderSetBean> data;

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

    public List<orderList.DataBean.SorderSetBean> getData() {
        return data;
    }

    public void setData(List<orderList.DataBean.SorderSetBean> data) {
        this.data = data;
    }

    public static class ProtypeBean {
        /**
         * id : 2
         * name : 海鲜味
         * pic : /img/2.jpg
         * inventory : 76
         * product : {"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}
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
             * id : 1
             * name : 1康师傅大方便面
             * price : 22.0
             * remark : 康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面
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

    public static class DataBean implements Parcelable {
        /**
         * id : 2
         * number : 1
         * protype : {"id":2,"name":"海鲜味","pic":"/img/2.jpg","inventory":76,"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}}
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

        public static class ProtypeBean implements Parcelable {
            /**
             * id : 2
             * name : 海鲜味
             * pic : /img/2.jpg
             * inventory : 76
             * product : {"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}
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

            public static class ProductBean implements Parcelable {
                /**
                 * id : 1
                 * name : 1康师傅大方便面
                 * price : 22.0
                 * remark : 康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面
                 * sales : 0
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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(this.id);
                    dest.writeString(this.name);
                    dest.writeDouble(this.price);
                    dest.writeString(this.remark);
                    dest.writeInt(this.sales);
                }

                public ProductBean() {
                }

                protected ProductBean(Parcel in) {
                    this.id = in.readInt();
                    this.name = in.readString();
                    this.price = in.readDouble();
                    this.remark = in.readString();
                    this.sales = in.readInt();
                }

                public static final Creator<ProductBean> CREATOR = new Creator<ProductBean>() {
                    @Override
                    public ProductBean createFromParcel(Parcel source) {
                        return new ProductBean(source);
                    }

                    @Override
                    public ProductBean[] newArray(int size) {
                        return new ProductBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.name);
                dest.writeString(this.pic);
                dest.writeInt(this.inventory);
                dest.writeParcelable(this.product, flags);
            }

            public ProtypeBean() {
            }

        protected ProtypeBean(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.pic = in.readString();
            this.inventory = in.readInt();
            this.product = in.readParcelable(ProductBean.class.getClassLoader());
        }

        public static final Creator<ProtypeBean> CREATOR = new Creator<ProtypeBean>() {
            @Override
            public ProtypeBean createFromParcel(Parcel source) {
                return new ProtypeBean(source);
            }

            @Override
            public ProtypeBean[] newArray(int size) {
                return new ProtypeBean[size];
            }
        };
    }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.number);
            dest.writeParcelable(this.protype, flags);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.number = in.readInt();
            this.protype = in.readParcelable(ProtypeBean.class.getClassLoader());
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.number);
        dest.writeParcelable((Parcelable) this.protype, flags);
        dest.writeString(this.message);
        dest.writeString(this.error_code);
        dest.writeList(this.data);
    }

    public CartBean() {
    }

    protected CartBean(Parcel in) {
        this.id = in.readInt();
        this.number = in.readInt();
        this.protype = in.readParcelable(ProtypeBean.class.getClassLoader());
        this.message = in.readString();
        this.error_code = in.readString();
        this.data = new ArrayList<orderList.DataBean.SorderSetBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Creator<CartBean> CREATOR = new Creator<CartBean>() {
        @Override
        public CartBean createFromParcel(Parcel source) {
            return new CartBean(source);
        }

        @Override
        public CartBean[] newArray(int size) {
            return new CartBean[size];
        }
    };
}
