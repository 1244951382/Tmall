package com.charon.Tmall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-08
 */
@Data
@ApiModel(value = "Items对象", description = "商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表")
@AllArgsConstructor
@NoArgsConstructor
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品主键id")
    private String id;

    @ApiModelProperty("商品名称 商品名称")
    private String itemName;

    @ApiModelProperty("分类外键id 分类id")
    private Integer catId;

    @ApiModelProperty("一级分类外键id")
    private Integer rootCatId;

    @ApiModelProperty("累计销售 累计销售")
    private Integer sellCounts;

    @ApiModelProperty("上下架状态 上下架状态,1:上架 2:下架")
    private Integer onOffStatus;

    @ApiModelProperty("商品图片地址")
    private String itemImgUrl;

    @ApiModelProperty("商品价格")
    private String itemPrice;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;


}
