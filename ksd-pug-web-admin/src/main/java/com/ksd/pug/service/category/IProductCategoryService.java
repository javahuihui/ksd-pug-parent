package com.ksd.pug.service.category;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pug.pojo.ProductCategory;

import java.util.List;

/**
 * @author 飞哥
 * @SysLoginUseritle: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 12:42
 */
public interface IProductCategoryService extends IService<ProductCategory> {

    /**
     * 查询分类
     *
     * @return
     */
    List<ProductCategory> findCategories();
}
