package com.demo.springbootdemo.service.serviceimpl;

import com.demo.springbootdemo.entry.User;
import com.demo.springbootdemo.mapper.UserMapper;
import com.demo.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void test() {
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert() {
        User user = new User();
        user.setAge(1);
        user.setName("djh");
        user.setSex(1);
        userMapper.insert(user);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addException() {
        User user = new User();
        user.setAge(1);
        user.setName("wbj");
        user.setSex(2);
        userMapper.insert(user);
        throw new RuntimeException();
    }
}
