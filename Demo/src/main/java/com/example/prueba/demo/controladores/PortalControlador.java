package com.example.prueba.demo.controladores;

import com.example.prueba.demo.entidades.Noticia;
import com.example.prueba.demo.excepciones.ExcepcionesPropias;
import com.example.prueba.demo.servicio.NoticiaServicio;
//import com.example.prueba.demo.servicio.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador { //localhost:8080/

    @Autowired
    private NoticiaServicio noticiaServicio;

//    @Autowired
//    private UsuarioServicio userServi;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias);
        return "index.html";
    }
//
//    @GetMapping("/registrar")
//    public String registrar() {
//        return "registro.html";
//    }

//    @PostMapping("/registro")
//    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {
//        try {
//            userServi.crearUsuario(nombre, email, password, password2);
//
//            modelo.put("exito", "Usuario registrado correctamente");
//            
//            return "index.html";
//        } catch (ExcepcionesPropias ex) {
//            modelo.put("error", ex.getMessage());
//            modelo.put("nombre", nombre);
//            modelo.put("email", email);
//                    
//            return "registro.html";
//        }
//
//    }
//
//    @GetMapping("/login")
//    public String login(@RequestParam (required = false) String error, ModelMap modelo) {
//        if (error != null){
//            modelo.put("error", "Usuario o contraseña invalidos");
//        }
//        return "login.html";
//    }
//    
//    @GetMapping("/inicio")
//    public String inicio(){
//        return "index.html";
//    }
}
