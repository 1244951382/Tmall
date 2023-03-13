package com.charon.Tmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-28
 */
@Getter
@Setter
@ApiModel(value = "Shopcart对象", description = "")
public class Shopcart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("购物车商品id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品图片地址")
    private String itemImgUrl;

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("商品价格")
    private BigDecimal itemPrice;

    @ApiModelProperty("所属用户id")
    private String ofUserId;

    @ApiModelProperty("商品数量")
    private Integer itemAccount;

    @ApiModelProperty("单个商品总价")
    private BigDecimal totalPrice;
}
