package com.example.exercise.controller;

import com.example.exercise.controller.respone.Paging;
import com.example.exercise.dto.CoursesInfo;
import com.example.exercise.entity.Course;
import com.example.exercise.entity.User;
import com.example.exercise.service.CourseService;
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
    @GetMapping("/")
    public String getHome(Model model,
                          @RequestParam(required = false, defaultValue = "") String keyword,
                          @RequestParam(required = false, defaultValue = "") Integer topic,
                          @RequestParam(required = false,defaultValue = "") Integer page){
        if (page == null){
            page = 0;
        }
        Page<Course> pageCourses = courseService.findAllPaging(page,6,keyword,topic);

        List<Course> courses = pageCourses.getContent();
        model.addAttribute("courses", courses);

        List<Paging> pagings = Paging.generatePages(page, pageCourses.getTotalPages());
        model.addAttribute("pagings", pagings);

        return "course/course-list";
    }

    @GetMapping(value = "/search")
    public String getHomeSearch(Model model, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword){
//        Page<Course> pageCourses = courseService.getByNameContainsIgnoreCase(keyword, PageRequest.of(page,6));
        return "course/course-list";
    }

    @GetMapping(value = "/onlab-list")
    public String getOnlabListPage(Model model, @RequestParam(value = "page", required = false) Integer page){
        if (page == null){
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 6);
        Page<Course> pageCourses = courseService.getByType(0, pageable);

        List<Course> courses = pageCourses.getContent();
        model.addAttribute("courses", courses);

        List<Paging> pagings = Paging.generatePages(page, pageCourses.getTotalPages());
        model.addAttribute("pagings", pagings);

        return "/course/course-onlab-list";
    }

    @GetMapping("/online-list")
    public String getOnlineListPage(Model model, @RequestParam(value = "page", required = false) Integer page){
        if (page == null){
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 6);
        Page<Course> pageCourses = courseService.getByType(1, pageable);

        List<Course> courses = pageCourses.getContent();
        model.addAttribute("courses", courses);

        List<Paging> pagings = Paging.generatePages(page, pageCourses.getTotalPages());
        model.addAttribute("pagings", pagings);
        return "/course/course-online-list";
    }

    @GetMapping("/detail/{id}/{slug}")
    public String getDetailPage(@PathVariable("id") Integer id, Model model){
        User user =courseService.getUserByCourseId(id);
        model.addAttribute("user", user);

        Optional<Course> course = courseService.findById(id);
        model.addAttribute("course", course);
        return "/course/detail";
    }
}
