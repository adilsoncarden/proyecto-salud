package com.salud.controller;

import com.salud.dao.CitaDAO;
import com.salud.dao.TriajeDAO;
import com.salud.model.Cita;
import com.salud.model.Triaje;
import com.salud.util.EstadoCita;
import com.salud.util.SesionUsuario;
import com.salud.view.MenuFrame;
import com.salud.view.TriajeFormFrame;
import com.salud.view.TriajeListFrame;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;

public class TriajeController {

    private final CitaDAO citaDAO = new CitaDAO();
    private final TriajeDAO triajeDAO = new TriajeDAO();

    public void abrirModulo() {
        TriajeListFrame frame = new TriajeListFrame();
        recargarCola(frame);
        frame.getBtnRegistrar().addActionListener(e -> abrirFormulario(frame));
        frame.getBtnVolver().addActionListener(e -> {
            frame.dispose();
            new MenuFrame().setVisible(true);
        });
        frame.setVisible(true);
    }

    private void abrirFormulario(TriajeListFrame listFrame) {
        int fila = listFrame.getTabla().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(listFrame, "Seleccione una cita.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int citaId = (int) listFrame.getModelo().getValueAt(fila, 0);
        Cita cita = citaDAO.buscarPorId(citaId);
        if (cita == null || !EstadoCita.POR_TRIAR.equals(cita.getEstado())) {
            JOptionPane.showMessageDialog(listFrame, "La cita debe estar en estado POR_TRIAR.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (triajeDAO.existeTriajeParaCita(citaId)) {
            JOptionPane.showMessageDialog(listFrame, "Esta cita ya tiene triaje registrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TriajeFormFrame form = new TriajeFormFrame(cita);
        form.getBtnGuardar().addActionListener(e -> guardarTriaje(form, listFrame));
        form.getBtnCancelar().addActionListener(e -> form.dispose());
        form.setVisible(true);
    }

    private void guardarTriaje(TriajeFormFrame form, TriajeListFrame listFrame) {
        String error = ValidacionHelper.validarTriaje(
                form.getTxtPeso().getText().trim(),
                form.getTxtTalla().getText().trim(),
                form.getTxtPresion().getText().trim(),
                form.getTxtTemperatura().getText().trim(),
                form.getTxtMotivo().getText().trim());
        if (error != null) {
            JOptionPane.showMessageDialog(form, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double peso = Double.parseDouble(form.getTxtPeso().getText().trim());
        double talla = Double.parseDouble(form.getTxtTalla().getText().trim());
        double temp = Double.parseDouble(form.getTxtTemperatura().getText().trim());
        double imc = ValidacionHelper.calcularImc(peso, talla);

        if (temp > 38.5 || temp < 35) {
            JOptionPane.showMessageDialog(form, "Advertencia: temperatura fuera del rango normal.", "Alerta", JOptionPane.WARNING_MESSAGE);
        }

        Triaje triaje = new Triaje();
        triaje.setCitaId(form.getCita().getId());
        if (SesionUsuario.getUsuario() != null) {
            triaje.setEnfermeraUsuarioId(SesionUsuario.getUsuario().getId());
        }
        triaje.setPesoKg(peso);
        triaje.setTallaM(talla);
        triaje.setPresionArterial(form.getTxtPresion().getText().trim());
        triaje.setTemperaturaC(temp);
        triaje.setMotivoConsulta(form.getTxtMotivo().getText().trim());
        triaje.setImc(imc);

        String fc = form.getTxtFc().getText().trim();
        if (!fc.isEmpty()) {
            triaje.setFrecuenciaCardiaca(Integer.parseInt(fc));
        }
        String fr = form.getTxtFr().getText().trim();
        if (!fr.isEmpty()) {
            triaje.setFrecuenciaRespiratoria(Integer.parseInt(fr));
        }
        String spo2 = form.getTxtSpo2().getText().trim();
        if (!spo2.isEmpty()) {
            triaje.setSaturacionOxigeno(Integer.parseInt(spo2));
        }

        if (triajeDAO.insertar(triaje) && citaDAO.actualizarEstado(form.getCita().getId(), EstadoCita.PENDIENTE_ATENCION)) {
            JOptionPane.showMessageDialog(form, "Triaje registrado. IMC: " + imc);
            form.dispose();
            recargarCola(listFrame);
        } else {
            JOptionPane.showMessageDialog(form, "No se pudo guardar el triaje.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void recargarCola(TriajeListFrame frame) {
        frame.getModelo().setRowCount(0);
        Date hoy = Date.valueOf(LocalDate.now());
        for (Cita c : citaDAO.listarConFiltros(hoy, null, EstadoCita.POR_TRIAR)) {
            frame.getModelo().addRow(new Object[] {
                    c.getId(), c.getPacienteNombre(), c.getMedicoNombre(), c.getHora()
            });
        }
    }
}
