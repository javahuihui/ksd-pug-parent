package ${rootPackage}.config.enums;

/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2022/1/5 21:33
 */
public interface IResultEnum {

     // 项目服务名  系统编码
     String  A = "101"; // 前台系统
     String  B = "202"; // APP系统

     // 成功状态常量
     int SUCCESS = 200;
     String SUCCESS_TEXT = "SUCCESS";

     // 无法捕获的异常状态
     int SERVER_ERROR = 500;

     // 需要覆盖的方法 每个子类堆外暴露的方法
     int status();
     String message();
}
