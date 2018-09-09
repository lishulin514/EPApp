package com.bl.ep.controller;

import com.alibaba.fastjson.JSON;
import com.bl.ep.config.Resource;
import com.bl.ep.constant.ResultModel;
import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.pojo.MerchandizeCategory;
import com.bl.ep.pojo.User;
import com.bl.ep.service.MerchandizeService;
import com.bl.ep.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/merchandize")
public class MerchandizeController {

    private final Logger logger = LoggerFactory.getLogger(MerchandizeController.class);

    @Autowired
    private MerchandizeService merchandizeService;

    @Autowired
    private UserService userService;

    @Autowired
    private Resource resource;

    /**
     * 环保物流列表
     * @param merchandizeParam 输入查询框查询条件 （查询"环保" 检索出所有title带环保字段的）
     * @param pageParam 分页条件 默认 获取第1页 查询999条数据
     * @return 商品列表
     */
    @ResponseBody
    @GetMapping("/list")
    public ResultModel merchandizeList(MerchandizeParam merchandizeParam, PageParam pageParam){
        logger.info("merchandizeList MerchandizeParam = {} ；PageParam = {}", JSON.toJSONString(merchandizeParam), JSON.toJSONString(pageParam));
        List<Merchandize> merchandizes = merchandizeService.merchandizeList(merchandizeParam, pageParam);
        return ResultModel.response(merchandizes);
    }

    /**
     * 环保物流详情
     * @param merchandizeId 商品id
     * @return 商品信息
     */
    @GetMapping(value = "/info/{merchandizeId}")
    public ModelAndView merchandizeInfo(@PathVariable Integer merchandizeId, HttpServletRequest request){
        logger.info("merchandizeInfo merchandizeId = {}", merchandizeId);
        Merchandize merchandize = merchandizeService.getMerchandizeInfoById(merchandizeId);
        if(!StringUtils.isEmpty(merchandize.getContentImage())){
            String path = /*resource.getHost()+":"+resource.getPort()+*/"/"+resource.getImagePath()+"/"+merchandize.getContentImage();
            merchandize.setContentImage(path);
        }
        request.setAttribute("merchandize", merchandize);
        return new ModelAndView("merchandize");
    }

    /**
     * 获取商品种类
     * @param merchandizeId 商品id
     * @return 商品种类列表
     */
    @ResponseBody
    @PostMapping(value = "/category/list/{merchandizeId}")
    public ResultModel merchandizeCategory(@PathVariable Integer merchandizeId){
        logger.info("merchandizeCategory merchandizeId = {}", merchandizeId);
        List<MerchandizeCategory> details = merchandizeService.getMerchandizeCategory(merchandizeId);
        return ResultModel.response(details);
    }


    @ResponseBody
    @PostMapping(value = "/create", params = {"userId", "title", "telephone", "content"})
    public ResultModel createMerchandize (Integer userId, Merchandize merchandize){
        logger.info("createMerchandize merchandize = {}", merchandize);
        User user = userService.getUserById(userId);
        merchandizeService.createMerchandize(user, merchandize);
        return ResultModel.response(true);
    }

    /**
     * 添加收藏（商品）
     * @param userId 用户id
     * @param merchandize 收藏的商品种类id
     * @return true （收藏成功）
     */
    @ResponseBody
    @PostMapping(value = "/collect/{userId}/{merchandize}")
    public ResultModel merchandizeCollect(@PathVariable("userId") Integer userId, @PathVariable("merchandize") Integer merchandize){
        merchandizeService.addMerchandizeCollect(userId, merchandize);
        logger.info("merchandizeCollect userId = {};merchandize = {}",
                userId, merchandize);
        return ResultModel.response(true);
    }
}
