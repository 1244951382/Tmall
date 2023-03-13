package com.charon.Tmall.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品参数
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-24
 */
@Getter
@Setter
@TableName("items_param")
@ApiModel(value = "ItemsParam对象", description = "商品参数 ")
public class ItemsParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品参数id")
    private String id;

    @ApiModelProperty("商品外键id")
    private String itemId;

    @ApiModelProperty("产地 产地，例：中国江苏")
    private String producPlace;

    @ApiModelProperty("保质期 保质期，例：180天")
    private String footPeriod;

    @ApiModelProperty("品牌名 品牌名，例：三只大灰狼")
    private String brand;

    @ApiModelProperty("生产厂名 生产厂名，例：大灰狼工厂")
    private String factoryName;

    @ApiModelProperty("生产厂址 生产厂址，例：大灰狼生产基地")
    private String factoryAddress;

    @ApiModelProperty("包装方式 包装方式，例：袋装")
    private String packagingMethod;

    @ApiModelProperty("规格重量 规格重量，例：35g")
    private String weight;

    @ApiModelProperty("存储方法 存储方法，例：常温5~25°")
    private String storageMethod;

    @ApiModelProperty("食用方式 食用方式，例：开袋即食")
    private String eatMethod;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;


}
