package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.TodoService;

@Controller
public class TodoController {
    @Autowired private TodoService todoService;


    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("todos", todoService.getTodos(""));
        return "index";
    }
}
