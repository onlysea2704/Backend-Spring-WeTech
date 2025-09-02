package com.wetech.backend_spring_wetech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Test {

    @GetMapping("/admin/test")
    public String testAdminApi(){
        return "test api admin thành công";
    }

    @GetMapping("/user/test")
    public String testUserApi(){
        return "test api user thành công";
    }

    @GetMapping("/auth/test")
    public String testAuthApi(){
        return "test api auth thành công";
    }

}
