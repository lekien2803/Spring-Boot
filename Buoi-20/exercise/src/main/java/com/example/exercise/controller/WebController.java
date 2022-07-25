package com.example.exercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {
//    @GetMapping("/")
//    public String getCourseListPage(){
//        return "/course/course-list";
//    }

    @GetMapping("/onlab-list")
    public String getOnlabListPage(){
        return "/course/course-onlab-list";
    }

    @GetMapping("/online-list")
    public String getOnlineListPage(){
        return "/course/course-online-list";
    }

    @GetMapping("/detail/{id}/{slug}")
    public String getDetailPage(@PathVariable("id") Integer id, Model model){
        return "/course/detail";
    }
}
