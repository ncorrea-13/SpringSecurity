package com.example.prueba.demo.repositorios;

import com.example.prueba.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepositorio extends CrudRepository<Usuario, String> {
    
    @Query("Select u from Usuario u where u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
}
