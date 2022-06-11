package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BMIRequest {
    
    private double height;
    private double weight;
}
