package com.example.user.mapper;

import com.example.user.dto.UserDto;
import com.example.user.model.User;

public class UserMapper {
    public static UserDto toUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setAvatar(user.getAvatar());
        userDto.setPhone(user.getPhone());
        
        return userDto;
    }
}
