package com.example;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    //atributos
    NotificacionService notificacionService;

   //Constructor

    public UserService() {
    }

    public UserService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }
    //metodos



}
