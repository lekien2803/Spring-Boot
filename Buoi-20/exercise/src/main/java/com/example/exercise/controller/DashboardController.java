package com.example.exercise.controller;

import com.example.exercise.entity.Course;
import com.example.exercise.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    @Autowired
    private CourseService courseService;

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

    @GetMapping("/api/course")
    public ResponseEntity<?> getTest(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword){
        Page<Course> coursePage = courseService.getByNameContainsIgnoreCase(keyword, PageRequest.of(0,6));

        return ResponseEntity.ok(coursePage);
    }
}
