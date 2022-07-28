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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

        return "redirect:/dashboard";
    }



    @GetMapping("/dashboard/edit/{id}")
    public String getEditPage(Model model, @PathVariable("id") Integer id){
        Optional<Course> course = courseService.findById(id);
        model.addAttribute("course", course);


        return "/admin/course-edit";
    }

}
