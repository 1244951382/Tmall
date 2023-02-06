package com.charon.springboot.controller.dto;

import lombok.Data;

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
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;
}
