package com.example.spring.usecase.BookApplication.service;

import com.example.spring.usecase.BookApplication.dto.BookDto;
import com.example.spring.usecase.BookApplication.entity.Book;
import com.example.spring.usecase.BookApplication.exceptions.AlreadyExistsException;
import com.example.spring.usecase.BookApplication.exceptions.NoDetailsExistsException;
import com.example.spring.usecase.BookApplication.mapper.BookMapper;
import com.example.spring.usecase.BookApplication.repository.BookRepository;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.spring.usecase.BookApplication.constants.BookConstants.NO_BOOK_FOUND_ERROR;
import static com.example.spring.usecase.BookApplication.mapper.BookMapper.toDto;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> getAllBooks() {
        log.info("Fetching all books.");
        List<BookDto> books = bookRepository.findAll().stream().map(BookMapper::toDto)
                .collect(Collectors.toList());
        if(books.isEmpty()){
            throw new NoDetailsExistsException(NO_BOOK_FOUND_ERROR);
        }
        log.info("Fetched {} books.", books.size());
        return books;
    }

    @Override
    public BookDto getBookById(Long id) {
        log.info("Fetching book by ID: {}", id);
        BookDto bookDto = toDto(bookRepository.findById(id).orElse(null));
        if(bookDto == null){
            throw new NoDetailsExistsException(NO_BOOK_FOUND_ERROR);
        }
        log.info("Fetched book: {}", bookDto);
        return bookDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public BookDto saveBook(BookDto book) {
        log.info("Saving book: {}", book);
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (existingBook.isPresent()) {
            throw new AlreadyExistsException("Book with title '" + book.getTitle() +
                    "' and author '" + book.getAuthor() + "' already exists.");
        }
        BookDto savedBook = toDto(bookRepository.save(BookMapper.toEntity(book)));
        log.info("Saved book: {}", savedBook);
        return savedBook;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @CachePut({"booksByGenreCache", "booksByPublicationYearCache"})
    public BookDto updateBook(Long id, BookDto updatedBook) {
        log.info("Updating book with ID: {}", id);
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        log.info("Fetched book: {}", existingBookOptional);
        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());
            log.info("Updated book: {}", existingBook);
            return toDto(existingBook);
        } else {
            throw new NoDetailsExistsException("Book with ID " + id + " not found for updating.");
        }
    }

    @Override
    @CacheEvict({"booksByGenreCache", "booksByPublicationYearCache"})
    public String deleteBook(Long id) {
        log.info("Deleting book by ID: {}", id);
        if (!bookRepository.existsById(id)) {
            throw new NoDetailsExistsException("No book found for deletion with ID: " + id);
        }
        bookRepository.deleteById(id);
        log.info("Book deleted successfully.");
        return "Successfully Deleted the Book Details with Id "+ id;
    }

    @Override
    @Cacheable("booksByGenreCache")
    public List<BookDto> getBooksByGenre(String genre) {
        log.info("Fetching books by genre: {}", genre);
        List<BookDto> books = bookRepository.findByGenreIgnoreCase(genre).stream().map(BookMapper::toDto)
                .collect(Collectors.toList());
        if (books.isEmpty()) {
            throw new NoDetailsExistsException("No books found for genre: " + genre);
        }
        log.info("Fetched {} books by genre: {}", books.size(), genre);
        return books;
    }

    @Override
    @Cacheable("booksByPublicationYearCache")
    public List<BookDto> getBooksPublishedAfterYear(int year) {
        log.info("Fetching books published after year: {}", year);
        if(year<1900){
            throw  new ValidationException("Year should be greater than 1900");
        }
        List<BookDto> books = bookRepository.findByPublicationYearGreaterThan(year).stream().map(BookMapper::toDto)
                .collect(Collectors.toList());
        if (books.isEmpty()) {
            throw new NoDetailsExistsException("No books found for Publication Year greater than: " + year);
        }
        log.info("Fetched {} books published after year: {}", books.size(), year);
        return books;
    }
}
