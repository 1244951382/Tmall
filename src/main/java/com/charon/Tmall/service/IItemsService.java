package com.charon.Tmall.service;

import com.charon.Tmall.entity.Items;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 服务类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-08
 */
public interface IItemsService extends IService<Items> {
    /**
     * 随机查询
     *
     * @return
     */
    List<Items> findRandItems();
}
