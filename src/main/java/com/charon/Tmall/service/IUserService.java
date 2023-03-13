package com.charon.Tmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.charon.Tmall.controller.dto.UserDTO;
import com.charon.Tmall.controller.dto.UserPasswordDTO;
import com.charon.Tmall.entity.User;

/**
 * @ClassName IUserService
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/2 17:03
 * @Version 1.0
 **/

public interface IUserService extends IService<User> {
    /**
     * 登录
     *
     * @param userDTO
     * @return
     */
    UserDTO login(UserDTO userDTO);

    /**
     * 注册
     *
     * @param userDTO
     * @return
     */
    User register(UserDTO userDTO);

    /**
     * 修改密码
     *
     * @param userPasswordDTO
     */
    void updatePassword(UserPasswordDTO userPasswordDTO);
}
