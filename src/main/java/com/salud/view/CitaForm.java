package com.salud.view;

import com.salud.model.Paciente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Formulario para registrar citas médicas.
 */
public class CitaForm extends JFrame {

    public static final String PLACEHOLDER_FECHA = "yyyy-MM-dd (ej: 2026-06-18)";
    public static final String PLACEHOLDER_HORA = "HH:mm (ej: 09:30)";

    private JTextField txtFecha;
    private JTextField txtHora;
    private JComboBox<Paciente> cmbPaciente;
    private JButton btnRegistrar;
    private JButton btnVolver;

    public CitaForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Centro de Salud - Gestión de Citas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UIStyles.configurarVentana(this, 580, 560);

        JPanel root = UIStyles.crearRootPanel();
        root.add(UIStyles.crearEncabezado(
                "Gestión de Citas",
                "Programe una cita médica para un paciente"), BorderLayout.NORTH);

        JPanel tarjeta = UIStyles.crearTarjeta();
        GridBagConstraints gbc = UIStyles.crearConstraintsFormulario();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        tarjeta.add(UIStyles.crearTituloSeccion("Programar Cita"), gbc);
        gbc.gridy = 1;
        tarjeta.add(UIStyles.crearSubtituloSeccion("Indique fecha, hora y paciente para la atención"), gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 0, 16, 0);
        tarjeta.add(UIStyles.crearSeparador(), gbc);

        txtFecha = UIStyles.crearCampoTexto(22);
        UIStyles.setPlaceholder(txtFecha, PLACEHOLDER_FECHA);

        txtHora = UIStyles.crearCampoTexto(22);
        UIStyles.setPlaceholder(txtHora, PLACEHOLDER_HORA);

        cmbPaciente = UIStyles.crearComboBox();

        btnRegistrar = UIStyles.crearBotonPrimario("Registrar cita");
        btnVolver = UIStyles.crearBotonOutline("Volver al menú");

        gbc = UIStyles.crearConstraintsFormulario();
        gbc.gridy = 3;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 3, "Fecha:", txtFecha);
        gbc.gridy = 4;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 4, "Hora:", txtHora);
        gbc.gridy = 5;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 5, "Paciente:", cmbPaciente);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(24, 0, 0, 0);
        tarjeta.add(UIStyles.crearPanelAcciones(btnRegistrar, btnVolver), gbc);

        root.add(UIStyles.centrarContenido(tarjeta), BorderLayout.CENTER);
        setContentPane(root);
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JTextField getTxtHora() {
        return txtHora;
    }

    public JComboBox<Paciente> getCmbPaciente() {
        return cmbPaciente;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
