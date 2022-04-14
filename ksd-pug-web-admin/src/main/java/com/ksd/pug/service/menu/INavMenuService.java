package com.ksd.pug.service.menu;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pug.pojo.NavMenu;

import java.util.List;

/**
 * @author 飞哥
 * @SysLoginUseritle: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 12:42
 */
public interface INavMenuService extends IService<NavMenu> {


    /**
     * 返回分类tree
     *
     * @return
     */
    List<NavMenu> findNavMenuTree();
}
