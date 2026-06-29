package com.salud.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class AdminCatalogoFrame extends JFrame {

    private final DefaultTableModel modelo;
    private final JTable tabla;
    private final JButton btnAgregar = new JButton("Agregar");
    private final JButton btnDesactivar = new JButton("Desactivar");
    private final JButton btnVolver = new JButton("Volver");

    public AdminCatalogoFrame(String titulo, String[] columnas) {
        setTitle(titulo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 400);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);

        JPanel top = new JPanel();
        top.add(btnAgregar);
        top.add(btnDesactivar);
        top.add(btnVolver);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public DefaultTableModel getModelo() { return modelo; }
    public JTable getTabla() { return tabla; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnDesactivar() { return btnDesactivar; }
    public JButton getBtnVolver() { return btnVolver; }
}
