package com.gitforgits.model;

import jakarta.persistence.*;

@Entity
public class BookDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private int pages;

    @OneToOne(mappedBy = "detail")
    private Book book;

}
