package com.example.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.user.service.UserService;

@Controller
public class UserViewController {
    @Autowired private UserService userService;
    @GetMapping("/")
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/create")
    public String getCreatePage() {
        return "create";
    }

    @GetMapping("/detail/{id}")
    public String getDetailPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("images", userService.getFiles(id));
        return "detail";
    }
}
