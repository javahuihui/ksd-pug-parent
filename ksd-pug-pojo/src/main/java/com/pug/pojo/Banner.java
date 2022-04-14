package com.pug.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * Banner实体
 * 创建人:yykk<br/>
 * 时间：2022-02-21 01:50:36 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("kss_banner")
public class Banner  implements java.io.Serializable {

    // 主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    // 标题
    private String name;
    // 链接地址
    private String hreflink;
    // 打开方式
    private String opentype;
    // 描述
    private String description;
    // 封面图标
    private String img;
    // 排序字段
    private Integer sorted;
    // 发布状态
    private Integer status;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    // 删除状态 0未删除 1删除
    private Integer isdelete;
    // 内容
    private String content;
    // 开始时间
    private String starttime;
    // 结束时间
    private String endtime;
    // 作者头像
    private String avatar;
    // 分类
    private Long categoryid;

}