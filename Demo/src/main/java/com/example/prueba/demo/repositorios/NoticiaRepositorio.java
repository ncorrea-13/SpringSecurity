package com.example.prueba.demo.repositorios;

import com.example.prueba.demo.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String>{
    
    @Query("Select n from Noticia n where n.titulo = :titulo")
    public Noticia buscarPornombre(@Param("titulo") String titulo);
    
    @Query("Select n from Noticia n where n.autor.nombre = :nombre")
    public List<Noticia> buscarPorAutorNombre(@Param("nombre") String titulo);
}
