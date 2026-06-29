package com.salud.controller;

import com.salud.dao.PacienteDAO;
import com.salud.model.Paciente;
import com.salud.view.PacienteForm;
import com.salud.view.PacienteListFrame;
import com.salud.view.UIStyles;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Controlador del módulo de pacientes.
 */
public class PacienteController {

    private final PacienteDAO pacienteDAO;

    public PacienteController() {
        this.pacienteDAO = new PacienteDAO();
    }

    /**
     * Configura el formulario de registro de pacientes.
     */
    public void configurarFormulario(PacienteForm form) {
        form.getBtnGuardar().setEnabled(false);
        form.getBtnGuardar().addActionListener(e -> guardarPaciente(form));
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

        form.getTxtDni().getDocument().addDocumentListener(listener);
        form.getTxtNombre().getDocument().addDocumentListener(listener);
        form.getTxtApellido().getDocument().addDocumentListener(listener);
        form.getTxtTelefono().getDocument().addDocumentListener(listener);

        javax.swing.SwingUtilities.invokeLater(() -> form.getTxtDni().requestFocusInWindow());
    }

    private void actualizarEstadoBoton(PacienteForm form) {
        boolean valido = ValidacionHelper.esPacienteValido(
                obtenerDni(form),
                obtenerNombre(form),
                obtenerApellido(form),
                obtenerTelefono(form));
        form.getBtnGuardar().setEnabled(valido);
    }

    private void guardarPaciente(PacienteForm form) {
        String dni = obtenerDni(form);
        String nombre = obtenerNombre(form);
        String apellido = obtenerApellido(form);
        String telefono = obtenerTelefono(form);

        String error = ValidacionHelper.validarPaciente(dni, nombre, apellido, telefono);
        if (error != null) {
            JOptionPane.showMessageDialog(form,
                    error,
                    "Datos inválidos",
                    JOptionPane.ERROR_MESSAGE);
            form.getBtnGuardar().setEnabled(false);
            return;
        }

        Paciente paciente = new Paciente(dni, nombre, apellido, telefono);
        boolean guardado = pacienteDAO.insertar(paciente);

        if (guardado) {
            JOptionPane.showMessageDialog(form,
                    "Paciente registrado correctamente.",
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario(form);
            form.getTxtDni().requestFocusInWindow();
        } else {
            JOptionPane.showMessageDialog(form,
                    "No se pudo registrar el paciente. Verifique que el DNI no esté duplicado.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerDni(PacienteForm form) {
        return UIStyles.obtenerTexto(form.getTxtDni(), PacienteForm.PLACEHOLDER_DNI);
    }

    private String obtenerNombre(PacienteForm form) {
        return UIStyles.obtenerTexto(form.getTxtNombre(), PacienteForm.PLACEHOLDER_NOMBRE);
    }

    private String obtenerApellido(PacienteForm form) {
        return UIStyles.obtenerTexto(form.getTxtApellido(), PacienteForm.PLACEHOLDER_APELLIDO);
    }

    private String obtenerTelefono(PacienteForm form) {
        return UIStyles.obtenerTexto(form.getTxtTelefono(), PacienteForm.PLACEHOLDER_TELEFONO);
    }

    private void limpiarFormulario(PacienteForm form) {
        restaurarPlaceholder(form.getTxtDni(), PacienteForm.PLACEHOLDER_DNI);
        restaurarPlaceholder(form.getTxtNombre(), PacienteForm.PLACEHOLDER_NOMBRE);
        restaurarPlaceholder(form.getTxtApellido(), PacienteForm.PLACEHOLDER_APELLIDO);
        restaurarPlaceholder(form.getTxtTelefono(), PacienteForm.PLACEHOLDER_TELEFONO);
        form.getBtnGuardar().setEnabled(false);
    }

    private void restaurarPlaceholder(javax.swing.JTextField campo, String placeholder) {
        campo.setForeground(UIStyles.COLOR_PLACEHOLDER);
        campo.setText(placeholder);
    }

    /**
     * Carga los pacientes en la tabla de la vista de listado.
     */
    public void cargarLista(PacienteListFrame frame) {
        List<Paciente> pacientes = pacienteDAO.listar();
        DefaultTableModel model = frame.getTableModel();

        model.setRowCount(0);

        for (Paciente p : pacientes) {
            model.addRow(new Object[] {
                    p.getId(),
                    p.getDni(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getTelefono() != null ? p.getTelefono() : ""
            });
        }

        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "No hay pacientes registrados todavía.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        frame.getBtnVolver().addActionListener(e -> {
            frame.dispose();
            new com.salud.view.MenuFrame().setVisible(true);
        });
    }
}
