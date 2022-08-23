package com.example.prueba.demo.servicio;

import com.example.prueba.demo.entidades.Autor;
import com.example.prueba.demo.excepciones.ExcepcionesPropias;
import com.example.prueba.demo.repositorios.AutorRepositorio;
import java.util.ArrayList;
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

    @Transactional
    public void crearAutor(String titulo) throws ExcepcionesPropias{
        
        validarExcepciones(titulo);
    
        Autor autor = new Autor();
        
        autor.setNombre(titulo);
        autor.setAlta(new Date());
        autor.setModificacion(new Date());
        
        autorRep.save(autor);
    }
    
    @Transactional
    public void modificarAutor(String titulo) throws ExcepcionesPropias{
        
        validarExcepciones(titulo);
        
        Optional<Autor> respAutor = autorRep.buscarPorNombre(titulo);
        
        if (respAutor.isPresent()){
            
            Autor autor = respAutor.get();
            
            autor.setNombre(titulo);
            autor.setModificacion(new Date());
            
            autorRep.save(autor);
                    
        }
    }
    
    public List<Autor> listarAutores(){
        
        List<Autor> noticias = new ArrayList();
        
        noticias = autorRep.findAll();
        
        return noticias;
    }
    
    private void validarExcepciones(String titulo) throws ExcepcionesPropias{
        if (titulo.isEmpty() || titulo == null) {
            throw new ExcepcionesPropias("El título debe ser completado");
        }
    }
}
