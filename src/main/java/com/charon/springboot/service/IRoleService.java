package com.charon.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.charon.springboot.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-06
 */
public interface IRoleService extends IService<Role> {

    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
