package com.salud.controller;

import com.salud.dao.CitaDAO;
import com.salud.dao.PacienteDAO;
import com.salud.model.Cita;
import com.salud.model.Paciente;
import com.salud.view.CitaForm;
import com.salud.view.UIStyles;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controlador del módulo de citas médicas.
 */
public class CitaController {

    private final CitaDAO citaDAO;
    private final PacienteDAO pacienteDAO;

    public CitaController() {
        this.citaDAO = new CitaDAO();
        this.pacienteDAO = new PacienteDAO();
    }

    /**
     * Configura el formulario de registro de citas.
     */
    public void configurarFormulario(CitaForm form) {
        boolean hayPacientes = cargarPacientes(form);
        form.getBtnRegistrar().setEnabled(false);

        form.getBtnRegistrar().addActionListener(e -> registrarCita(form));
        form.getBtnVolver().addActionListener(e -> {
            form.dispose();
            new com.salud.view.MenuFrame().setVisible(true);
        });

        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarEstadoBoton(form);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarEstadoBoton(form);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarEstadoBoton(form);
            }
        };

        form.getTxtFecha().getDocument().addDocumentListener(listener);
        form.getTxtHora().getDocument().addDocumentListener(listener);
        form.getCmbPaciente().addActionListener(e -> actualizarEstadoBoton(form));

        if (hayPacientes) {
            javax.swing.SwingUtilities.invokeLater(() -> form.getTxtFecha().requestFocusInWindow());
        }
    }

    private boolean cargarPacientes(CitaForm form) {
        List<Paciente> pacientes = pacienteDAO.listar();
        DefaultComboBoxModel<Paciente> model = new DefaultComboBoxModel<>();

        for (Paciente paciente : pacientes) {
            model.addElement(paciente);
        }

        form.getCmbPaciente().setModel(model);

        if (pacientes.isEmpty()) {
            form.getBtnRegistrar().setEnabled(false);
            JOptionPane.showMessageDialog(form,
                    "No hay pacientes registrados. Registre un paciente antes de crear una cita.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void actualizarEstadoBoton(CitaForm form) {
        Paciente paciente = (Paciente) form.getCmbPaciente().getSelectedItem();
        boolean valido = ValidacionHelper.esCitaValida(
                obtenerFecha(form),
                obtenerHora(form),
                paciente);
        form.getBtnRegistrar().setEnabled(valido);
    }

    private void registrarCita(CitaForm form) {
        String fechaTexto = obtenerFecha(form);
        String horaTexto = obtenerHora(form);
        Paciente paciente = (Paciente) form.getCmbPaciente().getSelectedItem();

        String error = ValidacionHelper.validarCita(fechaTexto, horaTexto, paciente);
        if (error != null) {
            JOptionPane.showMessageDialog(form,
                    error,
                    "Datos inválidos",
                    JOptionPane.ERROR_MESSAGE);
            actualizarEstadoBoton(form);
            return;
        }

        LocalDate fecha = LocalDate.parse(fechaTexto, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime hora = LocalTime.parse(horaTexto, DateTimeFormatter.ofPattern("HH:mm"));

        Cita cita = new Cita(Date.valueOf(fecha), Time.valueOf(hora), paciente.getId());
        boolean creada = citaDAO.crear(cita);

        if (creada) {
            JOptionPane.showMessageDialog(form,
                    "Cita registrada correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario(form);
            form.getTxtFecha().requestFocusInWindow();
        } else {
            JOptionPane.showMessageDialog(form,
                    "No se pudo registrar la cita. Verifique la conexión con la base de datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerFecha(CitaForm form) {
        return UIStyles.obtenerTexto(form.getTxtFecha(), CitaForm.PLACEHOLDER_FECHA);
    }

    private String obtenerHora(CitaForm form) {
        return UIStyles.obtenerTexto(form.getTxtHora(), CitaForm.PLACEHOLDER_HORA);
    }

    private void limpiarFormulario(CitaForm form) {
        restaurarPlaceholder(form.getTxtFecha(), CitaForm.PLACEHOLDER_FECHA);
        restaurarPlaceholder(form.getTxtHora(), CitaForm.PLACEHOLDER_HORA);
        actualizarEstadoBoton(form);
    }

    private void restaurarPlaceholder(javax.swing.JTextField campo, String placeholder) {
        campo.setForeground(UIStyles.COLOR_PLACEHOLDER);
        campo.setText(placeholder);
    }
}
