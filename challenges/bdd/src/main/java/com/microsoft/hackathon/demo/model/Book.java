package com.microsoft.hackathon.demo.model;

//create imports required for the class
import java.util.List;

import java.util.ArrayList;
import jakarta.persistence.*;


@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany
    private List<Author> authors;

    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;

    // constructors, getters, and setters

    // constructor
    public Book() {
        this.ratings = new ArrayList<>();
    }

    // constructor
    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
        this.ratings = new ArrayList<>();
    }

    // getters 
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //add author
    public void addAuthor(Author author) {
        if (getAuthors() == null) {
            this.authors = new ArrayList<>();
        }
        this.authors.add(author);
    }

    //add rating
    public void addRating(Rating rating) {
        if (getRatings() == null) {
            this.ratings = new ArrayList<>();
        }
        this.ratings.add(rating);
    }

    // toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title +
                '}';
    }

}
