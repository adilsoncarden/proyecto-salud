package com.salud.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class AtencionListFrame extends JFrame {

    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[] { "ID Cita", "Paciente", "Médico", "Fecha", "Hora" }, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabla = new JTable(modelo);
    private final JButton btnAtender = new JButton("Atender");
    private final JButton btnVolver = new JButton("Volver");

    public AtencionListFrame() {
        setTitle("Cola de Atención Médica");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 350);
        setLocationRelativeTo(null);

        JPanel top = new JPanel();
        top.add(btnAtender);
        top.add(btnVolver);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public DefaultTableModel getModelo() { return modelo; }
    public JTable getTabla() { return tabla; }
    public JButton getBtnAtender() { return btnAtender; }
    public JButton getBtnVolver() { return btnVolver; }
}
