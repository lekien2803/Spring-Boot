package com.baitap03.part1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public String getHome(){
        return "index";
    }

    @GetMapping("/text")
    public String demoText(Model model){
        model.addAttribute("name", "Tom");
        return "text";
    }
}
