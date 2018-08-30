package com.bl.ep.service;

import com.bl.ep.constant.Collect;
import com.bl.ep.constant.Resource;
import com.bl.ep.constant.UserEnum;
import com.bl.ep.dao.*;
import com.bl.ep.model.UserCollect;
import com.bl.ep.model.UserModel;
import com.bl.ep.param.HomeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.UserParam;
import com.bl.ep.pojo.Home;
import com.bl.ep.pojo.HomeCollect;
import com.bl.ep.pojo.MerchandizeCollect;
import com.bl.ep.pojo.User;
import com.bl.ep.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserService {

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
    @Autowired
    private Resource resource;


    @Transactional(propagation = Propagation.SUPPORTS)
    public User getUserByUsername(UserParam param) {
        User user = new User();
        user.setUsername(param.getUsername());
        return userDao.selectOne(user);
    }

    public UserModel signIn(UserParam param) {
        User user = userDao.selectOne(param);
        if(user.getPassword().equals(param.getPassword())){
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(user, userModel);
            userModel.setHost(resource.getHost());
            userModel.setPort(resource.getPort());
            userModel.setImagePath(resource.getImagePath());
            return userModel;
        }
        return null;
    }

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

    public void addMerchandizeCollect(Integer userId, Integer categoryId) {
        MerchandizeCollect collect = new MerchandizeCollect();
        collect.setUserId(userId);
        collect.setMerchandizeCategoryId(categoryId);
        collect.setIsDelete((byte)0);
        collect.setCreateTime(new Date());
        merchandizeCollectMapper.insertSelective(collect);
    }
    public void addHomeCollect(Integer userId, Integer homeId) {
        HomeCollect collect = new HomeCollect();
        collect.setUserId(userId);
        collect.setHomeId(homeId);
        collect.setIsDelete((byte)0);
        collect.setCreateTime(new Date());
        homeCollectMapper.insertSelective(collect);
    }

    public List<UserCollect> getUserCollects(Integer collectType) {
        List<UserCollect> userCollects = new ArrayList<>();

        if(collectType==null){
            List<HomeCollect> homeCollects = homeCollectCustomMapper.getHomeCollect();
            ArrayCopy(userCollects, homeCollects);
            List<MerchandizeCollect> merchandizeCollects = merchandizeCollectCustomMapper.getMerchandizeCollect();
            ArrayCopy(userCollects, merchandizeCollects);
        }else if(collectType.equals(UserEnum.COLLECT_HOME.getKey())){
            List<HomeCollect> homeCollects = homeCollectCustomMapper.getHomeCollect();
            ArrayCopy(userCollects, homeCollects);
        }else if(collectType.equals(UserEnum.COLLECT_MERCHANDIZE.getKey())){
            List<MerchandizeCollect> merchandizeCollects = merchandizeCollectCustomMapper.getMerchandizeCollect();
            ArrayCopy(userCollects, merchandizeCollects);
        }
        return userCollects;
    }

    private <T extends Collect> void ArrayCopy(List<UserCollect> src, List<T> dest){
        if(dest == null || dest.size() == 0){
            return;
        }
        if(dest.get(0) instanceof com.bl.ep.pojo.HomeCollect){
            List<HomeCollect> list = (List<HomeCollect>)dest;
            for (HomeCollect homeCollect : list) {
                UserCollect userCollect = new UserCollect();
                userCollect.setId(homeCollect.getId());
                userCollect.setUserId(homeCollect.getUserId());
                userCollect.setTargetId(homeCollect.getHomeId());
                userCollect.setTitle(homeCollect.getTitle());
                userCollect.setImage(homeCollect.getImage());
                userCollect.setUrl(homeCollect.getUrl());
                userCollect.setType(UserEnum.COLLECT_HOME.getKey());
                userCollect.setModifyTime(homeCollect.getModifyTime());
                userCollect.setCreateTime(homeCollect.getCreateTime());
                src.add(userCollect);
            }
        }
        if(dest.get(0) instanceof com.bl.ep.pojo.MerchandizeCollect){
            List<MerchandizeCollect> list = (List<MerchandizeCollect>)dest;
            for (MerchandizeCollect merchandizeCollect : list) {
                UserCollect userCollect = new UserCollect();
                userCollect.setId(merchandizeCollect.getId());
                userCollect.setUserId(merchandizeCollect.getUserId());
                userCollect.setTargetId(merchandizeCollect.getMerchandizeCategoryId());
                userCollect.setTitle(merchandizeCollect.getTitle());
                userCollect.setImage(merchandizeCollect.getImage());
                userCollect.setUrl(merchandizeCollect.getUrl());
                userCollect.setType(UserEnum.COLLECT_MERCHANDIZE.getKey());
                userCollect.setModifyTime(merchandizeCollect.getModifyTime());
                userCollect.setCreateTime(merchandizeCollect.getCreateTime());
                src.add(userCollect);
            }
        }
    }

    public int updatePassword(UserParam userParam) {
        User selectParam = new User();
        selectParam.setUsername(userParam.getUsername());
        User user = userDao.selectOne(selectParam);
        if(user.getPassword().equals(userParam.getPassword())){
            User updateUser = new User();
            updateUser.setId(user.getId());
            updateUser.setPassword(userParam.getNewPassword());
            userDao.updateByPrimaryKeySelective(updateUser);
            return UserEnum.UPDATE_PASSWORD_SUCCESS.getKey();
        }
        return UserEnum.UPDATE_PASSWORD_FAILED.getKey();
    }

    public int signUp(UserParam userParam) {
        User selectParam = new User();
        selectParam.setUsername(userParam.getUsername());
        User user = userDao.selectOne(selectParam);
        if(user==null){
            userDao.insert(userParam);
            return UserEnum.SIGN_UP_SUCCESS.getKey();
        }
        return UserEnum.USER_EXIST.getKey();
    }
}
