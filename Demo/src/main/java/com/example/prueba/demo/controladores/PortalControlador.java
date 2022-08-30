package com.example.prueba.demo.controladores;

import com.example.prueba.demo.entidades.Noticia;
import com.example.prueba.demo.excepciones.ExcepcionesPropias;
import com.example.prueba.demo.servicio.NoticiaServicio;
import com.example.prueba.demo.servicio.UsuarioServicio;
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

    private String index = "index.html";
    
    @Autowired
    private NoticiaServicio noticiaServicio;

    @Autowired
    private UsuarioServicio userServi;

    @GetMapping("/") //localhost:8080/
    public String index(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticias();

        modelo.addAttribute("noticias", noticias); //vincular noticias con usuarios
        return index;
    }

    @GetMapping("/registrar") //localhost:8080/registrar usuarios
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro") //localhost:8080/registro usuarios
    public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo) {
        try {
            userServi.crearUsuario(nombre, email, password, password2); //creación del usuario

            modelo.put("exito", "Usuario registrado correctamente");
            
            return index;
        } catch (ExcepcionesPropias ex) {
            modelo.put("error", ex.getMessage()); //mensaje de error + información que queda
            modelo.put("nombre", nombre);
            modelo.put("email", email);
                    
            return "registro.html";
        }

    }

    @GetMapping("/login") //localhost:8080/login
    public String login(@RequestParam (required = false) String error, ModelMap modelo) {
        if (error != null){
            modelo.put("error", "Usuario o contraseña invalidos"); //mensaje de error
        }
        return "login.html";
    }
    
    @GetMapping("/logueo") //localhost:8080/logueo
    public String inicio(){
        return "login.html";
    }
}
