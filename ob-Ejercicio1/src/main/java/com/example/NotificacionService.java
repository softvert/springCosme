package com.example;

import org.springframework.stereotype.Component;

@Component
public class NotificacionService {

    //atributos
    Saludo saludo;


    //constructor
    public NotificacionService() {
        System.out.println("Estamos ejecutando el constructor NotificacionService");
    }

    public NotificacionService(Saludo saludo) {
        this.saludo = saludo;
    }

    //metodos
    public String imprimirSaludoNotificacion(){
       return "Este es un Saludo de NoticicacionService";
    }
}
