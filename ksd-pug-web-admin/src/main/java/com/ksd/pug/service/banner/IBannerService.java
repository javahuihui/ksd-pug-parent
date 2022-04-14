package com.ksd.pug.service.banner;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pug.pojo.Banner;
import com.ksd.pug.vo.BannerVo;

import java.util.List;

/**
 * IBannerService接口
 * 创建人:yykk<br/>
 * 时间：2022-02-21 01:50:36 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
public interface IBannerService extends IService<Banner>{


    /**
     * 查询轮播图列表信息
     * @method: findBannerList
     * @result : List<Banner>
     * 创建人:yykk
     * 创建时间：2022-02-21 01:50:36
     * @version 1.0.0
     * @return
     */
    List<Banner> findBannerList() ;

	/**
     * 查询轮播图列表信息并分页
     * 方法名：findBanners<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-21 01:50:36<br/>
     * @param bannerVo
     * @return IPage<Banner><br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
	IPage<Banner> findBannerPage(BannerVo bannerVo);

    /**
     * 保存&修改轮播图
     * 方法名：saveupdateBanner<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-21 01:50:36<br/>
     * @param banner 
     * @return Banner<br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    Banner saveupdateBanner(Banner banner);

    /**
     * 根据Id删除轮播图
     * 方法名：deleteBannerById<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-21 01:50:36<br/>
     * @param id
     * @return int <br />
     * @throws <br/>
     * @since 1.0.0<br />
     */
    int deleteBannerById(Long id) ;

    /**
     * 根据Id查询轮播图明细信息
     * 方法名：getBannerById<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-21 01:50:36<br/>
     * @param id
     * @return Banner <br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    Banner getBannerById(Long id);

    /**
     * 根据轮播图ids批量删除轮播图
     * 方法名：delBatchBanner<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-21 01:50:36<br/>
     * @param ids
     * @return boolean <br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    boolean delBatchBanner(String ids);

}