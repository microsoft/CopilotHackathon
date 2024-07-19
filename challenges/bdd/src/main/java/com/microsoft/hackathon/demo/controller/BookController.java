package com.microsoft.hackathon.demo.controller;

// add imports
import com.microsoft.hackathon.demo.model.Book;
import com.microsoft.hackathon.demo.model.Rating;
import com.microsoft.hackathon.demo.repository.BookRepository;
import com.microsoft.hackathon.demo.repository.RatingRepository;
import com.microsoft.hackathon.demo.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        bookRepository.deleteById(id);
    }

    @GetMapping("/{bookId}/ratings")
    public List<Rating> getAllRatingsForBook(@PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return book.getRatings();
    }

    @GetMapping("/{bookId}/ratings/{id}")
    public Rating getRatingByIdForBook(@PathVariable Long bookId, @PathVariable Long id) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return book.getRatings().stream()
                .filter(rating -> rating.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{bookId}/ratings")
    public Rating createRatingForBook(@PathVariable Long bookId, @RequestBody Rating rating) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        rating.setBook(book);
        return ratingRepository.save(rating);
    }

    @PutMapping("/{bookId}/ratings/{id}")
    public Rating updateRatingForBook(@PathVariable Long bookId, @PathVariable Long id, @RequestBody Rating rating) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Rating existingRating = book.getRatings().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existingRating.setScore(rating.getScore());
        return ratingRepository.save(existingRating);
    }

    @DeleteMapping("/{bookId}/ratings/{id}")
    public void deleteRatingForBook(@PathVariable Long bookId, @PathVariable Long id) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Rating existingRating = book.getRatings().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ratingRepository.delete(existingRating);
    }

    @GetMapping("/{id}/ratings/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long id) {
        double averageRating = bookService.getAverageRating(id);
        return ResponseEntity.ok(averageRating);
    }
}