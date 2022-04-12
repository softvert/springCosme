package com.example.obrestdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //Extend Jpa repository es lo que obtiene los metodos crud, consultas
    //en <> Se pone siempre el nombre de la clase por el que estamos trabajando y el tipo de datos de la clave primaria








}
