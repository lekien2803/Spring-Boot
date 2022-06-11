package com.example.user.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserRequest {
    private String name;
    private String phone;
    private String address;
}
