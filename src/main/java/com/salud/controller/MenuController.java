package com.salud.controller;

import com.salud.util.RolConstantes;
import com.salud.util.SesionUsuario;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Construye el menú principal según el rol del usuario autenticado.
 */
public class MenuController {

    public List<JButton> crearBotonesPorRol() {
        String rol = SesionUsuario.getRol();
        List<JButton> botones = new ArrayList<>();

        if (RolConstantes.esAdmin(rol)) {
            agregar(botones, "Usuarios");
            agregar(botones, "Especialidades");
            agregar(botones, "Médicos");
            agregar(botones, "Reportes");
        }
        if (RolConstantes.esAdmin(rol) || RolConstantes.esAdmision(rol)) {
            agregar(botones, "Registrar Paciente");
            agregar(botones, "Ver Pacientes");
            agregar(botones, "Registrar Cita");
            agregar(botones, "Gestionar Citas");
        }
        if (RolConstantes.esAdmin(rol) || RolConstantes.esEnfermeria(rol)) {
            agregar(botones, "Triaje");
        }
        if (RolConstantes.esAdmin(rol) || RolConstantes.esMedico(rol)) {
            agregar(botones, "Atención Médica");
            agregar(botones, "Consultar Historia");
        }
        agregar(botones, "Cerrar Sesión");
        return botones;
    }

    public JPanel crearPanelBotones(List<JButton> botones) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        for (JButton boton : botones) {
            panel.add(boton);
        }
        return panel;
    }

    private void agregar(List<JButton> botones, String texto) {
        botones.add(new JButton(texto));
    }
}
