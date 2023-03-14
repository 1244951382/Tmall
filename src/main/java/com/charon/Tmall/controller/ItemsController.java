package com.charon.Tmall.controller;


import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charon.Tmall.common.Result;
import com.charon.Tmall.entity.Items;
import com.charon.Tmall.service.IItemsService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 前端控制器
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/items")
public class ItemsController {

    @Resource
    private IItemsService itemsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static final String ITEMS_KEY = "ITEMS_PAGE";

    /**
     * 新增或者更新
     *
     * @param items
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Items items) {
        itemsService.saveOrUpdate(items);
        return Result.success();
    }

    /**
     * 删除一条数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {
        itemsService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<String> ids) {
        itemsService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部
     *
     * @param
     * @return
     */
    @GetMapping
    public Result findAll() {
        String jsonStr = stringRedisTemplate.opsForValue().get(ITEMS_KEY);
        List<Items> items;
        if (StrUtil.isBlank(jsonStr)) {
            items = itemsService.list();
            stringRedisTemplate.opsForValue().set(ITEMS_KEY, JSONUtil.toJsonStr(items));
        }
        else {
            items = JSONUtil.toBean(jsonStr, new TypeReference<List<Items>>() {
            }, true);
        }
        return Result.success(items);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable String id) {
        return Result.success(itemsService.getById(id));
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String itemName) {
        QueryWrapper<Items> queryWrapper = new QueryWrapper<>();
        if (!"".equals(itemName)) {
            queryWrapper.like("item_name", itemName);
        }
        queryWrapper.orderByAsc("id");
        return Result.success(itemsService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 随机查询
     *
     * @return
     */
    @GetMapping("/rand")
    public Result findRandItems() {
        return Result.success(itemsService.findRandItems());
    }


}

