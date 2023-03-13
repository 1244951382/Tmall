package com.charon.Tmall.mapper;

import com.charon.Tmall.entity.Items;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 Mapper 接口
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-08
 */

@Mapper
public interface ItemsMapper extends BaseMapper<Items> {

    /**
     * 随机查询n条数据
     *
     * @return
     */
    @Select("select * from items  order by rand() limit 5")
    List<Items> findRandItems();
}
