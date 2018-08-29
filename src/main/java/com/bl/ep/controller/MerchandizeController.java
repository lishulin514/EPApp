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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MerchandizeController {

    private final Logger logger = LoggerFactory.getLogger(MerchandizeController.class);

    @Autowired
    private MerchandizeService merchandizeService;

    /**
     * 商品列表
     * @param merchandizeParam 输入查询框查询条件 （查询"环保" 检索出所有title带环保字段的）
     * @param pageParam 分页条件 默认 获取第1页 查询999条数据
     * @return 商品列表
     */
    @PostMapping("/merchandize/list")
    public ResultModel merchandizeList(MerchandizeParam merchandizeParam, PageParam pageParam){
        logger.info("merchandizeList MerchandizeParam = {} ；PageParam = {}", JSON.toJSONString(merchandizeParam), JSON.toJSONString(pageParam));
        List<Merchandize> merchandizes = merchandizeService.merchandizeList(merchandizeParam, pageParam);
        return ResultModel.response(merchandizes);
    }

    /**
     * 商品信息
     * @param merchandizeId 商品id
     * @return 商品信息
     */
    @PostMapping(value = "/merchandize/info/{merchandizeId}")
    public ResultModel merchandizeInfo(@PathVariable Integer merchandizeId){
        logger.info("merchandizeInfo merchandizeId = {}", merchandizeId);
        Merchandize merchandize = merchandizeService.getMerchandizeInfoById(merchandizeId);
        return ResultModel.response(merchandize);
    }

    /**
     * 获取商品种类
     * @param merchandizeId 商品id
     * @return 商品种类列表
     */
    @PostMapping(value = "/merchandize/category/list/{merchandizeId}")
    public ResultModel merchandizeCategory(@PathVariable Integer merchandizeId){
        logger.info("merchandizeCategory merchandizeId = {}", merchandizeId);
        List<MerchandizeCategory> details = merchandizeService.getMerchandizeCategory(merchandizeId);
        return ResultModel.response(details);
    }
}
