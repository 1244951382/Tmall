package com.charon.Tmall.service.impl;

import com.charon.Tmall.entity.Admin;
import com.charon.Tmall.mapper.AdminMapper;
import com.charon.Tmall.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-03-08
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
