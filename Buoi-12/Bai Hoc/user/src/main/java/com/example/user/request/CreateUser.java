package com.example.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUser {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
}
