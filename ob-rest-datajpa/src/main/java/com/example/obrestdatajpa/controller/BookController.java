package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.aspectj.weaver.loadtime.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final Logger log = LoggerFactory.getLogger(BookController.class);
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
    public ResponseEntity<Book> findOneById(@PathVariable Long id) {
        // Optional permite verificar si existe un libro a traves de if
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
    @PostMapping(value = "/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        if (book.getId() != null) {//Quiere decir que existe el id por tanto no es una creacion
            log.warn("trying to create book with id");
            System.out.println("trying to create book with id");
            ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);//El libro devuelto tiene una clave primaria

    }

    //Actualizar libro
    @PutMapping(value = "/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book) {
        if (book.getId() == null) {//Si no tiene id quiere decir que es una creacion
            log.warn("Estas intentando actualizar un libro inexistente");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRepository.existsById(book.getId())) {
            log.warn("Estas intentando actualizar un libro inexistente");
            return ResponseEntity.notFound().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result); //El libro devuelto tiene una clave primaria
    }
    //Borrar libro por id
    @DeleteMapping(value = "/api/book/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if (!bookRepository.existsById(id)){
            log.info("Estas queriendo borrar un id que no ha sido encontrado");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    //borrar libro
    @DeleteMapping("api/book")
    public ResponseEntity<Book> deleteAll(){
        log.info("Solicitud REST para eliminar todas los libros");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}



