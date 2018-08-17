package com.demo.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LoginController {
    @Autowired
    private HttpServletRequest request;
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    @GetMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
    @PostMapping("/loginval")
    @ResponseBody
    public String loginval(){
        return "success";
    }
    @GetMapping("/user")
    public ModelAndView user(){
        String id =request.getSession().getId();
        System.err.print("第一次"+id);
        return new ModelAndView("user");
    }

    @PostMapping("/data")
    @ResponseBody
    public Map<String ,Object>  data(){
        String id =request.getSession().getId();
        System.err.print("第二次"+id);
        Map<String ,Object> result = new HashMap<String ,Object>();
        List<Map<String ,Object>> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            Map<String ,Object> data = new HashMap<String ,Object>();
            data.put("id",i);
            data.put("name","item"+i);
            data.put("price",i);
            list.add(data);
        }
        result.put("total",list.size());
        result.put("rows",list);
        return result;
    }
}
