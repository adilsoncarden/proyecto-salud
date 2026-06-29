package com.salud.model;

/**
 * Modelo de la entidad Usuario del sistema.
 */
public class Usuario {

    private int id;
    private String username;
    private String password;
    private String rol;
    private String nombres;
    private String apellidos;
    private Integer idMedico;
    private boolean activo;

    public Usuario() {
        this.activo = true;
    }

    public Usuario(int id, String username, String password, String rol, String nombres,
            String apellidos, Integer idMedico, boolean activo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.idMedico = idMedico;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombreCompleto() {
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }

    @Override
    public String toString() {
        return username + " (" + rol + ")";
    }
}
