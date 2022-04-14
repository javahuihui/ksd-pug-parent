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
@TableName("kss_product")
public class Product implements java.io.Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品封面
     */
    private String img;
    /**
     * 产品标签
     */
    private String tags;
    /**
     * 产品价格
     */
    private String price;
    /**
     * 产品交易价格
     */
    private String realprice;
    /**
     * 产品描述
     */
    private String description;
    /**
     * 产品组图
     */
    private String imgjson;
    /**
     * 产品浏览数
     */
    private Integer views;
    /**
     * 产品收藏数
     */
    private Integer collections;
    /**
     * 产品分类id
     */
    private Integer categoryId;
    /**
     * 产品分类标题
     */
    private String categoryName;
    /**
     * 产品的商家
     */
    private Long userId;
    /**
     * 产品的删除
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
