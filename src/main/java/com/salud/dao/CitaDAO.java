package com.salud.dao;

import com.salud.model.Cita;
import com.salud.util.Conexion;
import com.salud.util.EstadoCita;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    private static final String SELECT_BASE = "SELECT c.id, c.paciente_id, c.id_medico, c.id_especialidad, "
            + "c.fecha, c.hora, c.estado, c.consultorio, c.motivo_cancelacion, "
            + "CONCAT(p.nombre, ' ', p.apellido) AS paciente_nombre, "
            + "CONCAT(m.nombres, ' ', m.apellidos) AS medico_nombre, e.nombre AS especialidad_nombre "
            + "FROM cita c "
            + "JOIN paciente p ON c.paciente_id = p.id "
            + "JOIN medico m ON c.id_medico = m.id "
            + "JOIN especialidad e ON c.id_especialidad = e.id ";

    public boolean crear(Cita cita) {
        String sql = "INSERT INTO cita (paciente_id, id_medico, id_especialidad, fecha, hora, estado, consultorio) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cita.getPacienteId());
            ps.setInt(2, cita.getMedicoId());
            ps.setInt(3, cita.getEspecialidadId());
            ps.setDate(4, cita.getFecha());
            ps.setTime(5, cita.getHora());
            ps.setString(6, cita.getEstado() != null ? cita.getEstado() : EstadoCita.PROGRAMADA);
            ps.setString(7, cita.getConsultorio());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear cita: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarEstado(int idCita, String estado) {
        String sql = "UPDATE cita SET estado=? WHERE id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, idCita);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado de cita: " + e.getMessage());
            return false;
        }
    }

    public boolean reprogramar(int idCita, Date fecha, Time hora) {
        String sql = "UPDATE cita SET fecha=?, hora=? WHERE id=? AND estado NOT IN ('ATENDIDA','CANCELADA')";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, fecha);
            ps.setTime(2, hora);
            ps.setInt(3, idCita);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al reprogramar cita: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelar(int idCita, String motivo) {
        String sql = "UPDATE cita SET estado=?, motivo_cancelacion=? WHERE id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, EstadoCita.CANCELADA);
            ps.setString(2, motivo);
            ps.setInt(3, idCita);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al cancelar cita: " + e.getMessage());
            return false;
        }
    }

    public boolean existeConflictoHorario(int medicoId, Date fecha, Time hora, Integer excluirCitaId) {
        String sql = "SELECT COUNT(*) FROM cita WHERE id_medico=? AND fecha=? AND hora=? "
                + "AND estado NOT IN ('CANCELADA')";
        if (excluirCitaId != null) {
            sql += " AND id<>?";
        }

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, medicoId);
            ps.setDate(2, fecha);
            ps.setTime(3, hora);
            if (excluirCitaId != null) {
                ps.setInt(4, excluirCitaId);
            }

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar conflicto de horario: " + e.getMessage());
        }
        return false;
    }

    public Cita buscarPorId(int id) {
        String sql = SELECT_BASE + "WHERE c.id=?";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cita: " + e.getMessage());
        }
        return null;
    }

    public List<Cita> listarPorFecha(Date fecha) {
        return listarConFiltros(fecha, null, null);
    }

    public List<Cita> listarPorEstado(String estado) {
        return listarConFiltros(null, null, estado);
    }

    public List<Cita> listarPorPaciente(int pacienteId) {
        List<Cita> citas = new ArrayList<>();
        String sql = SELECT_BASE + "WHERE c.paciente_id=? ORDER BY c.fecha DESC, c.hora DESC";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, pacienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    citas.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar citas del paciente: " + e.getMessage());
        }
        return citas;
    }

    public List<Cita> listarConFiltros(Date fecha, Integer especialidadId, String estado) {
        List<Cita> citas = new ArrayList<>();
        StringBuilder sql = new StringBuilder(SELECT_BASE + "WHERE 1=1 ");

        if (fecha != null) {
            sql.append("AND c.fecha=? ");
        }
        if (especialidadId != null) {
            sql.append("AND c.id_especialidad=? ");
        }
        if (estado != null && !estado.isEmpty()) {
            sql.append("AND c.estado=? ");
        }
        sql.append("ORDER BY c.fecha, c.hora");

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int i = 1;
            if (fecha != null) {
                ps.setDate(i++, fecha);
            }
            if (especialidadId != null) {
                ps.setInt(i++, especialidadId);
            }
            if (estado != null && !estado.isEmpty()) {
                ps.setString(i, estado);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    citas.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar citas: " + e.getMessage());
        }
        return citas;
    }

    public List<Cita> listarPorMedicoYEstado(int medicoId, String estado) {
        List<Cita> citas = new ArrayList<>();
        String sql = SELECT_BASE + "WHERE c.id_medico=? AND c.estado=? ORDER BY c.fecha, c.hora";

        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, medicoId);
            ps.setString(2, estado);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    citas.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar citas del médico: " + e.getMessage());
        }
        return citas;
    }

    private Cita mapear(ResultSet rs) throws SQLException {
        Cita cita = new Cita();
        cita.setId(rs.getInt("id"));
        cita.setPacienteId(rs.getInt("paciente_id"));
        cita.setMedicoId(rs.getInt("id_medico"));
        cita.setEspecialidadId(rs.getInt("id_especialidad"));
        cita.setFecha(rs.getDate("fecha"));
        cita.setHora(rs.getTime("hora"));
        cita.setEstado(rs.getString("estado"));
        cita.setConsultorio(rs.getString("consultorio"));
        cita.setMotivoCancelacion(rs.getString("motivo_cancelacion"));
        cita.setPacienteNombre(rs.getString("paciente_nombre"));
        cita.setMedicoNombre(rs.getString("medico_nombre"));
        cita.setEspecialidadNombre(rs.getString("especialidad_nombre"));
        return cita;
    }
}
