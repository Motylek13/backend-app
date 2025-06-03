package com.gitforgits.repository;

import org.springframework.stereotype.Component;

@Component
public class InMemoryBookDataSource implements BookDataSource{

    @Override
    public String findBookById(Long id) {
        // For demonstration, return a dummy title
        return "This is the book id: " + id;
    }
}
