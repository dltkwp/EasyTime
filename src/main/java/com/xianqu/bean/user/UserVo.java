package com.xianqu.bean.user;

import io.swagger.annotations.ApiModelProperty;

public class UserVo {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "名称")
    private String realname;

    @ApiModelProperty(value = "微信")
    private String wechart;

    @ApiModelProperty(value = "支付宝")
    private String alipay;

    @ApiModelProperty(value = "分销等级")
    private Long levelId;

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

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", wechart='" + wechart + '\'' +
                ", alipay='" + alipay + '\'' +
                ", levelId='" + levelId + '\'' +
                '}';
    }
}
