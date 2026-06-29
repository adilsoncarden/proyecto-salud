package com.salud.dao;

import com.salud.model.Usuario;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Acceso a datos para la entidad Usuario.
 */
public class UsuarioDAO {

    /**
     * Valida las credenciales del administrador.
     *
     * @return Usuario si las credenciales son correctas, null en caso contrario
     */
    public Usuario login(String username, String password) {
        String sql = "SELECT id, username, password FROM usuario WHERE username = ? AND password = ?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
        }

        return null;
    }
}
