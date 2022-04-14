package com.pug.aop;

import com.pug.commons.ex.PugBussinessException;
import com.pug.commons.ex.OrderException;
import com.pug.config.AspectProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

@Aspect
@Slf4j
@Order(1)
public class PugLogAspect {

    private AspectProperties aspectProperties;

    public PugLogAspect(AspectProperties aspectProperties) {
        this.aspectProperties = aspectProperties;
    }
    public PugLogAspect(){

    }

    //1.定义切点
    @Pointcut("@annotation(com.pug.commons.anno.PugLog)")
    public void pointcut(){

    }



    // 2: 通知 （切点或者切点表达式）
    @Around("pointcut()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint){
        // 1:方法执行的开始时间
        long starttime = System.currentTimeMillis();
        // 2:执行真实方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        System.out.println(signature.getMethod().getName());
        System.out.println(signature.getReturnType());
        System.out.println(signature.getParameterNames());
        System.out.println(signature.getParameterTypes());
        //核心代码
        log.info("PugLogAspect切面的aroundLog的前置方法");
        Object methodReturnValue = null;
        try {
            methodReturnValue = proceedingJoinPoint.proceed();
            // 3:方法执行的结束时间
            long endtime = System.currentTimeMillis();
            // 4：方法的总耗时
            long total = endtime - starttime;
            log.info("---PugLog---当前方法:{}，执行的时间是：{} ms", signature.getMethod().getName(), total);
            return methodReturnValue;
        }catch (PugBussinessException e) {
            throw e;
        }catch (OrderException e){
            throw e;
        } catch (Throwable ex) {
            throw new RuntimeException("PugLog异常");
        }
    }


//    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
//        try {
//            // 获取当前的用户
//            SysLoginUser loginUser = UserThrealLocal.get();
//            // *========数据库日志=========*//
//            // 请求的地址
//            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
//            // 设置方法名称
//            String className = joinPoint.getTarget().getClass().getName();
//            String methodName = joinPoint.getSignature().getName();
//
//        } catch (Exception exp) {
//            // 记录本地异常日志
//            log.error("==前置通知异常==");
//            log.error("异常信息:{}", exp.getMessage());
//            exp.printStackTrace();
//        }
//    }






}
