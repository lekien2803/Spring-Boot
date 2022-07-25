package com.example.exercise.controller;

import com.example.exercise.controller.respone.Paging;
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
public class WebController {
    @Autowired
    private CourseService courseService;
    @GetMapping(value = {"/", "/{page}"})
    public String getHome(Model model, @PathVariable(value = "page", required = false) Integer page){
        if (page == null){
            page = 0;
        }
        Page<Course> pageCourses = courseService.findAllPaging(page, 6);

        List<Course> courses = pageCourses.getContent();
        model.addAttribute("courses", courses);

        List<Paging> pagings = Paging.generatePages(page, pageCourses.getTotalPages());
        model.addAttribute("pagings", pagings);

        return "course/course-list";
    }

    @GetMapping("/onlab-list")
    public String getOnlabListPage(Model model){
        List<Course> courses = courseService.getCoursesOnlab();
        model.addAttribute("courses", courses);
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
