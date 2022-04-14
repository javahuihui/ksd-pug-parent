package com.ksd.pug.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pug.mapper.SysLoginUserMapper;
import com.pug.pojo.SysLoginUser;
import com.pug.pojo.SysPermission;
import com.pug.pojo.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 13:20
 */
@Slf4j
@Service
public class SysLoginUserServiceImpl extends ServiceImpl<SysLoginUserMapper, SysLoginUser> implements ISysLoginUserService {


    @Override
    public List<SysRole> findSysRoleByUserId(Long userid) {
        return this.baseMapper.findSysRoleByUserId(userid);
    }

    @Override
    public List<SysPermission> findBySysPermissionUserId(Long userid){
        return this.baseMapper.findBySysPermissionUserId(userid);
    }

    /**
     * 实现了多表关联查询分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public IPage<SysLoginUser> findLoginUserPage(int pageNo, int pageSize) {
        Page<SysLoginUser> page = new Page<>(pageNo, pageSize);
        QueryWrapper<SysLoginUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("su.status", 1);
        queryWrapper.eq("su.isdelete", 0);
        return this.baseMapper.findLoginUserPage(page, queryWrapper);
    }
}
