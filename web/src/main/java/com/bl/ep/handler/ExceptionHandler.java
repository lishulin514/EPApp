package com.bl.ep.handler;

import com.bl.ep.utils.ResultEnum;
import com.bl.ep.utils.ResultModel;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 * 处理异常
 */
@ControllerAdvice
public class ExceptionHandler {

    public static final String  ERROR_VIEW = "/error/404";

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse respone, Exception e) throws Exception{
        e.printStackTrace();
        //如果是ajax请求
        if(isAjax(request)){
            return ResultModel.error(ResultEnum.ERROR_500);
        //如果是页面请求
        }else{
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception",e);
            mav.addObject("url",request.getRequestURI());
            mav.setViewName(ERROR_VIEW);
            return mav;
        }
    }

    public static boolean isAjax(HttpServletRequest httpRequest){

        return (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
    }
}
