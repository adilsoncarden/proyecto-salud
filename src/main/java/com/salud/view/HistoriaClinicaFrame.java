package com.salud.view;

import com.salud.model.HistoriaClinica;
import com.salud.model.Paciente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class HistoriaClinicaFrame extends JFrame {

    private final JTextField txtTipoSangre = new JTextField();
    private final JTextField txtAlergias = new JTextField();
    private final DefaultTableModel modeloAtenciones = new DefaultTableModel(
            new String[] { "ID", "Fecha", "CIE-10", "Diagnóstico", "Médico" }, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tablaAtenciones = new JTable(modeloAtenciones);
    private final JButton btnGuardarAntecedentes = new JButton("Guardar Antecedentes");
    private final JButton btnVerReceta = new JButton("Ver Receta");
    private final JButton btnVolver = new JButton("Volver");

    public HistoriaClinicaFrame(Paciente paciente, HistoriaClinica historia) {
        setTitle("Historia Clínica - " + paciente.getNombre() + " " + paciente.getApellido());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        JPanel north = new JPanel(new GridLayout(4, 2, 5, 5));
        north.add(new JLabel("DNI:"));
        north.add(new JLabel(paciente.getDni()));
        north.add(new JLabel("Tipo sangre:"));
        north.add(txtTipoSangre);
        north.add(new JLabel("Alergias:"));
        north.add(txtAlergias);
        north.add(btnGuardarAntecedentes);
        north.add(btnVerReceta);

        if (historia != null) {
            txtTipoSangre.setText(historia.getTipoSangre() != null ? historia.getTipoSangre() : "");
            txtAlergias.setText(historia.getAlergias() != null ? historia.getAlergias() : "");
        }

        JPanel south = new JPanel();
        south.add(btnVolver);

        add(north, BorderLayout.NORTH);
        add(new JScrollPane(tablaAtenciones), BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }

    public JTextField getTxtTipoSangre() { return txtTipoSangre; }
    public JTextField getTxtAlergias() { return txtAlergias; }
    public DefaultTableModel getModeloAtenciones() { return modeloAtenciones; }
    public JTable getTablaAtenciones() { return tablaAtenciones; }
    public JButton getBtnGuardarAntecedentes() { return btnGuardarAntecedentes; }
    public JButton getBtnVerReceta() { return btnVerReceta; }
    public JButton getBtnVolver() { return btnVolver; }
}
