package com.salud.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Formulario para registrar nuevos pacientes.
 */
public class PacienteForm extends JFrame {

    public static final String PLACEHOLDER_DNI = "8 dígitos numéricos";
    public static final String PLACEHOLDER_NOMBRE = "Solo letras";
    public static final String PLACEHOLDER_APELLIDO = "Ingrese apellido";
    public static final String PLACEHOLDER_TELEFONO = "Máximo 9 dígitos (opcional)";

    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JButton btnGuardar;
    private JButton btnVolver;

    public PacienteForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Centro de Salud - Registro de Pacientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UIStyles.configurarVentana(this, 580, 620);

        JPanel root = UIStyles.crearRootPanel();
        root.add(UIStyles.crearEncabezado(
                "Registro de Pacientes",
                "Complete los datos del nuevo paciente"), BorderLayout.NORTH);

        JPanel tarjeta = UIStyles.crearTarjeta();
        GridBagConstraints gbc = UIStyles.crearConstraintsFormulario();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        tarjeta.add(UIStyles.crearTituloSeccion("Datos del Paciente"), gbc);
        gbc.gridy = 1;
        tarjeta.add(UIStyles.crearSubtituloSeccion("Todos los campos marcados son obligatorios, excepto teléfono"), gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 0, 16, 0);
        tarjeta.add(UIStyles.crearSeparador(), gbc);

        txtDni = UIStyles.crearCampoTexto(22);
        UIStyles.setPlaceholder(txtDni, PLACEHOLDER_DNI);
        UIStyles.limitarSoloNumeros(txtDni, 8);

        txtNombre = UIStyles.crearCampoTexto(22);
        UIStyles.setPlaceholder(txtNombre, PLACEHOLDER_NOMBRE);
        UIStyles.limitarSoloLetras(txtNombre);

        txtApellido = UIStyles.crearCampoTexto(22);
        UIStyles.setPlaceholder(txtApellido, PLACEHOLDER_APELLIDO);

        txtTelefono = UIStyles.crearCampoTexto(22);
        UIStyles.setPlaceholder(txtTelefono, PLACEHOLDER_TELEFONO);
        UIStyles.limitarSoloNumeros(txtTelefono, 9);

        btnGuardar = UIStyles.crearBotonPrimario("Guardar paciente");
        btnVolver = UIStyles.crearBotonOutline("Volver al menú");

        gbc = UIStyles.crearConstraintsFormulario();
        gbc.gridy = 3;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 3, "DNI:", txtDni);
        gbc.gridy = 4;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 4, "Nombre:", txtNombre);
        gbc.gridy = 5;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 5, "Apellido:", txtApellido);
        gbc.gridy = 6;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 6, "Teléfono:", txtTelefono);

        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(24, 0, 0, 0);
        tarjeta.add(UIStyles.crearPanelAcciones(btnGuardar, btnVolver), gbc);

        root.add(UIStyles.centrarContenido(tarjeta), BorderLayout.CENTER);
        setContentPane(root);
    }

    public JTextField getTxtDni() {
        return txtDni;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }
}
