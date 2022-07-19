package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.request.BlogRequest;
import vn.techmaster.blog.request.BlogUpdateRequest;
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

    @PostMapping(value = "/api/admin/blogs")
    public ResponseEntity<?> createBlog(@RequestBody BlogRequest blogRequest){
        //TODO : ve sau userId se la id user dang dang nhap
        Integer userId = 1;

        //Tao blog
        Blog blog = blogService.createBlog(userId, blogRequest);

        // tra ve ket qua
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/admin/delete/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable("id") String id){
        blogService.deleteBlogById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/admin/blogs/detail/{id}")
    public String getBlogDetailPage(@PathVariable String id, Model model){
        model.addAttribute("blog", blogService.getBlogDtoById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/blog/blog-detail";
    }

    @PutMapping(value = "/api/admin/blog/update/{id}")
    public ResponseEntity<?> updateBlog(@RequestBody BlogUpdateRequest blogUpdateRequest, @PathVariable("id") String id){
        Blog blog = blogService.updateBlog(id, blogUpdateRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
