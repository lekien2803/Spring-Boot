package com.example.exercise.controller;

import com.example.exercise.entity.Course;
import com.example.exercise.entity.Topic;
import com.example.exercise.repository.TopicRepository;
import com.example.exercise.request.CourseRequest;
import com.example.exercise.service.CourseService;
import com.example.exercise.service.TopicService;
import com.example.exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;


    @GetMapping("/dashboard")
    public String getDashboardPage(Model model,
            @RequestParam(required = false,defaultValue = "1") Integer page
    ){
        Page<Course> coursePage = courseService.findAllDashboard(page - 1,10);

        List<Course> courses = coursePage.getContent();
        model.addAttribute("courses", courses);

        int totalPages = coursePage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        model.addAttribute("currentPage", page);
        model.addAttribute("courseService",courseService);

        return "/admin/course-list";
    }

    @GetMapping("/dashboard/create")
    public String getCreatePage(Model model){
        model.addAttribute("course", new Course());
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("users", userService.findAll());
        return "/admin/course-create";
    }

    @PostMapping("/dashboard/create")
    public String createCrouse (@ModelAttribute Course course, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/admin/course-create";
        }

        courseService.createCrouse(course);

        return "redirect:/admin/course-list";
    }

    @GetMapping("/dashboard/edit")
    public String getEditPage(){



        return "/admin/course-edit";
    }

}
