package com.charon.Tmall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charon.Tmall.common.Constants;
import com.charon.Tmall.controller.dto.UserDTO;
import com.charon.Tmall.controller.dto.UserPasswordDTO;
import com.charon.Tmall.entity.Menu;
import com.charon.Tmall.entity.User;
import com.charon.Tmall.exception.ServiceException;
import com.charon.Tmall.mapper.RoleMapper;
import com.charon.Tmall.mapper.RoleMenuMapper;
import com.charon.Tmall.mapper.UserMapper;
import com.charon.Tmall.service.IMenuService;
import com.charon.Tmall.service.IUserService;
import com.charon.Tmall.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService iMenuService;

    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录
     *
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
            String role = one.getRole();
            //设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误错误");
        }
    }

    /**
     * 判断当前用户是否存在
     *
     * @param userDTO 待判断用户
     * @return 判断结果
     */
    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
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
     * @param userDTO
     * @return
     */
    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            //把copy完的用户对象存储到数据库
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return null;
    }

    /**
     * 获取当前角色的菜单列表
     *
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        //当前角色所有菜单id的集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        //查出系统所有的菜单
        List<Menu> menus = iMenuService.findMenus("");
        //new一个最后筛选完成的list
        List<Menu> roleMenus = new ArrayList<>();
        //筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            //removeIf 移除 children 里面不在menuId集合中的元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

    /**
     * 修改密码
     *
     * @param userPasswordDTO
     */
    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "密码错误");
        }
    }
}
