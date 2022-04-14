package com.ksd.pug.service.banner;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pug.mapper.BannerMapper;
import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.pug.commons.ex.PugValidationException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.pojo.Banner;
import com.ksd.pug.vo.BannerVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * BannerServiceImpl实现类
 * 创建人:yykk<br/>
 * 时间：2022-02-21 01:50:36 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
@Service
@Slf4j
public class BannerServiceImpl extends ServiceImpl<BannerMapper,Banner> implements IBannerService  {

    /**
     * 查询分页&搜索轮播图
     * @param bannerVo
     * @return IPage<Banner>
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
     */
    @Override
	public IPage<Banner> findBannerPage(BannerVo bannerVo){
	    // 设置分页信息
		Page<Banner> page = new Page<>(bannerVo.getPageNo(),bannerVo.getPageSize());
		// 设置查询条件
        LambdaQueryWrapper<Banner> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // lambdaQueryWrapper.select(Banner.class, column -> !column.getColumn().equals("description"));
        // 根据关键词搜索信息
        lambdaQueryWrapper.like(Vsserts.isNotEmpty(bannerVo.getKeyword()), Banner::getName,bannerVo.getKeyword());
         //查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(bannerVo.getStatus() != null ,Banner::getStatus,bannerVo.getStatus());
        // 根据分类查询
        lambdaQueryWrapper.eq(Vsserts.isNotNull(bannerVo.getCategoryId()), Banner::getCategoryid,bannerVo.getCategoryId());
        // 多列搜索
        // lambdaQueryWrapper.and(Vsserts.isNotEmpty(bannerVo.getKeyword()),wrapper -> wrapper
        //         .like(Banner::getName, bannerVo.getKeyword())
        //         .or()
        //         .like(Banner::getCategoryName, bannerVo.getKeyword())
        // );
        // 根据时间排降序
        lambdaQueryWrapper.orderByDesc(Banner::getCreateTime);
        // 查询分页返回
		IPage<Banner> results = this.page(page,lambdaQueryWrapper);
		return results;
	}

    /**
     * 查询轮播图列表信息
     * @method: findBannerList
     * @result : List<Banner>
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
     * @return
    */
    @Override
    public List<Banner> findBannerList() {
     	// 1：设置查询条件
        LambdaQueryWrapper<Banner> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(Banner::getStatus,1);
        lambdaQueryWrapper.eq(Banner::getIsdelete,0);
        // 3: 查询返回
        return this.list(lambdaQueryWrapper);
    }

	/**
     * 根据id查询轮播图明细信息
     * @param id
     * @return Banner
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
     */
    @Override
    public Banner getBannerById(Long id) {
        return this.getById(id);
    }


    /**
     * 保存&修改轮播图
     * @param banner
     * @return Banner
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
     */
    @Override
	public Banner saveupdateBanner(Banner banner){
		boolean flag = this.saveOrUpdate(banner);
		return flag ? banner : null;
	}


    /**
     * 根据id删除轮播图
     * @param id
     * @return int
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
     */
    @Override
    public int deleteBannerById(Long id) {
        boolean b = this.removeById(id);
        return b ? 1 : 0;
    }

    /**
     * 根据id删除
     * @param ids
     * @return boolean
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
     */
    @Override
    public boolean delBatchBanner(String ids) {
        try {
            // 1 : 把数据分割
            String[] strings = ids.split(",");
            // 2: 组装成一个List<Banner>
            List<Banner> bannerList = Arrays.stream(strings).map(idstr -> {
                Banner banner = new Banner();
                banner.setId(new Long(idstr));
                banner.setIsdelete(1);
                return banner;
            }).collect(Collectors.toList());
            // 3: 批量删除
            return this.updateBatchById(bannerList);
        } catch (Exception ex) {
            throw new PugValidationException(ResultStatusEnumA.SERVER_DB_ERROR);
        }
    }


}