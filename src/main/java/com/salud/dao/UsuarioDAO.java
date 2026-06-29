package com.salud.dao;

import com.salud.model.Usuario;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario login(String username, String password) {
        String sql = "SELECT id, username, password, rol, nombres, apellidos, id_medico, activo "
                + "FROM usuario WHERE username=? AND password=? AND activo=1";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
        }
        return null;
    }

    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuario (username, password, rol, nombres, apellidos, id_medico, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, 1)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getNombres());
            ps.setString(5, usuario.getApellidos());
            if (usuario.getIdMedico() != null) {
                ps.setInt(6, usuario.getIdMedico());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET username=?, password=?, rol=?, nombres=?, apellidos=?, id_medico=? "
                + "WHERE id=? AND activo=1";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getNombres());
            ps.setString(5, usuario.getApellidos());
            if (usuario.getIdMedico() != null) {
                ps.setInt(6, usuario.getIdMedico());
            } else {
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.setInt(7, usuario.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean desactivar(int id) {
        String sql = "UPDATE usuario SET activo=0 WHERE id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar usuario: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> listarActivos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, username, password, rol, nombres, apellidos, id_medico, activo "
                + "FROM usuario WHERE activo=1 ORDER BY rol, username";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    private Usuario mapear(ResultSet rs) throws SQLException {
        int idMedico = rs.getInt("id_medico");
        return new Usuario(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("rol"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.wasNull() ? null : idMedico,
                rs.getBoolean("activo"));
    }
}
