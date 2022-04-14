package com.ksd.pug.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pug.pojo.SysLoginUser;
import com.pug.pojo.SysPermission;
import com.pug.pojo.SysRole;

import java.util.List;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 13:20
 */
public interface ISysLoginUserService extends IService<SysLoginUser> {

    /**
     * 根据用户查询对应的角色
     * @param userid
     * @return
     */
    List<SysRole> findSysRoleByUserId(Long userid);

    /**
     * 查询用户对应的权限
     * @param userid
     * @return
     */
    List<SysPermission> findBySysPermissionUserId(Long userid);

    /**
     * 多表关联查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    IPage<SysLoginUser> findLoginUserPage(int pageNo, int pageSize);
}
