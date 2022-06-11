package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ColorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ColorController {
    @Autowired private ColorService colorService ;


    @GetMapping("/random-color")    
    public String randomColor(@RequestParam int type){

        return colorService.radnomColor(type);
    }
}
