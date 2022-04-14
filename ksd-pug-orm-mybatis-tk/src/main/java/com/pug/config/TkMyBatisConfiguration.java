package com.pug.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("com.pug.mapper")
public class TkMyBatisConfiguration {
}
