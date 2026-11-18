package com.mercago.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasenaHash;

    private String telefono;
    private String direccionPredeterminada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    protected Usuario() {
    }

    public Usuario(String nombre, String correo, String contrasenaHash,
                   String telefono, String direccionPredeterminada, Rol rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenaHash = contrasenaHash;
        this.telefono = telefono;
        this.direccionPredeterminada = direccionPredeterminada;
        this.rol = rol;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getContrasenaHash() { return contrasenaHash; }
    public String getTelefono() { return telefono; }
    public String getDireccionPredeterminada() { return direccionPredeterminada; }
    public Rol getRol() { return rol; }

    public void setDireccionPredeterminada(String direccionPredeterminada) {
        this.direccionPredeterminada = direccionPredeterminada;
    }
}

