package com.example.spring.usecase.BookApplication.service;

import com.example.spring.usecase.BookApplication.dto.BookDto;
import com.example.spring.usecase.BookApplication.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {

    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto saveBook(BookDto book);

    BookDto updateBook(Long id, BookDto updatedBook);
    String deleteBook(Long id);

    List<BookDto> getBooksByGenre(String genre);
    List<BookDto> getBooksPublishedAfterYear(int year);
}
