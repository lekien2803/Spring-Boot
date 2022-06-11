package com.example.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.user.dto.UserDto;
import com.example.user.exception.BadRequestException;
import com.example.user.exception.NotFoundException;
import com.example.user.mapper.UserMapper;
import com.example.user.model.User;
import com.example.user.request.CreateUser;
import com.example.user.request.UpdatePassword;
import com.example.user.request.UpdateUserRequest;

@Service
public class UserService {
    private List<User> users;
    @Autowired private FileService fileService;

    public UserService() {
        initData();
    }

    private void initData() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "a@gmail.com", "0123456789", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(2, "Trần Văn B", "b@gmail.com", "0123456789", "Tỉnh Nam Định", null, "222"));
        users.add(new User(3, "Ngô Thị C", "c@gmail.com", "0123456789", "Tỉnh Hưng Yên", null, "333"));
    }

    // danh sach user
    public List<UserDto> getUsers() {
        return users.stream().map(UserMapper::toUser).collect(Collectors.toList());
    }

    // tim kiem user theo ten
    public List<UserDto> getUser(String name) {
        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserMapper::toUser).collect(Collectors.toList());
    }

    // xoa user
    public void deleteUser(int id) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        users.removeIf(user -> user.getId() == id);
    }

    // tao user moi
    public UserDto createUser(CreateUser request) {
        // kiem tra email da ton tai hay chua
        if (findUser(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email này đã tồn tại");
        }
        User user = new User();
        Random rd = new Random();
        user.setId(rd.nextInt(100 - 4 + 1) + 4);
        user.setName(request.getName());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        users.add(user);
        return UserMapper.toUser(user);
    }

    // lay user theo id
    public UserDto getUserById(int id) {
        Optional<User> uOptional = findUser(id);
        if (uOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }
        return UserMapper.toUser(uOptional.get());
    }

    // cap nhat user
    public UserDto updateUser(int id, UpdateUserRequest request) {
        Optional<User> uOptional = findUser(id);
        if (uOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        User user = uOptional.get();

        user.setName(request.getName());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());

        return UserMapper.toUser(user);

    }

    // cap nhat mat khau
    public void updatePassword(int id, UpdatePassword request) {
        Optional<User> uOptional = findUser(id);
        if (uOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }
        User user = uOptional.get();

        // kiem tra password cu co dung khong
        if (!user.getPassword().equals(request.getOldPassword())) {
            throw new BadRequestException("Mật khẩu cũ không chính xác");

        }

        if (request.getNewPassword().equals(request.getNewPassword())) {
            throw new BadRequestException("Mật khẩu mới không được trùng với mật khẩu cũ");
        }

        // tao mat khau moi
        user.setPassword(request.getNewPassword());
    }

    // quen mat khau
    public String forgotPassword(int id) {
        Optional<User> uOptional = findUser(id);
        if (uOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }
        User user = uOptional.get();

        Random rd = new Random();
        String password = String.valueOf((rd.nextInt(1000 - 100) + 100));
        user.setPassword(password);
        return password;
    }

    // upload file
    public String uploadFile(int id, MultipartFile file) {
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        return fileService.uploadFile(id, file);
    }

    public byte[] readFile(int id, String fileId){

        return fileService.readFile(id, fileId);
    }

    // xoa file
    public void deleteFile(int id, String fileId){
        fileService.deleteFile(id, fileId);
    }

    // lay danh sach file
    public List<String> getFiles(int id){
        if (findUser(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        return fileService.getFiles(id);
    }

    // helper method
    public Optional<User> findUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public Optional<User> findUser(String email) {
        return users.stream().filter(user -> user.getEmail().toLowerCase().equals(email.toLowerCase())).findFirst();
    }

}
