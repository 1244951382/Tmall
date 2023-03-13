package com.charon.Tmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charon.Tmall.common.Result;
import com.charon.Tmall.entity.Shopcart;
import com.charon.Tmall.service.IShopcartService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-28
 */
@RestController
@RequestMapping("/shopcart")
public class ShopcartController {

    @Resource
    private IShopcartService shopcartService;

    /**
     * 新增或者更新
     *
     * @param shopcart
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Shopcart shopcart) {
        shopcartService.saveOrUpdate(shopcart);
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
        shopcartService.removeById(id);
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
        shopcartService.removeByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部
     *
     * @param
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll(@RequestParam Integer id) {
        QueryWrapper<Shopcart> queryWrapper = new QueryWrapper<>();
        if (!"".equals(id)) {
            queryWrapper.eq("of_user_id", id);
        }
        queryWrapper.orderByAsc("id");
        return Result.success(shopcartService.list(queryWrapper));
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(shopcartService.getById(id));
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
                           @RequestParam(defaultValue = "") String username) {
        QueryWrapper<Shopcart> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)) {
            queryWrapper.like("item_name", username);
        }
        queryWrapper.orderByAsc("id");
        return Result.success(shopcartService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}

