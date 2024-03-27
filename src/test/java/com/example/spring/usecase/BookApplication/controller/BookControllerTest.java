package com.example.spring.usecase.BookApplication.controller;

import com.example.spring.usecase.BookApplication.dto.BookDto;
import com.example.spring.usecase.BookApplication.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static com.example.spring.usecase.BookApplication.constants.BookTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void getAllBooks_PositiveScenario() {
        // Mocking the service method
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(
                BOOK_DTO_1,
                BOOK_DTO_2
        ));
        // Calling the controller method
        List<BookDto> result = bookController.getAllBooks();
        // Verifying the result
        assertEquals(BOOK_1, result.get(0).getTitle());
        assertEquals(BOOK_2, result.get(1).getTitle());
    }

    @Test
    void getBookById_PositiveScenario() {
        // Mocking the service method
        when(bookService.getBookById(anyLong())).thenReturn(BOOK_DTO_1);
        // Calling the controller method
        BookDto result = bookController.getBookById(LONG_VALUE);
        // Verifying the result
        assertEquals(BOOK_1, result.getTitle());
        assertEquals(Author_1, result.getAuthor());
    }

    @Test
    void saveBook_PositiveScenario() {
        // Mocking the service method
        when(bookService.saveBook(any(BookDto.class))).thenReturn(BOOK_DTO_1);
        // Calling the controller method
        BookDto result = bookController.saveBook(BOOK_DTO_1);
        // Verifying the result
        assertEquals(BOOK_1, result.getTitle());
        assertEquals(Author_1, result.getAuthor());
    }

    @Test
    void updateBook_PositiveScenario() {
        // Mocking the service method
        when(bookService.updateBook(anyLong(), any(BookDto.class))).thenReturn(BOOK_DTO_1);
        // Calling the controller method
        BookDto result = bookController.updateBook(LONG_VALUE,BOOK_DTO_1);
        // Verifying the result
        assertEquals(BOOK_1, result.getTitle());
        assertEquals(Author_1, result.getAuthor());
    }

    @Test
    void deleteBook_PositiveScenario() {
        // Mocking the service method
        when(bookService.deleteBook(anyLong())).thenReturn(DELETED_MSG);
        // Calling the controller method
        String result = bookController.deleteBook(LONG_VALUE);
        // Verifying the result
        assertEquals(DELETED_MSG, result);
    }

    @Test
    void getAllBooksByZenre_PositiveScenario() {
        // Mocking the service method
        when(bookService.getBooksByGenre(anyString())).thenReturn(Arrays.asList(BOOK_DTO_1,BOOK_DTO_2));
        // Calling the controller method
        List<BookDto> result = bookController.getBookByGenre(GENRE);
        // Verifying the result
        assertEquals(BOOK_1, result.get(0).getTitle());
        assertEquals(BOOK_2, result.get(1).getTitle());
    }

    @Test
    void getAllBooksByPublicationYear_PositiveScenario() {
        // Mocking the service method
        when(bookService.getBooksPublishedAfterYear(anyInt())).thenReturn(Arrays.asList(BOOK_DTO_1,BOOK_DTO_2));
        // Calling the controller method
        List<BookDto> result = bookController.getBookByPublicationYear(PUBLICATION_YEAR);
        // Verifying the result
        assertEquals(BOOK_1, result.get(0).getTitle());
        assertEquals(BOOK_2, result.get(1).getTitle());
    }


}
