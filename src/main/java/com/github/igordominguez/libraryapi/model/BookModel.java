package com.github.igordominguez.libraryapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer yearPublish;
    private boolean available = true;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel author;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearPublish() {
        return yearPublish;
    }

    public void setYearPublish(Integer yearPublish) {
        this.yearPublish = yearPublish;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }
}
