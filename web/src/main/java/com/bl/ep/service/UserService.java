package com.bl.ep.service;

import com.bl.ep.pojo.User;
import com.bl.ep.utils.PageParam;

import java.util.List;

public interface UserService {

    public User getUserByUsername(String username);

    List<User> getUserList(PageParam param, User user);

    void createUser(User user);

    void getUserById(Integer userId);

    void testTransactional(User user);
}
