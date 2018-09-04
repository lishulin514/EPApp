package com.bl.ep.controller;

import com.alibaba.fastjson.JSON;
import com.bl.ep.constant.ResultModel;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.RequestParam;
import com.bl.ep.pojo.Request;
import com.bl.ep.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/9/4 14:31
 */
@Controller
@RequestMapping("/request")
public class RequestController {

    private final Logger logger = LoggerFactory.getLogger(RequestController.class);
    @Autowired
    private RequestService requestService;

    /**
     * 获取达人问答列表
     * @param homeParam 输入查询框查询条件 （查询"环保" 检索出所有title带环保字段的）
     * @param pageParam 分页条件 默认 获取第1页 查询999条数据
     * @return 新闻列表
     */
    @ResponseBody
    @GetMapping("/list")
    public ResultModel requestList(RequestParam requestParam, PageParam pageParam){
        List<Request> requests = requestService.requestList(requestParam, pageParam);
        logger.info("homeList requestParam = {};PageParam = {}",
                JSON.toJSONString(requestParam), JSON.toJSONString(pageParam));
        return ResultModel.response(requests);
    }
}
