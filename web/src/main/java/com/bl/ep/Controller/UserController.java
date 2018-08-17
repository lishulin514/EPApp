package com.bl.ep.Controller;


import com.bl.ep.domain.Resource;
import com.bl.ep.domain.User;
import com.bl.ep.repository.UserRepository;
import com.bl.ep.utils.ResultModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserRepository userRepository;

    private Resource resource;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @PostMapping("/person/save")
    public ResultModel save(@RequestParam String name){

        User user = new User();

        user.setName(name);

        System.out.println(userRepository.save(user)+"user:"+name);
        return ResultModel.ok(user);
    }

    @GetMapping("/getResource")
    public ResultModel getResource(){

        Resource bean = new Resource();
        BeanUtils.copyProperties(resource, bean);
        ResultModel ok = ResultModel.ok(bean);
        return ok;
    }

}
