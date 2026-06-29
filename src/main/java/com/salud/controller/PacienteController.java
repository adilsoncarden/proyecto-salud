package com.salud.controller;

import com.salud.dao.HistoriaClinicaDAO;
import com.salud.dao.PacienteDAO;
import com.salud.model.Paciente;
import com.salud.view.HistoriaClinicaFrame;
import com.salud.view.MenuFrame;
import com.salud.view.PacienteFormFrame;
import com.salud.view.PacienteListFrame;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PacienteController {

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO();

    public void abrirRegistro() {
        PacienteFormFrame frame = new PacienteFormFrame("Registrar Paciente", null);
        frame.getBtnGuardar().addActionListener(e -> guardarNuevo(frame));
        frame.getBtnCancelar().addActionListener(e -> volverMenu(frame));
        frame.setVisible(true);
    }

    public void abrirListado() {
        PacienteListFrame frame = new PacienteListFrame();
        configurarListado(frame);
        frame.setVisible(true);
    }

    public void configurarListado(PacienteListFrame frame) {
        recargarTabla(frame, null);

        frame.getBtnBuscar().addActionListener(e ->
                recargarTabla(frame, frame.getTxtBuscar().getText()));

        frame.getBtnEditar().addActionListener(e -> editarSeleccionado(frame));

        frame.getBtnDesactivar().addActionListener(e -> desactivarSeleccionado(frame));

        frame.getBtnHistoria().addActionListener(e -> verHistoria(frame));

        frame.getBtnVolver().addActionListener(e -> volverMenu(frame));
    }

    private void guardarNuevo(PacienteFormFrame frame) {
        Paciente paciente = leerFormulario(frame);
        String error = ValidacionHelper.validarPaciente(
                paciente.getDni(), paciente.getNombre(), paciente.getApellido(),
                paciente.getTelefono(), textoFecha(frame), paciente.getTipoSeguro());
        if (error != null) {
            JOptionPane.showMessageDialog(frame, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (pacienteDAO.existeDni(paciente.getDni(), null)) {
            JOptionPane.showMessageDialog(frame, "El DNI ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (pacienteDAO.insertar(paciente)) {
            int idPaciente = pacienteDAO.buscarPorDni(paciente.getDni()).getId();
            historiaClinicaDAO.crearSiNoExiste(idPaciente);
            JOptionPane.showMessageDialog(frame, "Paciente registrado correctamente.");
            frame.dispose();
            new MenuFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(frame, "No se pudo registrar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarSeleccionado(PacienteListFrame frame) {
        int fila = frame.getTabla().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(frame, "Seleccione un paciente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) frame.getModelo().getValueAt(fila, 0);
        Paciente paciente = pacienteDAO.buscarPorId(id);
        if (paciente == null) {
            return;
        }

        PacienteFormFrame form = new PacienteFormFrame("Editar Paciente", paciente);
        form.getBtnGuardar().addActionListener(e -> {
            Paciente actualizado = leerFormulario(form);
            actualizado.setId(paciente.getId());
            String error = ValidacionHelper.validarPaciente(
                    actualizado.getDni(), actualizado.getNombre(), actualizado.getApellido(),
                    actualizado.getTelefono(), textoFecha(form), actualizado.getTipoSeguro());
            if (error != null) {
                JOptionPane.showMessageDialog(form, error, "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (pacienteDAO.existeDni(actualizado.getDni(), actualizado.getId())) {
                JOptionPane.showMessageDialog(form, "El DNI ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (pacienteDAO.actualizar(actualizado)) {
                JOptionPane.showMessageDialog(form, "Paciente actualizado.");
                form.dispose();
                recargarTabla(frame, frame.getTxtBuscar().getText());
            } else {
                JOptionPane.showMessageDialog(form, "No se pudo actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        form.getBtnCancelar().addActionListener(e -> form.dispose());
        form.setVisible(true);
    }

    private void desactivarSeleccionado(PacienteListFrame frame) {
        int fila = frame.getTabla().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(frame, "Seleccione un paciente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(frame,
                "¿Confirma desactivar este paciente?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        int id = (int) frame.getModelo().getValueAt(fila, 0);
        if (pacienteDAO.desactivar(id)) {
            JOptionPane.showMessageDialog(frame, "Paciente desactivado.");
            recargarTabla(frame, frame.getTxtBuscar().getText());
        }
    }

    private void verHistoria(PacienteListFrame frame) {
        int fila = frame.getTabla().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(frame, "Seleccione un paciente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) frame.getModelo().getValueAt(fila, 0);
        new HistoriaClinicaController().abrirHistoria(id);
    }

    public void recargarTabla(PacienteListFrame frame, String filtro) {
        frame.getModelo().setRowCount(0);
        for (Paciente p : pacienteDAO.buscarPorTexto(filtro)) {
            frame.getModelo().addRow(new Object[] {
                    p.getId(), p.getDni(), p.getNombre(), p.getApellido(),
                    p.getTelefono() != null ? p.getTelefono() : "",
                    p.getTipoSeguro() != null ? p.getTipoSeguro() : ""
            });
        }
    }

    private Paciente leerFormulario(PacienteFormFrame frame) {
        Paciente p = new Paciente();
        p.setDni(frame.getTxtDni().getText().trim());
        p.setNombre(frame.getTxtNombre().getText().trim());
        p.setApellido(frame.getTxtApellido().getText().trim());
        p.setTelefono(frame.getTxtTelefono().getText().trim());
        p.setDireccion(frame.getTxtDireccion().getText().trim());
        p.setTipoSeguro((String) frame.getCmbSeguro().getSelectedItem());
        String fecha = frame.getTxtFechaNacimiento().getText().trim();
        if (!fecha.isEmpty()) {
            p.setFechaNacimiento(Date.valueOf(LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE)));
        }
        return p;
    }

    private String textoFecha(PacienteFormFrame frame) {
        return frame.getTxtFechaNacimiento().getText().trim();
    }

    private void volverMenu(PacienteFormFrame frame) {
        frame.dispose();
        new MenuFrame().setVisible(true);
    }

    private void volverMenu(PacienteListFrame frame) {
        frame.dispose();
        new MenuFrame().setVisible(true);
    }
}
