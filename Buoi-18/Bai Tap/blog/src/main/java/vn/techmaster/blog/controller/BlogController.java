package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.request.BlogRequest;
import vn.techmaster.blog.service.BlogService;
import vn.techmaster.blog.service.CategoryService;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;


    // Danh sach tat ca bai viet
    @GetMapping("/admin/blogs")
    public String getBlogPage(Model model){
        // TODO: bo sung them phan trang
        model.addAttribute("blogs", blogService.getAllBlogDto());
        return "admin/blog/blog-index";
    }

    // Danh sach blog ca nhan
    @GetMapping("/admin/blogs/own-blog")
    public String getOwnBlogPage(Model model){
        //TODO : Ve sau userId se la id cua user dang dang nhap
        Integer userId = 1;
        model.addAttribute("blogs", blogService.getAllBlogDtoByUserId(userId));
        return "admin/blog/blog-yourself";
    }

    @GetMapping("/admin/blogs/create")
    public String getBlogCreatePage(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/blog/blog-create";
    }

    @PostMapping(value = "/admin/blogs/create")
    public String createBlog(@RequestBody BlogRequest blogRequest){
        blogService.createBlog(blogRequest);
        return "redirect:/admin/blog/blog-create";
    }

    @GetMapping("/admin/blogs/{id}/detail")
    public String getBlogDetailPage(@PathVariable String id){
        return "admin/blog/blog-detail";
    }
}
