package ${rootPackage}.config.resultex.result;

/**
 *
 * R结果返回
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 * @version 1.0.0<br/>
 *
 */
public class R {

    // 返回的编号
    private Integer code;
    // 返回的数据,数据类型N中，
    private Object data;
    // 返回的信息
    private String message;

    // 调用过程中，保持一种调用。直接用类去调用。
    private R() {

    }

    /**
     * @return com.kuangstudy.common.R
     * @Author xuke
     * @Description 成功返回
     * @Date 21:55 2021/6/23
     * @Param []
     **/
    public static R success(Object data, String message) {
        R r = new R();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setData(data);
        r.setMessage(message == null ? ResultCodeEnum.SUCCESS.getMessage() : message);
        return r;
    }

    /**
     * @return com.kuangstudy.common.R
     * @Author xuke
     * @Description 成功返回
     * @Date 21:55 2021/6/23
     * @Param []
     **/
    public static  R success(Object data) {
        return success(data, null);
    }


    /**
     * @return com.kuangstudy.common.R
     * @Author xuke
     * @Description
     * @Date 22:03 2021/6/23
     * @Param [code 失败的状态, message 失败的原因]
     **/
    public static  R fail(Integer code, String message) {
        R r = new R();
        r.setCode(code);
        r.setData(null);
        r.setMessage(message);
        return r;
    }

    /**
     * @return com.kuangstudy.common.R
     * @Author xuke
     * @Description
     * @Date 22:03 2021/6/23
     * @Param [code 失败的状态, message 失败的原因]
     **/
    public static  R fail(ResultCodeEnum responseEnum) {
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setData(null);
        r.setMessage(responseEnum.getMessage());
        return r;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

