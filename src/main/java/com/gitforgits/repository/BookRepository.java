package com.gitforgits.repository;

import com.gitforgits.model.Book;
import com.gitforgits.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.name = :categoryName")
    List<Book> findBooksByCategoryName(String categoryName);

    @Query("SELECT b FROM Book b WHERE b.author.firstName =:authorName")
    List<Book> findBooksByAuthorName(@Param("authorName") String authorName);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE b.author.firstName = :authorName AND b.publicationYear > :year AND c.name IN :categories")
    List<Book> findBooksByAuthorAndYearAndCategories(@Param("authorName")
                                               String authorName, @Param("year") int year,
                                               @Param("categories") List<Category> categories);
}
