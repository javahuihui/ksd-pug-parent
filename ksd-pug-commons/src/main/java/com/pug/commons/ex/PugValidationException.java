package com.pug.commons.ex;//package com.kuangstudy.config;


import com.pug.commons.enums.IResultEnum;

/**
 * Description: 自定义异常
 * Author: yykk Administrator
 * Version: 1.0
 * Create Date Time: 2021/12/15 21:48.
 * Update Date Time:
 *
 * @see
 */
public class PugValidationException extends RuntimeException {

    private int status;
    private String msg;

    public PugValidationException(int status, String message) {
        super(message);
        this.status = status;
        this.msg = message;
    }

    public PugValidationException(IResultEnum resultEnum) {
        super(resultEnum.message());
        this.status = resultEnum.status();
        this.msg = resultEnum.message();
    }


    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}
