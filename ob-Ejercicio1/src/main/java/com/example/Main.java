package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public Main() {

    }
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Saludo saludo = (Saludo)context.getBean("saludo");
        String texto = saludo.imprimirSaludo();
        System.out.println(texto);

        NotificacionService notificacionService = (NotificacionService) context.getBean("notificacionService");
        System.out.println(notificacionService.imprimirSaludoNotificacion());



    }
}
