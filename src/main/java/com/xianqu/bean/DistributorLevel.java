package com.xianqu.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "DistributorLevel", description = "分销等级")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistributorLevel {
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "分销等级")
    @NotNull
    private String levelName;

    @ApiModelProperty(value = "折扣")
    @NotNull
    private Long discount;

    @ApiModelProperty(value = "加盟费")
    private BigDecimal initialFee;

    @ApiModelProperty(value = "userId", hidden=true)
    @JsonIgnore
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public BigDecimal getInitialFee() {
        return initialFee;
    }

    public void setInitialFee(BigDecimal initialFee) {
        this.initialFee = initialFee;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}