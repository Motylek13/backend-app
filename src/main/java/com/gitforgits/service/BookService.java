package com.gitforgits.service;

import com.gitforgits.model.Author;
import com.gitforgits.model.Book;
import com.gitforgits.repository.AuthorRepository;
import com.gitforgits.repository.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    @Cacheable(value = "books", key = "#id")
    public Optional<Book> getBookById(final Long id) {
        return repository.findById(id);
    }

    public Book saveBook(final Book book){
        return repository.save(book);
    }

    public boolean deleteBookById(final Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }

        return !repository.existsById(id);
    }

    @CacheEvict(value = "books", key = "#book.id")
    public Optional<Book> updateBook(final Long id, final Book updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(updatedData.getTitle());
            existing.setAuthor(updatedData.getAuthor());
            existing.setDescription(updatedData.getDescription());
            return repository.save(existing);
        });
    }

    @Transactional
    public Book publishBookWithAuthor(final Book book, final Author author) {
        Author createdAuthor = authorRepository.save(author);
        book.setAuthor(createdAuthor);
        return repository.save(book);
    }
}
