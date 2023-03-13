package com.charon.Tmall.controller;

import cn.hutool.core.util.StrUtil;
import com.charon.Tmall.common.Constants;
import com.charon.Tmall.common.Result;
import com.charon.Tmall.controller.dto.UserLoginDTO;
import com.charon.Tmall.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/15 12:53
 * @Version 1.0
 **/

@RestController
@RequestMapping("/front")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 前台用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserLoginDTO dto = loginService.login(userLoginDTO);
        return Result.success(dto);
    }

    /**
     * 前台用户注册接口
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(loginService.register(userLoginDTO));
    }

}
