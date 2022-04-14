package com.ksd.pug.controller.category;

import com.ksd.pug.controller.BaseController;
import com.pug.pojo.ProductCategory;
import com.ksd.pug.service.category.IProductCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
public class CategoryController extends BaseController {

    private final IProductCategoryService productCategoryService;


    /**
     * 查询产品分类
     *
     * @return
     */
    @GetMapping("/product/categories")
    public List<ProductCategory> findProductCategories() {
        return productCategoryService.findCategories();
    }

    /**
     * 保存和修改分类
     *
     * @return
     */
    @PostMapping("/product/savecategory")
    public ProductCategory saveCategory(@RequestBody ProductCategory productCategory) {
        boolean flag = productCategoryService.saveOrUpdate(productCategory);
        return flag ? productCategory : null;
    }

    /**
     * 根据id删除分类
     *
     * @return
     */
    @PostMapping("/product/category/del/{id}")
    public boolean saveCategory(@PathVariable("id") Long id) {
        ProductCategory category = new ProductCategory();
        category.setId(id);
        category.setIsdelete(1);
        return productCategoryService.updateById(category);
    }

    /**
     * 根据id查询分类明细
     *
     * @return
     */
    @GetMapping("/product/category/get/{id}")
    public ProductCategory getCategory(@PathVariable("id") Long id) {
        return productCategoryService.getById(id);
    }
}
