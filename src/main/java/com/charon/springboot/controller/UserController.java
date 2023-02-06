package com.charon.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charon.springboot.common.Constants;
import com.charon.springboot.common.Result;
import com.charon.springboot.controller.dto.UserDTO;
import com.charon.springboot.entity.User;
import com.charon.springboot.mapper.UserMapper;
import com.charon.springboot.service.IUserService;
import com.charon.springboot.service.UserService;
import com.charon.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController @Description TODO @Author Charon.Wang @Date 2023/1/9 21:12 @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

  //    @Autowired
  //    private UserMapper userMapper;

  @Autowired
  private UserService userService;

  @Autowired
  private IUserService iUserService;

  /** 查询所有信息 */
  @GetMapping("/findAll")
  public List<User> findAll() {
    return userService.list();
  }

  /** 插入用户信息或更新一条信息 @RequestBody将JSON对象转换为Java对象 */
  @PostMapping
  private boolean save(@RequestBody User user) {
    return userService.saveOrUpdate(user);
  }

  /**
   * 根据id删除数据
   *
   * @param id
   * @return
   */
  @DeleteMapping("/delete/{id}")
  private boolean delete(@PathVariable Integer id) {
    return userService.removeById(id);
  }

  /**
   * 批量删除数据
   *
   * @param ids
   * @return
   */
  @PostMapping("/del/batch")
  private boolean deleteBatch(@RequestBody List<Integer> ids) {
    return userService.removeByIds(ids);
  }

  /**
   * 分页查询接口 /user/page
   *
   * @param pageNum = (pageNum - 1)
   * @param pageSize = pageSize
   * @param username
   * @param email
   * @param address
   * @return
   */
  @GetMapping("/page")
  public Page<User> findPage(
      @RequestParam Integer pageNum,
      @RequestParam Integer pageSize,
      @RequestParam(defaultValue = "") String username,
      @RequestParam(defaultValue = "") String email,
      @RequestParam(defaultValue = "") String address) {
    //      IPage<User> page = new Page<>(pageNum, pageSize);
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if (!"".equals(username)) {
      queryWrapper.like("username", username);
    }
    if (!"".equals(email)) {
      queryWrapper.like("email", email);
    }
    if (!"".equals(address)) {
      queryWrapper.like("address", address);
    }
    //获取当前用户信息
    User currentUser = TokenUtils.getCurrentUser();
    System.out.println("当前登录的用户名称为：" + currentUser.getNickname());
    queryWrapper.orderByAsc("id");
    return userService.page(new Page<>(pageNum, pageSize), queryWrapper);
  }

  /**
   * 导出接口
   * */
  @GetMapping("/export")
  public void export(HttpServletResponse response) throws Exception {
    // 从数据库查询出所有的数据
    List<User> list = userService.list();
    // 在内存操作，写出到浏览器
    ExcelWriter writer = ExcelUtil.getWriter(true);
    // 自定义标题别名
    writer.addHeaderAlias("username", "用户名");
    writer.addHeaderAlias("password", "密码");
    writer.addHeaderAlias("nickname", "昵称");
    writer.addHeaderAlias("email", "邮箱");
    writer.addHeaderAlias("phone", "电话");
    writer.addHeaderAlias("address", "地址");
    writer.addHeaderAlias("createTime", "创建时间");
    writer.addHeaderAlias("avatarUrl", "头像");

    // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
    writer.write(list, true);

    // 设置浏览器响应的格式
    response.setContentType(
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
    String fileName = URLEncoder.encode("用户信息", "UTF-8");
    response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

    ServletOutputStream out = response.getOutputStream();
    writer.flush(out, true);
    out.close();
    writer.close();
  }

  /**
   *  导入接口
   * @param file
   * @throws Exception
   */
  @PostMapping("/import")
  public boolean imp(MultipartFile file) throws Exception {
    InputStream inputStream = file.getInputStream();
    ExcelReader reader = ExcelUtil.getReader(inputStream);
    List<List<Object>> list = reader.read(1);
    List<User> users = CollUtil.newArrayList();
    for (List<Object> row : list) {
      User user = new User();
      user.setUsername(row.get(0).toString());
      user.setNickname(row.get(1).toString());
      user.setPassword(row.get(2).toString());
      user.setEmail(row.get(3).toString());
      user.setPhone(row.get(4).toString());
      user.setAddress(row.get(5).toString());
      users.add(user);
    }
    userService.saveBatch(users);

    return true;
  }

  /**
   *  用户登录接口
   * @param userDTO
   * @return
   */
  @PostMapping("/login")
  public Result login(@RequestBody UserDTO userDTO) {
    String username = userDTO.getUsername();
    String password = userDTO.getPassword();
    if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
      return Result.error(Constants.CODE_400, "参数错误");
    }
    UserDTO dto = iUserService.login(userDTO);
    return Result.success(dto);
  }

/**
*  用户注册接口
 * @param userDTO
 * @return
*/
  @PostMapping("/register")
  public Result register(@RequestBody UserDTO userDTO) {
    String username = userDTO.getUsername();
    String password = userDTO.getPassword();
    if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
      return Result.error(Constants.CODE_400, "参数错误");
    }
    return Result.success(iUserService.register(userDTO));
  }

/**
* 用户个人信息接口
 * @param username
 * @return
*/
  @GetMapping("/username/{username}")
  public Result findOne(@PathVariable String username) {
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username", username);
    return Result.success(iUserService.getOne(queryWrapper));
  }
}
