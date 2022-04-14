package com.ksd.pug.controller.login;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pug.commons.anno.PugLog;
import com.pug.commons.anno.PugRateLimiter;
import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.pug.commons.ex.PugBussinessException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.commons.utils.pwd.MD5Util;
import com.ksd.pug.config.interceptor.repeat.RepeatSubmit;
import com.ksd.pug.controller.BaseController;
import com.pug.pojo.SysLoginUser;
import com.pug.pojo.SysPermission;
import com.pug.pojo.SysRole;
import com.ksd.pug.service.jwt.JwtBlackStringService;
import com.ksd.pug.service.user.ISysLoginUserService;
import com.ksd.pug.utils.jwt.JwtTokenUtils;
import com.ksd.pug.vo.LoginUserVo;
import com.ksd.pug.vo.LoginVo;
import com.pug.redis.config.PugRedisCacheTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/1 23:35
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController extends BaseController {

    private final ISysLoginUserService sysLoginUserService;
    private final PugRedisCacheTemplate pugRedisCacheTemplate;
    private final JwtBlackStringService jwtBlackListService2;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 不需要密码
     *
     * @param loginVo
     * @return
     */
    @PostMapping("/auth/login")
    @PugLog(title = "登录生成token")
    @RepeatSubmit
    @PugRateLimiter(timeout = 1, limit = 3)
    public LoginUserVo loginbyname(@RequestBody LoginVo loginVo, HttpSession session) {
        Vsserts.isEmptyEx(loginVo.getUsername(), new PugBussinessException(ResultStatusEnumA.USER_PWD_STATUS_INPUT));
        Vsserts.isEmptyEx(loginVo.getCode(), new PugBussinessException(ResultStatusEnumA.USER_CODE_STATUS_INPUT));
        log.info("1----------------------->登录进来了，你输入的用户名{}，密码是：{}，验证码是：{}",loginVo.getUsername(),
                loginVo.getPassword(),loginVo.getCode());
        // 验证码的判断
        String cacheCode = stringRedisTemplate.opsForValue().get(loginVo.getUuid());
        log.info("2----------------------->从服务器redis中获取code：{}",cacheCode);
        Vsserts.isEmptyEx(cacheCode, new PugBussinessException(ResultStatusEnumA.USER_CODE_ERROR_CACHE));
        if (!cacheCode.equalsIgnoreCase(loginVo.getCode())) {
            throw new PugBussinessException(ResultStatusEnumA.USER_CODE_INPUT_ERROR);
        }

        // 1: 根据username查询用户信息
        LambdaQueryWrapper<SysLoginUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysLoginUser::getUsername, loginVo.getUsername());
        SysLoginUser user = sysLoginUserService.getOne(userLambdaQueryWrapper);
        // 如果查询用户存在
        if (user != null) {
            // 2: 创建token
            String token = JwtTokenUtils.createToken(user);
            // 3: 创建vo,按需加载封装返回即可
            LoginUserVo loginUserVo = new LoginUserVo();
            loginUserVo.setUserId(user.getId());
            loginUserVo.setAvatar(user.getAvatar());
            loginUserVo.setNickname(user.getNickname());
            loginUserVo.setUsername(user.getUsername());
            loginUserVo.setToken(token);
            // 4: 把对应用户的角色和权限查询出来
            List<SysRole> roleList = sysLoginUserService.findSysRoleByUserId(user.getId());
            List<SysPermission> permissionList = sysLoginUserService.findBySysPermissionUserId(user.getId());
            loginUserVo.setRoleList(roleList);
            loginUserVo.setPermissionList(permissionList);
            // 写入信息到redis缓存中
            String tokenKey = "sys:user:token:" + token;
            pugRedisCacheTemplate.setCacheObject(tokenKey, token, JwtTokenUtils.REDIS_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
            // 返回即可
            return loginUserVo;
        } else {
            // 代表输入账号密码有误
            throw new PugBussinessException(ResultStatusEnumA.USER_PWD_STATUS);
        }
    }


    /**
     * @param loginVo
     * @return
     */
    @PostMapping("/auth/login/pwd")
    @PugLog(title = "登录生成token")
    @PugRateLimiter(timeout = 1, limit = 3)
    public LoginUserVo loginbynamepwd(@RequestBody LoginVo loginVo) {
        log.info("1----------------------->登录进来了，你输入的用户名{}，密码是：{}，验证码是：{}",loginVo.getUsername(),
                loginVo.getPassword(),loginVo.getCode());

        Vsserts.isEmptyEx(loginVo.getUsername(), new PugBussinessException(ResultStatusEnumA.USER_USERNAME_STATUS));
        Vsserts.isEmptyEx(loginVo.getPassword(), new PugBussinessException(ResultStatusEnumA.USER_PWD_STATUS_INPUT));
        Vsserts.isEmptyEx(loginVo.getCode(), new PugBussinessException(ResultStatusEnumA.USER_CODE_STATUS_INPUT));
        // 验证码的判断
        String cacheCode = stringRedisTemplate.opsForValue().get(loginVo.getUuid());
        log.info("2----------------------->从服务器redis中获取code：{}",cacheCode);
        Vsserts.isEmptyEx(cacheCode, new PugBussinessException(ResultStatusEnumA.USER_CODE_ERROR_CACHE));
        if (!cacheCode.equalsIgnoreCase(loginVo.getCode())) {
            throw new PugBussinessException(ResultStatusEnumA.USER_CODE_INPUT_ERROR);
        }

        // 1: 根据username查询用户信息
        LambdaQueryWrapper<SysLoginUser> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(SysLoginUser::getUsername, loginVo.getUsername());
        SysLoginUser user = sysLoginUserService.getOne(userLambdaQueryWrapper);
        if (user != null) {
            // 把用户输入的密码进行md5假面
//            String inputpwd = MD5Util.md5slat(loginVo.getPassword());
            String inputpwd = loginVo.getPassword();
            // 然后把用户输入的加密的密码和数据库中user.getPassword()进行比较
            if (!inputpwd.equalsIgnoreCase(user.getPassword())) {
                throw new PugBussinessException(ResultStatusEnumA.USER_PWD_STATUS);
            }
            // 2：生成token
            String token = JwtTokenUtils.createToken(user);
            // 3: 创建vo,按需加载封装返回即可
            LoginUserVo loginUserVo = new LoginUserVo();
            loginUserVo.setUserId(user.getId());
            loginUserVo.setUsername(user.getUsername());
            loginUserVo.setAvatar(user.getAvatar());
            loginUserVo.setNickname(user.getNickname());
            loginUserVo.setToken(token);
            // 4: 把对应用户的角色和权限查询出来
            List<SysRole> roleList = sysLoginUserService.findSysRoleByUserId(user.getId());
            List<SysPermission> permissionList = sysLoginUserService.findBySysPermissionUserId(user.getId());
            loginUserVo.setRoleList(roleList);
            loginUserVo.setPermissionList(permissionList);
            // 写入信息到redis缓存中
            String tokenKey = "sys:user:token:" + token;
            pugRedisCacheTemplate.setCacheObject(tokenKey, token, JwtTokenUtils.REDIS_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
            return loginUserVo;
        } else {
            // 代表输入账号密码有误
            throw new PugBussinessException(ResultStatusEnumA.USER_PWD_STATUS);
        }
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @PostMapping("/auth/logout")
    @PugLog(title = "退出登录")
    public String logout(HttpServletRequest request) {
        // 获取请求toekn
        String token = JwtTokenUtils.getJwtToken(request);
        // 把当前token拉入黑名单中
        jwtBlackListService2.addBlackList(token);
        // 返回成功
        return "success";
    }
}
