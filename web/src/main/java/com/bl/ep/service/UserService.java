package com.bl.ep.service;

import com.bl.ep.param.HomeParam;
import com.bl.ep.param.PageParam;
import com.bl.ep.param.UserParam;
import com.bl.ep.pojo.Home;
import com.bl.ep.pojo.User;

import java.util.List;

public interface UserService {

    public User getUserByUsername(UserParam param);

    public List<Home> homeList(HomeParam param, PageParam pageParam);

    public int signIn(UserParam param);
}
