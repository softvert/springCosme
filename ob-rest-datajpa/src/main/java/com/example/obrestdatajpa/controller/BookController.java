package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    //atributos
    private BookRepository bookRepository;
    private Book book;
    //Constructores


    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Crear CRUD sobre la entidad Book
    //recuperar todos los libros
    /*
    para acceder al metodo get digitamos en el navegador o es postman
    http://localhost:8081/api/books
     */
    @GetMapping(value = "/api/books")
    public List<Book> findAll() {
        //recuperar y devolver una lista de los libros
        return bookRepository.findAll();

        }

    //Obtener un libro por id
    @GetMapping(value = "/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
      Optional<Book> bookOpt = bookRepository.findById(id);
      if (bookOpt.isPresent())
          //Opcion 1
          return ResponseEntity.ok(bookOpt.get());
      else
          //Opcion 2
          return ResponseEntity.notFound().build();
      //Opcion 3
      //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }

    //Crear libros
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
       // System.out.println(headers.get("User-Agent"));

        //guardar el libro recibido por parametro en la db
        return bookRepository.save(book);

    }


    //almacenar libros





    //borrar libro

}

