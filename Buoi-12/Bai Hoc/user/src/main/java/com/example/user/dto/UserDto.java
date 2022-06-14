package com.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
}
