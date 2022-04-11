package com.example;

import org.springframework.stereotype.Component;

@Component
public class Saludo {
     //constructores
    public Saludo() {
        System.out.println("imprimiendo constructor saludo");
    }

    //metodos
      public String imprimirSaludo() {
        return "holaaa";
    }
}
