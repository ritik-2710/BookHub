package com.example.spring.usecase.BookApplication.constants;

import com.example.spring.usecase.BookApplication.dto.BookDto;

public class BookTestConstants {

    public static final BookDto BOOK_DTO_1 = new BookDto("Book1", "Author1", 20.0, "Genre1", 2000);

    public static final BookDto BOOK_DTO_2 = new BookDto("Book2", "Author2", 20.0, "Genre1", 2000);

    public static final String BOOK_1 = "Book1";

    public static final String BOOK_2 = "Book2";

    public static final String Author_1 = "Author1";

    public static final String DELETED_MSG = "Successfully Deleted";

    public static final Long LONG_VALUE = Long.valueOf("1");

    public static final String PUBLICATION_YEAR = "1900";

    public static final String GENRE = "Tech";
}
