package com.charon.Tmall.service.impl;

import com.charon.Tmall.entity.Category;
import com.charon.Tmall.mapper.CategoryMapper;
import com.charon.Tmall.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类  服务实现类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-26
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
