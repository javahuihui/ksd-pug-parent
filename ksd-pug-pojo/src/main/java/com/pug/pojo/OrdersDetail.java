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
@TableName("kss_order_detail")
public class OrdersDetail implements java.io.Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品价格
     */
    private String productPrice;
    /**
     * 产品描述
     */
    private String productDesc;
    /**
     * 产品标题
     */
    private String productName;
    /**
     * 产品封面
     */
    private String productImg;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户头像
     */
    private String useravatar;
    /**
     * 订单号
     */
    private String ordernumber;
    /**
     * 交易号
     */
    private String tradeno;
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
