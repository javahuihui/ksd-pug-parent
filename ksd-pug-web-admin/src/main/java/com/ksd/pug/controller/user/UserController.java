package com.ksd.pug.controller.user;

import com.ksd.pug.controller.BaseController;
import com.pug.pojo.SysLoginUser;
import com.ksd.pug.service.user.ISysLoginUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class UserController extends BaseController {

    private final ISysLoginUserService userService;

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/user/get/{id}")
    public SysLoginUser getUser(@PathVariable("id")  Long id) {
        log.info("用户id是:{}", id);
        return userService.getById(id);
    }


    /**
     * 根据用户id查询用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/user/saveorupdate")
    public SysLoginUser saveUpdateUser(@RequestBody @Validated SysLoginUser user) {
        return userService.saveOrUpdate(user) ? user : null;
    }

}
