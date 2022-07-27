package com.example.pagination.service;

import com.example.pagination.entity.Book;
import com.example.pagination.repository.BookRepository;
import com.example.pagination.util.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Page<Book> findAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 6);
        return bookRepository.findAll(pageable);
    }
}
