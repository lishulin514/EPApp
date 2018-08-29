package com.bl.ep;

import com.bl.ep.dao.UserMapper;
import com.bl.ep.model.UserModel;
import com.bl.ep.param.UserParam;
import com.bl.ep.pojo.User;
import com.bl.ep.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EpAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserMapperTest {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void getUsers() {
        UserParam param = new UserParam();
        param.setUsername("lishulin");
        User users = userService.getUserByUsername(param);
        System.out.println(users.toString());
    }

    @Test
    public void TestCopyArray() {
       userService.getUserCollects(null);
    }

    @Test
    public void login(){
        UserParam user = new UserParam();
        user.setUsername("lishulin");
        user.setPassword("123");
        UserModel userModel = userService.signIn(user);
    }
}