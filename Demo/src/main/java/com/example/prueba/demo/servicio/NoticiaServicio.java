package com.example.prueba.demo.servicio;

import com.example.prueba.demo.entidades.Autor;
import com.example.prueba.demo.entidades.Noticia;
import com.example.prueba.demo.excepciones.ExcepcionesPropias;
import com.example.prueba.demo.repositorios.AutorRepositorio;
import com.example.prueba.demo.repositorios.NoticiaRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import net.iharder.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticiaServicio {

    @Autowired
    private AutorRepositorio autorRep;

    @Autowired
    private NoticiaRepositorio notiRep;

    @Transactional
    public void crearNoticia(String titulo, String texto, String idAutor, MultipartFile imagen) throws ExcepcionesPropias, IOException {

        validarExcepciones(titulo, texto, idAutor);
        
        Autor autor = autorRep.findById(idAutor).get();
        Noticia noticia = new Noticia();

        if (!imagen.isEmpty()) {
            try {
                noticia.setFoto(Base64.encodeBytes(imagen.getBytes()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        noticia.setTitulo(titulo);
        noticia.setTexto(texto);
        noticia.setTextoAcortado(texto.substring(0, 15));
        noticia.setAlta(new Date());
        noticia.setModificacion(new Date());
        noticia.setAutor(autor);

        notiRep.save(noticia);
    }

    @Transactional
    public void modificarNoticia(String id, String titulo, String texto, String idAutor) throws ExcepcionesPropias {

        validarExcepciones(titulo, texto, idAutor);

        Optional<Noticia> respNoti = notiRep.findById(id);
        Optional<Autor> respAutor = autorRep.findById(idAutor);

        Autor autor = new Autor();

        if (respAutor.isPresent()) {

            autor = respAutor.get();

        }

        if (respNoti.isPresent()) {

            Noticia noticia = respNoti.get();

            noticia.setTitulo(titulo);
            noticia.setAutor(autor);
            noticia.setTexto(texto);
            noticia.setTextoAcortado(texto.substring(0, 15));
            noticia.setModificacion(new Date());

            notiRep.save(noticia);

        }
    }

    public List<Noticia> listarNoticias() {

        List<Noticia> noticias = new ArrayList();

        noticias = notiRep.findAll();

        return noticias;
    }

    public Noticia getOne(String id) {
        return notiRep.getOne(id);
    }

    public void validarExcepciones(String titulo, String texto, String idAutor) throws ExcepcionesPropias {

        if (titulo.isEmpty()) {
            throw new ExcepcionesPropias("El título debe ser completado");
        }

        if (texto.isEmpty()) {
            throw new ExcepcionesPropias("La noticia necesita tener texto");
        }

        if (idAutor.isEmpty()) {
            throw new ExcepcionesPropias("El id del autor debe ser completado");
        }
    }
}
