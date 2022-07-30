package com.example.exercise.controller;


import com.example.exercise.dto.CoursesInfo;
import com.example.exercise.entity.Course;
import com.example.exercise.entity.Topic;
import com.example.exercise.entity.User;
import com.example.exercise.service.CourseService;
import com.example.exercise.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Controller
public class WebController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TopicService topicService;

    @GetMapping("/")
    public String getHome(Model model,
                          @RequestParam(required = false, defaultValue = "") String keyword,
                          @RequestParam(required = false, defaultValue = "") String topic,
                          @RequestParam(required = false,defaultValue = "1") Integer page){

        Page<Course>  pageCourses = courseService.findAllPaging(page - 1,6,keyword, topic);

        int totalPages = pageCourses.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        List<Course> courses = pageCourses.getContent();
        model.addAttribute("courses", courses);

        List<Topic> topics = topicService.findAll();
        model.addAttribute("topics", topics);

        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("topic", topic);

        return "course/course-list";
    }


    @GetMapping(value = "/onlab-list")
    public String getOnlabListPage(Model model,
                                   @RequestParam(required = false, defaultValue = "") String keyword,
                                   @RequestParam(required = false, defaultValue = "") String topic,
                                   @RequestParam(required = false,defaultValue = "1") Integer page){

        Page<Course> pageCourses = courseService.findAllPagingByType(-1,6,keyword,0,topic);

        int totalPages = pageCourses.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        List<Course> courses = pageCourses.getContent();
        model.addAttribute("courses", courses);

        List<Topic> topics = topicService.findAll();
        model.addAttribute("topics", topics);

        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("topic", topic);

        return "course/course-onlab-list";
    }

    @GetMapping("/online-list")
    public String getOnlineListPage(Model model,
                                    @RequestParam(required = false, defaultValue = "") String keyword,
                                    @RequestParam(required = false, defaultValue = "") String topic,
                                    @RequestParam(required = false,defaultValue = "1") Integer page){

        Page<Course> pageCourses = courseService.findAllPagingByType(-1,6,keyword,1,topic);

        int totalPages = pageCourses.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        List<Course> courses = pageCourses.getContent();
        model.addAttribute("courses", courses);

        List<Topic> topics = topicService.findAll();
        model.addAttribute("topics", topics);

        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("topic", topic);

        return "course/course-online-list";
    }

    @GetMapping("/detail/{id}/{slug}")
    public String getDetailPage(@PathVariable("id") Integer id, Model model){
        User user =courseService.getUserByCourseId(id);
        model.addAttribute("user", user);

        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        return "course/detail";
    }

    @GetMapping("/test")
    public String getTest(){
        return "test";
    }
}
