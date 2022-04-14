package com.pug.anno;


import com.pug.config.MpMyBatisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MpMyBatisConfiguration.class)
public @interface EnableMpMybatis {
}
