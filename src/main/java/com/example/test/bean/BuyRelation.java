package com.example.test.bean;

import java.util.Date;

public class BuyRelation {
    private String buyrelationid;
    private String userid;
    private String goodsid;
    private String buynum;
    private String goodratetogoods;
    private String evaluatetogoods;
    private String goodratetomerchant;
    private String state;//0代表加购，其他用户还是可以买
                         //1代表已支付，其他用户不能再购买
    private java.util.Date ordertime;

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getBuyrelationid() {
        return buyrelationid;
    }

    public void setBuyrelationid(String buyrelationid) {
        this.buyrelationid = buyrelationid;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBuynum() {
        return buynum;
    }

    public void setBuynum(String buynum) {
        this.buynum = buynum;
    }

    public String getGoodratetogoods() {
        return goodratetogoods;
    }

    public void setGoodratetogoods(String goodratetogoods) {
        this.goodratetogoods = goodratetogoods;
    }

    public String getEvaluatetogoods() {
        return evaluatetogoods;
    }

    public void setEvaluatetogoods(String evaluatetogoods) {
        this.evaluatetogoods = evaluatetogoods;
    }

    public String getGoodratetomerchant() {
        return goodratetomerchant;
    }

    public void setGoodratetomerchant(String goodratetomerchant) {
        this.goodratetomerchant = goodratetomerchant;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
