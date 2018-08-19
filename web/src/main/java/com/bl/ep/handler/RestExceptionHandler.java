//package com.bl.ep.handler;
//
//import com.bl.ep.utils.ResultEnum;
//import com.bl.ep.utils.ResultModel;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class RestExceptionHandler {
//
//    @ExceptionHandler(value = Exception.class)
//    public ResultModel defaultErrorHandler(Model model, Exception e) throws Exception{
//        return ResultModel.error(ResultEnum.ERROR_500);
//    }
//}
