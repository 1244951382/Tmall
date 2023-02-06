package com.charon.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charon.springboot.common.Constants;
import com.charon.springboot.common.Result;
import com.charon.springboot.entity.Dict;
import com.charon.springboot.entity.Menu;
import com.charon.springboot.mapper.DictMapper;
import com.charon.springboot.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-06
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private DictMapper dictMapper;

    /**
     * 新增或者更新
     * @param menu
     * @return
     */
    @PostMapping
    public boolean save(@RequestBody Menu menu) {
        return menuService.saveOrUpdate(menu);
    }

    /**
     * 删除一条数据
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return menuService.removeById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return menuService.removeByIds(ids);
    }

    /**
     * 查询全部
     * @param name
     * @return
     */
    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        //查询所有数据
        List<Menu> list = menuService.list(queryWrapper);
        //找出pid为null的一级菜单
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        //找出一级菜单的子菜单
        for (Menu menu : parentNode) {
            //筛选所有数据中pid=父级id的数据就是二级菜单
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));

        }
        return Result.success(parentNode);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Menu findOne(@PathVariable Integer id) {
        return menuService.getById(id);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Page<Menu> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByAsc("id");
        return menuService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(null));
    }

}

