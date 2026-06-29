package com.salud.controller;

import com.salud.dao.UsuarioDAO;
import com.salud.model.Usuario;
import com.salud.util.SesionUsuario;
import com.salud.view.LoginFrame;
import com.salud.view.MenuFrame;

import javax.swing.JOptionPane;

public class LoginController {

    private final LoginFrame vista;
    private final UsuarioDAO usuarioDAO;

    public LoginController(LoginFrame vista) {
        this.vista = vista;
        this.usuarioDAO = new UsuarioDAO();
        vista.getBtnLogin().addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String username = vista.getTxtUsuario().getText().trim();
        String password = new String(vista.getTxtPassword().getPassword()).trim();

        String error = ValidacionHelper.validarLogin(username, password);
        if (error != null) {
            JOptionPane.showMessageDialog(vista, error, "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = usuarioDAO.login(username, password);
        if (usuario != null) {
            SesionUsuario.iniciar(usuario);
            vista.dispose();
            new MenuFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Usuario o contraseña incorrectos.",
                    "Error de acceso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
