package com.bl.ep.controller;

import com.bl.ep.domain.Resource;
import com.bl.ep.domain.User;
import com.bl.ep.service.UserService;
import com.bl.ep.utils.ResultModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    private Resource resource;

    @Autowired
    public void setSserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @PostMapping("/login")
    public ResultModel login(@RequestParam String username, @RequestParam String password){
        User userByUsername = userService.getUserByUsername(username);
        if(userByUsername.getPassword().equals(password)){
            return ResultModel.ok(1);
        }
        return ResultModel.ok(2);
    }

    @GetMapping("/getResource")
    public ResultModel getResource(){

        int i = 1/0;
        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);
        ResultModel ok = ResultModel.ok(bean);
        return ok;
    }

}
