package com.pug.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
@TableName("kss_category")
public class ProductCategory implements java.io.Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 分类标题
     */
    private String name;
    /**
     * 分类图标
     */
    private String icon;
    /**
     * 分类排序
     */
    private Integer sorted;

    /*
    * 删除状态 0 未删除 1删除
     */
    private Integer isdelete;
    /**
     * 分类父id
     */
    private Long pid;
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

    /*定义装载的子集*/
    @TableField(exist = false)
    private List<ProductCategory> children;
}
