package com.salud.view;

import com.salud.model.Especialidad;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class CitaListFrame extends JFrame {

    private final JTextField txtFecha = new JTextField(10);
    private final JComboBox<Especialidad> cmbEspecialidad = new JComboBox<>();
    private final JComboBox<String> cmbEstado = new JComboBox<>(new String[] {
            "TODOS", "PROGRAMADA", "POR_TRIAR", "PENDIENTE_ATENCION", "ATENDIDA", "CANCELADA"
    });
    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[] { "ID", "Paciente", "Médico", "Especialidad", "Fecha", "Hora", "Estado" }, 0) {
        @Override
        public boolean isCellEditable(int r, int c) { return false; }
    };
    private final JTable tabla = new JTable(modelo);
    private final JButton btnFiltrar = new JButton("Filtrar");
    private final JButton btnConfirmarLlegada = new JButton("Confirmar Llegada");
    private final JButton btnReprogramar = new JButton("Reprogramar");
    private final JButton btnCancelar = new JButton("Cancelar Cita");
    private final JButton btnVolver = new JButton("Volver");

    public CitaListFrame() {
        setTitle("Gestión de Citas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 450);
        setLocationRelativeTo(null);

        JPanel top = new JPanel();
        top.add(new javax.swing.JLabel("Fecha:"));
        top.add(txtFecha);
        top.add(new javax.swing.JLabel("Especialidad:"));
        top.add(cmbEspecialidad);
        top.add(new javax.swing.JLabel("Estado:"));
        top.add(cmbEstado);
        top.add(btnFiltrar);
        top.add(btnConfirmarLlegada);
        top.add(btnReprogramar);
        top.add(btnCancelar);
        top.add(btnVolver);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    public JTextField getTxtFecha() { return txtFecha; }
    public JComboBox<Especialidad> getCmbEspecialidad() { return cmbEspecialidad; }
    public JComboBox<String> getCmbEstado() { return cmbEstado; }
    public DefaultTableModel getModelo() { return modelo; }
    public JTable getTabla() { return tabla; }
    public JButton getBtnFiltrar() { return btnFiltrar; }
    public JButton getBtnConfirmarLlegada() { return btnConfirmarLlegada; }
    public JButton getBtnReprogramar() { return btnReprogramar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnVolver() { return btnVolver; }
}
