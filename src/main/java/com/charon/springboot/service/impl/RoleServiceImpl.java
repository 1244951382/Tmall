package com.charon.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.charon.springboot.entity.Role;
import com.charon.springboot.entity.RoleMenu;
import com.charon.springboot.mapper.RoleMapper;
import com.charon.springboot.mapper.RoleMenuMapper;
import com.charon.springboot.service.IMenuService;
import com.charon.springboot.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-06
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        // 先删除当前角色id所有的绑定关系
        roleMenuMapper.deleteByRoleId(roleId);
        // 再把前端传过来的菜单id数组绑定到当前的这个角色id上去
        for (Integer menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }

}