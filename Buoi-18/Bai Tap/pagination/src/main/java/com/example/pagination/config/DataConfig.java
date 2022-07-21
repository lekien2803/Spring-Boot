package com.example.pagination.config;

import com.example.pagination.entity.Book;
import com.example.pagination.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class DataConfig {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void initData(){
        bookRepository.saveAll(IntStream.range(0,100).mapToObj(i -> Book.builder().name("name " + i).build()).collect(Collectors.toList()));
    }
}
