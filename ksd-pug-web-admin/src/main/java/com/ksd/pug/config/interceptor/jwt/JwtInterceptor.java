package com.ksd.pug.config.interceptor.jwt;

import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.ksd.pug.service.jwt.IJwtBlackService;
import com.ksd.pug.service.user.ISysLoginUserService;
import com.ksd.pug.utils.jwt.JwtTokenUtils;
import com.pug.commons.ex.PugBussinessException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.pojo.SysLoginUser;
import com.pug.redis.config.PugRedisCacheTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/6 17:48
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private ISysLoginUserService sysLoginUserService;
    @Autowired
    private PugRedisCacheTemplate pugRedisCacheTemplate;
    @Autowired
    @Qualifier("jwtBlackStringService")
    IJwtBlackService jwtBlackService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1: 判断当前请求头中是否有token
        String token = JwtTokenUtils.getJwtToken(request);
        if (Vsserts.isEmpty(token)) {
            throw new PugBussinessException(ResultStatusEnumA.TOKEN_EMPTY);
        }

        // 判断当前token是不是黑名单里
        if (jwtBlackService.isBlackList(token)) {
            throw new PugBussinessException(ResultStatusEnumA.TOKEN_EMPTY_EXPIRED);
        }

        // 2: 校验token 是否过期 isverfiy = false 代表过期
        boolean isverfiy = JwtTokenUtils.isverfiy(token);
        String tokenKey = "sys:user:token:" + token;
        if (!isverfiy) {
            // 3：如果你过期了就从缓存中去获取token
            token = pugRedisCacheTemplate.getCacheObject(tokenKey);
        }

        // 建议使用这种方式,这种具有实时性  5==== status= 1
        String userId = JwtTokenUtils.parseToken(token);
        if (Vsserts.isNull(userId) || Vsserts.isNull(pugRedisCacheTemplate.getCacheObject(tokenKey))) {
            throw new PugBussinessException(ResultStatusEnumA.TOKEN_UN_VALID);
        }
        SysLoginUser user = sysLoginUserService.getById(Long.parseLong(userId));

        // 判断用户是否激活
        if (user.getStatus().equals(0)) {
            throw new PugBussinessException(ResultStatusEnumA.TOKEN_UN_VALID);
        }

        //2.判断是否需要续期 30分钟就续期
        if (pugRedisCacheTemplate.getExpireTime(tokenKey) < 60 * 40) {
            String newToken = JwtTokenUtils.createToken(user);
            pugRedisCacheTemplate.setCacheObject(tokenKey, newToken, JwtTokenUtils.REDIS_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
