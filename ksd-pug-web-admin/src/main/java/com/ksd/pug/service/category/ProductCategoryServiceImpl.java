package com.ksd.pug.service.category;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pug.mapper.ProductCategoryMapper;
import com.pug.commons.utils.collection.CollectionUtils;
import com.pug.pojo.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 飞哥
 * @SysLoginUseritle: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 12:42
 */
@Service
@Slf4j
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {

    @Override
    public List<ProductCategory> findCategories() {
        // 1: 查询所有分类
        LambdaQueryWrapper<ProductCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：设置查询排序
        lambdaQueryWrapper.orderByAsc(ProductCategory::getSorted);
        // 3：查询所有的分类，因为分类的数据是很少的，所以直接全部查询，进行组装
        List<ProductCategory> allCategories = this.list(lambdaQueryWrapper);
        // 2: 查询第一级分类
        List<ProductCategory> productCategories = allCategories.stream().
                filter(c -> c.getPid().equals(0L)).collect(Collectors.toList());
        // 3: 根据当前的id去找对应的子集
        productCategories.forEach(item -> {
            // 找到每个父元素的子集
            List<ProductCategory> childrenList = allCategories.stream()
                    .filter(c -> item.getId().equals(c.getPid())).collect(Collectors.toList());
            // 如果为空，代表没有匹配带子元素，就放一个空集合进去，目的：保证数据解构的完整性，已经方便后续前台的判断
            if (CollectionUtils.isEmpty(childrenList)) {
                childrenList = new ArrayList<>();
            }
            item.setChildren(childrenList);
        });

        return productCategories;
    }
}
