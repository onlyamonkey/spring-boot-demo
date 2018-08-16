package com.demo.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("index");
    }
    @GetMapping("/user")
    public ModelAndView user(){
        return new ModelAndView("user");
    }
}
