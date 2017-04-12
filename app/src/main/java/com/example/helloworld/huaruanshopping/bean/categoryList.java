package com.example.helloworld.huaruanshopping.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by helloworld on 2017/3/7.
 */

public class categoryList {

    /**
     * msg : [{"create_date":1487780526000,"hot":true,"id":1,"type":"方便面","update_date":1486539864000},{"create_date":1487780526000,"hot":true,"id":2,"type":"薯片","update_date":1490773469000},{"create_date":1487780526000,"hot":true,"id":3,"type":"饮料","update_date":1489045472000},{"create_date":1487780526000,"hot":true,"id":4,"type":"饼干","update_date":1488527075000},{"create_date":1487780526000,"hot":true,"id":5,"type":"糖","update_date":1488613478000},{"create_date":1487780526000,"hot":true,"id":6,"type":"茶叶","update_date":1489995881000},{"create_date":1487780526000,"hot":true,"id":7,"type":"牛奶","update_date":1489304684000}]
     * status : 200 OK
     */
    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private List<MsgBean> msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * create_date : 1487780526000
         * hot : true
         * id : 1
         * type : 方便面
         * update_date : 1486539864000
         */
        @SerializedName("create_date")
        private long create_date;
        @SerializedName("hot")
        private boolean hot;
        @SerializedName("id")
        private int id;
        @SerializedName("type")
        private String type;
        @SerializedName("update_date")
        private long update_date;

        public long getCreate_date() {
            return create_date;
        }

        public void setCreate_date(long create_date) {
            this.create_date = create_date;
        }

        public boolean isHot() {
            return hot;
        }

        public void setHot(boolean hot) {
            this.hot = hot;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public long getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(long update_date) {
            this.update_date = update_date;
        }
    }
}
