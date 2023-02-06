package com.charon.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 用户实体类
 * @ClassName User
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/1/9 20:34
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
@ToString
//@ApiModel(value = "User对象", description = "")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;
    /**
     * 忽略该字段，不展示给前端
     */
    @JsonIgnore
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("角色")
    private String role;
}
