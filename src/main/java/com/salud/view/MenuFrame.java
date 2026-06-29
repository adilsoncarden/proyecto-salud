package com.salud.view;

import com.salud.controller.CatalogoController;
import com.salud.controller.CitaController;
import com.salud.controller.HistoriaClinicaController;
import com.salud.controller.MenuController;
import com.salud.controller.PacienteController;
import com.salud.controller.ReporteController;
import com.salud.controller.TriajeController;
import com.salud.controller.AtencionMedicaController;
import com.salud.util.SesionUsuario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;

public class MenuFrame extends JFrame {

    public MenuFrame() {
        setTitle("Centro de Salud - Menú");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel north = new JPanel();
        String nombre = SesionUsuario.getUsuario() != null
                ? SesionUsuario.getUsuario().getNombreCompleto()
                : "Usuario";
        String rol = SesionUsuario.getRol() != null ? SesionUsuario.getRol() : "";
        north.add(new JLabel("Bienvenido: " + nombre + " | Rol: " + rol));

        MenuController menuController = new MenuController();
        List<JButton> botones = menuController.crearBotonesPorRol();
        asignarAcciones(botones);

        add(north, BorderLayout.NORTH);
        add(menuController.crearPanelBotones(botones), BorderLayout.CENTER);
    }

    private void asignarAcciones(List<JButton> botones) {
        PacienteController pacienteController = new PacienteController();
        CitaController citaController = new CitaController();
        CatalogoController catalogoController = new CatalogoController();

        for (JButton boton : botones) {
            String texto = boton.getText();
            switch (texto) {
                case "Registrar Paciente" -> boton.addActionListener(e -> {
                    dispose();
                    pacienteController.abrirRegistro();
                });
                case "Ver Pacientes" -> boton.addActionListener(e -> {
                    dispose();
                    pacienteController.abrirListado();
                });
                case "Gestionar Citas" -> boton.addActionListener(e -> {
                    dispose();
                    citaController.abrirGestion();
                });
                case "Registrar Cita" -> boton.addActionListener(e -> {
                    dispose();
                    citaController.abrirRegistro();
                });
                case "Triaje" -> boton.addActionListener(e -> {
                    dispose();
                    new TriajeController().abrirModulo();
                });
                case "Atención Médica" -> boton.addActionListener(e -> {
                    dispose();
                    new AtencionMedicaController().abrirModulo();
                });
                case "Consultar Historia" -> boton.addActionListener(e -> {
                    dispose();
                    new HistoriaClinicaController().abrirBusqueda();
                });
                case "Reportes" -> boton.addActionListener(e -> {
                    dispose();
                    new ReporteController().abrirModulo();
                });
                case "Usuarios" -> boton.addActionListener(e -> {
                    dispose();
                    catalogoController.abrirUsuarios();
                });
                case "Especialidades" -> boton.addActionListener(e -> {
                    dispose();
                    catalogoController.abrirEspecialidades();
                });
                case "Médicos" -> boton.addActionListener(e -> {
                    dispose();
                    catalogoController.abrirMedicos();
                });
                case "Cerrar Sesión" -> boton.addActionListener(e -> {
                    int ok = JOptionPane.showConfirmDialog(this, "¿Cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (ok == JOptionPane.YES_OPTION) {
                        SesionUsuario.cerrar();
                        dispose();
                        new LoginFrame().setVisible(true);
                    }
                });
                default -> {
                }
            }
        }
    }
}
