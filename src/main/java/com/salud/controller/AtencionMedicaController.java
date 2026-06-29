package com.salud.controller;

import com.salud.dao.AtencionMedicaDAO;
import com.salud.dao.CatalogoCie10DAO;
import com.salud.dao.CitaDAO;
import com.salud.dao.HistoriaClinicaDAO;
import com.salud.dao.RecetaMedicaDAO;
import com.salud.dao.TriajeDAO;
import com.salud.model.AtencionMedica;
import com.salud.model.Cita;
import com.salud.model.DiagnosticoCie10;
import com.salud.model.HistoriaClinica;
import com.salud.model.RecetaMedica;
import com.salud.model.Triaje;
import com.salud.util.EstadoCita;
import com.salud.util.SesionUsuario;
import com.salud.view.AtencionFormFrame;
import com.salud.view.AtencionListFrame;
import com.salud.view.MenuFrame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.sql.Date;

public class AtencionMedicaController {

    private final CitaDAO citaDAO = new CitaDAO();
    private final TriajeDAO triajeDAO = new TriajeDAO();
    private final HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO();
    private final AtencionMedicaDAO atencionMedicaDAO = new AtencionMedicaDAO();
    private final RecetaMedicaDAO recetaMedicaDAO = new RecetaMedicaDAO();
    private final CatalogoCie10DAO catalogoCie10DAO = new CatalogoCie10DAO();

    public void abrirModulo() {
        AtencionListFrame frame = new AtencionListFrame();
        recargarCola(frame);
        frame.getBtnAtender().addActionListener(e -> abrirConsulta(frame));
        frame.getBtnVolver().addActionListener(e -> {
            frame.dispose();
            new MenuFrame().setVisible(true);
        });
        frame.setVisible(true);
    }

    private void abrirConsulta(AtencionListFrame listFrame) {
        int fila = listFrame.getTabla().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(listFrame, "Seleccione una cita.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int citaId = (int) listFrame.getModelo().getValueAt(fila, 0);
        Cita cita = citaDAO.buscarPorId(citaId);
        if (cita == null || !EstadoCita.PENDIENTE_ATENCION.equals(cita.getEstado())) {
            JOptionPane.showMessageDialog(listFrame, "La cita debe estar PENDIENTE_ATENCION.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!triajeDAO.existeTriajeParaCita(citaId)) {
            JOptionPane.showMessageDialog(listFrame, "La cita no tiene triaje registrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Triaje triaje = triajeDAO.buscarPorCita(citaId);
        HistoriaClinica historia = historiaClinicaDAO.buscarPorPaciente(cita.getPacienteId());

        AtencionFormFrame form = new AtencionFormFrame(cita, triaje, historia);
        DefaultComboBoxModel<DiagnosticoCie10> model = new DefaultComboBoxModel<>();
        for (DiagnosticoCie10 d : catalogoCie10DAO.listarTodos()) {
            model.addElement(d);
        }
        form.getCmbCie10().setModel(model);

        form.getBtnGuardar().addActionListener(e -> guardarAtencion(form, listFrame));
        form.getBtnReceta().addActionListener(e -> emitirReceta(form));
        form.getBtnCancelar().addActionListener(e -> form.dispose());
        form.setVisible(true);
    }

    private void guardarAtencion(AtencionFormFrame form, AtencionListFrame listFrame) {
        DiagnosticoCie10 cie10 = (DiagnosticoCie10) form.getCmbCie10().getSelectedItem();
        String codigo = cie10 != null ? cie10.getCodigo() : "";
        String error = ValidacionHelper.validarAtencion(codigo,
                form.getTxtDiagnostico().getText().trim(),
                form.getTxtAnamnesis().getText().trim());
        if (error != null) {
            JOptionPane.showMessageDialog(form, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(form,
                "¿Registrar atención? No podrá editarse después.",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Cita cita = form.getCita();
        HistoriaClinica historia = form.getHistoria();
        if (historia == null) {
            historiaClinicaDAO.crearSiNoExiste(cita.getPacienteId());
            historia = historiaClinicaDAO.buscarPorPaciente(cita.getPacienteId());
        }

        int medicoId = cita.getMedicoId();
        if (SesionUsuario.getUsuario() != null && SesionUsuario.getUsuario().getIdMedico() != null) {
            medicoId = SesionUsuario.getUsuario().getIdMedico();
        }

        AtencionMedica atencion = new AtencionMedica();
        atencion.setHistoriaId(historia.getId());
        atencion.setCitaId(cita.getId());
        atencion.setMedicoId(medicoId);
        atencion.setCodigoCie10(codigo);
        atencion.setDiagnosticoDetalle(form.getTxtDiagnostico().getText().trim());
        atencion.setAnamnesis(form.getTxtAnamnesis().getText().trim());
        atencion.setExamenFisico(form.getTxtExamen().getText().trim());
        atencion.setPlanTratamiento(form.getTxtPlan().getText().trim());

        if (atencionMedicaDAO.insertar(atencion) && citaDAO.actualizarEstado(cita.getId(), EstadoCita.ATENDIDA)) {
            form.setAtencionId(atencionMedicaDAO.obtenerUltimoIdPorCita(cita.getId()));
            JOptionPane.showMessageDialog(form, "Atención registrada correctamente.");
            form.getBtnGuardar().setEnabled(false);
            recargarCola(listFrame);
        } else {
            JOptionPane.showMessageDialog(form, "No se pudo registrar la atención.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void emitirReceta(AtencionFormFrame form) {
        if (form.getAtencionId() <= 0) {
            JOptionPane.showMessageDialog(form, "Primero registre la atención médica.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String medicamentos = JOptionPane.showInputDialog(form, "Medicamentos:");
        String indicaciones = JOptionPane.showInputDialog(form, "Indicaciones:");
        String error = ValidacionHelper.validarReceta(medicamentos, indicaciones);
        if (error != null) {
            JOptionPane.showMessageDialog(form, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        RecetaMedica receta = new RecetaMedica(form.getAtencionId(),
                new Date(System.currentTimeMillis()), medicamentos, indicaciones);

        if (recetaMedicaDAO.insertar(receta)) {
            JOptionPane.showMessageDialog(form,
                    "Receta emitida:\n" + medicamentos + "\n" + indicaciones,
                    "Receta",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void recargarCola(AtencionListFrame frame) {
        frame.getModelo().setRowCount(0);
        Integer medicoId = null;
        if (SesionUsuario.getUsuario() != null && SesionUsuario.getUsuario().getIdMedico() != null) {
            medicoId = SesionUsuario.getUsuario().getIdMedico();
        }

        java.util.List<Cita> citas;
        if (medicoId != null) {
            citas = citaDAO.listarPorMedicoYEstado(medicoId, EstadoCita.PENDIENTE_ATENCION);
        } else {
            citas = citaDAO.listarPorEstado(EstadoCita.PENDIENTE_ATENCION);
        }

        for (Cita c : citas) {
            frame.getModelo().addRow(new Object[] {
                    c.getId(), c.getPacienteNombre(), c.getMedicoNombre(), c.getFecha(), c.getHora()
            });
        }
    }
}
