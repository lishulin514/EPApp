package com.bl.ep.controller;

import com.alibaba.fastjson.JSON;
import com.bl.ep.constant.ResultModel;
import com.bl.ep.param.HomeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Home;
import com.bl.ep.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/9/4 14:19
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;
    /**
     * 获取环保英知列表
     * @param homeParam 输入查询框查询条件 （查询"环保" 检索出所有title带环保字段的）
     * @param pageParam 分页条件 默认 获取第1页 查询999条数据
     * @return 新闻列表
     */
    @ResponseBody
    @GetMapping("/list")
    public ResultModel homeList(HomeParam homeParam, PageParam pageParam){
        List<Home> homes = homeService.homeList(homeParam, pageParam);
        logger.info("homeList HomeParam = {};PageParam = {}",
                JSON.toJSONString(homeParam), JSON.toJSONString(pageParam));
        return ResultModel.response(homes);
    }

    /**
     * 获取环保英知内容详情
     * @param id
     * @return 新闻列表
     */
    @GetMapping("/info/{id}")
    public ModelAndView homeIndex(@PathVariable Integer id, HttpServletRequest request){
        logger.info("home/index id = {}",id);
        Home home = homeService.getHomeIndex(id);
        logger.info("home/index home = {}",JSON.toJSONString(home));
        request.setAttribute("home", home);
        return new ModelAndView("home");
    }

    /**
     * 添加收藏(新闻)
     * @param userId 用户Id
     * @param homeId 首页信息id
     * @return true （收藏成功）
     */
    @ResponseBody
    @PutMapping(value = "/home/collect/{userId}/{homeId}")
    public ResultModel homeCollect(@PathVariable("userId") Integer userId, @PathVariable("homeId") Integer homeId){
        logger.info("homeCollect userId = {};homeId = {}",
                userId, homeId);
        homeService.addHomeCollect(userId, homeId);
        return ResultModel.response(true);
    }
}
