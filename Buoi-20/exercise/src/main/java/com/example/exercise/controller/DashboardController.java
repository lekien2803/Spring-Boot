package com.example.exercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String getDashboardPage(){
        return "/admin/course-list";
    }

    @GetMapping("/dashboard/create")
    public String getCreatePage(){
        return "/admin/course-create";
    }

    @GetMapping("/dashboard/edit")
    public String getEditPage(){
        return "/admin/course-edit";
    }

}
