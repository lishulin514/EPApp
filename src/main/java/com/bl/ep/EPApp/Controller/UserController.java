package com.bl.ep.EPApp.Controller;

import com.bl.ep.EPApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bl.ep.EPApp.repository.UserRepository;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name){

        User user = new User();

        user.setName(name);

        System.out.println(userRepository.save(user)+"user:"+name);
        return user;
    }

}
