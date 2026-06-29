package com.salud.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class PacienteListFrame extends JFrame {

    private final JTextField txtBuscar = new JTextField(20);
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[] { "ID", "DNI", "Nombre", "Apellido", "Teléfono", "Seguro" }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnEditar = new JButton("Editar");
    private final JButton btnDesactivar = new JButton("Desactivar");
    private final JButton btnHistoria = new JButton("Ver Historia");
    private final JButton btnVolver = new JButton("Volver");

    public PacienteListFrame() {
        setTitle("Pacientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);

        JPanel top = new JPanel();
        top.add(new javax.swing.JLabel("Buscar:"));
        top.add(txtBuscar);
        top.add(btnBuscar);
        top.add(btnEditar);
        top.add(btnDesactivar);
        top.add(btnHistoria);
        top.add(btnVolver);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public JTextField getTxtBuscar() { return txtBuscar; }
    public DefaultTableModel getModelo() { return modelo; }
    public JTable getTabla() { return tabla; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnDesactivar() { return btnDesactivar; }
    public JButton getBtnHistoria() { return btnHistoria; }
    public JButton getBtnVolver() { return btnVolver; }
}
