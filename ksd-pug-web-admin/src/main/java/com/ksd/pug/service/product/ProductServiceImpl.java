package com.ksd.pug.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pug.mapper.ProductMapper;
import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.pug.commons.ex.PugBussinessException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.pojo.Product;
import com.ksd.pug.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    /**
     * 查询产品列表信息并分页11
     *
     * @return
     */
    @Override
    public IPage<Product> findProductPage(ProductVo productVo) {
        // 1: 设置分页
        Page<Product> page = new Page<>(productVo.getPageNo(), productVo.getPageSize());
        // 2: 设置条件
        LambdaQueryWrapper<Product> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 3：查询过滤一些字段，为啥要这样做呢？因为在开放中很多时候，我们一些表存在大字段列，而这些返回的过程非常非常查询性能。
        lambdaQueryWrapper.select(Product.class, column -> !column.getColumn().equals("description"));
        // 4: 把未删除的查询出来，那些删除的数据可以做一个回收站去回收即可
        lambdaQueryWrapper.eq(Product::getIsdelete, productVo.getIsDelete());
        // 5:根据名字进行模糊匹配
        // 多列搜索
        lambdaQueryWrapper.and(Vsserts.isNotEmpty(productVo.getKeyword()),wrapper -> wrapper
                .like(Product::getName, productVo.getKeyword())
                .or()
                .like(Product::getCategoryName, productVo.getKeyword())
        );

        // 条件拼接分类
        lambdaQueryWrapper.eq(Vsserts.isNotNull(productVo.getCategoryId()),Product::getCategoryId,productVo.getCategoryId());
        lambdaQueryWrapper.eq(Vsserts.isNotNull(productVo.getStatus()),Product::getStatus,productVo.getStatus());

        // 6: 设置排序 根据产品排降序，代表，最新的放在最前
        lambdaQueryWrapper.orderByDesc(Product::getCreateTime);
        // 7：最后返回
        return this.page(page, lambdaQueryWrapper);
    }


    /**
     * 保存和修改产品
     *
     * @param product
     */
    @Override
    public Product saveUpdateProduct(Product product) {
        return this.saveOrUpdate(product) ? product : null;
    }


    /**
     * 根据产品id删除产品
     *
     * @param id
     * @return
     */
    @Override
    public boolean delProduct(Long id) {
        return this.removeById(id);
    }


    @Override
    public boolean delBatchProduct(String ids) {
        try {
            // 1 : 把数据分割
            String[] strings = ids.split(",");
            // 2: 组装成一个List<Product>
            List<Product> productList = Arrays.stream(strings).map(idstr -> {
                Product product = new Product();
                product.setId(new Long(idstr));
                product.setIsdelete(1);
                return product;
            }).collect(Collectors.toList());
            // 3: 批量删除
            return this.updateBatchById(productList);
        } catch (Exception ex) {
            throw new PugBussinessException(ResultStatusEnumA.ORDER_ERROR_STATUS);
        }
    }

    /**
     * 根据产品id查询产品
     *
     * @param id
     * @return
     */
    @Override
    public Product getProduct(Long id) {
        return this.getById(id);
    }
}
