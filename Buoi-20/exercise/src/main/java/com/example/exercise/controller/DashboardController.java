package com.example.exercise.controller;

import com.example.exercise.entity.Course;
import com.example.exercise.entity.Topic;
import com.example.exercise.repository.TopicRepository;
import com.example.exercise.service.CourseService;
import com.example.exercise.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private TopicService topicService;


    @GetMapping("/dashboard")
    public String getDashboardPage(Model model,
            @RequestParam(required = false,defaultValue = "1") Integer page
    ){
        Page<Course> coursePage = courseService.findAllDashboard();

        List<Course> courses = coursePage.getContent();
        model.addAttribute("courses", courses);

        model.addAttribute("courseService",courseService);


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
