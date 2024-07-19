package com.microsoft.hackathon.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

// add required imports
import jakarta.persistence.*;


@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    @ManyToOne
    @JsonIgnore
    private Book book;

    // constructors, getters, and setters

    // constructor
    public Rating() {
    }

    // constructor
    public Rating(int score, Book book) {
        this.score = score;
        this.book = book;
    }

    // getters
    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public Book getBook() {
        return book;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // add book
    public void setBook(Book book) {
        this.book = book;
    }

    //toString
    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", score=" + score +
                ", book=" + book +
                '}';
    }
}
