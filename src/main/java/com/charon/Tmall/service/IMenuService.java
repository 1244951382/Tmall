package com.charon.Tmall.service;

import com.charon.Tmall.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-06
 */
public interface IMenuService extends IService<Menu> {

    /**
     * findMenus
     *
     * @param name
     * @return
     */
    List<Menu> findMenus(String name);
}
