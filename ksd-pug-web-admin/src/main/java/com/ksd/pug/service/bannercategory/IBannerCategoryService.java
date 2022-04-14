package com.ksd.pug.service.bannercategory;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pug.pojo.BannerCategory;
import com.ksd.pug.vo.BannerCategoryVo;

import java.util.List;

/**
 * IBannerCategoryService接口
 * 创建人:yykk<br/>
 * 时间：2022-02-20 20:17:40 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
public interface IBannerCategoryService extends IService<BannerCategory>{


    /**
     * 查询轮播图分类列表信息
     * @method: findBannerCategoryList
     * @result : List<BannerCategory>
     * 创建人:yykk
     * 创建时间：2022-02-20 20:17:40
     * @version 1.0.0
     * @return
     */
    List<BannerCategory> findBannerCategoryList() ;

	/**
     * 查询轮播图分类列表信息并分页
     * 方法名：findBannerCategorys<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 20:17:40<br/>
     * @param bannercategoryVo
     * @return IPage<BannerCategory><br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
	IPage<BannerCategory> findBannerCategoryPage(BannerCategoryVo bannercategoryVo);

    /**
     * 保存&修改轮播图分类
     * 方法名：saveupdateBannerCategory<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 20:17:40<br/>
     * @param bannercategory 
     * @return BannerCategory<br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    BannerCategory saveupdateBannerCategory(BannerCategory bannercategory);

    /**
     * 根据Id删除轮播图分类
     * 方法名：deleteBannerCategoryById<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 20:17:40<br/>
     * @param id
     * @return int <br />
     * @throws <br/>
     * @since 1.0.0<br />
     */
    int deleteBannerCategoryById(Long id) ;

    /**
     * 根据Id查询轮播图分类明细信息
     * 方法名：getBannerCategoryById<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 20:17:40<br/>
     * @param id
     * @return BannerCategory <br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    BannerCategory getBannerCategoryById(Long id);

    /**
     * 根据轮播图分类ids批量删除轮播图分类
     * 方法名：delBatchBannerCategory<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 20:17:40<br/>
     * @param ids
     * @return boolean <br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    boolean delBatchBannerCategory(String ids);

}