package com.xianqu.bean.order;

import com.xianqu.bean.OrderSupplier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVo {
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

    private List<OrderSupplier> orderSupplierList;

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
        this.content = content;
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
        this.recipientsPhone = recipientsPhone;
    }

    public String getRecipientsAddress() {
        return recipientsAddress;
    }

    public void setRecipientsAddress(String recipientsAddress) {
        this.recipientsAddress = recipientsAddress;
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
        this.payType = payType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<OrderSupplier> getOrderSupplierList() {
        return orderSupplierList;
    }

    public void setOrderSupplierList(List<OrderSupplier> orderSupplierList) {
        this.orderSupplierList = orderSupplierList;
    }
}
