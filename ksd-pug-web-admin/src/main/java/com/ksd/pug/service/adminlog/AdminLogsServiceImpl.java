package com.ksd.pug.service.adminlog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pug.mapper.AdminLogsMapper;
import com.pug.pojo.AdminLogs;
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
public class AdminLogsServiceImpl extends ServiceImpl<AdminLogsMapper, AdminLogs> implements IAdminLogsService {
}
