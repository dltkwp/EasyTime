package com.xianqu.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "User", description = "用户对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    private static final long serialVersionUID = 9196447971812203657L;

    @ApiModelProperty(value = "ID", hidden=true)
    private Long id;

    @ApiModelProperty(value = "用户名", hidden=true)
    private String username;

    @ApiModelProperty(value = "密码", hidden=true)
    private String password;

    @ApiModelProperty(value = "名称")
    private String realname;

    @ApiModelProperty(value = "openId", hidden=true)
    private String openId;

    @ApiModelProperty(value = "微信")
    private String wechart;

    @ApiModelProperty(value = "支付宝")
    private String alipay;

    @ApiModelProperty(value = "备注")
    private String comment;

    @ApiModelProperty(value = "创建时间", hidden=true)
    @JsonIgnore
    private Date createDate;

    @ApiModelProperty(value = "更新时间", hidden=true)
    @JsonIgnore
    private Date updateDate;

    @ApiModelProperty(value = "是否删除", hidden=true)
    @JsonIgnore
    private Boolean isDelete;

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
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWechart() {
        return wechart;
    }

    public void setWechart(String wechart) {
        this.wechart = wechart == null ? null : wechart.trim();
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay == null ? null : alipay.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", openId='" + openId + '\'' +
                ", wechart='" + wechart + '\'' +
                ", alipay='" + alipay + '\'' +
                ", comment='" + comment + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isDelete=" + isDelete +
                '}';
    }
}