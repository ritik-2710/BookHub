package com.example.spring.usecase.BookApplication.builder;

import com.example.spring.usecase.BookApplication.dto.BookDto;

public class Builder {
    private String title;
    private String author;
    private double price;
    private String genre;
    private int publicationYear;

    public Builder title(String title) {
        this.title = title;
        return this;
    }

    public Builder author(String author) {
        this.author = author;
        return this;
    }

    public Builder price(double price) {
        this.price = price;
        return this;
    }

    public Builder genre(String genre) {
        this.genre = genre;
        return this;
    }

    public Builder publicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    public BookDto build() {
        return new BookDto(this.title,this.author, this.price,this.genre,this.publicationYear);
    }
}
