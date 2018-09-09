package com.bl.ep.handler;

import com.bl.ep.constant.ResultEnum;
import com.bl.ep.constant.ResultModel;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 拦截异常 使接口返回指定错误信息
 */
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResultModel missingServletRequestParameterException(Model model, Exception e) throws Exception{
        e.printStackTrace();
        return ResultModel.error(ResultEnum.ERROR_400);
    }
    @ExceptionHandler(value = UnsatisfiedServletRequestParameterException.class)
    public ResultModel unsatisfiedServletRequestParameterException(Model model, Exception e) throws Exception{
        e.printStackTrace();
        return ResultModel.error(ResultEnum.ERROR_400);
    }
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResultModel requestSupportErrorHandler(Model model, Exception e) throws Exception{
        e.printStackTrace();
        return ResultModel.error(ResultEnum.ERROR_405);
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResultModel runtimeErrorHandler(Model model, Exception e) throws Exception{
        e.printStackTrace();
        return ResultModel.error(ResultEnum.ERROR_500);
    }
    @ExceptionHandler(value = Exception.class)
    public ResultModel defaultErrorHandler(Model model, Exception e) throws Exception{
        e.printStackTrace();
        return ResultModel.error(ResultEnum.ERROR_500);
    }

}
