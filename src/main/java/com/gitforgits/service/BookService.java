package com.gitforgits.service;

import com.gitforgits.model.Book;
import com.gitforgits.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> getBookById(Long id) {
        return repository.findById(id);
    }

    public Book saveBook(Book book){
        return repository.save(book);
    }
}
