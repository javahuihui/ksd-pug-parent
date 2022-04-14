package com.ksd.pug.service.bannercategory;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pug.mapper.BannerCategoryMapper;
import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.pug.commons.ex.PugValidationException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.pojo.BannerCategory;
import com.ksd.pug.vo.BannerCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * BannerCategoryServiceImpl实现类
 * 创建人:yykk<br/>
 * 时间：2022-02-20 20:17:40 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
@Service
@Slf4j
public class BannerCategoryServiceImpl extends ServiceImpl<BannerCategoryMapper,BannerCategory> implements IBannerCategoryService  {

    /**
     * 查询分页&搜索轮播图分类
     * @param bannercategoryVo
     * @return IPage<BannerCategory>
     * 创建人:yykk
     * 创建时间：2022-02-20 20:17:40
     * @version 1.0.0
     */
    @Override
	public IPage<BannerCategory> findBannerCategoryPage(BannerCategoryVo bannercategoryVo){
	    // 设置分页信息
		Page<BannerCategory> page = new Page<>(bannercategoryVo.getPageNo(),bannercategoryVo.getPageSize());
		// 设置查询条件
        LambdaQueryWrapper<BannerCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // lambdaQueryWrapper.select(BannerCategory.class, column -> !column.getColumn().equals("description"));
        // 根据关键词搜索信息
        lambdaQueryWrapper.like(Vsserts.isNotEmpty(bannercategoryVo.getKeyword()), BannerCategory::getName,bannercategoryVo.getKeyword());
         //查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(bannercategoryVo.getStatus() != null ,BannerCategory::getStatus,bannercategoryVo.getStatus());
        // 多列搜索
        // lambdaQueryWrapper.and(Vsserts.isNotEmpty(bannercategoryVo.getKeyword()),wrapper -> wrapper
        //         .like(BannerCategory::getName, bannercategoryVo.getKeyword())
        //         .or()
        //         .like(BannerCategory::getCategoryName, bannercategoryVo.getKeyword())
        // );
        // 根据时间排降序
        lambdaQueryWrapper.orderByDesc(BannerCategory::getCreateTime);
        // 查询分页返回
		IPage<BannerCategory> results = this.page(page,lambdaQueryWrapper);
		return results;
	}

    /**
     * 查询轮播图分类列表信息
     * @method: findBannerCategoryList
     * @result : List<BannerCategory>
     * 创建人:yykk
     * 创建时间：2022-02-20 20:17:40
     * @version 1.0.0
     * @return
    */
    @Override
    public List<BannerCategory> findBannerCategoryList() {
     	// 1：设置查询条件
        LambdaQueryWrapper<BannerCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(BannerCategory::getStatus,1);
        lambdaQueryWrapper.eq(BannerCategory::getIsdelete,0);
        // 3: 查询返回
        return this.list(lambdaQueryWrapper);
    }

	/**
     * 根据id查询轮播图分类明细信息
     * @param id
     * @return BannerCategory
     * 创建人:yykk
     * 创建时间：2022-02-20 20:17:40
     * @version 1.0.0
     */
    @Override
    public BannerCategory getBannerCategoryById(Long id) {
        return this.getById(id);
    }


    /**
     * 保存&修改轮播图分类
     * @param bannercategory
     * @return BannerCategory
     * 创建人:yykk
     * 创建时间：2022-02-20 20:17:40
     * @version 1.0.0
     */
    @Override
	public BannerCategory saveupdateBannerCategory(BannerCategory bannercategory){
		boolean flag = this.saveOrUpdate(bannercategory);
		return flag ? bannercategory : null;
	}


    /**
     * 根据id删除轮播图分类
     * @param id
     * @return int
     * 创建人:yykk
     * 创建时间：2022-02-20 20:17:40
     * @version 1.0.0
     */
    @Override
    public int deleteBannerCategoryById(Long id) {
        boolean b = this.removeById(id);
        return b ? 1 : 0;
    }

    /**
     * 根据id删除
     * @param ids
     * @return boolean
     * 创建人:yykk
     * 创建时间：2022-02-20 20:17:40
     * @version 1.0.0
     */
    @Override
    public boolean delBatchBannerCategory(String ids) {
        try {
            // 1 : 把数据分割
            String[] strings = ids.split(",");
            // 2: 组装成一个List<BannerCategory>
            List<BannerCategory> bannercategoryList = Arrays.stream(strings).map(idstr -> {
                BannerCategory bannercategory = new BannerCategory();
                bannercategory.setId(new Long(idstr));
                bannercategory.setIsdelete(1);
                return bannercategory;
            }).collect(Collectors.toList());
            // 3: 批量删除
            return this.updateBatchById(bannercategoryList);
        } catch (Exception ex) {
            throw new PugValidationException(ResultStatusEnumA.SERVER_DB_ERROR);
        }
    }


}