package com.bl.ep.controller;

import com.bl.ep.constant.Resource;
import com.bl.ep.pojo.User;
import com.bl.ep.service.UserService;
import com.bl.ep.utils.ResultEnum;
import com.bl.ep.utils.ResultModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping("/register")
    public ResultModel register(){
        try{
            for (int i = 0 ; i < 10 ; i++){
                User user = new User();
                user.setUsername("lishulin"+i);
                user.setPassword("lishulin"+i);
                user.setRealname("lishulin"+i);
                user.setIsDelete(0);
                user.setAge(i);
                user.setSex(1);
                user.setCreateTime(new Date());
                userService.createUser(user);
            }
            return ResultModel.ok(null);
        }catch(Exception e){
            e.printStackTrace();
            return ResultModel.error(ResultEnum.ERROR_500);
        }
    }

    @GetMapping("/testTransactional")
    public ResultModel testTransactional(){
        User user = new User();
        user.setUsername("lishulinX");
        user.setPassword("lishulinX");
        user.setRealname("lishulinX");
        user.setIsDelete(0);
        user.setAge(111);
        user.setSex(1);
        user.setCreateTime(new Date());
        userService.testTransactional(user);

        return ResultModel.ok(null);
    }
}
