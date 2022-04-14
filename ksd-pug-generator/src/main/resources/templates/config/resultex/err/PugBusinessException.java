package ${rootPackage}.config.resultex.err;

import ${rootPackage}.config.resultex.result.ResultCodeEnum;
import ${rootPackage}.config.resultex.result.R;

/**
 *
 * PugBusinessException业务异常
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 * @version 1.0.0<br/>
 *
 */
public class PugBusinessException extends RuntimeException {
    private Integer code;
    private String message;

    public PugBusinessException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public PugBusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}