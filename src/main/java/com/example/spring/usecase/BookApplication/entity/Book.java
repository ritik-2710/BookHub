package com.example.spring.usecase.BookApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

import static com.example.spring.usecase.BookApplication.constants.BookConstants.*;


@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = TITLE_ERROR)
    @NotEmpty(message = TITLE_ERROR)
    private String title;

    @NotBlank(message = AUTHOR_ERROR)
    @NotEmpty(message = AUTHOR_ERROR)
    private String author;

    private double price;

    private String genre;

    @Min(value = 1900, message = PUBLICATION_ERROR)
    private int publicationYear;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    public Book(String title, String author, double price, String genre, int publicationYear) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }
}
