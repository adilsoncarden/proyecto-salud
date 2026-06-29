package com.salud.dao;

import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReporteDAO {

    public Map<String, Integer> contarCitasPorEstadoEnFecha(Date fecha) {
        Map<String, Integer> resultado = new LinkedHashMap<>();
        String sql = "SELECT estado, COUNT(*) AS total FROM cita WHERE fecha=? GROUP BY estado ORDER BY estado";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, fecha);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.put(rs.getString("estado"), rs.getInt("total"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en reporte de citas: " + e.getMessage());
        }
        return resultado;
    }

    public Map<String, Integer> contarAtencionesPorMedico(Date desde, Date hasta) {
        Map<String, Integer> resultado = new LinkedHashMap<>();
        String sql = "SELECT CONCAT(m.nombres, ' ', m.apellidos) AS medico, COUNT(*) AS total "
                + "FROM atencion_medica a JOIN medico m ON a.id_medico = m.id "
                + "WHERE DATE(a.fecha_atencion) BETWEEN ? AND ? "
                + "GROUP BY m.id, m.nombres, m.apellidos ORDER BY total DESC";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, desde);
            ps.setDate(2, hasta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    resultado.put(rs.getString("medico"), rs.getInt("total"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en reporte de atenciones: " + e.getMessage());
        }
        return resultado;
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
            System.err.println("Error en reporte de pacientes: " + e.getMessage());
        }
        return 0;
    }
}
