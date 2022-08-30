package com.example.prueba.demo.excepciones;

public class ExcepcionesPropias extends Exception {

    public ExcepcionesPropias() {
    }

    public ExcepcionesPropias(String msg) { //proporciona los mensajes de error y facilita la forma de publicación en la página
        super(msg);
    }
}
