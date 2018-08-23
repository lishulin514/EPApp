package com.bl.ep.service.impl;

import com.bl.ep.dao.UserMapperCustom;
import com.bl.ep.pojo.User;
import com.bl.ep.dao.UserMapper;
import com.bl.ep.service.UserService;
import com.bl.ep.utils.PageParam;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;

    @Autowired
    private UserMapperCustom userDaoCustom;



    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userDao.selectOne(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> getUserList(PageParam param, User user) {
        PageHelper.startPage(param.getPage(),param.getRows());
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmptyOrWhitespace(user.getRealname())){
            criteria.andLike("nickname", "%" + user.getRealname() + "%");
        }
        example.orderBy("createTime").desc();
        return userDao.selectByExample(example);
    }

    @Override
    public void createUser(User user){

        userDao.insert(user);
    }

    @Override
    public void getUserById(Integer userId){

        userDaoCustom.getUserById(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void testTransactional(User user){
        int insert = userDao.insert(user);
        user.setId(9);
        user.setIsDelete(1);
        userDao.updateByPrimaryKey(user);
    }
}
