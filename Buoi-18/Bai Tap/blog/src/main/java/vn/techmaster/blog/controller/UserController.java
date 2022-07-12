package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.techmaster.blog.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public String getUserPage(Model model){
        model.addAttribute("users", userService.getAllUserDto());
        return "/admin/user/user-index";
    }

    @GetMapping("/admin/users/{id}/detail")
    public String getUserDetailPage(){

        return "admin/user/user-detail";
    }

    @GetMapping("/admin/users/add-user")
    public String getAddUserPage(){
        return "/admin/user/user-add";
    }
}
