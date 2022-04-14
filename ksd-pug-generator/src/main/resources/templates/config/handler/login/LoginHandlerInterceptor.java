package ${rootPackage}.config.handler.login;

import ${rootPackage}.config.constants.SessionContants;
import ${rootPackage}.config.thread.UserThreadLocal;
import ${rootPackage}.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginHandlerInterceptor登录拦截
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 *
 * @version 1.0.0<br/>
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("x-frame-options", "SAMEORIGIN");
        // 获取 loginUser 信息进行判断
        User user = (User) request.getSession().getAttribute(SessionContants.LOGIN_USER);
        if (user == null) { // 未登录，返回登录页面
            request.setAttribute("msg", "没有权限，请先登录");
            response.sendRedirect("/admin/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserThreadLocal.remove();
    }

    // 此处两个方法，一定都是要preHandle方法返回为true的时候执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserThreadLocal.remove();
    }
}