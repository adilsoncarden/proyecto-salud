package com.salud.controller;

import com.salud.dao.UsuarioDAO;
import com.salud.model.Usuario;
import com.salud.view.LoginFrame;
import com.salud.view.MenuFrame;
import com.salud.view.UIStyles;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Controlador del módulo de autenticación.
 */
public class LoginController {

    private final LoginFrame vista;
    private final UsuarioDAO usuarioDAO;

    public LoginController(LoginFrame vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();
        initListeners();
        SwingUtilities.invokeLater(() -> vista.getTxtUsuario().requestFocusInWindow());
    }

    private void initListeners() {
        vista.getBtnLogin().addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String username = UIStyles.obtenerTexto(vista.getTxtUsuario(), LoginFrame.PLACEHOLDER_USUARIO);
        String password = new String(vista.getTxtPassword().getPassword()).trim();

        String error = ValidacionHelper.validarLogin(username, password);
        if (error != null) {
            mostrarAdvertencia(error);
            enfocarCampoConError(username.isEmpty());
            return;
        }

        vista.getBtnLogin().setEnabled(false);
        Usuario usuario = usuarioDAO.login(username, password);
        vista.getBtnLogin().setEnabled(true);

        if (usuario != null) {
            vista.dispose();
            new MenuFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Usuario o contraseña incorrectos.",
                    "Error de acceso",
                    JOptionPane.ERROR_MESSAGE);
            vista.getTxtPassword().setText("");
            vista.getTxtPassword().requestFocusInWindow();
        }
    }

    private void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(vista,
                mensaje,
                "Campos requeridos",
                JOptionPane.WARNING_MESSAGE);
    }

    private void enfocarCampoConError(boolean usuarioVacio) {
        if (usuarioVacio) {
            vista.getTxtUsuario().requestFocusInWindow();
        } else {
            vista.getTxtPassword().requestFocusInWindow();
        }
    }
}
