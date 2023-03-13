package com.charon.Tmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-26
 */
@Getter
@Setter
@ApiModel(value = "Category对象", description = "商品分类 ")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("分类类型")
    private Integer type;

    @ApiModelProperty("父id")
    private Integer fatherId;

    @ApiModelProperty("图标")
    private String logo;

    @ApiModelProperty("口号")
    private String slogan;

    @ApiModelProperty("分类图")
    private String catImage;

    @ApiModelProperty("背景颜色")
    private String bgColor;


}
