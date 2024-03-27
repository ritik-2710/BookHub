package com.example.spring.usecase.BookApplication.controller;

import com.example.spring.usecase.BookApplication.dto.BookDto;
import com.example.spring.usecase.BookApplication.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        logger.info("Fetching all books.");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        logger.info("Fetching book with ID: {}", id);
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDto saveBook(@Valid @RequestBody BookDto book) {
        logger.info("Saving book: {}", book);
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @Valid @RequestBody BookDto updatedBook) {
        logger.info("Updating book with ID: {}", id);
        BookDto result = bookService.updateBook(id, updatedBook);
        logger.info("Updated book: {}", result);
        return result;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        logger.info("Deleting book with ID: {}", id);
        return bookService.deleteBook(id);
    }

    @GetMapping("/genre/{genre}")
    public List<BookDto> getBookByGenre(@PathVariable("genre") String genre) {
        logger.info("Fetching books by genre: {}", genre);
        return bookService.getBooksByGenre(genre);
    }

    @GetMapping("/year/{year}")
    public List<BookDto> getBookByPublicationYear(@PathVariable("year") String year) {
        logger.info("Fetching books published after year: {}", year);
        return bookService.getBooksPublishedAfterYear(Integer.parseInt(year));
    }
}
