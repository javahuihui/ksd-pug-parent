package com.ksd.pug.config.webmvc;

import com.ksd.pug.config.interceptor.jwt.JwtInterceptor;
import com.ksd.pug.config.interceptor.repeat.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/6 17:50
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * jwt的token校验
     */
    @Autowired
    public JwtInterceptor jwtInterceptor;

    /**
     * 表单重复提交
     */
    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;

    /**
     * 拦截的注册
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 防止连续请求
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/admin/v1/**");
        registry.addInterceptor(jwtInterceptor)
                // 需要登录拦截的地址
                .addPathPatterns("/admin/v1/**")
                // 不拦截的地址
                .excludePathPatterns("/admin/v1/auth/**","/admin/captcha","/admin/v1/upload/**");

    }

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                //.allowedOrigins("http://yyy.com", "http://xxx.com") //
                // 允许跨域的域名
                .allowedOriginPatterns("*") // 允许所有域
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                //.allowedMethods("*") // 允许任何方法（post、get等）
                .allowedHeaders("*") // 允许任何请求头
                .allowCredentials(true) // 允许证书、cookie
                .maxAge(3600L); // maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
    }
}
