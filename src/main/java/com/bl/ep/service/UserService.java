package com.bl.ep.service;

import com.bl.ep.molds.Collect;
import com.bl.ep.config.Resource;
import com.bl.ep.constant.UserEnum;
import com.bl.ep.dao.*;
import com.bl.ep.model.UserCollect;
import com.bl.ep.model.UserModel;
import com.bl.ep.param.UserParam;
import com.bl.ep.pojo.HomeCollect;
import com.bl.ep.pojo.MerchandizeCollect;
import com.bl.ep.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private HomeCollectCustomMapper homeCollectCustomMapper;
    @Autowired
    private MerchandizeCollectCustomMapper merchandizeCollectCustomMapper;
    @Autowired
    private Resource resource;


    /**
     * 根据用户名获取用户信息
     * @param param 用户名
     * @return 用户信息
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public User getUserByUsername(UserParam param) {
        User user = new User();
        user.setUsername(param.getUsername());
        return userDao.selectOne(user);
    }

    /**
     * 判断对应用户的密码是否正确
     * @param param 用户名、密码
     * @return 用户信息
     */
    public UserModel signIn(UserParam param) {
        User user = userDao.selectOne(param);
        if(param != null && user !=null && user.getPassword().equals(param.getPassword())){
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(user, userModel);
            userModel.setHost(resource.getHost());
            userModel.setPort(resource.getPort());
            userModel.setImagePath(resource.getImagePath());
            return userModel;
        }
        return null;
    }

    /**
     * 根据类型获取收藏集合
     * @param collectType 类型
     * @return 收藏集合
     */
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

    /**
     *  讲一个集合中的信息复制到另一个集合
     * @param src  目标
     * @param dest 源头
     * @param <T> 信息集合类型
     */
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
                userCollect.setType(UserEnum.COLLECT_MERCHANDIZE.getKey());
                userCollect.setModifyTime(merchandizeCollect.getModifyTime());
                userCollect.setCreateTime(merchandizeCollect.getCreateTime());
                src.add(userCollect);
            }
        }
    }

    /**
     * 修改密码
     * @param userParam 用户名、原密码、新密码
     * @return 1、成功 2失败
     */
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

    /**
     * 注册
     * @param userParam 注册用户信息
     * @return 0、该用户已注册 1、注册成功
     */
    public int signUp(UserParam userParam) {
        User selectParam = new User();
        selectParam.setUsername(userParam.getUsername());
        User user = userDao.selectOne(selectParam);
        if(user==null){
            userParam.setCreateTime(new Date());
            userDao.insert(userParam);
            return UserEnum.SIGN_UP_SUCCESS.getKey();
        }
        return UserEnum.USER_EXIST.getKey();
    }

    public User getUserById(Integer userId) {

        return userDao.selectByPrimaryKey(userId);
    }
}
