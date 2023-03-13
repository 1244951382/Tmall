package com.charon.Tmall.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charon.Tmall.entity.User;
import com.charon.Tmall.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/1/11 16:17
 * @Version 1.0
 **/

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

//    @Resource
//    private UserMapper userMapper;
//
//    public UserBean loginIn(String username, String password) {
//        return userMapper.getInfo(username, password);
//    }

}
