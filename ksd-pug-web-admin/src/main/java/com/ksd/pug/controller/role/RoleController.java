package com.ksd.pug.controller.role;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ksd.pug.service.role.IRoleService;
import com.pug.pojo.Role;
import com.ksd.pug.vo.RoleVo;
import com.ksd.pug.commons.enums.ResultStatusEnumA;
import com.pug.commons.ex.PugValidationException;
import com.pug.commons.utils.fn.asserts.Vsserts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.ksd.pug.controller.BaseController;
import java.util.List;

/**
 * RoleController
 * 创建人:yykk<br/>
 * 时间：2022-02-20 14:09:44 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/

@RestController
@RequiredArgsConstructor
@Slf4j
public class RoleController extends BaseController{

    private final IRoleService roleService;


    /**
     * 查询角色管理列表信息
     * @path : /admin/role/load
     * @method: findRoles
     * @result : List<Role>
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
     * @return
     */
    @GetMapping("/role/load")
    public List<Role> findRoleList() {
        return roleService.findRoleList();
    }

	/**
	 * 查询角色管理列表信息并分页
	 * @path : /admin/role/list
     * @method: findRoles
     * @param : roleVo
     * @result : IPage<Role>
	 * 创建人:yykk
	 * 创建时间：2022-02-20 14:09:44
	 * @version 1.0.0
	*/
    @PostMapping("/role/list")
    public IPage<Role> findRoles(@RequestBody RoleVo roleVo) {
        return roleService.findRolePage(roleVo);
    }


    /**
     * 根据角色管理id查询明细信息
     * @method: get/{id}
     * @path : /admin/role/get/{id}
     * @param : id
     * @result : Role
     * 创建人:yykk
     * 创建时间：2022-02-20 14:09:44
     * @version 1.0.0
    */
    @GetMapping("/role/get/{id}")
    public Role getRoleById(@PathVariable("id") String id) {
        if(Vsserts.isEmpty(id)){
           throw new PugValidationException(ResultStatusEnumA.ID_NOT_EMPTY);
        }
        return roleService.getRoleById(new Long(id));
    }


	/**
	 * 保存和修改角色管理
     * @method: saveupdate
	 * @path : /admin/role/save
     * @param : role
     * @result : Role
	 * 创建人:yykk
	 * 创建时间：2022-02-20 14:09:44
	 * @version 1.0.0
	*/
    @PostMapping("/role/saveupdate")
    public Role saveupdateRole(@RequestBody Role role) {
        return roleService.saveupdateRole(role);
    }


    /**
	 * 根据角色管理id删除角色管理
     * @method: delete/{id}
     * @path : /admin/role/delete/{id}
     * @param : id
     * @result : int
	 * 创建人:yykk
	 * 创建时间：2022-02-20 14:09:44
	 * @version 1.0.0
	*/
    @PostMapping("/role/delete/{id}")
    public int deleteRoleById(@PathVariable("id") String id) {
        if(Vsserts.isEmpty(id)){
            throw new PugValidationException(ResultStatusEnumA.ID_NOT_EMPTY);
        }
        return roleService.deleteRoleById(new Long(id));
    }


   /**
   	 * 根据角色管理ids批量删除角色管理
     * @method: role/delBatch
     * @path : /admin/role/delBatch
     * @param : roleVo
     * @result : boolean
   	 * 创建人:yykk
   	 * 创建时间：2022-02-20 14:09:44
   	 * @version 1.0.0
   	*/
    @PostMapping("/role/delBatch")
    public boolean delRole(@RequestBody RoleVo roleVo) {
        log.info("你要批量删除的IDS是:{}", roleVo.getBatchIds());
        if (Vsserts.isEmpty(roleVo.getBatchIds())) {
            throw new PugValidationException(ResultStatusEnumA.ID_NOT_EMPTY);
        }
        return roleService.delBatchRole(roleVo.getBatchIds());
    }

}