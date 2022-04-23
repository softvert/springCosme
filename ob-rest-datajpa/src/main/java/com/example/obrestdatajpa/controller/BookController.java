package com.example.obrestdatajpa.controller;
import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import com.sun.xml.bind.v2.model.core.ID;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.aspectj.weaver.loadtime.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    //atributos
    private final Logger log = LoggerFactory.getLogger(BookController.class);
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

    //Obtener un libro por id  se coloca el id entre llava para que pueda funcionar como una variable{id}"
    //es decir que pueda recibir cualquier codigo de la db y retornar si existe
    @GetMapping(value = "/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
      Optional<Book> bookOpt = bookRepository.findById(id);
      if (bookOpt.isPresent())
    //Opcion 1
          return ResponseEntity.ok(bookOpt.get());
      else
    //Opcion 2
          return ResponseEntity.notFound().build();
    //Opcion 3 expresiones lambda
    //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Crear libros
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
    // System.out.println(headers.get("User-Agent"));
        if (book.getId()!=null){//quiere decir que existe el id por tanto no es una creacion
            log.warn("Estas intentando hacer algo distinto que crear un libro");
            System.out.print("Deberias de revisar el metodo que estas queriendo usar");
            return ResponseEntity.badRequest().build();

        }

   //guardar el libro recibido por parametro en la db
   //el parametro se puede cargar en postman, en caso de la vida real seria desde un formulario de una app
            Book result = bookRepository.save(book);
            return ResponseEntity.ok(result);//el libro devuelto tiene una clave primaria

    }
    /*
    *actualizar Book
     */
    @PutMapping("/api/books")
    @ApiOperation("Metodo para actualizar Book")
    public ResponseEntity<Book> update(@ApiParam("El requesbody es para imprimir mas datos del libro") @RequestBody Book book){
        if (book.getId() == null){//si tiene el id se puede actualizar los datos del libro
            log.warn("Estas intentando hacer algo distinto que actualizar");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRepository.existsById(book.getId())){
            log.warn("Estas intentando actualizar un libro que no ha sido encontrado");
            return ResponseEntity.notFound().build();
        }
    //El proceso de actualizacion
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);//el libro devuelto tiene una clave primaria


    }
    //almacenar libros


    //borrar libro
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity <Book> delete(@PathVariable Long id){

        if (!bookRepository.existsById(id)){
            log.warn("tryng to delete a non existent book"); //es: tratando de borrar un libro inexistente
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build();

   }
   //Borrar todos
    @DeleteMapping("/api/books")
    @ApiIgnore //Sirve para que no aparezca en la documentacion de swagger
    public ResponseEntity<Book> deleteAll(){
        bookRepository.deleteAll();
        log.info("Deleten all books");//imprimimos un log de tipo info para ver que se ha borrado todos los libros
        return ResponseEntity.noContent().build();
    }


}

