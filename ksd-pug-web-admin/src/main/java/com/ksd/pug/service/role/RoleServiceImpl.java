package com.ksd.pug.service.role;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pug.mapper.RoleMapper;
import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.pug.commons.ex.PugValidationException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import com.pug.pojo.Role;
import com.ksd.pug.vo.RoleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * RoleServiceImpl实现类
 * 创建人:yykk<br/>
 * 时间：2022-02-20 14:09:44 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements IRoleService  {

    /**
     * 查询分页&搜索角色管理
     * @param roleVo
     * @return IPage<Role>
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
     */
    @Override
	public IPage<Role> findRolePage(RoleVo roleVo){
	    // 设置分页信息
		Page<Role> page = new Page<>(roleVo.getPageNo(),roleVo.getPageSize());
		// 设置查询条件
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // lambdaQueryWrapper.select(Role.class, column -> !column.getColumn().equals("description"));
        // 根据关键词搜索信息
        lambdaQueryWrapper.like(Vsserts.isNotEmpty(roleVo.getKeyword()), Role::getName,roleVo.getKeyword());
         //查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(roleVo.getStatus() != null ,Role::getStatus,roleVo.getStatus());
        // 多列搜索
        // lambdaQueryWrapper.and(Vsserts.isNotEmpty(roleVo.getKeyword()),wrapper -> wrapper
        //         .like(Role::getName, roleVo.getKeyword())
        //         .or()
        //         .like(Role::getCategoryName, roleVo.getKeyword())
        // );
        // 根据时间排降序
        lambdaQueryWrapper.orderByDesc(Role::getCreateTime);
        // 查询分页返回
		IPage<Role> results = this.page(page,lambdaQueryWrapper);
		return results;
	}

    /**
     * 查询角色管理列表信息
     * @method: findRoleList
     * @result : List<Role>
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
     * @return
    */
    @Override
    public List<Role> findRoleList() {
     	// 1：设置查询条件
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2：查询发布的 0 未发布  1 发布
        lambdaQueryWrapper.eq(Role::getStatus,1);
        lambdaQueryWrapper.eq(Role::getIsdelete,0);
        // 3: 查询返回
        return this.list(lambdaQueryWrapper);
    }

	/**
     * 根据id查询角色管理明细信息
     * @param id
     * @return Role
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
     */
    @Override
    public Role getRoleById(Long id) {
        return this.getById(id);
    }


    /**
     * 保存&修改角色管理
     * @param role
     * @return Role
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
     */
    @Override
	public Role saveupdateRole(Role role){
		boolean flag = this.saveOrUpdate(role);
		return flag ? role : null;
	}


    /**
     * 根据id删除角色管理
     * @param id
     * @return int
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
     */
    @Override
    public int deleteRoleById(Long id) {
        boolean b = this.removeById(id);
        return b ? 1 : 0;
    }

    /**
     * 根据id删除
     * @param ids
     * @return boolean
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
     */
    @Override
    public boolean delBatchRole(String ids) {
        try {
            // 1 : 把数据分割
            String[] strings = ids.split(",");
            // 2: 组装成一个List<Role>
            List<Role> roleList = Arrays.stream(strings).map(idstr -> {
                Role role = new Role();
                role.setId(new Long(idstr));
                role.setIsdelete(1);
                return role;
            }).collect(Collectors.toList());
            // 3: 批量删除
            return this.updateBatchById(roleList);
        } catch (Exception ex) {
            throw new PugValidationException(ResultStatusEnumA.SERVER_DB_ERROR);
        }
    }


}