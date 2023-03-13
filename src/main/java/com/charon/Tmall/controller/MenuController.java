package com.charon.Tmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charon.Tmall.common.Constants;
import com.charon.Tmall.common.Result;
import com.charon.Tmall.entity.Dict;
import com.charon.Tmall.entity.Menu;
import com.charon.Tmall.mapper.DictMapper;
import com.charon.Tmall.service.IMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    private IMenuService iMenuService;

    @Resource
    private DictMapper dictMapper;

    /**
     * 新增或者更新
     * @param menu
     * @return
     */
    @PostMapping
    public boolean save(@RequestBody Menu menu) {
        return iMenuService.saveOrUpdate(menu);
    }

    /**
     * 删除一条数据
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return iMenuService.removeById(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return iMenuService.removeByIds(ids);
    }

    /**
     * findAllIds
     *
     * @return
     */
    @GetMapping("/ids")
    public Result findAllIds() {
        return Result.success(iMenuService.list().stream().map(Menu::getId));
    }

    /**
     * 查询全部
     *
     * @param name
     * @return
     */
    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        return Result.success(iMenuService.findMenus(name));
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Menu findOne(@PathVariable Integer id) {
        return iMenuService.getById(id);
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
        return iMenuService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(null));
    }

}

