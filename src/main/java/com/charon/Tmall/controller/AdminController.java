package com.charon.Tmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charon.Tmall.common.Result;
import com.charon.Tmall.entity.Admin;
import com.charon.Tmall.service.IAdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    /**
     * 新增或者更新
     *
     * @param admin
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Admin admin) {
        adminService.saveOrUpdate(admin);
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
        adminService.removeById(id);
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
        adminService.removeByIds(ids);
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
        return Result.success(adminService.list());
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(adminService.getById(id));
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
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        return Result.success(adminService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
}

