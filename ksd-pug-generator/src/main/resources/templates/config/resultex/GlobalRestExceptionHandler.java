package ${rootPackage}.config.resultex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ${rootPackage}.config.resultex.err.PugBusinessException;
import ${rootPackage}.config.resultex.err.PugValidationException;
import ${rootPackage}.config.resultex.result.ErrorHandler;
import ${rootPackage}.config.resultex.result.ResultCodeEnum;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GlobalRestExceptionHandler全局异常处理
 * 创建人:YYKK<br/>
 * 时间：2022-02-16 02:36:00 <br/>
 * 源码下载：www.gitee.com
 * 飞哥B站地址：www.baidu.com
 * @version 1.0.0<br/>
 *
 */
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    /**
     * 对服务器端出现500异常进行统一处理
     * 缺点：不明确
     * 场景：
     */
    @ExceptionHandler(Throwable.class)
    public ErrorHandler makeExcepton(Throwable e, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.fail(ResultCodeEnum.SERVER_ERROR, e);
        return errorHandler;
    }


    /**
     * 对服务器出现RuntimeException进行捕获
     * 缺点：不明确
     * 场景：
     */
    @ExceptionHandler(RuntimeException.class)
    public ErrorHandler makeExcepton(RuntimeException e, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.fail(ResultCodeEnum.SERVER_ERROR, e, e.getMessage());
        return errorHandler;
    }

    /**
     * 对自定义异常的统一处理
     * 缺点：明确异常信息
     */
    @ExceptionHandler(PugValidationException.class)
    public ErrorHandler makevalidationException(PugValidationException validationException, HttpServletRequest request) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setMessage(validationException.getMessage());
        errorHandler.setStatus(validationException.getCode());
        return errorHandler;
    }

    /**
     * 对服务器端出现500异常进行统一处理
     * 缺点：明确异常信息
     */
    @ExceptionHandler(PugBusinessException.class)
    public ErrorHandler makeOrderException(PugBusinessException businessException, HttpServletRequest request) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setMessage(businessException.getMessage());
        errorHandler.setStatus(businessException.getCode());
        return errorHandler;
    }


    /**
     * 对验证的统一异常进行统一处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorHandler handlerValiator(MethodArgumentNotValidException e, HttpServletRequest request) throws JsonProcessingException {
        // 1: 从MethodArgumentNotValidException提取验证失败的所有的信息。返回一个List<FieldError>
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        // 2: 把fieldErrors中，需要的部分提出出来进行返回
        List<Map<String, String>> mapList = toValidatorMsg(fieldErrors);
        // 3: 把需要的异常信息转换成json进行返回
        ObjectMapper objectMapper = new ObjectMapper();
        String mapJson = objectMapper.writeValueAsString(mapList);
        ErrorHandler errorHandler = ErrorHandler.fail(ResultCodeEnum.SERVER_ERROR, e, mapJson);
        return errorHandler;
    }


    /**
     * 对验证异常进行统一处理提取需要的部分
     *
     * @param fieldErrorList
     * @return
     */
    private List<Map<String, String>> toValidatorMsg(List<FieldError> fieldErrorList) {
        List<Map<String, String>> mapList = new ArrayList<>();
        // 循环提取
        for (FieldError fieldError : fieldErrorList) {
            Map<String, String> map = new HashMap<>();
            // 获取验证失败的属性
            map.put("field", fieldError.getField());
            // 获取验证失败的的提示信息
            map.put("msg", fieldError.getDefaultMessage());

            mapList.add(map);
        }
        return mapList;
    }


}