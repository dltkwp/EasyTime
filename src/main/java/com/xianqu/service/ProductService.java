package com.xianqu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xianqu.bean.*;
import com.xianqu.bean.product.ProductVo;
import com.xianqu.mapper.ProductDescriptionMapper;
import com.xianqu.mapper.ProductImageMapper;
import com.xianqu.mapper.ProductMapper;
import com.xianqu.mapper.ProductPriceMapper;
import org.apache.ibatis.reflection.ArrayUtil;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/1/29.
 */
@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDescriptionMapper productDescriptionMapper;

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductPriceMapper productPriceMapper;

    public Long insert(ProductVo productVo, Long userId) {
        Product hasProduct = productMapper.selectByName(productVo.getProductName(), userId);
        if (null != hasProduct) {
            throw new RuntimeException("商品名已存在");
        }
        Product productDto = new Product();
        Date now = new Date();
        productDto.setCreateUser(userId);
        productDto.setUpdateUser(userId);
        productDto.setCreateDate(now);
        productDto.setUpdateDate(now);
        productDto.setIsDelete(false);
        BeanUtils.copyProperties(productVo, productDto);
        Long productId = productMapper.insert(productDto);
        if (!StringUtils.isEmpty(productVo.getDescription())) {
            ProductDescription description = new ProductDescription();
            description.setProductId(productId);
            description.setCreateDate(now);
            description.setUpdateDate(now);
            description.setCreateUser(userId);
            description.setUpdateUser(userId);
            description.setDescription(productVo.getDescription());
            description.setIsDelete(false);
            productDescriptionMapper.insert(description);
        }

        List<String> images = productVo.getImages();
        int imagesSize = images.size();
        if(null != images && imagesSize > 0) {
            List<ProductImage> imageList = new ArrayList<ProductImage>();
            for(int i = 0; i < imagesSize; i++) {
                ProductImage productImage = new ProductImage();
                productImage.setCreateDate(now);
                productImage.setCreateUser(userId);
                productImage.setProductId(productId);
                productImage.setOrder(i);
                productImage.setImageCode(images.get(i));
                imageList.add(productImage);
            }
            productImageMapper.insertByList(imageList);
        }

        List<ProductPrice> prices = productVo.getPrices();
        if(null != prices && prices.size() > 0) {
            for(ProductPrice price: prices) {
                price.setCreateDate(now);
                price.setCreateUser(userId);
                price.setUpdateDate(now);
                price.setUpdateUser(userId);
                price.setProductId(productId);
                price.setIsDelete(false);
            }
            productPriceMapper.insertByList(prices);
        }

        return productId;
    }

    public PageInfo getListByUserId(Long userId, Integer pageNumber, Integer pageSize, String productName, Long categoriesId, Boolean status) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Product> list = productMapper.selectByUserId(userId, productName, categoriesId, status);
        PageInfo page = new PageInfo(list);
        return page;
    }

    public ProductVo selectByPrimaryKey(Long productId) {
        ProductVo productVo = new ProductVo();
        Product product = productMapper.selectByPrimaryKey(productId);
        BeanUtils.copyProperties(product, productVo);
        ProductDescription description = productDescriptionMapper.selectByProductId(productId);
        productVo.setDescription(description.getDescription());

        List<ProductImage> productImages = productImageMapper.selectByProductId(productId);
        List<String> images = productImages.stream().map(ProductImage::getImageCode).collect(Collectors.toList());
        productVo.setImages(images);

        List<ProductPrice> productPrices = productPriceMapper.selectByProductId(productId);
        productVo.setPrices(productPrices);

        return productVo;
    }

    public void delete(Long productId) {
        Product productDto = new Product();
        productDto.setId(productId);
        productDto.setIsDelete(true);
        productMapper.updateByPrimaryKeySelective(productDto);
    }

    public void update(ProductVo productVo, Long userId) {
        Product hasProduct = productMapper.selectByName(productVo.getProductName(), userId);
        if (null != hasProduct && productVo.getId().equals(hasProduct.getId())) {
            throw new RuntimeException("商品名已存在");
        }
        Product productDto = new Product();
        Date now = new Date();
        productDto.setUpdateUser(userId);
        productDto.setUpdateDate(now);
        BeanUtils.copyProperties(productVo, productDto);
        productMapper.updateByPrimaryKeySelective(productDto);

        if (!StringUtils.isEmpty(productVo.getDescription())) {
            ProductDescription description = new ProductDescription();
            description.setProductId(productDto.getId());
            description.setUpdateDate(now);
            description.setUpdateUser(userId);
            description.setDescription(null == productVo.getDescription() ? "" : productVo.getDescription());
            productDescriptionMapper.updateByPrimaryKeyWithBLOBs(description);
        }

        productImageMapper.deleteByProductId(productDto.getId());

        List<String> images = productVo.getImages();
        int imagesSize = images.size();
        if(null != images && imagesSize > 0) {
            List<ProductImage> imageList = new ArrayList<ProductImage>();
            for(int i = 0; i < imagesSize; i++) {
                ProductImage productImage = new ProductImage();
                productImage.setCreateDate(now);
                productImage.setCreateUser(userId);
                productImage.setProductId(productDto.getId());
                productImage.setOrder(i);
                productImage.setImageCode(images.get(i));
                imageList.add(productImage);
            }
            productImageMapper.insertByList(imageList);
        }

        productPriceMapper.deleteByProductId(productDto.getId());

        List<ProductPrice> prices = productVo.getPrices();
        if(null != prices && prices.size() > 0) {
            for(ProductPrice price: prices) {
                price.setCreateDate(now);
                price.setCreateUser(userId);
                price.setUpdateDate(now);
                price.setUpdateUser(userId);
                price.setProductId(productDto.getId());
                price.setIsDelete(false);
            }
            productPriceMapper.insertByList(prices);
        }
    }
}
