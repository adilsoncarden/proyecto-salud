package com.salud.controller;

import com.salud.dao.ReporteDAO;
import com.salud.view.MenuFrame;
import com.salud.view.ReporteFrame;

import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ReporteController {

    private final ReporteDAO reporteDAO = new ReporteDAO();

    public void abrirModulo() {
        ReporteFrame frame = new ReporteFrame();
        frame.getBtnCitasDia().addActionListener(e -> reporteCitasDia(frame));
        frame.getBtnAtencionesMedico().addActionListener(e -> reporteAtenciones(frame));
        frame.getBtnPacientes().addActionListener(e -> reportePacientes(frame));
        frame.getBtnVolver().addActionListener(e -> {
            frame.dispose();
            new MenuFrame().setVisible(true);
        });
        frame.setVisible(true);
    }

    private void reporteCitasDia(ReporteFrame frame) {
        String fechaTxt = frame.getTxtFecha().getText().trim();
        if (ValidacionHelper.esVacio(fechaTxt)) {
            JOptionPane.showMessageDialog(frame, "Ingrese una fecha.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Date fecha = Date.valueOf(LocalDate.parse(fechaTxt, DateTimeFormatter.ISO_LOCAL_DATE));
        Map<String, Integer> datos = reporteDAO.contarCitasPorEstadoEnFecha(fecha);
        StringBuilder sb = new StringBuilder("Citas del día ").append(fechaTxt).append(":\n\n");
        if (datos.isEmpty()) {
            sb.append("Sin citas registradas.");
        } else {
            for (Map.Entry<String, Integer> e : datos.entrySet()) {
                sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
            }
        }
        frame.getTxtResultado().setText(sb.toString());
    }

    private void reporteAtenciones(ReporteFrame frame) {
        String desdeTxt = frame.getTxtDesde().getText().trim();
        String hastaTxt = frame.getTxtHasta().getText().trim();
        if (ValidacionHelper.esVacio(desdeTxt) || ValidacionHelper.esVacio(hastaTxt)) {
            JOptionPane.showMessageDialog(frame, "Ingrese rango de fechas.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Date desde = Date.valueOf(LocalDate.parse(desdeTxt, DateTimeFormatter.ISO_LOCAL_DATE));
        Date hasta = Date.valueOf(LocalDate.parse(hastaTxt, DateTimeFormatter.ISO_LOCAL_DATE));
        Map<String, Integer> datos = reporteDAO.contarAtencionesPorMedico(desde, hasta);
        StringBuilder sb = new StringBuilder("Atenciones por médico (").append(desdeTxt).append(" a ").append(hastaTxt).append("):\n\n");
        if (datos.isEmpty()) {
            sb.append("Sin atenciones en el período.");
        } else {
            for (Map.Entry<String, Integer> e : datos.entrySet()) {
                sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
            }
        }
        frame.getTxtResultado().setText(sb.toString());
    }

    private void reportePacientes(ReporteFrame frame) {
        int total = reporteDAO.contarPacientesActivos();
        frame.getTxtResultado().setText("Total de pacientes activos: " + total);
    }
}
