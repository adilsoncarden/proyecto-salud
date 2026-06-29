package com.salud.dao;

import com.salud.model.Medico;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    private static final String SELECT_BASE = "SELECT m.id, m.dni, m.nombres, m.apellidos, m.id_especialidad, "
            + "m.consultorio, m.activo, e.nombre AS especialidad_nombre "
            + "FROM medico m JOIN especialidad e ON m.id_especialidad = e.id ";

    public boolean insertar(Medico medico) {
        String sql = "INSERT INTO medico (dni, nombres, apellidos, id_especialidad, consultorio, activo) "
                + "VALUES (?, ?, ?, ?, ?, 1)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, medico.getDni());
            ps.setString(2, medico.getNombres());
            ps.setString(3, medico.getApellidos());
            ps.setInt(4, medico.getEspecialidadId());
            ps.setString(5, medico.getConsultorio());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar médico: " + e.getMessage());
            return false;
        }
    }

    public boolean desactivar(int id) {
        String sql = "UPDATE medico SET activo=0 WHERE id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar médico: " + e.getMessage());
            return false;
        }
    }

    public Medico buscarPorId(int id) {
        String sql = SELECT_BASE + "WHERE m.id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar médico: " + e.getMessage());
        }
        return null;
    }

    public List<Medico> listarActivos() {
        return listarPorEspecialidad(null);
    }

    public List<Medico> listarPorEspecialidad(Integer especialidadId) {
        List<Medico> medicos = new ArrayList<>();
        String sql = SELECT_BASE + "WHERE m.activo=1 ";
        if (especialidadId != null) {
            sql += "AND m.id_especialidad=? ";
        }
        sql += "ORDER BY m.apellidos, m.nombres";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            if (especialidadId != null) {
                ps.setInt(1, especialidadId);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    medicos.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar médicos: " + e.getMessage());
        }
        return medicos;
    }

    private Medico mapear(ResultSet rs) throws SQLException {
        Medico medico = new Medico(
                rs.getInt("id"),
                rs.getString("dni"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getInt("id_especialidad"),
                rs.getString("consultorio"),
                rs.getBoolean("activo"));
        medico.setEspecialidadNombre(rs.getString("especialidad_nombre"));
        return medico;
    }
}
