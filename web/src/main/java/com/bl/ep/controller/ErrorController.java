package com.bl.ep.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController {

    @RequestMapping("/404")
    public String center(){

        return "/error/404";
    }
}
