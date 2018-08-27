package com.bl.ep.controller;

import com.bl.ep.constant.ResultModel;
import com.bl.ep.param.HomeParam;
import com.bl.ep.param.MerchandizeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Home;
import com.bl.ep.pojo.Merchandize;
import com.bl.ep.service.MerchandizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
