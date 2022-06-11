package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.exception.BadRequest;

@Service
public class BMIService {
    public double calculateBMI(double height,double weight){
        if(height <= 0 || weight <=0){
            throw new BadRequest("chieu cao hoac can nang phai > 0");
        }

        

        double bmi = weight/(height*height);
        return bmi;
    }


}
