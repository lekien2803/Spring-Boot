package com.example.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private String id;
    private String fullName;
    private String email;
    private String hashedPassword;
    private State state;
}
