package com.pug.config;

import com.pug.aop.PugLogAspect;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AspectProperties.class) //为什么要加这个注解 思考？
public class PugLogsConfiguration {


    @Bean
    public PugLogAspect pugLogAspect(AspectProperties aspectProperties) {
        return new PugLogAspect(aspectProperties);
    }

}
