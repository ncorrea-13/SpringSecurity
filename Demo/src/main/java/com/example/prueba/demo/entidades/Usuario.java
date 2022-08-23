//package com.example.prueba.demo.entidades;
//
//import com.example.prueba.demo.enumeraciones.Rol;
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//public class Usuario implements Serializable {
//
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    private String id;
//    private String nombre;
//    private String email;
//    private String password;
//
//    @Temporal(TemporalType.DATE)
//    private Date alta;
//
//    @Enumerated(EnumType.STRING)
//    private Rol rol;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Date getAlta() {
//        return alta;
//    }
//
//    public void setAlta(Date alta) {
//        this.alta = alta;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Rol getRol() {
//        return rol;
//    }
//
//    public void setRol(Rol rol) {
//        this.rol = rol;
//    }
//
//    @Override
//    public String toString() {
//        return "com.example.prueba.demo.entidades.Admin[ id=" + id + " ]";
//    }
//
//}
