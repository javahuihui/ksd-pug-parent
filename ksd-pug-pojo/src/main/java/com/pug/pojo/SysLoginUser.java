package com.pug.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.pug.validator.anno.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;


/**
 * todo:用户管理
 * User<br/>
 * ******************************************
 * 更多精彩B站搜索:学相伴飞哥
 * IUserService
 * 资料下载学习平台：http://www.itbooking.net
 * 创世神B站：https://space.bilibili.com/490711252
 * ******************************************
 * 创建人:yykk<br/>
 * 时间：2021年09月26日 21:21:00 <br/>
 *
 * @version 1.0.0<br />
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("kss_user")
public class SysLoginUser implements java.io.Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 发布状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 用户名
     */
    @NotEmpty(message = "请输入用户名")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "请输入密码")
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer male;
    /**
     * 电话
     */
    @Phone
    private String telephone;
    /**
     * 身份证
     */
    private String idcard;
    /**
     * 微信openid
     */
    private String openid;
}