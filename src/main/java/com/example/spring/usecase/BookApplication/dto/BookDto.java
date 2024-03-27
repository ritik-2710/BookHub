package com.example.spring.usecase.BookApplication.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.spring.usecase.BookApplication.builder.Builder;

import static com.example.spring.usecase.BookApplication.constants.BookConstants.*;

@Data
@AllArgsConstructor
public class BookDto {

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

    public static Builder builder() {
        return new Builder();
    }

}
