package com.charon.Tmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.charon.Tmall.controller.dto.UserLoginDTO;
import com.charon.Tmall.entity.User;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/15 13:05
 * @Version 1.0
 **/

public interface LoginService extends IService<User> {

    /**
     * 前台用户登录
     *
     * @param userLoginDTO
     * @return
     */
    UserLoginDTO login(UserLoginDTO userLoginDTO);

    /**
     * 前台用户注册
     *
     * @param userLoginDTO
     * @return
     */
    User register(UserLoginDTO userLoginDTO);
}
