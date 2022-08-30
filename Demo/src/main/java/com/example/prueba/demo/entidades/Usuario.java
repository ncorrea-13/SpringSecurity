package com.example.prueba.demo.entidades;

import com.example.prueba.demo.enumeraciones.Rol;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data //autogenera los getters, setters, toString y los constructores
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String email;
    private String password;

    @Temporal(TemporalType.DATE)
    private Date alta;

    @Enumerated(EnumType.STRING)
    private Rol rol;

}
