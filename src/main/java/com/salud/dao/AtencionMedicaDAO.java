package com.salud.dao;

import com.salud.model.AtencionMedica;
import com.salud.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtencionMedicaDAO {

    private static final String SELECT_BASE = "SELECT a.id, a.id_historia, a.id_cita, a.id_medico, a.codigo_cie10, "
            + "a.diagnostico_detalle, a.anamnesis, a.examen_fisico, a.plan_tratamiento, a.fecha_atencion, "
            + "CONCAT(m.nombres, ' ', m.apellidos) AS medico_nombre, "
            + "CONCAT(p.nombre, ' ', p.apellido) AS paciente_nombre "
            + "FROM atencion_medica a "
            + "JOIN medico m ON a.id_medico = m.id "
            + "JOIN cita c ON a.id_cita = c.id "
            + "JOIN paciente p ON c.paciente_id = p.id ";

    public boolean insertar(AtencionMedica atencion) {
        String sql = "INSERT INTO atencion_medica (id_historia, id_cita, id_medico, codigo_cie10, "
                + "diagnostico_detalle, anamnesis, examen_fisico, plan_tratamiento) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, atencion.getHistoriaId());
            ps.setInt(2, atencion.getCitaId());
            ps.setInt(3, atencion.getMedicoId());
            ps.setString(4, atencion.getCodigoCie10());
            ps.setString(5, atencion.getDiagnosticoDetalle());
            ps.setString(6, atencion.getAnamnesis());
            ps.setString(7, atencion.getExamenFisico());
            ps.setString(8, atencion.getPlanTratamiento());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar atención médica: " + e.getMessage());
            return false;
        }
    }

    public int obtenerUltimoIdPorCita(int citaId) {
        String sql = "SELECT id FROM atencion_medica WHERE id_cita=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, citaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener atención: " + e.getMessage());
        }
        return -1;
    }

    public List<AtencionMedica> listarPorHistoria(int historiaId) {
        List<AtencionMedica> lista = new ArrayList<>();
        String sql = SELECT_BASE + "WHERE a.id_historia=? ORDER BY a.fecha_atencion DESC";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, historiaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar atenciones: " + e.getMessage());
        }
        return lista;
    }

    public AtencionMedica buscarPorCita(int citaId) {
        String sql = SELECT_BASE + "WHERE a.id_cita=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, citaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar atención por cita: " + e.getMessage());
        }
        return null;
    }

    private AtencionMedica mapear(ResultSet rs) throws SQLException {
        AtencionMedica atencion = new AtencionMedica();
        atencion.setId(rs.getInt("id"));
        atencion.setHistoriaId(rs.getInt("id_historia"));
        atencion.setCitaId(rs.getInt("id_cita"));
        atencion.setMedicoId(rs.getInt("id_medico"));
        atencion.setCodigoCie10(rs.getString("codigo_cie10"));
        atencion.setDiagnosticoDetalle(rs.getString("diagnostico_detalle"));
        atencion.setAnamnesis(rs.getString("anamnesis"));
        atencion.setExamenFisico(rs.getString("examen_fisico"));
        atencion.setPlanTratamiento(rs.getString("plan_tratamiento"));
        atencion.setFechaAtencion(rs.getTimestamp("fecha_atencion"));
        atencion.setMedicoNombre(rs.getString("medico_nombre"));
        atencion.setPacienteNombre(rs.getString("paciente_nombre"));
        return atencion;
    }
}
