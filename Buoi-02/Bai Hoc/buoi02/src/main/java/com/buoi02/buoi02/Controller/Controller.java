package com.buoi02.buoi02.Controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.buoi02.buoi02.dto.BookRequest;
import com.buoi02.buoi02.model.Book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class Controller {
    private ConcurrentHashMap<String, Book> books;

    public Controller() {
        books = new ConcurrentHashMap<>();
        books.put("001", new Book("001", "Cuon theo chieu gio", "ABC", 1942));
        books.put("002", new Book("002", "tat den", "MCL", 1942));
    }

    @GetMapping
    public List<Book> getBook() {

        return books.values().stream().toList();
    }

    
    @PostMapping
    public Book createNewBook(@RequestBody BookRequest bookRequest) {
        String uuid = UUID.randomUUID().toString();
        // Book newBook = new Book(uuid, bookRequest.title(), bookRequest.author(),
        // bookRequest.year());
        Book newBook = Book.builder().id(uuid).title(bookRequest.title()).author(bookRequest.author())
                .year(bookRequest.year()).build();
        books.put(uuid, newBook);
        return newBook;
    }

    @GetMapping(value = "/{id}")
    public Book getBookById(@PathVariable("id") String id) {
        return books.get(id);
    }

    @GetMapping("/searchByParam")
    public Book getBookById1(@RequestParam("id") String id) {
        return books.get(id);
    }

    @PutMapping(value = "/{id}")
    public Book updateBookById(@PathVariable("id") String id, @RequestBody BookRequest bookRequest) {
        Book updateBook = new Book(id, bookRequest.title(), bookRequest.author(), bookRequest.year());
        books.put(id, updateBook);
        return updateBook;
    }

}
