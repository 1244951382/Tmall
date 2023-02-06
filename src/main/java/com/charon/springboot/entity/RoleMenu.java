package com.charon.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName RoleMenu
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/6 15:50
 * @Version 1.0
 **/

@Data
@TableName("role_menu")
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;
}
