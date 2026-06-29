package com.salud.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ReporteFrame extends JFrame {

    private final JTextField txtFecha = new JTextField(10);
    private final JTextField txtDesde = new JTextField(10);
    private final JTextField txtHasta = new JTextField(10);
    private final JTextArea txtResultado = new JTextArea(15, 50);
    private final JButton btnCitasDia = new JButton("Citas del día");
    private final JButton btnAtencionesMedico = new JButton("Atenciones por médico");
    private final JButton btnPacientes = new JButton("Pacientes activos");
    private final JButton btnVolver = new JButton("Volver");

    public ReporteFrame() {
        setTitle("Reportes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 450);
        setLocationRelativeTo(null);

        JPanel filtros = new JPanel(new GridLayout(4, 2, 5, 5));
        filtros.add(new javax.swing.JLabel("Fecha citas:"));
        filtros.add(txtFecha);
        filtros.add(new javax.swing.JLabel("Desde:"));
        filtros.add(txtDesde);
        filtros.add(new javax.swing.JLabel("Hasta:"));
        filtros.add(txtHasta);
        filtros.add(btnCitasDia);
        filtros.add(btnAtencionesMedico);
        filtros.add(btnPacientes);
        filtros.add(btnVolver);

        txtResultado.setEditable(false);
        add(filtros, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
    }

    public JTextField getTxtFecha() { return txtFecha; }
    public JTextField getTxtDesde() { return txtDesde; }
    public JTextField getTxtHasta() { return txtHasta; }
    public JTextArea getTxtResultado() { return txtResultado; }
    public JButton getBtnCitasDia() { return btnCitasDia; }
    public JButton getBtnAtencionesMedico() { return btnAtencionesMedico; }
    public JButton getBtnPacientes() { return btnPacientes; }
    public JButton getBtnVolver() { return btnVolver; }
}
