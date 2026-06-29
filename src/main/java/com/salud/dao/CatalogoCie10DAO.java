package com.salud.dao;

import com.salud.model.DiagnosticoCie10;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoCie10DAO {

    public List<DiagnosticoCie10> listarTodos() {
        List<DiagnosticoCie10> lista = new ArrayList<>();
        String sql = "SELECT codigo, descripcion FROM catalogo_cie10 ORDER BY codigo";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new DiagnosticoCie10(rs.getString("codigo"), rs.getString("descripcion")));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar CIE-10: " + e.getMessage());
        }
        return lista;
    }
}
