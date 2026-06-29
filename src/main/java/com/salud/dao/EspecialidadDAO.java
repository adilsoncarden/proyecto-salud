package com.salud.dao;

import com.salud.model.Especialidad;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadDAO {

    public boolean insertar(Especialidad especialidad) {
        String sql = "INSERT INTO especialidad (nombre, descripcion, activo) VALUES (?, ?, 1)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, especialidad.getNombre());
            ps.setString(2, especialidad.getDescripcion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar especialidad: " + e.getMessage());
            return false;
        }
    }

    public boolean desactivar(int id) {
        String sql = "UPDATE especialidad SET activo=0 WHERE id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al desactivar especialidad: " + e.getMessage());
            return false;
        }
    }

    public List<Especialidad> listarActivas() {
        List<Especialidad> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion, activo FROM especialidad WHERE activo=1 ORDER BY nombre";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Especialidad(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getBoolean("activo")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar especialidades: " + e.getMessage());
        }
        return lista;
    }
}
