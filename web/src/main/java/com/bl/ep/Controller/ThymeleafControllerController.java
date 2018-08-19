package com.bl.ep.controller;

import com.bl.ep.domain.Resource;
import com.bl.ep.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("th")
public class ThymeleafControllerController {

    @Autowired
    private Resource resource;

    @RequestMapping("/index")
    public String index(Model map){
        map.addAttribute("name", "thymeleaf-imooc");
        return "/thymeleaf/index";
    }

    @RequestMapping("/center")
    public String center(){

        return "/thymeleaf/center/center";
    }

    @RequestMapping("/user")
    public String user(Model map){

        User u = new User();
        u.setId(11);
        u.setAge(18);
        u.setName("lee");
        u.setPassword("123456");
        u.setBirthday(new Date());
        u.setDesc("<font color='green'><b>hello</b></font>");
        map.addAttribute("user", u);

        User u1 = new User();
        u1.setId(111);
        u1.setAge(118);
        u1.setName("lee1");
        u1.setPassword("1234567");
        u1.setBirthday(new Date());
        u1.setDesc("<font color='green'><b>hello</b></font>");

        User u2 = new User();
        u2.setId(112);
        u2.setAge(128);
        u2.setName("lee");
        u2.setPassword("123456");
        u2.setBirthday(new Date());
        u2.setDesc("<font color='green'><b>hello</b></font>");

        List<User> userList = new ArrayList<>();
        userList.add(u);
        userList.add(u1);
        userList.add(u2);
        map.addAttribute("userList", userList);
        return "/thymeleaf/user";
    }
    @RequestMapping("/postform")
    public String postform(User user){

        System.out.println(user.getName());
        return "redirect:/th/user";
    }

    @RequestMapping("/testErrorHandler")
    public String testErrorHandler(){

        return "redirect:/th/user";
    }
}
