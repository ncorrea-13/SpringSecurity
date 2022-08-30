package com.example.prueba.demo.controladores;

import com.example.prueba.demo.entidades.Autor;
import com.example.prueba.demo.excepciones.ExcepcionesPropias;
import com.example.prueba.demo.servicio.AutorServicio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/autores")
public class AutorControlador { // localhost:8080/autor

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar() {
        return "formAutor.html"; //retorna el html
    }

    @PostMapping("/registro") // envío del registro
    public String registro(@RequestParam String nombre, ModelMap modelo) throws ExcepcionesPropias {

        try {
            autorServicio.crearAutor(nombre); //crea un nuevo autor
            modelo.put("exito", "El autor fue cargado correctamente");
        } catch (ExcepcionesPropias ex) {
            modelo.put("error", ex.getMessage()); //retorna el error
            return "formAutor.html";
        }

        return "index.html";
    }

    @GetMapping("/listar") // listado de los autores
    public String listar(ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutores(); //lista los autores existentes

        modelo.addAttribute("autores", autores); //actualiza el html

        return "autorListado.html";
    }

}
