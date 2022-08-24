package com.example.prueba.demo.servicio;

import com.example.prueba.demo.entidades.Usuario;
import com.example.prueba.demo.enumeraciones.Rol;
import com.example.prueba.demo.excepciones.ExcepcionesPropias;
import com.example.prueba.demo.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio userRep;

    @Transactional
    public void crearUsuario(String nombre, String email, String password, String password2) throws ExcepcionesPropias {

        validarExcepciones(nombre, email, password, password2);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));

        usuario.setAlta(new Date());

        usuario.setRol(Rol.USER);

        userRep.save(usuario);
    }

    public void validarExcepciones(String nombre, String email, String password, String password2) throws ExcepcionesPropias {
        if (nombre.isEmpty()) {
            throw new ExcepcionesPropias("El título debe ser completado");
        }

        if (email.isEmpty()) {
            throw new ExcepcionesPropias("El email debe ser completado");
        }

        if (password.isEmpty()|| password.length() <= 5) {
            throw new ExcepcionesPropias("Debe dejar una contraseña y debe poseer más de 5 dígitos");
        }

        if (password2.isEmpty()) {
            throw new ExcepcionesPropias("Debe repetir su contraseña");
        }

        if (!password.equals(password2)) {
            throw new ExcepcionesPropias("Su contraseña no coincide");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = userRep.buscarPorEmail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE " + usuario.getRol().toString()); //ROLLE_USER

            permisos.add(p);

            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            return null;
        }

    }
}
