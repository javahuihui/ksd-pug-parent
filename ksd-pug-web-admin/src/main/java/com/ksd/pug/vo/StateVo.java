package com.ksd.pug.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * StateVo参数类
 * 创建人:星辰同学<br/>
 * 时间：2022-02-17 00:45:48 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 * @version 1.0.0<br/>
 *
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateVo implements java.io.Serializable  {
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
}