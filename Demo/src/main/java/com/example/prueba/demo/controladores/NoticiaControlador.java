package com.example.prueba.demo.controladores;

import com.example.prueba.demo.entidades.Autor;
import com.example.prueba.demo.entidades.Noticia;
import com.example.prueba.demo.excepciones.ExcepcionesPropias;
import com.example.prueba.demo.servicio.AutorServicio;
import com.example.prueba.demo.servicio.NoticiaServicio;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/noticias")
public class NoticiaControlador { //localhost:8080/noticias

    @Autowired
    private NoticiaServicio noticiaServicio;

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar") //localhost:8080/noticias/registrar
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores(); //muestra los autores para registrar a la noticia

        modelo.addAttribute("autores", autores);

        return "formNoticia.html";
    }

    @PostMapping("/registro") //localhost:8080/noticias/registro
    public String registro(@RequestParam String nombre, @RequestParam String texto, @RequestParam MultipartFile foto, @RequestParam String idAutor, ModelMap modelo) throws IOException {

        try {
            noticiaServicio.crearNoticia(nombre, texto, idAutor, foto); //validación de datos

            modelo.put("exito", "La noticia fue cargada correctamente");
        } catch (ExcepcionesPropias ex) {
            modelo.put("error", ex.getMessage()); //envío del error al html

            List<Noticia> noticias = noticiaServicio.listarNoticias(); //listado de noticias al principio

            modelo.addAttribute("noticias", noticias); //publicación en el html

            return "formNoticia.html";
        }

        return "index.html";
    }

    @GetMapping("/modificar/{id}") //localhost:8080/noticias/modificar/(numero de id de la noiticia)
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("noticia", noticiaServicio.getOne(id)); //trae al principio la noticia a modificar

        return "noticiaModificar.html";
    }

    @PostMapping("/modificado/{id}") //localhost:8080/noticias/modificado/(numero de id de la noiticia)
    public String modificarRecibido(@PathVariable String id, String titulo, String texto, String idAutor, ModelMap modelo){
        try {
            noticiaServicio.modificarNoticia(id, titulo, texto, idAutor); //validación de datos
            
            return "index.html";
        } catch (ExcepcionesPropias ex) {
            modelo.put("error", ex.getMessage()); //envío del error al html
            return "noticiaModificar.html";
        }
    }


}
