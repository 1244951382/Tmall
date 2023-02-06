package com.charon.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charon.springboot.common.Constants;
import com.charon.springboot.controller.dto.UserDTO;
import com.charon.springboot.entity.User;
import com.charon.springboot.exception.ServiceException;
import com.charon.springboot.mapper.UserMapper;
import com.charon.springboot.service.IUserService;
import com.charon.springboot.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/2 17:06
 * @Version 1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    //用于打印错误日志
    private static final Log LOG = Log.get();

/**
   * 用户登录
   * @param userDTO
   * @return
*/
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误错误");
        }
    }

/**
*  判断当前用户是否存在
 * @param userDTO  待判断用户
 * @return   判断结果
*/
    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try{
            //判断当前用户名和密码能否在数据库中查询到
            one = getOne(queryWrapper);
        }catch (Exception e) {
            //在控制台打印错误日志
            LOG.error(e);
            throw new ServiceException(Constants.CODE_600, "系统错误");
        }
        return one;
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one); //把copy完的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return null;
    }
}
