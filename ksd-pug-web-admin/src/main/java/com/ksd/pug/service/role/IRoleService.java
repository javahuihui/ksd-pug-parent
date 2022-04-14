package com.ksd.pug.service.role;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pug.pojo.Role;
import com.ksd.pug.vo.RoleVo;

import java.util.List;

/**
 * IRoleService接口
 * 创建人:yykk<br/>
 * 时间：2022-02-20 14:09:44 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
public interface IRoleService extends IService<Role>{


    /**
     * 查询角色管理列表信息
     * @method: findRoleList
     * @result : List<Role>
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
     * @return
     */
    List<Role> findRoleList() ;

	/**
     * 查询角色管理列表信息并分页
     * 方法名：findRoles<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 14:09:44<br/>
     * @param roleVo
     * @return IPage<Role><br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
	IPage<Role> findRolePage(RoleVo roleVo);

    /**
     * 保存&修改角色管理
     * 方法名：saveupdateRole<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 14:09:44<br/>
     * @param role 
     * @return Role<br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    Role saveupdateRole(Role role);

    /**
     * 根据Id删除角色管理
     * 方法名：deleteRoleById<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 14:09:44<br/>
     * @param id
     * @return int <br />
     * @throws <br/>
     * @since 1.0.0<br />
     */
    int deleteRoleById(Long id) ;

    /**
     * 根据Id查询角色管理明细信息
     * 方法名：getRoleById<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 14:09:44<br/>
     * @param id
     * @return Role <br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    Role getRoleById(Long id);

    /**
     * 根据角色管理ids批量删除角色管理
     * 方法名：delBatchRole<br/>
     * 创建人：yykk <br/>
     * 时间：2022-02-20 14:09:44<br/>
     * @param ids
     * @return boolean <br />
     * @throws <br/>
     * @since 1.0.0<br />
    */
    boolean delBatchRole(String ids);

}