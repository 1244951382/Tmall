package com.charon.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.charon.springboot.entity.User;
import com.charon.springboot.service.IUserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName TokenUtils
 * @Description TODO
 * @Author Charon.Wang
 * @Date 2023/2/4 16:42
 * @Version 1.0
 **/

@Component
public class TokenUtils {

    private static IUserService staticiUserService;

    @Resource
    private IUserService iUserService;

    @PostConstruct
    public void setUserService() {
        staticiUserService = iUserService;
    }

/**
*  生成Token
 * @param userId
 * @param sign
 * @return
*/
    public static String genToken(String userId, String sign) {
        //将userId保存到Token里面，作为载荷
        //2小时后token过期
        //以password作为token的密钥
        return JWT.create()
                .withAudience(userId)
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                .sign(Algorithm.HMAC256(sign));
    }

/**
*  获取当前用户
 * @return
*/
    public static User getCurrentUser() {
        try{
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticiUserService.getById(Integer.valueOf(userId));
            }
        }catch (Exception e) {
            return null;
        }
        return null;
    }
}
