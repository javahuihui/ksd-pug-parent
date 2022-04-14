package com.ksd.pug.controller.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.pug.commons.ex.PugBussinessException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.ksd.pug.controller.BaseController;
import com.pug.pojo.Product;
import com.ksd.pug.service.product.IProductService;
import com.ksd.pug.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
public class ProductController extends BaseController {

    private final IProductService productService;


    /**
     * 查询产品列表信息并分页
     *
     * @return
     */
    @PostMapping("/product/list")
    public IPage<Product> findProductPage(@RequestBody ProductVo productVo) {
        return productService.findProductPage(productVo);
    }

    /**
     * 保存和修改产品
     *
     * @param product
     */
    @PostMapping("/product/saveupdate")
    public Product saveUpdateProduct(@RequestBody Product product) {
        return productService.saveUpdateProduct(product);
    }


    /**
     * 根据产品id删除产品
     *
     * @param id
     * @return
     */
    @PostMapping("/product/del/{id}")
    public boolean delProduct(@PathVariable("id") Long id) {
        return productService.delProduct(id);
    }


    /**
     * 根据产品ids批量删除产品
     *
     * @param productVo
     * @return
     */
    @PostMapping("/product/delBatch")
    public boolean delProduct(@RequestBody ProductVo productVo) {
        log.info("你要批量删除的IDS是:{}", productVo.getBatchIds());
        if (Vsserts.isEmpty(productVo.getBatchIds())) {
            throw new PugBussinessException(ResultStatusEnumA.EMPTY_IDS_VALID);
        }
        return productService.delBatchProduct(productVo.getBatchIds());
    }

    /**
     * 根据产品id查询产品
     *
     * @param id
     * @return
     */
    @GetMapping("/product/get/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }


}
