package com.salud.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class TriajeListFrame extends JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[] { "ID Cita", "Paciente", "Médico", "Hora" }, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabla = new JTable(modelo);
    private final JButton btnRegistrar = new JButton("Registrar Triaje");
    private final JButton btnVolver = new JButton("Volver");

    public TriajeListFrame() {
        setTitle("Cola de Triaje - Hoy");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 350);
        setLocationRelativeTo(null);

        JPanel top = new JPanel();
        top.add(btnRegistrar);
        top.add(btnVolver);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public DefaultTableModel getModelo() { return modelo; }
    public JTable getTabla() { return tabla; }
    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnVolver() { return btnVolver; }
}
