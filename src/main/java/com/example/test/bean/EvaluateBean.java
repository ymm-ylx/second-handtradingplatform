package com.example.test.bean;

public class EvaluateBean {
    private String evaluateid;
    private String buyrelationid;
    private String content;
    private String goodrate;
    private String username;
    private String merchantgoodrate;
    private String usergoodrate;

    public String getUsergoodrate() {
        return usergoodrate;
    }

    public void setUsergoodrate(String usergoodrate) {
        this.usergoodrate = usergoodrate;
    }

    public String getMerchantgoodrate() {
        return merchantgoodrate;
    }

    public void setMerchantgoodrate(String merchantgoodrate) {
        this.merchantgoodrate = merchantgoodrate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEvaluateid() {
        return evaluateid;
    }

    public void setEvaluateid(String evaluateid) {
        this.evaluateid = evaluateid;
    }

    public String getBuyrelationid() {
        return buyrelationid;
    }

    public void setBuyrelationid(String buyrelationid) {
        this.buyrelationid = buyrelationid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGoodrate() {
        return goodrate;
    }

    public void setGoodrate(String goodrate) {
        this.goodrate = goodrate;
    }
}
