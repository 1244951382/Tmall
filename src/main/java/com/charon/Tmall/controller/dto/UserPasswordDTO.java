package com.charon.Tmall.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserPasswordDTO
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/7 10:30
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPasswordDTO {
    private String username;
    private String password;
    private String newPassword;
}
