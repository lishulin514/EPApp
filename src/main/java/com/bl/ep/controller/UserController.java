package com.bl.ep.controller;

import com.alibaba.fastjson.JSON;
import com.bl.ep.constant.Resource;
import com.bl.ep.constant.ResultModel;
import com.bl.ep.model.UserCollect;
import com.bl.ep.model.UserModel;
import com.bl.ep.param.HomeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.UserParam;
import com.bl.ep.pojo.Home;
import com.bl.ep.pojo.User;
import com.bl.ep.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Resource resource;

    /**
     * 登录
     * @param param 接收用户名和密码
     * @return 1成功  2失败
     */
    @ResponseBody
    @PostMapping(value = "/login", params = {"username","password"})
    public ResultModel signIn(UserParam param){
        UserModel userModel = userService.signIn(param);
        logger.info("login UserParam = {}",JSON.toJSONString(param));

        return ResultModel.response(userModel);
    }

    /**
     * 注册
     * @param param （用户名称 密码）必填、（姓名、年龄、性别）选填
     * @return 0该用户已注册 1注册成功
     */
    @ResponseBody
    @PutMapping(value = "/signUp", params = {"username","password"})
    public ResultModel signUp(UserParam param){

        int result = userService.signUp(param);

        return ResultModel.response(result);
    }

    /**
     * 根据用户名查询用户
     * @param param 用户名
     * @return 获取到的用户信息
     */
    @ResponseBody
    @PostMapping(value = "/user/info", params = {"username"})
    public ResultModel getUserByName(UserParam param){

        User user = userService.getUserByUsername(param);
        return ResultModel.response(user);
    }


    /**
     * 获取收藏内容
     * @param collectType 1=新闻 、2=商品 、默认=所有
     * @return 收藏信息列表
     */
    @ResponseBody
    @PostMapping(value = "/user/collect")
    public ResultModel userCollect(
            @RequestParam(value="collectType" ,required =false ) Integer collectType){
        logger.info("userCollect param = {}", collectType);
        List<UserCollect> collects = userService.getUserCollects(collectType);
        return ResultModel.response(collects);
    }

    /**
     * 修改密码
     * @param userParam 用户名、原密码、新密码必填
     * @return
     */
    @ResponseBody
    @PutMapping(value = "/update/password", params = {"username","password","newPassword"})
    public ResultModel updatePassword(UserParam userParam){
        logger.info("updatePassword param = {}", JSON.toJSONString(userParam));

        int isUpdate = userService.updatePassword(userParam);

        return ResultModel.response(isUpdate);
    }
}
