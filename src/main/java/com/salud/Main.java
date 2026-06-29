package com.salud;

import com.salud.view.LoginFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punto de entrada de la aplicación.
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usa el look and feel por defecto si falla
        }

        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
