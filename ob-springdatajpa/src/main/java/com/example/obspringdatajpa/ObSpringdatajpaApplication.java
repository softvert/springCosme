package com.example.obspringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class ObSpringdatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObSpringdatajpaApplication.class, args);
		CocheRepository repository = context.getBean(CocheRepository.class);

		System.out.println("find");

		System.out.println("El nunero de coches en la base de datos es: " +repository.count());


		//Crear coche en la base de datos

		Coche toyota = new Coche(null, "Mitsubishi", "L200", 2015);
		repository.save(toyota);

		System.out.println("El nunero de coches en la base de datos es: " +repository.count());

		//recuperar coches todos los coches de la base de datos

		System.out.println("Los coches encontrados son: "+ repository.findAll());



	}




}
