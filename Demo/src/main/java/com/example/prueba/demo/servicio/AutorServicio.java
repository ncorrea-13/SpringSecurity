package com.example.prueba.demo.servicio;

import com.example.prueba.demo.entidades.Autor;
import com.example.prueba.demo.excepciones.ExcepcionesPropias;
import com.example.prueba.demo.repositorios.AutorRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRep;

    @Transactional // creación del autor
    public void crearAutor(String titulo) throws ExcepcionesPropias {

        validarExcepciones(titulo); // validación de datos

        Autor autor = new Autor();

        autor.setNombre(titulo);
        autor.setAlta(new Date());
        autor.setModificacion(new Date());

        autorRep.save(autor); // actualizar base de datos
    }

    @Transactional // modificar un autor
    public void modificarAutor(String titulo) throws ExcepcionesPropias {

        validarExcepciones(titulo); // validar que no llegue un autor nullo

        Optional<Autor> respAutor = autorRep.buscarPorNombre(titulo); // funcion que busca por el nombre y lo guarda en una lista si es que existe

        if (respAutor.isPresent()) {

            Autor autor = respAutor.get();

            autor.setNombre(titulo);
            autor.setModificacion(new Date());

            autorRep.save(autor); // actualiza el autor

        }
    }

    // listar los autores
    public List<Autor> listarAutores() {
        return autorRep.findAll(); //simplificación a la hora de listar los autores
    }

    // método hecho para que no hayan datos nulos
    private void validarExcepciones(String titulo) throws ExcepcionesPropias {
        if (titulo.isEmpty()) {
            throw new ExcepcionesPropias("El título debe ser completado");
        }
    }
}
