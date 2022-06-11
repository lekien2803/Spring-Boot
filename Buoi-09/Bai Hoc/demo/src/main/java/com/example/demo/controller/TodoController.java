package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ToDo;
import com.example.demo.request.CreateRequest;
import com.example.demo.request.UpdateRequest;
import com.example.demo.service.TodoService;

@RestController
@RequestMapping("api/v1")
public class TodoController {
    @Autowired
    private TodoService todoService;

    // lấy công việc theo id
    @GetMapping("/todos/{id}")
    public ToDo getTodoByID(@PathVariable int id) {
        return todoService.getTodoByID(id);
    }

    // lấy danh sách công việc
    @GetMapping("/todos")
    public List<ToDo> getTodos(@RequestParam(required = false, defaultValue = "") String status) {
        return todoService.getTodos(status);
    }

    // tạo mới công việc
    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public ToDo createTodo(@RequestBody CreateRequest request) {
        return todoService.createTodo(request);
    }

    // cập nhật công việc
    @PutMapping("/todos/{id}")
    public ToDo updateTodo(@PathVariable int id, @RequestBody UpdateRequest request) {
        return todoService.updateTodo(id, request);
    }

    // xóa công việc
    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
