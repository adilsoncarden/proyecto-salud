package com.salud.controller;

import com.salud.dao.EspecialidadDAO;
import com.salud.dao.MedicoDAO;
import com.salud.dao.UsuarioDAO;
import com.salud.model.Especialidad;
import com.salud.model.Medico;
import com.salud.model.Usuario;
import com.salud.util.RolConstantes;
import com.salud.view.AdminCatalogoFrame;
import com.salud.view.MenuFrame;

import javax.swing.JOptionPane;

public class CatalogoController {

    private final EspecialidadDAO especialidadDAO = new EspecialidadDAO();
    private final MedicoDAO medicoDAO = new MedicoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void abrirEspecialidades() {
        AdminCatalogoFrame frame = new AdminCatalogoFrame("Administrar Especialidades", new String[] { "ID", "Nombre", "Descripción" });
        recargarEspecialidades(frame);
        frame.getBtnAgregar().addActionListener(e -> agregarEspecialidad(frame));
        frame.getBtnDesactivar().addActionListener(e -> desactivarEspecialidad(frame));
        frame.getBtnVolver().addActionListener(e -> volverMenu(frame));
        frame.setVisible(true);
    }

    public void abrirMedicos() {
        AdminCatalogoFrame frame = new AdminCatalogoFrame("Administrar Médicos", new String[] { "ID", "DNI", "Nombres", "Apellidos", "Especialidad" });
        recargarMedicos(frame);
        frame.getBtnAgregar().addActionListener(e -> agregarMedico(frame));
        frame.getBtnDesactivar().addActionListener(e -> desactivarMedico(frame));
        frame.getBtnVolver().addActionListener(e -> volverMenu(frame));
        frame.setVisible(true);
    }

    public void abrirUsuarios() {
        AdminCatalogoFrame frame = new AdminCatalogoFrame("Administrar Usuarios", new String[] { "ID", "Usuario", "Rol", "Nombres", "Apellidos" });
        recargarUsuarios(frame);
        frame.getBtnAgregar().addActionListener(e -> agregarUsuario(frame));
        frame.getBtnDesactivar().addActionListener(e -> desactivarUsuario(frame));
        frame.getBtnVolver().addActionListener(e -> volverMenu(frame));
        frame.setVisible(true);
    }

    private void agregarEspecialidad(AdminCatalogoFrame frame) {
        String nombre = JOptionPane.showInputDialog(frame, "Nombre de especialidad:");
        if (ValidacionHelper.esVacio(nombre)) {
            return;
        }
        String desc = JOptionPane.showInputDialog(frame, "Descripción:");
        if (especialidadDAO.insertar(new Especialidad(nombre.trim(), desc != null ? desc.trim() : ""))) {
            JOptionPane.showMessageDialog(frame, "Especialidad registrada.");
            recargarEspecialidades(frame);
        }
    }

    private void desactivarEspecialidad(AdminCatalogoFrame frame) {
        int id = obtenerIdSeleccionado(frame);
        if (id > 0 && especialidadDAO.desactivar(id)) {
            JOptionPane.showMessageDialog(frame, "Especialidad desactivada.");
            recargarEspecialidades(frame);
        }
    }

    private void agregarMedico(AdminCatalogoFrame frame) {
        String dni = JOptionPane.showInputDialog(frame, "DNI médico:");
        String nombres = JOptionPane.showInputDialog(frame, "Nombres:");
        String apellidos = JOptionPane.showInputDialog(frame, "Apellidos:");
        String consultorio = JOptionPane.showInputDialog(frame, "Consultorio:");
        String espIdStr = JOptionPane.showInputDialog(frame, "ID de especialidad (ver tabla especialidades):");
        if (ValidacionHelper.esVacio(espIdStr)) {
            return;
        }
        int espId;
        try {
            espId = Integer.parseInt(espIdStr.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "ID de especialidad inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Medico medico = new Medico();
        medico.setDni(dni.trim());
        medico.setNombres(nombres.trim());
        medico.setApellidos(apellidos.trim());
        medico.setEspecialidadId(espId);
        medico.setConsultorio(consultorio != null ? consultorio.trim() : "");
        if (medicoDAO.insertar(medico)) {
            JOptionPane.showMessageDialog(frame, "Médico registrado.");
            recargarMedicos(frame);
        }
    }

    private void desactivarMedico(AdminCatalogoFrame frame) {
        int id = obtenerIdSeleccionado(frame);
        if (id > 0 && medicoDAO.desactivar(id)) {
            JOptionPane.showMessageDialog(frame, "Médico desactivado.");
            recargarMedicos(frame);
        }
    }

    private void agregarUsuario(AdminCatalogoFrame frame) {
        String username = JOptionPane.showInputDialog(frame, "Usuario:");
        String password = JOptionPane.showInputDialog(frame, "Contraseña:");
        String rol = (String) JOptionPane.showInputDialog(frame, "Rol:", "Rol",
                JOptionPane.QUESTION_MESSAGE, null,
                new String[] { RolConstantes.ADMINISTRADOR, RolConstantes.ADMISION, RolConstantes.ENFERMERIA, RolConstantes.MEDICO },
                RolConstantes.ADMISION);
        String nombres = JOptionPane.showInputDialog(frame, "Nombres:");
        String apellidos = JOptionPane.showInputDialog(frame, "Apellidos:");
        if (ValidacionHelper.esVacio(username) || ValidacionHelper.esVacio(password) || rol == null) {
            JOptionPane.showMessageDialog(frame, "Complete usuario, contraseña y rol.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(username.trim());
        usuario.setPassword(password.trim());
        usuario.setRol(rol);
        usuario.setNombres(nombres != null ? nombres.trim() : "");
        usuario.setApellidos(apellidos != null ? apellidos.trim() : "");
        if (usuarioDAO.insertar(usuario)) {
            JOptionPane.showMessageDialog(frame, "Usuario registrado.");
            recargarUsuarios(frame);
        }
    }

    private void desactivarUsuario(AdminCatalogoFrame frame) {
        int id = obtenerIdSeleccionado(frame);
        if (id > 0 && usuarioDAO.desactivar(id)) {
            JOptionPane.showMessageDialog(frame, "Usuario desactivado.");
            recargarUsuarios(frame);
        }
    }

    private void recargarEspecialidades(AdminCatalogoFrame frame) {
        frame.getModelo().setRowCount(0);
        for (Especialidad e : especialidadDAO.listarActivas()) {
            frame.getModelo().addRow(new Object[] { e.getId(), e.getNombre(), e.getDescripcion() });
        }
    }

    private void recargarMedicos(AdminCatalogoFrame frame) {
        frame.getModelo().setRowCount(0);
        for (Medico m : medicoDAO.listarActivos()) {
            frame.getModelo().addRow(new Object[] { m.getId(), m.getDni(), m.getNombres(), m.getApellidos(), m.getEspecialidadNombre() });
        }
    }

    private void recargarUsuarios(AdminCatalogoFrame frame) {
        frame.getModelo().setRowCount(0);
        for (Usuario u : usuarioDAO.listarActivos()) {
            frame.getModelo().addRow(new Object[] { u.getId(), u.getUsername(), u.getRol(), u.getNombres(), u.getApellidos() });
        }
    }

    private int obtenerIdSeleccionado(AdminCatalogoFrame frame) {
        int fila = frame.getTabla().getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(frame, "Seleccione un registro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        return (int) frame.getModelo().getValueAt(fila, 0);
    }

    private void volverMenu(AdminCatalogoFrame frame) {
        frame.dispose();
        new MenuFrame().setVisible(true);
    }
}
