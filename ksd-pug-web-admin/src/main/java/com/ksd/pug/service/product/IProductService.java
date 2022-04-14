package com.ksd.pug.service.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pug.pojo.Product;
import com.ksd.pug.vo.ProductVo;

/**
 * @author 飞哥
 * @SysLoginUseritle: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 12:42
 */
public interface IProductService extends IService<Product> {

    /**
     * 查询产品列表信息并分页
     *
     * @return
     */
    IPage<Product> findProductPage(ProductVo productVo);



    /**
     * 保存和修改产品
     *
     * @param product
     */
    Product saveUpdateProduct(Product product);


    /**
     * 根据产品id删除产品
     *
     * @param id
     * @return
     */
    boolean delProduct(Long id);

    /**
     * 根据产品id删除产品
     *
     * @param ids
     * @return
     */
    boolean delBatchProduct(String ids);

    /**
     * 根据产品id查询产品
     *
     * @param id
     * @return
     */
    Product getProduct(Long id);


}
