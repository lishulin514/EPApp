package com.bl.ep.controller;

import com.alibaba.fastjson.JSON;
import com.bl.ep.constant.ResultModel;
import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.pojo.MerchandizeCategory;
import com.bl.ep.service.MerchandizeService;
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


    /**
     * 添加收藏（商品）
     * @param userId 用户id
     * @param categoryId 收藏的商品种类id
     * @return true （收藏成功）
     */
    @ResponseBody
    @PutMapping(value = "/collect/{userId}/{categoryId}")
    public ResultModel merchandizeCollect(@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId){
        merchandizeService.addMerchandizeCollect(userId, categoryId);
        logger.info("merchandizeCollect userId = {};categoryId = {}",
                userId, categoryId);
        return ResultModel.response(true);
    }
}
