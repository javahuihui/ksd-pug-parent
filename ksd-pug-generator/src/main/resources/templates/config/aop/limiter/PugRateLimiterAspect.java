package ${rootPackage}.config.aop.limiter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import ${rootPackage}.config.anno.PugRateLimiter;
import ${rootPackage}.config.enums.LimiterType;
import ${rootPackage}.utils.ip.IpUtils;
import ${rootPackage}.utils.json.FastJsonUtil;
import ${rootPackage}.utils.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 飞哥B站地址：https://space.bilibili.com/490711252
 * 记得关注和三连哦！
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/12/22 23:05
 */
@Aspect
@Slf4j
@Order(1)
public class PugRateLimiterAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private DefaultRedisScript<Boolean> ipLimiterLuaScript;
    @Autowired
    private DefaultRedisScript<Boolean> ipLimitLua;

    // 1: 切入点
    @Pointcut("@annotation(${rootPackage}.config.anno.PugRateLimiter)")
    public void limiterPonicut() {
    }

    @Before("limiterPonicut()")
    public void limiter(JoinPoint joinPoint) {
        log.info("限流进来了.......");
        log.info("LimiterAspect------------around before limter-------------------------");
        // 1：获取方法的签名作为key
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 4: 读取方法的注解信息获取限流参数
        PugRateLimiter annotation = method.getAnnotation(PugRateLimiter.class);
        // 6：获取服务请求的对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        String methodNameKey = getRateLimiterKey(annotation, joinPoint, request);
        String userIp = IpUtils.getIpAddr(request);
        log.info("用户IP是：.......{}", userIp);
        // 7：通过方法反射获取注解的参数
        Integer limit = annotation.limit();
        Integer timeout = annotation.timeout();
        try {
            // 8: 请求lua脚本
            Boolean acquired = stringRedisTemplate.execute(ipLimitLua, Lists.newArrayList(methodNameKey), limit.toString(), timeout.toString());
            // 如果超过限流限制
            if (!acquired) {
                try {
                    HashMap<Object, Object> objectObjectHashMap = Maps.newHashMap();
                    objectObjectHashMap.put("msg", "客官你慢点，请稍后在试一试!");
                    WebUtils.renderString(response, FastJsonUtil.toJSON(objectObjectHashMap));
                } catch (Exception ex) {
                    throw new RuntimeException("客官你慢点，请稍后在试一试!!!");
                }
            }
        } catch (Exception ex) {
            log.info("redis服务器出问题了....");
        }
    }


    public String getRateLimiterKey(PugRateLimiter rateLimiter, JoinPoint point, HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
        if (rateLimiter.limitType() == LimiterType.IP) {
            stringBuffer.append(IpUtils.getIpAddr(request)).append("-");
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        return stringBuffer.toString();
    }

}
