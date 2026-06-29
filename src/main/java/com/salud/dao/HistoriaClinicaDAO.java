package com.salud.dao;

import com.salud.model.HistoriaClinica;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoriaClinicaDAO {

    public HistoriaClinica buscarPorPaciente(int pacienteId) {
        String sql = "SELECT h.id, h.id_paciente, h.fecha_creacion, h.tipo_sangre, h.alergias, "
                + "CONCAT(p.nombre, ' ', p.apellido) AS paciente_nombre "
                + "FROM historia_clinica h JOIN paciente p ON h.id_paciente = p.id "
                + "WHERE h.id_paciente=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pacienteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar historia clínica: " + e.getMessage());
        }
        return null;
    }

    public boolean crearSiNoExiste(int pacienteId) {
        if (buscarPorPaciente(pacienteId) != null) {
            return true;
        }

        String sql = "INSERT INTO historia_clinica (id_paciente, fecha_creacion) VALUES (?, ?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pacienteId);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear historia clínica: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarAntecedentes(int id, String tipoSangre, String alergias) {
        String sql = "UPDATE historia_clinica SET tipo_sangre=?, alergias=? WHERE id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipoSangre);
            ps.setString(2, alergias);
            ps.setInt(3, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar historia clínica: " + e.getMessage());
            return false;
        }
    }

    private HistoriaClinica mapear(ResultSet rs) throws SQLException {
        HistoriaClinica historia = new HistoriaClinica(
                rs.getInt("id"),
                rs.getInt("id_paciente"),
                rs.getDate("fecha_creacion"),
                rs.getString("tipo_sangre"),
                rs.getString("alergias"));
        historia.setPacienteNombre(rs.getString("paciente_nombre"));
        return historia;
    }
}
