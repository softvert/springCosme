package com.example.obspringdatajpa;

//Este repositoty: sirve para almacenar los datos del coche en la db
//Es decir sirve para crear repositorio de Coches en la db.

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//en el parametro <Coche, Long> Coche seria el tipo de datos que queremos almacenar
//Luego le pasamos el tipo de datos de la clave primaria en este caso seria Long el tipo de datos de Id Coche
public interface CocheRepository extends JpaRepository <Coche, Long>{
}
