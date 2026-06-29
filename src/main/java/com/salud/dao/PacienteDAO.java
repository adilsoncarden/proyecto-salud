package com.salud.dao;

import com.salud.model.Paciente;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public boolean insertar(Paciente paciente) {
        String sql = "INSERT INTO paciente (dni, nombre, apellido, fecha_nacimiento, telefono, direccion, tipo_seguro, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 1)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getDni());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellido());
            ps.setDate(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getTelefono());
            ps.setString(6, paciente.getDireccion());
            ps.setString(7, paciente.getTipoSeguro());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar paciente: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET dni=?, nombre=?, apellido=?, fecha_nacimiento=?, telefono=?, "
                + "direccion=?, tipo_seguro=? WHERE id=? AND activo=1";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getDni());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getApellido());
            ps.setDate(4, paciente.getFechaNacimiento());
            ps.setString(5, paciente.getTelefono());
            ps.setString(6, paciente.getDireccion());
            ps.setString(7, paciente.getTipoSeguro());
            ps.setInt(8, paciente.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar paciente: " + e.getMessage());
            return false;
        }
    }

    public boolean desactivar(int id) {
        String sql = "UPDATE paciente SET activo=0 WHERE id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar paciente: " + e.getMessage());
            return false;
        }
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT id, dni, nombre, apellido, fecha_nacimiento, telefono, direccion, tipo_seguro, activo "
                + "FROM paciente WHERE id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar paciente: " + e.getMessage());
        }
        return null;
    }

    public Paciente buscarPorDni(String dni) {
        String sql = "SELECT id, dni, nombre, apellido, fecha_nacimiento, telefono, direccion, tipo_seguro, activo "
                + "FROM paciente WHERE dni=? AND activo=1";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar paciente por DNI: " + e.getMessage());
        }
        return null;
    }

    public boolean existeDni(String dni, Integer excluirId) {
        String sql = excluirId == null
                ? "SELECT COUNT(*) FROM paciente WHERE dni=? AND activo=1"
                : "SELECT COUNT(*) FROM paciente WHERE dni=? AND id<>? AND activo=1";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dni);
            if (excluirId != null) {
                ps.setInt(2, excluirId);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar DNI: " + e.getMessage());
        }
        return false;
    }

    public List<Paciente> listar() {
        return buscarPorTexto(null);
    }

    public List<Paciente> buscarPorTexto(String texto) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT id, dni, nombre, apellido, fecha_nacimiento, telefono, direccion, tipo_seguro, activo "
                + "FROM paciente WHERE activo=1 ";

        boolean conFiltro = texto != null && !texto.trim().isEmpty();
        if (conFiltro) {
            sql += "AND (dni LIKE ? OR nombre LIKE ? OR apellido LIKE ?) ";
        }
        sql += "ORDER BY apellido, nombre";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            if (conFiltro) {
                String filtro = "%" + texto.trim() + "%";
                ps.setString(1, filtro);
                ps.setString(2, filtro);
                ps.setString(3, filtro);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pacientes.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    public int contarPacientesActivos() {
        String sql = "SELECT COUNT(*) FROM paciente WHERE activo=1";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al contar pacientes: " + e.getMessage());
        }
        return 0;
    }

    private Paciente mapear(ResultSet rs) throws SQLException {
        return new Paciente(
                rs.getInt("id"),
                rs.getString("dni"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getDate("fecha_nacimiento"),
                rs.getString("telefono"),
                rs.getString("direccion"),
                rs.getString("tipo_seguro"),
                rs.getBoolean("activo"));
    }
}
