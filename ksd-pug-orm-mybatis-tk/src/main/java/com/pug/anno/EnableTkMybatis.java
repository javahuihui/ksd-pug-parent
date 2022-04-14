package com.pug.anno;


import com.pug.config.TkMyBatisConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(TkMyBatisConfiguration.class)
public @interface EnableTkMybatis {
}
