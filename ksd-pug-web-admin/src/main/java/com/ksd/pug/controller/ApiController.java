package com.ksd.pug.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/15$ 23:04$
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/{model}")
    public String apindex(@PathVariable("model") String model) {
        return "/" +model + "/api";
    }

}
