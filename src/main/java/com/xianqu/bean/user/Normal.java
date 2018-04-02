package com.xianqu.bean.user;

public class Normal {
    private Long id;
    private String username;
    private String realname;
    private String recipientsPhone;
    private String recipientsAddress;
    private String comment;
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

    public String getRecipientsPhone() {
        return recipientsPhone;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        this.recipientsPhone = recipientsPhone;
    }

    public String getRecipientsAddress() {
        return recipientsAddress;
    }

    public void setRecipientsAddress(String recipientsAddress) {
        this.recipientsAddress = recipientsAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        return "Normal{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", recipientsPhone='" + recipientsPhone + '\'' +
                ", recipientsAddress='" + recipientsAddress + '\'' +
                ", comment='" + comment + '\'' +
                ", sumOrder=" + sumOrder +
                ", sumPay=" + sumPay +
                '}';
    }
}
