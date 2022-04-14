package com.pug.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by yykk on 17/1/20.
 */
@TableName("sys_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission implements java.io.Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 权限名称
     */
    private String name;
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
     * 权限代号
     */
    private String code;
    /**
     * 权限URL
     */
    private String url;
    /**
     * 权限排序
     */
    private Integer sorted;
}