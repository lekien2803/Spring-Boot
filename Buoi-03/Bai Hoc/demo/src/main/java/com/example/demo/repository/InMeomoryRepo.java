package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.model.Book;

import org.springframework.stereotype.Repository;

import lombok.Getter;

@Repository
public class InMeomoryRepo {
    private List<Book> books;

    public InMeomoryRepo() {
        books = new ArrayList<>();

        Book book1 = new Book("001", "Cuon theo chieu gio", "ABC", 1942);
        Book book2 = new Book("002", "tat den", "MCL", 1942);

        books.add(book1);
        books.add(book2);
    }

    public List<Book> getBooks() {
        return books;
    }

}
