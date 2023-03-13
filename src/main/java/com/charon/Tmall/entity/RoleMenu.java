package com.charon.Tmall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RoleMenu
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/6 15:50
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("role_menu")
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;
}
