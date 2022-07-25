package com.example.exercise.controller;

import com.example.exercise.entity.Course;
import com.example.exercise.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/page-{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") Integer currentPage){
        Page<Course> page = courseService.findPage(currentPage);
        int totalPage = page.getTotalPages();
        long totalItem = page.getTotalElements();
        List<Course> courses = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("courses", courses);
        return "/course/course-list";
    }

    @GetMapping("/")
    public String getAllPages(Model model){
        return getOnePage(model,1);
    }
}
