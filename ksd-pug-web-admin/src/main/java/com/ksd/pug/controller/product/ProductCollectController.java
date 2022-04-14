package com.ksd.pug.controller.product;

import com.ksd.pug.controller.BaseController;
import com.ksd.pug.service.product.IProductCollectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/15$ 23:05$
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductCollectController extends BaseController {

    private final IProductCollectService productCollectService;
}
