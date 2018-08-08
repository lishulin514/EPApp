package com.bl.ep.EPApp.repository;

import com.bl.ep.EPApp.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User}  {@link Repository}
 */
@Repository
public class UserRepository {

    private final ConcurrentHashMap<Integer, User>  rep = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator = new AtomicInteger();

    public boolean save(User user){

        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return rep.put(id, user) == null;
    }

    public Collection<User> findAll() {

        return rep.values();
    }
}
