package com.charon.Tmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charon.Tmall.common.Result;
import com.charon.Tmall.entity.ItemsParam;
import com.charon.Tmall.service.IItemsParamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品参数  前端控制器
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-24
 */
@RestController
@RequestMapping("/items-param")
public class ItemsParamController {

    @Resource
    private IItemsParamService itemsParamService;

    /**
     * 新增或者更新
     *
     * @param itemsParam
     * @return
     */
    @PostMapping
    public Result save(@RequestBody ItemsParam itemsParam) {
        itemsParamService.saveOrUpdate(itemsParam);
        return Result.success();
    }

    /**
     * 删除一条数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        itemsParamService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        itemsParamService.removeByIds(ids);
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
        return Result.success(itemsParamService.list());
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable String id) {
        return Result.success(itemsParamService.getById(id));
    }

    /**
     * 根据itemId查询
     *
     * @param itemId
     * @return
     */
    @GetMapping("/itemParams")
    public Result findParams(@RequestParam String itemId) {
        QueryWrapper<ItemsParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemId);
        return Result.success(itemsParamService.getOne(queryWrapper));
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
                           @RequestParam Integer pageSize) {
        QueryWrapper<ItemsParam> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        return Result.success(itemsParamService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}

