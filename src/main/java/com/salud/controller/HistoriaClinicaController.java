package com.salud.controller;

import com.salud.dao.AtencionMedicaDAO;
import com.salud.dao.HistoriaClinicaDAO;
import com.salud.dao.PacienteDAO;
import com.salud.dao.RecetaMedicaDAO;
import com.salud.model.AtencionMedica;
import com.salud.model.HistoriaClinica;
import com.salud.model.Paciente;
import com.salud.model.RecetaMedica;
import com.salud.view.HistoriaClinicaFrame;
import com.salud.view.MenuFrame;

import javax.swing.JOptionPane;

public class HistoriaClinicaController {

    private final PacienteDAO pacienteDAO = new PacienteDAO();
    private final HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO();
    private final AtencionMedicaDAO atencionMedicaDAO = new AtencionMedicaDAO();
    private final RecetaMedicaDAO recetaMedicaDAO = new RecetaMedicaDAO();

    public void abrirBusqueda() {
        String dni = JOptionPane.showInputDialog(null, "Ingrese DNI del paciente:");
        if (ValidacionHelper.esVacio(dni)) {
            return;
        }
        Paciente paciente = pacienteDAO.buscarPorDni(dni.trim());
        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        abrirHistoria(paciente.getId());
    }

    public void abrirHistoria(int pacienteId) {
        Paciente paciente = pacienteDAO.buscarPorId(pacienteId);
        HistoriaClinica historia = historiaClinicaDAO.buscarPorPaciente(pacienteId);
        if (historia == null) {
            historiaClinicaDAO.crearSiNoExiste(pacienteId);
            historia = historiaClinicaDAO.buscarPorPaciente(pacienteId);
        }

        final HistoriaClinica historiaFinal = historia;
        HistoriaClinicaFrame frame = new HistoriaClinicaFrame(paciente, historiaFinal);
        cargarAtenciones(frame, historiaFinal.getId());

        frame.getBtnGuardarAntecedentes().addActionListener(e -> {
            if (historiaClinicaDAO.actualizarAntecedentes(
                    historiaFinal.getId(),
                    frame.getTxtTipoSangre().getText().trim(),
                    frame.getTxtAlergias().getText().trim())) {
                JOptionPane.showMessageDialog(frame, "Antecedentes actualizados.");
            }
        });

        frame.getBtnVerReceta().addActionListener(e -> verReceta(frame));

        frame.getBtnVolver().addActionListener(e -> {
            frame.dispose();
            new MenuFrame().setVisible(true);
        });

        frame.setVisible(true);
    }

    private void cargarAtenciones(HistoriaClinicaFrame frame, int historiaId) {
        frame.getModeloAtenciones().setRowCount(0);
        for (AtencionMedica a : atencionMedicaDAO.listarPorHistoria(historiaId)) {
            frame.getModeloAtenciones().addRow(new Object[] {
                    a.getId(), a.getFechaAtencion(), a.getCodigoCie10(),
                    a.getDiagnosticoDetalle(), a.getMedicoNombre()
            });
        }
    }

    private void verReceta(HistoriaClinicaFrame frame) {
        int fila = frame.getTablaAtenciones().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(frame, "Seleccione una atención.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int atencionId = (int) frame.getModeloAtenciones().getValueAt(fila, 0);
        RecetaMedica receta = recetaMedicaDAO.buscarPorAtencion(atencionId);
        if (receta == null) {
            JOptionPane.showMessageDialog(frame, "Esta atención no tiene receta.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(frame,
                "Medicamentos:\n" + receta.getMedicamentos() + "\n\nIndicaciones:\n" + receta.getIndicaciones(),
                "Receta médica",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
