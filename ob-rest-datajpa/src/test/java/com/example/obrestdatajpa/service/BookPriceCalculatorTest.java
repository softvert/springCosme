package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatePriceTest() {

        //configuracion de la prueba

        Book book = new Book(1L, "Titulo del libre", "Autor", 100, 300.00, LocalDate.now(),true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        //se ejecuta el comportamiento a testear

        double price = calculator.calculatePrice(book);
        System.out.println(price);

        //comprobaciones aserciones

        assertTrue(price>0);
        assertEquals(302.99,price);


    }
}