package com.microsoft.hackathon.demo.model;

// add required imports
import java.util.List;
import jakarta.persistence.*;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private List<Book> books;

    // constructors, getters, and setters

    // constructor
    public Author() {
    }

    // constructor
    public Author(String name) {
        this.name = name;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    // add book
    public void addBook(Book book) {
        if (getBooks()==null) {
            books = new ArrayList<>();
        }
        books.add(book);
    }

    //toString
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }

}