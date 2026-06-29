package com.salud.dao;

import com.salud.model.Triaje;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TriajeDAO {

    public boolean insertar(Triaje triaje) {
        String sql = "INSERT INTO triaje (id_cita, id_enfermera_usuario, peso_kg, talla_m, presion_arterial, "
                + "frecuencia_cardiaca, frecuencia_respiratoria, temperatura_c, saturacion_oxigeno, imc, motivo_consulta) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, triaje.getCitaId());
            if (triaje.getEnfermeraUsuarioId() != null) {
                ps.setInt(2, triaje.getEnfermeraUsuarioId());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            ps.setDouble(3, triaje.getPesoKg());
            ps.setDouble(4, triaje.getTallaM());
            ps.setString(5, triaje.getPresionArterial());
            if (triaje.getFrecuenciaCardiaca() != null) {
                ps.setInt(6, triaje.getFrecuenciaCardiaca());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            if (triaje.getFrecuenciaRespiratoria() != null) {
                ps.setInt(7, triaje.getFrecuenciaRespiratoria());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }
            ps.setDouble(8, triaje.getTemperaturaC());
            if (triaje.getSaturacionOxigeno() != null) {
                ps.setInt(9, triaje.getSaturacionOxigeno());
            } else {
                ps.setNull(9, java.sql.Types.INTEGER);
            }
            ps.setDouble(10, triaje.getImc());
            ps.setString(11, triaje.getMotivoConsulta());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar triaje: " + e.getMessage());
            return false;
        }
    }

    public boolean existeTriajeParaCita(int citaId) {
        String sql = "SELECT COUNT(*) FROM triaje WHERE id_cita=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, citaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar triaje: " + e.getMessage());
        }
        return false;
    }

    public Triaje buscarPorCita(int citaId) {
        String sql = "SELECT id, id_cita, id_enfermera_usuario, peso_kg, talla_m, presion_arterial, "
                + "frecuencia_cardiaca, frecuencia_respiratoria, temperatura_c, saturacion_oxigeno, "
                + "imc, motivo_consulta, fecha_registro FROM triaje WHERE id_cita=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, citaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Triaje triaje = new Triaje();
                    triaje.setId(rs.getInt("id"));
                    triaje.setCitaId(rs.getInt("id_cita"));
                    int enfermera = rs.getInt("id_enfermera_usuario");
                    triaje.setEnfermeraUsuarioId(rs.wasNull() ? null : enfermera);
                    triaje.setPesoKg(rs.getDouble("peso_kg"));
                    triaje.setTallaM(rs.getDouble("talla_m"));
                    triaje.setPresionArterial(rs.getString("presion_arterial"));
                    int fc = rs.getInt("frecuencia_cardiaca");
                    triaje.setFrecuenciaCardiaca(rs.wasNull() ? null : fc);
                    int fr = rs.getInt("frecuencia_respiratoria");
                    triaje.setFrecuenciaRespiratoria(rs.wasNull() ? null : fr);
                    triaje.setTemperaturaC(rs.getDouble("temperatura_c"));
                    int spo2 = rs.getInt("saturacion_oxigeno");
                    triaje.setSaturacionOxigeno(rs.wasNull() ? null : spo2);
                    triaje.setImc(rs.getDouble("imc"));
                    triaje.setMotivoConsulta(rs.getString("motivo_consulta"));
                    triaje.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    return triaje;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar triaje: " + e.getMessage());
        }
        return null;
    }
}
