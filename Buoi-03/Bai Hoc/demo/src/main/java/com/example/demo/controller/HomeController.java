package com.example.demo.controller;

import com.example.demo.model.Book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String homePage(Model model){
        model.addAttribute("name", "Kien");
        model.addAttribute("age", "28");
        model.addAttribute("massage", "<h2>Display <span style='color:red'> HTML </span> here.");

        return "index";
    }

    @GetMapping("/about")
    public String displayAbout(Model model, @RequestParam("foo") String a, @RequestParam("tom") String b ){
        model.addAttribute("foo", a);
        model.addAttribute("tom", b);
        return "about";
    }

    @GetMapping("/text")
    public String demoText(Model model){
        return "text";
    }
}
