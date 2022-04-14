package ${rootPackage}.config.resultex.result;


/**
 * ResultCodeEnum枚举类
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 * @version 1.0.0<br/>
 *
 */
public enum ResultCodeEnum {

    SUCCESS(true, 200, "成功"),
    UNKNOWN_REASON(false, 20001, "未知错误"),
    SERVER_ERROR(false, 500, "服务器忙，请稍后在试"),
    SERVER_DB_ERROR(false, 501, "数据库服务器忙，请稍后在试"),
    EMPTY_ERROR(false, 901, "不允许为空"),
    ORDER_CREATE_FAIL(false, 601, "订单下单失败");

    private Boolean success;
    private Integer code;
    private String message;
    private ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}