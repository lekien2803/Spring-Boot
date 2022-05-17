package com.baitap02.baitap02.dto;

import com.baitap02.baitap02.Model.Location;

public record JobRequest(String title, String discription, Location location, int min_salary, int max_salary, String email_to) {
    
}
