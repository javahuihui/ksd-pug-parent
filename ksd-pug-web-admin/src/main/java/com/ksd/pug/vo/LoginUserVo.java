package com.ksd.pug.vo;

import com.pug.pojo.SysPermission;
import com.pug.pojo.SysRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/6 16:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo implements java.io.Serializable {
    private Long userId;
    private String username;
    private String nickname;
    private String avatar;
    private String token;
    // 存放用户对应的角色
    private List<SysRole> roleList;
    // 存放用户对应的权限
    private List<SysPermission> permissionList;
}
