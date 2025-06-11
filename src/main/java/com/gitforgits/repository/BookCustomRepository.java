package com.gitforgits.repository;

import com.gitforgits.model.Book;
import com.gitforgits.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookCustomRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Book> findBooksDynamically(String authorName, Integer year, List<String> categories) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> bookRoot = cq.from(Book.class);

        // Start building predicates
        List<Predicate> predicates = new ArrayList<>();

        // Filter by author name (if provided)
        if (authorName != null && !authorName.isEmpty()) {
            predicates.add(cb.equal(bookRoot.get("author").get("name"), authorName));
        }

        // Filter by publication year (if provided)
        if (year != null) {
            predicates.add(cb.greaterThanOrEqualTo(bookRoot.get("publicationYear"), year));
        }

        // Filter by categories (if provided)
        if (categories != null && !categories.isEmpty()) {
            Join<Book, Category> categoryJoin = bookRoot.join("categories");
            predicates.add(categoryJoin.get("name").in(categories));
        }

        // Apply all predicates
        cq.where(predicates.toArray(new Predicate[0]));

        // Execute query and return results
        return em.createQuery(cq).getResultList();
    }
}