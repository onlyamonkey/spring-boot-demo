package com.demo.springbootdemo.service.serviceimpl;

import com.demo.springbootdemo.service.TestService;
import com.demo.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private UserService userService;
    @Override
    @Transactional
    public void testTranction() {
        userService.insert();
        userService.addException();
        throw new RuntimeException("111");
    }

    @Override
    public void testNoTranction() {
        userService.insert();
        userService.addException();
    }
}
