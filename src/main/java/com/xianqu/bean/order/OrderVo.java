package com.xianqu.bean.order;

import com.xianqu.bean.OrderProduct;
import com.xianqu.bean.OrderSupplier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVo {
    private Long id;

    private Boolean isAgent;

    private Long productId;

    private String content;

    private String recipients;

    private String recipientsPhone;

    private String recipientsAddress;

    private BigDecimal payment;

    /**
     *  none-未支付 alipay-支付宝 wechart-微信
     */
    private String payType;

    private String payChannel;

    private String outTradeNo;
    /**
     *  REVIEW-待审核 WAIT-等待发货 DELIVERY-已发货 RECEIVED-已签收
     */
    private String status;

    private Long agentId;

    private Date createDate;

    private Long createUser;

    private Date updateDate;

    private Long updateUser;

    private String source;

    private String userType;

    private Long recipientsId;

    private Date reviewDate;

    private Date payDate;

    private List<OrderSupplier> orderSupplierList;

    private List<OrderProduct> orderProductList;

    public Long getId() {
        return id;
    }

    public Boolean getAgent() {
        return isAgent;
    }

    public void setAgent(Boolean agent) {
        isAgent = agent;
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

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
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

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getRecipientsId() {
        return recipientsId;
    }

    public void setRecipientsId(Long recipientsId) {
        this.recipientsId = recipientsId;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
