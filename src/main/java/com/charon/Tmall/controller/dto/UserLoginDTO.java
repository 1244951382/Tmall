package com.charon.Tmall.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserLoginDTO
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/15 9:51
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    private int id;
    private String username;
    private String password;
    private String token;
    private String nickname;
    private String avatar;
}
