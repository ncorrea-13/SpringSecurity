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
import lombok.Data;

@Entity
@Data //autogenera los getters, setters, toString y los constructores
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

}
