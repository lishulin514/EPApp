package com.bl.ep.service;

import com.bl.ep.dao.AnswerMapper;
import com.bl.ep.dao.RequestMapper;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.ReqParam;
import com.bl.ep.pojo.Answer;
import com.bl.ep.pojo.Request;
import com.bl.ep.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/9/4 14:38
 */
@Service
public class RequestService {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AnswerMapper answerMapper;

    public List<Request> requestList(ReqParam requestParam, PageParam pageParam) {

        Example example = PageUtils.getExample(pageParam, Request.class);
        if(!StringUtils.isEmpty(requestParam.getTitle())){
            example.createCriteria().andLike("title","%"+requestParam.getTitle()+"%");
        }
        return requestMapper.selectByExample(example);
    }

    public List<Answer> answerList(Integer requestId) {
        Answer answer = new Answer();
        answer.setRequestId(requestId);
        return answerMapper.select(answer);
    }

    public int saveRequest(Request request) {
        request.setCreateTime(new Date());
        return requestMapper.insert(request);
    }

    public int saveAnswer(Answer answer) {
        answer.setCreateTime(new Date());
        return answerMapper.insert(answer);
    }
}
