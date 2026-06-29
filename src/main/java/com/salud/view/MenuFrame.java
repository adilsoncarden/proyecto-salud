package com.salud.view;

import com.salud.controller.CitaController;
import com.salud.controller.PacienteController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Menú principal con acceso a los módulos del sistema.
 */
public class MenuFrame extends JFrame {

    private JButton btnRegistrarPaciente;
    private JButton btnVerPacientes;
    private JButton btnCrearCita;
    private JButton btnSalir;

    public MenuFrame() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        setTitle("Centro de Salud - Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIStyles.configurarVentana(this, 680, 580);

        JPanel root = UIStyles.crearRootPanel();
        root.add(UIStyles.crearEncabezado(
                "Centro de Salud",
                "Panel de Control — Gestión Médica"), BorderLayout.NORTH);

        JPanel tarjeta = UIStyles.crearTarjeta();
        GridBagConstraints gbc = UIStyles.crearConstraintsFormulario();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        tarjeta.add(UIStyles.crearTituloSeccion("Menú Principal"), gbc);
        gbc.gridy = 1;
        tarjeta.add(UIStyles.crearSubtituloSeccion("Seleccione el módulo que desea administrar"), gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 0, 20, 0);
        tarjeta.add(UIStyles.crearSeparador(), gbc);

        btnRegistrarPaciente = UIStyles.crearBotonDashboard(
                "Registrar Paciente", "Alta de nuevos pacientes");
        btnVerPacientes = UIStyles.crearBotonDashboard(
                "Ver Pacientes", "Consultar listado completo");
        btnCrearCita = UIStyles.crearBotonDashboard(
                "Crear Cita", "Programar atención médica");
        btnSalir = UIStyles.crearBotonDashboardSecundario(
                "Cerrar Sesión", "Salir del sistema");

        JPanel gridDashboard = new JPanel(new GridBagLayout());
        gridDashboard.setOpaque(false);
        GridBagConstraints gbcGrid = new GridBagConstraints();
        gbcGrid.insets = new Insets(8, 8, 8, 8);
        gbcGrid.fill = GridBagConstraints.BOTH;
        gbcGrid.weightx = 1;
        gbcGrid.weighty = 1;

        gbcGrid.gridx = 0;
        gbcGrid.gridy = 0;
        gridDashboard.add(btnRegistrarPaciente, gbcGrid);
        gbcGrid.gridx = 1;
        gridDashboard.add(btnVerPacientes, gbcGrid);
        gbcGrid.gridx = 0;
        gbcGrid.gridy = 1;
        gridDashboard.add(btnCrearCita, gbcGrid);
        gbcGrid.gridx = 1;
        gridDashboard.add(btnSalir, gbcGrid);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        tarjeta.add(gridDashboard, gbc);

        root.add(UIStyles.centrarContenido(tarjeta), BorderLayout.CENTER);
        setContentPane(root);
    }

    private void initListeners() {
        PacienteController pacienteController = new PacienteController();
        CitaController citaController = new CitaController();

        btnRegistrarPaciente.addActionListener(e -> {
            dispose();
            PacienteForm form = new PacienteForm();
            pacienteController.configurarFormulario(form);
            form.setVisible(true);
        });

        btnVerPacientes.addActionListener(e -> {
            dispose();
            PacienteListFrame listFrame = new PacienteListFrame();
            pacienteController.cargarLista(listFrame);
            listFrame.setVisible(true);
        });

        btnCrearCita.addActionListener(e -> {
            dispose();
            CitaForm citaForm = new CitaForm();
            citaController.configurarFormulario(citaForm);
            citaForm.setVisible(true);
        });

        btnSalir.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
