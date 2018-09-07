package com.bl.ep.service;

import com.bl.ep.dao.HomeCollectCustomMapper;
import com.bl.ep.dao.HomeCollectMapper;
import com.bl.ep.dao.HomeMapper;
import com.bl.ep.param.HomeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.pojo.Home;
import com.bl.ep.pojo.HomeCollect;
import com.bl.ep.pojo.Request;
import com.bl.ep.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author： 李树林
 * @description：
 * @date： 2018/9/4 14:22
 */
@Service
public class HomeService {

    @Autowired
    private HomeMapper homeDao;
    @Autowired
    private HomeCollectMapper homeCollectMapper;

    /**
     * 根据分页条件获取新闻列表
     * @param param 首页查询条件
     * @param pageParam 分页条件
     * @return 首页列表
     */
    public List<Home> homeList(HomeParam param, PageParam pageParam) {
        //设置分页开始和分页条数
        Example example = PageUtils.getExample(pageParam, Home.class);
        if(!StringUtils.isEmpty(param.getTitle())){
            example.createCriteria().andLike("title","%"+param.getTitle()+"%");
        }
        return homeDao.selectByExample(example);
    }

    /**
     * 收藏新闻
     * @param userId 用户Id
     * @param homeId 新闻Id
     */
    public void addHomeCollect(Integer userId, Integer homeId) {
        HomeCollect collect = new HomeCollect();
        collect.setUserId(userId);
        collect.setHomeId(homeId);
        collect.setIsDelete((byte)0);
        collect.setCreateTime(new Date());
        homeCollectMapper.insertSelective(collect);
    }

    public Home getHomeIndex(Integer id) {

        return homeDao.selectByPrimaryKey(id);
    }
}
