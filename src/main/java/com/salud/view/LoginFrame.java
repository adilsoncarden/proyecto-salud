package com.salud.view;

import com.salud.controller.LoginController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Pantalla de inicio de sesión para el administrador.
 */
public class LoginFrame extends JFrame {

    public static final String PLACEHOLDER_USUARIO = "Ingrese su usuario";
    public static final String PLACEHOLDER_PASSWORD = "Ingrese su contraseña";

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        initComponents();
        new LoginController(this);
    }

    private void initComponents() {
        setTitle("Centro de Salud - Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UIStyles.configurarVentana(this, 520, 560);

        JPanel root = UIStyles.crearRootPanel();
        root.add(UIStyles.crearEncabezado(
                "Centro de Salud",
                "Sistema de Atención Médica — Inicio de Sesión"), BorderLayout.NORTH);

        JPanel tarjeta = UIStyles.crearTarjeta();
        GridBagConstraints gbc = UIStyles.crearConstraintsFormulario();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        tarjeta.add(UIStyles.crearTituloSeccion("Acceso al sistema"), gbc);
        gbc.gridy = 1;
        tarjeta.add(UIStyles.crearSubtituloSeccion("Ingrese sus credenciales de administrador"), gbc);

        txtUsuario = UIStyles.crearCampoTexto(22);
        UIStyles.setPlaceholder(txtUsuario, PLACEHOLDER_USUARIO);

        txtPassword = UIStyles.crearCampoPassword(22);
        txtPassword.setEchoChar('●');

        btnLogin = UIStyles.crearBotonPrimario("Iniciar sesión");

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 0, 16, 0);
        tarjeta.add(UIStyles.crearSeparador(), gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 3, "Usuario:", txtUsuario);

        gbc.gridy = 4;
        UIStyles.agregarCampoFormulario(tarjeta, gbc, 4, "Contraseña:", txtPassword);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(24, 0, 0, 0);
        tarjeta.add(btnLogin, gbc);

        root.add(UIStyles.centrarContenido(tarjeta), BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBackground(UIStyles.COLOR_FONDO);
        footer.setBorder(new javax.swing.border.EmptyBorder(0, 0, 16, 0));
        JLabel lblFooter = new JLabel("© Centro de Salud — Gestión Médica", JLabel.CENTER);
        lblFooter.setFont(UIStyles.FUENTE_NORMAL);
        lblFooter.setForeground(UIStyles.COLOR_TEXTO_SUAVE);
        footer.add(lblFooter);
        root.add(footer, BorderLayout.SOUTH);

        setContentPane(root);
        SwingUtilities.invokeLater(() -> txtUsuario.requestFocusInWindow());
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }
}
