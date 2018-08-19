package com.bl.ep.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 处理异常
 */
//@ControllerAdvice
public class ExceptionHandler {

    public static final String  ERROR_VIEW = "/error/404";

//    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public String errorHandler(Model model, Exception e) throws Exception{

        return ERROR_VIEW;
    }
}
