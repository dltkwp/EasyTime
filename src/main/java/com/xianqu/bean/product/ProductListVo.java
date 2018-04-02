package com.xianqu.bean.product;

import com.xianqu.bean.ProductImage;
import com.xianqu.bean.ProductPrice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductListVo {
    private Long id;

    private Long productNo;

    private String productName;

    private Long categoriesId;

    private BigDecimal cost;

    private BigDecimal recommendedRetailPrice;

    private BigDecimal minRetailPrice;

    private Long stock;

    private Boolean status;

    private Date createDate;

    private Long createUser;

    private Date updateDate;

    private Long updateUser;

    private Boolean isDelete;

    private String description;

    private Boolean isOwner;

    private List<ProductImage> images;

    private List<ProductPrice> prices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductNo() {
        return productNo;
    }

    public void setProductNo(Long productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getRecommendedRetailPrice() {
        return recommendedRetailPrice;
    }

    public void setRecommendedRetailPrice(BigDecimal recommendedRetailPrice) {
        this.recommendedRetailPrice = recommendedRetailPrice;
    }

    public BigDecimal getMinRetailPrice() {
        return minRetailPrice;
    }

    public void setMinRetailPrice(BigDecimal minRetailPrice) {
        this.minRetailPrice = minRetailPrice;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public List<ProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPrice> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "ProductListVo{" +
                "id=" + id +
                ", productNo=" + productNo +
                ", productName='" + productName + '\'' +
                ", categoriesId=" + categoriesId +
                ", cost=" + cost +
                ", recommendedRetailPrice=" + recommendedRetailPrice +
                ", minRetailPrice=" + minRetailPrice +
                ", stock=" + stock +
                ", status=" + status +
                ", createDate=" + createDate +
                ", createUser=" + createUser +
                ", updateDate=" + updateDate +
                ", updateUser=" + updateUser +
                ", isDelete=" + isDelete +
                ", description='" + description + '\'' +
                ", isOwner=" + isOwner +
                ", images=" + images +
                ", prices=" + prices +
                '}';
    }
}
