package com.ksd.pug.controller.banner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ksd.pug.service.banner.IBannerService;
import com.ksd.pug.vo.BannerVo;
import com.pug.pojo.Banner;
import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.pug.commons.ex.PugValidationException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.ksd.pug.controller.BaseController;
import java.util.List;

/**
 * BannerController
 * 创建人:yykk<br/>
 * 时间：2022-02-21 01:50:36 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/

@RestController
@RequiredArgsConstructor
@Slf4j
public class BannerController extends BaseController{

    private final IBannerService bannerService;


    /**
     * 查询轮播图列表信息
     * @path : /admin/banner/load
     * @method: findBanners
     * @result : List<Banner>
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
     * @return
     */
    @GetMapping("/banner/load")
    public List<Banner> findBannerList() {
        return bannerService.findBannerList();
    }

	/**
	 * 查询轮播图列表信息并分页
	 * @path : /admin/banner/list
     * @method: findBanners
     * @param : bannerVo
     * @result : IPage<Banner>
	 * 创建人:yykk
	 * 创建时间：2022-02-21 01:50:36
	 * @version 1.0.0
	*/
    @PostMapping("/banner/list")
    public IPage<Banner> findBanners(@RequestBody BannerVo bannerVo) {
        return bannerService.findBannerPage(bannerVo);
    }


    /**
     * 根据轮播图id查询明细信息
     * @method: get/{id}
     * @path : /admin/banner/get/{id}
     * @param : id
     * @result : Banner
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
    */
    @GetMapping("/banner/get/{id}")
    public Banner getBannerById(@PathVariable("id") String id) {
        if(Vsserts.isEmpty(id)){
           throw new PugValidationException(ResultStatusEnumA.ID_NOT_EMPTY);
        }
        return bannerService.getBannerById(new Long(id));
    }


	/**
	 * 保存和修改轮播图
     * @method: saveupdate
	 * @path : /admin/banner/save
     * @param : banner
     * @result : Banner
	 * 创建人:yykk
	 * 创建时间：2022-02-21 01:50:36
	 * @version 1.0.0
	*/
    @PostMapping("/banner/saveupdate")
    public Banner saveupdateBanner(@RequestBody Banner banner) {
        return bannerService.saveupdateBanner(banner);
    }


    /**
	 * 根据轮播图id删除轮播图
     * @method: delete/{id}
     * @path : /admin/banner/delete/{id}
     * @param : id
     * @result : int
	 * 创建人:yykk
	 * 创建时间：2022-02-21 01:50:36
	 * @version 1.0.0
	*/
    @PostMapping("/banner/delete/{id}")
    public int deleteBannerById(@PathVariable("id") String id) {
        if(Vsserts.isEmpty(id)){
            throw new PugValidationException(ResultStatusEnumA.ID_NOT_EMPTY);
        }
        return bannerService.deleteBannerById(new Long(id));
    }


   /**
   	 * 根据轮播图ids批量删除轮播图
     * @method: banner/delBatch
     * @path : /admin/banner/delBatch
     * @param : bannerVo
     * @result : boolean
   	 * 创建人:yykk
   	 * 创建时间：2022-02-21 01:50:36
   	 * @version 1.0.0
   	*/
    @PostMapping("/banner/delBatch")
    public boolean delBanner(@RequestBody BannerVo bannerVo) {
        log.info("你要批量删除的IDS是:{}", bannerVo.getBatchIds());
        if (Vsserts.isEmpty(bannerVo.getBatchIds())) {
            throw new PugValidationException(ResultStatusEnumA.ID_NOT_EMPTY);
        }
        return bannerService.delBatchBanner(bannerVo.getBatchIds());
    }

}