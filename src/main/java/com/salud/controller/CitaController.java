package com.salud.controller;

import com.salud.dao.CitaDAO;
import com.salud.dao.EspecialidadDAO;
import com.salud.dao.MedicoDAO;
import com.salud.dao.PacienteDAO;
import com.salud.model.Cita;
import com.salud.model.Especialidad;
import com.salud.model.Medico;
import com.salud.model.Paciente;
import com.salud.util.EstadoCita;
import com.salud.view.CitaFormFrame;
import com.salud.view.CitaListFrame;
import com.salud.view.MenuFrame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CitaController {

    private final CitaDAO citaDAO = new CitaDAO();
    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final EspecialidadDAO especialidadDAO = new EspecialidadDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();

    public void abrirRegistro() {
        CitaFormFrame frame = new CitaFormFrame();
        cargarCombos(frame);
        frame.getBtnGuardar().addActionListener(e -> registrarCita(frame));
        frame.getBtnCancelar().addActionListener(e -> volverMenu(frame));
        frame.getCmbEspecialidad().addActionListener(e -> cargarMedicos(frame));
        frame.setVisible(true);
    }

    public void abrirGestion() {
        CitaListFrame frame = new CitaListFrame();
        cargarFiltros(frame);
        recargarTabla(frame);
        frame.getBtnFiltrar().addActionListener(e -> recargarTabla(frame));
        frame.getBtnConfirmarLlegada().addActionListener(e -> confirmarLlegada(frame));
        frame.getBtnReprogramar().addActionListener(e -> reprogramar(frame));
        frame.getBtnCancelar().addActionListener(e -> cancelar(frame));
        frame.getBtnVolver().addActionListener(e -> volverMenu(frame));
        frame.setVisible(true);
    }

    private void registrarCita(CitaFormFrame frame) {
        Paciente paciente = (Paciente) frame.getCmbPaciente().getSelectedItem();
        Especialidad especialidad = (Especialidad) frame.getCmbEspecialidad().getSelectedItem();
        Medico medico = (Medico) frame.getCmbMedico().getSelectedItem();
        String fechaTxt = frame.getTxtFecha().getText().trim();
        String horaTxt = frame.getTxtHora().getText().trim();

        Integer medicoId = medico != null ? medico.getId() : null;
        Integer espId = especialidad != null ? especialidad.getId() : null;

        String error = ValidacionHelper.validarCita(fechaTxt, horaTxt, paciente, medicoId, espId);
        if (error != null) {
            JOptionPane.showMessageDialog(frame, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LocalDate fecha = LocalDate.parse(fechaTxt, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime hora = LocalTime.parse(horaTxt, DateTimeFormatter.ofPattern("HH:mm"));
        Date sqlFecha = Date.valueOf(fecha);
        Time sqlHora = Time.valueOf(hora);

        if (citaDAO.existeConflictoHorario(medicoId, sqlFecha, sqlHora, null)) {
            JOptionPane.showMessageDialog(frame, "El médico ya tiene cita en ese horario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cita cita = new Cita(sqlFecha, sqlHora, paciente.getId(), medicoId, espId,
                EstadoCita.PROGRAMADA, medico.getConsultorio());

        if (citaDAO.crear(cita)) {
            String comprobante = "Cita registrada\nPaciente: " + paciente + "\nMédico: " + medico
                    + "\nFecha: " + fechaTxt + " Hora: " + horaTxt + "\nEstado: PROGRAMADA";
            JOptionPane.showMessageDialog(frame, comprobante, "Comprobante", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new MenuFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "No se pudo registrar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarLlegada(CitaListFrame frame) {
        Cita cita = obtenerSeleccionada(frame);
        if (cita == null) {
            return;
        }
        if (!EstadoCita.PROGRAMADA.equals(cita.getEstado())) {
            JOptionPane.showMessageDialog(frame, "Solo citas PROGRAMADA pueden confirmarse.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (citaDAO.actualizarEstado(cita.getId(), EstadoCita.POR_TRIAR)) {
            JOptionPane.showMessageDialog(frame, "Llegada confirmada. Estado: POR_TRIAR");
            recargarTabla(frame);
        }
    }

    private void reprogramar(CitaListFrame frame) {
        Cita cita = obtenerSeleccionada(frame);
        if (cita == null) {
            return;
        }
        String nuevaFecha = JOptionPane.showInputDialog(frame, "Nueva fecha (yyyy-MM-dd):", cita.getFecha());
        String nuevaHora = JOptionPane.showInputDialog(frame, "Nueva hora (HH:mm):", cita.getHora());
        if (ValidacionHelper.esVacio(nuevaFecha) || ValidacionHelper.esVacio(nuevaHora)) {
            return;
        }
        String error = ValidacionHelper.validarCita(nuevaFecha, nuevaHora, new Paciente(), cita.getMedicoId(), cita.getEspecialidadId());
        if (error != null) {
            JOptionPane.showMessageDialog(frame, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Date f = Date.valueOf(LocalDate.parse(nuevaFecha));
        Time h = Time.valueOf(LocalTime.parse(nuevaHora, DateTimeFormatter.ofPattern("HH:mm")));
        if (citaDAO.existeConflictoHorario(cita.getMedicoId(), f, h, cita.getId())) {
            JOptionPane.showMessageDialog(frame, "Conflicto de horario.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (citaDAO.reprogramar(cita.getId(), f, h)) {
            JOptionPane.showMessageDialog(frame, "Cita reprogramada.");
            recargarTabla(frame);
        }
    }

    private void cancelar(CitaListFrame frame) {
        Cita cita = obtenerSeleccionada(frame);
        if (cita == null) {
            return;
        }
        int ok = JOptionPane.showConfirmDialog(frame, "¿Cancelar esta cita?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ok != JOptionPane.YES_OPTION) {
            return;
        }
        String motivo = JOptionPane.showInputDialog(frame, "Motivo de cancelación:");
        if (ValidacionHelper.esVacio(motivo)) {
            JOptionPane.showMessageDialog(frame, "Debe indicar el motivo.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (citaDAO.cancelar(cita.getId(), motivo)) {
            JOptionPane.showMessageDialog(frame, "Cita cancelada.");
            recargarTabla(frame);
        }
    }

    private Cita obtenerSeleccionada(CitaListFrame frame) {
        int fila = frame.getTabla().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(frame, "Seleccione una cita.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        int id = (int) frame.getModelo().getValueAt(fila, 0);
        return citaDAO.buscarPorId(id);
    }

    public void recargarTabla(CitaListFrame frame) {
        frame.getModelo().setRowCount(0);
        String estado = (String) frame.getCmbEstado().getSelectedItem();
        if ("TODOS".equals(estado)) {
            estado = null;
        }
        Integer espId = null;
        Especialidad esp = (Especialidad) frame.getCmbEspecialidad().getSelectedItem();
        if (esp != null && esp.getId() > 0) {
            espId = esp.getId();
        }
        String fechaTxt = frame.getTxtFecha().getText().trim();
        Date fecha = fechaTxt.isEmpty() ? null : Date.valueOf(LocalDate.parse(fechaTxt));

        for (Cita c : citaDAO.listarConFiltros(fecha, espId, estado)) {
            frame.getModelo().addRow(new Object[] {
                    c.getId(), c.getPacienteNombre(), c.getMedicoNombre(),
                    c.getEspecialidadNombre(), c.getFecha(), c.getHora(), c.getEstado()
            });
        }
    }

    private void cargarCombos(CitaFormFrame frame) {
        DefaultComboBoxModel<Paciente> pacientes = new DefaultComboBoxModel<>();
        for (Paciente p : pacienteDAO.listar()) {
            pacientes.addElement(p);
        }
        frame.getCmbPaciente().setModel(pacientes);

        DefaultComboBoxModel<Especialidad> especialidades = new DefaultComboBoxModel<>();
        for (Especialidad e : especialidadDAO.listarActivas()) {
            especialidades.addElement(e);
        }
        frame.getCmbEspecialidad().setModel(especialidades);
        cargarMedicos(frame);
    }

    private void cargarMedicos(CitaFormFrame frame) {
        Especialidad esp = (Especialidad) frame.getCmbEspecialidad().getSelectedItem();
        DefaultComboBoxModel<Medico> medicos = new DefaultComboBoxModel<>();
        if (esp != null) {
            for (Medico m : medicoDAO.listarPorEspecialidad(esp.getId())) {
                medicos.addElement(m);
            }
        }
        frame.getCmbMedico().setModel(medicos);
    }

    private void cargarFiltros(CitaListFrame frame) {
        DefaultComboBoxModel<Especialidad> especialidades = new DefaultComboBoxModel<>();
        especialidades.addElement(new Especialidad(0, "TODAS", "", true));
        for (Especialidad e : especialidadDAO.listarActivas()) {
            especialidades.addElement(e);
        }
        frame.getCmbEspecialidad().setModel(especialidades);
    }

    private void volverMenu(CitaFormFrame frame) {
        frame.dispose();
        new MenuFrame().setVisible(true);
    }

    private void volverMenu(CitaListFrame frame) {
        frame.dispose();
        new MenuFrame().setVisible(true);
    }
}
