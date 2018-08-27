package com.bl.ep.service.impl;

import com.bl.ep.constant.ResultEnum;
import com.bl.ep.dao.*;
import com.bl.ep.interfaces.Collect;
import com.bl.ep.model.UserCollect;
import com.bl.ep.param.CollectParam;
import com.bl.ep.param.HomeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.UserParam;
import com.bl.ep.pojo.Home;
import com.bl.ep.pojo.HomeCollect;
import com.bl.ep.pojo.MerchandizeCollect;
import com.bl.ep.pojo.User;
import com.bl.ep.service.UserService;
import com.bl.ep.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;
    @Autowired
    private HomeMapper homeDao;
    @Autowired
    private HomeCollectMapper homeCollectMapper;
    @Autowired
    private HomeCollectCustomMapper homeCollectCustomMapper;
    @Autowired
    private MerchandizeCollectMapper merchandizeCollectMapper;
    @Autowired
    private MerchandizeCollectCustomMapper merchandizeCollectCustomMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User getUserByUsername(UserParam param) {
        User user = new User();
        user.setUsername(param.getUsername());
        return userDao.selectOne(user);
    }

    @Override
    public int signIn(UserParam param) {
        User user = userDao.selectOne(param);
        if(user.getPassword().equals(param.getPassword())){
            return 1;
        }
        return 2;
    }

    @Override
    public List<Home>   homeList(HomeParam param, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getRows());
        Example example = new Example(Home.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(param.getTitle())){
            criteria.andLike("title","%"+param.getTitle()+"%");
        }
        PageUtils.pageHelperOrderBy(example, pageParam);
        return homeDao.selectByExample(example);
    }

    @Override
    public void addMerchandizeCollect(Integer userId, Integer categoryId) {
        MerchandizeCollect collect = new MerchandizeCollect();
        collect.setUserId(userId);
        collect.setMerchandizeCategoryId(categoryId);
        collect.setIsDelete((byte)0);
        collect.setCreateTime(new Date());
        merchandizeCollectMapper.insertSelective(collect);
    }
    @Override
    public void addHomeCollect(Integer userId, Integer homeId) {
        HomeCollect collect = new HomeCollect();
        collect.setUserId(userId);
        collect.setHomeId(homeId);
        collect.setIsDelete((byte)0);
        collect.setCreateTime(new Date());
        homeCollectMapper.insertSelective(collect);
    }

    @Override
    public List<UserCollect> getUserCollects(CollectParam param) {
        List<UserCollect> userCollects = new ArrayList<>();

//        if(param==null || param .getCollectType()==null){
//            List<HomeCollect> homeCollects = homeCollectMapper.selectAll();
//            ArrayCopy(userCollects, homeCollects);
//            List<MerchandizeCollect> merchandizeCollects = merchandizeCollectMapper.selectAll();
//            ArrayCopy(userCollects, merchandizeCollects);
//        }else if(param.getCollectType().equals(ResultEnum.COLLECT_HOME.getKey())){
//            List<HomeCollect> homeCollects = homeCollectMapper.selectAll();
//            ArrayCopy(userCollects, homeCollects);
//        }else if(param.getCollectType().equals(ResultEnum.COLLECT_MERCHANDIZE.getKey())){
//            List<MerchandizeCollect> merchandizeCollects = merchandizeCollectMapper.selectAll();
//            ArrayCopy(userCollects, merchandizeCollects);
//        }
        return userCollects;
    }

//    private <T extends Collect> void ArrayCopy(List<UserCollect> src, List<T> dest){
//
//        if(dest == null || dest.size() == 0){
//            return;
//        }
//        if(dest.get(0) instanceof com.bl.ep.pojo.HomeCollect){
//            List<HomeCollect> list = (List<HomeCollect>)dest;
//            for (HomeCollect homeCollect : list) {
//                UserCollect userCollect = new UserCollect();
//                userCollect.setId(homeCollect.getId());
//                userCollect.setUserId(homeCollect.getUserId());
//                userCollect.setImage(homeCollect.get());
//                userCollect.setUserId(homeCollect.getUserId());
//                userCollect.setUserId(homeCollect.getUserId());
//
//            }
//        }
//        if(dest.get(0) instanceof com.bl.ep.pojo.MerchandizeCollect){
//            List<MerchandizeCollect> list = (List<MerchandizeCollect>)dest;
//            for (MerchandizeCollect merchandizeCollect : list) {
//                src.add(new UserCollect(
//                        merchandizeCollect.getId(), merchandizeCollect.getUserId(),merchandizeCollect.getMerchandizeCategoryId(),ResultEnum.COLLECT_MERCHANDIZE.getKey()
//                        ,merchandizeCollect.getModifyTime(),merchandizeCollect.getCreateTime()));
//            }
//        }
//    }
}
