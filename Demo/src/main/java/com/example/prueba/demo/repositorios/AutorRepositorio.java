package com.example.prueba.demo.repositorios;

import com.example.prueba.demo.entidades.Autor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    
    @Query("Select a from Autor a where a.nombre = :nombre")
    public Optional<Autor> buscarPorNombre(@Param("nombre") String nombre);

}
