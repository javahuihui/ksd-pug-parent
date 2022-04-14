package com.ksd.pug.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RoleVo参数类
 * 创建人:yykk<br/>
 * 时间：2022-02-20 14:09:44 <br/>
 * 源码下载：前台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-ui.git
 * 飞哥B站地址：后台代码 git clone https://gitee.com/kekesam/kuangstudy-pug-parent.git
 * @version 1.0.0<br/>
 *
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo implements java.io.Serializable  {

    // 查询的id
	private Long id;
	// 查询状态
	private Integer status;
	// 当前页
	private Integer pageNo = 1;
	// 每页显示多少条
	private Integer pageSize = 10;
	// 搜索关键词
	private String keyword;
	// 批量删除的ids
    private String batchIds;
    // 查询是否删除的
    private Integer isDelete = 0;
    // 搜索分类id
    private Long categoryId;

}