package com.charon.springboot.service.impl;

import com.charon.springboot.entity.Menu;
import com.charon.springboot.mapper.MenuMapper;
import com.charon.springboot.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
