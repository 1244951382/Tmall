package com.charon.Tmall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charon.Tmall.common.Constants;
import com.charon.Tmall.controller.dto.UserLoginDTO;
import com.charon.Tmall.entity.User;
import com.charon.Tmall.exception.ServiceException;
import com.charon.Tmall.mapper.UserMapper;
import com.charon.Tmall.service.LoginService;
import com.charon.Tmall.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/15 13:05
 * @Version 1.0
 **/

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    //用于打印错误日志
    private static final Log LOG = Log.get();

    /**
     * 前台用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public UserLoginDTO login(UserLoginDTO userLoginDTO) {
        User one = getUserInfo(userLoginDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userLoginDTO, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userLoginDTO.setToken(token);
            return userLoginDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误错误");
        }
    }


    /**
     * 判断是否存在
     *
     * @param userLoginDTO
     * @return
     */
    private User getUserInfo(UserLoginDTO userLoginDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userLoginDTO.getUsername());
        queryWrapper.eq("password", userLoginDTO.getPassword());
        User one;
        try {
            //判断当前用户名和密码能否在数据库中查询到
            one = getOne(queryWrapper);
        } catch (Exception e) {
            //在控制台打印错误日志
            LOG.error(e);
            throw new ServiceException(Constants.CODE_600, "系统错误");
        }
        return one;
    }

    /**
     * 用户注册
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public User register(UserLoginDTO userLoginDTO) {
        User one = getUserInfo(userLoginDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userLoginDTO, one, true);
            //把copy完的用户对象存储到数据库
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return null;
    }
}
