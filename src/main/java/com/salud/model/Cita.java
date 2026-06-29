package com.salud.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Modelo de la entidad Cita médica.
 */
public class Cita {

    private int id;
    private Date fecha;
    private Time hora;
    private int pacienteId;

    public Cita() {
    }

    public Cita(int id, Date fecha, Time hora, int pacienteId) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.pacienteId = pacienteId;
    }

    public Cita(Date fecha, Time hora, int pacienteId) {
        this.fecha = fecha;
        this.hora = hora;
        this.pacienteId = pacienteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }
}
