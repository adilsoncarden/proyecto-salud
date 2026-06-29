package com.salud.model;

import java.sql.Date;

/**
 * Receta médica asociada a una atención.
 */
public class RecetaMedica {

    private int id;
    private int atencionId;
    private Date fechaEmision;
    private String medicamentos;
    private String indicaciones;

    public RecetaMedica() {
    }

    public RecetaMedica(int atencionId, Date fechaEmision, String medicamentos, String indicaciones) {
        this.atencionId = atencionId;
        this.fechaEmision = fechaEmision;
        this.medicamentos = medicamentos;
        this.indicaciones = indicaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAtencionId() {
        return atencionId;
    }

    public void setAtencionId(int atencionId) {
        this.atencionId = atencionId;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }
}
