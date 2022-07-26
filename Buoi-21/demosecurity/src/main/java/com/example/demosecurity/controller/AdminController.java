package com.example.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/blogs")
    public String getHome(){
        return "blog";
    }

    @GetMapping("/admin/users")
    public String getProfilePage(){
        return "user";
    }

}
