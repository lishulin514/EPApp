package com.bl.ep.service.impl;

import com.bl.ep.domain.User;
import com.bl.ep.mapper.UserMapper;
import com.bl.ep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userDao;

    @Autowired
    public void setUserDao(UserMapper userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
}
