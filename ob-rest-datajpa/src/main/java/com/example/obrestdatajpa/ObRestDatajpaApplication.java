package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;

@EnableWebMvc
@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		//Crear libros
		Book book1 = new Book(null, "Padre rico Padre Pobre", "Robert k.", 200, 80.000, LocalDate.of(2022, 03, 12),true);
		Book book2 = new Book(null, "Las 7 leyes", "Robert k.", 200, 80.000, LocalDate.of(2022, 03, 12),true);
		Book book3 = new Book(null, "7 habitos competitivos", "Robert k.", 200, 80.000, LocalDate.of(2022, 03, 12),true);

		//almacenar libros
		System.out.println("Cantidad de libros en la db: "+repository.findAll().size());
		repository.save(book1);
		repository.save(book2);
		repository.save(book3);


		//recuperar todos los libros
		System.out.println("Cantidad de libros en la db: "+repository.findAll().size());

		//borrar libro
		//repository.deleteById(1L);
		//System.out.println("Cantidad de libros en la db: "+repository.findAll().size());
	}

}
