package com.microsoft.hackathon.demo.repository;

// add imports
import com.microsoft.hackathon.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}