package com.bl.ep.handler;

import com.bl.ep.constant.ResultEnum;
import com.bl.ep.constant.ResultModel;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

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
