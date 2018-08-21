package com.bl.ep.mapper;

import com.bl.ep.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public List<User> getUsers();

    public User getUserByUsername(String username);
}
