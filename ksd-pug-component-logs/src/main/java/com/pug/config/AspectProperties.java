package com.pug.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ksd.aop")
@Data
public class AspectProperties {
    private boolean isFlag = true;
}