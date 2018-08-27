package com.bl.ep.controller;

import com.bl.ep.constant.Resource;
import com.bl.ep.constant.ResultModel;
import com.bl.ep.model.UserCollect;
import com.bl.ep.param.CollectParam;
import com.bl.ep.param.HomeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.UserParam;
import com.bl.ep.pojo.Home;
import com.bl.ep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Resource resource;

    @PostMapping(value = "/login", params = {"username","password"})
    public ResultModel login(UserParam param){
        int result = userService.signIn(param);

        return ResultModel.response(result);
    }

    @PostMapping("/home/list")
    public ResultModel homeList(HomeParam param, PageParam pageParam){

        List<Home> homes = userService.homeList(param, pageParam);
        return ResultModel.response(homes);
    }

    @PostMapping(value = "/merchandize/collect/{userId}/{categoryId}")
    public ResultModel merchandizeCollect(@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId){

        userService.addMerchandizeCollect(userId, categoryId);
        return ResultModel.response(true);
    }

    @PostMapping(value = "/home/collect/{userId}/{homeId}")
    public ResultModel homeCollect(@PathVariable("userId") Integer userId, @PathVariable("homeId") Integer homeId){

        userService.addHomeCollect(userId, homeId);
        return ResultModel.response(true);
    }

    @PostMapping(value = "/user/collect")
    public ResultModel userCollect(CollectParam param){

        List<UserCollect> collects = userService.getUserCollects(param);
        return ResultModel.response(collects);
    }
}
