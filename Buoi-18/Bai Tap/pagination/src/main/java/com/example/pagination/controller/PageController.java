package com.example.pagination.controller;

import com.example.pagination.entity.Book;
import com.example.pagination.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PageController {


    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String listBooks(
            Model model,
            @RequestParam("page") Integer page
            ){
        Page<Book> books = bookService.findAll(page);
        int totalPages = books.getTotalPages();
        int totalItems = books.getTotalPages();

        return "listBooks";
    }
}
