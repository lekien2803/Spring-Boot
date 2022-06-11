package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BMIService;
import com.example.request.BMIRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BMIController {
    @Autowired private BMIService bmiService;

    @GetMapping("/bmi-get")
    public double calculateBMIform(@RequestParam("height") double height, @RequestParam("weight") double weight){
        return bmiService.calculateBMI(height, weight);
    }

    @PostMapping("/bmi-post")
    public double calculateBMI(@RequestBody BMIRequest request){
        return bmiService.calculateBMI(request.getHeight(), request.getWeight());
    }

}
