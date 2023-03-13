package com.charon.Tmall.controller.dto;

import com.charon.Tmall.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName UserDTO
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/2 15:17
 * @Version 1.0
 **/

/**
 * 接收前端登录请求的参数
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;
    private String role;
    private List<Menu> menus;
}
