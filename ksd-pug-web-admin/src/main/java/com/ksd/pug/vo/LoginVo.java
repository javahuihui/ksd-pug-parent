package com.ksd.pug.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/20$ 19:02$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVo implements java.io.Serializable {
    // 登录的用户名
    private String username;
    // 登录的密码
    private String password;
    // 登录验证码
    private String code;
    // 验证码缓存的key
    private String uuid;
}
