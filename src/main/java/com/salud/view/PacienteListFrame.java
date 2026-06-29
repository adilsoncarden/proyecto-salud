package com.salud.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

/**
 * Pantalla con tabla de pacientes registrados.
 */
public class PacienteListFrame extends JFrame {

    private JTable tablaPacientes;
    private DefaultTableModel tableModel;
    private JButton btnVolver;

    public PacienteListFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Centro de Salud - Lista de Pacientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UIStyles.configurarVentana(this, 900, 580);

        JPanel root = UIStyles.crearRootPanel();
        root.add(UIStyles.crearEncabezado(
                "Pacientes Registrados",
                "Listado completo de pacientes en el sistema"), BorderLayout.NORTH);

        JPanel tarjeta = UIStyles.crearTarjeta();
        GridBagConstraints gbc = UIStyles.crearConstraintsFormulario();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        tarjeta.add(UIStyles.crearTituloSeccion("Listado de Pacientes"), gbc);
        gbc.gridy = 1;
        tarjeta.add(UIStyles.crearSubtituloSeccion("Consulte la información registrada de cada paciente"), gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new java.awt.Insets(4, 0, 16, 0);
        tarjeta.add(UIStyles.crearSeparador(), gbc);

        String[] columnas = { "ID", "DNI", "Nombre", "Apellido", "Teléfono" };
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPacientes = new JTable(tableModel);
        UIStyles.estilizarTabla(tablaPacientes);

        JScrollPane scrollPane = new JScrollPane(tablaPacientes);
        UIStyles.estilizarScrollTabla(scrollPane);
        scrollPane.setPreferredSize(new java.awt.Dimension(780, 300));

        btnVolver = UIStyles.crearBotonOutline("Volver al menú");

        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        tarjeta.add(scrollPane, gbc);

        gbc.gridy = 4;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(20, 0, 0, 0);
        tarjeta.add(UIStyles.crearPanelAcciones(btnVolver), gbc);

        root.add(UIStyles.centrarContenido(tarjeta), BorderLayout.CENTER);
        setContentPane(root);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
