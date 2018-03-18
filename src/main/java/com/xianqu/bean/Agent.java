package com.xianqu.bean;

public class Agent {
    private Long id;
    private String username;
    private String realname;
    private String phone;
    private String wechart;
    private String alipay;
    private String levelName;
    private Long sumOrder;
    private Long sumPay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechart() {
        return wechart;
    }

    public void setWechart(String wechart) {
        this.wechart = wechart;
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Long getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(Long sumOrder) {
        this.sumOrder = sumOrder;
    }

    public Long getSumPay() {
        return sumPay;
    }

    public void setSumPay(Long sumPay) {
        this.sumPay = sumPay;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", phone='" + phone + '\'' +
                ", wechart='" + wechart + '\'' +
                ", alipay='" + alipay + '\'' +
                ", levelName='" + levelName + '\'' +
                ", sumOrder=" + sumOrder +
                ", sumPay=" + sumPay +
                '}';
    }
}
