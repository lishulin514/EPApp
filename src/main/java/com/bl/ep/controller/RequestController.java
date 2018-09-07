package com.bl.ep.controller;

import com.bl.ep.constant.ResultEnum;
import com.bl.ep.constant.ResultModel;
import com.bl.ep.dao.AnswerMapper;
import com.bl.ep.model.AnswerModel;
import com.bl.ep.model.RequestModel;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.ReqParam;
import com.bl.ep.pojo.Answer;
import com.bl.ep.pojo.Request;
import com.bl.ep.pojo.User;
import com.bl.ep.service.RequestService;
import com.bl.ep.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private UserService userService;

    /**
     * 获取达人问答列表
     * @param requestParam
     * @param pageParam 分页条件 默认 获取第1页 查询999条数据
     * @return 新闻列表
     */
    @ResponseBody
    @GetMapping("/list")
    public ResultModel requestList(@RequestParam Integer userId, ReqParam requestParam, PageParam pageParam){

        List<Request> requests = requestService.requestList(requestParam, pageParam);
        List<RequestModel> models = new ArrayList<>();
        for (Request request : requests) {
            RequestModel requestModel = new RequestModel();
            BeanUtils.copyProperties(request, requestModel);
            if(userId.equals(request.getWriterId())){
                requestModel.setMyRequest(true);
            }
            models.add(requestModel);
        }

        return ResultModel.response(models);
    }

    /**
     * 上传《问题》
     * @param userId
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/put")
    public ResultModel putRequest(@RequestParam Integer userId, ReqParam request){
        User user = userService.getUserById(userId);
        if(user != null){
            request.setWriterId(user.getId());
            request.setWriter(user.getUsername());
            int isSave = requestService.saveRequest(request);
            return ResultModel.response(isSave);
        }

        return ResultModel.error(ResultEnum.NONO_USER);
    }

    /**
     * 获取回答列表
     * @param requestId
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("/answer/list")
    public ResultModel answerList(@RequestParam Integer requestId, @RequestParam Integer userId){

        List<Answer> answers = requestService.answerList(requestId);
        List<AnswerModel> models = new ArrayList<>();
        for (Answer answer : answers) {
            AnswerModel answerModel = new AnswerModel();
            BeanUtils.copyProperties(answer, answerModel);
            if(userId.equals(answer.getWriterId())){
                answerModel.setMyRequest(true);
            }
            models.add(answerModel);
        }
        return ResultModel.response(models);
    }

    /**
     * 上传<回答>
     * @param userId
     * @param answer
     * @return
     */
    @ResponseBody
    @PostMapping("/answer/put")
    public ResultModel putAnswer(@RequestParam Integer userId, @RequestParam Integer requestId, Answer answer){
        User user = userService.getUserById(userId);
        if(user != null){
            answer.setWriterId(user.getId());
            answer.setWriter(user.getUsername());
            answer.setRequestId(requestId);
            int save = requestService.saveAnswer(answer);
            return ResultModel.response(save);
        }
        return ResultModel.error(ResultEnum.NONO_USER);
    }
}
