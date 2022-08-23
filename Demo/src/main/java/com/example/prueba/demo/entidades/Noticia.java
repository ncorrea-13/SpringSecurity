package com.example.prueba.demo.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Noticia implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String titulo;
    private String textoAcortado;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String texto;

    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String foto;

    @ManyToOne
    private Autor autor;

    @Temporal(TemporalType.DATE)
    private Date alta;

    @Temporal(TemporalType.DATE)
    private Date modificacion;

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Date getAlta() {
        return alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public Date getModificacion() {
        return modificacion;
    }

    public String getTexto() {
        return texto;
    }

    public String getTextoAcortado() {
        return textoAcortado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setTextoAcortado(String textoAcortado) {
        this.textoAcortado = textoAcortado;
    }

    public void setModificacion(Date modificacion) {
        this.modificacion = modificacion;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticia)) {
            return false;
        }
        Noticia other = (Noticia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.prueba.nueva.entidades.Libro[ id=" + id + " ]";
    }

}
