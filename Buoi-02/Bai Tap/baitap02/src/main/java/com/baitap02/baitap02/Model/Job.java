package com.baitap02.baitap02.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
    private String id;
    private String title;
    private String discription;
    private String location;
    private int min_salary;
    private int max_salary;
    private String email_to;

}
