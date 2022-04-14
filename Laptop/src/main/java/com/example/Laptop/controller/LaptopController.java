package com.example.Laptop.controller;

import com.example.Laptop.entity.Laptop;
import com.example.Laptop.repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class LaptopController {
    //atributos
    private LaptopRepository laptopRepository;
    //constructor


    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }
    //Metodo get para obtener todas las laptop`almacenada en la db
    //Obtenemos realizando una peticion al http://localhost:8082/api/laptops
    @GetMapping(value = "/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }
    //Crear laptop y almacenarlos en la db
    //Para guardar una laptop lo realizamos al http://localhost:8082/api/laptop
    @PostMapping(value = "/api/laptop")
    public Laptop create(@RequestBody Laptop laptop){
        return laptopRepository.save(laptop);
    }


}
