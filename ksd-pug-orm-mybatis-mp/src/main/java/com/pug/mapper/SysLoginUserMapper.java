package com.pug.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pug.pojo.SysLoginUser;
import com.pug.pojo.SysPermission;
import com.pug.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 飞哥
 * @SysLoginUseritle: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 12:42
 */
public interface SysLoginUserMapper extends BaseMapper<SysLoginUser> {
    // 查询用户对应的角色
    List<SysRole> findSysRoleByUserId(@Param("userId")Long userid);
    // 查询用户对应角色的权限列表
    List<SysPermission> findBySysPermissionUserId(@Param("userId") Long userId);
    // 多表关联查询分页
    IPage<SysLoginUser> findLoginUserPage(Page<SysLoginUser> page, @Param("ew") Wrapper<SysLoginUser> queryWrapper);
}
