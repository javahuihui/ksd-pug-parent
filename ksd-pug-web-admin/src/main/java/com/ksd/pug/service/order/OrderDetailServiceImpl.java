package com.ksd.pug.service.order;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pug.mapper.OrdersDetailMapper;
import com.pug.pojo.OrdersDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 飞哥
 * @SysLoginUseritle: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/2 12:42
 */
@Service
@Slf4j
public class OrderDetailServiceImpl extends ServiceImpl<OrdersDetailMapper, OrdersDetail> implements IOrdersDetailService {
}
