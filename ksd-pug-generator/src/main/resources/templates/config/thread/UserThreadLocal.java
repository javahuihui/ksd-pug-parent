package ${rootPackage}.config.thread;

import ${rootPackage}.pojo.User;

/**
 * UserThreadLocal状态管理
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 *
 * @version 1.0.0<br/>
 */
public class UserThreadLocal {

    private static ThreadLocal<User> userDoThreadLocal = new ThreadLocal<>();

    public static void put(User userDo) {
        userDoThreadLocal.set(userDo);
    }

    public static User get() {
        return userDoThreadLocal.get();
    }

    public static void remove() {
        userDoThreadLocal.remove();
    }
}
