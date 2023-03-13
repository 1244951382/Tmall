package com.charon.Tmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charon.Tmall.common.Result;
import com.charon.Tmall.entity.Orders;
import com.charon.Tmall.service.IOrdersService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 订单表; 前端控制器
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-09
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private IOrdersService ordersService;

    /**
     * 新增或者更新
     *
     * @param orders
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Orders orders) {
        ordersService.saveOrUpdate(orders);
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
        ordersService.removeById(id);
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
        ordersService.removeByIds(ids);
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
        return Result.success(ordersService.list());
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(ordersService.getById(id));
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
                           @RequestParam String id) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("id", id);
        queryWrapper.orderByAsc("id");
        return Result.success(ordersService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}

