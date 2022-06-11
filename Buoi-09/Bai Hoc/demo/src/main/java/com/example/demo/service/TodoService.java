package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.ToDo;
import com.example.demo.request.CreateRequest;
import com.example.demo.request.UpdateRequest;

@Service
public class TodoService {
    private List<ToDo> todos;

    public TodoService() {
        Random rd = new Random();
        todos = new ArrayList<>();

        todos.add(new ToDo(rd.nextInt(100), "Làm bài tập", true));
        todos.add(new ToDo(rd.nextInt(100), "Đi đá bóng", false));
        todos.add(new ToDo(rd.nextInt(100), "Đi chơi với Bíu", false));

    }

    public List<ToDo> getTodos(String status) {
        return switch (status) {
            case "true" -> todos.stream().filter(ToDo::isStatus).collect(Collectors.toList());
            case "false" -> todos.stream().filter(todo -> !todo.isStatus()).collect(Collectors.toList());
            default -> todos;
        };
    }

    // Helper method: tìm kiếm todo theo id
    public Optional<ToDo> findByID(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst();
    }

    public ToDo getTodoByID(int id) {
        Optional<ToDo> tOptional = findByID(id);
        // if(tOptional.isPresent()){
        // return tOptional.get();
        // }
        // else{
        // throw new NotFoundException("Không tìm thấy công việc có id = " + id);
        // }
        return tOptional.orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + id);
        });
    }

    public ToDo createTodo(CreateRequest request) {
        // có thể validate title nếu để trống

        Random rd = new Random();
        ToDo toDo = new ToDo(rd.nextInt(100), request.getTitle(), false);
        todos.add(toDo);
        return toDo;
    }

    public ToDo updateTodo(int id, UpdateRequest request) {
        ToDo toDo = getTodoByID(id);
        toDo.setTitle(request.getTitle());
        toDo.setStatus(request.isStatus());
        return toDo;
    }

    public void deleteTodo(int id) {
        ToDo toDo = getTodoByID(id);
        todos.removeIf(t -> t.getId() == toDo.getId());
    }
}
