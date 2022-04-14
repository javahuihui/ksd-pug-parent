package com.pug.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * BannerCategory实体
 * 创建人:yykk<br/>
 * 时间：2022-02-20 20:17:40 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("kss_banner_category")
public class BannerCategory  implements java.io.Serializable {

    // 主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    // 分类标题
    private String name;
    // 分类图标
    private String icon;
    // 分类排序
    private Integer sorted;
    // 分类父id
    private Long pid;
    // 发布状态
    private Integer status;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    // 删除状态 0 未删除 1删除
    private Integer isdelete;

}