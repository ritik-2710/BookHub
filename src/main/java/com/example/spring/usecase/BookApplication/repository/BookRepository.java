package com.example.spring.usecase.BookApplication.repository;

import com.example.spring.usecase.BookApplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.genre) = LOWER(:genre)")
    List<Book> findByGenreIgnoreCase(@Param("genre") String genre);

    @Query("SELECT b FROM Book b WHERE b.publicationYear > :year")
    List<Book> findByPublicationYearGreaterThan(@Param("year") int year);

    Optional<Book> findByTitleAndAuthor(String title, String author);
}
