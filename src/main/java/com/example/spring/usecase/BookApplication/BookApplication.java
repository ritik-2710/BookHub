package com.example.spring.usecase.BookApplication;

import com.example.spring.usecase.BookApplication.entity.Book;
import com.example.spring.usecase.BookApplication.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

	@Bean
	CommandLineRunner getRunner(BookRepository bookRepository)
	{
		return args -> {
			bookRepository.save(new Book( "MicroServices", "Oreilley", 599.0, "Tech", 2002));
			bookRepository.save(new Book("MicroServices 2", "Oreilley", 699.0, "Tech", 2005));
			bookRepository.save(new Book("MicroServices 3", "Oreilley", 799.0, "Tech", 2009));
			bookRepository.save(new Book("MicroServices 4", "Oreilley", 899.0, "", 2014));
		};
	}

}
