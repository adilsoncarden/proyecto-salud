package com.salud.model;

import java.sql.Timestamp;

/**
 * Registro de triaje previo a la consulta médica.
 */
public class Triaje {

    private int id;
    private int citaId;
    private Integer enfermeraUsuarioId;
    private double pesoKg;
    private double tallaM;
    private String presionArterial;
    private Integer frecuenciaCardiaca;
    private Integer frecuenciaRespiratoria;
    private double temperaturaC;
    private Integer saturacionOxigeno;
    private double imc;
    private String motivoConsulta;
    private Timestamp fechaRegistro;
    private String pacienteNombre;

    public Triaje() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCitaId() {
        return citaId;
    }

    public void setCitaId(int citaId) {
        this.citaId = citaId;
    }

    public Integer getEnfermeraUsuarioId() {
        return enfermeraUsuarioId;
    }

    public void setEnfermeraUsuarioId(Integer enfermeraUsuarioId) {
        this.enfermeraUsuarioId = enfermeraUsuarioId;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public double getTallaM() {
        return tallaM;
    }

    public void setTallaM(double tallaM) {
        this.tallaM = tallaM;
    }

    public String getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(String presionArterial) {
        this.presionArterial = presionArterial;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public Integer getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(Integer frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public double getTemperaturaC() {
        return temperaturaC;
    }

    public void setTemperaturaC(double temperaturaC) {
        this.temperaturaC = temperaturaC;
    }

    public Integer getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(Integer saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }
}
