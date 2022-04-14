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
 * @date 2022/1/20$ 22:03$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo implements java.io.Serializable {
    // 分页
    private Integer pageNo = 1;
    private Integer pageSize = 15;
    // 搜索关键词
    private String keyword;
    // 搜索状态
    private Integer status;
    // 搜索分类id
    private Long categoryId;
    // 查询是否删除的
    private Integer isDelete = 0;
    // 批量删除的ids
    private String batchIds;
}
