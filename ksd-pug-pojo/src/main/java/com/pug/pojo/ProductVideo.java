package com.pug.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/15$ 22:52$
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("kss_product_video")
public class ProductVideo implements java.io.Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 视频名称
     */
    private String name;
    /**
     * 视频VID
     */
    private String img;
    /**
     * 视频链接
     */
    private String videolink;
    /**
     * 视频介绍
     */
    private String description;
    /**
     * 视频关联产品
     */
    private String productids;
    /**
     * 视频浏览数
     */
    private Integer views;
    /**
     * 视频收藏数
     */
    private Integer collections;
    /**
     * 视频分类id
     */
    private Long categoryId;
    /**
     * 视频分类标题
     */
    private String categoryName;
    /**
     * 视频的商家
     */
    private Long userId;
    /**
     * 视频的删除
     */
    private Integer isdelete;
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
}
