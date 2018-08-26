package com.bl.ep.service.impl;

import com.bl.ep.dao.HomeMapper;
import com.bl.ep.dao.UserMapper;
import com.bl.ep.param.HomeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.UserParam;
import com.bl.ep.pojo.Home;
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

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;
    @Autowired
    private HomeMapper homeDao;


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
}
