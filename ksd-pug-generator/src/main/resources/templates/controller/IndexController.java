package ${rootPackage}.controller;


import ${rootPackage}.config.anno.PugRateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ${rootPackage}.controller.BaseController;

/**
 * 首页
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 * @version 1.0.0<br/>
 *
 */
@RestController
public class IndexController extends BaseController {

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    @PugRateLimiter(limit = 3,timeout = 1)
    public String index() {
        return "欢迎使用Pug自动构建";
    }
}
