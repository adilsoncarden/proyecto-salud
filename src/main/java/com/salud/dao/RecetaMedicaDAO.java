package com.salud.dao;

import com.salud.model.RecetaMedica;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecetaMedicaDAO {

    public boolean insertar(RecetaMedica receta) {
        String sql = "INSERT INTO receta_medica (id_atencion, fecha_emision, medicamentos, indicaciones) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, receta.getAtencionId());
            ps.setDate(2, receta.getFechaEmision());
            ps.setString(3, receta.getMedicamentos());
            ps.setString(4, receta.getIndicaciones());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar receta: " + e.getMessage());
            return false;
        }
    }

    public RecetaMedica buscarPorAtencion(int atencionId) {
        String sql = "SELECT id, id_atencion, fecha_emision, medicamentos, indicaciones "
                + "FROM receta_medica WHERE id_atencion=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, atencionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    RecetaMedica receta = new RecetaMedica();
                    receta.setId(rs.getInt("id"));
                    receta.setAtencionId(rs.getInt("id_atencion"));
                    receta.setFechaEmision(rs.getDate("fecha_emision"));
                    receta.setMedicamentos(rs.getString("medicamentos"));
                    receta.setIndicaciones(rs.getString("indicaciones"));
                    return receta;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar receta: " + e.getMessage());
        }
        return null;
    }
}
