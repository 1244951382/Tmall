package com.charon.Tmall.service.impl;

import com.charon.Tmall.entity.Items;
import com.charon.Tmall.mapper.ItemsMapper;
import com.charon.Tmall.service.IItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务实现类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-08
 */
@Service
public class ItemsServiceImpl extends ServiceImpl<ItemsMapper, Items> implements IItemsService {

    @Autowired
    ItemsMapper itemsMapper;

    @Override
    public List<Items> findRandItems() {
        return itemsMapper.findRandItems();
    }
}
