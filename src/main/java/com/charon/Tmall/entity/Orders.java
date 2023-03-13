package com.charon.Tmall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表;
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-09
 */
@Getter
@Setter
@ApiModel(value = "Orders对象", description = "订单表;")
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单主键;同时也是订单编号")
    private String id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("订单图片地址")
    private String orderImgUrl;

    @ApiModelProperty("收货人快照")
    private String receiverName;

    @ApiModelProperty("收货人手机号快照")
    private String receiverMobile;

    @ApiModelProperty("收货地址快照")
    private String receiverAddress;

    @ApiModelProperty("订单总价格")
    private Integer totalAmount;

    @ApiModelProperty("创建时间（成交时间）")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;


}
