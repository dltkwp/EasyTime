package com.xianqu.bean.user;

public class NormalVo {
    private String realname;
    private String recipientsPhone;
    private String recipientsAddress;
    private String comment;

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

    @Override
    public String toString() {
        return "NormalVo{" +
                "realname='" + realname + '\'' +
                ", recipientsPhone='" + recipientsPhone + '\'' +
                ", recipientsAddress='" + recipientsAddress + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
