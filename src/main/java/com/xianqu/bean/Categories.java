package com.xianqu.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Categories", description = "分类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categories {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "分类名")
    @NotNull
    private String categoriesName;

    @ApiModelProperty(value = "userId", hidden=true)
    @JsonIgnore
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName == null ? null : categoriesName.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}