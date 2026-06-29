package com.salud.dao;

import com.salud.model.Cita;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Acceso a datos para la entidad Cita.
 */
public class CitaDAO {

    /**
     * Registra una nueva cita médica.
     */
    public boolean crear(Cita cita) {
        String sql = "INSERT INTO cita (fecha, hora, paciente_id) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, cita.getFecha());
            ps.setTime(2, cita.getHora());
            ps.setInt(3, cita.getPacienteId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear cita: " + e.getMessage());
            return false;
        }
    }
}
