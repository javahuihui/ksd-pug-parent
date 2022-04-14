package ${rootPackage}.config;

import ${rootPackage}.config.handler.login.LoginHandlerInterceptor;
import ${rootPackage}.config.handler.repeat.RepeatSubmitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * mvc配置类
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 *
 * @version 1.0.0<br />
 */
@Configuration
@Profile("prod")
public class WebMvcConfig implements WebMvcConfigurer {


    @Value("${kuohao2('file.staticPatternPath')}")
    private String staticPatternPath;
    @Value("${kuohao2('file.uploadFolder')}")
    private String uploadFolder;


    /**
     * 表单重复提交
     */
    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;
    /**
     * 登录拦截
     */
    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;


    /*
     * @Author 徐柯
     * @Description 静态资源拦截处理
     * @Date 20:11 2021/4/18
     * @Param [registry]
     * @return void
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticPatternPath).addResourceLocations("file:" + uploadFolder);
    }

    /**
     * 拦截的注册
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 防止连续请求
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginHandlerInterceptor)
                // 需要登录拦截的地址
                .addPathPatterns("/admin/**")
                // 不拦截的地址
                .excludePathPatterns("/admin/auth/**", "/admin/captcha", "/admin/upload/**");

    }


//    /**
//     * 解决跨域问题
//     * @param registry
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry
//                .addMapping("/**")
//                // 允许跨域的域名
//                .allowedOriginPatterns("*") // 允许所有域
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                //.allowedMethods("*") // 允许任何方法（post、get等）
//                .allowedHeaders("*") // 允许任何请求头
//                .allowCredentials(true) // 允许证书、cookie
//                .maxAge(3600L); // maxAge(3600)表明在3600秒内，不需要再发送预检验请求，可以缓存该结果
//    }

}