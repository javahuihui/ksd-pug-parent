package com.ksd.pug.controller.menu;

import com.ksd.pug.controller.BaseController;
import com.pug.pojo.NavMenu;
import com.ksd.pug.service.menu.INavMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class NavMenuController extends BaseController {

    private final INavMenuService slideMenuService;

    /**
     * 查询分类的接口信息-tree
     *
     * @return
     */
    @GetMapping("/navmenu/tree")
    @ResponseBody
    public List<NavMenu> tree() {
        return slideMenuService.findNavMenuTree();
    }

}
