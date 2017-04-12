package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/4/6.
 */

public class Comment {

    /**
     * message : success
     * data : [{"id":12,"star":5,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490963005000,"update_date":1491355840000,"flag":0,"user":{"id":12,"username":"JBA2828329"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[],"append_comment":null},{"id":11,"star":1,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962997000,"update_date":1491355839000,"flag":0,"user":{"id":11,"username":"GRE533654"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[],"append_comment":null},{"id":10,"star":3,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962988000,"update_date":1491355841000,"flag":0,"user":{"id":10,"username":"JSA063269"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[],"append_comment":null},{"id":9,"star":3,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962980000,"update_date":1491355843000,"flag":1,"user":{"id":9,"username":"GGR436346"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[],"append_comment":null},{"id":8,"star":2,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962969000,"update_date":1491355845000,"flag":0,"user":{"id":8,"username":"DHI874839"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[{"id":15,"pic":"15.jpg"}],"append_comment":null},{"id":7,"star":5,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962959000,"update_date":1491355846000,"flag":1,"user":{"id":7,"username":"FFC6177576"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[{"id":14,"pic":"14.jpg"}],"append_comment":null},{"id":6,"star":2,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962952000,"update_date":1491355854000,"flag":0,"user":{"id":6,"username":"BGC4170356"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[{"id":13,"pic":"13.jpg"}],"append_comment":{"id":6,"append_comment":"追加评论追加评论追加评论追加评论追加评论追加评论追加评论","create_date":1491029801000,"append_comment_pic_Set":[]}},{"id":5,"star":5,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962944000,"update_date":1491355852000,"flag":1,"user":{"id":15,"username":"GCI6128638"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[{"id":12,"pic":"12.jpg"}],"append_comment":{"id":5,"append_comment":"追加评论追加评论追加评论追加评论追加评论追加评论","create_date":1491029795000,"append_comment_pic_Set":[{"id":4,"pic":"5.jpg"}]}},{"id":4,"star":1,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962933000,"update_date":1491355109000,"flag":0,"user":{"id":4,"username":"GJG1063129"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[{"id":9,"pic":"9.jpg"},{"id":11,"pic":"11.jpg"},{"id":10,"pic":"10.jpg"},{"id":8,"pic":"8.jpg"}],"append_comment":{"id":4,"append_comment":"追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论追加评论","create_date":1491029790000,"append_comment_pic_Set":[]}},{"id":3,"star":2,"comment":"这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论这是评论","create_date":1490962917000,"update_date":1491355107000,"flag":1,"user":{"id":16,"username":"FAE5560868"},"product":{"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0},"comment_pic_Set":[{"id":7,"pic":"7.jpg"}],"append_comment":{"id":3,"append_comment":"追加评论追加评论追加评论追加评论","create_date":1491029781000,"append_comment_pic_Set":[{"id":3,"pic":"3.jpg"}]}}]
     * error_code : 0
     */
    @SerializedName("message")
    private String message;
    @SerializedName("error_code")
    private String error_code;
    @SerializedName("data")
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 12
         * star : 5
         * comment : 这是评论这是评论这是评论这是评论这是评论这是评论这是评论
         * create_date : 1490963005000
         * update_date : 1491355840000
         * flag : 0
         * user : {"id":12,"username":"JBA2828329"}
         * product : {"id":1,"name":"1康师傅大方便面","price":22,"remark":"康师傅 大食代红烧牛肉面 124g*5/袋 方便面泡面","sales":0}
         * comment_pic_Set : []
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
        @SerializedName("product")
        private ProductBean product;
        @SerializedName("append_comment")
        private Object append_comment;
        @SerializedName("comment_pic_Set")
        private List<CommentPicSetBean> comment_pic_Set;

        public List<CommentPicSetBean> getComment_pic_Set() {
            return comment_pic_Set;
        }

        public void setComment_pic_Set(List<CommentPicSetBean> comment_pic_Set) {
            this.comment_pic_Set = comment_pic_Set;
        }

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

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public Object getAppend_comment() {
            return append_comment;
        }

        public void setAppend_comment(Object append_comment) {
            this.append_comment = append_comment;
        }

        public static class CommentPicSetBean {
            /**
             * id : 34
             * pic : f0829529-02bf-4fc7-a39d-e85955098c4b.jpg
             */

            @SerializedName("id")
            private int idX;
            @SerializedName("pic")
            private String pic;

            public int getIdX() {
                return idX;
            }

            public void setIdX(int idX) {
                this.idX = idX;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

    }

    public static class UserBean {
        /**
         * id : 12
         * username : JBA2828329
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

    public static class ProductBean {
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
    }
}

