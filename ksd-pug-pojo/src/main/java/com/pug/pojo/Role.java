package com.pug.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * Role实体
 * 创建人:yykk<br/>
 * 时间：2022-02-20 14:09:44 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("kss_role")
public class Role  implements java.io.Serializable {

    // 主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    // 角色名字
    private String name;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    // 角色代号
    private String code;
    // 发布状态 1发布中 0未发布
    private Integer status;
    // 删除状态 0未删除 1删除
    private Integer isdelete;

}