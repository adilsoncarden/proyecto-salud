package com.salud.model;

/**
 * Código CIE-10 del catálogo auxiliar.
 */
public class DiagnosticoCie10 {

    private String codigo;
    private String descripcion;

    public DiagnosticoCie10() {
    }

    public DiagnosticoCie10(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return codigo + " - " + descripcion;
    }
}
