package com.xianqu.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private Long productId;

    private String content;

    private Long recipients;

    private String recipientsPhone;

    private String recipientsAddress;

    private BigDecimal payment;

    private String payType;

    private String status;

    private Date createDate;

    private Long createUser;

    private Date updateDate;

    private Long updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getRecipients() {
        return recipients;
    }

    public void setRecipients(Long recipients) {
        this.recipients = recipients;
    }

    public String getRecipientsPhone() {
        return recipientsPhone;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        this.recipientsPhone = recipientsPhone == null ? null : recipientsPhone.trim();
    }

    public String getRecipientsAddress() {
        return recipientsAddress;
    }

    public void setRecipientsAddress(String recipientsAddress) {
        this.recipientsAddress = recipientsAddress == null ? null : recipientsAddress.trim();
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}