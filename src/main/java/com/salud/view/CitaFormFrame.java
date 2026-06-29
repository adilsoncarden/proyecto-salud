package com.salud.view;

import com.salud.model.Especialidad;
import com.salud.model.Medico;
import com.salud.model.Paciente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class CitaFormFrame extends JFrame {

    private final JComboBox<Paciente> cmbPaciente = new JComboBox<>();
    private final JComboBox<Especialidad> cmbEspecialidad = new JComboBox<>();
    private final JComboBox<Medico> cmbMedico = new JComboBox<>();
    private final JTextField txtFecha = new JTextField();
    private final JTextField txtHora = new JTextField();
    private final JButton btnGuardar = new JButton("Registrar Cita");
    private final JButton btnCancelar = new JButton("Cancelar");

    public CitaFormFrame() {
        setTitle("Registrar Cita");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 280);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        panel.add(new JLabel("Paciente:"));
        panel.add(cmbPaciente);
        panel.add(new JLabel("Especialidad:"));
        panel.add(cmbEspecialidad);
        panel.add(new JLabel("Médico:"));
        panel.add(cmbMedico);
        panel.add(new JLabel("Fecha (yyyy-MM-dd):"));
        panel.add(txtFecha);
        panel.add(new JLabel("Hora (HH:mm):"));
        panel.add(txtHora);
        panel.add(btnGuardar);
        panel.add(btnCancelar);
        add(panel);
    }

    public JComboBox<Paciente> getCmbPaciente() { return cmbPaciente; }
    public JComboBox<Especialidad> getCmbEspecialidad() { return cmbEspecialidad; }
    public JComboBox<Medico> getCmbMedico() { return cmbMedico; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JTextField getTxtHora() { return txtHora; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}
