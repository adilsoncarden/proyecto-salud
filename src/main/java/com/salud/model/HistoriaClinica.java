package com.salud.model;

import java.sql.Date;

/**
 * Historia clínica electrónica del paciente.
 */
public class HistoriaClinica {

    private int id;
    private int pacienteId;
    private Date fechaCreacion;
    private String tipoSangre;
    private String alergias;
    private String pacienteNombre;

    public HistoriaClinica() {
    }

    public HistoriaClinica(int id, int pacienteId, Date fechaCreacion, String tipoSangre, String alergias) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.fechaCreacion = fechaCreacion;
        this.tipoSangre = tipoSangre;
        this.alergias = alergias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }
}
