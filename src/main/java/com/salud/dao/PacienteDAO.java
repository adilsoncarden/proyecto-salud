package com.salud.dao;

import com.salud.model.Paciente;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a datos para la entidad Paciente.
 */
public class PacienteDAO {

    /**
     * Inserta un nuevo paciente en la base de datos.
     */
    public boolean insertar(Paciente paciente) {
        String sql = "INSERT INTO paciente (dni, nombre, apellido, telefono) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getDni());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellido());
            ps.setString(4, paciente.getTelefono());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene la lista completa de pacientes registrados.
     */
    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT id, dni, nombre, apellido, telefono FROM paciente ORDER BY apellido, nombre";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                pacientes.add(new Paciente(
                        rs.getInt("id"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar pacientes: " + e.getMessage());
        }

        return pacientes;
    }
}
