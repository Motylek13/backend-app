package com.gitforgits.web;

import com.gitforgits.model.Book;
import com.gitforgits.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getBook(@PathVariable Long id){
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody Book book){
        Book saved = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


}
