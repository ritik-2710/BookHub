package com.example.spring.usecase.BookApplication.mapper;

import com.example.spring.usecase.BookApplication.dto.BookDto;
import com.example.spring.usecase.BookApplication.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private static final Logger log = LoggerFactory.getLogger(BookMapper.class);

    public static BookDto toDto(Book entity) {
        if (entity == null) {
            return null;
        }

        log.debug("Converting Book entity to BookDto. Entity: {}", entity);
        BookDto bookDto = BookDto.builder()
                .title(entity.getTitle())
                .author(entity.getAuthor())
                .price(entity.getPrice())
                .genre(entity.getGenre())
                .publicationYear(entity.getPublicationYear())
                .build();
        log.debug("Conversion result: {}", bookDto);

        return bookDto;
    }

    public static Book toEntity(BookDto dto) {
        if (dto == null) {
            return null;
        }

        log.debug("Converting BookDto to Book entity. DTO: {}", dto);
        Book book = new Book(dto.getTitle(),
                dto.getAuthor(),
                dto.getPrice(),
                dto.getGenre(),
                dto.getPublicationYear());
        log.debug("Conversion result: {}", book);

        return book;
    }
}
