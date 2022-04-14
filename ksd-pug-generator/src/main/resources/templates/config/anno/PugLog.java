package ${rootPackage}.config.anno;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author ruoyi
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PugLog {
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 是否保存请求的参数 保存用户的 ip ip地址，浏览版本  操作系统型号
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数  返回值
     */
    public boolean isSaveResponseData() default true;
}
