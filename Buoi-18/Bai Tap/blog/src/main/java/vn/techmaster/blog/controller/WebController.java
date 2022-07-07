package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.techmaster.blog.service.BlogService;

@Controller
public class WebController {


    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("blogs", blogService.getAllBlogInfo());
        model.addAttribute("blogsPopular", blogService.getBlogPopular(3));
        model.addAttribute("categoriesPopular", blogService.getCategoriesPopular(5));
        return "index";
    }

    @GetMapping("/blogs/{id}/{slug}")
    public String getDetailPage(@PathVariable String id, Model model){
        model.addAttribute("blog", blogService.getBlogInfoById(id));
        model.addAttribute()
        return "detail";
    }

    @GetMapping("/about")
    public String getAboutPage(){
        return "about";
    }

    @GetMapping("/contact")
    public String getContactPage(){
        return "contact";
    }
}
