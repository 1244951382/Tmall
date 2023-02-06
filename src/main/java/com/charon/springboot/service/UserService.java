package com.charon.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charon.springboot.controller.dto.UserDTO;
import com.charon.springboot.entity.User;
import com.charon.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
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


//    @Autowired
//    private UserMapper userMapper;

//    public int save(User user) {
//        // 判断输入的数据是否指定id
//        if (user.getId() == null) {
//            //如果没有指定id，则执行insert方法
//            return userMapper.insert(user);
//        }else {
//            //如果指定id，则执行update方法
//            return userMapper.update(user);
//        }
//    }
}
