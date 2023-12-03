package com.microsoft.hackathon.demo.service;

// add imports
import java.util.List;
import com.microsoft.hackathon.demo.model.Book;
import com.microsoft.hackathon.demo.model.Rating;
import com.microsoft.hackathon.demo.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {
    
    @Autowired
    BookRepository bookRepository;

    public double getAverageRating(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException());

        List<Rating> ratings = book.getRatings();
        if (ratings.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Rating rating : ratings) {
            sum += rating.getScore();
        }

        return sum / ratings.size();
    }
}
