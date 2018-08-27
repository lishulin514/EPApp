package com.bl.ep.controller;

import com.bl.ep.constant.ResultModel;
import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.pojo.MerchandizeCategory;
import com.bl.ep.service.MerchandizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MerchandizeController {

    @Autowired
    private MerchandizeService merchandizeService;

    @PostMapping("/merchandize/list")
    public ResultModel merchandizeList(MerchandizeParam param, PageParam pageParam){

        List<Merchandize> merchandizes = merchandizeService.merchandizeList(param, pageParam);
        return ResultModel.response(merchandizes);
    }
    @PostMapping("/merchandize/info/merchandizeId}")
    public ResultModel merchandizeInfo(@PathVariable Integer merchandizeId){

        Merchandize merchandize = merchandizeService.getMerchandizeInfoById(merchandizeId);
        return ResultModel.response(merchandize);
    }

    @PostMapping("/merchandize/category/list/{merchandizeId}")
    public ResultModel merchandizeCategory(@PathVariable Integer merchandizeId){

        List<MerchandizeCategory> details = merchandizeService.getMerchandizeCategory(merchandizeId);
        return ResultModel.response(details);
    }
}
